package kz.bmt.carmanager;
//Test on JUinit5
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*Тестирование*/
class TestCar {
	
	Car newCar =new PassengerCar("Toyota Prius", CarColor.BLACK, 2008, 15000, 1600);

	@Test
	void testCarColor() {
		assertEquals("BLACK",newCar.carColor.compareTo("BLACK"));
	}
	
	@Test
	void testCarName() {
		assertEquals("Toyota Prius",newCar.carName);
	}
	
	@Test
	void testCarYaerOfProduction() {
		assertEquals(2008,newCar.carYaerOfProduction);
	}
	
	@Test
	void testCarPrice() {
		assertEquals(15000,newCar.carPrice);
	}
	
	@Test
	void testCarWeight() {
		assertEquals(1600,newCar.carWeight);
	}

}
