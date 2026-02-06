import { Card, CardContent, CardFooter } from "./ui/card";
import { Badge } from "./ui/badge";
import { Clock, ChefHat, Heart, Eye, User, Crown } from "lucide-react";
import { ImageWithFallback } from "./figma/ImageWithFallback";
import { Button } from "./ui/button";

export interface Recipe {
  id: number;
  title: string;
  category: string;
  image: string;
  cookTime: string;
  difficulty: "쉬움" | "보통" | "어려움";
  servings: number;
  description: string;
  ingredients: string[];
  instructions: string[];
  views?: number;
  author?: string;
  authorId?: string;
  userId?: string; // 레시피 작성자의 userId
  isPremium?: boolean; // 프리미엄 레시피 여부
  createdAt?: string; // 작성 날짜
  likes?: number; // 좋아요 수
}

interface RecipeCardProps {
  recipe: Recipe;
  onClick: () => void;
  isFavorite?: boolean;
  onToggleFavorite?: (recipeId: number, e: React.MouseEvent) => void;
}

export function RecipeCard({ recipe, onClick, isFavorite, onToggleFavorite }: RecipeCardProps) {
  const difficultyColor = {
    쉬움: "bg-green-100 text-green-800 hover:bg-green-100",
    보통: "bg-yellow-100 text-yellow-800 hover:bg-yellow-100",
    어려움: "bg-red-100 text-red-800 hover:bg-red-100",
  };

  return (
    <Card 
      className="overflow-hidden cursor-pointer transition-all hover:shadow-lg group"
      onClick={onClick}
    >
      <div className="relative h-48 overflow-hidden">
        <ImageWithFallback
          src={recipe.image}
          alt={recipe.title}
          className="w-full h-full object-cover transition-transform duration-300 group-hover:scale-110"
        />
        {recipe.isPremium && (
          <div className="absolute top-2 left-2 z-10">
            <Badge className="bg-gradient-to-r from-yellow-500 to-orange-500 text-white border-0">
              <Crown className="w-3 h-3 mr-1" />
              프리미엄
            </Badge>
          </div>
        )}
        <div className={`absolute top-2 ${recipe.isPremium ? 'right-2' : 'right-2'}`}>
          <Badge variant="secondary" className="bg-white/90 backdrop-blur-sm">
            {recipe.category}
          </Badge>
        </div>
        {onToggleFavorite && (
          <div className={`absolute ${recipe.isPremium ? 'bottom-2 left-2' : 'top-2 left-2'}`}>
            <Button
              size="icon"
              variant="ghost"
              className="w-8 h-8 bg-white/90 backdrop-blur-sm hover:bg-white"
              onClick={(e) => onToggleFavorite(recipe.id, e)}
            >
              <Heart
                className={`w-4 h-4 ${
                  isFavorite ? "fill-red-500 text-red-500" : "text-gray-600"
                }`}
              />
            </Button>
          </div>
        )}
      </div>
      <CardContent className="pt-4">
        <h3 className="mb-2 line-clamp-1">{recipe.title}</h3>
        <p className="text-gray-600 text-sm line-clamp-2 mb-3">
          {recipe.description}
        </p>
        <div className="flex items-center gap-4 text-sm text-gray-500">
          <div className="flex items-center gap-1">
            <Clock className="w-4 h-4" />
            <span>{recipe.cookTime}</span>
          </div>
          <div className="flex items-center gap-1">
            <ChefHat className="w-4 h-4" />
            <span>{recipe.servings}인분</span>
          </div>
          {recipe.views && (
            <div className="flex items-center gap-1">
              <Eye className="w-4 h-4" />
              <span>{recipe.views}회</span>
            </div>
          )}
          {recipe.author && (
            <div className="flex items-center gap-1">
              <User className="w-4 h-4" />
              <span>{recipe.author}</span>
            </div>
          )}
          {recipe.isPremium && (
            <div className="flex items-center gap-1">
              <Crown className="w-4 h-4" />
              <span>프리미엄</span>
            </div>
          )}
        </div>
      </CardContent>
      <CardFooter className="pt-0">
        <Badge className={difficultyColor[recipe.difficulty]}>
          {recipe.difficulty}
        </Badge>
      </CardFooter>
    </Card>
  );
}