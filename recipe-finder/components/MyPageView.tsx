import { userService, UpdateProfileDto } from '../services/userService';
import { UserDto } from '../services/authService';
import { toast } from 'sonner';
import { Recipe } from './RecipeCard';
import { ImageWithFallback } from './figma/ImageWithFallback';
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

interface MyPageViewProps {
  userId: string;
  onBack: () => void;
  userRecipes?: Recipe[];
  favoriteRecipes?: Recipe[];
  onDeleteRecipe: (recipeId: number) => void;
  onEditRecipe: (recipe: Recipe) => void;
  onRemoveFavorite: (recipeId: number) => void;
  onRecipeClick: (recipe: Recipe) => void;
  onAccountDelete?: () => void;
}

// 기본 프로필 이미지
const DEFAULT_PROFILE_IMAGE = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx1c2VyJTIwYXZhdGFyfGVufDF8fHx8MTc2MzU5OTM4Nnww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral";

export function MyPageView({ 
  userId, 
  onBack, 
  userRecipes = [],
  favoriteRecipes = [],
  onDeleteRecipe,
  onEditRecipe,
  onRemoveFavorite,
  onRecipeClick,
  onAccountDelete
}: MyPageViewProps) {
  const [profile, setProfile] = useState<UserDto | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [isSaving, setIsSaving] = useState(false);
  const [isUploadingImage, setIsUploadingImage] = useState(false);
  const [showDeleteDialog, setShowDeleteDialog] = useState(false);
  const [showPasswordChange, setShowPasswordChange] = useState(false);
  const [deletePassword, setDeletePassword] = useState('');
  
  // 편집 폼 상태
  const [editForm, setEditForm] = useState({
    name: '',
    email: '',
    phone: '',
  });

  // 비밀번호 변경 폼
  const [passwordForm, setPasswordForm] = useState({
    currentPassword: '',
    newPassword: '',
    confirmPassword: '',
  });

  // 프로필 불러오기
  useEffect(() => {
    if (userId) {
      loadProfile();
    }
  }, [userId]);

  const loadProfile = async () => {
    setIsLoading(true);
    try {
      const profileData = await userService.getProfile(userId);
      setProfile(profileData);
      setEditForm({
        name: profileData.name || '',
        email: profileData.email || '',
        phone: profileData.phone || '',
      });
    } catch (error) {
      toast.error('프로필을 불러오는데 실패했습니다.');
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };

  // 프로필 이미지 업로드
  const handleImageUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    // 파일 크기 체크 (5MB 제한)
    if (file.size > 5 * 1024 * 1024) {
      toast.error('파일 크기는 5MB 이하여야 합니다.');
      return;
    }

    // 파일 타입 체크
    if (!file.type.startsWith('image/')) {
      toast.error('이미지 파일만 업로드 가능합니다.');
      return;
    }

    setIsUploadingImage(true);
    try {
      const imageUrl = await userService.uploadProfileImage(userId, file);
      
      // 프로필 상태 업데이트
      setProfile((prev) => prev ? { ...prev, profileImage: imageUrl } : null);
      
      toast.success('프로필 이미지가 업데이트되었습니다!');
    } catch (error) {
      toast.error('이미지 업로드에 실패했습니다.');
      console.error(error);
    } finally {
      setIsUploadingImage(false);
    }
  };

  // 프로필 이미지 삭제
  const handleRemoveImage = async () => {
    try {
      await userService.deleteProfileImage(userId);
      setProfile((prev) => prev ? { ...prev, profileImage: undefined } : null);
      toast.success('프로필 이미지가 삭제되었습니다.');
    } catch (error) {
      toast.error('이미지 삭제에 실패했습니다.');
      console.error(error);
    }
  };

  // 프로필 수정 저장
  const handleSaveProfile = async () => {
    setIsSaving(true);
    try {
      const updatedProfile = await userService.updateProfile(userId, editForm);
      setProfile(updatedProfile);
      setIsEditing(false);
      toast.success('프로필이 업데이트되었습니다!');
    } catch (error) {
      toast.error('프로필 업데이트에 실패했습니다.');
      console.error(error);
    } finally {
      setIsSaving(false);
    }
  };

  // 편집 취소
  const handleCancelEdit = () => {
    if (profile) {
      setEditForm({
        name: profile.name || '',
        email: profile.email || '',
        phone: profile.phone || '',
      });
    }
    setIsEditing(false);
  };

  const profileImage = profile?.profileImage || DEFAULT_PROFILE_IMAGE;
  const userInitials = profile?.name?.substring(0, 2).toUpperCase() || 'U';

  const difficultyColor = {
    쉬움: "bg-green-100 text-green-800 hover:bg-green-100",
    보통: "bg-yellow-100 text-yellow-800 hover:bg-yellow-100",
    어려움: "bg-red-100 text-red-800 hover:bg-red-100",
  };

  return (
    <div className="min-h-screen bg-gradient-to-b from-orange-50 to-white">
      {/* Header */}
      <div className="bg-white shadow-sm">
        <div className="container mx-auto px-4 py-6">
          <Button
            variant="ghost"
            onClick={onBack}
            className="mb-4"
          >
            <ArrowLeft className="w-4 h-4 mr-2" />
            돌아가기
          </Button>
          <h1 className="text-orange-500">마이페이지</h1>
        </div>
      </div>

      {/* Main Content */}
      <div className="container mx-auto px-4 py-8">
        {isLoading ? (
          <div className="flex items-center justify-center py-12">
            <Loader2 className="w-8 h-8 animate-spin text-orange-500" />
          </div>
        ) : profile ? (
          <div className="max-w-6xl mx-auto">
            {/* 프로필 헤더 */}
            <Card className="mb-6">
              <CardContent className="pt-6">
                <div className="flex flex-col md:flex-row gap-6 items-start">
                  {/* 프로필 이미지 */}
                  <div className="flex flex-col items-center gap-4">
                    <div className="relative">
                      <Avatar className="w-32 h-32">
                        <AvatarImage src={profileImage} alt={profile.name} />
                        <AvatarFallback className="text-2xl">{userInitials}</AvatarFallback>
                      </Avatar>
                      
                      {isUploadingImage && (
                        <div className="absolute inset-0 flex items-center justify-center bg-black/50 rounded-full">
                          <Loader2 className="w-8 h-8 animate-spin text-white" />
                        </div>
                      )}
                    </div>

                    <div className="flex flex-col gap-2">
                      <Button
                        variant="outline"
                        size="sm"
                        onClick={() => document.getElementById('profile-image-input')?.click()}
                        disabled={isUploadingImage}
                      >
                        <Camera className="w-4 h-4 mr-2" />
                        사진 변경
                      </Button>
                      
                      {profile.profileImage && (
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={handleRemoveImage}
                          disabled={isUploadingImage}
                        >
                          기본 이미지로
                        </Button>
                      )}
                    </div>

                    <input
                      id="profile-image-input"
                      type="file"
                      accept="image/*"
                      className="hidden"
                      onChange={handleImageUpload}
                    />
                  </div>

                  {/* 프로필 정보 */}
                  <div className="flex-1 space-y-4">
                    <div className="flex items-center justify-between">
                      <h2 className="flex items-center gap-2">
                        <User className="w-5 h-5" />
                        프로필 정보
                      </h2>
                      {!isEditing && (
                        <Button
                          variant="outline"
                          size="sm"
                          onClick={() => setIsEditing(true)}
                        >
                          수정
                        </Button>
                      )}
                    </div>

                    {isEditing ? (
                      <div className="space-y-4">
                        <div className="space-y-2">
                          <Label htmlFor="edit-name">이름</Label>
                          <Input
                            id="edit-name"
                            value={editForm.name}
                            onChange={(e) => setEditForm({ ...editForm, name: e.target.value })}
                            placeholder="이름을 입력하세요"
                          />
                        </div>

                        <div className="space-y-2">
                          <Label htmlFor="edit-email">이메일</Label>
                          <Input
                            id="edit-email"
                            type="email"
                            value={editForm.email}
                            onChange={(e) => setEditForm({ ...editForm, email: e.target.value })}
                            placeholder="이메일을 입력하세요"
                          />
                        </div>

                        <div className="space-y-2">
                          <Label htmlFor="edit-phone">전화번호</Label>
                          <Input
                            id="edit-phone"
                            type="tel"
                            value={editForm.phone}
                            onChange={(e) => setEditForm({ ...editForm, phone: e.target.value })}
                            placeholder="전화번호를 입력하세요"
                          />
                        </div>

                        <div className="flex gap-2 justify-end">
                          <Button
                            variant="outline"
                            onClick={handleCancelEdit}
                            disabled={isSaving}
                          >
                            취소
                          </Button>
                          <Button
                            onClick={handleSaveProfile}
                            disabled={isSaving}
                            className="bg-orange-500 hover:bg-orange-600"
                          >
                            {isSaving ? (
                              <>
                                <Loader2 className="w-4 h-4 mr-2 animate-spin" />
                                저장 중...
                              </>
                            ) : (
                              '저장'
                            )}
                          </Button>
                        </div>
                      </div>
                    ) : (
                      <div className="space-y-3">
                        <div className="flex items-center gap-3 text-gray-600">
                          <User className="w-4 h-4" />
                          <span>{profile.name}</span>
                        </div>

                        <div className="flex items-center gap-3 text-gray-600">
                          <Mail className="w-4 h-4" />
                          <span>{profile.email}</span>
                        </div>

                        {profile.createdAt && (
                          <div className="flex items-center gap-3 text-gray-600">
                            <Calendar className="w-4 h-4" />
                            <span>가입일: {new Date(profile.createdAt).toLocaleDateString('ko-KR')}</span>
                          </div>
                        )}

                        {profile.phone && (
                          <div className="flex items-center gap-3 text-gray-600">
                            <Phone className="w-4 h-4" />
                            <span>{profile.phone}</span>
                          </div>
                        )}
                      </div>
                    )}
                  </div>

                  {/* 통계 */}
                  <div className="grid grid-cols-2 gap-4 md:w-64">
                    <Card>
                      <CardHeader className="pb-3">
                        <CardTitle className="text-sm flex items-center gap-2">
                          <BookOpen className="w-4 h-4 text-orange-500" />
                          내 레시피
                        </CardTitle>
                      </CardHeader>
                      <CardContent>
                        <p className="text-3xl">{userRecipes.length}</p>
                      </CardContent>
                    </Card>

                    <Card>
                      <CardHeader className="pb-3">
                        <CardTitle className="text-sm flex items-center gap-2">
                          <Heart className="w-4 h-4 text-red-500" />
                          즐겨찾기
                        </CardTitle>
                      </CardHeader>
                      <CardContent>
                        <p className="text-3xl">{favoriteRecipes.length}</p>
                      </CardContent>
                    </Card>
                  </div>
                </div>
              </CardContent>
            </Card>

            <Separator className="my-6" />

            {/* 탭 섹션 */}
            <Tabs defaultValue="recipes" className="w-full">
              <TabsList className="grid w-full grid-cols-2 max-w-md">
                <TabsTrigger value="recipes">
                  <BookOpen className="w-4 h-4 mr-2" />
                  내 레시피 ({userRecipes.length})
                </TabsTrigger>
                <TabsTrigger value="favorites">
                  <Heart className="w-4 h-4 mr-2" />
                  즐겨찾기 ({favoriteRecipes.length})
                </TabsTrigger>
              </TabsList>

              {/* 내 레시피 탭 */}
              <TabsContent value="recipes" className="mt-6">
                {userRecipes.length > 0 ? (
                  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                    {userRecipes.map((recipe) => (
                      <Card key={recipe.id} className="overflow-hidden hover:shadow-lg transition-shadow">
                        <div 
                          className="relative h-48 cursor-pointer"
                          onClick={() => onRecipeClick(recipe)}
                        >
                          <ImageWithFallback
                            src={recipe.image}
                            alt={recipe.title}
                            className="w-full h-full object-cover"
                          />
                        </div>
                        <CardContent className="p-4">
                          <div className="flex items-start justify-between mb-2">
                            <h3 
                              className="cursor-pointer hover:text-orange-500"
                              onClick={() => onRecipeClick(recipe)}
                            >
                              {recipe.title}
                            </h3>
                          </div>
                          
                          <div className="flex flex-wrap gap-2 mb-3">
                            <Badge variant="secondary">{recipe.category}</Badge>
                            <Badge className={difficultyColor[recipe.difficulty]}>
                              {recipe.difficulty}
                            </Badge>
                          </div>

                          <p className="text-sm text-gray-600 mb-4 line-clamp-2">
                            {recipe.description}
                          </p>

                          <div className="flex items-center justify-between text-sm text-gray-500 mb-4">
                            <div className="flex items-center gap-1">
                              <Clock className="w-4 h-4" />
                              <span>{recipe.cookTime}</span>
                            </div>
                            <div className="flex items-center gap-1">
                              <Users className="w-4 h-4" />
                              <span>{recipe.servings}인분</span>
                            </div>
                          </div>

                          <div className="flex gap-2">
                            <Button
                              variant="outline"
                              size="sm"
                              className="flex-1"
                              onClick={() => onEditRecipe(recipe)}
                            >
                              <Edit className="w-4 h-4 mr-2" />
                              수정
                            </Button>
                            <Button
                              variant="outline"
                              size="sm"
                              className="flex-1 text-red-600 hover:text-red-700"
                              onClick={() => {
                                if (confirm('정말 이 레시피를 삭제하시겠습니까?')) {
                                  onDeleteRecipe(recipe.id);
                                }
                              }}
                            >
                              <Trash2 className="w-4 h-4 mr-2" />
                              삭제
                            </Button>
                          </div>
                        </CardContent>
                      </Card>
                    ))}
                  </div>
                ) : (
                  <div className="text-center py-16">
                    <BookOpen className="w-16 h-16 mx-auto text-gray-300 mb-4" />
                    <p className="text-gray-500">아직 작성한 레시피가 없습니다.</p>
                  </div>
                )}
              </TabsContent>

              {/* 즐겨찾기 탭 */}
              <TabsContent value="favorites" className="mt-6">
                {favoriteRecipes.length > 0 ? (
                  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                    {favoriteRecipes.map((recipe) => (
                      <Card key={recipe.id} className="overflow-hidden hover:shadow-lg transition-shadow">
                        <div 
                          className="relative h-48 cursor-pointer"
                          onClick={() => onRecipeClick(recipe)}
                        >
                          <ImageWithFallback
                            src={recipe.image}
                            alt={recipe.title}
                            className="w-full h-full object-cover"
                          />
                        </div>
                        <CardContent className="p-4">
                          <div className="flex items-start justify-between mb-2">
                            <h3 
                              className="cursor-pointer hover:text-orange-500"
                              onClick={() => onRecipeClick(recipe)}
                            >
                              {recipe.title}
                            </h3>
                          </div>
                          
                          <div className="flex flex-wrap gap-2 mb-3">
                            <Badge variant="secondary">{recipe.category}</Badge>
                            <Badge className={difficultyColor[recipe.difficulty]}>
                              {recipe.difficulty}
                            </Badge>
                          </div>

                          <p className="text-sm text-gray-600 mb-4 line-clamp-2">
                            {recipe.description}
                          </p>

                          <div className="flex items-center justify-between text-sm text-gray-500 mb-4">
                            <div className="flex items-center gap-1">
                              <Clock className="w-4 h-4" />
                              <span>{recipe.cookTime}</span>
                            </div>
                            <div className="flex items-center gap-1">
                              <Users className="w-4 h-4" />
                              <span>{recipe.servings}인분</span>
                            </div>
                          </div>

                          <Button
                            variant="outline"
                            size="sm"
                            className="w-full text-red-600 hover:text-red-700"
                            onClick={() => {
                              if (confirm('즐겨찾기에서 제거하시겠습니까?')) {
                                onRemoveFavorite(recipe.id);
                              }
                            }}
                          >
                            <X className="w-4 h-4 mr-2" />
                            즐겨찾기 해제
                          </Button>
                        </CardContent>
                      </Card>
                    ))}
                  </div>
                ) : (
                  <div className="text-center py-16">
                    <Heart className="w-16 h-16 mx-auto text-gray-300 mb-4" />
                    <p className="text-gray-500">아직 즐겨찾기한 레시피가 없습니다.</p>
                  </div>
                )}
              </TabsContent>
            </Tabs>

            {/* 비밀번호 변경 다이얼로그 */}
            <AlertDialog open={showPasswordChange} onOpenChange={setShowPasswordChange}>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle>비밀번호 변경</AlertDialogTitle>
                  <AlertDialogDescription>
                    현재 비밀번호와 새로운 비밀번호를 입력하세요.
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <div className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="current-password">현재 비밀번호</Label>
                    <Input
                      id="current-password"
                      type="password"
                      value={passwordForm.currentPassword}
                      onChange={(e) => setPasswordForm({ ...passwordForm, currentPassword: e.target.value })}
                      placeholder="현재 비밀번호를 입력하세요"
                    />
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="new-password">새 비밀번호</Label>
                    <Input
                      id="new-password"
                      type="password"
                      value={passwordForm.newPassword}
                      onChange={(e) => setPasswordForm({ ...passwordForm, newPassword: e.target.value })}
                      placeholder="새 비밀번호를 입력하세요"
                    />
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="confirm-password">비밀번호 확인</Label>
                    <Input
                      id="confirm-password"
                      type="password"
                      value={passwordForm.confirmPassword}
                      onChange={(e) => setPasswordForm({ ...passwordForm, confirmPassword: e.target.value })}
                      placeholder="비밀번호를 다시 입력하세요"
                    />
                  </div>
                </div>
                <AlertDialogFooter>
                  <AlertDialogCancel>취소</AlertDialogCancel>
                  <AlertDialogAction
                    onClick={async () => {
                      if (passwordForm.newPassword !== passwordForm.confirmPassword) {
                        toast.error('새 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
                        return;
                      }

                      try {
                        await userService.changePassword(userId, passwordForm);
                        toast.success('비밀번호가 변경되었습니다.');
                        setShowPasswordChange(false);
                      } catch (error) {
                        toast.error('비밀번호 변경에 실패했습니다.');
                        console.error(error);
                      }
                    }}
                  >
                    변경
                  </AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>

            {/* 계정 삭제 다이얼로그 */}
            <AlertDialog open={showDeleteDialog} onOpenChange={(open) => {
              setShowDeleteDialog(open);
              if (!open) {
                setDeletePassword(''); // 다이얼로그 닫을 때 비밀번호 초기화
              }
            }}>
              <AlertDialogContent>
                <AlertDialogHeader>
                  <AlertDialogTitle className="flex items-center gap-2 text-red-600">
                    <AlertTriangle className="w-5 h-5" />
                    계정 삭제
                  </AlertDialogTitle>
                  <AlertDialogDescription>
                    계정을 삭제하면 모든 데이터가 영구적으로 삭제되며 복구할 수 없습니다.
                  </AlertDialogDescription>
                </AlertDialogHeader>
                <div className="space-y-4">
                  <div className="bg-red-50 border border-red-200 rounded-lg p-4">
                    <p className="text-sm text-red-800">
                      ⚠️ 계정 삭제 시 다음 데이터가 모두 삭제됩니다:
                    </p>
                    <ul className="text-sm text-red-700 mt-2 ml-4 list-disc">
                      <li>내가 작성한 모든 레시피</li>
                      <li>즐겨찾기 목록</li>
                      <li>프로필 정보</li>
                    </ul>
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="delete-password" className="text-gray-700">
                      비밀번호를 입력하여 본인 확인
                    </Label>
                    <div className="relative">
                      <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                      <Input
                        id="delete-password"
                        type="password"
                        placeholder="비밀번호를 입력하세요"
                        className="pl-10"
                        value={deletePassword}
                        onChange={(e) => setDeletePassword(e.target.value)}
                        autoFocus
                      />
                    </div>
                  </div>
                </div>
                <AlertDialogFooter>
                  <AlertDialogCancel onClick={() => setDeletePassword('')}>
                    취소
                  </AlertDialogCancel>
                  <AlertDialogAction
                    onClick={() => {
                      if (!deletePassword) {
                        toast.error('비밀번호를 입력해주세요.');
                        return;
                      }

                      // Mock: 실제로는 API로 비밀번호 확인
                      const savedUser = localStorage.getItem('user');
                      if (savedUser) {
                        const user = JSON.parse(savedUser);
                        // 실제 구현에서는 해시된 비밀번호와 비교
                        // 여기서는 간단히 Mock 처리
                        if (deletePassword.length < 6) {
                          toast.error('비밀번호가 일치하지 않습니다.');
                          return;
                        }
                      }

                      setShowDeleteDialog(false);
                      setDeletePassword('');
                      if (onAccountDelete) {
                        onAccountDelete();
                      }
                    }}
                    className="bg-red-500 hover:bg-red-600"
                    disabled={!deletePassword}
                  >
                    계정 삭제
                  </AlertDialogAction>
                </AlertDialogFooter>
              </AlertDialogContent>
            </AlertDialog>

            {/* 비밀번호 변경 버튼 */}
            <Button
              variant="outline"
              size="sm"
              className="mt-4"
              onClick={() => setShowPasswordChange(true)}
            >
              <Lock className="w-4 h-4 mr-2" />
              비밀번호 변경
            </Button>

            {/* 계정 삭제 버튼 */}
            <Button
              variant="outline"
              size="sm"
              className="mt-4 text-red-600 hover:text-red-700"
              onClick={() => setShowDeleteDialog(true)}
            >
              <AlertTriangle className="w-4 h-4 mr-2" />
              계정 삭제
            </Button>
          </div>
        ) : (
          <div className="text-center py-8 text-gray-500">
            프로필을 불러올 수 없습니다.
          </div>
        )}
      </div>
    </div>
  );
}