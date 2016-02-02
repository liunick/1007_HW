import java.awt.*;
import java.awt.geom.*;

/**
 * A house shape.
 * Modified to remove x, y, and translate.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class HouseShape extends CompoundShape
{
   /**
      Constructs a house shape.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public HouseShape(int x, int y, int width)
   {
      super(x, y);
      this.width = width;
      int a = getX();
      int b = getY();

      Rectangle2D.Double base
         = new Rectangle2D.Double(a, b + width, width, width);

      // the left bottom of the roof
      Point2D.Double r1
         = new Point2D.Double(a, b + width);
      // the top of the roof
      Point2D.Double r2
         = new Point2D.Double(a + width / 2, b);
      // the right bottom of the roof
      Point2D.Double r3
         = new Point2D.Double(a + width, b + width);

      Line2D.Double roofLeft
         = new Line2D.Double(r1, r2);
      Line2D.Double roofRight
         = new Line2D.Double(r2, r3);
      add(base);
      add(roofLeft);
      add(roofRight);
   }

   /**
    * This returns the bounds of the house
    * @return Returns the Rectangle object surrounding the house
    */
   public Rectangle getBounds()
   {
	   return new Rectangle(getX(),getY(),width, width);
   }
   private int width;
}