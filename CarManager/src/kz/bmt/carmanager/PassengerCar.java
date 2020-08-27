package kz.bmt.carmanager;

public class PassengerCar extends Car {

	public PassengerCar(String carName, CarColor carColor, int carYaerOfProduction, int carPrice, int carWeight) {
		super(carName, carColor, carYaerOfProduction, carPrice, carWeight);
	}

	@Override
	public boolean isReadyToService() {
		if(distanceOnService>10000) {
			return true;
		}
		return false;
	}

}
