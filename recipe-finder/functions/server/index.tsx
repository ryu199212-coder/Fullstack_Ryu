import { Hono } from "npm:hono";
import { cors } from "npm:hono/cors";
import { logger } from "npm:hono/logger";
import { createClient } from "npm:@supabase/supabase-js@2";
import * as kv from "./kv_store.tsx";

const app = new Hono();

// Supabase 클라이언트 생성
const supabase = createClient(
  Deno.env.get('SUPABASE_URL') ?? '',
  Deno.env.get('SUPABASE_SERVICE_ROLE_KEY') ?? '',
);

// Enable logger
app.use('*', logger(console.log));

// Enable CORS for all routes and methods
app.use(
  "/*",
  cors({
    origin: "*",
    allowHeaders: ["Content-Type", "Authorization"],
    allowMethods: ["GET", "POST", "PUT", "DELETE", "OPTIONS"],
    exposeHeaders: ["Content-Length"],
    maxAge: 600,
  }),
);

// 인증 미들웨어
async function authMiddleware(c: any, next: any) {
  const authHeader = c.req.header('Authorization');
  
  if (!authHeader || !authHeader.startsWith('Bearer ')) {
    return c.json({ error: 'Unauthorized - No token provided' }, 401);
  }

  const token = authHeader.split(' ')[1];
  
  // Supabase 인증 확인
  const { data: { user }, error } = await supabase.auth.getUser(token);
  
  if (error || !user) {
    return c.json({ error: 'Unauthorized - Invalid token' }, 401);
  }

  // 사용자 정보를 컨텍스트에 저장
  c.set('userId', user.id);
  c.set('user', user);
  
  await next();
}

// 관리자 미들웨어
async function adminMiddleware(c: any, next: any) {
  const userId = c.get('userId');
  
  // 관리자 목록 확인
  const adminList = await kv.get('admin_users') || [];
  
  if (!adminList.includes(userId)) {
    return c.json({ error: 'Forbidden - Admin access required' }, 403);
  }
  
  await next();
}

// Health check endpoint
app.get("/make-server-9dfbea58/health", (c) => {
  return c.json({ status: "ok" });
});

// =========================
// 인증 API
// =========================

// 회원가입
app.post("/make-server-9dfbea58/auth/signup", async (c) => {
  try {
    const { name, email, password } = await c.req.json();

    if (!name || !email || !password) {
      return c.json({ error: 'Name, email and password are required' }, 400);
    }

    // Supabase Auth를 사용한 사용자 생성
    const { data, error } = await supabase.auth.admin.createUser({
      email,
      password,
      user_metadata: { name },
      // 이메일 서버가 구성되지 않았으므로 자동으로 이메일 확인
      email_confirm: true
    });

    if (error) {
      console.log('Signup error:', error);
      return c.json({ error: `회원가입 실패: ${error.message}` }, 400);
    }

    // 사용자 정보 저장
    const userData = {
      id: data.user!.id,
      name,
      email,
      profileImage: undefined,
      subscription: 'none',
      createdAt: new Date().toISOString(),
    };

    await kv.set(`user:${data.user!.id}`, userData);

    // 세션 생성
    const { data: sessionData, error: signInError } = await supabase.auth.signInWithPassword({
      email,
      password,
    });

    if (signInError) {
      return c.json({ error: `로그인 실패: ${signInError.message}` }, 400);
    }

    return c.json({
      user: userData,
      token: sessionData.session!.access_token,
    });
  } catch (error) {
    console.log('Signup error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 로그인
app.post("/make-server-9dfbea58/auth/login", async (c) => {
  try {
    const { email, password } = await c.req.json();

    if (!email || !password) {
      return c.json({ error: 'Email and password are required' }, 400);
    }

    // Supabase Auth를 사용한 로그인
    const { data, error } = await supabase.auth.signInWithPassword({
      email,
      password,
    });

    if (error) {
      console.log('Login error:', error);
      return c.json({ error: `로그인 실패: ${error.message}` }, 400);
    }

    // 사용자 정보 조회
    const userData = await kv.get(`user:${data.user.id}`);

    if (!userData) {
      return c.json({ error: '사용자 정보를 찾을 수 없습니다' }, 404);
    }

    return c.json({
      user: userData,
      token: data.session!.access_token,
    });
  } catch (error) {
    console.log('Login error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 소셜 로그인
app.post("/make-server-9dfbea58/auth/social-login", async (c) => {
  try {
    const { provider } = await c.req.json();

    if (!provider || !['google', 'facebook', 'github'].includes(provider)) {
      return c.json({ error: 'Invalid provider' }, 400);
    }

    // Supabase OAuth를 사용한 소셜 로그인
    // 참고: Google 소셜 로그인을 사용하려면 https://supabase.com/docs/guides/auth/social-login/auth-google 설정 필요
    const { data, error } = await supabase.auth.signInWithOAuth({
      provider: provider as 'google' | 'facebook' | 'github',
    });

    if (error) {
      console.log('Social login error:', error);
      return c.json({ 
        error: `소셜 로그인 실패: ${error.message}`,
        notice: `${provider} 로그인을 사용하려면 Supabase 콘솔에서 OAuth 설정을 완료해야 합니다. 자세한 내용은 https://supabase.com/docs/guides/auth/social-login/auth-${provider} 참조`
      }, 400);
    }

    return c.json({ url: data.url });
  } catch (error) {
    console.log('Social login error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 현재 사용자 정보 조회
app.get("/make-server-9dfbea58/auth/me", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const userData = await kv.get(`user:${userId}`);

    if (!userData) {
      return c.json({ error: '사용자를 찾을 수 없습니다' }, 404);
    }

    return c.json({ user: userData });
  } catch (error) {
    console.log('Get user error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// =========================
// 레시피 API
// =========================

// 레시피 목록 조회
app.get("/make-server-9dfbea58/recipes", async (c) => {
  try {
    const category = c.req.query('category');
    const search = c.req.query('search');
    const userId = c.req.query('userId'); // 특정 사용자 레시피만 조회

    // 모든 레시피 조회
    const allRecipes = await kv.getByPrefix('recipe:');
    let recipes = allRecipes.map(item => item.value);

    // 필터링
    if (category && category !== '전체') {
      recipes = recipes.filter((r: any) => r.category === category);
    }

    if (search) {
      const searchLower = search.toLowerCase();
      recipes = recipes.filter((r: any) =>
        r.title.toLowerCase().includes(searchLower) ||
        r.description?.toLowerCase().includes(searchLower) ||
        r.ingredients?.some((ing: string) => ing.toLowerCase().includes(searchLower))
      );
    }

    if (userId) {
      recipes = recipes.filter((r: any) => r.userId === userId);
    }

    // 최신순 정렬
    recipes.sort((a: any, b: any) => {
      const dateA = new Date(a.createdAt || 0).getTime();
      const dateB = new Date(b.createdAt || 0).getTime();
      return dateB - dateA;
    });

    return c.json({ recipes });
  } catch (error) {
    console.log('Get recipes error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 레시피 상세 조회
app.get("/make-server-9dfbea58/recipes/:id", async (c) => {
  try {
    const id = c.req.param('id');
    const recipe = await kv.get(`recipe:${id}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    return c.json({ recipe });
  } catch (error) {
    console.log('Get recipe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 레시피 생성
app.post("/make-server-9dfbea58/recipes", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeData = await c.req.json();

    // 레시피 ID 생성
    const recipeId = Date.now();

    // 사용자 정보 조회
    const userData = await kv.get(`user:${userId}`);

    const newRecipe = {
      ...recipeData,
      id: recipeId,
      userId,
      author: userData?.name || 'Unknown',
      authorId: userId,
      views: 0,
      likes: 0,
      isPremium: false, // 기본값 false, 관리자만 변경 가능
      createdAt: new Date().toISOString(),
    };

    // 레시피 저장
    await kv.set(`recipe:${recipeId}`, newRecipe);

    // 사용자 레시피 목록에 추가
    const userRecipeIds = await kv.get(`user_recipes:${userId}`) || [];
    userRecipeIds.push(recipeId);
    await kv.set(`user_recipes:${userId}`, userRecipeIds);

    return c.json({ recipe: newRecipe }, 201);
  } catch (error) {
    console.log('Create recipe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 레시피 수정
app.put("/make-server-9dfbea58/recipes/:id", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeId = c.req.param('id');
    const updateData = await c.req.json();

    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    // 작성자만 수정 가능
    if (recipe.userId !== userId) {
      return c.json({ error: '레시피를 수정할 권한이 없습니다' }, 403);
    }

    const updatedRecipe = {
      ...recipe,
      ...updateData,
      // 수정 불가 필드는 유지
      id: recipe.id,
      userId: recipe.userId,
      author: recipe.author,
      authorId: recipe.authorId,
      views: recipe.views,
      likes: recipe.likes,
      isPremium: recipe.isPremium,
      createdAt: recipe.createdAt,
    };

    await kv.set(`recipe:${recipeId}`, updatedRecipe);

    return c.json({ recipe: updatedRecipe });
  } catch (error) {
    console.log('Update recipe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 레시피 삭제
app.delete("/make-server-9dfbea58/recipes/:id", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeId = c.req.param('id');

    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    // 작성자만 삭제 가능
    if (recipe.userId !== userId) {
      return c.json({ error: '레시피를 삭제할 권한이 없습니다' }, 403);
    }

    // 레시피 삭제
    await kv.del(`recipe:${recipeId}`);

    // 사용자 레시피 목록에서 제거
    const userRecipeIds = await kv.get(`user_recipes:${userId}`) || [];
    const filteredIds = userRecipeIds.filter((id: number) => id !== parseInt(recipeId));
    await kv.set(`user_recipes:${userId}`, filteredIds);

    // 좋아요 카운트 삭제
    await kv.del(`recipe_likes:${recipeId}`);

    return c.json({ message: '레시피가 삭제되었습니다' });
  } catch (error) {
    console.log('Delete recipe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 조회수 증가
app.post("/make-server-9dfbea58/recipes/:id/view", async (c) => {
  try {
    const recipeId = c.req.param('id');
    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    recipe.views = (recipe.views || 0) + 1;
    await kv.set(`recipe:${recipeId}`, recipe);

    return c.json({ views: recipe.views });
  } catch (error) {
    console.log('Increment views error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 좋아요 추가
app.post("/make-server-9dfbea58/recipes/:id/like", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeId = c.req.param('id');

    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    // 이미 좋아요 했는지 확인
    const likeKey = `likes:${recipeId}:${userId}`;
    const alreadyLiked = await kv.get(likeKey);

    if (alreadyLiked) {
      return c.json({ error: '이미 좋아요를 눌렀습니다' }, 400);
    }

    // 좋아요 추가
    await kv.set(likeKey, true);
    recipe.likes = (recipe.likes || 0) + 1;
    await kv.set(`recipe:${recipeId}`, recipe);

    return c.json({ likes: recipe.likes });
  } catch (error) {
    console.log('Like recipe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 좋아요 취소
app.delete("/make-server-9dfbea58/recipes/:id/like", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeId = c.req.param('id');

    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    // 좋아요 여부 확인
    const likeKey = `likes:${recipeId}:${userId}`;
    const alreadyLiked = await kv.get(likeKey);

    if (!alreadyLiked) {
      return c.json({ error: '좋아요를 누르지 않았습니다' }, 400);
    }

    // 좋아요 취소
    await kv.del(likeKey);
    recipe.likes = Math.max((recipe.likes || 0) - 1, 0);
    await kv.set(`recipe:${recipeId}`, recipe);

    return c.json({ likes: recipe.likes });
  } catch (error) {
    console.log('Unlike recipe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 프리미엄 레시피 토글 (관리자 전용)
app.put("/make-server-9dfbea58/recipes/:id/premium", authMiddleware, adminMiddleware, async (c) => {
  try {
    const recipeId = c.req.param('id');
    const { isPremium } = await c.req.json();

    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    recipe.isPremium = isPremium;
    await kv.set(`recipe:${recipeId}`, recipe);

    return c.json({ recipe });
  } catch (error) {
    console.log('Toggle premium error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// =========================
// 즐겨찾기 API
// =========================

// 즐겨찾기 목록 조회
app.get("/make-server-9dfbea58/favorites", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const favoriteIds = await kv.get(`favorites:${userId}`) || [];

    // 레시피 정보 조회
    const recipes = [];
    for (const recipeId of favoriteIds) {
      const recipe = await kv.get(`recipe:${recipeId}`);
      if (recipe) {
        recipes.push(recipe);
      }
    }

    return c.json({ recipes });
  } catch (error) {
    console.log('Get favorites error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 즐겨찾기 추가
app.post("/make-server-9dfbea58/favorites/:recipeId", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeId = parseInt(c.req.param('recipeId'));

    const recipe = await kv.get(`recipe:${recipeId}`);

    if (!recipe) {
      return c.json({ error: '레시피를 찾을 수 없습니다' }, 404);
    }

    const favoriteIds = await kv.get(`favorites:${userId}`) || [];

    if (favoriteIds.includes(recipeId)) {
      return c.json({ error: '이미 즐겨찾기에 추가되었습니다' }, 400);
    }

    favoriteIds.push(recipeId);
    await kv.set(`favorites:${userId}`, favoriteIds);

    return c.json({ message: '즐겨찾기에 추가되었습니다' });
  } catch (error) {
    console.log('Add favorite error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 즐겨찾기 제거
app.delete("/make-server-9dfbea58/favorites/:recipeId", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const recipeId = parseInt(c.req.param('recipeId'));

    const favoriteIds = await kv.get(`favorites:${userId}`) || [];
    const filteredIds = favoriteIds.filter((id: number) => id !== recipeId);

    await kv.set(`favorites:${userId}`, filteredIds);

    return c.json({ message: '즐겨찾기에서 제거되었습니다' });
  } catch (error) {
    console.log('Remove favorite error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// =========================
// TOP 작성자 API
// =========================

// TOP 20 작성자 계산 (조회수×0.5 + 좋아요수×1.5 + 게시글수×2)
app.get("/make-server-9dfbea58/top-authors", async (c) => {
  try {
    // 모든 레시피 조회
    const allRecipes = await kv.getByPrefix('recipe:');
    const recipes = allRecipes.map(item => item.value);

    // 작성자별 통계 계산
    const authorStats: any = {};

    for (const recipe of recipes) {
      const authorId = recipe.userId || recipe.authorId;
      if (!authorId) continue;

      if (!authorStats[authorId]) {
        authorStats[authorId] = {
          authorId,
          name: recipe.author || 'Unknown',
          totalViews: 0,
          totalLikes: 0,
          recipeCount: 0,
          score: 0,
        };
      }

      authorStats[authorId].totalViews += recipe.views || 0;
      authorStats[authorId].totalLikes += recipe.likes || 0;
      authorStats[authorId].recipeCount += 1;
    }

    // 점수 계산 및 정렬
    const topAuthors = Object.values(authorStats)
      .map((author: any) => {
        author.score = 
          author.totalViews * 0.5 +
          author.totalLikes * 1.5 +
          author.recipeCount * 2;
        return author;
      })
      .sort((a: any, b: any) => b.score - a.score)
      .slice(0, 20); // TOP 20

    return c.json({ topAuthors });
  } catch (error) {
    console.log('Get top authors error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// =========================
// 구독 API
// =========================

// 구독 상태 조회
app.get("/make-server-9dfbea58/subscription/status", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const userData = await kv.get(`user:${userId}`);

    if (!userData) {
      return c.json({ error: '사용자를 찾을 수 없습니다' }, 404);
    }

    const subscription = {
      plan: userData.subscription || 'none',
      startDate: userData.subscriptionStartDate,
      endDate: userData.subscriptionEndDate,
    };

    return c.json({ subscription });
  } catch (error) {
    console.log('Get subscription status error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 구독 신청
app.post("/make-server-9dfbea58/subscription/subscribe", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const { plan } = await c.req.json(); // 'monthly' or 'yearly'

    if (!['monthly', 'yearly'].includes(plan)) {
      return c.json({ error: '잘못된 구독 플랜입니다' }, 400);
    }

    const userData = await kv.get(`user:${userId}`);

    if (!userData) {
      return c.json({ error: '사용자를 찾을 수 없습니다' }, 404);
    }

    const startDate = new Date();
    let endDate = new Date();

    if (plan === 'monthly') {
      endDate.setMonth(endDate.getMonth() + 1);
    } else {
      endDate.setFullYear(endDate.getFullYear() + 1);
    }

    userData.subscription = plan;
    userData.subscriptionStartDate = startDate.toISOString();
    userData.subscriptionEndDate = endDate.toISOString();

    await kv.set(`user:${userId}`, userData);

    return c.json({
      message: '구독이 완료되었습니다',
      subscription: {
        plan,
        startDate: startDate.toISOString(),
        endDate: endDate.toISOString(),
      },
    });
  } catch (error) {
    console.log('Subscribe error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// 구독 취소
app.delete("/make-server-9dfbea58/subscription/cancel", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const userData = await kv.get(`user:${userId}`);

    if (!userData) {
      return c.json({ error: '사용자를 찾을 수 없습니다' }, 404);
    }

    userData.subscription = 'none';
    userData.subscriptionStartDate = undefined;
    userData.subscriptionEndDate = undefined;

    await kv.set(`user:${userId}`, userData);

    return c.json({ message: '구독이 취소되었습니다' });
  } catch (error) {
    console.log('Cancel subscription error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// =========================
// 프로필 API
// =========================

// 프로필 수정
app.put("/make-server-9dfbea58/profile", authMiddleware, async (c) => {
  try {
    const userId = c.get('userId');
    const updateData = await c.req.json();

    const userData = await kv.get(`user:${userId}`);

    if (!userData) {
      return c.json({ error: '사용자를 찾을 수 없습니다' }, 404);
    }

    // 허용된 필드만 업데이트
    const allowedFields = ['name', 'profileImage', 'phone'];
    for (const field of allowedFields) {
      if (updateData[field] !== undefined) {
        userData[field] = updateData[field];
      }
    }

    await kv.set(`user:${userId}`, userData);

    return c.json({ user: userData });
  } catch (error) {
    console.log('Update profile error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

// =========================
// 관리자 API
// =========================

// 관리자 추가 (초기 설정용 - 실제로는 보안을 위해 제거하거나 특수 키 필요)
app.post("/make-server-9dfbea58/admin/add", async (c) => {
  try {
    const { userId, secretKey } = await c.req.json();

    // 간단한 보안 키 (실제로는 환경 변수 사용)
    if (secretKey !== 'admin-secret-key-2026') {
      return c.json({ error: '잘못된 시크릿 키입니다' }, 403);
    }

    const adminList = await kv.get('admin_users') || [];
    
    if (!adminList.includes(userId)) {
      adminList.push(userId);
      await kv.set('admin_users', adminList);
    }

    return c.json({ message: '관리자가 추가되었습니다' });
  } catch (error) {
    console.log('Add admin error:', error);
    return c.json({ error: `서버 오류: ${error.message}` }, 500);
  }
});

Deno.serve(app.fetch);
