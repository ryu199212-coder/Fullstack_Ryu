import { Trophy, Crown, Medal, Award } from "lucide-react";
import { Recipe } from "./RecipeCard";

interface TopAuthorsProps {
  recipes: Recipe[];
}

interface AuthorRanking {
  author: string;
  count: number;
  isPremiumCount: number;
  totalViews: number;
  totalLikes: number;
  score: number; // 종합 점수
}

export function TopAuthors({ recipes }: TopAuthorsProps) {
  // 이번 달의 레시피만 필터링
  const currentDate = new Date();
  const currentMonth = currentDate.getMonth();
  const currentYear = currentDate.getFullYear();

  const thisMonthRecipes = recipes.filter((recipe) => {
    if (!recipe.createdAt) return true; // 날짜가 없으면 포함 (기존 레시피)
    const recipeDate = new Date(recipe.createdAt);
    return (
      recipeDate.getMonth() === currentMonth &&
      recipeDate.getFullYear() === currentYear
    );
  });

  // 작성자별 레시피 수 계산
  const authorMap = new Map<string, AuthorRanking>();
  
  thisMonthRecipes.forEach((recipe) => {
    const author = recipe.author || "익명";
    const existing = authorMap.get(author);
    
    if (existing) {
      existing.count++;
      if (recipe.isPremium) existing.isPremiumCount++;
      existing.totalViews += recipe.views || 0;
      existing.totalLikes += recipe.likes || 0;
      existing.score = existing.totalViews * 0.5 + existing.totalLikes * 1.5 + existing.count * 2;
    } else {
      authorMap.set(author, {
        author,
        count: 1,
        isPremiumCount: recipe.isPremium ? 1 : 0,
        totalViews: recipe.views || 0,
        totalLikes: recipe.likes || 0,
        score: (recipe.views || 0) * 0.5 + (recipe.likes || 0) * 1.5 + 2,
      });
    }
  });

  // 레시피 수로 정렬하고 TOP 20 추출
  const topAuthors = Array.from(authorMap.values())
    .sort((a, b) => {
      // 종합 점수로 정렬 (조회수 + 좋아요수 + 게시글수)
      return b.score - a.score;
    })
    .slice(0, 20);

  if (topAuthors.length === 0) return null;

  const getRankIcon = (index: number) => {
    if (index === 0) return <Trophy className="w-4 h-4 text-yellow-500" />;
    if (index === 1) return <Medal className="w-4 h-4 text-gray-400" />;
    if (index === 2) return <Medal className="w-4 h-4 text-orange-600" />;
    return <Award className="w-3 h-3 text-orange-400" />;
  };

  const getRankBadgeColor = (index: number) => {
    if (index === 0) return "bg-gradient-to-r from-yellow-400 to-yellow-600 text-white";
    if (index === 1) return "bg-gradient-to-r from-gray-300 to-gray-500 text-white";
    if (index === 2) return "bg-gradient-to-r from-orange-400 to-orange-600 text-white";
    return "bg-orange-100 text-orange-700";
  };

  return (
    <div className="mb-6 bg-gradient-to-r from-orange-50 via-yellow-50 to-orange-50 rounded-lg p-4 border border-orange-200">
      <div className="flex items-center gap-2 mb-3">
        <Trophy className="w-5 h-5 text-orange-500" />
        <h2 className="font-semibold text-lg text-gray-800">이달의 작성자 TOP 20</h2>
        <span className="text-sm text-gray-500">
          ({currentMonth + 1}월)
        </span>
      </div>
      
      <div className="flex flex-wrap gap-2">
        {topAuthors.map((author, index) => (
          <div
            key={author.author}
            className={`inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full text-sm transition-all hover:scale-105 ${getRankBadgeColor(index)}`}
          >
            <span className="flex items-center gap-1">
              {getRankIcon(index)}
              <span className="font-medium">{index + 1}위</span>
            </span>
            <span className="font-semibold">{author.author}</span>
            <span className="text-xs opacity-80">
              ({author.count}개
              {author.isPremiumCount > 0 && (
                <>
                  , <Crown className="w-3 h-3 inline" /> {author.isPremiumCount}
                </>
              )}
              )
            </span>
          </div>
        ))}
      </div>
    </div>
  );
}