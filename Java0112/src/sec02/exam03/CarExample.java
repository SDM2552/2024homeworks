package sec02.exam03;

public class CarExample {

	public static void main(String[] args) {

		Car myCar = new Car();
		
		myCar.SetGas(5);
		
		boolean gasState=myCar.isLeftGas();
		if(gasState) {
			System.out.println("출발합니다.");
			myCar.run();
		}
		myCar.goToGas();
		
		
		if(myCar.isLeftGas()) {
			System.out.println("gas를 주입할 필요가 없습니다.");
			myCar.run();
		} else {
			System.out.println("gas를 주입하세요.");
		}
		
	}

}
