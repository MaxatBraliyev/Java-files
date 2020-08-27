package kz.bmt.carmanager;

public class Bus extends Car {

	public Bus(String carName, CarColor carColor, int carYaerOfProduction, int carPrice, int carWeight) {
		super(carName, carColor, carYaerOfProduction, carPrice, carWeight);
	}

	@Override
	public boolean isReadyToService() {
		if(distanceOnService>50000) {
			return true;
		}
		return false;
	}
	
}
