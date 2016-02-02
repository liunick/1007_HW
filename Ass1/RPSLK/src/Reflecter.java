/**
 * A type of User that throws whatever was thrown by the computer last round
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Reflecter extends User {
	
	/*
	 * userRounds is the number of rounds
	 * computerChoices is an array of the computerChoices
	 * smartThrowPosition is the position of smartThrow
	 * isSmartChoice is a boolean to decide if choosing smart
	 * lastChoice holds the last choice
	 */
	private int userRounds = 0;
	String[] computerChoices;
	private int smartThrowPosition = 0;
	boolean isSmartChoice = false;
	String lastChoice = "";
	
	/**
	 * Reflecter construct
	 * @param userRounds sets the local userRounds to the parameter
	 * @param computerChoices sets the local computerChoices to the parameter
	 */
	public Reflecter(int userRounds, String[] computerChoices) {
		super(userRounds);
		this.userRounds = userRounds;
		this.computerChoices = computerChoices;
	}
	
	/**
	 * Sets the smartThrowPosition
	 * @param pos Sets smartThrowPosition to pos
	 */
	public void setSmartThrowPosition(int pos) {
		smartThrowPosition = pos;
	}
	
	/**
	 * Gets the smartThrow
	 * @return Returns the value of allUsers at smartThrowPosition
	 */
	public String getSmartThrow() {
		String[] allUsers = getThrow();
		isSmartChoice = true;
		return allUsers[smartThrowPosition];
	}
	
	/**
	 * sets lastChoice to the input
	 * @param input lastChoice is set to this parameter
	 */
	public void setLastChoice(String input) {
		lastChoice = input;
	}
	
	/**
	 * Gets the lastChoice variable
	 * @return Returns value of lastChoice
	 */
	public String getLastChoice() {
		return lastChoice;
	}
	
	/**
	 * Gets the throw in an array
	 * @return returns an array of the userThrows
	 */
	public String[] getThrow() {
		for (int x = 0; x < userRounds; x++) {
			if (getFirstThrow()) {
				setUserThrow(getRandomThrow(), x);
				changeFirstThrow();
			} else {
				if (!isSmartChoice)
					setUserThrow(computerChoices[x-1], x);
				else
					setUserThrow(getLastChoice(), x);
			}
		}
		return getUserThrow();	
	}
}
