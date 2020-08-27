package kz.bmt.carmanager;

public interface Serviceable {
	
	public boolean isReadyToService();
	
	public int getDistanceOnService();
	
	public void addCarDistance(double additinalCarDistance) throws Exception;
	
	public void addCarDistance(int additinalCarDistance) throws Exception;

}
