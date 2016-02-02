/** 
 * Tester class of the Rock, Paper, Scissors, Lizard, Spock
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class RPSLKMain {
	public static void main(String args[]) {
		
		/*
		 * winner holds the winner, userOption holds the option user chose, userRounds holds the number of rounds
		 * automateChoice is whether the user wants to automate the user.
		 * thinkerChoice is whether the user wants ai
		 */
		int winner, userOption, userRounds;
		String automateChoice, thinkerChoice = null;
		
		/*
		 * Creates the Decider, Thrower, Talker objects
		 */
		Decider myDecider = new Decider();
		Thrower myThrower = new Thrower();
		Talker myTalker = new Talker();
		
		
		myTalker.getInstructions();
		automateChoice = myTalker.getAutomateChoice();
		thinkerChoice = myTalker.getThinkerChoice();
		
		//If user doesn't want to automate the user
		if (automateChoice.equals("n")) {
			do {
				String computerInput;
				String playerInput = myTalker.getManualThrow();
				//If the user wants to use AI
				if (thinkerChoice.equals("y")) {
					computerInput = myThrower.getSmartThrow();
					myThrower.setSmartThrows(playerInput);
				}
				//If the user doesn't want to use AI
				else {
					computerInput = myThrower.getThrow();
				}
				myTalker.sayComputerChoice(computerInput);
				myDecider.testWin(playerInput, computerInput);
				winner = myDecider.getWinner();
				myTalker.sayWinner(winner);
				} while (winner != 4);	
		}
		//If user wants to automate the user
		else {
			userOption = myTalker.getUserOptions();
			userRounds = myTalker.getUserRounds();
			String[] playerInput, computerInput;
			int[] winners = new int[userRounds];
			//If user wants to use AI
			if (thinkerChoice.equals("y")) 
				myDecider.setSmartPlay(userOption, userRounds, myThrower);
			//If user doesn't want to use AI
			else
				myDecider.setNormalPlay(userOption, userRounds, myThrower);
			playerInput = myDecider.getPlayerInput();
			computerInput = myDecider.getComputerInput();
			myDecider.testWin(playerInput, computerInput);
			winners = myDecider.getWinners();
			myTalker.sayWinners(winners);
		}
	}
}
