package kz.bmt.carmanager;

public abstract class Car implements Serviceable {

	String carName;
	CarColor carColor;
	int carYaerOfProduction;
	int carPrice;
	int carWeight;
	private int carDistance = 0;
	protected int distanceOnService = 0;

	public Car(String carName, CarColor carColor, int carYaerOfProduction, int carPrice, int carWeight) {
		this.carName = carName;
		this.carColor = carColor;
		this.carYaerOfProduction = carYaerOfProduction;
		this.carPrice = carPrice;
		this.carWeight = carWeight;
	}

	public void addCarDistance(int additinalCarDistance) throws Exception {
		if (additinalCarDistance<0) {
	            throw new Exception("Внимание: вы ввели отрицательное значение!");
	        }
		
		carDistance += additinalCarDistance;
		distanceOnService += additinalCarDistance;
	}
	
	public void addCarDistance(double additinalCarDistance) throws Exception {
		if (additinalCarDistance<0) {
			throw new Exception("Внимание: вы ввели отрицательное значение!");
		}
		
		carDistance += additinalCarDistance;
		distanceOnService += additinalCarDistance;
	}

	public int getDistanceOnService() {
		return distanceOnService;
	}

	public int getCarDistance() {
		return carDistance;
	}

	@Override
	public String toString() {
		return "Car [carName=" + carName + ", carColor=" + carColor + ", carYaerOfProduction=" + carYaerOfProduction
				+ ", carPrice=" + carPrice + ", carWeight=" + carWeight + ", carDistance=" + carDistance
				+ ", distanceOnService=" + distanceOnService + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carColor == null) ? 0 : carColor.hashCode());
		result = prime * result + ((carName == null) ? 0 : carName.hashCode());
		result = prime * result + carPrice;
		result = prime * result + carWeight;
		result = prime * result + carYaerOfProduction;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carColor != other.carColor)
			return false;
		if (carName == null) {
			if (other.carName != null)
				return false;
		} else if (!carName.equals(other.carName))
			return false;
		if (carPrice != other.carPrice)
			return false;
		if (carWeight != other.carWeight)
			return false;
		if (carYaerOfProduction != other.carYaerOfProduction)
			return false;
		return true;
	}

}
