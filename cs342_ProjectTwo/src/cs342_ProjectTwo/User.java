package cs342_ProjectTwo;

//
//Class: User
//
//Description:
//This class will contain all input provided by the user.
//

public class User {
		
	private final int MAX_NUMBER;

	
	
///////////////////////////////////////////////////////////////////
/// User ///
/// Input : int///
/// Output: none///
/// Constructor for user. Requires the user's preferred max number.
/// ///
///////////////////////////////////////////////////////////////////

	public User (int maxNumber) {
		MAX_NUMBER = maxNumber;
	}
	
	
	
	//getter
	public int getMAX_NUMBER() {
		return MAX_NUMBER;
	}

}
