import { useState, useEffect } from 'react';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from './ui/dialog';
import { Button } from './ui/button';
import { Input } from './ui/input';
import { Label } from './ui/label';
import { Textarea } from './ui/textarea';
import { Card, CardContent, CardHeader, CardTitle } from './ui/card';
import { Badge } from './ui/badge';
import { Plus, Edit, Trash2, Search, Apple, X } from 'lucide-react';
import { toast } from 'sonner';
import { IngredientInfo, getAllIngredients, addIngredient, updateIngredient, deleteIngredient } from '../services/ingredientDataNew';
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "./ui/alert-dialog";

interface IngredientManagementProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  isAdmin: boolean;
}

export function IngredientManagement({ open, onOpenChange, isAdmin }: IngredientManagementProps) {
  const [ingredients, setIngredients] = useState<IngredientInfo[]>([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [editingIngredient, setEditingIngredient] = useState<IngredientInfo | null>(null);
  const [showForm, setShowForm] = useState(false);
  const [deleteTarget, setDeleteTarget] = useState<IngredientInfo | null>(null);

  // Form state
  const [formData, setFormData] = useState<IngredientInfo>({
    name: '',
    category: '',
    calories: 0,
    protein: 0,
    carbs: 0,
    fat: 0,
    fiber: 0,
    vitamins: [],
    minerals: [],
    benefits: [],
    description: ''
  });

  useEffect(() => {
    loadIngredients();
  }, [open]);

  const loadIngredients = () => {
    const allIngredients = getAllIngredients();
    setIngredients(allIngredients);
  };

  const resetForm = () => {
    setFormData({
      name: '',
      category: '',
      calories: 0,
      protein: 0,
      carbs: 0,
      fat: 0,
      fiber: 0,
      vitamins: [],
      minerals: [],
      benefits: [],
      description: ''
    });
    setEditingIngredient(null);
    setShowForm(false);
  };

  const handleEdit = (ingredient: IngredientInfo) => {
    setFormData(ingredient);
    setEditingIngredient(ingredient);
    setShowForm(true);
  };

  const handleDelete = (ingredient: IngredientInfo) => {
    setDeleteTarget(ingredient);
  };

  const confirmDelete = () => {
    if (deleteTarget) {
      deleteIngredient(deleteTarget.name);
      loadIngredients();
      toast.success('재료가 삭제되었습니다.');
      setDeleteTarget(null);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!formData.name.trim()) {
      toast.error('재료명을 입력해주세요.');
      return;
    }

    if (!formData.category.trim()) {
      toast.error('카테고리를 입력해주세요.');
      return;
    }

    if (editingIngredient) {
      updateIngredient(editingIngredient.name, formData);
      toast.success('재료가 수정되었습니다.');
    } else {
      addIngredient(formData);
      toast.success('재료가 추가되었습니다.');
    }

    loadIngredients();
    resetForm();
  };

  const handleArrayInput = (field: 'vitamins' | 'minerals' | 'benefits', value: string) => {
    const array = value.split(',').map(item => item.trim()).filter(item => item !== '');
    setFormData({ ...formData, [field]: array });
  };

  const filteredIngredients = ingredients.filter(ingredient =>
    ingredient.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    ingredient.category.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <>
      <Dialog open={open} onOpenChange={onOpenChange}>
        <DialogContent className="max-w-6xl max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Apple className="w-6 h-6 text-orange-500" />
              재료 관리
            </DialogTitle>
            <DialogDescription>
              {isAdmin ? '재료를 추가, 수정, 삭제할 수 있습니다.' : '등록된 재료 정보를 확인할 수 있습니다.'}
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-6">
            {/* Search and Add Button */}
            <div className="flex gap-4">
              <div className="flex-1 relative">
                <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                <Input
                  type="text"
                  placeholder="재료 검색..."
                  className="pl-10"
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                />
              </div>
              {isAdmin && !showForm && (
                <Button
                  onClick={() => setShowForm(true)}
                  className="bg-orange-500 hover:bg-orange-600"
                >
                  <Plus className="w-4 h-4 mr-2" />
                  재료 추가
                </Button>
              )}
            </div>

            {/* Add/Edit Form */}
            {showForm && isAdmin && (
              <Card className="border-orange-200">
                <CardHeader>
                  <CardTitle className="flex items-center justify-between">
                    <span>{editingIngredient ? '재료 수정' : '새 재료 추가'}</span>
                    <Button
                      variant="ghost"
                      size="icon"
                      onClick={resetForm}
                    >
                      <X className="w-4 h-4" />
                    </Button>
                  </CardTitle>
                </CardHeader>
                <CardContent>
                  <form onSubmit={handleSubmit} className="space-y-4">
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                      <div className="space-y-2">
                        <Label htmlFor="name">재료명 *</Label>
                        <Input
                          id="name"
                          value={formData.name}
                          onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                          placeholder="예: 토마토"
                          required
                          disabled={!!editingIngredient}
                        />
                      </div>

                      <div className="space-y-2">
                        <Label htmlFor="category">카테고리 *</Label>
                        <Input
                          id="category"
                          value={formData.category}
                          onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                          placeholder="예: 채소"
                          required
                        />
                      </div>

                      <div className="space-y-2">
                        <Label htmlFor="calories">칼로리 (kcal/100g)</Label>
                        <Input
                          id="calories"
                          type="number"
                          value={formData.calories}
                          onChange={(e) => setFormData({ ...formData, calories: Number(e.target.value) })}
                          placeholder="0"
                        />
                      </div>

                      <div className="space-y-2">
                        <Label htmlFor="protein">단백질 (g/100g)</Label>
                        <Input
                          id="protein"
                          type="number"
                          step="0.1"
                          value={formData.protein}
                          onChange={(e) => setFormData({ ...formData, protein: Number(e.target.value) })}
                          placeholder="0"
                        />
                      </div>

                      <div className="space-y-2">
                        <Label htmlFor="carbs">탄수화물 (g/100g)</Label>
                        <Input
                          id="carbs"
                          type="number"
                          step="0.1"
                          value={formData.carbs}
                          onChange={(e) => setFormData({ ...formData, carbs: Number(e.target.value) })}
                          placeholder="0"
                        />
                      </div>

                      <div className="space-y-2">
                        <Label htmlFor="fat">지방 (g/100g)</Label>
                        <Input
                          id="fat"
                          type="number"
                          step="0.1"
                          value={formData.fat}
                          onChange={(e) => setFormData({ ...formData, fat: Number(e.target.value) })}
                          placeholder="0"
                        />
                      </div>

                      <div className="space-y-2">
                        <Label htmlFor="fiber">식이섬유 (g/100g)</Label>
                        <Input
                          id="fiber"
                          type="number"
                          step="0.1"
                          value={formData.fiber}
                          onChange={(e) => setFormData({ ...formData, fiber: Number(e.target.value) })}
                          placeholder="0"
                        />
                      </div>
                    </div>

                    <div className="space-y-2">
                      <Label htmlFor="vitamins">비타민 (쉼표로 구분)</Label>
                      <Input
                        id="vitamins"
                        value={formData.vitamins.join(', ')}
                        onChange={(e) => handleArrayInput('vitamins', e.target.value)}
                        placeholder="예: 비타민 C, 비타민 A"
                      />
                    </div>

                    <div className="space-y-2">
                      <Label htmlFor="minerals">미네랄 (쉼표로 구분)</Label>
                      <Input
                        id="minerals"
                        value={formData.minerals.join(', ')}
                        onChange={(e) => handleArrayInput('minerals', e.target.value)}
                        placeholder="예: 칼륨, 철분"
                      />
                    </div>

                    <div className="space-y-2">
                      <Label htmlFor="benefits">효능 (쉼표로 구분)</Label>
                      <Input
                        id="benefits"
                        value={formData.benefits.join(', ')}
                        onChange={(e) => handleArrayInput('benefits', e.target.value)}
                        placeholder="예: 항산화 효과, 면역력 강화"
                      />
                    </div>

                    <div className="space-y-2">
                      <Label htmlFor="description">설명</Label>
                      <Textarea
                        id="description"
                        value={formData.description}
                        onChange={(e) => setFormData({ ...formData, description: e.target.value })}
                        placeholder="재료에 대한 설명을 입력하세요"
                        rows={3}
                      />
                    </div>

                    <div className="flex gap-2 justify-end">
                      <Button type="button" variant="outline" onClick={resetForm}>
                        취소
                      </Button>
                      <Button type="submit" className="bg-orange-500 hover:bg-orange-600">
                        {editingIngredient ? '수정' : '추가'}
                      </Button>
                    </div>
                  </form>
                </CardContent>
              </Card>
            )}

            {/* Ingredients List */}
            <div className="space-y-4">
              <h3 className="text-lg font-semibold">
                등록된 재료 ({filteredIngredients.length}개)
              </h3>
              
              {filteredIngredients.length === 0 ? (
                <div className="text-center py-8 text-gray-500">
                  <Apple className="w-12 h-12 mx-auto mb-2 text-gray-400" />
                  <p>등록된 재료가 없습니다.</p>
                </div>
              ) : (
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  {filteredIngredients.map((ingredient) => (
                    <Card key={ingredient.name} className="hover:shadow-md transition-shadow">
                      <CardHeader>
                        <div className="flex items-start justify-between">
                          <div>
                            <CardTitle className="text-lg">{ingredient.name}</CardTitle>
                            <Badge variant="secondary" className="mt-1">
                              {ingredient.category}
                            </Badge>
                          </div>
                          {isAdmin && (
                            <div className="flex gap-2">
                              <Button
                                variant="ghost"
                                size="icon"
                                onClick={() => handleEdit(ingredient)}
                              >
                                <Edit className="w-4 h-4" />
                              </Button>
                              <Button
                                variant="ghost"
                                size="icon"
                                onClick={() => handleDelete(ingredient)}
                                className="text-red-500 hover:text-red-700"
                              >
                                <Trash2 className="w-4 h-4" />
                              </Button>
                            </div>
                          )}
                        </div>
                      </CardHeader>
                      <CardContent className="space-y-3">
                        {ingredient.description && (
                          <p className="text-sm text-gray-600">{ingredient.description}</p>
                        )}
                        
                        <div className="grid grid-cols-2 gap-2 text-sm">
                          <div>
                            <span className="text-gray-500">칼로리:</span>
                            <span className="ml-1 font-medium">{ingredient.calories} kcal</span>
                          </div>
                          <div>
                            <span className="text-gray-500">단백질:</span>
                            <span className="ml-1 font-medium">{ingredient.protein}g</span>
                          </div>
                          <div>
                            <span className="text-gray-500">탄수화물:</span>
                            <span className="ml-1 font-medium">{ingredient.carbs}g</span>
                          </div>
                          <div>
                            <span className="text-gray-500">지방:</span>
                            <span className="ml-1 font-medium">{ingredient.fat}g</span>
                          </div>
                        </div>

                        {ingredient.vitamins.length > 0 && (
                          <div className="text-sm">
                            <span className="text-gray-500">비타민:</span>
                            <span className="ml-1">{ingredient.vitamins.join(', ')}</span>
                          </div>
                        )}

                        {ingredient.benefits.length > 0 && (
                          <div className="text-sm">
                            <span className="text-gray-500">효능:</span>
                            <span className="ml-1">{ingredient.benefits.join(', ')}</span>
                          </div>
                        )}
                      </CardContent>
                    </Card>
                  ))}
                </div>
              )}
            </div>
          </div>
        </DialogContent>
      </Dialog>

      {/* Delete Confirmation Dialog */}
      <AlertDialog open={!!deleteTarget} onOpenChange={(open) => !open && setDeleteTarget(null)}>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>재료 삭제</AlertDialogTitle>
            <AlertDialogDescription>
              정말 "{deleteTarget?.name}" 재료를 삭제하시겠습니까?
              이 작업은 되돌릴 수 없습니다.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel>취소</AlertDialogCancel>
            <AlertDialogAction onClick={confirmDelete} className="bg-red-500 hover:bg-red-600">
              삭제
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </>
  );
}