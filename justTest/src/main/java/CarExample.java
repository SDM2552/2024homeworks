

class Car {
	
	String company="현대자동차";
	String model="그랜저";
	String color="검정";
	int maxSpeed=350;
	int speed;
}

public class CarExample {
	
	public static void main(String[] args) {
		Car myCar = new Car();
		
		System.out.println("제작회사:: "+ myCar.company);
		System.out.println("모델명: "+myCar.model);
		System.out.println("색깔: "+myCar.color);
		System.out.println("최대속도: "+myCar.maxSpeed);
		
		myCar.speed=60;
		System.out.println("수정된 속도: "+myCar.speed);
	}

}
