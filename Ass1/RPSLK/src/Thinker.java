import java.util.ArrayList;
import java.util.Arrays;

/** 
 * Provides AI for the Thrower class
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Thinker {
	
	/*
	 * rockNum, paperNum, scissorNum, lizardNum, spockNum hold the number of times each choice arose
	 * holdValues is an arraylist of all the values
	 * numOfEach is an array of all the number of each choice
	 * returnChoice is the returned smart throw
	 */
	private int rockNum, paperNum, scissorNum, lizardNum, spockNum;
	ArrayList<String> holdValues = new ArrayList<String>();
	int[] numOfEach;
	String returnChoice;

	/**
	 * Thinker constructor
	 */
	public Thinker() {

	}
	
	/**
	 * Adds a value to the holdValues arraylist
	 * @param s input that gets added to holdValues
	 */
	public void setValue(String s) {
		holdValues.add(s);
	}
	
	/**
	 * Gets the smart choice and returns it
	 * @return Returns the smart choice
	 */
	public String getChoice() {

		rockNum = 0;
		paperNum = 0;
		scissorNum = 0;
		lizardNum = 0;
		spockNum = 0;
		
		//Loops through holdValues to add values to each var
		for (String s : holdValues) {
			if (s.equals("r"))
				rockNum++;
			else if (s.equals("p"))
				paperNum++;
			else if (s.equals("s"))
				scissorNum++;
			else if (s.equals("l"))
				lizardNum++;
			else
				spockNum++;
		}
		numOfEach = new int[]{rockNum, paperNum, scissorNum, lizardNum, spockNum}; 
		Arrays.sort(numOfEach);
		if (numOfEach[4] == spockNum)
			returnChoice = "p";
		else if (numOfEach[4] == lizardNum)
			returnChoice = "r";
		else if (numOfEach[4] == scissorNum)
			returnChoice = "r";
		else if (numOfEach[4] == paperNum)
			returnChoice = "s";
		else
			returnChoice = "p";
		return returnChoice;
	}
	
	
	
}
