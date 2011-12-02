package sequence.utilities;

import java.awt.Component;

import sequence.model.activity.Activity;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.subSequence.SubSequenceView;

public class TikzFactory {
	final static String NEW_LINE = System.getProperty("line.separator");
	final static double HSCALE = 0.1;
	final static double VSCALE = 0.2;
	final static int DefaultHscale = 4;
	final static int DefaultVscale = 10;
	final static int PDFHeight = 800;
	final static int PDFWidth = 600;
	final static int HMARGIN = 60;
	final static int VMARGIN = 40;
	
	public static String ActivityToTikz(ActivityView activityView)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\draw[draw=none,fill="+((Activity) activityView.getModel()).getId()+"] (0,.8) rectangle ("+activityView.getWidth()+",1.1);");
		return sb.toString();
	}
	private static String SequenceActivityToTikz(ActivityView activityView, SubSequenceView sequenceView) {
		StringBuilder sb = new StringBuilder();
		float Hscale=4;//sequenceView.getWidth()/(PDFWidth-HMARGIN);
		float Vscale=1;//sequenceView.getHeight()/(PDFHeight-VMARGIN);
		sb.append("\\draw[draw=none,fill=color"+((Activity) activityView.getModel()).getId()+"] ("+activityView.getX()/(Hscale)+","+(sequenceView.getHeight()-activityView.getY())/Vscale+") rectangle ("+(activityView.getX()+activityView.getWidth())/Hscale+","+(sequenceView.getHeight()-activityView.getY()-activityView.getHeight())/Vscale+");");
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
				sb.append(TikzFactory.SequenceActivityToTikz((ActivityView) objet, sequenceView));
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
		sb.append("\\usepackage[paperwidth="+PDFWidth+"px, paperheight="+PDFHeight+"px]{geometry}");
		sb.append(NEW_LINE);
		sb.append("\\usepackage{tikz}");
		sb.append(NEW_LINE);
		sb.append("\\begin{document}");
		sb.append(NEW_LINE);
		sb.append("\\begin{tikzpicture}[yscale="+VSCALE+", xscale="+HSCALE+"] ");
		sb.append(NEW_LINE);
		sb.append(s);
		sb.append(NEW_LINE);
		sb.append("\\end{scope}");
		sb.append(NEW_LINE);
		sb.append("\\end{tikzpicture}");
		sb.append(NEW_LINE);
		sb.append("\\end{document}");
		return sb.toString();
	}
	private static String TikzColor(ActivityView activityView)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\definecolor{color"+((Activity) activityView.getModel()).getId()+"}{RGB}{"+((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getRed()+","+((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getGreen()+","+((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getBlue()+"});");
		return sb.toString();
	}
}
