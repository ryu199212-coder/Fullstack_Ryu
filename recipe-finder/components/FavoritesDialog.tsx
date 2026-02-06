import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Recipe } from "./RecipeCard";
import { RecipeCard } from "./RecipeCard";
import { Heart } from "lucide-react";

interface FavoritesDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  favorites: Recipe[];
  onRecipeClick: (recipe: Recipe) => void;
}

export function FavoritesDialog({ 
  open, 
  onOpenChange, 
  favorites, 
  onRecipeClick 
}: FavoritesDialogProps) {
  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-5xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle className="flex items-center gap-2">
            <Heart className="w-6 h-6 text-red-500 fill-red-500" />
            내가 좋아하는 레시피
          </DialogTitle>
          <DialogDescription className="text-sm text-gray-500">
            즐겨찾기한 레시피를 확인하고 선택할 수 있습니다.
          </DialogDescription>
        </DialogHeader>

        {favorites.length === 0 ? (
          <div className="text-center py-16">
            <Heart className="w-16 h-16 text-gray-300 mx-auto mb-4" />
            <p className="text-gray-500 text-lg mb-2">
              아직 즐겨찾기한 레시피가 없습니다
            </p>
            <p className="text-gray-400 text-sm">
              마음에 드는 레시피에 하트를 눌러보세요!
            </p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
            {favorites.map((recipe) => (
              <RecipeCard
                key={recipe.id}
                recipe={recipe}
                onClick={() => onRecipeClick(recipe)}
              />
            ))}
          </div>
        )}
      </DialogContent>
    </Dialog>
  );
}