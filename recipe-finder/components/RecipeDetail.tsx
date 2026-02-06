import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Badge } from "./ui/badge";
import { Separator } from "./ui/separator";
import { Clock, ChefHat, Users, Eye, User, Edit, Trash2, Crown } from "lucide-react";
import { Recipe } from "./RecipeCard";
import { ImageWithFallback } from "./figma/ImageWithFallback";
import { useState } from "react";
import { IngredientInfoDialog } from "./IngredientInfoDialog";
import { findIngredientInfo, IngredientInfo } from "../services/ingredientDataNew";
import { Button } from "./ui/button";

interface RecipeDetailProps {
  recipe: Recipe | null;
  open: boolean;
  onOpenChange: (open: boolean) => void;
  currentUserId?: string;
  onEdit?: (recipe: Recipe) => void;
  onDelete?: (recipeId: number) => void;
  isAdmin?: boolean;
  onTogglePremium?: (recipeId: number) => void;
}

export function RecipeDetail({ recipe, open, onOpenChange, currentUserId, onEdit, onDelete, isAdmin, onTogglePremium }: RecipeDetailProps) {
  const [selectedIngredient, setSelectedIngredient] = useState<IngredientInfo | null>(null);
  const [ingredientDialogOpen, setIngredientDialogOpen] = useState(false);

  if (!recipe) return null;

  const difficultyColor = {
    쉬움: "bg-green-100 text-green-800 hover:bg-green-100",
    보통: "bg-yellow-100 text-yellow-800 hover:bg-yellow-100",
    어려움: "bg-red-100 text-red-800 hover:bg-red-100",
  };

  const handleIngredientClick = (ingredientStr: string) => {
    const info = findIngredientInfo(ingredientStr);
    if (info) {
      setSelectedIngredient(info);
      setIngredientDialogOpen(true);
    }
  };

  const handleEditClick = () => {
    if (onEdit) {
      onEdit(recipe);
    }
  };

  const handleDeleteClick = () => {
    if (onDelete) {
      onDelete(recipe.id);
    }
  };

  const handleTogglePremiumClick = () => {
    if (onTogglePremium) {
      onTogglePremium(recipe.id);
    }
  };

  return (
    <>
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-3xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <div 
            className="relative h-64 -mx-6 -mt-6 mb-4 cursor-pointer"
            onClick={() => onOpenChange(false)}
          >
            <ImageWithFallback
              src={recipe.image}
              alt={recipe.title}
              className="w-full h-full object-cover"
            />
            <div className="absolute inset-0 bg-black/0 hover:bg-black/10 transition-colors flex items-center justify-center">
              <span className="opacity-0 hover:opacity-100 text-white text-sm bg-black/50 px-3 py-1 rounded">
                클릭하여 닫기
              </span>
            </div>
          </div>
          <DialogTitle className="text-3xl">{recipe.title}</DialogTitle>
          <DialogDescription className="text-gray-600">{recipe.description}</DialogDescription>
        </DialogHeader>
        
        <div className="space-y-6">
          <div className="flex flex-wrap gap-3">
            <Badge variant="secondary">{recipe.category}</Badge>
            <Badge className={difficultyColor[recipe.difficulty]}>
              {recipe.difficulty}
            </Badge>
          </div>

          <div className="flex gap-6 text-sm">
            <div className="flex items-center gap-2">
              <Clock className="w-5 h-5 text-gray-500" />
              <div>
                <div className="text-gray-500">조리시간</div>
                <div>{recipe.cookTime}</div>
              </div>
            </div>
            <div className="flex items-center gap-2">
              <Users className="w-5 h-5 text-gray-500" />
              <div>
                <div className="text-gray-500">분량</div>
                <div>{recipe.servings}인분</div>
              </div>
            </div>
            <div className="flex items-center gap-2">
              <ChefHat className="w-5 h-5 text-gray-500" />
              <div>
                <div className="text-gray-500">난이도</div>
                <div>{recipe.difficulty}</div>
              </div>
            </div>
            {recipe.views !== undefined && (
              <div className="flex items-center gap-2">
                <Eye className="w-5 h-5 text-gray-500" />
                <div>
                  <div className="text-gray-500">조회수</div>
                  <div>{recipe.views.toLocaleString()}</div>
                </div>
              </div>
            )}
            {recipe.author && (
              <div className="flex items-center gap-2">
                <User className="w-5 h-5 text-gray-500" />
                <div>
                  <div className="text-gray-500">작성자</div>
                  <div>{recipe.author}</div>
                </div>
              </div>
            )}
          </div>

          <Separator />

          <div>
            <h3 className="mb-3">재료 <span className="text-sm text-gray-500">(클릭하면 재료 정보를 볼 수 있어요)</span></h3>
            <ul className="space-y-2">
              {recipe.ingredients.map((ingredient, index) => (
                <li key={index} className="flex items-start gap-2">
                  <span className="text-orange-500 mt-1">•</span>
                  <span 
                    className="text-gray-700 cursor-pointer hover:text-orange-500 hover:underline transition-colors"
                    onClick={() => handleIngredientClick(ingredient)}
                  >
                    {ingredient}
                  </span>
                </li>
              ))}
            </ul>
          </div>

          <Separator />

          <div>
            <h3 className="mb-3">조리 방법</h3>
            <ol className="space-y-4">
              {recipe.instructions.map((instruction, index) => (
                <li key={index} className="flex gap-3">
                  <span className="flex-shrink-0 w-6 h-6 rounded-full bg-orange-500 text-white flex items-center justify-center text-sm">
                    {index + 1}
                  </span>
                  <span className="text-gray-700 pt-0.5">{instruction}</span>
                </li>
              ))}
            </ol>
          </div>

          {currentUserId && recipe.userId === currentUserId && (
            <div className="flex gap-4">
              <Button
                variant="outline"
                className="flex items-center gap-2"
                onClick={handleEditClick}
              >
                <Edit className="w-4 h-4" />
                수정
              </Button>
              <Button
                variant="destructive"
                className="flex items-center gap-2"
                onClick={handleDeleteClick}
              >
                <Trash2 className="w-4 h-4" />
                삭제
              </Button>
            </div>
          )}

          {isAdmin && (
            <div className="flex gap-4">
              <Button
                variant="outline"
                className="flex items-center gap-2"
                onClick={handleTogglePremiumClick}
              >
                <Crown className="w-4 h-4" />
                프리미엄 토글
              </Button>
            </div>
          )}
        </div>
      </DialogContent>
    </Dialog>
    <IngredientInfoDialog
      open={ingredientDialogOpen}
      onOpenChange={setIngredientDialogOpen}
      ingredient={selectedIngredient}
    />
    </>
  );
}