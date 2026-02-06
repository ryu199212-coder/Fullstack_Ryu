package com.company.java011_ex;

public class Apple {
		private String name;
		private String order;
		private int num;
		private int price;
	
		
		@Override
		public String toString() {
			return "Apple [name=" + name + ", order=" + order + ", num=" + num + ", price=" + price + "]";}
		
		public Apple() { super();}
		
		public Apple(String name, String order, int num, int price) {
			super(); this.name = name; this.order = order; this.num = num; this.price = price; }

		public String getName() { return name; }   public void setName(String name) { this.name = name; }
		public String getOrder() { return order; } public void setOrder(String order) { this.order = order; }
		public int getNum() { return num; }        public void setNum(int num) { this.num = num; }
		public int getPrice() { return price; }    public void setPrice(int price) { this.price = price; } 
		
}
/*ㅁ출력된 화면
Apple [name=RED, order=iron, num=2, price=1000]
Apple [name=GREEN, order=hulk, num=1, price=1500]
Apple [name=GOLD, order=captain, num=3, price=2000]*/