// Mock 인증 서비스 (localStorage 기반)

export interface UserDto {
  id: string;
  email: string;
  name: string;
  phone: string;
  profileImage?: string;
  subscription?: 'none' | 'monthly' | 'yearly';
  createdAt?: string;
}

export interface LoginDto {
  email: string;
  password: string;
}

export interface SignupDto {
  email: string;
  password: string;
  name: string;
  phone: string;
  profileImage?: string;
}

export interface AuthResponse {
  user: UserDto;
  token: string;
}

// Mock 인증 서비스
class AuthService {
  // 로그인
  async login(loginData: LoginDto): Promise<AuthResponse> {
    // Mock 로그인 - 모든 이메일/비밀번호 조합 허용
    await this.delay(500); // 네트워크 지연 시뮬레이션
    
    const mockUser: UserDto = {
      id: Date.now().toString(),
      email: loginData.email,
      name: loginData.email.split('@')[0],
      phone: '',
      subscription: 'none',
      createdAt: new Date().toISOString()
    };
    
    const mockToken = 'mock-jwt-token-' + Date.now();
    
    localStorage.setItem('authToken', mockToken);
    localStorage.setItem('user', JSON.stringify(mockUser));
    
    return {
      user: mockUser,
      token: mockToken
    };
  }

  // 회원가입
  async signup(signupData: SignupDto): Promise<AuthResponse> {
    await this.delay(500);
    
    const mockUser: UserDto = {
      id: Date.now().toString(),
      email: signupData.email,
      name: signupData.name,
      phone: signupData.phone,
      profileImage: signupData.profileImage,
      subscription: 'none',
      createdAt: new Date().toISOString()
    };
    
    const mockToken = 'mock-jwt-token-' + Date.now();
    
    localStorage.setItem('authToken', mockToken);
    localStorage.setItem('user', JSON.stringify(mockUser));
    
    return {
      user: mockUser,
      token: mockToken
    };
  }

  // 소셜 로그인
  async socialLogin(provider: 'google' | 'naver' | 'kakao', code: string): Promise<AuthResponse> {
    await this.delay(800);
    
    const providerNames = {
      google: '구글',
      naver: '네이버',
      kakao: '카카오'
    };
    
    const mockUser: UserDto = {
      id: Date.now().toString(),
      email: `${provider}user@${provider}.com`,
      name: `${providerNames[provider]} 사용자`,
      phone: '',
      profileImage: `https://ui-avatars.com/api/?name=${providerNames[provider]}&background=random`,
      subscription: 'none',
      createdAt: new Date().toISOString()
    };
    
    const mockToken = 'mock-jwt-token-' + Date.now();
    
    localStorage.setItem('authToken', mockToken);
    localStorage.setItem('user', JSON.stringify(mockUser));
    
    return {
      user: mockUser,
      token: mockToken
    };
  }

  // 현재 사용자 정보 가져오기
  async getCurrentUser(): Promise<UserDto | null> {
    const userStr = localStorage.getItem('user');
    if (!userStr) return null;
    
    try {
      return JSON.parse(userStr);
    } catch {
      return null;
    }
  }

  // 로그아웃
  async logout(): Promise<void> {
    await this.delay(200);
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
  }

  // 네트워크 지연 시뮬레이션
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

export const authService = new AuthService();
