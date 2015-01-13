package fr.lusis.tools.subFSM;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.w3c.dom.Document;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorAboutFrame;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

import fr.lusis.tools.subFSM.editor.EditorMenuBar;
import fr.lusis.tools.subFSM.editor.EditorPopupMenu;
import fr.lusis.tools.subFSM.editor.SubFSMGraph;
import fr.lusis.tools.subFSM.shapes.Component;

public class Editor extends BasicGraphEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6145527433631463460L;

	/**
	 * Holds the shared number formatter.
	 * 
	 * @see NumberFormat#getInstance()
	 */
	public static final NumberFormat numberFormat = NumberFormat.getInstance();

	/**
	 * Holds the URL for the icon to be used as a handle for creating new
	 * connections. This is currently unused.
	 */
	public static URL url = null;

	static
	{
		try
		{
			mxResources.add("fr/lusis/tools/subFSM/resources/editor");
		}
		catch (Exception e)
		{
			// ignore
		}
	}
	//Editor.class.getResource("/com/mxgraph/examples/swing/images/connector.gif");

	public Editor()
	{
		this("sub-FSM Editor - LUSIS", new CustomGraphComponent(new SubFSMGraph()));
	}

	/**
	 * 
	 */
	public Editor(String appTitle, mxGraphComponent component)
	{
		super(appTitle, component);
		final mxGraph graph = graphComponent.getGraph();

		// Creates the shapes palette
		EditorPalette shapesPalette = insertPalette(mxResources.get("shapes"));
//		EditorPalette imagesPalette = insertPalette(mxResources.get("images"));
//		EditorPalette symbolsPalette = insertPalette(mxResources.get("symbols"));

		// Sets the edge template to be used for creating new edges if an edge
		// is clicked in the shape palette
		shapesPalette.addListener(mxEvent.SELECT, new mxIEventListener()
		{
			public void invoke(Object sender, mxEventObject evt)
			{
				Object tmp = evt.getProperty("transferable");

				if (tmp instanceof mxGraphTransferable)
				{
					mxGraphTransferable t = (mxGraphTransferable) tmp;
					Object cell = t.getCells()[0];

					if (graph.getModel().isEdge(cell))
					{
						((SubFSMGraph) graph).setEdgeTemplate(cell);
					}
				}
			}

		});

		// Adds some template cells for dropping into the graph
//		shapesPalette
//				.addTemplate(
//						"Container",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/swimlane.png")),
//						"swimlane", 280, 280, "Container");
		shapesPalette
				.addTemplate(
						"sub-FSM",
						new ImageIcon(
								Editor.class
										.getResource("/fr/lusis/tools/subFSM/images/component_cyan.png")),
//						"shape=component;fillColor=#00ffff",
						"subFSM",
						100, 60, "sub-FSM");
//		shapesPalette
//				.addTemplate(
//						"Rounded Rectangle",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/rounded.png")),
//						"rounded=1", 160, 120, "");
		shapesPalette
				.addTemplate(
						"Condition node",
						new ImageIcon(
								Editor.class
										.getResource("/com/mxgraph/examples/swing/images/ellipse.png")),
//						"ellipse", 100, 60, "");
						"conditionNode", 100, 60, "");
		shapesPalette
				.addTemplate(
						"Event node",
						new ImageIcon(
								Editor.class
								.getResource("/fr/lusis/tools/subFSM/images/rectangle_yellow.png")),
//						"fillColor=#ffff00", 100, 60, "");
						"eventNode", 100, 60, "");
//		shapesPalette
//				.addTemplate(
//						"Triangle",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/triangle.png")),
//						"triangle", 120, 160, "");
//		shapesPalette
//				.addTemplate(
//						"Rhombus",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/rhombus.png")),
//						"rhombus", 160, 160, "");
//		shapesPalette
//				.addTemplate(
//						"Horizontal Line",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/hline.png")),
//						"line", 160, 10, "");
//		shapesPalette
//				.addTemplate(
//						"Hexagon",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/hexagon.png")),
//						"shape=hexagon", 160, 120, "");
//		shapesPalette
//				.addEdgeTemplate(
//						"Straight",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/straight.png")),
//						"straight", 120, 120, "");
		shapesPalette
				.addEdgeTemplate(
						"Horizontal Connector",
						new ImageIcon(
								Editor.class
										.getResource("/com/mxgraph/examples/swing/images/connect.png")),
						null, 100, 100, "");
		shapesPalette
				.addEdgeTemplate(
						"Vertical Connector",
						new ImageIcon(
								Editor.class
										.getResource("/com/mxgraph/examples/swing/images/vertical.png")),
						"vertical", 100, 100, "");
		shapesPalette
				.addEdgeTemplate(
						"Entity Relation",
						new ImageIcon(
								Editor.class
										.getResource("/com/mxgraph/examples/swing/images/entity.png")),
						"entity", 100, 100, "");
//		shapesPalette
//				.addEdgeTemplate(
//						"Arrow",
//						new ImageIcon(
//								Editor.class
//										.getResource("/com/mxgraph/examples/swing/images/arrow.png")),
//						"arrow", 120, 120, "");
	}

	/**
	 * 
	 */
	public static class CustomGraphComponent extends mxGraphComponent
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6833603133512882012L;

		/**
		 * 
		 * @param graph
		 */
		public CustomGraphComponent(mxGraph graph)
		{
			super(graph);
			
			// Register custom shape
			mxGraphics2DCanvas.putShape("component", new Component());

			// Sets switches typically used in an editor
//			setPageVisible(true);
			setGridVisible(true);
			setToolTips(true);
			getConnectionHandler().setCreateTarget(true);

			// Loads the defalt stylesheet from an external file
			mxCodec codec = new mxCodec();
			Document doc = mxUtils.loadDocument(Editor.class.getResource(
					"/fr/lusis/tools/subFSM/resources/default-style.xml")
					.toString());
			codec.decode(doc.getDocumentElement(), graph.getStylesheet());

			// Sets the background to white
			getViewport().setOpaque(true);
			getViewport().setBackground(Color.WHITE);
		}

		/**
		 * Overrides drop behaviour to set the cell style if the target
		 * is not a valid drop target and the cells are of the same
		 * type (eg. both vertices or both edges). 
		 */
		public Object[] importCells(Object[] cells, double dx, double dy,
				Object target, Point location)
		{
			if (target == null && cells.length == 1 && location != null)
			{
				target = getCellAt(location.x, location.y);

				if (target instanceof mxICell && cells[0] instanceof mxICell)
				{
					mxICell targetCell = (mxICell) target;
					mxICell dropCell = (mxICell) cells[0];

					if (targetCell.isVertex() == dropCell.isVertex()
							|| targetCell.isEdge() == dropCell.isEdge())
					{
						mxIGraphModel model = graph.getModel();
						model.setStyle(target, model.getStyle(cells[0]));
						graph.setSelectionCell(target);

						return null;
					}
				}
			}

			return super.importCells(cells, dx, dy, target, location);
		}

	}

	
	/**
	 * 
	 */
	@Override
	public void about()
	{
		JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);

		if (frame != null)
		{
			EditorAboutFrame about = new fr.lusis.tools.subFSM.editor.EditorAboutFrame(frame);
			about.setModal(true);

			// Centers inside the application frame
			int x = frame.getX() + (frame.getWidth() - about.getWidth()) / 2;
			int y = frame.getY() + (frame.getHeight() - about.getHeight()) / 2;
			about.setLocation(x, y);

			// Shows the modal dialog and waits
			about.setVisible(true);
		}
	}
	
	@Override
	protected void showGraphPopupMenu(MouseEvent e)
	{
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		EditorPopupMenu menu = new EditorPopupMenu(Editor.this);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}

		mxSwingConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
		mxConstants.W3C_SHADOWCOLOR = "#D3D3D3";

		Editor editor = new Editor();
		JFrame frame = editor.createFrame(new EditorMenuBar(editor));
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

}
