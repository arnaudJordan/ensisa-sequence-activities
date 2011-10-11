package sequence.utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import sequence.model.Action;
import sequence.model.Activity;
import sequence.model.AnatomicStructure;
import sequence.model.Instrument;
import sequence.model.UsedInstruments;

public class ColorFactory {

	private List<Action> actions;
	private List<AnatomicStructure> anatomicStructures;
	private List<UsedInstruments> usedInstruments;
	
	public ColorFactory(List<Activity> activities) {
		actions = new ArrayList<Action>();
		anatomicStructures = new ArrayList<AnatomicStructure>();
		usedInstruments = new ArrayList<UsedInstruments>();
		for(Activity current : activities) {
			if(!actions.contains(current.getAction()))
				actions.add(current.getAction());
			if(!anatomicStructures.contains(current.getTreatedStructure()))
				anatomicStructures.add(current.getTreatedStructure());
			if(!usedInstruments.contains(current.getUsedInstrument()))
				usedInstruments.add(current.getUsedInstrument());
		}
	}
	
	public Color createColor(Activity activity) {
		int red = 255 / actions.size() * actions.indexOf(activity.getAction());
		int green = 255 / anatomicStructures.size() * anatomicStructures.indexOf(activity.getTreatedStructure());
		int blue = 255 / usedInstruments.size() * usedInstruments.indexOf(activity.getUsedInstrument());
		return new Color(red, green, blue);
	}
}
