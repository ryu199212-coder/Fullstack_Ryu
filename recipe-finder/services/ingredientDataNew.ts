export interface IngredientInfo {
  name: string;
  category: string;
  calories: number;
  protein: number;
  carbs: number;
  fat: number;
  fiber: number;
  vitamins: string[];
  minerals: string[];
  benefits: string[];
  description: string;
}

const STORAGE_KEY = 'customIngredients';

// 기본 재료 데이터베이스 (간단한 버전)
const defaultIngredients: IngredientInfo[] = [
  {
    name: '토마토',
    category: '채소',
    calories: 18,
    protein: 0.9,
    carbs: 3.9,
    fat: 0.2,
    fiber: 1.2,
    vitamins: ['비타민 C', '비타민 A'],
    minerals: ['칼륨'],
    benefits: ['항산화 효과', '심혈관 건강'],
    description: '새콤달콤한 과채로, 리코펜이 풍부하여 건강에 좋습니다.'
  },
  {
    name: '시금치',
    category: '채소',
    calories: 23,
    protein: 2.9,
    carbs: 3.6,
    fat: 0.4,
    fiber: 2.2,
    vitamins: ['비타민 A', '비타민 C', '비타민 K'],
    minerals: ['철분', '칼슘'],
    benefits: ['빈혈 예방', '뼈 건강'],
    description: '철분과 비타민이 풍부한 녹색 잎채소입니다.'
  },
  {
    name: '닭가슴살',
    category: '육류',
    calories: 165,
    protein: 31,
    carbs: 0,
    fat: 3.6,
    fiber: 0,
    vitamins: ['비타민 B6', '비타민 B12'],
    minerals: ['인', '셀레늄'],
    benefits: ['근육 성장', '체중 관리'],
    description: '고단백 저지방 식품의 대표주자입니다.'
  },
  {
    name: '밥',
    category: '곡물',
    calories: 130,
    protein: 2.7,
    carbs: 28,
    fat: 0.3,
    fiber: 0.4,
    vitamins: ['비타민 B1'],
    minerals: ['마그네슘'],
    benefits: ['에너지 공급'],
    description: '쌀을 물과 함께 가열하여 익힌 한국의 주식입니다.'
  },
  {
    name: '달걀',
    category: '동물성',
    calories: 155,
    protein: 13,
    carbs: 1.1,
    fat: 11,
    fiber: 0,
    vitamins: ['비타민 A', '비타민 D', '비타민 B12'],
    minerals: ['철분', '인'],
    benefits: ['완전 식품', '눈 건강'],
    description: '완전 식품으로 불리는 닭의 알로, 다양한 요리에 활용됩니다.'
  }
];

// localStorage에서 사용자 추가 재료 가져오기
function getCustomIngredients(): IngredientInfo[] {
  try {
    const stored = localStorage.getItem(STORAGE_KEY);
    return stored ? JSON.parse(stored) : [];
  } catch (error) {
    console.error('Failed to load custom ingredients:', error);
    return [];
  }
}

// localStorage에 사용자 추가 재료 저장하기
function saveCustomIngredients(ingredients: IngredientInfo[]): void {
  try {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(ingredients));
  } catch (error) {
    console.error('Failed to save custom ingredients:', error);
  }
}

// 모든 재료 가져오기 (기본 + 사용자 추가)
export function getAllIngredients(): IngredientInfo[] {
  const customIngredients = getCustomIngredients();
  return [...defaultIngredients, ...customIngredients];
}

// 재료 추가
export function addIngredient(ingredient: IngredientInfo): void {
  const customIngredients = getCustomIngredients();
  const exists = customIngredients.some(i => i.name === ingredient.name);
  
  if (exists) {
    throw new Error('이미 존재하는 재료입니다.');
  }
  
  customIngredients.push(ingredient);
  saveCustomIngredients(customIngredients);
}

// 재료 수정
export function updateIngredient(originalName: string, updatedIngredient: IngredientInfo): void {
  const customIngredients = getCustomIngredients();
  const index = customIngredients.findIndex(i => i.name === originalName);
  
  if (index !== -1) {
    customIngredients[index] = updatedIngredient;
    saveCustomIngredients(customIngredients);
  } else {
    throw new Error('재료를 찾을 수 없습니다.');
  }
}

// 재료 삭제
export function deleteIngredient(name: string): void {
  const customIngredients = getCustomIngredients();
  const filtered = customIngredients.filter(i => i.name !== name);
  saveCustomIngredients(filtered);
}

// 재료명에서 키워드를 추출하는 함수
export function extractIngredientKeyword(ingredientStr: string): string {
  const cleanedStr = ingredientStr
    .replace(/[0-9]+/g, '')
    .replace(/[g|kg|ml|L|개|컵|큰술|작은술|쪽|톨|대|장|분|약간|적당량|적당히]/g, '')
    .replace(/[\(\)]/g, '')
    .trim();
  
  const words = cleanedStr.split(/[,\/\s]+/);
  return words[0] || ingredientStr;
}

// 재료 정보 검색
export function findIngredientInfo(ingredientStr: string): IngredientInfo | null {
  const keyword = extractIngredientKeyword(ingredientStr);
  const allIngredients = getAllIngredients();
  
  // 정확히 일치하는 재료 찾기
  const exact = allIngredients.find(ing => ing.name === keyword);
  if (exact) return exact;
  
  // 부분 일치하는 재료 찾기
  const partial = allIngredients.find(ing => 
    keyword.includes(ing.name) || ing.name.includes(keyword)
  );
  
  return partial || null;
}
