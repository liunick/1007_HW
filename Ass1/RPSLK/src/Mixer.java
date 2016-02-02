/**
 * A type of User class that randomizes the user type every set number of rounds
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Mixer extends User {
	
	/*
	 * userRounds is the number of rounds, counter of iterations 
	 * computerChoices is the number of computerChoices
	 * smartThrowPosition is the position of smartThrow
	 */
	private int userRounds = 0, counter = 0;
	private String[] computerChoices;
	private int smartThrowPosition = 0;
	
	/**
	 * Mixer constructor
	 * @param userRounds sets the local userRounds to the parameter
	 * @param computerChoices sets the local computerChoices to the parameter
	 */
	public Mixer(int userRounds, String[] computerChoices) {
		super(userRounds);
		this.userRounds = userRounds;
		this.computerChoices = computerChoices;
	}
	
	/**
	 * Sets smartThrowPosition to pos
	 * @param pos the passed in position integer to smartThrowPosition
	 */
	public void setSmartThrowPosition(int pos) {
		smartThrowPosition = pos;
	}
	
	/**
	 * Gets the value of allUsers at smartThrowPosition
	 * @return the value at smartThrowPosition in allUsers
	 */
	public String getSmartThrow() {
		String[] allUsers = getThrow();
		return allUsers[smartThrowPosition];
	}
	
	/**
	 * Gets an array of strings of the throws
	 * @return returns an array of Strings
	 */
	public String[] getThrow() {
		for (int x = 0; x < userRounds; x++) {
			if (counter < 10) {
				Repeater myRepeater = new Repeater(1);
				setUserThrow(myRepeater.getThrow()[0], x);
				counter++;
			} else if (counter < 20) {
				Rotater myRotater = new Rotater(1);
				setUserThrow(myRotater.getThrow()[0], x);
				counter++;
			} else if (counter < 30) {
				Reflecter myReflecter = new Reflecter(1, computerChoices);
				setUserThrow(myReflecter.getThrow()[0], x);
				counter++;
			} else if (counter < 40) {
				Randomizer myRandomizer = new Randomizer(1);
				setUserThrow(myRandomizer.getThrow()[0], x);
				counter++;
				if (counter == 40)
					counter = 0;
			}
		}
		return getUserThrow();
	}
	
}
