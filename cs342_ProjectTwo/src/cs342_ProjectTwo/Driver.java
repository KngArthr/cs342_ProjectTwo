package cs342_ProjectTwo;

public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.doStuff();
	}
	
	public void doStuff() {
		
		UI userInterface = new UI();
		
		userInterface.promptForNumber();
		
	}

}
