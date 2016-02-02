import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * This is the snowman shape that extends compound shape. The snowman
 * shape is created by 3 equal triangles spanning the width given. There are two arms
 * that jut out of the middle circle at a constant offset set in the code. When a snowman
 * is created, it will be positioned in the average x and average y coords of all the 
 * surrounding entities.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class SnowmanShape extends CompoundShape {

	private int width;
	private final double UNIT;
	
	/**
	 * This is the constructor for the snowman shape
	 * @param x This is the x coord for the shape
	 * @param y This is the y coord for the shape
	 * @param width This is the width of the space given for this
	 */
	public SnowmanShape(int x, int y, int width) {
		super(x, y);
		this.width = width;
		UNIT = width/3;
		final double ARM_OFFSET = 5;
		double diameter = UNIT;
		double topLeft = x + ((width / 2) - (UNIT / 2));
		double topTop = y;
		Ellipse2D.Double topBall = 
				new Ellipse2D.Double(topLeft, topTop, diameter, diameter);

		topTop = topTop + diameter;
		Ellipse2D.Double midBall = 
				new Ellipse2D.Double(topLeft, topTop, diameter, diameter);
		Point2D.Double armLeftStart = new Point2D.Double(topLeft, topTop + (diameter/2));
		Point2D.Double armRightStart = new Point2D.Double(topLeft + diameter, topTop + (diameter/2));
		Point2D.Double armLeftEnd = new Point2D.Double(x, topTop + (diameter/2) - ARM_OFFSET);
		Point2D.Double armRightEnd = new Point2D.Double(x + width, topTop + (diameter/2) - ARM_OFFSET);
		
		topTop = topTop + diameter;
		Ellipse2D.Double botBall = 
				new Ellipse2D.Double(topLeft, topTop, diameter, diameter);
		
		
		Line2D.Double armLeft = new Line2D.Double(armLeftStart, armLeftEnd);
		Line2D.Double armRight = new Line2D.Double(armRightStart, armRightEnd);
		
		add(topBall);
		add(midBall);
		add(botBall);
		add(armLeft);
		add(armRight);
		
	}

	/**
	 * This returns the boundary around this object
	 * @return Returns the Rectangle object surround the snowman
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),width, width);
	}

}
