import { useState } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Button } from "./ui/button";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./ui/tabs";
import { User, Mail, Lock, Phone, Camera } from "lucide-react";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { toast } from "sonner";
import { Separator } from "./ui/separator";

interface LoginDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onLogin: (email: string, password: string) => void;
  onSignup: (name: string, email: string, password: string, phone: string, profileImage?: string) => void;
  onSocialLogin?: (provider: 'google' | 'naver' | 'kakao') => void;
}

// 기본 프로필 이미지
const DEFAULT_PROFILE_IMAGE = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx1c2VyJTIwYXZhdGFyfGVufDF8fHx8MTc2MzU5OTM4Nnww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral";

export function LoginDialog({ open, onOpenChange, onLogin, onSignup, onSocialLogin }: LoginDialogProps) {
  const [activeTab, setActiveTab] = useState("login");
  
  // 로그인 폼
  const [loginEmail, setLoginEmail] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  
  // 회원가입 폼
  const [signupName, setSignupName] = useState("");
  const [signupEmail, setSignupEmail] = useState("");
  const [signupPassword, setSignupPassword] = useState("");
  const [signupConfirmPassword, setSignupConfirmPassword] = useState("");
  const [signupPhone, setSignupPhone] = useState("");
  const [signupProfileImage, setSignupProfileImage] = useState<string>(DEFAULT_PROFILE_IMAGE);
  
  // 아이디/비밀번호 찾기 폼
  const [findType, setFindType] = useState<"email" | "password" | null>(null);
  const [findPhone, setFindPhone] = useState("");
  const [findEmail, setFindEmail] = useState("");

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (loginEmail && loginPassword) {
      onLogin(loginEmail, loginPassword);
      resetForms();
    }
  };

  const handleSignup = (e: React.FormEvent) => {
    e.preventDefault();
    
    // 유효성 검사
    if (!signupName || !signupEmail || !signupPassword || !signupPhone) {
      toast.error("모든 필수 항목을 입력해주세요.");
      return;
    }
    
    if (signupPassword !== signupConfirmPassword) {
      toast.error("비밀번호가 일치하지 않습니다.");
      return;
    }
    
    if (signupPassword.length < 6) {
      toast.error("비밀번호는 6자 이상이어야 합니다.");
      return;
    }
    
    // 전화번호 형식 검증
    const phoneRegex = /^01[0-9]-?[0-9]{3,4}-?[0-9]{4}$/;
    if (!phoneRegex.test(signupPhone)) {
      toast.error("올바른 전화번호 형식이 아닙니다. (예: 010-1234-5678)");
      return;
    }
    
    onSignup(signupName, signupEmail, signupPassword, signupPhone, signupProfileImage);
    resetForms();
  };

  const handleFindAccount = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (findType === "email") {
      // 아이디(이메일) 찾기 - 전화번호로 찾기
      if (!findPhone) {
        toast.error("전화번호를 입력해주세요.");
        return;
      }
      
      // 실제로는 API 호출
      // Mock: localStorage에서 찾기
      const savedUser = localStorage.getItem("user");
      if (savedUser) {
        const user = JSON.parse(savedUser);
        if (user.phone === findPhone) {
          toast.success(`회원님의 이메일은 ${user.email} 입니다.`);
        } else {
          toast.error("일치하는 회원 정보가 없습니다.");
        }
      } else {
        toast.error("일치하는 회원 정보가 없습니다.");
      }
    } else if (findType === "password") {
      // 비밀번호 찾기 - 이메일과 전화번호로 찾기
      if (!findEmail || !findPhone) {
        toast.error("이메일과 전화번호를 모두 입력해주세요.");
        return;
      }
      
      // 실제로는 API 호출하여 임시 비밀번호 발송
      const savedUser = localStorage.getItem("user");
      if (savedUser) {
        const user = JSON.parse(savedUser);
        if (user.email === findEmail && user.phone === findPhone) {
          toast.success("임시 비밀번호가 이메일로 전송되었습니다.");
          // 실제로는 이메일 발송
        } else {
          toast.error("일치하는 회원 정보가 없습니다.");
        }
      } else {
        toast.error("일치하는 회원 정보가 없습니다.");
      }
    }
    
    setFindType(null);
    setFindPhone("");
    setFindEmail("");
  };

  const handleImageSelect = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    // 파일 크기 체크 (5MB 제한)
    if (file.size > 5 * 1024 * 1024) {
      toast.error("파일 크기는 5MB 이하여야 합니다.");
      return;
    }

    // 파일 타입 체크
    if (!file.type.startsWith("image/")) {
      toast.error("이미지 파일만 업로드 가능합니다.");
      return;
    }

    // 이미지 미리보기
    const reader = new FileReader();
    reader.onloadend = () => {
      setSignupProfileImage(reader.result as string);
    };
    reader.readAsDataURL(file);
  };

  const handleSocialLogin = (provider: 'google' | 'naver' | 'kakao') => {
    // 소셜 로그인 처리
    if (onSocialLogin) {
      onSocialLogin(provider);
    } else {
      // 기본 처리: 백엔드 OAuth URL로 리다이렉트
      const baseUrl = 'http://localhost:8080'; // Spring 백엔드 URL
      window.location.href = `${baseUrl}/oauth2/authorization/${provider}`;
    }
  };

  const resetForms = () => {
    setLoginEmail("");
    setLoginPassword("");
    setSignupName("");
    setSignupEmail("");
    setSignupPassword("");
    setSignupConfirmPassword("");
    setSignupPhone("");
    setSignupProfileImage(DEFAULT_PROFILE_IMAGE);
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="sm:max-w-md max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>환영합니다!</DialogTitle>
          <DialogDescription>
            로그인하고 나만의 레시피를 저장해보세요
          </DialogDescription>
        </DialogHeader>

        <Tabs value={activeTab} onValueChange={setActiveTab} className="w-full">
          <TabsList className="grid w-full grid-cols-2">
            <TabsTrigger value="login">로그인</TabsTrigger>
            <TabsTrigger value="signup">회원가입</TabsTrigger>
          </TabsList>

          {/* 로그인 탭 */}
          <TabsContent value="login" className="space-y-4">
            {findType ? (
              // 아이디/비밀번호 찾기 폼
              <form onSubmit={handleFindAccount} className="space-y-4">
                <div className="text-center mb-4">
                  <h3 className="text-lg">
                    {findType === "email" ? "이메일 찾기" : "비밀번호 찾기"}
                  </h3>
                  <p className="text-sm text-gray-500 mt-1">
                    {findType === "email" 
                      ? "가입 시 등록한 전화번호를 입력해주세요" 
                      : "가입 시 등록한 이메일과 전화번호를 입력해주세요"}
                  </p>
                </div>

                {findType === "password" && (
                  <div className="space-y-2">
                    <Label htmlFor="find-email">이메일</Label>
                    <div className="relative">
                      <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                      <Input
                        id="find-email"
                        type="email"
                        placeholder="email@example.com"
                        className="pl-10"
                        value={findEmail}
                        onChange={(e) => setFindEmail(e.target.value)}
                        required
                      />
                    </div>
                  </div>
                )}

                <div className="space-y-2">
                  <Label htmlFor="find-phone">전화번호</Label>
                  <div className="relative">
                    <Phone className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <Input
                      id="find-phone"
                      type="tel"
                      placeholder="010-1234-5678"
                      className="pl-10"
                      value={findPhone}
                      onChange={(e) => setFindPhone(e.target.value)}
                      required
                    />
                  </div>
                </div>

                <div className="flex gap-2">
                  <Button 
                    type="button" 
                    variant="outline" 
                    className="flex-1"
                    onClick={() => {
                      setFindType(null);
                      setFindPhone("");
                      setFindEmail("");
                    }}
                  >
                    취소
                  </Button>
                  <Button type="submit" className="flex-1 bg-orange-500 hover:bg-orange-600">
                    찾기
                  </Button>
                </div>
              </form>
            ) : (
              // 로그인 폼
              <>
                {/* 소셜 로그인 버튼 */}
                <div className="space-y-3">
                  <Button
                    type="button"
                    variant="outline"
                    className="w-full h-11 border-2 hover:bg-gray-50"
                    onClick={() => handleSocialLogin('google')}
                  >
                    <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24">
                      <path
                        fill="#4285F4"
                        d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
                      />
                      <path
                        fill="#34A853"
                        d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
                      />
                      <path
                        fill="#FBBC05"
                        d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
                      />
                      <path
                        fill="#EA4335"
                        d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
                      />
                    </svg>
                    구글로 시작하기
                  </Button>

                  <Button
                    type="button"
                    variant="outline"
                    className="w-full h-11 border-2 bg-[#03C75A] hover:bg-[#02b350] text-white border-[#03C75A]"
                    onClick={() => handleSocialLogin('naver')}
                  >
                    <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24" fill="white">
                      <path d="M16.273 12.845L7.376 0H0v24h7.726V11.156L16.624 24H24V0h-7.727v12.845z" />
                    </svg>
                    네이버로 시작하기
                  </Button>

                  <Button
                    type="button"
                    variant="outline"
                    className="w-full h-11 border-2 bg-[#FEE500] hover:bg-[#ffe100] border-[#FEE500]"
                    onClick={() => handleSocialLogin('kakao')}
                  >
                    <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24">
                      <path
                        fill="#000000"
                        d="M12 3C6.477 3 2 6.477 2 10.8c0 2.827 1.88 5.314 4.686 6.72-.194.712-.633 2.367-.732 2.748-.12.462.17.457.357.332.138-.092 2.244-1.497 3.12-2.09.51.07 1.03.11 1.569.11 5.523 0 10-3.477 10-7.8S17.523 3 12 3z"
                      />
                    </svg>
                    카카오로 시작하기
                  </Button>
                </div>

                <div className="relative">
                  <div className="absolute inset-0 flex items-center">
                    <Separator className="w-full" />
                  </div>
                  <div className="relative flex justify-center text-xs uppercase">
                    <span className="bg-white px-2 text-gray-500">또는</span>
                  </div>
                </div>

                <form onSubmit={handleLogin} className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="login-email">이메일</Label>
                    <div className="relative">
                      <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                      <Input
                        id="login-email"
                        type="email"
                        placeholder="email@example.com"
                        className="pl-10"
                        value={loginEmail}
                        onChange={(e) => setLoginEmail(e.target.value)}
                        required
                      />
                    </div>
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="login-password">비밀번호</Label>
                    <div className="relative">
                      <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                      <Input
                        id="login-password"
                        type="password"
                        placeholder="••••••••"
                        className="pl-10"
                        value={loginPassword}
                        onChange={(e) => setLoginPassword(e.target.value)}
                        required
                      />
                    </div>
                  </div>

                  <Button type="submit" className="w-full bg-orange-500 hover:bg-orange-600">
                    로그인
                  </Button>
                </form>

                <div className="flex justify-center gap-4 text-sm text-gray-500">
                  <button 
                    type="button"
                    className="hover:text-orange-500"
                    onClick={() => setFindType("email")}
                  >
                    이메일 찾기
                  </button>
                  <span>|</span>
                  <button 
                    type="button"
                    className="hover:text-orange-500"
                    onClick={() => setFindType("password")}
                  >
                    비밀번호 찾기
                  </button>
                </div>
              </>
            )}
          </TabsContent>

          {/* 회원가입 탭 */}
          <TabsContent value="signup" className="space-y-4">
            {/* 소셜 로그인 버튼 */}
            <div className="space-y-3">
              <Button
                type="button"
                variant="outline"
                className="w-full h-11 border-2 hover:bg-gray-50"
                onClick={() => handleSocialLogin('google')}
              >
                <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24">
                  <path
                    fill="#4285F4"
                    d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
                  />
                  <path
                    fill="#34A853"
                    d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
                  />
                  <path
                    fill="#FBBC05"
                    d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
                  />
                  <path
                    fill="#EA4335"
                    d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
                  />
                </svg>
                구글로 시작하기
              </Button>

              <Button
                type="button"
                variant="outline"
                className="w-full h-11 border-2 bg-[#03C75A] hover:bg-[#02b350] text-white border-[#03C75A]"
                onClick={() => handleSocialLogin('naver')}
              >
                <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24" fill="white">
                  <path d="M16.273 12.845L7.376 0H0v24h7.726V11.156L16.624 24H24V0h-7.727v12.845z" />
                </svg>
                네이버로 시작하기
              </Button>

              <Button
                type="button"
                variant="outline"
                className="w-full h-11 border-2 bg-[#FEE500] hover:bg-[#ffe100] border-[#FEE500]"
                onClick={() => handleSocialLogin('kakao')}
              >
                <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24">
                  <path
                    fill="#000000"
                    d="M12 3C6.477 3 2 6.477 2 10.8c0 2.827 1.88 5.314 4.686 6.72-.194.712-.633 2.367-.732 2.748-.12.462.17.457.357.332.138-.092 2.244-1.497 3.12-2.09.51.07 1.03.11 1.569.11 5.523 0 10-3.477 10-7.8S17.523 3 12 3z"
                  />
                </svg>
                카카오로 시작하기
              </Button>
            </div>

            <div className="relative">
              <div className="absolute inset-0 flex items-center">
                <Separator className="w-full" />
              </div>
              <div className="relative flex justify-center text-xs uppercase">
                <span className="bg-white px-2 text-gray-500">또는</span>
              </div>
            </div>

            <form onSubmit={handleSignup} className="space-y-4">
              {/* 프로필 이미지 선택 */}
              <div className="flex flex-col items-center gap-3">
                <Avatar className="w-24 h-24">
                  <AvatarImage src={signupProfileImage} alt="프로필 사진" />
                  <AvatarFallback>
                    <User className="w-12 h-12 text-gray-400" />
                  </AvatarFallback>
                </Avatar>
                <Button
                  type="button"
                  variant="outline"
                  size="sm"
                  onClick={() => document.getElementById("signup-profile-image")?.click()}
                >
                  <Camera className="w-4 h-4 mr-2" />
                  프로필 사진 선택 (선택사항)
                </Button>
                <input
                  id="signup-profile-image"
                  type="file"
                  accept="image/*"
                  className="hidden"
                  onChange={handleImageSelect}
                />
                <p className="text-xs text-gray-500">선택하지 않으면 기본 이미지가 설정됩니다</p>
              </div>

              <div className="space-y-2">
                <Label htmlFor="signup-name">이름 *</Label>
                <div className="relative">
                  <User className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                  <Input
                    id="signup-name"
                    type="text"
                    placeholder="홍길동"
                    className="pl-10"
                    value={signupName}
                    onChange={(e) => setSignupName(e.target.value)}
                    required
                  />
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="signup-email">이메일 *</Label>
                <div className="relative">
                  <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                  <Input
                    id="signup-email"
                    type="email"
                    placeholder="email@example.com"
                    className="pl-10"
                    value={signupEmail}
                    onChange={(e) => setSignupEmail(e.target.value)}
                    required
                  />
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="signup-phone">전화번호 *</Label>
                <div className="relative">
                  <Phone className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                  <Input
                    id="signup-phone"
                    type="tel"
                    placeholder="010-1234-5678"
                    className="pl-10"
                    value={signupPhone}
                    onChange={(e) => setSignupPhone(e.target.value)}
                    required
                  />
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="signup-password">비밀번호 *</Label>
                <div className="relative">
                  <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                  <Input
                    id="signup-password"
                    type="password"
                    placeholder="••••••••"
                    className="pl-10"
                    value={signupPassword}
                    onChange={(e) => setSignupPassword(e.target.value)}
                    required
                    minLength={6}
                  />
                </div>
                <p className="text-xs text-gray-500">6자 이상 입력해주세요</p>
              </div>

              <div className="space-y-2">
                <Label htmlFor="signup-confirm-password">비밀번호 확인 *</Label>
                <div className="relative">
                  <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                  <Input
                    id="signup-confirm-password"
                    type="password"
                    placeholder="••••••••"
                    className="pl-10"
                    value={signupConfirmPassword}
                    onChange={(e) => setSignupConfirmPassword(e.target.value)}
                    required
                    minLength={6}
                  />
                </div>
              </div>

              <Button type="submit" className="w-full bg-orange-500 hover:bg-orange-600">
                회원가입
              </Button>
            </form>

            <p className="text-xs text-center text-gray-500">
              회원가입 시 서비스 이용약관 및 개인정보 처리방침에 동의하게 됩니다.
            </p>
          </TabsContent>
        </Tabs>
      </DialogContent>
    </Dialog>
  );
}