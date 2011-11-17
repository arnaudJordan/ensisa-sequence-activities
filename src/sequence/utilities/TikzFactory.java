package sequence.utilities;

import java.awt.Component;

import sequence.model.activity.Activity;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class TikzFactory {
	final static String NEW_LINE = System.getProperty("line.separator");
	final static int HSCALE = 10;
	final static int VSCALE = 2;
	
	public static String ActivityToTikz(ActivityView activityView)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\draw[draw=none,fill=color"+((Activity) activityView.getModel()).getId()+"] (0,.8) rectangle ("+activityView.getWidth()+",1.1);");
		return sb.toString();
	}
	private static String SequenceActivityToTikz(ActivityView activityView) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\draw[draw=none,fill=color"+((Activity) activityView.getModel()).getId()+"] ("+activityView.getX()/HSCALE+","+activityView.getY()/VSCALE+") rectangle ("+activityView.getWidth()/HSCALE+","+activityView.getHeight()/VSCALE+");");
		return sb.toString();
	}
	public static String SequenceToTikz(SubSequenceView sequenceView)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i <sequenceView.getComponentCount(); i++)
		{
			Component objet = sequenceView.getComponent(i);
			if(objet instanceof ActivityView)
			{
				sb.append(TikzFactory.TikzColor((ActivityView) objet));
				sb.append(NEW_LINE);
			}
		}
		sb.append("\\begin{scope}[yshift=-0]");
		for(int i=0; i <sequenceView.getComponentCount(); i++)
		{
			Component objet = sequenceView.getComponent(i);
			if(objet instanceof ActivityView)
			{
				sb.append(TikzFactory.SequenceActivityToTikz((ActivityView) objet));
				sb.append(NEW_LINE);
			}
		}
		return sb.toString();
	}
	
	
	public static String AddHeader(String s)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\documentclass{article}");
		sb.append(NEW_LINE);
		sb.append("\\usepackage[paperwidth=1000px, paperheight=1000px]{geometry}");
		sb.append(NEW_LINE);
		sb.append("\\usepackage{tikz}");
		sb.append(NEW_LINE);
		sb.append("\\usepackage{subfigure}");
		sb.append(NEW_LINE);
		sb.append("\\usepackage[tightpage,active]{preview}");
		sb.append(NEW_LINE);
		sb.append("\\PreviewEnvironment{tikzpicture}");
		sb.append(NEW_LINE);
		sb.append(TikzLib());
		sb.append(NEW_LINE);
		sb.append("\\begin{document}");
		sb.append(NEW_LINE);
		sb.append("\\begin{figure}");
		sb.append(NEW_LINE);
		sb.append("\\centering");
		sb.append(NEW_LINE);
		sb.append("\\begin{tikzpicture}");
		sb.append(NEW_LINE);
		sb.append(s);
		sb.append(NEW_LINE);
		sb.append("\\end{scope}");
		sb.append(NEW_LINE);
		sb.append("\\end{tikzpicture}");
		sb.append(NEW_LINE);
		sb.append("\\end{figure}");
		sb.append(NEW_LINE);
		sb.append("\\end{document}");
		return sb.toString();
	}
	private static String TikzLib()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\usetikzlibrary{arrows,shapes,snakes,automata,backgrounds,petri}");
		sb.append(NEW_LINE);
		sb.append("\\usetikzlibrary[arrows,backgrounds,fit,decorations.pathmorphing,shapes.geometric,shapes,chains,scopes,positioning,arrows,fit,shadows,shapes.gates.logic.US,trees,positioning,arrows]");
		sb.append(NEW_LINE);
		sb.append("\\usetikzlibrary[arrows,backgrounds,fit,decorations.pathmorphing,shapes.geometric,shapes,chains,scopes,positioning,arrows,fit,shadows,shapes.gates.logic.US,trees,positioning,arrows]");
		return sb.toString();
	}
	private static String TikzColor(ActivityView activityView)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\definecolor{color"+((Activity) activityView.getModel()).getId()+"}{RGB}{"+((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getRed()+","+((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getGreen()+","+((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getBlue()+"});");
		return sb.toString();
	}
}
