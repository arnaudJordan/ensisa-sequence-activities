package sequence.utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sequence.model.Sequence;
import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.model.activity.AnatomicStructure;
import sequence.model.activity.UsedInstruments;

/**
 * A factory for creating Color of each <code>ActivityView</code>.
 * 
 * @see sequence.ui.component.activity.ActivityView
 */
public class ColorFactory {

	/** The actions. */
	private List<Action> actions;
	
	/** The anatomic structures. */
	private List<AnatomicStructure> anatomicStructures;
	
	/** The used instruments. */
	private List<UsedInstruments> usedInstruments;
	
	/**
	 * Instantiates a new color factory.
	 *
	 * @param sequence the sequence
	 */
	public ColorFactory(Sequence sequence) {
		actions = new ArrayList<Action>();
		anatomicStructures = new ArrayList<AnatomicStructure>();
		usedInstruments = new ArrayList<UsedInstruments>();
		for(Activity current : sequence) {
			if(!actions.contains(current.getAction()))
				actions.add(current.getAction());
			if(!anatomicStructures.contains(current.getTreatedStructure()))
				anatomicStructures.add(current.getTreatedStructure());
			if(!usedInstruments.contains(current.getUsedInstrument()))
				usedInstruments.add(current.getUsedInstrument());
		}
		Collections.shuffle(actions);
		Collections.shuffle(anatomicStructures);
		Collections.shuffle(usedInstruments);
	}
	
	/**
	 * Creates a new Color object.
	 *
	 * @param activity the activity
	 * @return the color
	 */
	public Color createColor(Activity activity) {
		int red = 255 / actions.size() * actions.indexOf(activity.getAction());
		int green = 255 / anatomicStructures.size() * anatomicStructures.indexOf(activity.getTreatedStructure());
		int blue = 255 / usedInstruments.size() * usedInstruments.indexOf(activity.getUsedInstrument());
		return new Color(red, green, blue);
	}
	
	/**
	 * Sets the alpha.
	 *
	 * @param color the color
	 * @param alpha the alpha
	 * @return the color
	 */
	public static Color setAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
}
