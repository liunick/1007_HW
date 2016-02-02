import java.awt.*;
import java.awt.geom.*;

/**
   A car shape.
   Modified to remove x, y, and translate.
*/
public class CarShape extends CompoundShape
{
   /**
      Constructs a car shape.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public CarShape(int x, int y, int width)
   {
      super(x, y);
      this.width = width;
      UNIT  = width/6;
      
	  //Y Dependencies
	  roofCompartmentTop = y;
	  roofCompartmentDepth = UNIT * 1;
	  roofCompartmentBottom = roofCompartmentTop + roofCompartmentDepth;
	  bodyTop = roofCompartmentBottom;
	  bodyDepth = UNIT * 1;
	  bodyBottom = bodyDepth + bodyTop;
	  wheelTop = bodyBottom;
	  
	  //X Dependencies
	  bodyLeft = x;
	  leftWindowOffset = UNIT * 1;
	  leftWindowLeft = bodyLeft + leftWindowOffset;
	  leftWindowWidth = UNIT * 1;
	  leftWindowRight = leftWindowLeft + leftWindowWidth;
	  roofPanelLeft = leftWindowRight;
	  roofPanelWidth = UNIT * 2;
	  roofPanelRight = roofPanelLeft + roofPanelWidth;
	  rightWindowLeft = roofPanelRight;
	  rightWindowWidth = UNIT * 1;
	  rightWindowRight = rightWindowLeft + rightWindowWidth;
	  bodyWidth = UNIT * 6;
	  bodyRight = bodyLeft + bodyWidth;
	  leftWheelOffset = UNIT * 1;
	  leftWheelLeft = bodyLeft + leftWheelOffset;
	  interwheelOffset = UNIT * 3;
	  rightWheelLeft = leftWheelLeft + interwheelOffset;
	  
	  //Wheel Diameter
	  diameter = 1 * UNIT;
	  
	  //Combines the entire car to a shape
     Rectangle2D.Double body = new Rectangle2D.Double(bodyLeft, bodyTop, bodyWidth, bodyDepth);
     Ellipse2D.Double frontTire
        = new Ellipse2D.Double(leftWheelLeft, wheelTop, diameter, diameter);
     Ellipse2D.Double rearTire
        = new Ellipse2D.Double(rightWheelLeft, wheelTop, diameter, diameter);

     // the bottom of the front windshield
     Point2D.Double r1
        = new Point2D.Double(leftWindowLeft, roofCompartmentBottom);
     // the front of the roof
     Point2D.Double r2
        = new Point2D.Double(roofPanelLeft, roofCompartmentTop);
     // the rear of the roof
     Point2D.Double r3
        = new Point2D.Double(roofPanelRight, roofCompartmentTop);
     // the bottom of the rear windshield
     Point2D.Double r4
        = new Point2D.Double(rightWindowRight, roofCompartmentBottom);
     Line2D.Double frontWindshield
        = new Line2D.Double(r1, r2);
     Line2D.Double roofTop
        = new Line2D.Double(r2, r3);
     Line2D.Double rearWindshield
        = new Line2D.Double(r3, r4);
     
     add(body);
     add(frontTire);
     add(rearTire);
     add(frontWindshield);
     add(roofTop);
     add(rearWindshield);
   }
   
   /**
    * Gets the boundaries of the shape
    * @return Returns the rectangle around the shape
    */
   public Rectangle getBounds()
   {
      return (new Rectangle (getX(),getY(), width,width/2));

   }
   private final double UNIT;
   private double roofCompartmentTop, roofCompartmentDepth, roofCompartmentBottom, bodyTop, bodyDepth,
   		bodyBottom, wheelTop, bodyLeft, leftWindowOffset, leftWindowLeft, leftWindowWidth, leftWindowRight, roofPanelLeft,
   		roofPanelWidth, roofPanelRight, rightWindowLeft, rightWindowWidth, rightWindowRight, bodyWidth, bodyRight,
   		leftWheelOffset, leftWheelLeft, interwheelOffset, rightWheelLeft, diameter;
   private int width;
}