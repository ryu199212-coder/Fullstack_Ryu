import { 
  DropdownMenu, 
  DropdownMenuContent, 
  DropdownMenuItem, 
  DropdownMenuLabel, 
  DropdownMenuSeparator, 
  DropdownMenuTrigger 
} from "./ui/dropdown-menu";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { User, Heart, Settings, LogOut, BookOpen, Apple } from "lucide-react";

interface UserMenuProps {
  user: {
    name: string;
    email: string;
    profileImage?: string;
  };
  onLogout: () => void;
  onViewFavorites: () => void;
  onViewMyRecipes?: () => void;
  onViewMyPage?: () => void;
  onViewIngredients?: () => void;
  isAdmin?: boolean;
}

export function UserMenu({ user, onLogout, onViewFavorites, onViewMyRecipes, onViewMyPage, onViewIngredients, isAdmin }: UserMenuProps) {
  const getInitials = (name: string) => {
    return name
      .split(" ")
      .map((n) => n[0])
      .join("")
      .toUpperCase()
      .slice(0, 2);
  };

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <button className="flex items-center gap-2 hover:opacity-80 transition-opacity">
          <Avatar className="w-10 h-10 border-2 border-orange-500">
            {user.profileImage && <AvatarImage src={user.profileImage} alt={user.name} />}
            <AvatarFallback className="bg-orange-100 text-orange-600">
              {getInitials(user.name)}
            </AvatarFallback>
          </Avatar>
          <div className="hidden md:block text-left">
            <div className="text-sm">{user.name}</div>
            <div className="text-xs text-gray-500">{user.email}</div>
          </div>
        </button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="w-56">
        <DropdownMenuLabel>내 계정</DropdownMenuLabel>
        <DropdownMenuSeparator />
        <DropdownMenuItem className="cursor-pointer" onClick={onViewMyPage}>
          <User className="mr-2 h-4 w-4" />
          <span>마이페이지</span>
        </DropdownMenuItem>
        <DropdownMenuItem className="cursor-pointer" onClick={onViewFavorites}>
          <Heart className="mr-2 h-4 w-4" />
          <span>즐겨찾기</span>
        </DropdownMenuItem>
        {onViewMyRecipes && (
          <DropdownMenuItem className="cursor-pointer" onClick={onViewMyRecipes}>
            <BookOpen className="mr-2 h-4 w-4" />
            <span>내 레시피</span>
          </DropdownMenuItem>
        )}
        {onViewIngredients && (
          <DropdownMenuItem className="cursor-pointer" onClick={onViewIngredients}>
            <Apple className="mr-2 h-4 w-4" />
            <span>재료 관리</span>
          </DropdownMenuItem>
        )}
        {isAdmin && (
          <DropdownMenuItem className="cursor-pointer">
            <Settings className="mr-2 h-4 w-4" />
            <span>관리자 설정</span>
          </DropdownMenuItem>
        )}
        <DropdownMenuSeparator />
        <DropdownMenuItem className="cursor-pointer text-red-600" onClick={onLogout}>
          <LogOut className="mr-2 h-4 w-4" />
          <span>로그아웃</span>
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}