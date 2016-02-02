/**
 * A type of User that throws randomly
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Randomizer extends User {
	
	/*
	 * userRounds is the number of rounds
	 * smartThrowPosition is the position of smartThrow
	 */
	private int userRounds = 0;
	private int smartThrowPosition = 0;
	
	/**
	 * Randomizer constructor
	 * @param userRounds sets local userRounds to parameter
	 */
	public Randomizer(int userRounds) {
		super(userRounds);
		this.userRounds = userRounds;
	}
	
	/**
	 * sets the smartThrowPosition variable
	 * @param pos sets smartThrowPosition to this parameter
	 */
	public void setSmartThrowPosition(int pos) {
		smartThrowPosition = pos;
	}
	
	/**
	 * gets the smartThrow
	 * @return returns the value of allUsers at smartThrowPosition
	 */
	public String getSmartThrow() {
		String[] allUsers = getThrow();
		return allUsers[smartThrowPosition];
	}
	
	/**
	 * Gets an array from getThrow
	 * @return returns an array of Strings thrown
	 */
	public String[] getThrow() {
		for (int x = 0; x < userRounds; x++) {
			setUserThrow(getRandomThrow(), x);
		}
		return getUserThrow();
			
	}
}
