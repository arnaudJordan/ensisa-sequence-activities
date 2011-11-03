package sequence.utilities;

import sequence.model.activity.Activity;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;

public class SVGFactory {
	public static String ActivityToSVG(ActivityView activityView)
	{
		Activity activity = (Activity) activityView.getModel();
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + activityView.getWidth() + "\" height=\"" + activityView.getHeight() + "\">");
		sb.append("<title>Activity : "+ activity.getId() +"</title>");
		sb.append("<rect width=\""+activityView.getWidth()+"\" height=\""+activityView.getHeight()+"\" x=\"0\" y=\"0\" fill=\"#"+Integer.toHexString(((ActivityRenderingModel) activityView.getRenderingModel()).getColor().getRGB()-0xff000000)+"\" />");
		sb.append("</svg>");
		return sb.toString();
	}
}
