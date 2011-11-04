package sequence.utilities;

import java.awt.Component;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.ui.component.activity.ActivityContractedRenderer;
import sequence.ui.component.activity.ActivityRenderer;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.sequence.SequenceView;

public class SVGFactory {
	final static String NEW_LINE = System.getProperty("line.separator");
	public static String ActivityToSVG(ActivityView activityView)
	{
		Activity activity = (Activity) activityView.getModel();
		StringBuilder sb = new StringBuilder();
		sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + activityView.getWidth() + "\" height=\"" + activityView.getHeight() + "\">");
		sb.append(NEW_LINE);
		sb.append("<title>Activity : "+ activity.getId() +"</title>");
		sb.append(NEW_LINE);
		sb.append(SequenceActivityToSVG(activityView));
		return sb.toString();
	}
	private static String SequenceActivityToSVG(ActivityView activityView)
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append("<g>");
		sb.append(NEW_LINE);
		sb.append("<rect width=\""+activityView.getWidth()+"\" height=\""+activityView.getHeight()+"\" x=\""+activityView.getX()+"\" y=\""+activityView.getY()+"\" fill=\"#"+Integer.toHexString(((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getRGB()-0xff000000)+"\" />");
		sb.append(NEW_LINE);
		if((ActivityContractedRenderer)activityView.getRenderer() instanceof ActivityContractedRenderer)
			if(((ActivityContractedRenderer) activityView.getRenderer()).isContracted())
			{
				sb.append("<line x1=\""+(activityView.getX()+activityView.getWidth()/2-10)+"\" y1=\""+(activityView.getY()+activityView.getHeight())+"\" x2=\""+(activityView.getX()+activityView.getWidth()/2-5)+"\" y2=\""+activityView.getY()+"\" style=\"stroke:white;stroke-width:2\" />");
				sb.append(NEW_LINE);
				sb.append("<line x1=\""+(activityView.getX()+activityView.getWidth()/2+5)+"\" y1=\""+(activityView.getY()+activityView.getHeight())+"\" x2=\""+(activityView.getX()+activityView.getWidth()/2+10)+"\" y2=\""+activityView.getY()+"\" style=\"stroke:white;stroke-width:2\" />");
				sb.append(NEW_LINE);
			}
		sb.append("</g>");
		sb.append(NEW_LINE);
		return sb.toString();
	}
	public static String SequenceToSVG(SequenceView sequenceView)
	{
		Sequence sequence = (Sequence) sequenceView.getModel();
		StringBuilder sb = new StringBuilder();
		sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + sequenceView.getWidth() + "\" height=\"" + sequenceView.getHeight() + "\">");
		sb.append(NEW_LINE);
		sb.append("<title>Sequence : "+ sequence.getWorkflowID() +"</title>");
		sb.append(NEW_LINE);
		for(int i=0; i <sequenceView.getComponentCount(); i++)
		{
			Component objet = sequenceView.getComponent(i);
			if(objet instanceof ActivityView)
			{
				sb.append(SVGFactory.SequenceActivityToSVG((ActivityView) objet));
			}
		}
		return sb.toString();
	}
	public static String AddHeader(String s)
	{
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append(NEW_LINE);
		sb.append(s);
		sb.append("</svg>");
		return sb.toString();
	}
}
