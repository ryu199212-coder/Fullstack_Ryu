import { API_BASE_URL, HttpMethod, ApiResponse, ApiError } from './apiConfig';

// API 클라이언트 - 실제 Spring 백엔드 연동 시 사용할 구조
class ApiClient {
  private baseUrl: string;
  private token: string | null = null;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;
    this.loadToken();
  }

  // 토큰 로드
  private loadToken() {
    this.token = localStorage.getItem('authToken');
  }

  // 토큰 설정
  setToken(token: string) {
    this.token = token;
    localStorage.setItem('authToken', token);
  }

  // 토큰 제거
  removeToken() {
    this.token = null;
    localStorage.removeItem('authToken');
  }

  // HTTP 요청 헬퍼
  private async request<T>(
    endpoint: string,
    method: HttpMethod = 'GET',
    data?: any,
    isFormData: boolean = false
  ): Promise<ApiResponse<T>> {
    try {
      const headers: HeadersInit = {};

      if (this.token) {
        headers['Authorization'] = `Bearer ${this.token}`;
      }

      if (!isFormData) {
        headers['Content-Type'] = 'application/json';
      }

      const config: RequestInit = {
        method,
        headers,
      };

      if (data && (method === 'POST' || method === 'PUT' || method === 'PATCH')) {
        config.body = isFormData ? data : JSON.stringify(data);
      }

      const response = await fetch(`${this.baseUrl}${endpoint}`, config);

      if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        throw {
          message: errorData.message || '요청 처리 중 오류가 발생했습니다.',
          code: errorData.code,
          status: response.status,
        } as ApiError;
      }

      const responseData = await response.json();

      return {
        success: true,
        data: responseData,
      };
    } catch (error) {
      console.error('API Error:', error);
      
      if ((error as ApiError).message) {
        return {
          success: false,
          error: error as ApiError,
        };
      }

      return {
        success: false,
        error: {
          message: '네트워크 오류가 발생했습니다.',
        },
      };
    }
  }

  // GET 요청
  async get<T>(endpoint: string): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, 'GET');
  }

  // POST 요청
  async post<T>(endpoint: string, data: any): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, 'POST', data);
  }

  // PUT 요청
  async put<T>(endpoint: string, data: any): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, 'PUT', data);
  }

  // DELETE 요청
  async delete<T>(endpoint: string): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, 'DELETE');
  }

  // PATCH 요청
  async patch<T>(endpoint: string, data: any): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, 'PATCH', data);
  }

  // 파일 업로드 (FormData)
  async uploadFile<T>(endpoint: string, formData: FormData): Promise<ApiResponse<T>> {
    return this.request<T>(endpoint, 'POST', formData, true);
  }
}

// API 클라이언트 싱글톤 인스턴스
export const apiClient = new ApiClient(API_BASE_URL);
