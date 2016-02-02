/** 
 * Tests win condition and sets smart/normal play
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Decider {
	
	/*
	 * testWin is a value that signifies who won
	 * listOfInts is an array that holds all the winners
	 * playerInput, computerInput are arrays that hold Strings that the user and computer throw
	 */
	private int testWin =0;
	private int[] listOfInts;
	String[] playerInput;
	String[] computerInput;

	/**
	 * Decider constructor
	 */
	public Decider() {
		
	}

	/**
	 * Test your wins 
	 * @param player passes in the player choice
	 * @param computer passes in the computer choice
	 */
	public void testWin(String player, String computer) {
		/*
		 * Used to check who wins
		 * 0 = error
		 * 1 = player wins
		 * 2 = computer wins
		 * 3 = tie
		 * 4 = exit
		 */		
		//If it's the same throw, return 3
		if (player.equals(computer))
			testWin =  3;
		else if (player.equals("z"))
			testWin = 4;
		else {

			switch(player.toCharArray()[0]) {
				case 'r':
					if (computer.equals("1") || computer.equals("s"))
						testWin = 1;
					else
						testWin = 2;
					break;
				case 'p':
					if (computer.equals("r") || computer.equals("k"))
						testWin = 1;
					else
						testWin = 2;
					break;
				case 's':
					if (computer.equals("p") || computer.equals("l"))
						testWin = 1;
					else 
						testWin = 2;
					break;
				case 'l':
					if (computer.equals("p") || computer.equals("k"))
						testWin = 1;
					else
						testWin = 2;
					break;
				case 'k':
					if (computer.equals("r") || computer.equals("s"))
						testWin = 1;
					else 
						testWin = 2;
					break;
				default:
					testWin = 0;
					break;
			}		
		}
	}
	
	/**
	 * Test your wins except takes in multiple choices
	 * @param player An array of strings of player choices
	 * @param computer An array of strings of computer choices
	 */
	public void testWin(String[] player, String[] computer) {
		listOfInts = new int[computer.length];
		for (int x = 0; x < player.length; x++) {
			testWin(player[x], computer[x]);
			listOfInts[x] = getWinner();
		}
	}

	/**
	 * Sets the options for smart play
	 * @param userOption takes in the choice that the user took
	 * @param userRounds takes in the number of rounds user wants to play
	 * @param myThrower takes in the thrower object passed in from main
	 */
	public void setSmartPlay(int userOption, int userRounds, Thrower myThrower) {
		
		playerInput = new String[userRounds];
		computerInput = new String[userRounds];
		Repeater myRepeater = new Repeater(userRounds);;
		Rotater myRotater = new Rotater(userRounds);
		Reflecter myReflecter = new Reflecter(userRounds, computerInput);
		Randomizer myRandomizer = new Randomizer(userRounds);
		Mixer myMixer = new Mixer(userRounds, computerInput);
		
		//Loops through the userRounds to set player and computer input
		for (int x = 0; x < userRounds; x++) {
			if (userOption == 1) {
				myRepeater.setSmartThrowPosition(x);
				playerInput[x] = myRepeater.getSmartThrow();
				computerInput[x] = myThrower.getSmartThrow();
				myThrower.setSmartThrows(playerInput[x]);
			} else if (userOption == 2) {
				myRotater.setSmartThrowPosition(x);
				playerInput[x] = myRotater.getSmartThrow();
				computerInput[x] = myThrower.getSmartThrow();
				myThrower.setSmartThrows(playerInput[x]);
			} else if (userOption == 3) {
				myReflecter.setSmartThrowPosition(x);
				playerInput[x] = myReflecter.getSmartThrow();
				computerInput[x] = myThrower.getSmartThrow();
				myThrower.setSmartThrows(playerInput[x]);
				myReflecter.setLastChoice(computerInput[x]);
			} else if (userOption == 4) {
				myRandomizer.setSmartThrowPosition(x);
				playerInput[x] = myRandomizer.getSmartThrow();
				computerInput[x] = myThrower.getSmartThrow();
				myThrower.setSmartThrows(playerInput[x]);
			} else {
				myMixer.setSmartThrowPosition(x);
				playerInput[x] = myMixer.getSmartThrow();
				computerInput[x] = myThrower.getSmartThrow();
				myThrower.setSmartThrows(playerInput[x]);
				//playerInput = myMixer.getSmartThrow();
			}
		}
	}
	
	/**
	 * Sets the options for normal play
	 * @param userOption takes in the choice that the user took
	 * @param userRounds takes in the number of rounds user wants to play
	 * @param myThrower takes in the thrower object passed in from main
	 */
	public void setNormalPlay(int userOption, int userRounds, Thrower myThrower) {
		computerInput = new String[userRounds];
		for (int x = 0; x < userRounds; x++) {
			computerInput[x] = myThrower.getThrow();
		}
		if (userOption == 1) {
			Repeater myRepeater = new Repeater(userRounds);
			playerInput = myRepeater.getThrow();	
		} else if (userOption == 2) {
			Rotater myRotater = new Rotater(userRounds);
			playerInput = myRotater.getThrow();
		} else if (userOption == 3) {
			Reflecter myReflecter = new Reflecter(userRounds, computerInput);
			playerInput = myReflecter.getThrow();
		} else if (userOption == 4) {
			Randomizer myRandomizer = new Randomizer(userRounds);
			playerInput = myRandomizer.getThrow();
		} else {
			Mixer myMixer = new Mixer(userRounds, computerInput);
			playerInput = myMixer.getThrow();
		}
	}
	
	/**
	 * Gets the playerInput
	 * @return Returns playerInput String array
	 */
	public String[] getPlayerInput() {
		return playerInput;
	}
	
	/**
	 * Gets the computerInput
	 * @return Returns computerInput String array
	 */
	public String[] getComputerInput() {
		return computerInput;
	}
	
	/**
	 * Gets the winners
	 * @return Returns listOfInts Int array
	 */
	public int[] getWinners() {
		return listOfInts;
	}
	
	/**
	 * Gets the winner of the testWin
	 * @return Returns testWin int
	 */
	public int getWinner() {
		return testWin;
	}
}
