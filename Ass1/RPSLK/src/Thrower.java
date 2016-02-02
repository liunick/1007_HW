import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for the computer response
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Thrower {
	
	/*
	 * myList is a list that holds all the values that the computer throws
	 * multipleReturns is a String array to hold all the computer returns
	 * myThinker is a Thinker object to help the Thrower make smart decisions
	 * thrown is a variable that holds the different Throws
	 */
	List<Integer> myList = new ArrayList<Integer>();
	String[] multipleReturns;
	Thinker myThinker = new Thinker();
	String thrown;
	/**
	 * Thrower constructor
	 */
	public Thrower() {
		
	}
	
	/**
	 * Returns a throw
	 * @return Returns a random throw
	 */
	public String getThrow() {
		
		int throwerNum = -1;
		throwerNum = (int) (Math.random() * 5);
		
		myList.add(throwerNum);
		if (throwerNum == 0)
			thrown = "r";
		else if (throwerNum == 1)
			thrown = "p";
		else if (throwerNum == 2)
			thrown = "s";
		else if (throwerNum == 3)
			thrown = "l";
		else if (throwerNum == 4)
			thrown = "k";
		return thrown;
		
	}
	
	/**
	 * Returns an array of Strings based on a number of rounds
	 * @param rounds rounds are the number of rounds
	 * @return String array of that holds all the results
	 */
	public String[] getThrow(int rounds) {
		multipleReturns = new String[rounds];
		for (int x = 0; x < rounds; x++) {
			multipleReturns[x] = getThrow();
		}
		return multipleReturns;
		
	}

	/**
	 * Sets the thinker smart throw
	 * @param value the value that gets added to thinker
	 */
	public void setSmartThrows(String value) {
		myThinker.setValue(value);
	}
	
	/**
	 * Gets the smart throw
	 * @return Either a random throw if first throw or a smart throw if not
	 */
	public String getSmartThrow() {
		if (myList.size() == 0)
			return getThrow();
		return myThinker.getChoice();
	}

}
