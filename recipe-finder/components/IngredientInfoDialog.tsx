import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Badge } from "./ui/badge";
import { Separator } from "./ui/separator";
import { Info, Lightbulb, Package, RefreshCw } from "lucide-react";
import { IngredientInfo } from "../services/ingredientDataNew";

interface IngredientInfoDialogProps {
  ingredient: IngredientInfo | null;
  open: boolean;
  onOpenChange: (open: boolean) => void;
}

export function IngredientInfoDialog({ ingredient, open, onOpenChange }: IngredientInfoDialogProps) {
  if (!ingredient) return null;

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle className="text-2xl flex items-center gap-2">
            <Info className="w-6 h-6 text-orange-500" />
            {ingredient.name}
          </DialogTitle>
          <DialogDescription>
            <Badge variant="secondary" className="mt-2">{ingredient.category}</Badge>
          </DialogDescription>
        </DialogHeader>

        <div className="space-y-4">
          {ingredient.description && (
            <div>
              <p className="text-gray-700">{ingredient.description}</p>
            </div>
          )}

          {/* 영양 정보 */}
          <div className="bg-green-50 rounded-lg p-4">
            <h3 className="flex items-center gap-2 mb-3 text-green-800">
              <span className="text-lg">🥗</span>
              영양 정보 (100g 기준)
            </h3>
            <div className="grid grid-cols-2 gap-3 text-sm">
              <div>
                <span className="text-gray-600">칼로리:</span>
                <span className="ml-2 font-medium">{ingredient.calories} kcal</span>
              </div>
              <div>
                <span className="text-gray-600">단백질:</span>
                <span className="ml-2 font-medium">{ingredient.protein}g</span>
              </div>
              <div>
                <span className="text-gray-600">탄수화물:</span>
                <span className="ml-2 font-medium">{ingredient.carbs}g</span>
              </div>
              <div>
                <span className="text-gray-600">지방:</span>
                <span className="ml-2 font-medium">{ingredient.fat}g</span>
              </div>
              {ingredient.fiber > 0 && (
                <div>
                  <span className="text-gray-600">식이섬유:</span>
                  <span className="ml-2 font-medium">{ingredient.fiber}g</span>
                </div>
              )}
            </div>
          </div>

          {/* 비타민 */}
          {ingredient.vitamins && ingredient.vitamins.length > 0 && (
            <div className="bg-blue-50 rounded-lg p-4">
              <h3 className="flex items-center gap-2 mb-2 text-blue-800">
                <Package className="w-5 h-5" />
                비타민
              </h3>
              <div className="flex flex-wrap gap-2">
                {ingredient.vitamins.map((vitamin, index) => (
                  <Badge key={index} variant="secondary" className="bg-blue-100 text-blue-800">
                    {vitamin}
                  </Badge>
                ))}
              </div>
            </div>
          )}

          {/* 미네랄 */}
          {ingredient.minerals && ingredient.minerals.length > 0 && (
            <div className="bg-purple-50 rounded-lg p-4">
              <h3 className="flex items-center gap-2 mb-2 text-purple-800">
                <RefreshCw className="w-5 h-5" />
                미네랄
              </h3>
              <div className="flex flex-wrap gap-2">
                {ingredient.minerals.map((mineral, index) => (
                  <Badge key={index} variant="secondary" className="bg-purple-100 text-purple-800">
                    {mineral}
                  </Badge>
                ))}
              </div>
            </div>
          )}

          {/* 효능 */}
          {ingredient.benefits && ingredient.benefits.length > 0 && (
            <div className="bg-yellow-50 rounded-lg p-4">
              <h3 className="flex items-center gap-2 mb-2 text-yellow-800">
                <Lightbulb className="w-5 h-5" />
                건강 효능
              </h3>
              <div className="flex flex-wrap gap-2">
                {ingredient.benefits.map((benefit, index) => (
                  <Badge key={index} variant="secondary" className="bg-yellow-100 text-yellow-800">
                    {benefit}
                  </Badge>
                ))}
              </div>
            </div>
          )}
        </div>

        <Separator className="my-2" />

        <div className="text-sm text-gray-500 text-center">
          재료를 클릭하여 더 많은 정보를 확인하세요
        </div>
      </DialogContent>
    </Dialog>
  );
}