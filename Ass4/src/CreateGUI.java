import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the meat of the class. In this class, I create 3 buttons, one for each shape.
 * Secondly, I create a remove button that will remove all the shapes that are selected. Finally,
 * I add all the buttons onto the frame.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 *
 */
public class CreateGUI {
	
	/**
	 * Constructor for the CreateGUI class
	 */
	public CreateGUI() {
		
	}
	
	/**
	 * Runs the GUI
	 */
	public static void run() {
		createAndShowGUI();
	}
	
	/**
	 * This is the method that adds all the content to the frame
	 * @param pane This is the pane of the container of the JFrame
	 */
	public static void addContentToFrame(Container pane) {
		
		  	
	      final SceneComponent scene = new SceneComponent();
	      ArrayList<DrawingAction> otherActions = new ArrayList<DrawingAction>();
	      
	      /*
	       * These are all the DrawingAction objects for the House, car and snowman
	       */
	      DrawingAction drawHouse = new DrawingAction(scene,new HouseShape(20, 20, 50));
	      drawHouse.putValue(Action.NAME, "House");
	      JButton houseButton = new JButton(drawHouse);
	      otherActions.add(drawHouse);
	      
	      DrawingAction drawCar = new DrawingAction(scene, new CarShape(20, 20, 50));
	      drawCar.putValue(Action.NAME, "Car");
	      JButton carButton = new JButton(drawCar);
	      otherActions.add(drawCar);
	      
	      DrawingAction drawSnowman = new DrawingAction(scene, new SnowmanShape(20, 20, 50));
	      drawSnowman.putValue(Action.NAME, "Snowman");
	      JButton snowmanButton = new JButton(drawSnowman);
	      otherActions.add(drawSnowman);

	      /*
	       * This is the remove button object
	       */
	      RemovingAction removeItems = new RemovingAction(scene);
	      removeItems.putValue(Action.NAME, "Remove");
	      JButton removeButton = new JButton(removeItems);
	      
	      /*
	       * These provide the settings for the different Action objects
	       */
	      drawHouse.setAnotherAction(otherActions);
	      drawCar.setAnotherAction(otherActions);
	      drawSnowman.setAnotherAction(otherActions);
	      drawHouse.setRemove(removeItems);
	      drawCar.setRemove(removeItems);
	      drawSnowman.setRemove(removeItems);
	      removeItems.addDrawingAction(otherActions);
	      
	      /*
	       * These addds all the buttons onto the panel
	       */
	      JPanel buttons = new JPanel();
	      buttons.add(houseButton);
	      buttons.add(carButton);
	      buttons.add(snowmanButton);
	      buttons.add(removeButton);

	      /*
	       * Adds the scene and the button to the pane
	       */
	      pane.add(scene, BorderLayout.CENTER);
	      pane.add(buttons, BorderLayout.NORTH);
	}
	
	/**
	 * Creates and shows the GUI
	 */
	public static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		addContentToFrame(frame.getContentPane());
		
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}