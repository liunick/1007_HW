import java.awt.*;
import java.awt.geom.*;

/**
 * A shape that manages its selection state.
 * Modified to include x, y, translate and a new constructor.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public abstract class SelectableShape implements SceneShape, Cloneable
{
	/**
	 * Constructor for SelectableShape
	 * @param x This is the x coordinate of the shape
	 * @param y This is the y coordinate of the shape
	 */
   public SelectableShape(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   /**
    * This sets whether a shape is selected or not
    * @param b This is what selected is being set to
    */
   public void setSelected(boolean b)
   {
      selected = b;
   }

   /**
    * Returns whether the object is selected
    * @return Returns the value of selected
    */
   public boolean isSelected()
   {
      return selected;
   }

   /**
    * This draws the box around an object that is selected
    * @param g2 This is the Graphics2D object passed in
    */
   public void drawSelection(Graphics2D g2)
   {
      translate(1, 1);
      draw(g2);
      translate(1, 1);
      draw(g2);
      translate(-2, -2);
      g2.draw(getBounds());
   }

   /**
    * Returns the x coord
    * @return returns the value of x
    */
   public int getX()
   {
      return x;
   }

   /**
    * Returns the y coord
    * @return returns the value of y
    */
   public int getY()
   {
      return y;
   }

   /**
    * This takes in a dx and dy to signify a change in position
    * @param dx This is the change of x applied to the object
    * @param dy This is the change of y applied to the object
    */
   public void translate(double dx, double dy)
   {
      x += dx;
      y += dy;
   }
   private int x;
   private int y;
   private boolean selected;
}