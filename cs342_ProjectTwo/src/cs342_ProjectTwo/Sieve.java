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
		//sieve stores the initial number bank and bank of prime numbers
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
		int count = 2;//number bank starts at 2 because one is automatically out of the picture
		
		ArrayQueue numberBank = new ArrayQueue();//initializes number bank
		
		while(count <= maxNumber) {//generates numbers up until the user-provided max number
			numberBank.add(count);//add number to array queue
			count++;//increment count
		}
		
		return numberBank;//return the complete number bank
		
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
		int p = 0;//prime holder
		int tempData = 0;//temp data holder
		int initialSize = 0;//holfer of initial size
		
		do {//do this first
			p = numberBank.remove(); //removes a number, which will always be prime and places it into the prime holder
			
			
			primeBank.add(p);//Places this prime number into the prime bank
			initialSize = numberBank.size();//captures the initial size of the number bank
											//because size will constantly change as we modify the queue
			
			for(int i = 0; i < initialSize; i++) {//for the initial size of the queue
							
				tempData = numberBank.remove();//remove a value from the array and place into temp variable
					
				if((tempData % p) == 0) {
					//if data divides cleanly with prime number, data is not prime and stays removed
					
				}else {//if data does not divide cleanly, it is prime
					
					numberBank.add(tempData);//add it back to the bank
				}
			}
			
		}while(p < Math.sqrt(maxNumber));//do all this until we measure the numbers in the number bank against all prime numbers up to
										//the square root of the maxNumber
	}
///////////////////////////////////////////////////////////////////
/// transferPrime ///
/// Input : None///
/// Output: None ///
/// transfers all prime numbers from one queue to the next.
/// ///
///////////////////////////////////////////////////////////////////		
	public void transferPrime() {
		int initialSize = numberBank.size();
		
		for(int i = 0; i < initialSize; i++) {
			primeBank.add(numberBank.remove());//add data from the num bank into the prime bank
												//by removing from the number bank

		}
		
		/*for(int i = 0; i < numberBank.size(); i++) {
			primeBank.add(numberBank.dataAt(i));//add data from the num bank into the prime bank
												//by copying from specific places in the number bank

		}*/
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
