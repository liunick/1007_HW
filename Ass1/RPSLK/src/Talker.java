import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * A class that takes care of all the input and output from the console
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Talker {
	
	/*
	 * input is a Scanner object
	 * myList holds all the winner
	 * compWinCounter, playerWinCounter, tieCounter, totalGames count the number of wins
	 */
	private Scanner input;
	private List<Integer> myList = new ArrayList<Integer>();
	private int compWinCounter, playerWinCounter, tieCounter, totalGames;
	
	/**
	 * Talker constructor
	 */
	public Talker() {
		input = new Scanner(System.in);
	}
	
	/**
	 * Prints out the instructions
	 */
	public void getInstructions() {
		System.out.println("********************************************");
		System.out.println("*           Welcome to RPSLK!              *");
		System.out.println("*   This game is similar to rock, paper,   *");
		System.out.println("*   scissors in except there two extra     *");
		System.out.println("*       options: lizard and spock.         *");
		System.out.println("********************************************\n");
		System.out.println("'r' = Rock"
				+ "\n'p' = Paper"
				+ "\n's' = Scissors"
				+ "\n'l' = Lizard"
				+ "\n'k' = Spock"
				+ "\n'z' = Quit\n");
	}
	
	/**
	 * Asks the user if they want to automate the user and returns their choice
	 * @return Returns the choice of whether to automate the user
	 */
	public String getAutomateChoice() {
		String automateChoice;
		System.out.print("Would you like to automate the user? (y/n): ");
		String userChoice = input.next();
		if (userChoice.equals("y") || userChoice.equals("n"))
			automateChoice = userChoice;
		else {
			while (!userChoice.equals("y") && !userChoice.equals("n")) {
				System.out.println("Please enter a valid input!. Automate the user? (y/n): ");
				userChoice = input.next();
			}
			automateChoice = userChoice;
		}
		return automateChoice;
	}
	
	/**
	 * Asks the user if they want to have AI
	 * @return Returns the choice of whether to have AI or not
	 */
	public String getThinkerChoice() {
		String thinkerChoice;
		System.out.print("Would you like the computer to have AI? (y/n): ");
		String userChoice = input.next();
		if (userChoice.equals("y") || userChoice.equals("n"))
			thinkerChoice = userChoice;
		else {
			while (!userChoice.equals("y") && !userChoice.equals("n")) {
				System.out.println("Please enter a valid input!. Have AI? (y/n): ");
				userChoice = input.next();
			}
			thinkerChoice = userChoice;
		}
		return thinkerChoice;
	}
	
	/**
	 * Gets a manual throw
	 * @return Returns the user's choice
	 */
	public String getManualThrow() {	
		System.out.print("Please input your throw: ");
		String firstThrow = input.next();
		while (!(firstThrow.equals("r")
				|| firstThrow.equals("p")
				|| firstThrow.equals("s")
				|| firstThrow.equals("l")
				|| firstThrow.equals("k")
				|| firstThrow.equals("z"))) {
			System.out.println("You have not entered a valid choice.");
			System.out.print("Please enter a valid throw: ");
			firstThrow = input.next();
		}
		return firstThrow;
	}
	
	/**
	 * print out the computer choice
	 * @param computerInput This is what the computer inputted
	 */
	public void sayComputerChoice(String computerInput) {
		System.out.println("The computer chose: " + computerInput);
	}
	
	/**
	 * Says the winner
	 * @param winner Whoever won gets passed in
	 */
	public void sayWinner(int winner) {
		if (winner == 4) {
			for (int i = 0; i < myList.size(); i++) {
				if ((Integer)myList.get(i) == 1)
					playerWinCounter++;
				else if ((Integer)myList.get(i) == 2)
					compWinCounter++;
				else
					tieCounter++;
			}
			totalGames = playerWinCounter + compWinCounter + tieCounter;
			System.out.println("\n\nThe game was played " + totalGames + " times.");
			System.out.print("The player won " + playerWinCounter + " times or ");
			System.out.printf("%.2f",(float)playerWinCounter / totalGames * 100);
			System.out.println("% of the time");
			System.out.print("The computer won " + compWinCounter + " times or ");
			System.out.printf("%.2f",(float)compWinCounter / totalGames * 100);
			System.out.println("% of the time");
			System.out.print("The player and computer tied " + tieCounter + " times or ");
			System.out.printf("%.2f",(float)tieCounter / totalGames * 100);
			System.out.println("% of the time");
		}
		else if(winner == 3) { 
			myList.add(3);
			System.out.println("There is a tie!");
		}
		else if (winner == 2) {
			myList.add(2);
			System.out.println("The computer has won!");
		}
		else if (winner == 1) { 
			myList.add(1);
			System.out.println("The player has won!");
		}
		else
			System.out.println("Error");
		
	}
	
	/**
	 * Says the winners for the automated users
	 * @param winners Passes in the array of winners 
	 */
	public void sayWinners(int[] winners) {
		int player = 0, computer = 0, tie = 0;
		int totalElements = winners.length;
		for (int element : winners) {
			if (element == 1)
				player++;
			else if (element == 2)
				computer++;
			else
				tie++;
		}
		
		System.out.print(totalElements + " games were played."
				+ "\nThe player won " + player + " games or ");
		System.out.printf("%.2f", (float)player/totalElements * 100);
		System.out.println("% of the time.");
		System.out.print("The computer won " + computer + " games or ");
		System.out.printf("%.2f", (float)computer/totalElements * 100);
		System.out.println("% of the time.");
		System.out.print("There were " + tie + " tied games or ");
		System.out.printf("%.2f", (float)tie/totalElements * 100);
		System.out.println("% of the time.");
		
	}

	/**
	 * Prints out which choices the user 
	 * @return Returns the option user chose
	 */
	public int getUserOptions() {
		int userOption = 0;
		while (!(userOption == 1 
				|| userOption == 2
				|| userOption == 3
				|| userOption == 4
				|| userOption == 5)) {
			System.out.println("1. Repeater: User throws same choice everytime. "
					+ "\n2. Rotater: User throws cyclically. "
					+ "\n3. Reflector: User throws whatever the system threw last round."
					+ "\n4. Randomizer: User throws randomly."
					+ "\n5. Mixer: User throws a different strategy every 5 throws.");
			System.out.println("Please choose a user (1-5): ");
			userOption = input.nextInt();
		}
		return userOption;
	}
	
	/**
	 * Gets the number of rounds the user wants to play
	 * @return Returns the number of rounds being played
	 */
	public int getUserRounds() {
		int userRounds = 0;
		while (userRounds <= 0) {
			try {
				System.out.print("How many rounds do you want the user to play: ");
				userRounds = input.nextInt();
				if (userRounds <= 0)
					System.out.println("Please input a postive integer");
			} catch (InputMismatchException e) {
				System.out.println("Please input a positive integer");
				input.next();
			}
		}
		return userRounds;
	}	
}
