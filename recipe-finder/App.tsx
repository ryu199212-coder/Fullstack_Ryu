import React, { useState, useEffect } from "react";
import { RecipeCard, Recipe } from "./components/RecipeCard";
import { RecipeDetail } from "./components/RecipeDetail";
import { LoginDialog } from "./components/LoginDialog";
import { UserMenu } from "./components/UserMenu";
import { FavoritesDialog } from "./components/FavoritesDialog";
import { RecipeFinder } from "./components/RecipeFinder";
import { CreateRecipeDialog } from "./components/CreateRecipeDialog";
import { UserRecipesDialog } from "./components/UserRecipesDialog";
import { MyPageView } from "./components/MyPageView";
import { IngredientManagement } from "./components/IngredientManagement";
import { AdBanner } from "./components/AdBanner";
import { TopAuthors } from "./components/TopAuthors";
import { SubscriptionDialog } from "./components/SubscriptionDialog";
import { Input } from "./components/ui/input";
import { Tabs, TabsList, TabsTrigger } from "./components/ui/tabs";
import { Button } from "./components/ui/button";
import { Search, UtensilsCrossed, LogIn, Sparkles } from "lucide-react";
import { Toaster } from "./components/ui/sonner";
import { toast } from "sonner";
import { authService } from "./services/authService";
import { favoriteService } from "./services/favoriteService";
import { userRecipeService, recipeService } from "./services/recipeService";
import { userService } from "./services/userService";

interface User {
  id: string;
  name: string;
  email: string;
  phone: string;
  profileImage?: string;
  subscription?: 'none' | 'monthly' | 'yearly';
}

// 초기 데이터 (서버에 레시피가 없을 때 사용할 샘플 데이터)
const sampleRecipes: Recipe[] = [
  {
    id: 1,
    title: "비빔밥",
    category: "한식",
    image: "https://images.unsplash.com/photo-1590301157890-4810ed352733?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrb3JlYW4lMjBmb29kJTIwYmliaW1iYXB8ZW58MXx8fHwxNzYzNDA4NTU0fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "30분",
    difficulty: "보통",
    servings: 2,
    description: "다양한 나물과 고추장을 비벼 먹는 한국의 대표적인 건강식",
    views: 1245,
    likes: 85,
    author: "한식요리사",
    isPremium: true,
    ingredients: ["밥 2공기", "시금치 100g", "콩나물 100g", "당근 1/2개"],
    instructions: ["시금치, 콩나물, 고사리는 각각 데쳐서 물기를 짜고 참기름과 소금으로 무쳐주세요."]
  },
  {
    id: 2,
    title: "까르보나라",
    category: "양식",
    image: "https://images.unsplash.com/photo-1655662844229-d2c2a81f09ec?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxpdGFsaWFuJTIwcGFzdGElMjBjYXJib25hcmF8ZW58MXx8fHwxNzYzMzc0Njg4fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "20분",
    difficulty: "보통",
    servings: 2,
    description: "크리미한 소스와 베이컨의 조화가 일품인 이탈리아 파스타",
    views: 983,
    likes: 65,
    author: "이탈리아셰프",
    ingredients: ["스파게티 면 200g", "베이컨 100g", "달걀 노른자 2개"],
    instructions: ["끓는 물에 소금을 넣고 스파게티 면을 삶아주세요."]
  },
  {
    id: 3,
    title: "스시 롤",
    category: "일식",
    image: "https://images.unsplash.com/photo-1717988732486-285ea23a6f88?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxqYXBhbmVzZSUyMHN1c2hpfGVufDF8fHx8MTc2MzQzNTg4Nnww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "40분",
    difficulty: "어려움",
    servings: 3,
    description: "신선한 재료로 만드는 건강한 일본식 김밥",
    views: 756,
    likes: 42,
    author: "스시마스터",
    ingredients: ["스시용 밥 2컵", "김 5장", "연어 회 150g"],
    instructions: ["밥을 지어 스시 식초를 넣고 부채질하며 식혀주세요."]
  },
  {
    id: 4,
    title: "볶음밥",
    category: "중식",
    image: "https://images.unsplash.com/photo-1630914441929-0d8ea69f95e6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjaGluZXNlJTIwZnJpZWQlMjByaWNlfGVufDF8fHx8MTc2MzM1MTUzNHww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "15분",
    difficulty: "쉬움",
    servings: 2,
    description: "간단하지만 맛있는 중식 볶음밥",
    views: 2103,
    likes: 98,
    author: "중식대가",
    ingredients: ["밥 2공기", "달걀 2개", "양파 1/2개"],
    instructions: ["모든 재료를 잘게 다져주세요."]
  },
  {
    id: 5,
    title: "초콜릿 케이크",
    category: "디저트",
    image: "https://images.unsplash.com/photo-1607257882338-70f7dd2ae344?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkZXNzZXJ0JTIwY2hvY29sYXRlJTIwY2FrZXxlbnwxfHx8fDE3NjM0MDM4ODN8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "60분",
    difficulty: "보통",
    servings: 8,
    description: "진한 초콜릿 맛이 일품인 수제 케이크",
    views: 1500,
    likes: 78,
    author: "케이크마스터",
    ingredients: ["박력분 200g", "코코아 파우더 50g", "설탕 150g"],
    instructions: ["버터와 설탕을 흰색이 될 때까지 휘핑해주세요."]
  },
  {
    id: 6,
    title: "불고기",
    category: "한식",
    image: "https://images.unsplash.com/photo-1584278858536-52532423b9ea?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrb3JlYW4lMjBidWxnb2dpfGVufDF8fHx8MTc2MzQ0NDUwNXww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "40분",
    difficulty: "쉬움",
    servings: 4,
    description: "달콤하고 짭짤한 양념이 일품인 한국식 소고기 요리",
    views: 1800,
    likes: 92,
    author: "한식요리사",
    ingredients: ["소고기(불고기용) 500g", "양파 1개", "대파 2대"],
    instructions: ["소고기는 키친타월로 핏물을 제거해주세요."]
  },
  {
    id: 7,
    title: "크루아상",
    category: "디저트",
    image: "https://images.unsplash.com/photo-1705856987588-1e6ae0b6061f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmVuY2glMjBjcm9pc3NhbnR8ZW58MXx8fHwxNzYzNDQ0NTA1fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "180분",
    difficulty: "어려움",
    servings: 8,
    description: "겹겹이 쌓인 버터 층이 매력적인 프랑스식 페이스트리",
    views: 1000,
    likes: 55,
    author: "프랑스요리사",
    ingredients: ["강력분 250g", "우유 120ml", "설탕 30g"],
    instructions: ["강력분, 설탕, 소금, 이스트, 우유를 섞어 반죽을 만드세요."]
  },
  {
    id: 8,
    title: "타코",
    category: "양식",
    image: "https://images.unsplash.com/photo-1552332386-f8dd00dc2f85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtZXhpY2FuJTIwdGFjb3N8ZW58MXx8fHwxNzYzMzkxNzg1fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "25분",
    difficulty: "쉬움",
    servings: 4,
    description: "신선한 재료와 매콤한 양념이 어우러진 멕시칸 요리",
    views: 1100,
    likes: 60,
    author: "멕시코셰프",
    ingredients: ["또띠야 8장", "소고기 다짐육 300g", "양상추 100g"],
    instructions: ["양파를 다져서 올리브유에 볶아주세요."]
  },
  {
    id: 9,
    title: "커리",
    category: "양식",
    image: "https://images.unsplash.com/photo-1710091691802-7dedb8af9a77?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxpbmRpYW4lMjBjdXJyeXxlbnwxfHx8fDE3NjM0NDQ1MDd8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "45분",
    difficulty: "보통",
    servings: 4,
    description: "향신료가 풍부한 인도식 카레",
    views: 1300,
    likes: 70,
    author: "인도셰프",
    ingredients: ["닭고기 500g", "양파 2개", "감자 2개"],
    instructions: ["닭고기는 한입 크기로, 채소는 먹기 좋은 크기로 썰어주세요."]
  },
  {
    id: 10,
    title: "샐러드 볼",
    category: "건강식",
    image: "https://images.unsplash.com/photo-1649531794884-b8bb1de72e68?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzYWxhZCUyMGJvd2wlMjBoZWFsdGh5fGVufDF8fHx8MTc2MzQ0NDUwN3ww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "15분",
    difficulty: "쉬움",
    servings: 2,
    description: "신선한 채소와 단백질이 가득한 건강한 한 끼",
    views: 1400,
    likes: 75,
    author: "건강요리사",
    ingredients: ["양상추 100g", "방울토마토 10개", "오이 1개"],
    instructions: ["퀴노아는 삶아서 식혀주세요."]
  },
  {
    id: 11,
    title: "마르게리타 피자",
    category: "양식",
    image: "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwaXp6YSUyMG1hcmdoZXJpdGF8ZW58MXx8fHwxNzYzNDM1MTY3fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "90분",
    difficulty: "보통",
    servings: 2,
    description: "심플하지만 맛있는 이탈리아 정통 피자",
    views: 1600,
    likes: 88,
    author: "이탈리아셰프",
    ingredients: ["강력분 300g", "물 180ml", "이스트 5g"],
    instructions: ["강력분, 물, 이스트, 설탕, 소금, 올리브유를 섞어 반죽하세요."]
  },
  {
    id: 12,
    title: "라멘",
    category: "일식",
    image: "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyYW1lbiUyMG5vb2RsZXN8ZW58MXx8fHwxNzYzMzg4NzEzfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "120분",
    difficulty: "어려움",
    servings: 2,
    description: "깊은 육수 맛이 일품인 일본식 라멘",
    views: 1700,
    likes: 95,
    author: "일식요리사",
    ingredients: ["라멘 면 2인분", "돼지 등뼈 500g", "대파 2대"],
    instructions: ["돼지 등뼈를 끓는 물에 데쳐 핏물을 제거하세요."]
  },
  {
    id: 13,
    title: "김치찌개",
    category: "한식",
    image: "https://images.unsplash.com/photo-1760228865341-675704c22a5b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrb3JlYW4lMjBmb29kJTIwa2ltY2hpJTIwc3Rld3xlbnwxfHx8fDE3NjkzOTczODN8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "35분",
    difficulty: "쉬움",
    servings: 3,
    description: "칼칼한 맛이 일품인 한국의 국민 찌개",
    views: 2200,
    likes: 110,
    author: "한식요리사",
    isPremium: true,
    ingredients: ["김치 200g", "돼지고기 150g", "두부 1/2모"],
    instructions: ["김치를 먹기 좋은 크기로 썰어주세요."]
  },
  {
    id: 14,
    title: "치킨",
    category: "한식",
    image: "https://images.unsplash.com/photo-1687966699414-095ca9c35593?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrb3JlYW4lMjBmcmllZCUyMGNoaWNrZW58ZW58MXx8fHwxNzY5MzA1MDUwfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "50분",
    difficulty: "보통",
    servings: 3,
    description: "바삭한 겉과 부드러운 속이 매력적인 프라이드 치킨",
    views: 2800,
    likes: 145,
    author: "치킨마스터",
    ingredients: ["닭 1마리", "튀김가루 200g", "식용유 500ml"],
    instructions: ["닭을 깨끗이 씻어 물기를 제거하세요."]
  },
  {
    id: 15,
    title: "해물 파스타",
    category: "양식",
    image: "https://images.unsplash.com/photo-1632778129004-f142ce499b3e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwYXN0YSUyMHNlYWZvb2R8ZW58MXx8fHwxNzY5Mzk3Mzg0fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "25분",
    difficulty: "보통",
    servings: 2,
    description: "신선한 해산물이 가득한 이탈리아 파스타",
    views: 1550,
    likes: 82,
    author: "이탈리아셰프",
    isPremium: true,
    ingredients: ["스파게티 면 200g", "새우 10마리", "조개 100g"],
    instructions: ["스파게티 면을 알덴테로 삶아주세요."]
  },
  {
    id: 16,
    title: "스테이크",
    category: "양식",
    image: "https://images.unsplash.com/photo-1706650616334-97875fae8521?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzdGVhayUyMGRpbm5lcnxlbnwxfHx8fDE3NjkzNTYxNzl8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "20분",
    difficulty: "보통",
    servings: 1,
    description: "육즙이 풍부한 완벽한 미디움 스테이크",
    views: 1950,
    likes: 105,
    author: "스테이크셰프",
    ingredients: ["소고기(등심) 250g", "마늘 3쪽", "로즈마리"],
    instructions: ["고기를 실온에 30분간 두세요."]
  },
  {
    id: 17,
    title: "덴뿌라",
    category: "일식",
    image: "https://images.unsplash.com/photo-1666599207746-0868c6a556d2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxqYXBhbmVzZSUyMHRlbXB1cmF8ZW58MXx8fHwxNzY5Mzk3Mzg1fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "30분",
    difficulty: "보통",
    servings: 2,
    description: "바삭한 튀김옷이 매력적인 일본식 튀김",
    views: 1320,
    likes: 68,
    author: "일식요리사",
    ingredients: ["새우 8마리", "가지 1개", "호박 1/2개"],
    instructions: ["채소를 먹기 좋은 크기로 썰어주세요."]
  },
  {
    id: 18,
    title: "만두",
    category: "중식",
    image: "https://images.unsplash.com/photo-1570604127008-f644337cfb8b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjaGluZXNlJTIwZHVtcGxpbmdzfGVufDF8fHx8MTc2OTM5MDYwNHww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "60분",
    difficulty: "어려움",
    servings: 4,
    description: "육즙이 가득한 수제 만두",
    views: 1880,
    likes: 95,
    author: "중식대가",
    ingredients: ["만두피 30장", "돼지고기 300g", "부추 100g"],
    instructions: ["돼지고기와 부추를 잘게 다져주세요."]
  },
  {
    id: 19,
    title: "티라미수",
    category: "디저트",
    image: "https://images.unsplash.com/photo-1631206753348-db44968fd440?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxkZXNzZXJ0JTIwdGlyYW1pc3V8ZW58MXx8fHwxNzY5Mzk3Mzg2fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "40분",
    difficulty: "보통",
    servings: 6,
    description: "이탈리아의 대표 디저트",
    views: 1650,
    likes: 89,
    author: "디저트마스터",
    ingredients: ["마스카포네 치즈 250g", "에스프레소 200ml"],
    instructions: ["에스프레소를 진하게 내려주세요."]
  },
  {
    id: 20,
    title: "부다 볼",
    category: "건강식",
    image: "https://images.unsplash.com/photo-1589442305595-62647c1514f9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxoZWFsdGh5JTIwYnVkZGhhJTIwYm93bHxlbnwxfHx8fDE3NjkzOTczODZ8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "20분",
    difficulty: "쉬움",
    servings: 2,
    description: "영양소가 가득한 건강 한 그릇",
    views: 1720,
    likes: 92,
    author: "건강요리사",
    ingredients: ["현미 1컵", "병아리콩 100g", "아보카도 1개"],
    instructions: ["현미를 지어 식혀주세요."]
  },
  {
    id: 21,
    title: "돌솥비빔밥",
    category: "한식",
    image: "https://images.unsplash.com/photo-1741295017668-c8132acd6fc0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrb3JlYW4lMjBiaWJpbWJhcCUyMGJvd2x8ZW58MXx8fHwxNzY5MzI1ODQ2fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "35분",
    difficulty: "보통",
    servings: 2,
    description: "누룽지가 맛있는 돌솥 비빔밥",
    views: 1840,
    likes: 97,
    author: "한식요리사",
    ingredients: ["밥 2공기", "나물 각종", "고추장 2큰술"],
    instructions: ["돌솥을 충분히 달구어주세요."]
  },
  {
    id: 22,
    title: "리조또",
    category: "양식",
    image: "https://images.unsplash.com/photo-1581073746562-e7fd2422f0eb?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxpdGFsaWFuJTIwcmlzb3R0b3xlbnwxfHx8fDE3NjkzOTczODZ8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "40분",
    difficulty: "보통",
    servings: 2,
    description: "크리미한 이탈리아 쌀 요리",
    views: 1470,
    likes: 76,
    author: "이탈리아셰프",
    ingredients: ["아르보리오 쌀 200g", "육수 500ml"],
    instructions: ["쌀을 버터에 볶아주세요."]
  },
  {
    id: 23,
    title: "돈까스 라멘",
    category: "일식",
    image: "https://images.unsplash.com/photo-1697652974652-a2336106043b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxqYXBhbmVzZSUyMHJhbWVuJTIwYm93bHxlbnwxfHx8fDE3NjkzMzE3ODJ8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "50분",
    difficulty: "보통",
    servings: 2,
    description: "돈까스와 라멘의 환상 조합",
    views: 1990,
    likes: 108,
    author: "일식요리사",
    isPremium: true,
    ingredients: ["라멘 면 2인분", "돼지고기 등심 200g"],
    instructions: ["돈까스를 튀겨주세요."]
  },
  {
    id: 24,
    title: "마파두부",
    category: "중식",
    image: "https://images.unsplash.com/photo-1769065647078-f067eb768035?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjaGluZXNlJTIwbWFwbyUyMHRvZnV8ZW58MXx8fHwxNzY5MzMxODYwfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "25분",
    difficulty: "쉬움",
    servings: 3,
    description: "매콤하고 얼얼한 사천식 두부 요리",
    views: 1680,
    likes: 85,
    author: "중식대가",
    ingredients: ["두부 1모", "돼지고기 다짐육 100g"],
    instructions: ["두부를 깍둑썰기 하세요."]
  },
  {
    id: 25,
    title: "마카롱",
    category: "디저트",
    image: "https://images.unsplash.com/photo-1633997454158-71c87e49cd31?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxmcmVuY2glMjBtYWNhcm9ufGVufDF8fHx8MTc2OTM5NzM4OHww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "90분",
    difficulty: "어려움",
    servings: 20,
    description: "프랑스의 대표 디저트 마카롱",
    views: 2100,
    likes: 115,
    author: "디저트마스터",
    ingredients: ["아몬드 가루 100g", "슈가 파우더 100g"],
    instructions: ["아몬드 가루와 슈가 파우더를 체에 쳐주세요."]
  },
  {
    id: 26,
    title: "스무디 볼",
    category: "건강식",
    image: "https://images.unsplash.com/photo-1592503469196-3a7880cc2d05?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxoZWFsdGh5JTIwc21vb3RoaWUlMjBib3dsfGVufDF8fHx8MTc2OTM5NzM4OHww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "10분",
    difficulty: "쉬움",
    servings: 1,
    description: "신선한 과일로 만드는 건강한 아침식사",
    views: 1580,
    likes: 81,
    author: "건강요리사",
    ingredients: ["바나나 2개", "베리류 100g", "요거트 200ml"],
    instructions: ["모든 재료를 믹서에 넣고 갈아주세요."]
  },
  {
    id: 27,
    title: "떡볶이",
    category: "한식",
    image: "https://images.unsplash.com/photo-1679581083578-94eae6e8d7a4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxrb3JlYW4lMjB0dGVva2Jva2tpfGVufDF8fHx8MTc2OTM3MDk0MXww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "20분",
    difficulty: "쉬움",
    servings: 2,
    description: "매콤달콤한 한국의 대표 간식",
    views: 2400,
    likes: 128,
    author: "한식요리사",
    ingredients: ["떡 300g", "어묵 100g", "고추장 2큰술"],
    instructions: ["떡을 물에 불려주세요."]
  },
  {
    id: 28,
    title: "부리또",
    category: "양식",
    image: "https://images.unsplash.com/photo-1622620283268-5cf46da1df39?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxtZXhpY2FuJTIwYnVycml0b3xlbnwxfHx8fDE3NjkzOTczODl8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "30분",
    difficulty: "쉬움",
    servings: 2,
    description: "푸짐한 멕시칸 부리또",
    views: 1620,
    likes: 84,
    author: "멕시코셰프",
    ingredients: ["또띠야 4장", "소고기 200g", "콩 100g"],
    instructions: ["소고기를 양념하여 볶아주세요."]
  },
  {
    id: 29,
    title: "연어 데리야끼",
    category: "일식",
    image: "https://images.unsplash.com/photo-1684815595429-cf46bff6294f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzYWxtb24lMjB0ZXJpeWFraXxlbnwxfHx8fDE3NjkzNDcxNDJ8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "25분",
    difficulty: "보통",
    servings: 2,
    description: "달콤짭짤한 데리야끼 소스를 곁들인 연어 구이",
    views: 1780,
    likes: 93,
    author: "일식요리사",
    ingredients: ["연어 2조각", "간장 3큰술", "미림 2큰술"],
    instructions: ["연어에 소금과 후추로 밑간하세요."]
  },
  {
    id: 30,
    title: "쌀국수",
    category: "기타",
    image: "https://images.unsplash.com/photo-1701480253822-1842236c9a97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwaG8lMjB2aWV0bmFtZXNlJTIwc291cHxlbnwxfHx8fDE3NjkzOTczODl8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    cookTime: "90분",
    difficulty: "보통",
    servings: 4,
    description: "깊은 맛의 베트남 쌀국수",
    views: 1920,
    likes: 102,
    author: "베트남셰프",
    ingredients: ["쌀국수 면 400g", "소뼈 500g", "숙주 100g"],
    instructions: ["소뼈를 물에 담가 핏물을 빼주세요."]
  }
];

export default function App() {
  const [user, setUser] = useState<User | null>(null);
  const [selectedRecipe, setSelectedRecipe] = useState<Recipe | null>(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("전체");
  const [loginDialogOpen, setLoginDialogOpen] = useState(false);
  const [favoritesDialogOpen, setFavoritesDialogOpen] = useState(false);
  const [recipeFinderOpen, setRecipeFinderOpen] = useState(false);
  const [createRecipeOpen, setCreateRecipeOpen] = useState(false);
  const [userRecipesOpen, setUserRecipesOpen] = useState(false);
  const [myPageOpen, setMyPageOpen] = useState(false);
  const [favoriteIds, setFavoriteIds] = useState<number[]>([]);
  const [userRecipes, setUserRecipes] = useState<Recipe[]>([]);
  const [editingRecipe, setEditingRecipe] = useState<Recipe | null>(null);
  const [showUserRecipes, setShowUserRecipes] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const RECIPES_PER_PAGE = 8;
  const FIRST_PAGE_COUNT = 16; // 첫 8개 + 광고 + 다음 8개
  const [ingredientManagementOpen, setIngredientManagementOpen] = useState(false);
  const [subscriptionDialogOpen, setSubscriptionDialogOpen] = useState(false);
  const [recipes, setRecipes] = useState<Recipe[]>(sampleRecipes);

  const categories = ["전체", "한식", "양식", "중식", "일식", "디저트", "건강식", "기타"];
  
  // 관리자 이메일 체크
  const isAdmin = user?.email === "admin@recipe.com";

  // Load user, favorites, and user recipes from localStorage on mount
  useEffect(() => {
    const savedUser = localStorage.getItem("user");
    const savedFavorites = localStorage.getItem("favorites");
    const savedUserRecipes = localStorage.getItem("userRecipes");
    
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }
    if (savedFavorites) {
      setFavoriteIds(JSON.parse(savedFavorites));
    }
    if (savedUserRecipes) {
      setUserRecipes(JSON.parse(savedUserRecipes));
    }
  }, []);

  const handleLogin = (email: string, password: string) => {
    const mockUser: User = {
      id: Date.now().toString(),
      name: email.split("@")[0],
      email: email,
      phone: ""
    };
    
    setUser(mockUser);
    localStorage.setItem("user", JSON.stringify(mockUser));
    setLoginDialogOpen(false);
    toast.success(`환영합니다, ${mockUser.name}님!`);
  };

  const handleSignup = (name: string, email: string, password: string, phone: string, profileImage?: string) => {
    const mockUser: User = {
      id: Date.now().toString(),
      name: name,
      email: email,
      phone: phone,
      profileImage: profileImage
    };
    
    setUser(mockUser);
    localStorage.setItem("user", JSON.stringify(mockUser));
    setLoginDialogOpen(false);
    toast.success(`회원가입이 완료되었습니다! 환영합니다, ${mockUser.name}님!`);
  };

  const handleSocialLogin = async (provider: 'google' | 'naver' | 'kakao') => {
    try {
      toast.info(`${provider === 'google' ? '구글' : provider === 'naver' ? '네이버' : '카카오'} 로그인 중...`);
      
      const result = await authService.socialLogin(provider, 'mock-code');
      
      const socialUser: User = {
        id: result.user.id,
        name: result.user.name,
        email: result.user.email,
        phone: '',
        profileImage: result.user.profileImage
      };
      
      setUser(socialUser);
      localStorage.setItem("user", JSON.stringify(socialUser));
      setLoginDialogOpen(false);
      toast.success(`${provider === 'google' ? '구글' : provider === 'naver' ? '네이버' : '카카오'} 로그인 성공! 환영합니다, ${socialUser.name}님!`);
    } catch (error) {
      toast.error("소셜 로그인에 실패했습니다. 다시 시도해주세요.");
      console.error("Social login error:", error);
    }
  };

  const handleLogout = () => {
    setUser(null);
    localStorage.removeItem("user");
    toast.info("로그아웃되었습니다.");
  };

  const handleAccountDelete = () => {
    setUser(null);
    setUserRecipes([]);
    setFavoriteIds([]);
    localStorage.removeItem("user");
    localStorage.removeItem("userRecipes");
    localStorage.removeItem("favorites");
    setMyPageOpen(false);
    toast.success("계정이 삭제되었습니다.");
  };

  const handleCreateRecipe = (recipeData: Omit<Recipe, "id">) => {
    const newRecipe: Recipe = {
      ...recipeData,
      id: Date.now(),
      userId: user?.id,
      author: user?.name || '익명',
      views: 0,
      createdAt: new Date().toISOString(),
    };
    
    const updatedUserRecipes = [...userRecipes, newRecipe];
    setUserRecipes(updatedUserRecipes);
    localStorage.setItem("userRecipes", JSON.stringify(updatedUserRecipes));
  };

  const handleEditRecipe = (recipeData: Omit<Recipe, "id">) => {
    if (!editingRecipe) return;
    
    const updatedRecipes = userRecipes.map((recipe) =>
      recipe.id === editingRecipe.id
        ? { ...recipeData, id: editingRecipe.id, userId: recipe.userId, author: recipe.author, views: recipe.views }
        : recipe
    );
    
    setUserRecipes(updatedRecipes);
    localStorage.setItem("userRecipes", JSON.stringify(updatedRecipes));
    setEditingRecipe(null);
  };

  const handleDeleteRecipe = (recipeId: number) => {
    const updatedRecipes = userRecipes.filter((recipe) => recipe.id !== recipeId);
    setUserRecipes(updatedRecipes);
    localStorage.setItem("userRecipes", JSON.stringify(updatedRecipes));
    toast.success("레시피가 삭제되었습니다.");
  };

  const handleRemoveFavorite = (recipeId: number) => {
    const newFavorites = favoriteIds.filter((id) => id !== recipeId);
    setFavoriteIds(newFavorites);
    localStorage.setItem("favorites", JSON.stringify(newFavorites));
    toast.info("즐겨찾기에서 제거되었습니다.");
  };

  const handleToggleFavorite = (recipeId: number, e: React.MouseEvent) => {
    e.stopPropagation();
    
    if (!user) {
      toast.error("로그인이 필요한 기능입니다.", {
        action: {
          label: "로그인",
          onClick: () => setLoginDialogOpen(true),
        },
      });
      return;
    }

    const newFavorites = favoriteIds.includes(recipeId)
      ? favoriteIds.filter((id) => id !== recipeId)
      : [...favoriteIds, recipeId];
    
    setFavoriteIds(newFavorites);
    localStorage.setItem("favorites", JSON.stringify(newFavorites));
    
    if (newFavorites.includes(recipeId)) {
      toast.success("즐겨찾기에 추가되었습니다!");
    } else {
      toast.info("즐겨찾기에서 제거되었습니다.");
    }
  };

  const favoriteRecipes = [...recipes, ...userRecipes].filter((recipe) => 
    favoriteIds.includes(recipe.id)
  );

  const allRecipes = showUserRecipes 
    ? userRecipes 
    : [...recipes, ...userRecipes];

  const filteredRecipes = allRecipes.filter((recipe) => {
    const matchesSearch = recipe.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
                         recipe.description.toLowerCase().includes(searchQuery.toLowerCase()) ||
                         recipe.ingredients.some(ingredient => 
                           ingredient.toLowerCase().includes(searchQuery.toLowerCase())
                         );
    const matchesCategory = selectedCategory === "전체" || recipe.category === selectedCategory;
    return matchesSearch && matchesCategory;
  });

  // 페이징 로직: 첫 페이지는 16개(8+광고+8), 이후 페이지는 8개씩
  const getDisplayedRecipes = () => {
    if (currentPage === 1) {
      return filteredRecipes.slice(0, FIRST_PAGE_COUNT);
    } else {
      const startIndex = FIRST_PAGE_COUNT + (currentPage - 2) * RECIPES_PER_PAGE;
      return filteredRecipes.slice(0, startIndex + RECIPES_PER_PAGE);
    }
  };
  
  const displayedRecipes = getDisplayedRecipes();
  const hasMoreRecipes = filteredRecipes.length > displayedRecipes.length;

  return (
    <div className="min-h-screen bg-gradient-to-b from-orange-50 to-white">
      <Toaster position="top-center" richColors />
      
      {/* 마이페이지가 열려있으면 마이페이지만 표시 */}
      {myPageOpen && user ? (
        <MyPageView
          userId={user.id}
          onBack={() => setMyPageOpen(false)}
          userRecipes={userRecipes}
          favoriteRecipes={favoriteRecipes}
          onDeleteRecipe={handleDeleteRecipe}
          onEditRecipe={(recipe) => {
            setEditingRecipe(recipe);
            setCreateRecipeOpen(true);
          }}
          onRemoveFavorite={handleRemoveFavorite}
          onRecipeClick={(recipe) => {
            setSelectedRecipe(recipe);
          }}
          onDeleteAccount={handleAccountDelete}
        />
      ) : (
        <>
      {/* Header */}
      <header className="bg-white shadow-sm sticky top-0 z-10">
        <div className="container mx-auto px-4 py-6">
          <div className="flex items-center justify-between mb-6">
            <div className="flex items-center gap-3">
              <UtensilsCrossed className="w-8 h-8 text-orange-500" />
              <h1 className="text-orange-500">오늘 뭐 먹지?</h1>
            </div>
            
            {/* User Menu / Login Button */}
            {user ? (
              <UserMenu
                user={user}
                onLogout={handleLogout}
                onViewFavorites={() => setFavoritesDialogOpen(true)}
                onViewMyRecipes={() => setUserRecipesOpen(true)}
                onViewMyPage={() => setMyPageOpen(true)}
                onViewIngredients={() => setIngredientManagementOpen(true)}
                isAdmin={isAdmin}
              />
            ) : (
              <Button 
                onClick={() => setLoginDialogOpen(true)}
                className="bg-orange-500 hover:bg-orange-600 text-white"
              >
                <LogIn className="w-4 h-4 mr-2" />
                <span>로그인</span>
              </Button>
            )}
          </div>
          
          {/* Recipe Finder CTA */}
          <div className="mb-6">
            <Button
              onClick={() => setRecipeFinderOpen(true)}
              className="w-full bg-gradient-to-r from-orange-500 to-red-500 hover:from-orange-600 hover:to-red-600 text-white"
              size="lg"
            >
              <Sparkles className="w-5 h-5 mr-2" />
              나에게 딱 맞는 레시피 찾기
            </Button>
          </div>

          {/* Search */}
          <div className="relative mb-6">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
            <Input
              type="text"
              placeholder="레시피를 검색해보세요..."
              className="pl-10"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
          </div>

          {/* Category Tabs */}
          <Tabs value={selectedCategory} onValueChange={setSelectedCategory}>
            <TabsList className="w-full justify-start overflow-x-auto">
              {categories.map((category) => (
                <TabsTrigger key={category} value={category}>
                  {category}
                </TabsTrigger>
              ))}
            </TabsList>
          </Tabs>
        </div>
      </header>

      {/* Main Content */}
      <main className="container mx-auto px-4 py-8">
        {/* 이달의 작성자 TOP20 */}
        <TopAuthors recipes={[...recipes, ...userRecipes]} />

        <div className="mb-6 flex items-center justify-between">
          <p className="text-gray-600">
            {filteredRecipes.length}개의 레시피를 찾았습니다
            {showUserRecipes && " (내가 작성한 레시피)"}
          </p>
          {userRecipes.length > 0 && (
            <Button
              variant="ghost"
              size="sm"
              onClick={() => setShowUserRecipes(!showUserRecipes)}
              className="text-orange-600"
            >
              {showUserRecipes ? "전체 레시피 보기" : "내 레시피만 보기"}
            </Button>
          )}
        </div>

        {/* Recipe Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          {displayedRecipes.map((recipe, index) => (
            <React.Fragment key={recipe.id}>
              <RecipeCard
                recipe={recipe}
                onClick={() => setSelectedRecipe(recipe)}
                isFavorite={favoriteIds.includes(recipe.id)}
                onToggleFavorite={handleToggleFavorite}
              />
              {/* 8번째 레시피 다음에 인라인 광고 배너 추가 (첫 페이지만) */}
              {index === 7 && currentPage === 1 && displayedRecipes.length > 8 && (
                <div className="md:col-span-2 lg:col-span-3 xl:col-span-4">
                  <AdBanner position="inline" />
                </div>
              )}
            </React.Fragment>
          ))}
        </div>

        {filteredRecipes.length === 0 && (
          <div className="text-center py-16">
            <p className="text-gray-500 text-lg">
              검색 결과가 없습니다. 다른 검색어를 입력해보세요.
            </p>
          </div>
        )}

        {/* 더보기 버튼 */}
        {hasMoreRecipes && (
          <div className="mt-8 flex justify-center">
            <Button
              onClick={() => setCurrentPage(currentPage + 1)}
              variant="outline"
              size="lg"
              className="border-orange-500 text-orange-600 hover:bg-orange-50"
            >
              더보기
            </Button>
          </div>
        )}
        
        {/* 처음으로 버튼 */}
        {currentPage > 1 && (
          <div className="mt-4 flex justify-center">
            <Button
              onClick={() => setCurrentPage(1)}
              variant="ghost"
              size="sm"
              className="text-gray-600"
            >
              처음으로
            </Button>
          </div>
        )}
      </main>

      {/* Recipe Detail Modal */}
      <RecipeDetail
        recipe={selectedRecipe}
        open={!!selectedRecipe}
        onOpenChange={(open) => !open && setSelectedRecipe(null)}
        currentUserId={user?.id}
        onEdit={(recipe) => {
          setEditingRecipe(recipe);
          setCreateRecipeOpen(true);
          setSelectedRecipe(null);
        }}
        onDelete={(recipeId) => {
          if (confirm('정말 이 레시피를 삭제하시겠습니까?')) {
            handleDeleteRecipe(recipeId);
            setSelectedRecipe(null);
          }
        }}
      />

      {/* Login Dialog */}
      <LoginDialog
        open={loginDialogOpen}
        onOpenChange={setLoginDialogOpen}
        onLogin={handleLogin}
        onSignup={handleSignup}
        onSocialLogin={handleSocialLogin}
      />

      {/* Favorites Dialog */}
      <FavoritesDialog
        open={favoritesDialogOpen}
        onOpenChange={setFavoritesDialogOpen}
        favorites={favoriteRecipes}
        onRecipeClick={(recipe) => {
          setSelectedRecipe(recipe);
          setFavoritesDialogOpen(false);
        }}
      />

      {/* Recipe Finder Dialog */}
      <RecipeFinder
        open={recipeFinderOpen}
        onOpenChange={setRecipeFinderOpen}
        recipes={[...recipes, ...userRecipes]}
        onRecipeClick={setSelectedRecipe}
        favoriteIds={favoriteIds}
        onToggleFavorite={handleToggleFavorite}
      />

      {/* Create Recipe Dialog */}
      <CreateRecipeDialog
        open={createRecipeOpen}
        onOpenChange={(isOpen) => {
          setCreateRecipeOpen(isOpen);
          if (!isOpen) setEditingRecipe(null);
        }}
        onSave={editingRecipe ? handleEditRecipe : handleCreateRecipe}
        editRecipe={editingRecipe}
      />

      {/* User Recipes Dialog */}
      <UserRecipesDialog
        open={userRecipesOpen}
        onOpenChange={setUserRecipesOpen}
        userRecipes={userRecipes}
        onRecipeClick={(recipe) => {
          setSelectedRecipe(recipe);
          setUserRecipesOpen(false);
        }}
        onEdit={(recipe) => {
          setEditingRecipe(recipe);
          setCreateRecipeOpen(true);
          setUserRecipesOpen(false);
        }}
        onDelete={handleDeleteRecipe}
        favoriteIds={favoriteIds}
        onToggleFavorite={handleToggleFavorite}
      />

      {/* Ingredient Management Dialog */}
      <IngredientManagement
        open={ingredientManagementOpen}
        onOpenChange={setIngredientManagementOpen}
        isAdmin={isAdmin}
      />

      {/* Subscription Dialog */}
      <SubscriptionDialog
        open={subscriptionDialogOpen}
        onOpenChange={setSubscriptionDialogOpen}
        user={user}
        onSubscribe={(subscriptionType) => {
          if (user) {
            const updatedUser: User = {
              ...user,
              subscription: subscriptionType
            };
            setUser(updatedUser);
            localStorage.setItem("user", JSON.stringify(updatedUser));
            toast.success("구독이 완료되었습니다!");
          }
        }}
      />

      {/* Footer */}
      <footer className="bg-gray-50 border-t mt-16">
        <div className="container mx-auto px-4 py-8 text-center text-gray-600">
          <p>© 2025 오늘 뭐 먹지? 모든 레시피를 한 곳에서.</p>
        </div>
      </footer>
        </>
      )}
    </div>
  );
}
