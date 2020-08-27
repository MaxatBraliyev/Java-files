package kz.bmt.carmanager;

public class CarManager {

	public static void main(String[] args) throws Exception {

		Car prius = new PassengerCar("Toyota Prius", CarColor.BLACK, 2008, 15000, 1600);
		
		Car camry = new PassengerCar("Toyota Camry", CarColor.BLACK, 2008, 15000, 1600);
		
		Car renault = new Bus("Renaul Logan", CarColor.GREEN, 2012, 10000, 1800);
		
		Car renault2 = new Bus("Renaul Logan", CarColor.GREEN, 2012, 10000, 1800);
		
		/*Добавление пробега автомобилю и автобусу для проверки что JVM сама определит какого типа вызвать метод*/
		prius.addCarDistance(5);
		prius.addCarDistance(20.5);
		
		/*Добавление пробега автомобилю и автобусу для проверки готовности его к сервису*/
		camry.addCarDistance(15000);
		renault.addCarDistance(20000);
		
		/*Просто вызов автомобилей и автобусов*/
		System.out.println(prius);
		System.out.println(camry);
		System.out.println(renault);
		System.out.println(renault2);
		
		/*Метод сравнения который реализован в классе Car*/
		//System.out.println(prius.equals(renault));
		//System.out.println(renault.equals(renault2));
		
		/*Проверка готов ли автомобиль camry или автобус renault к сервису*/
		System.out.println(camry.isReadyToService());
		System.out.println(renault.isReadyToService());
		
	}

}
