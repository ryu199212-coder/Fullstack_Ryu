import { projectId, publicAnonKey } from '../utils/supabase/info';

// Supabase API 기본 설정
export const API_BASE_URL = `https://${projectId}.supabase.co/functions/v1/make-server-9dfbea58`;

// 인증 토큰 가져오기 헬퍼
export const getAuthToken = (): string => {
  return localStorage.getItem('authToken') || publicAnonKey;
};

// API 엔드포인트 정의
export const API_ENDPOINTS = {
  // 인증 관련
  auth: {
    login: '/auth/login',
    signup: '/auth/signup',
    socialLogin: '/auth/social-login',
    getCurrentUser: '/auth/me',
  },
  // 프로필 관련
  profile: {
    get: '/profile',
    update: '/profile',
  },
  // 레시피 관련
  recipes: {
    getAll: '/recipes',
    getById: (id: number) => `/recipes/${id}`,
    create: '/recipes',
    update: (id: number) => `/recipes/${id}`,
    delete: (id: number) => `/recipes/${id}`,
    view: (id: number) => `/recipes/${id}/view`,
    like: (id: number) => `/recipes/${id}/like`,
    unlike: (id: number) => `/recipes/${id}/like`,
    togglePremium: (id: number) => `/recipes/${id}/premium`,
  },
  // 즐겨찾기 관련
  favorites: {
    getAll: '/favorites',
    add: (recipeId: number) => `/favorites/${recipeId}`,
    remove: (recipeId: number) => `/favorites/${recipeId}`,
  },
  // TOP 작성자
  topAuthors: '/top-authors',
  // 구독 관련
  subscription: {
    getStatus: '/subscription/status',
    subscribe: '/subscription/subscribe',
    cancel: '/subscription/cancel',
  },
  // 관리자
  admin: {
    add: '/admin/add',
  },
};

// HTTP 메서드 타입
export type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH';

// API 에러 타입
export interface ApiError {
  message: string;
  code?: string;
  status?: number;
}

// API 응답 타입
export interface ApiResponse<T> {
  success: boolean;
  data?: T;
  error?: ApiError;
  message?: string;
}