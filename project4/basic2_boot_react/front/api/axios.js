import axios from "axios";
import { message } from "antd"; // ✅ 알림창을 위해 추가

const api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8484", 
  withCredentials: true, 
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

// 요청 인터셉터
api.interceptors.request.use(
  (config) => {
    if (typeof window !== "undefined") {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// 응답 인터셉터
api.interceptors.response.use(
  (res) => res,
  async (error) => {
    const original = error.config;
    const status = error.response?.status;
    const errorData = error.response?.data; // ✅ 서버에서 보낸 에러 데이터

    // ✅ [추가] 403 에러이고 서버가 'BANNED' 에러를 보냈을 때 알림창 띄우기
    if (status === 403 && errorData?.error === "BANNED") {
      message.error(errorData.message || "해당 계정은 이용이 정지되었습니다.");
      return Promise.reject(error); // 무한 로딩 방지를 위해 여기서 에러 종료
    }

    // 401(권한 없음) 에러 발생 시에만 재발급 시도
    if (status === 401 && !original._retry) {
      original._retry = true;
      try {
        const { data } = await axios.post(`${api.defaults.baseURL}/auth/refresh`, {}, { withCredentials: true });
        const newAccessToken = data?.accessToken;

        if (typeof window !== "undefined" && newAccessToken) {
          localStorage.setItem("accessToken", newAccessToken);
          document.cookie = `accessToken=${newAccessToken}; path=/`; 
        }

        original.headers.Authorization = `Bearer ${newAccessToken}`;
        return api(original); 
      } catch (refreshErr) {
        console.error("세션 만료: 토큰 재발급에 실패했습니다.");
        
        if (typeof window !== "undefined") {
          if (refreshErr.response?.status === 401 || refreshErr.response?.status === 403) {
             // 차단 유저가 아닐 때만 로그인 이동 로직 검토
          }
        }
        return Promise.reject(refreshErr);
      }
    }

    return Promise.reject(error);
  }
);

export const loadMaterialsAPI = (page) => {
  return api.get(`/api/material/list`, {
    params: { page },
  });
};

export default api;