import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/**
 * A component that shows a scene composed of shapes.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 *
 */
public class SceneComponent extends JComponent
{
   /**
    * This is the SceneComponent constructor
    */
   public SceneComponent()
   {
      shapes = new ArrayList<SceneShape>();

      addMouseListener(new
         MouseAdapter()
         {
            public void mousePressed(MouseEvent event)
            {
               mousePoint = event.getPoint();

               for (SceneShape s : shapes)
               {
                  if (s.contains(mousePoint))
                  {   if(!s.isSelected())
                         s.setSelected(true);
			      }
			      else
					 s.setSelected(false);
               }
               repaint();
            }
         });

      addMouseMotionListener(new
         MouseMotionAdapter()
         {
            public void mouseDragged(MouseEvent event)
            {
               Point lastMousePoint = mousePoint;

               mousePoint = event.getPoint();
               for (SceneShape s : shapes)
               {
                  if (s.isSelected())
                  {
					  double dx = mousePoint.getX() - lastMousePoint.getX();
					  double dy = mousePoint.getY() - lastMousePoint.getY();
                      s.translate((int) dx, (int) dy);

                  }
		       }
               repaint();
            }
         });
   }

   /**
      Adds an shape to the scene.
      @param s the shape to add
   */
   public void add(SceneShape s)
   {
      shapes.add(s);
      repaint();
   }
   
   /**
    * Returns the arraylist of SceneShapes that are selected
    * @return Returns an Arraylist of SceneShapes
    */
   public ArrayList<SceneShape> getSelected() {
	   ArrayList<SceneShape> selectedShape = new ArrayList<SceneShape>();
	   for (int i = shapes.size() - 1; i >= 0; i--) {
		   SceneShape s = shapes.get(i);
		   if (s.isSelected())
			   selectedShape.add(s);
	   }
	   return selectedShape;
   }

   /**
    * Removes all selected shapes from the scene.
    */
   public void removeSelected()
   {
      for (int i = shapes.size() - 1; i >= 0; i--)
      {
         SceneShape s = shapes.get(i);
         if (s.isSelected()) shapes.remove(i);
      }
      repaint();
   }

   /**
    * Paints the shapes on the field
    */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      for (SceneShape s :shapes)
      {
         s.draw(g2);
         if (s.isSelected())
            s.drawSelection(g2);
      }
   }

   private ArrayList<SceneShape> shapes;
   private Point mousePoint;

}