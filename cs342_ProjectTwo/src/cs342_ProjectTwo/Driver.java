package cs342_ProjectTwo;
//---------------------------------------------------------------------------
//
//The Sieve of Eratosthenes
//Provides prime numbers up to the given number
//
//Author: Mujahid Kazi
//Date: 05/17/2020
//Class: MET CS342
//Issues: None known
//
//Description:
//This program generates prime number using the algorithm
//of the sieve of Eratosthenes. Array-based queues are used for this.
//The queues must initially have a capacity of 10 and increase when full
// and more space is needed. Two queue arrays are used for this. The first one
// is loaded with all the numbers up to the provided number, then primes of number list
// starting from the smallest numbers up until sqrt(n) are use to find multiples throughout
// the number list.
// 
//Assumptions:
//The user will input an integer larger than 2.
//

//
//Class: Driver
//
//Description:
//This is the main class. Calls on methods in other classes to execute
//the program.
//
//

///////////////////////////////////////////////////////////////////
/// Driver ///
/// Input : String[] args ///
/// Output: None ///
/// intializes the driver and call on the method that puts the program together ///
/// ///
///////////////////////////////////////////////////////////////////
public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.doStuff();
	}
///////////////////////////////////////////////////////////////////
/// doStuff ///
/// Input : None ///
/// Output: None ///
/// Initializes object, and calls methods in other classes to put the program together///
/// ///
///////////////////////////////////////////////////////////////////	

	public void doStuff() {
		
		System.out.println("Welcome to the Sieve of Eratosthenes.");
		
		
		UI userInterface = new UI();
		User user = new User(userInterface.promptForNumber());
		Sieve sieve = new Sieve(user.getMAX_NUMBER());
		System.out.println("Number Bank: ");
		System.out.println(sieve.getNumberBank());
		
		
		sieve.sieveOfEratosthenes(user.getMAX_NUMBER());
		
		System.out.println("Number Bank: ");

		System.out.println(sieve.getNumberBank());
		
		System.out.println("\nPrime numbers up to " + user.getMAX_NUMBER() + ": ");
		sieve.transferPrime();
		System.out.println(userInterface.displayArrayQueue(sieve.getPrimeBank()));

	
		
		
	}

}
