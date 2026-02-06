import { X } from "lucide-react";
import { useState } from "react";
import { Button } from "./ui/button";

interface AdBannerProps {
  position?: "sidebar" | "inline" | "footer";
}

export function AdBanner({ position = "inline" }: AdBannerProps) {
  const [isVisible, setIsVisible] = useState(true);

  if (!isVisible) return null;

  // 사이드바 스타일 배너
  if (position === "sidebar") {
    return (
      <div className="sticky top-24 bg-gradient-to-br from-orange-50 to-red-50 rounded-lg p-4 border border-orange-200 shadow-sm">
        <button
          onClick={() => setIsVisible(false)}
          className="absolute top-2 right-2 text-gray-400 hover:text-gray-600 transition-colors"
          aria-label="광고 닫기"
        >
          <X className="w-4 h-4" />
        </button>
        <div className="text-center">
          <p className="text-xs text-gray-500 mb-2">SPONSORED</p>
          <img
            src="https://images.unsplash.com/photo-1556911220-bff31c812dba?w=300&h=200&fit=crop"
            alt="광고"
            className="w-full h-32 object-cover rounded mb-3"
          />
          <h3 className="font-semibold text-sm mb-1">프리미엄 주방용품</h3>
          <p className="text-xs text-gray-600 mb-3">요리를 더 즐겁게 만드는 최고의 도구</p>
          <Button size="sm" className="w-full bg-orange-500 hover:bg-orange-600 text-xs">
            자세히 보기
          </Button>
        </div>
      </div>
    );
  }

  // 인라인 스타일 배너 (레시피 목록 사이)
  if (position === "inline") {
    return (
      <div className="relative bg-gradient-to-r from-orange-50 via-yellow-50 to-orange-50 rounded-lg p-6 border border-orange-200 shadow-sm hover:shadow-md transition-shadow">
        <button
          onClick={() => setIsVisible(false)}
          className="absolute top-2 right-2 text-gray-400 hover:text-gray-600 transition-colors z-10"
          aria-label="광고 닫기"
        >
          <X className="w-4 h-4" />
        </button>
        <div className="flex items-center gap-4">
          <img
            src="https://images.unsplash.com/photo-1556911220-bff31c812dba?w=150&h=150&fit=crop"
            alt="광고"
            className="w-24 h-24 object-cover rounded-lg hidden sm:block"
          />
          <div className="flex-1">
            <p className="text-xs text-gray-500 mb-1">SPONSORED</p>
            <h3 className="font-semibold mb-1">신선한 식재료를 집으로</h3>
            <p className="text-sm text-gray-600 mb-3">프리미엄 식재료 정기배송 서비스 - 첫 구매 20% 할인</p>
            <Button size="sm" className="bg-orange-500 hover:bg-orange-600 text-sm">
              할인받기 →
            </Button>
          </div>
        </div>
      </div>
    );
  }

  // 푸터 스타일 배너
  if (position === "footer") {
    return (
      <div className="relative bg-gradient-to-r from-orange-500 to-red-500 text-white py-8 px-6 rounded-lg shadow-lg">
        <button
          onClick={() => setIsVisible(false)}
          className="absolute top-3 right-3 text-white/80 hover:text-white transition-colors"
          aria-label="광고 닫기"
        >
          <X className="w-5 h-5" />
        </button>
        <div className="max-w-4xl mx-auto text-center">
          <p className="text-xs text-white/80 mb-2">SPONSORED</p>
          <h3 className="text-2xl font-bold mb-2">요리 클래스 특별 할인</h3>
          <p className="text-white/90 mb-4">
            전문 셰프와 함께하는 온라인 요리 강좌 - 지금 가입하면 첫 달 무료!
          </p>
          <Button 
            size="lg" 
            className="bg-white text-orange-600 hover:bg-gray-100 font-semibold"
          >
            무료로 시작하기
          </Button>
        </div>
      </div>
    );
  }

  return null;
}
