import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * A program that allows users to edit a scene composed
 * of items.
 * Since this is the client, there is little code as it will
 * just create a CreateGUI class and call the run method in that.	 
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class SceneEditor
{
   public static void main(String[] args)
   {
	   CreateGUI myGUI = new CreateGUI();
	   myGUI.run();
   }
}

