import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

/**
 * This class removes items based on which items are selected. It will be disabled
 * when there are no items on the scene. This can also remove more than one at a
 * time.
 * @author Nicholas Liu (nl2523)
 * @version 1.0
 *
 */
public class RemovingAction extends AbstractAction {

	private SceneComponent scene;
	private ArrayList<DrawingAction> listOfActions = new ArrayList<DrawingAction>();
	
	/**
	 * Constructor for the removingAction object
	 * @param scene This is the scene from the JFrame
	 */
	public RemovingAction(SceneComponent scene) {
		this.scene = scene;
		setEnabled(false);
	}
	
	/**
	 * This adds the arraylist of drawingActions on the field
	 * @param otherAction This is an arraylist holding the drawingactions
	 */
	public void addDrawingAction(ArrayList<DrawingAction> otherAction) {
		listOfActions.addAll(otherAction);
	}
	
	/**
	 * This method is called when the remove button is clicked
	 * @param e This is the ActionEvent that calls this method.
	 */
	public void actionPerformed(ActionEvent e) {
		
		ArrayList<SceneShape> listOfComponents = new ArrayList<SceneShape>();
		listOfComponents = scene.getSelected();
		for (SceneShape i : listOfComponents) {
			if (i instanceof HouseShape) {
				for (int x = 0; x < listOfActions.size(); x++) {
					DrawingAction holder = listOfActions.get(x);
					if (holder.getShape() instanceof HouseShape) {
						holder.setEnabled(true);
						holder.remove(i);
					}
				}
			} else if (i instanceof CarShape) {
				for (int x = 0; x < listOfActions.size(); x++) {
					DrawingAction holder = listOfActions.get(x);
					if (holder.getShape() instanceof CarShape) {
						holder.setEnabled(true);
						holder.remove(i);
					}
				}
			} else if (i instanceof SnowmanShape) {
				for (int x = 0; x < listOfActions.size(); x++) {
					DrawingAction holder = listOfActions.get(x);
					if (holder.getShape() instanceof SnowmanShape) {
						holder.setEnabled(true);
						holder.remove(i);
					}
				}
			}
			
		}
		scene.removeSelected();
		boolean noneLeft = true;
		for (int x = 0; x < listOfActions.size(); x++) {
			if (listOfActions.get(x).getCounter() > 0)
				noneLeft = false;
		}
		if (noneLeft) {
			setEnabled(false);
		}
	}
}
