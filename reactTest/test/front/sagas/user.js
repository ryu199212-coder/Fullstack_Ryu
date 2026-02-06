import { all, fork, call, put, takeLatest } from 'redux-saga/effects';
import axios from 'axios';
import {
  LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
  LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
  SIGN_UP_REQUEST, SIGN_UP_SUCCESS, SIGN_UP_FAILURE,
  LOAD_USERS_REQUEST, LOAD_USERS_SUCCESS, LOAD_USERS_FAILURE,
  UPDATE_NICKNAME_REQUEST, UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
  DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE
} from '../reducers/user';

const client = axios.create({
  baseURL: 'http://localhost:3065',
  withCredentials: true,
});

export function logoutApi() {
  return client.post('/user/logout');
}
export function* logout() {
  try {
    yield call(logoutApi);
    yield put({ type: LOG_OUT_SUCCESS });
  } catch (err) {
    yield put({ type: LOG_OUT_FAILURE, error: err.response?.data || err.message });
  }
}
function* watchLogout() {
  yield takeLatest(LOG_OUT_REQUEST, logout);
}

export function loginApi(data) {
  return client.post('/user/login', data);
}
export function* login(action) {
  try {
    const result = yield call(loginApi, action.data);
    const user = {
      id: result.data.APP_USER_ID,
      email: result.data.EMAIL,
      nickname: result.data.NICKNAME,
    };
    yield put({ type: LOG_IN_SUCCESS, data: user });
  } catch (err) {
    yield put({ type: LOG_IN_FAILURE, error: err.response?.data || err.message });
  }
}
function* watchLogin() {
  yield takeLatest(LOG_IN_REQUEST, login);
}

export function signUpApi(data) {
  return client.post('/user/register', data);
}
export function* signUp(action) {
  try {
    yield call(signUpApi, action.data);
    yield put({ type: SIGN_UP_SUCCESS });
  } catch (err) {
    yield put({ type: SIGN_UP_FAILURE, error: err.response?.data || err.message });
  }
}
function* watchSignUp() {
  yield takeLatest(SIGN_UP_REQUEST, signUp);
}

export function loadUsersApi() {
  return client.get('/user');
}
export function* loadUsers() {
  try {
    const result = yield call(loadUsersApi);
    const users = result.data.map((u) => ({
      id: u.APP_USER_ID,
      email: u.EMAIL,
      nickname: u.NICKNAME,
    }));
    yield put({ type: LOAD_USERS_SUCCESS, data: users });
  } catch (err) {
    yield put({ type: LOAD_USERS_FAILURE, error: err.response?.data || err.message });
  }
}
function* watchLoadUsers() {
  yield takeLatest(LOAD_USERS_REQUEST, loadUsers);
}

export function updateNicknameApi(data) {
  return client.patch(`/user/${data.id}/nickname`, { nickname: data.nickname });
}
export function* updateNickname(action) {
  try {
    yield call(updateNicknameApi, action.data);
    yield put({ type: UPDATE_NICKNAME_SUCCESS, data: { id: action.data.id, nickname: action.data.nickname } });
  } catch (err) {
    yield put({ type: UPDATE_NICKNAME_FAILURE, error: err.response?.data || err.message });
  }
}
function* watchUpdateNickname() {
  yield takeLatest(UPDATE_NICKNAME_REQUEST, updateNickname);
}

export function deleteUserApi(id) {
  return client.delete(`/user/${id}`);
}
export function* deleteUser(action) {
  try {
    yield call(deleteUserApi, action.data.id);
    yield put({ type: DELETE_USER_SUCCESS, data: { id: action.data.id } });
  } catch (err) {
    yield put({ type: DELETE_USER_FAILURE, error: err.response?.data || err.message });
  }
}
function* watchDeleteUser() {
  yield takeLatest(DELETE_USER_REQUEST, deleteUser);
}

export default function* userSaga() {
  yield all([
    fork(watchLogin),
    fork(watchLogout),
    fork(watchSignUp),
    fork(watchLoadUsers),
    fork(watchUpdateNickname),
    fork(watchDeleteUser),
  ]);
}