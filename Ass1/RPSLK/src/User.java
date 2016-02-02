/**
 * A class that provides the template for more specific users
 * @author Nicholas Liu (nl2523) 
 * @version 1.0
 */
public class User {
	
	/*
	 * previousUserThrow holds the previous variables
	 * userThrows is an array that holds all the user throws
	 * isFirstThrow is a boolean that checks whether the user is on their first throw
	 */
	private String previousUserThrow;
	private String[] userThrows;
	private boolean isFirstThrow = true;
	
	/**
	 * Constructor for User
	 */
	public User() {
		
	}
	
	/**
	 * Takes in userRounds and uses it to initialize userThrows
	 * @param userRounds	the number of rounds the user wants to play
	 */
	public User(int userRounds) {	
		userThrows = new String[userRounds];
	}
	
	/**
	 * Changes the value of isFirstThrow
	 */
	public void changeFirstThrow() {
		isFirstThrow = !isFirstThrow;
	}
	
	/**
	 * Gets the value of isFirstThrow
	 * @return isFirstThrow	boolean value of first throw or not
	 */
	public boolean getFirstThrow() {
		return isFirstThrow;
	}
	
	/**
	 * Sets inputtedThrow into the position in the userThrows array
	 * @param inputtedThrow The value that the user passes in
	 * @param position The position The position that the inputtedThrow value should be placed in the array
	 */
	public void setUserThrow(String inputtedThrow, int position) {
		userThrows[position] = inputtedThrow;
	}
	
	/**
	 * Sets the previous throw
	 * @param inputtedThrow String variable passed in to set to previousUserThrow
	 */
	public void setPreviousThrow(String inputtedThrow) {
		previousUserThrow = inputtedThrow;
	}
	
	/**
	 * Gets the previous throw
	 * @return Returns the String value of previousUserThrow
	 */
	public String getPreviousThrow() {
		return previousUserThrow;
	}
	
	/**
	 * Gets the current user throw array
	 * @return Returns the String array of userThrows
	 */
	public String[] getUserThrow() {
		return userThrows;
	}
	
	/**
	 * Gets an element of user throw array
	 * @param position Position of element wanted from the array
	 * @return Returns element of the userThrows array at the set position
	 */
	public String getUserThrow(int position) {
		return userThrows[position];
	}
	
	/**
	 * Gets a random throw
	 * @return Returns a random String, either "r", "p", "s", "l", "k"
	 */
	public String getRandomThrow() {
		String randomThrow = "";
		int throwerNum = (int) (Math.random() * 5);
		if (throwerNum == 0)
			randomThrow = "r";
		else if (throwerNum == 1)
			randomThrow = "p";
		else if (throwerNum == 2)
			randomThrow = "s";
		else if (throwerNum == 3)
			randomThrow = "l";
		else if (throwerNum == 4)
			randomThrow = "k";
		return randomThrow;
	}
}
