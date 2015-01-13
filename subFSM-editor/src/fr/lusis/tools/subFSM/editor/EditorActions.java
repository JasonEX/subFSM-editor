package fr.lusis.tools.subFSM.editor;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.view.mxGraph;

public class EditorActions extends com.mxgraph.examples.swing.editor.EditorActions {

//	@SuppressWarnings("serial")
//	public static class RestoreDefaultStyleAction extends AbstractAction
//	{
//		/**
//		 * 
//		 */
//		protected String key;
//
//		public RestoreDefaultStyleAction()
//		{
//			this.key = "defaultStyle";
//		}
//		/**
//		 * 
//		 * @param key
//		 */
//		public RestoreDefaultStyleAction(String value)
//		{
//			this.key = value;
//		}
//
//		/**
//		 * 
//		 */
//		public void actionPerformed(ActionEvent e)
//		{
//			mxGraph graph = mxGraphActions.getGraph(e);
//
//			if (graph != null && !graph.isSelectionEmpty())
//			{
//				Object[] cells = graph.getSelectionCells();
//				mxIGraphModel model = graph.getModel();
//
//				model.beginUpdate();
//				try
//				{
//					String value = "";
//					for (int i = 0; i < cells.length; i++)
//					{
//						Map<String, Object> style = graph.getCellStyle(cells[i]);
//						value = (String) style.get(key);
//						if (value != null && !value.isEmpty())
//							graph.setCellStyle(value, new Object[]{cells[i]});
//					}
//				}
//				finally
//				{
//					model.endUpdate();
//				}
//			}
//		}
//	}
	
	@SuppressWarnings("serial")
	public static class SetSpecialNodeAction extends AbstractAction
	{
		public enum NodeType
		{
			ENTRY_NODE, EXIT_NODE, NORMAL_NODE
		}
		/**
		 * 
		 */
		protected NodeType type;

		/**
		 * 
		 * @param type
		 */
		public SetSpecialNodeAction(NodeType type)
		{
			this.type = type;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null && !graph.isSelectionEmpty())
			{
				Object[] cells = graph.getSelectionCells();
				mxIGraphModel model = graph.getModel();

				model.beginUpdate();
				try
				{
					String value = "";
					for (int i = 0; i < cells.length; i++)
						if (((mxCell) cells[i]).isVertex())
						{
							switch (type) {
							case ENTRY_NODE:
								graph.setCellStyles("fillColor", "00ff00", new Object[]{cells[i]});
								break;
							case EXIT_NODE:
								graph.setCellStyles("fillColor", "ff0000", new Object[]{cells[i]});
								break;
							case NORMAL_NODE:
								Map<String, Object> style = graph.getCellStyle(cells[i]);
								value = (String) style.get("defaultStyle");
								if (value != null && !value.isEmpty())
									graph.setCellStyle(value, new Object[]{cells[i]});
								break;
							default:
							}
						}
				}
				finally
				{
					model.endUpdate();
				}
			}
		}
	}
}
