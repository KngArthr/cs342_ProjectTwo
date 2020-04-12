package cs342_ProjectTwo;

import java.util.Scanner;

public class UI {
	
	public int promptForNumber() {
		
		String rtn = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Sieve of Eratosthenes.");

		
		
		while( true) {
			
			
			System.out.println("Please enter a number 2 or larger to display all prime numbers up to: ");

			rtn = scanner.next();
			
			System.out.println("You have chosen the number " + rtn);
			
			if(Integer.parseInt(rtn) >= 2) {
				return Integer.parseInt(rtn);

			}else {
				System.out.println("The input " + rtn + " is invalid.");
				
			}
			//add error checking?
			
		}
		
	}

}
