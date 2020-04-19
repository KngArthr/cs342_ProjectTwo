package cs342_ProjectTwo;

//
//Class: Sieve
//
//Description:
//This class will contain the main algorithms involved with this program,
//including, but not limited to, the sieve of Eratosthenes.
//

public class Sieve {
	
	private ArrayQueue numberBank;
	private ArrayQueue primeBank;
	
	
	
///////////////////////////////////////////////////////////////////
/// Sieve ///
/// Input : int///
/// Output: None ///
/// Constructor for Sieve, initializes numberBank an primeBank///
/// ///
///////////////////////////////////////////////////////////////////	
	public Sieve (int maxNumber) {
		
		numberBank = generateNumBank(maxNumber);
		primeBank = new ArrayQueue();
	}
	
///////////////////////////////////////////////////////////////////
/// generateNumbBank ///
/// Input : int///
/// Output: None ///
/// generates numbers starting at 2 until the maximum number
/// and places them in an array-based queue
/// ///
///////////////////////////////////////////////////////////////////		
	public ArrayQueue generateNumBank(int maxNumber){
		int count = 2;
		
		ArrayQueue numberBank = new ArrayQueue();
		
		while(count <= maxNumber) {
			numberBank.add(count);
			System.out.println("numberBank:\n" + numberBank);
			count++;
		}
		
		return numberBank;
		
	}
///////////////////////////////////////////////////////////////////
/// sieveOfEratosthenes ///
/// Input : int///
/// Output: None ///
/// removes all numbers that are not prime using the sieveOfEratosthenes
/// algorithm
/// ///
///////////////////////////////////////////////////////////////////		
	public void sieveOfEratosthenes (int maxNumber) {
		int p = 0;
		
		int tempData = 0;
		
		int valueCounter = 0;
		
		do {
			p = numberBank.remove();
			
			primeBank.add(p);
			valueCounter = numberBank.size();
			
			for(int i = 0; i < valueCounter; i++) {
				
				
				System.out.println("Iteration: " + i + "/" + valueCounter);
				System.out.println("NumberBank Capacity: " + numberBank.maxCapacity());
				
				tempData = numberBank.remove();
				
				System.out.println("TempData: " + tempData);
				System.out.println("P: " + p);
				
				if((tempData % p) == 0) {
					System.out.println("True --> Not Prime");
					//already removed
				}else {
					System.out.println("False --> Prime\n");
					System.out.println(numberBank);
					numberBank.add(tempData);
				}
			}
			
		}while(p < Math.sqrt(maxNumber));
	}
	
	public void transferPrime() {
		for(int i = 0; i < numberBank.size(); i++) {
			primeBank.add(numberBank.dataAt(i));
		}
	}

	
	
	//getters and setters
	public ArrayQueue getNumberBank() {
		return numberBank;
	}


	public void setNumberBank(ArrayQueue numberBank) {
		this.numberBank = numberBank;
	}


	public ArrayQueue getPrimeBank() {
		return primeBank;
	}


	public void setPrimeBank(ArrayQueue primeBank) {
		this.primeBank = primeBank;
	}

}
