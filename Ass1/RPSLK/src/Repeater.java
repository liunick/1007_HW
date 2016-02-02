/**
 * A type of User throws the same choice every time
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Repeater extends User {
	
	/*
	 * userRounds is the number of rounds
	 * smartThrowPosition is the position of smartThrow
	 */
	private int userRounds = 0;
	private int smartThrowPosition = 0;
	
	/**
	 * Repeater constructor
	 * @param userRounds Sets local variable userRounds to parameter
	 */
	public Repeater(int userRounds) {
		super(userRounds);
		this.userRounds = userRounds;
	}
	
	/**
	 * Sets the position of smartThrowPosition
	 * @param pos the value set to smartThrowPosition
	 */
	public void setSmartThrowPosition(int pos) {
		smartThrowPosition = pos;
	}
	
	/**
	 * Gets the value of allUsers at smartThrowPosition
	 * @return Gets the value of allUsers at smartThrowPosition
	 */
	public String getSmartThrow() {
		String[] allUsers = getThrow();
		return allUsers[smartThrowPosition];
	}
	
	/**
	 * Gets a throw
	 * @return Returns the throw
	 */
	public String[] getThrow() {
		for (int x = 0; x < userRounds; x++) {
			if (getFirstThrow()) {
				setUserThrow(getRandomThrow(), x);
				changeFirstThrow();
			} else {
				setUserThrow(getPreviousThrow(), x);
			}
			setPreviousThrow(getUserThrow(x));
		}
		return getUserThrow();	
	}
}
