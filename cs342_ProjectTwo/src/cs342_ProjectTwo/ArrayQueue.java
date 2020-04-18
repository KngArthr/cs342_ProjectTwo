package cs342_ProjectTwo;
//
//Class: ArrayQueue
//
//Description:
//This class contains all necessary methods to build and use array-based queues.
//
//

public class ArrayQueue {

	private static final int QUEUE_SIZE = 10;
	private static final int GROWTH_INCREMENT = 10;

	private int[] queue;
	private int count;
	private int head;
	private int tail;
	
///////////////////////////////////////////////////////////////////
/// ArrayQueue ///
/// Input : None ///
/// Output: None ///
/// Initializes new array queue and sets counter to 0  ///
/// ///
///////////////////////////////////////////////////////////////////
	
	public ArrayQueue() {
		clear();
	}

///////////////////////////////////////////////////////////////////
/// add ///
/// Input : data as an int ///
/// Output: returns a boolean true or false ///
/// Adds data to the back of array queue.
/// ///
///////////////////////////////////////////////////////////////////
	public boolean add(int data) {
		
		if (isFull()) {
			
			//return false;
			
			extend();
		}
		//add data to the tail of the queue
		queue[tail] = data;
		tail++;//tail increments as the location of the tail has shifted
		if (tail == queue.length) {//if the tail has hit the maximum of the queue, loop back around.
			tail = 0;
		}
		count++;//increment count because there is now another data value
		return true;//return true if data has been added successfully
	}
///////////////////////////////////////////////////////////////////
/// extend ///
/// Input : none///
/// Output: none///
/// extends array queue by growth increment
/// ///
///////////////////////////////////////////////////////////////////
	public void extend() {
		
		int[] temp = new int [queue.length + GROWTH_INCREMENT];
		
		for(int i = 0; i < count; i++) {
			temp[i] = queue[i];
		}
		
		queue = temp;
		

		
		
		System.out.println("New queue size is: " + queue.length);
		//System.out.println(toString());
		
	}

///////////////////////////////////////////////////////////////////
/// remove ///
/// Input : none///
/// Output: returns the removed data as int///
/// Removes data from the current head in the array queue by decrementing count.
/// increments the head to move the the next slot in the queue
/// returns removed data.
/// ///
///////////////////////////////////////////////////////////////////

	public int remove() {
		//put error handling here
		if (isEmpty()) {
			
			System.out.println("Queue is empty.");
			
			return -1;
		}
		
		int rtn = queue[head];
		
		
		head++;//shift the head over one, data does not exist if nothing is pointed to it
		
		if (head == queue.length) {//if head has reached the limit then wrap back around
			head = 0;
		}
		count--;//subtract one from count because one less data
		
		return rtn;//return the data that we eliminated
	}

///////////////////////////////////////////////////////////////////
/// clear ///
/// Input : None ///
/// Output: None ///
/// Initializes new array queue and sets counter to 0 ///
/// 
/// ///
///////////////////////////////////////////////////////////////////
	public void clear() {
		//initialize a new queue with 0 data
		queue = new int[QUEUE_SIZE];
		count = 0;
	}

///////////////////////////////////////////////////////////////////
/// size ///
/// Input : none///
/// Output: returns count as int///
/// returns count of the amount of data in the queue
/// ///
///////////////////////////////////////////////////////////////////

	public int size() {
		return count;
	}
///////////////////////////////////////////////////////////////////
/// maxCapacity ///
/// Input : none///
/// Output: returns length of queue as int///
/// returns length of queue which is the maximum smount of data the queue can hold
/// ///
///////////////////////////////////////////////////////////////////

	public int maxCapacity() {
		return queue.length;
	}
///////////////////////////////////////////////////////////////////
/// peek ///
/// Input : none///
/// Output: returns the head of the queue as int///
/// returns the next data in the queue
/// ///
///////////////////////////////////////////////////////////////////

	public int peek() {
		
		if (isEmpty()) {
			
			System.out.println("Queue is empty.");

			return -1;
		}
		//return the head of the queue
		return queue[head];
	}
///////////////////////////////////////////////////////////////////
/// isEmpty ///
/// Input : none///
/// Output: boolean ///
/// returns true or false based on if the queue is empty.
/// ///
///////////////////////////////////////////////////////////////////
	
	public boolean isEmpty() {
		//if count is 0 then the queue is empty
		//if the count is not zero then the queue i not empty
		return count == 0;
	}
///////////////////////////////////////////////////////////////////
/// isFull ///
/// Input : none///
/// Output: boolean ///
/// returns true or false based on if the queue is full
/// ///
///////////////////////////////////////////////////////////////////	
	public boolean isFull() {
		//if count is equivalent to the length of the queue array, then the queue is full
		//anything less and the queue is not full
		return count == queue.length;
	}
///////////////////////////////////////////////////////////////////
/// toString ///
/// Input : none///
/// Output: String ///
/// returns of all contents of array queue in a orderly format.
/// ///
///////////////////////////////////////////////////////////////////		

	public String toString() {
		String rtn = "";
		
		//if array is empty then return "empty"
		if (count == 0) {
			return "<Empty>";
		} else {
			
			int n = head;
			int c = count;
			//go through the array starting form the head and ending at count
			while(c > 0) {
				if (n == head) {
					rtn += "head -> ";
				} else {
					rtn += "        ";
				}
				///print the contents of each slot into a new line
				rtn += queue[n] + "\n";
				n++;
				//if we reach the end of the array then go back to the beginning of the array
				//(queues can loop around sometimes)
				if (n == queue.length) {
					n = 0;
				}
				
				c--;
			}
			//return the string
			return rtn;
		}
	}
		
	
}