package cs342_ProjectTwo;

public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.doStuff();
	}
	
	public void doStuff() {
		
		UI userInterface = new UI();
		
		userInterface.promptForNumber();
		
		ArrayQueue q = new ArrayQueue();
		
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		System.out.println(q);
		
		System.out.println("Item at head of queue is : " + q.peek());
		System.out.println("Queue length is          : " + q.size());

//		String r;
//		while((r = q.remove()) != null) {
//			System.out.println("Removed : " + r);
		
		while(q.size() > 0) {
			System.out.println("Removing " + q.peek());
			q.remove();
		}
		
	}

}
