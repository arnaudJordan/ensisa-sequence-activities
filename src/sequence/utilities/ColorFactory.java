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

public class ColorFactory {

	private List<Action> actions;
	private List<AnatomicStructure> anatomicStructures;
	private List<UsedInstruments> usedInstruments;
	
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
	
	public Color createColor(Activity activity) {
		int red = 255 / actions.size() * actions.indexOf(activity.getAction());
		int green = 255 / anatomicStructures.size() * anatomicStructures.indexOf(activity.getTreatedStructure());
		int blue = 255 / usedInstruments.size() * usedInstruments.indexOf(activity.getUsedInstrument());
		return new Color(red, green, blue);
	}
	
	public static Color setAlpha(Color color, int alpha) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
}
