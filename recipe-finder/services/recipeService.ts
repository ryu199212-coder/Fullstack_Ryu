// Mock 레시피 서비스 (localStorage 기반)
import { Recipe } from '../components/RecipeCard';

export interface CreateRecipeDto {
  title: string;
  category: string;
  image: string;
  cookTime: string;
  difficulty: string;
  servings: number;
  description: string;
  ingredients: string[];
  instructions: string[];
  isPremium?: boolean;
}

export interface UpdateRecipeDto extends Partial<CreateRecipeDto> {}

// Mock 사용자 레시피 서비스
class UserRecipeService {
  private readonly STORAGE_KEY = 'userRecipes';

  // 사용자 레시피 목록 가져오기
  async getUserRecipes(userId: string): Promise<Recipe[]> {
    await this.delay(200);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!recipesStr) return [];
    
    try {
      const allRecipes: Recipe[] = JSON.parse(recipesStr);
      return allRecipes.filter(recipe => recipe.userId === userId);
    } catch {
      return [];
    }
  }

  // 레시피 생성
  async createRecipe(userId: string, recipeData: CreateRecipeDto): Promise<Recipe> {
    await this.delay(300);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    const recipes: Recipe[] = recipesStr ? JSON.parse(recipesStr) : [];
    
    const newRecipe: Recipe = {
      ...recipeData,
      id: Date.now(),
      userId,
      author: this.getUserName(),
      views: 0,
      likes: 0,
      createdAt: new Date().toISOString()
    };
    
    recipes.push(newRecipe);
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(recipes));
    
    return newRecipe;
  }

  // 레시피 수정
  async updateRecipe(recipeId: number, userId: string, updateData: UpdateRecipeDto): Promise<Recipe> {
    await this.delay(300);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!recipesStr) {
      throw new Error('레시피를 찾을 수 없습니다.');
    }
    
    try {
      const recipes: Recipe[] = JSON.parse(recipesStr);
      const index = recipes.findIndex(r => r.id === recipeId && r.userId === userId);
      
      if (index === -1) {
        throw new Error('레시피를 찾을 수 없거나 수정 권한이 없습니다.');
      }
      
      recipes[index] = {
        ...recipes[index],
        ...updateData
      };
      
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(recipes));
      return recipes[index];
    } catch (error) {
      throw new Error('레시피 수정에 실패했습니다.');
    }
  }

  // 레시피 삭제
  async deleteRecipe(recipeId: number, userId: string): Promise<void> {
    await this.delay(200);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!recipesStr) return;
    
    try {
      const recipes: Recipe[] = JSON.parse(recipesStr);
      const filtered = recipes.filter(r => !(r.id === recipeId && r.userId === userId));
      
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(filtered));
    } catch {
      throw new Error('레시피 삭제에 실패했습니다.');
    }
  }

  // 현재 사용자 이름 가져오기
  private getUserName(): string {
    const userStr = localStorage.getItem('user');
    if (!userStr) return '익명';
    
    try {
      const user = JSON.parse(userStr);
      return user.name || '익명';
    } catch {
      return '익명';
    }
  }

  // 네트워크 지연 시뮬레이션
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

// Mock 레시피 서비스 (전체 레시피)
class RecipeService {
  private readonly STORAGE_KEY = 'allRecipes';

  // 모든 레시피 가져오기
  async getAllRecipes(): Promise<Recipe[]> {
    await this.delay(300);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!recipesStr) return [];
    
    try {
      return JSON.parse(recipesStr);
    } catch {
      return [];
    }
  }

  // 레시피 상세 조회
  async getRecipeById(recipeId: number): Promise<Recipe | null> {
    await this.delay(200);
    
    const recipes = await this.getAllRecipes();
    return recipes.find(r => r.id === recipeId) || null;
  }

  // 레시피 조회수 증가
  async incrementViews(recipeId: number): Promise<void> {
    await this.delay(100);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!recipesStr) return;
    
    try {
      const recipes: Recipe[] = JSON.parse(recipesStr);
      const index = recipes.findIndex(r => r.id === recipeId);
      
      if (index !== -1) {
        recipes[index].views = (recipes[index].views || 0) + 1;
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(recipes));
      }
    } catch {
      // 오류 무시
    }
  }

  // 레시피 좋아요 토글
  async toggleLike(recipeId: number, userId: string): Promise<boolean> {
    await this.delay(200);
    
    const recipesStr = localStorage.getItem(this.STORAGE_KEY);
    if (!recipesStr) return false;
    
    try {
      const recipes: Recipe[] = JSON.parse(recipesStr);
      const index = recipes.findIndex(r => r.id === recipeId);
      
      if (index !== -1) {
        const currentLikes = recipes[index].likes || 0;
        // 간단한 토글 로직 (실제로는 사용자별 좋아요 관리가 필요)
        recipes[index].likes = currentLikes + 1;
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(recipes));
        return true;
      }
      
      return false;
    } catch {
      return false;
    }
  }

  // 프리미엄 레시피 토글 (관리자만)
  async togglePremium(recipeId: number, userId: string): Promise<Recipe> {
    await this.delay(300);
    
    // 관리자 체크 (간단하게 이메일로)
    const userStr = localStorage.getItem('user');
    if (!userStr) {
      throw new Error('권한이 없습니다.');
    }
    
    try {
      const user = JSON.parse(userStr);
      if (user.email !== 'admin@recipe.com') {
        throw new Error('관리자만 프리미엄 레시피를 지정할 수 있습니다.');
      }
      
      const recipesStr = localStorage.getItem(this.STORAGE_KEY);
      if (!recipesStr) {
        throw new Error('레시피를 찾을 수 없습니다.');
      }
      
      const recipes: Recipe[] = JSON.parse(recipesStr);
      const index = recipes.findIndex(r => r.id === recipeId);
      
      if (index === -1) {
        throw new Error('레시피를 찾을 수 없습니다.');
      }
      
      recipes[index].isPremium = !recipes[index].isPremium;
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(recipes));
      
      return recipes[index];
    } catch (error) {
      throw error;
    }
  }

  // 네트워크 지연 시뮬레이션
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

export const userRecipeService = new UserRecipeService();
export const recipeService = new RecipeService();
