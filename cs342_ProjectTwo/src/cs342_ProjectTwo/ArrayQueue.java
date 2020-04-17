package cs342_ProjectTwo;

public class ArrayQueue {

	private static final int QUEUE_SIZE = 20;
	private int[] queue;
	private int count;
	private int head;
	private int tail;
	
	public ArrayQueue() {
		clear();
	}

	public boolean add(int data) {
		
		if (isFull()) {
			return false;
		}		
		
		queue[tail] = data;
		tail++;
		if (tail == queue.length) {
			tail = 0;
		}
		count++;
		return true;
	}

	public int remove() {
		//put error handling here
		if (isEmpty()) {
			return -1;
		}
		
		int rtn = queue[head];
		head++;
		if (head == queue.length) {
			head = 0;
		}
		count--;
		
		return rtn;
	}

	public void clear() {
		queue = new int[QUEUE_SIZE];
		count = 0;
	}

	public int size() {
		return count;
	}

	public int maxCapacity() {
		return queue.length;
	}

	public int peek() {
		//put error handling
		if (isEmpty()) {
			
			return -1;
		}
		
		return queue[head];
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public boolean isFull() {
		return count == queue.length;
	}
	

	public String toString() {
		String rtn = "";
		
		if (count == 0) {
			return "<Empty>";
		} else {
			int n = head;
			int c = count;
			
			while(c > 0) {
				if (n == head) {
					rtn += "head -> ";
				} else {
					rtn += "        ";
				}
				
				rtn += queue[n] + "\n";
				n++;
				if (n == queue.length) {
					n = 0;
				}
				
				c--;
			}
			
			return rtn;
		}
	}
		
	
}