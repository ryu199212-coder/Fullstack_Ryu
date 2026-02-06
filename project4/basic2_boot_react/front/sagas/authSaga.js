import { call, put, takeLatest } from "redux-saga/effects";
import Cookies from "js-cookie"; 
import api from "../api/axios";
import Router from "next/router";
import { message } from "antd";
import {
  signupRequest, signupSuccess, signupFailure,
  loginRequest, loginSuccess, loginFailure,
  refreshTokenRequest, refreshTokenSuccess, refreshTokenFailure,
  logoutRequest, logout, logoutFailure,
  updateNicknameRequest, updateNicknameSuccess, updateNicknameFailure,
  updateProfileImageRequest, updateProfileImageSuccess, updateProfileImageFailure,
} from "../reducers/authReducer";

const signupApi = (formData) => api.post("/auth/signup", formData, { headers: { "Content-Type": "multipart/form-data" } });
const loginApi = (payload) => api.post("/auth/login", payload);
const refreshApi = () => api.post("/auth/refresh");
const logoutApi = () => api.post("/auth/logout");

const updateNicknameApi = (payload) => 
  api.patch(`/auth/${payload.userId}/nickname`, null, {
    params: { nickname: payload.nickname }
  });

// ✅ 수정: Authorization 헤더에 토큰을 명시적으로 주입
const updateProfileImageApi = (payload) => {
  const formData = new FormData();
  formData.append("ufile", payload.file); 
  const token = typeof window !== "undefined" ? localStorage.getItem("accessToken") : null;
  
  return api.post(`/auth/${payload.userId}/profile-image`, formData, {
    headers: { 
      "Content-Type": "multipart/form-data",
      "Authorization": token ? `Bearer ${token}` : "" 
    },
  });
};

export function* signup(action) {
  try {
    yield call(signupApi, action.payload);
    yield put(signupSuccess());
    message.success("회원가입 완료!");
  } catch (err) {
    yield put(signupFailure(err.response?.data?.error || err.message));
  }
}

export function* login(action) {
  try {
    const { data } = yield call(loginApi, action.payload);
    const { accessToken, user } = data;
    if (accessToken && user) { 
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("user", JSON.stringify(user)); 
      Cookies.set("accessToken", accessToken);
      yield put(loginSuccess({ user, accessToken }));
      message.success(`${user.nickname}님 환영합니다!`);
      Router.push("/"); 
    }
  } catch (err) {
    yield put(loginFailure(err.message));
  }
}

export function* refresh() {
  try {
    const { data } = yield call(refreshApi);
    const newAccessToken = data?.accessToken;
    if (newAccessToken) {
      localStorage.setItem("accessToken", newAccessToken);
      Cookies.set("accessToken", newAccessToken);
      yield put(refreshTokenSuccess({ accessToken: newAccessToken }));
    }
  } catch (err) { 
    yield put(refreshTokenFailure(err.message));
  }
}

export function* logoutFlow() {
  try {
    yield call(logoutApi); 
  } finally {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("user");
    Cookies.remove("accessToken");
    yield put(logout());
    Router.push("/");
  }
}

export function* updateNickname(action) {
  try {
    const { data } = yield call(updateNicknameApi, action.payload);
    yield put(updateNicknameSuccess({ user: data }));
    if (typeof window !== "undefined") {
        localStorage.setItem("user", JSON.stringify(data));
    }
    message.success("닉네임이 변경되었습니다.");
  } catch (err) {
    yield put(updateNicknameFailure(err.response?.data?.error || err.message));
    message.error("닉네임 변경 실패");
  }
}

export function* updateProfileImage(action) {
  try {
    const { data } = yield call(updateProfileImageApi, action.payload);
    yield put(updateProfileImageSuccess({ user: data }));
    if (typeof window !== "undefined") {
        localStorage.setItem("user", JSON.stringify(data));
    }
    message.success("프로필 이미지가 변경되었습니다!");
  } catch (err) {
    yield put(updateProfileImageFailure(err.response?.data?.error || err.message));
    message.error("이미지 업로드 실패");
  }
}

export default function* authSaga() {
  yield takeLatest(signupRequest.type, signup);
  yield takeLatest(loginRequest.type, login);
  yield takeLatest(refreshTokenRequest.type, refresh);
  yield takeLatest(logoutRequest.type, logoutFlow);
  yield takeLatest(updateNicknameRequest.type, updateNickname);
  yield takeLatest(updateProfileImageRequest.type, updateProfileImage);
}