import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Recipe } from "./RecipeCard";
import { RecipeCard } from "./RecipeCard";
import { Button } from "./ui/button";
import { BookOpen, Edit, Trash2 } from "lucide-react";
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from "./ui/alert-dialog";
import { useState } from "react";

interface UserRecipesDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  userRecipes: Recipe[];
  onRecipeClick: (recipe: Recipe) => void;
  onEdit: (recipe: Recipe) => void;
  onDelete: (recipeId: number) => void;
  favoriteIds?: number[];
  onToggleFavorite?: (recipeId: number, e: React.MouseEvent) => void;
}

export function UserRecipesDialog({
  open,
  onOpenChange,
  userRecipes,
  onRecipeClick,
  onEdit,
  onDelete,
  favoriteIds,
  onToggleFavorite,
}: UserRecipesDialogProps) {
  const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
  const [recipeToDelete, setRecipeToDelete] = useState<Recipe | null>(null);

  const handleDeleteClick = (recipe: Recipe, e: React.MouseEvent) => {
    e.stopPropagation();
    setRecipeToDelete(recipe);
    setDeleteDialogOpen(true);
  };

  const handleConfirmDelete = () => {
    if (recipeToDelete) {
      onDelete(recipeToDelete.id);
      setDeleteDialogOpen(false);
      setRecipeToDelete(null);
    }
  };

  return (
    <>
      <Dialog open={open} onOpenChange={onOpenChange}>
        <DialogContent className="max-w-5xl max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <BookOpen className="w-6 h-6 text-orange-500" />
              내가 작성한 레시피
            </DialogTitle>
            <DialogDescription>
              나만의 레시피를 확인하고 수정하거나 삭제할 수 있습니다.
            </DialogDescription>
          </DialogHeader>

          {userRecipes.length === 0 ? (
            <div className="text-center py-16">
              <BookOpen className="w-16 h-16 text-gray-300 mx-auto mb-4" />
              <p className="text-gray-500 text-lg mb-2">
                아직 작성한 레시피가 없습니다
              </p>
              <p className="text-gray-400 text-sm">
                나만의 레시피를 공유해보세요!
              </p>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
              {userRecipes.map((recipe) => (
                <div key={recipe.id} className="relative group">
                  <RecipeCard
                    recipe={recipe}
                    onClick={() => onRecipeClick(recipe)}
                    isFavorite={favoriteIds?.includes(recipe.id)}
                    onToggleFavorite={onToggleFavorite}
                  />
                  {/* Edit and Delete Buttons Overlay */}
                  <div className="absolute bottom-20 left-0 right-0 p-3 bg-gradient-to-t from-black/70 to-transparent opacity-0 group-hover:opacity-100 transition-opacity flex gap-2 justify-center">
                    <Button
                      size="sm"
                      variant="secondary"
                      onClick={(e) => {
                        e.stopPropagation();
                        onEdit(recipe);
                      }}
                      className="bg-white/90 hover:bg-white"
                    >
                      <Edit className="w-4 h-4 mr-1" />
                      수정
                    </Button>
                    <Button
                      size="sm"
                      variant="destructive"
                      onClick={(e) => handleDeleteClick(recipe, e)}
                      className="bg-red-500/90 hover:bg-red-500"
                    >
                      <Trash2 className="w-4 h-4 mr-1" />
                      삭제
                    </Button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </DialogContent>
      </Dialog>

      {/* Delete Confirmation Dialog */}
      <AlertDialog open={deleteDialogOpen} onOpenChange={setDeleteDialogOpen}>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>레시피를 삭제하시겠습니까?</AlertDialogTitle>
            <AlertDialogDescription>
              "{recipeToDelete?.title}" 레시피가 영구적으로 삭제됩니다. 이 작업은 되돌릴 수 없습니다.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel>취소</AlertDialogCancel>
            <AlertDialogAction
              onClick={handleConfirmDelete}
              className="bg-red-500 hover:bg-red-600"
            >
              삭제
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </>
  );
}