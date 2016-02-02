/**
 * Worker for the RoadLoadForce. Does all the calculations.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class Worker {
	
	/**
	 * coefDragArea, coefRollingResis, velocity, mass are required for the equation
	 * RHO and GRAVITY and constants
	 * coefDragArea: m^2
	 * coefRollingResis: no units
	 * velocity: m/s
	 * mass: kg
	 * RHO: no units kg/m^3
	 * GRAVITY: m/s^2
	 */
	private double coefDragArea, coefRollingResis, velocity, mass;
	private final double RHO = 1.3, GRAVITY = 9.8;
	
	/**
	 * Worker Constructor with no parameters
	 */
	public Worker() {
		
	}
	
	/**
	 * Worker Constructor with coefDragArea, coefRollingResis, velocity, mass as parameters
	 * @param coefDragArea Coefficient of Drag times Area. Set to local coefDragArea
	 * @param coefRollingResis Coefficient of Rolling Resistance 
	 * @param velocity Velocity of the mass
	 * @param mass Mass of the object
	 */
	public Worker(double coefDragArea, double coefRollingResis, double velocity, double mass) {
		this.coefDragArea = coefDragArea;
		this.coefRollingResis = coefRollingResis;
		this.velocity = velocity;
		this.mass = mass;
	}
	
	/**
	 * Gets the Force
	 * @return Returns the equation with all the values filled
	 */
	public double getForce() {
		return 0.5 * coefDragArea * RHO * Math.pow(velocity, 2) + coefRollingResis * mass * GRAVITY;
	}
}
