package fr.lusis.tools.subFSM.handlers;

import java.awt.event.MouseEvent;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxConnectionHandler;
import com.mxgraph.util.mxPoint;

import fr.lusis.tools.subFSM.editor.SubFSMGraph;

public class SubFSMConnectionHandler extends mxConnectionHandler {

	public SubFSMConnectionHandler(mxGraphComponent graphComponent) {
		super(graphComponent);
	}

	@Override
	public Object createTargetVertex(MouseEvent e, Object source)
	{
		// If vertex template is not null, always use template
		SubFSMGraph graph = (SubFSMGraph) graphComponent.getGraph();
		if (graph.getVertexTemplate() != null)
			source = graph.getVertexTemplate();
//		mxGraph graph = graphComponent.getGraph();
		Object clone = graph.cloneCells(new Object[] { source })[0];
		mxIGraphModel model = graph.getModel();
		mxGeometry geo = model.getGeometry(clone);

		if (geo != null)
		{
			mxPoint point = graphComponent.getPointForEvent(e);
			geo.setX(graph.snap(point.getX() - geo.getWidth() / 2));
			geo.setY(graph.snap(point.getY() - geo.getHeight() / 2));
		}

		return clone;
	}
}
