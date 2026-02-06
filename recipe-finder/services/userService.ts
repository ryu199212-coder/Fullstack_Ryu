// Mock 사용자 서비스 (localStorage 기반)
import { UserDto } from './authService';

// 사용자 프로필 수정 DTO
export interface UpdateProfileDto {
  name?: string;
  profileImage?: string;
  phone?: string;
}

// 구독 정보
export interface SubscriptionDto {
  plan: 'none' | 'monthly' | 'yearly';
  startDate?: string;
  endDate?: string;
}

// TOP 작성자 정보
export interface TopAuthorDto {
  authorId: string;
  name: string;
  totalViews: number;
  totalLikes: number;
  recipeCount: number;
  score: number;
}

// Mock 사용자 서비스
class UserService {
  // 프로필 조회
  async getProfile(userId: string): Promise<UserDto> {
    await this.delay(200);
    
    const userStr = localStorage.getItem('user');
    if (!userStr) {
      throw new Error('사용자를 찾을 수 없습니다.');
    }
    
    try {
      const user: UserDto = JSON.parse(userStr);
      if (user.id === userId) {
        return user;
      }
      throw new Error('사용자를 찾을 수 없습니다.');
    } catch {
      throw new Error('프로필 조회에 실패했습니다.');
    }
  }

  // 프로필 수정
  async updateProfile(userId: string, updateData: UpdateProfileDto): Promise<UserDto> {
    await this.delay(300);
    
    const userStr = localStorage.getItem('user');
    if (!userStr) {
      throw new Error('사용자를 찾을 수 없습니다.');
    }
    
    try {
      const user: UserDto = JSON.parse(userStr);
      
      const updatedUser: UserDto = {
        ...user,
        ...updateData
      };
      
      localStorage.setItem('user', JSON.stringify(updatedUser));
      return updatedUser;
    } catch {
      throw new Error('프로필 수정에 실패했습니다.');
    }
  }

  // TOP 20 작성자 조회 (Mock 데이터)
  async getTopAuthors(): Promise<TopAuthorDto[]> {
    await this.delay(300);
    
    // Mock TOP 작성자 데이터
    const mockAuthors: TopAuthorDto[] = [
      { authorId: '1', name: '한식요리사', totalViews: 5245, totalLikes: 285, recipeCount: 15, score: 742.5 },
      { authorId: '2', name: '이탈리아셰프', totalViews: 4830, totalLikes: 245, recipeCount: 12, score: 656.5 },
      { authorId: '3', name: '일식요리사', totalViews: 4520, totalLikes: 230, recipeCount: 10, score: 605.0 },
      { authorId: '4', name: '중식대가', totalViews: 4200, totalLikes: 210, recipeCount: 11, score: 577.0 },
      { authorId: '5', name: '디저트마스터', totalViews: 3900, totalLikes: 200, recipeCount: 9, score: 528.0 },
      { authorId: '6', name: '건강요리사', totalViews: 3600, totalLikes: 180, recipeCount: 8, score: 486.0 },
      { authorId: '7', name: '프랑스요리사', totalViews: 3300, totalLikes: 165, recipeCount: 7, score: 444.5 },
      { authorId: '8', name: '멕시코셰프', totalViews: 3000, totalLikes: 150, recipeCount: 7, score: 401.0 },
      { authorId: '9', name: '인도셰프', totalViews: 2800, totalLikes: 140, recipeCount: 6, score: 372.0 },
      { authorId: '10', name: '치킨마스터', totalViews: 2600, totalLikes: 130, recipeCount: 6, score: 347.0 },
      { authorId: '11', name: '스시마스터', totalViews: 2400, totalLikes: 120, recipeCount: 5, score: 310.0 },
      { authorId: '12', name: '스테이크셰프', totalViews: 2200, totalLikes: 110, recipeCount: 5, score: 275.0 },
      { authorId: '13', name: '베트남셰프', totalViews: 2000, totalLikes: 100, recipeCount: 4, score: 258.0 },
      { authorId: '14', name: '케이크마스터', totalViews: 1800, totalLikes: 90, recipeCount: 4, score: 233.0 },
      { authorId: '15', name: '파스타마스터', totalViews: 1600, totalLikes: 80, recipeCount: 3, score: 206.0 },
      { authorId: '16', name: '샐러드셰프', totalViews: 1400, totalLikes: 70, recipeCount: 3, score: 181.0 },
      { authorId: '17', name: '스프셰프', totalViews: 1200, totalLikes: 60, recipeCount: 3, score: 156.0 },
      { authorId: '18', name: '면요리사', totalViews: 1000, totalLikes: 50, recipeCount: 2, score: 129.0 },
      { authorId: '19', name: '빵집주인', totalViews: 800, totalLikes: 40, recipeCount: 2, score: 104.0 },
      { authorId: '20', name: '카페사장', totalViews: 600, totalLikes: 30, recipeCount: 2, score: 79.0 },
    ];
    
    return mockAuthors;
  }

  // 구독 상태 조회
  async getSubscriptionStatus(userId: string): Promise<SubscriptionDto> {
    await this.delay(200);
    
    const userStr = localStorage.getItem('user');
    if (!userStr) {
      return { plan: 'none' };
    }
    
    try {
      const user: UserDto = JSON.parse(userStr);
      return {
        plan: user.subscription || 'none',
        startDate: new Date().toISOString(),
        endDate: user.subscription !== 'none' 
          ? new Date(Date.now() + 30 * 24 * 60 * 60 * 1000).toISOString()
          : undefined
      };
    } catch {
      return { plan: 'none' };
    }
  }

  // 구독 신청
  async subscribe(userId: string, plan: 'monthly' | 'yearly'): Promise<SubscriptionDto> {
    await this.delay(500);
    
    const userStr = localStorage.getItem('user');
    if (!userStr) {
      throw new Error('사용자를 찾을 수 없습니다.');
    }
    
    try {
      const user: UserDto = JSON.parse(userStr);
      user.subscription = plan;
      localStorage.setItem('user', JSON.stringify(user));
      
      return {
        plan,
        startDate: new Date().toISOString(),
        endDate: new Date(Date.now() + (plan === 'yearly' ? 365 : 30) * 24 * 60 * 60 * 1000).toISOString()
      };
    } catch {
      throw new Error('구독 신청에 실패했습니다.');
    }
  }

  // 구독 취소
  async cancelSubscription(userId: string): Promise<void> {
    await this.delay(300);
    
    const userStr = localStorage.getItem('user');
    if (!userStr) return;
    
    try {
      const user: UserDto = JSON.parse(userStr);
      user.subscription = 'none';
      localStorage.setItem('user', JSON.stringify(user));
    } catch {
      throw new Error('구독 취소에 실패했습니다.');
    }
  }

  // 관리자 추가 (개발/테스트용)
  async addAdmin(userId: string, secretKey: string): Promise<void> {
    await this.delay(300);
    
    if (secretKey !== 'admin-secret-key-2025') {
      throw new Error('잘못된 비밀키입니다.');
    }
    
    // Mock: 관리자 권한은 이메일로 체크하므로 여기서는 성공만 반환
    console.log('관리자 추가 성공 (Mock)');
  }

  // 프로필 이미지 업로드 (Mock)
  async uploadProfileImage(userId: string, file: File): Promise<string> {
    await this.delay(800);
    
    // Mock: FileReader로 이미지를 base64로 변환하여 localStorage에 저장
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      
      reader.onload = async (e) => {
        try {
          const imageUrl = e.target?.result as string;
          
          // 사용자 정보 업데이트
          const userStr = localStorage.getItem('user');
          if (userStr) {
            const user: UserDto = JSON.parse(userStr);
            user.profileImage = imageUrl;
            localStorage.setItem('user', JSON.stringify(user));
          }
          
          resolve(imageUrl);
        } catch (error) {
          reject(new Error('이미지 업로드에 실패했습니다.'));
        }
      };
      
      reader.onerror = () => {
        reject(new Error('이미지 읽기에 실패했습니다.'));
      };
      
      reader.readAsDataURL(file);
    });
  }

  // 프로필 이미지 삭제 (Mock)
  async deleteProfileImage(userId: string): Promise<void> {
    await this.delay(300);
    
    const userStr = localStorage.getItem('user');
    if (userStr) {
      const user: UserDto = JSON.parse(userStr);
      delete user.profileImage;
      localStorage.setItem('user', JSON.stringify(user));
    }
  }

  // 비밀번호 변경 (Mock)
  async changePassword(userId: string, passwordData: { currentPassword: string; newPassword: string; confirmPassword: string }): Promise<void> {
    await this.delay(500);
    
    // Mock: 비밀번호 변경 시뮬레이션
    if (passwordData.currentPassword.length < 6) {
      throw new Error('현재 비밀번호가 올바르지 않습니다.');
    }
    
    if (passwordData.newPassword.length < 6) {
      throw new Error('새 비밀번호는 최소 6자 이상이어야 합니다.');
    }
    
    if (passwordData.newPassword !== passwordData.confirmPassword) {
      throw new Error('새 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
    }
    
    // Mock: 실제로는 서버에 비밀번호 변경 요청
    console.log('비밀번호 변경 성공 (Mock)');
  }

  // 네트워크 지연 시뮬레이션
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

export const userService = new UserService();