package morphology.app;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class SwitchButton {
	
	public static void switchImage() {
/*
 * Ändert die Verbindungsanzeige, indem das vorderste nach hinten geschoben wird.
 */
		ObservableList<Node> xy = MakeBoxes.connectionStack.getChildren();
		if (xy.size() > 1) {
			 Node topNode = xy.get(xy.size()-1);
			   topNode.toBack();
		}  		
	}

}
