// Mock 즐겨찾기 서비스 (localStorage 기반)

export interface FavoriteDto {
  userId: string;
  recipeId: number;
  createdAt: string;
}

// Mock 즐겨찾기 서비스
class FavoriteService {
  private readonly STORAGE_KEY = 'favorites';

  // 사용자의 즐겨찾기 목록 가져오기
  async getFavorites(userId: string): Promise<number[]> {
    await this.delay(200);
    
    const favoritesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!favoritesStr) return [];
    
    try {
      const allFavorites: FavoriteDto[] = JSON.parse(favoritesStr);
      return allFavorites
        .filter(fav => fav.userId === userId)
        .map(fav => fav.recipeId);
    } catch {
      return [];
    }
  }

  // 즐겨찾기 추가
  async addFavorite(userId: string, recipeId: number): Promise<void> {
    await this.delay(200);
    
    const favoritesStr = localStorage.getItem(this.STORAGE_KEY);
    const favorites: FavoriteDto[] = favoritesStr ? JSON.parse(favoritesStr) : [];
    
    // 중복 체크
    const exists = favorites.some(
      fav => fav.userId === userId && fav.recipeId === recipeId
    );
    
    if (!exists) {
      favorites.push({
        userId,
        recipeId,
        createdAt: new Date().toISOString()
      });
      
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(favorites));
    }
  }

  // 즐겨찾기 제거
  async removeFavorite(userId: string, recipeId: number): Promise<void> {
    await this.delay(200);
    
    const favoritesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!favoritesStr) return;
    
    try {
      const favorites: FavoriteDto[] = JSON.parse(favoritesStr);
      const filtered = favorites.filter(
        fav => !(fav.userId === userId && fav.recipeId === recipeId)
      );
      
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(filtered));
    } catch {
      // 오류 발생시 무시
    }
  }

  // 즐겨찾기 토글
  async toggleFavorite(userId: string, recipeId: number): Promise<boolean> {
    const favorites = await this.getFavorites(userId);
    const isFavorite = favorites.includes(recipeId);
    
    if (isFavorite) {
      await this.removeFavorite(userId, recipeId);
      return false;
    } else {
      await this.addFavorite(userId, recipeId);
      return true;
    }
  }

  // 네트워크 지연 시뮬레이션
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

export const favoriteService = new FavoriteService();
