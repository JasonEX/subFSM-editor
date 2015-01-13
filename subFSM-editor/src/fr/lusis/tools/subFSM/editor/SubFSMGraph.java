package fr.lusis.tools.subFSM.editor;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

/**
 * A graph that creates new edges from a given template edge.
 */
public class SubFSMGraph extends mxGraph {

	public static final NumberFormat numberFormat = NumberFormat.getInstance();
	
	/**
	 * Holds the edge to be used as a template for inserting new edges.
	 */
	protected Object edgeTemplate;
	
	protected Object vertexTemplate;

	/**
	 * Custom graph that defines the alternate edge style to be used when
	 * the middle control point of edges is double clicked (flipped).
	 */
	public SubFSMGraph()
	{
		setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
//		setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.EntityRelation");
	}

	/**
	 * Sets the edge template to be used to inserting edges.
	 */
	public void setEdgeTemplate(Object template)
	{
		edgeTemplate = template;
	}
	
	public void setVertexTemplate(Object template)
	{
		vertexTemplate = template;
	}
	
	public Object getVertexTemplate()
	{
		return vertexTemplate;
	}

	/**
	 * Prints out some useful information about the cell in the tooltip.
	 */
	public String getToolTipForCell(Object cell)
	{
		String tip = "<html>";
		mxGeometry geo = getModel().getGeometry(cell);
		mxCellState state = getView().getState(cell);

		if (getModel().isEdge(cell))
		{
			tip += "points={";

			if (geo != null)
			{
				List<mxPoint> points = geo.getPoints();

				if (points != null)
				{
					Iterator<mxPoint> it = points.iterator();

					while (it.hasNext())
					{
						mxPoint point = it.next();
						tip += "[x=" + numberFormat.format(point.getX())
								+ ",y=" + numberFormat.format(point.getY())
								+ "],";
					}

					tip = tip.substring(0, tip.length() - 1);
				}
			}

			tip += "}<br>";
			tip += "absPoints={";

			if (state != null)
			{

				for (int i = 0; i < state.getAbsolutePointCount(); i++)
				{
					mxPoint point = state.getAbsolutePoint(i);
					tip += "[x=" + numberFormat.format(point.getX())
							+ ",y=" + numberFormat.format(point.getY())
							+ "],";
				}

				tip = tip.substring(0, tip.length() - 1);
			}

			tip += "}";
		}
		else
		{
			tip += "geo=[";

			if (geo != null)
			{
				tip += "x=" + numberFormat.format(geo.getX()) + ",y="
						+ numberFormat.format(geo.getY()) + ",width="
						+ numberFormat.format(geo.getWidth()) + ",height="
						+ numberFormat.format(geo.getHeight());
			}

			tip += "]<br>";
			tip += "state=[";

			if (state != null)
			{
				tip += "x=" + numberFormat.format(state.getX()) + ",y="
						+ numberFormat.format(state.getY()) + ",width="
						+ numberFormat.format(state.getWidth())
						+ ",height="
						+ numberFormat.format(state.getHeight());
			}

			tip += "]";
		}

		mxPoint trans = getView().getTranslate();

		tip += "<br>scale=" + numberFormat.format(getView().getScale())
				+ ", translate=[x=" + numberFormat.format(trans.getX())
				+ ",y=" + numberFormat.format(trans.getY()) + "]";
		tip += "</html>";

		return tip;
	}
	
	public Object getCellStyleValueMatches(String key, Object cell)
	{
		Map<String, Object> style = getCellStyle(cell);
		return style.get(key);
	}
	
	public boolean isConditionNodeByCheckingStyle(Object cell)
	{
		String value = (String) getCellStyleValueMatches("defaultStyle", cell);
		return value != null && !value.isEmpty() && value.equals("conditionNode");
	}
	
	public boolean isEventNodeByCheckingStyle(Object cell)
	{
		String value = (String) getCellStyleValueMatches("defaultStyle", cell);
		return value != null && !value.isEmpty() && value.equals("eventNode");
	}

	/**
	 * Overrides the method to use the currently selected edge template for
	 * new edges.
	 * 
	 * @param graph
	 * @param parent
	 * @param id
	 * @param value
	 * @param source
	 * @param target
	 * @param style
	 * @return
	 */
	public Object createEdge(Object parent, String id, Object value,
			Object source, Object target, String style)
	{
		if (edgeTemplate != null)
		{
			mxCell edge = (mxCell) cloneCells(new Object[] { edgeTemplate })[0];
			edge.setId(id);

			return edge;
		}

		String defaultEdgeStyle = "edgeStyle=mxEdgeStyle.EntityRelation";
		defaultEdgeStyle = "edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical";
		
		if (isEventNodeByCheckingStyle(source))
			defaultEdgeStyle += ";dashed=1";
		
		return super.createEdge(parent, id, value, source, target, defaultEdgeStyle);
	}
	
}
