/**
 * A type of User throws cyclically through all the choices
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Rotater extends User {
	
	/*
	 * userRounds is the number of rounds
	 * CHOICES is the number of choices
	 * smartThrowPosition is the position of smartThrow
	 */
	private int userRounds = 0;
	private final int CHOICES = 5;
	private int smartThrowPosition;
	
	/**
	 * Rotater constructor
	 * @param userRounds This is the number of rounds the user has
	 */
	public Rotater(int userRounds) {
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
			int modulus = x % CHOICES;
			if (modulus == 0)
				setUserThrow("r", x);
			else if (modulus == 1)
				setUserThrow("p", x);
			else if (modulus == 2)
				setUserThrow("s", x);
			else if (modulus == 3)
				setUserThrow("l", x);
			else
				setUserThrow("k", x);
		}
		
		return getUserThrow();
			
	}
}
