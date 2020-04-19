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
///////////////////////////////////////////////////////////////////
/// promptForNumber ///
/// Input : none///
/// Output: user input as int///
/// prompts user for input as int and returns that int
/// ///
///////////////////////////////////////////////////////////////////
	
	public int promptForNumber() {
		
		String rtn = "";
		Scanner scanner = new Scanner(System.in);
		

		
		
		while(true) {// infinite loop to keep asking the user until the correct format is detected.
			
			
			System.out.println("Please enter an int that is 2 or larger to display all the prime numbers up to that number: ");
			//prompt
			
			rtn = scanner.next();//scans for the input
			
			System.out.println("You have chosen the input " + rtn);//displays number back to the user
			
			if(isStringInt(rtn)) {//error checking to verify int
				
				if(isGreaterThanOrEqualTo(Integer.parseInt(rtn), 2)) {//error checking to verify it is greater than two
					
					return Integer.parseInt(rtn);//return the number if everything checks out

				}else {
					
					System.out.println("The input " + rtn + " is invalid.");//otherwise state that it is invalid
					
				}
				
			}else {
				
				System.out.println("The input " + rtn + " is invalid.");//otherwise state that it is invalid
				
			}
			
						
		}
		
	}
	
///////////////////////////////////////////////////////////////////
/// isStringInt ///
/// Input : String///
/// Output: boolean true or false///
/// Checks if String can be parsed to an int
/// ///
///////////////////////////////////////////////////////////////////
	
	
	public boolean isStringInt(String s) {
		
	    try {//try to parse the string to an int, if it works then return true
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex) {//if it does not work then string is not an int and return false.
	        return false;
	    }
	}
	
///////////////////////////////////////////////////////////////////
/// isGreaterThanOrEqualTo ///
/// Input : int///
/// Output: boolean true or false///
/// Checks if int is greater than or equal to a second int, checkAgainst
/// ///
///////////////////////////////////////////////////////////////////
	
	public boolean isGreaterThanOrEqualTo(int value, int checkAgainst) {
		//if int is greater than or equal checkAgainst then returns true
		//if int is less than checkAgainst then return false;
		
	    return value >= checkAgainst;
	}

///////////////////////////////////////////////////////////////////
/// displayArrayQueue ///
/// Input : ArrayQueue///
/// Output: String///
/// returns a String containing all the data in queueToDisplay
/// ///
///////////////////////////////////////////////////////////////////
	
	public String displayArrayQueue(ArrayQueue queueToDisplay) {
		
		String rtn = "";
				
		int size = queueToDisplay.size();
		
		//if array is empty then return "empty"
		if(queueToDisplay.isEmpty()) {
			return "Empty>";
			
		}else {
					
			//for every value in the size of the queue
			for (int i = 0; i < size; i++) {
				
				//add a new line at every tenth value
				if(i % 10 == 0) {
					
					rtn += ("\n");
				}
				
				//add the value to the String
				rtn += (queueToDisplay.dataAt(i) + "");
				
				//add a comma, except for the last value
				if(i < size - 1) {
					rtn += (", ");

				}

				
				
			}
			
		}
		
		
		
		//return the string
		return rtn;
		
		
		
		
		
		
		
	}

}
