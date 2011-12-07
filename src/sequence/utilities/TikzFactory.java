package sequence.utilities;

import java.awt.Component;

import sequence.model.activity.Activity;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;

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
	
	public static String ActivityToTikz(View view)
	{
		StringBuilder sb = new StringBuilder();
		int id=0;
		if(view.getModel() instanceof Activity)
			id=((Activity) view.getModel()).getId();
		sb.append("\\draw[draw=none,fill="+id+"] (0,.8) rectangle ("+view.getWidth()+",1.1);");
		return sb.toString();
	}
	private static String SequenceActivityToTikz(View elementView, View containerView) {
		StringBuilder sb = new StringBuilder();
		float Hscale=4;//sequenceView.getWidth()/(PDFWidth-HMARGIN);
		float Vscale=1;//sequenceView.getHeight()/(PDFHeight-VMARGIN);
		int id=0;
		if(elementView.getModel() instanceof Activity)
			id=((Activity) elementView.getModel()).getId();
		sb.append("\\draw[draw=none,fill=color"+id+"] ("+elementView.getX()/(Hscale)+","+(containerView.getHeight()-elementView.getY())/Vscale+") rectangle ("+(elementView.getX()+elementView.getWidth())/Hscale+","+(containerView.getHeight()-elementView.getY()-elementView.getHeight())/Vscale+");");
		return sb.toString();
	}
	public static String SequenceToTikz(View view)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i <view.getComponentCount(); i++)
		{
			Component objet = view.getComponent(i);
			if(objet instanceof View)
			{
				sb.append(TikzFactory.TikzColor((ActivityView) objet));
				sb.append(NEW_LINE);
			}
		}
		sb.append("\\begin{scope}[yshift=-0]");
		for(int i=0; i <view.getComponentCount(); i++)
		{
			Component objet = view.getComponent(i);
			if(objet instanceof View)
			{
				sb.append(TikzFactory.SequenceActivityToTikz((View) objet, view));
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
