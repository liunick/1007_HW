import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is a tester for the Worker RoadLoadForce
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Tester {
	/**
	 * Main method of the Tester
	 * @param args Console input - invalid in this case
	 */
	public static void main(String args[]) {
		
		/*
		 * coefDragArea, coefRollingResis, velocity, mass are all parameter that
		 * the user needs to input
		 * leaveLoop is an exit condition for the upcoming while loop
		 * input is a Scanner object
		 */
		double coefDragArea = 0, coefRollingResis = 0, velocity = 0, mass = 0;
		boolean leaveLoop = true;
		Scanner input = new Scanner(System.in);
		Worker RoadForceCalc;
		
		/*
		 * While loop checks for valid inputs while the actual loop
		 * sets the required values
		 */
		while (leaveLoop) {
			try {		
				System.out.print("Input CdA (m^2): ");
				coefDragArea = input.nextDouble();
				System.out.print("Input CRR: ");
				coefRollingResis = input.nextDouble();
				System.out.print("Input velocity (m/s): ");
				velocity = input.nextDouble();
				System.out.print("Input mass (kg): ");
				mass = input.nextDouble();
				leaveLoop = false;
			} catch (InputMismatchException e) {
				System.out.println("Please input a valid number!");
				input.next();
			}
		}	
		RoadForceCalc = new Worker(coefDragArea, coefRollingResis, velocity, mass);
		System.out.println("Road Load Force is: " + RoadForceCalc.getForce() + " Newtons");
	}
}
