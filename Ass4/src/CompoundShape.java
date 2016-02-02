import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This class extends SelectableShape so that it can use the GeneralPath class.
 * The generalpath class makes it easier to detect whether a point is within the shape
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public abstract class CompoundShape extends SelectableShape {

	private GeneralPath path;
	private boolean hasTransformed = false;
	
	/**
	 * Constructor for compoundshape
	 * @param x This is the x coordinate that the shape will be placed
	 * @param y This is the y coordinate that the shape will be placed
	 */
	public CompoundShape(int x, int y) {
		super(x, y);
		path = new GeneralPath();
	}
	
	/**
	 * Adds an additional shape to the generalpath
	 * @param s This is the shape that is added to the generalpath
	 */
	protected void add(Shape s) {
		path.append(s, false);
	}
	
	/**
	 * This method checks whether the point passed in is within the path.
	 * @param point This is the point that is tested with the path
	 * @return Returns a boolean whether it the point is contained
	 */
	public boolean contains(Point2D point) {
		return path.contains(point);
	}

	/**
	 * This method translates the path a given distance
	 * @param dx The change of x that the path is translated
	 * @param dy The change of y that the path is translated
	 */
	public void translate(double dx, double dy) {
		hasTransformed = true;
		path.transform(
				AffineTransform.getTranslateInstance(dx, dy));
		super.translate(dx, dy);
	}
	
	/**
	 * This method draws the path with the Graphics2D object
	 * @param g2 This is the Graphics2D that allows for the drawing
	 */
	public void draw(Graphics2D g2) {
		g2.draw(path);
	}
}
