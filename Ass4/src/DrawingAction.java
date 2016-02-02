import javax.swing.*;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This is a class that creates DrawingAction objects. It extends abstract action. 
 * It also has an action listener attached to it that will get called when the button
 * is clicked. 
 * I have set the maximum number of items for one entity to be 10. The button will be
 * disabled if there are 10 of the same entity on the field but will be reenabled once
 * at least one of those entities have been removed.
 * To deal with the snowman, since I need to make sure that it will not overlap with any
 * other shape, I convered all the other shapes into GeneralPaths. This way, I can use the
 * contains method which returns a boolean whether a tested point is within another shape.
 * For the most part, this method is very accurate in how it tests. There is one small exception
 * however. Since the contains method checks based on whether the point is within a shape added to
 * path, the roof of the house is not checked within this contain method because it is simply to lines
 * connecting at a single point and not based off a shape. Therefore, GeneralPath cannot "check" whether
 * a point is in the roof the house shape.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 */
public class DrawingAction extends AbstractAction {
	private final int WIDTH = 50;
	private SceneComponent scene;
	private static final int LIMIT = 10;
	private int counter = 0;
	private SelectableShape newShape;
	private ArrayList<DrawingAction> otherAction;
	private ArrayList<SelectableShape> listOfShapes;
	private RemovingAction removeAction;
	
	/**
	 * This is the constructor for DrawingAction
	 * @param scene This is the scene component for the frame
	 * @param newShape This is the kind of selectable shape that the DrawingAction
	 * is refering to
	 */
	public DrawingAction(SceneComponent scene, SelectableShape newShape) {
		this.scene = scene;
		this.newShape = newShape;
		listOfShapes = new ArrayList<SelectableShape>();
	}
	
	/**
	 * This removes one of the SceneShapes from the list that holds it
	 * @param removedShape This is the kind shape that is being removed
	 */
	public void remove(SceneShape removedShape) {
		counter--;
		int indexOfRemove = listOfShapes.indexOf(removedShape);
		listOfShapes.remove(indexOfRemove);
	}
	
	/**
	 * This returns the list of shapes that are SelectableShapes
	 * @return Returns the list of SelectableShapes
	 */
	public ArrayList<SelectableShape> getListOfShapes() {
		return listOfShapes;
	}
	
	/**
	 * Returns the shape of the DrawingAction
	 * @return Returns the SelectableShape of the DrawingAction
	 */
	public SelectableShape getShape() {
		return newShape;
	}
	
	/**
	 * Sets otheractions
	 * @param otherAction This is the arraylist of the other actions
	 */
	public void setAnotherAction(ArrayList<DrawingAction> otherAction) {
		this.otherAction = otherAction;
	}
	
	/**
	 * This sets the remove action
	 * @param otherAction This is the action to be set
	 */
	public void setRemove(RemovingAction otherAction) {
		removeAction = otherAction;
	}
	
	/**
	 * Returns the counter
	 * @return Returns the value of counter
	 */
	public int getCounter() {
		return counter;
	}
	
	/**
	 * This is called whenever the button is pressed
	 * @param e This is the actionevent that causes this method to be called
	 */
	public void actionPerformed(ActionEvent e) {
		boolean snowmanAdded = false;
		if (newShape instanceof HouseShape) {
			newShape = new HouseShape(20, 20, WIDTH);
		} else if (newShape instanceof CarShape) {
			newShape = new CarShape(20, 20, WIDTH);
		} else if (newShape instanceof SnowmanShape) {
			snowmanAdded = true;
		}
		if (counter < LIMIT && isEnabled()) {
			if (snowmanAdded) {
				int xTotal = 0, yTotal = 0, totalComps = 0;
				//Find totalComps, xTotal, and yTotal
				for (DrawingAction i : otherAction) {
					for (SelectableShape j : i.getListOfShapes()) {
						totalComps++;
						xTotal += j.getX();
						yTotal += j.getY();
					}
				}
				boolean conflicting = false;
				System.out.println(totalComps);
				if (totalComps == 0)
					newShape = new SnowmanShape(20, 20, WIDTH);
				else {
					int xAvg = xTotal/totalComps, yAvg = yTotal/totalComps;
					for (DrawingAction i : otherAction) {
						for (SelectableShape j : i.getListOfShapes()) {
							for (int x = xAvg; x < xAvg + WIDTH; x++) {
								for (int y = yAvg; y < yAvg + WIDTH; y++) {
									Point tempPoint = new Point(x, y);
									if (j.contains(tempPoint)) {
										conflicting = true;
									}
								}
							}
						}
					}
					newShape = new SnowmanShape(xAvg, yAvg, 50);
				}
				if (!conflicting) {
					listOfShapes.add(newShape);
					scene.add(newShape);
					counter++;
				}
			} else {
				scene.add(newShape);
				listOfShapes.add(newShape);
				counter++;
			}

			//Check if the remove button should be enabled
			for (DrawingAction i : otherAction) {
				if (this.counter + i.getCounter() > 0) {
					removeAction.setEnabled(true);
				}
			}
			
			//Check if it should be disabled
			if (counter == LIMIT)
				setEnabled(false);
		}
	}
}