import { useState, useEffect } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Button } from "./ui/button";
import { Textarea } from "./ui/textarea";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "./ui/select";
import { Recipe } from "./RecipeCard";
import { Plus, Minus, ImageIcon } from "lucide-react";
import { toast } from "sonner";

interface Ingredient {
  name: string;
  amount: string;
}

interface CreateRecipeDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSave: (recipe: Omit<Recipe, "id">) => void;
  editRecipe?: Recipe | null;
}

export function CreateRecipeDialog({ open, onOpenChange, onSave, editRecipe }: CreateRecipeDialogProps) {
  const [title, setTitle] = useState("");
  const [category, setCategory] = useState("한식");
  const [cookTime, setCookTime] = useState("");
  const [difficulty, setDifficulty] = useState<"쉬움" | "보통" | "어려움">("보통");
  const [servings, setServings] = useState("2");
  const [description, setDescription] = useState("");
  const [image, setImage] = useState("");
  const [ingredients, setIngredients] = useState<Ingredient[]>([{ name: "", amount: "" }]);
  const [instructions, setInstructions] = useState<string[]>([""]);

  useEffect(() => {
    if (editRecipe) {
      setTitle(editRecipe.title);
      setCategory(editRecipe.category);
      setCookTime(editRecipe.cookTime.replace("분", ""));
      setDifficulty(editRecipe.difficulty);
      setServings(editRecipe.servings.toString());
      setDescription(editRecipe.description);
      setImage(editRecipe.image);
      
      // 기존 재료를 파싱 (예: "돼지고기 300g" -> { name: "돼지고기", amount: "300g" })
      const parsedIngredients = editRecipe.ingredients.length > 0 
        ? editRecipe.ingredients.map((ing) => {
            const parts = ing.split(' ');
            if (parts.length >= 2) {
              const amount = parts[parts.length - 1];
              const name = parts.slice(0, -1).join(' ');
              return { name, amount };
            }
            return { name: ing, amount: '' };
          }) 
        : [{ name: "", amount: "" }];
      
      setIngredients(parsedIngredients);
      setInstructions(editRecipe.instructions.length > 0 ? editRecipe.instructions : [""]);
    } else {
      resetForm();
    }
  }, [editRecipe, open]);

  const resetForm = () => {
    setTitle("");
    setCategory("한식");
    setCookTime("");
    setDifficulty("보통");
    setServings("2");
    setDescription("");
    setImage("");
    setIngredients([{ name: "", amount: "" }]);
    setInstructions([""]);
  };

  const handleAddIngredient = () => {
    setIngredients([...ingredients, { name: "", amount: "" }]);
  };

  const handleRemoveIngredient = (index: number) => {
    if (ingredients.length > 1) {
      setIngredients(ingredients.filter((_, i) => i !== index));
    }
  };

  const handleIngredientChange = (index: number, value: string, field: "name" | "amount") => {
    const newIngredients = [...ingredients];
    newIngredients[index][field] = value;
    setIngredients(newIngredients);
  };

  const handleAddInstruction = () => {
    setInstructions([...instructions, ""]);
  };

  const handleRemoveInstruction = (index: number) => {
    if (instructions.length > 1) {
      setInstructions(instructions.filter((_, i) => i !== index));
    }
  };

  const handleInstructionChange = (index: number, value: string) => {
    const newInstructions = [...instructions];
    newInstructions[index] = value;
    setInstructions(newInstructions);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!title.trim()) {
      toast.error("레시피 제목을 입력해주세요.");
      return;
    }

    if (!cookTime || isNaN(Number(cookTime))) {
      toast.error("올바른 조리 시간을 입력해주세요.");
      return;
    }

    if (!servings || isNaN(Number(servings))) {
      toast.error("올바른 인분 수를 입력해주세요.");
      return;
    }

    if (!description.trim()) {
      toast.error("레시피 설명을 입력해주세요.");
      return;
    }

    const filteredIngredients = ingredients.filter((ing) => ing.name.trim() !== "");
    const filteredInstructions = instructions.filter((inst) => inst.trim() !== "");

    if (filteredIngredients.length === 0) {
      toast.error("최소 1개의 재료를 입력해주세요.");
      return;
    }

    if (filteredInstructions.length === 0) {
      toast.error("최소 1개의 조리 방법을 입력해주세요.");
      return;
    }

    const recipe: Omit<Recipe, "id"> = {
      title: title.trim(),
      category,
      cookTime: `${cookTime}분`,
      difficulty,
      servings: Number(servings),
      description: description.trim(),
      image: image.trim() || "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=1080",
      ingredients: filteredIngredients.map((ing) => 
        ing.amount.trim() ? `${ing.name.trim()} ${ing.amount.trim()}` : ing.name.trim()
      ),
      instructions: filteredInstructions,
    };

    onSave(recipe);
    onOpenChange(false);
    resetForm();
    toast.success(editRecipe ? "레시피가 수정되었습니다!" : "레시피가 등록되었습니다!");
  };

  return (
    <Dialog open={open} onOpenChange={(isOpen) => {
      onOpenChange(isOpen);
      if (!isOpen) resetForm();
    }}>
      <DialogContent className="max-w-4xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>{editRecipe ? "레시피 수정" : "새 레시피 작성"}</DialogTitle>
          <DialogDescription>
            {editRecipe 
              ? "레시피 정보를 수정하세요" 
              : "나만의 레시피를 작성하고 공유해보세요"}
          </DialogDescription>
        </DialogHeader>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {/* Title */}
            <div className="space-y-2 md:col-span-2">
              <Label htmlFor="title">레시피 제목 *</Label>
              <Input
                id="title"
                placeholder="예: 김치찌개"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
              />
            </div>

            {/* Category */}
            <div className="space-y-2">
              <Label htmlFor="category">카테고리 *</Label>
              <Select value={category} onValueChange={setCategory}>
                <SelectTrigger>
                  <SelectValue />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="한식">한식</SelectItem>
                  <SelectItem value="양식">양식</SelectItem>
                  <SelectItem value="중식">중식</SelectItem>
                  <SelectItem value="일식">일식</SelectItem>
                  <SelectItem value="디저트">디저트</SelectItem>
                  <SelectItem value="건강식">건강식</SelectItem>
                  <SelectItem value="기타">기타</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {/* Difficulty */}
            <div className="space-y-2">
              <Label htmlFor="difficulty">난이도 *</Label>
              <Select value={difficulty} onValueChange={(value) => setDifficulty(value as "쉬움" | "보통" | "어려움")}>
                <SelectTrigger>
                  <SelectValue />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="쉬움">쉬움</SelectItem>
                  <SelectItem value="보통">보통</SelectItem>
                  <SelectItem value="어려움">어려움</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {/* Cook Time */}
            <div className="space-y-2">
              <Label htmlFor="cookTime">조리 시간 (분) *</Label>
              <Input
                id="cookTime"
                type="number"
                min="1"
                placeholder="30"
                value={cookTime}
                onChange={(e) => setCookTime(e.target.value)}
                required
              />
            </div>

            {/* Servings */}
            <div className="space-y-2">
              <Label htmlFor="servings">인분 *</Label>
              <Input
                id="servings"
                type="number"
                min="1"
                placeholder="2"
                value={servings}
                onChange={(e) => setServings(e.target.value)}
                required
              />
            </div>

            {/* Image URL */}
            <div className="space-y-2 md:col-span-2">
              <Label htmlFor="image">이미지 URL (선택사항)</Label>
              <div className="flex gap-2">
                <ImageIcon className="w-5 h-5 text-gray-400 mt-2" />
                <Input
                  id="image"
                  type="url"
                  placeholder="https://example.com/image.jpg"
                  value={image}
                  onChange={(e) => setImage(e.target.value)}
                />
              </div>
              <p className="text-xs text-gray-500">
                이미지 URL을 입력하지 않으면 기본 이미지가 사용됩니다.
              </p>
            </div>

            {/* Description */}
            <div className="space-y-2 md:col-span-2">
              <Label htmlFor="description">레시피 설명 *</Label>
              <Textarea
                id="description"
                placeholder="이 레시피에 대한 간단한 설명을 입력하세요"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                rows={3}
                required
              />
            </div>
          </div>

          {/* Ingredients */}
          <div className="space-y-3">
            <div className="flex items-center justify-between">
              <Label>재료 *</Label>
              <Button type="button" variant="outline" size="sm" onClick={handleAddIngredient}>
                <Plus className="w-4 h-4 mr-1" />
                재료 추가
              </Button>
            </div>
            <div className="space-y-2">
              {ingredients.map((ingredient, index) => (
                <div key={index} className="flex gap-2">
                  <Input
                    placeholder={`재료 ${index + 1} 이름 (예: 돼지고기)`}
                    value={ingredient.name}
                    onChange={(e) => handleIngredientChange(index, e.target.value, "name")}
                  />
                  <Input
                    placeholder={`재료 ${index + 1} 양 (예: 300g)`}
                    value={ingredient.amount}
                    onChange={(e) => handleIngredientChange(index, e.target.value, "amount")}
                  />
                  {ingredients.length > 1 && (
                    <Button
                      type="button"
                      variant="outline"
                      size="icon"
                      onClick={() => handleRemoveIngredient(index)}
                    >
                      <Minus className="w-4 h-4" />
                    </Button>
                  )}
                </div>
              ))}
            </div>
          </div>

          {/* Instructions */}
          <div className="space-y-3">
            <div className="flex items-center justify-between">
              <Label>조리 방법 *</Label>
              <Button type="button" variant="outline" size="sm" onClick={handleAddInstruction}>
                <Plus className="w-4 h-4 mr-1" />
                단계 추가
              </Button>
            </div>
            <div className="space-y-2">
              {instructions.map((instruction, index) => (
                <div key={index} className="flex gap-2">
                  <div className="flex-shrink-0 w-8 h-8 rounded-full bg-orange-500 text-white flex items-center justify-center text-sm mt-1">
                    {index + 1}
                  </div>
                  <Textarea
                    placeholder={`조리 단계 ${index + 1}`}
                    value={instruction}
                    onChange={(e) => handleInstructionChange(index, e.target.value)}
                    rows={2}
                  />
                  {instructions.length > 1 && (
                    <Button
                      type="button"
                      variant="outline"
                      size="icon"
                      onClick={() => handleRemoveInstruction(index)}
                      className="flex-shrink-0"
                    >
                      <Minus className="w-4 h-4" />
                    </Button>
                  )}
                </div>
              ))}
            </div>
          </div>

          {/* Submit Buttons */}
          <div className="flex justify-end gap-2 pt-4">
            <Button type="button" variant="outline" onClick={() => onOpenChange(false)}>
              취소
            </Button>
            <Button type="submit" className="bg-orange-500 hover:bg-orange-600">
              {editRecipe ? "수정 완료" : "레시피 등록"}
            </Button>
          </div>
        </form>
      </DialogContent>
    </Dialog>
  );
}