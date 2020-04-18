package cs342_ProjectTwo;

//
//Class: UI
//
//Description:
//This class will contain the user interface. This include prompts
//to extract infomation form the user, error checking, and the final
//output methods.
//
//

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
			
			if(isStringInt(rtn)) {
				
				if(Integer.parseInt(rtn) >= 2) {
					
					return Integer.parseInt(rtn);

				}else {
					
					System.out.println("The input " + rtn + " is invalid.");
					
				}
				
			}else {
				
				System.out.println("The input " + rtn + " is invalid.");
				
			}
			
						
		}
		
	}
	public boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}

}
