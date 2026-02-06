import { useState } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Button } from "./ui/button";
import { Checkbox } from "./ui/checkbox";
import { Label } from "./ui/label";
import { Recipe } from "./RecipeCard";
import { RecipeCard } from "./RecipeCard";
import { ChefHat, ArrowRight, RotateCcw } from "lucide-react";
import { Progress } from "./ui/progress";

interface RecipeFinderProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  recipes: Recipe[];
  onRecipeClick: (recipe: Recipe) => void;
  favoriteIds?: number[];
  onToggleFavorite?: (recipeId: number, e: React.MouseEvent) => void;
}

interface QuestionOption {
  id: string;
  label: string;
  value: string;
}

interface Question {
  id: string;
  title: string;
  description: string;
  options: QuestionOption[];
  type: "checkbox" | "radio";
}

const questions: Question[] = [
  {
    id: "mealType",
    title: "어떤 식사를 원하시나요?",
    description: "식사 종류를 선택해주세요 (중복 선택 가능)",
    type: "checkbox",
    options: [
      { id: "breakfast", label: "아침 식사", value: "breakfast" },
      { id: "lunch", label: "점심 식사", value: "lunch" },
      { id: "dinner", label: "저녁 식사", value: "dinner" },
      { id: "snack", label: "간식/디저트", value: "snack" },
    ],
  },
  {
    id: "cuisine",
    title: "어떤 음식을 좋아하시나요?",
    description: "음식 종류를 선택해주세요 (중복 선택 가능)",
    type: "checkbox",
    options: [
      { id: "korean", label: "한식", value: "한식" },
      { id: "western", label: "양식", value: "양식" },
      { id: "chinese", label: "중식", value: "중식" },
      { id: "japanese", label: "일식", value: "일식" },
      { id: "dessert", label: "디저트", value: "디저트" },
      { id: "healthy", label: "건강식", value: "건강식" },
    ],
  },
  {
    id: "cookTime",
    title: "조리 시간은 얼마나 되나요?",
    description: "가능한 조리 시간을 선택해주세요 (중복 선택 가능)",
    type: "checkbox",
    options: [
      { id: "quick", label: "빠르게 (30분 이하)", value: "quick" },
      { id: "medium", label: "적당히 (30분~60분)", value: "medium" },
      { id: "long", label: "여유있게 (60분 이상)", value: "long" },
    ],
  },
  {
    id: "difficulty",
    title: "요리 난이도는 어떻게 하시겠어요?",
    description: "가능한 난이도를 선택해주세요 (중복 선택 가능)",
    type: "checkbox",
    options: [
      { id: "easy", label: "쉬움", value: "쉬움" },
      { id: "medium", label: "보통", value: "보통" },
      { id: "hard", label: "어려움", value: "어려움" },
    ],
  },
];

export function RecipeFinder({ 
  open, 
  onOpenChange, 
  recipes,
  onRecipeClick,
  favoriteIds,
  onToggleFavorite
}: RecipeFinderProps) {
  const [currentStep, setCurrentStep] = useState(0);
  const [answers, setAnswers] = useState<Record<string, string[]>>({});
  const [showResults, setShowResults] = useState(false);

  const currentQuestion = questions[currentStep];
  const progress = ((currentStep + 1) / questions.length) * 100;

  const handleOptionToggle = (questionId: string, optionValue: string) => {
    const currentAnswers = answers[questionId] || [];
    const newAnswers = currentAnswers.includes(optionValue)
      ? currentAnswers.filter((v) => v !== optionValue)
      : [...currentAnswers, optionValue];
    
    setAnswers({ ...answers, [questionId]: newAnswers });
  };

  const handleNext = () => {
    if (currentStep < questions.length - 1) {
      setCurrentStep(currentStep + 1);
    } else {
      setShowResults(true);
    }
  };

  const handleBack = () => {
    if (currentStep > 0) {
      setCurrentStep(currentStep - 1);
    }
  };

  const handleReset = () => {
    setCurrentStep(0);
    setAnswers({});
    setShowResults(false);
  };

  const canProceed = answers[currentQuestion.id]?.length > 0;

  const getMatchingRecipes = (): Recipe[] => {
    const cuisineTypes = answers.cuisine || [];
    const cookTimes = answers.cookTime || [];
    const difficulties = answers.difficulty || [];
    const mealTypes = answers.mealType || [];

    return recipes.filter((recipe) => {
      // Check cuisine match
      const cuisineMatch = cuisineTypes.length === 0 || cuisineTypes.includes(recipe.category);

      // Check cook time match
      let cookTimeMatch = cookTimes.length === 0;
      if (!cookTimeMatch) {
        const cookMinutes = parseInt(recipe.cookTime);
        if (cookTimes.includes("quick") && cookMinutes <= 30) cookTimeMatch = true;
        if (cookTimes.includes("medium") && cookMinutes > 30 && cookMinutes <= 60) cookTimeMatch = true;
        if (cookTimes.includes("long") && cookMinutes > 60) cookTimeMatch = true;
      }

      // Check difficulty match
      const difficultyMatch = difficulties.length === 0 || difficulties.includes(recipe.difficulty);

      // Check meal type match (simplified - based on category)
      let mealTypeMatch = mealTypes.length === 0;
      if (!mealTypeMatch) {
        if (mealTypes.includes("breakfast") && ["건강식", "디저트"].includes(recipe.category)) mealTypeMatch = true;
        if (mealTypes.includes("lunch") && ["한식", "양식", "중식", "일식", "건강식"].includes(recipe.category)) mealTypeMatch = true;
        if (mealTypes.includes("dinner") && ["한식", "양식", "중식", "일식"].includes(recipe.category)) mealTypeMatch = true;
        if (mealTypes.includes("snack") && recipe.category === "디저트") mealTypeMatch = true;
      }

      return cuisineMatch && cookTimeMatch && difficultyMatch && mealTypeMatch;
    });
  };

  const matchingRecipes = showResults ? getMatchingRecipes() : [];

  return (
    <Dialog open={open} onOpenChange={(isOpen) => {
      onOpenChange(isOpen);
      if (!isOpen) {
        handleReset();
      }
    }}>
      <DialogContent className="max-w-3xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle className="flex items-center gap-2">
            <ChefHat className="w-6 h-6 text-orange-500" />
            {showResults ? "추천 레시피" : "나에게 맞는 레시피 찾기"}
          </DialogTitle>
          <DialogDescription>
            {showResults 
              ? "선택하신 조건에 맞는 레시피를 찾았어요!"
              : "몇 가지 질문에 답하면 맞춤 레시피를 추천해드려요"}
          </DialogDescription>
        </DialogHeader>

        {!showResults ? (
          <div className="space-y-6">
            {/* Progress Bar */}
            <div className="space-y-2">
              <div className="flex justify-between text-sm text-gray-600">
                <span>진행 상황</span>
                <span>{currentStep + 1} / {questions.length}</span>
              </div>
              <Progress value={progress} className="h-2" />
            </div>

            {/* Question */}
            <div className="space-y-4">
              <div>
                <h3 className="mb-2">{currentQuestion.title}</h3>
                <p className="text-sm text-gray-600">{currentQuestion.description}</p>
              </div>

              {/* Options */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-3">
                {currentQuestion.options.map((option) => {
                  const isChecked = answers[currentQuestion.id]?.includes(option.value) || false;
                  
                  return (
                    <div
                      key={option.id}
                      className={`flex items-center space-x-3 p-4 rounded-lg border-2 cursor-pointer transition-all ${
                        isChecked
                          ? "border-orange-500 bg-orange-50"
                          : "border-gray-200 hover:border-orange-300"
                      }`}
                      onClick={() => handleOptionToggle(currentQuestion.id, option.value)}
                    >
                      <Checkbox
                        id={option.id}
                        checked={isChecked}
                        onCheckedChange={() => handleOptionToggle(currentQuestion.id, option.value)}
                      />
                      <Label
                        htmlFor={option.id}
                        className="cursor-pointer flex-1"
                      >
                        {option.label}
                      </Label>
                    </div>
                  );
                })}
              </div>
            </div>

            {/* Navigation Buttons */}
            <div className="flex justify-between pt-4">
              <Button
                variant="outline"
                onClick={handleBack}
                disabled={currentStep === 0}
              >
                이전
              </Button>
              <Button
                onClick={handleNext}
                disabled={!canProceed}
                className="bg-orange-500 hover:bg-orange-600"
              >
                {currentStep === questions.length - 1 ? "결과 보기" : "다음"}
                <ArrowRight className="w-4 h-4 ml-2" />
              </Button>
            </div>
          </div>
        ) : (
          <div className="space-y-6">
            {/* Results */}
            {matchingRecipes.length > 0 ? (
              <>
                <p className="text-gray-600">
                  총 <span className="text-orange-500">{matchingRecipes.length}개</span>의 레시피가 조건에 맞아요!
                </p>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  {matchingRecipes.map((recipe) => (
                    <RecipeCard
                      key={recipe.id}
                      recipe={recipe}
                      onClick={() => {
                        onRecipeClick(recipe);
                        onOpenChange(false);
                        handleReset();
                      }}
                      isFavorite={favoriteIds?.includes(recipe.id)}
                      onToggleFavorite={onToggleFavorite}
                    />
                  ))}
                </div>
              </>
            ) : (
              <div className="text-center py-12">
                <ChefHat className="w-16 h-16 text-gray-300 mx-auto mb-4" />
                <p className="text-gray-500 text-lg mb-2">
                  조건에 맞는 레시피를 찾지 못했어요
                </p>
                <p className="text-gray-400 text-sm mb-6">
                  다른 조건으로 다시 시도해보세요
                </p>
              </div>
            )}

            {/* Reset Button */}
            <div className="flex justify-center pt-4">
              <Button
                variant="outline"
                onClick={handleReset}
                className="gap-2"
              >
                <RotateCcw className="w-4 h-4" />
                다시 찾기
              </Button>
            </div>
          </div>
        )}
      </DialogContent>
    </Dialog>
  );
}
