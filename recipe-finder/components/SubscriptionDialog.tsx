import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from "./ui/dialog";
import { Button } from "./ui/button";
import { Check, Crown, Zap, Star } from "lucide-react";
import { Badge } from "./ui/badge";
import { useState } from "react";
import { toast } from "sonner";

interface SubscriptionDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSubscribe: (plan: 'monthly' | 'yearly') => void;
}

export function SubscriptionDialog({ open, onOpenChange, onSubscribe }: SubscriptionDialogProps) {
  const [selectedPlan, setSelectedPlan] = useState<'monthly' | 'yearly'>('monthly');

  const handleSubscribe = () => {
    onSubscribe(selectedPlan);
    onOpenChange(false);
    toast.success(`${selectedPlan === 'monthly' ? '월간' : '연간'} 구독이 완료되었습니다!`);
  };

  const benefits = [
    "프리미엄 레시피 무제한 열람",
    "광고 없는 깔끔한 화면",
    "레시피 북마크 무제한 저장",
    "이달의 작성자 우대 혜택",
    "독점 레시피 콘텐츠 제공",
    "우선 고객 지원",
  ];

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-4xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <div className="flex items-center gap-2">
            <Crown className="w-6 h-6 text-orange-500" />
            <DialogTitle className="text-2xl">프리미엄 멤버십</DialogTitle>
          </div>
          <DialogDescription>
            더 많은 레시피와 혜택을 누리세요
          </DialogDescription>
        </DialogHeader>

        <div className="space-y-6">
          {/* Plan Selection */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {/* Monthly Plan */}
            <div
              onClick={() => setSelectedPlan('monthly')}
              className={`border-2 rounded-lg p-6 cursor-pointer transition-all ${
                selectedPlan === 'monthly'
                  ? 'border-orange-500 bg-orange-50'
                  : 'border-gray-200 hover:border-orange-300'
              }`}
            >
              <div className="flex items-center justify-between mb-4">
                <div>
                  <h3 className="text-xl font-semibold">월간 플랜</h3>
                  <p className="text-gray-600 text-sm">언제든지 해지 가능</p>
                </div>
                {selectedPlan === 'monthly' && (
                  <Badge className="bg-orange-500">선택됨</Badge>
                )}
              </div>
              <div className="mb-4">
                <span className="text-4xl font-bold">₩9,900</span>
                <span className="text-gray-600">/월</span>
              </div>
              <div className="flex items-center gap-2 text-sm text-gray-600">
                <Zap className="w-4 h-4" />
                <span>즉시 시작</span>
              </div>
            </div>

            {/* Yearly Plan */}
            <div
              onClick={() => setSelectedPlan('yearly')}
              className={`border-2 rounded-lg p-6 cursor-pointer transition-all relative ${
                selectedPlan === 'yearly'
                  ? 'border-orange-500 bg-orange-50'
                  : 'border-gray-200 hover:border-orange-300'
              }`}
            >
              <Badge className="absolute -top-3 right-4 bg-gradient-to-r from-orange-500 to-red-500 text-white">
                2개월 무료!
              </Badge>
              <div className="flex items-center justify-between mb-4">
                <div>
                  <h3 className="text-xl font-semibold">연간 플랜</h3>
                  <p className="text-gray-600 text-sm">16% 할인</p>
                </div>
                {selectedPlan === 'yearly' && (
                  <Badge className="bg-orange-500">선택됨</Badge>
                )}
              </div>
              <div className="mb-2">
                <span className="text-4xl font-bold">₩99,000</span>
                <span className="text-gray-600">/년</span>
              </div>
              <div className="flex items-center gap-2 text-sm">
                <span className="line-through text-gray-400">₩118,800</span>
                <span className="text-orange-600 font-semibold">₩19,800 절약</span>
              </div>
            </div>
          </div>

          {/* Benefits */}
          <div className="bg-gray-50 rounded-lg p-6">
            <h3 className="font-semibold mb-4 flex items-center gap-2">
              <Crown className="w-5 h-5 text-orange-500" />
              프리미엄 혜택
            </h3>
            <ul className="grid grid-cols-1 md:grid-cols-2 gap-3">
              {benefits.map((benefit, index) => (
                <li key={index} className="flex items-center gap-2">
                  <Check className="w-5 h-5 text-green-500 flex-shrink-0" />
                  <span className="text-gray-700">{benefit}</span>
                </li>
              ))}
            </ul>
          </div>

          {/* Payment Info */}
          <div className="bg-blue-50 border border-blue-200 rounded-lg p-4">
            <div className="flex items-start gap-2">
              <span className="text-2xl">💳</span>
              <div>
                <h4 className="font-semibold mb-1">안전한 결제</h4>
                <p className="text-sm text-gray-600">
                  카드 정보는 PG사를 통해 안전하게 암호화되어 처리됩니다.
                  언제든지 구독을 해지할 수 있으며, 해지 후에도 결제 기간까지 서비스를 이용할 수 있습니다.
                </p>
              </div>
            </div>
          </div>

          {/* Action Buttons */}
          <div className="flex gap-3">
            <Button
              variant="outline"
              className="flex-1"
              onClick={() => onOpenChange(false)}
            >
              나중에
            </Button>
            <Button
              className="flex-1 bg-gradient-to-r from-orange-500 to-red-500 hover:from-orange-600 hover:to-red-600"
              onClick={handleSubscribe}
            >
              <Crown className="w-4 h-4 mr-2" />
              {selectedPlan === 'monthly' ? '월간 구독 시작' : '연간 구독 시작'}
            </Button>
          </div>

          {/* Terms */}
          <p className="text-xs text-gray-500 text-center">
            구독을 시작하면 <a href="#" className="underline">이용약관</a> 및{' '}
            <a href="#" className="underline">개인정보 처리방침</a>에 동의하는 것으로 간주됩니다.
          </p>
        </div>
      </DialogContent>
    </Dialog>
  );
}