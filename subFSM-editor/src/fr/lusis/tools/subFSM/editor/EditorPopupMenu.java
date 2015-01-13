// Use code from com.mxgraph.examples.swing.editor.EditorPopupMenu.java
// Because there is no way to customize the inherited class
// Customize by modify code directly
// Yuqian YANG
// 01/13/2015

package fr.lusis.tools.subFSM.editor;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorMenuBar;
import com.mxgraph.examples.swing.editor.EditorActions.*;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;

import fr.lusis.tools.subFSM.editor.EditorActions.*;

public class EditorPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4539676857245598340L;

	public EditorPopupMenu(BasicGraphEditor editor)
	{
		boolean selected = !editor.getGraphComponent().getGraph()
				.isSelectionEmpty();

		add(editor.bind(mxResources.get("undo"), new HistoryAction(true),
				"/com/mxgraph/examples/swing/images/undo.gif"));

		addSeparator();

		add(
				editor.bind(mxResources.get("cut"), TransferHandler
						.getCutAction(),
						"/com/mxgraph/examples/swing/images/cut.gif"))
				.setEnabled(selected);
		add(
				editor.bind(mxResources.get("copy"), TransferHandler
						.getCopyAction(),
						"/com/mxgraph/examples/swing/images/copy.gif"))
				.setEnabled(selected);
		add(editor.bind(mxResources.get("paste"), TransferHandler
				.getPasteAction(),
				"/com/mxgraph/examples/swing/images/paste.gif"));

		addSeparator();

		add(
				editor.bind(mxResources.get("delete"), mxGraphActions
						.getDeleteAction(),
						"/com/mxgraph/examples/swing/images/delete.gif"))
				.setEnabled(selected);

		addSeparator();
		
		// Set as a entry node
		add(editor.bind(mxResources.get("setAsEntry"), new SetSpecialNodeAction(SetSpecialNodeAction.NodeType.ENTRY_NODE)));
		// Set as a final node
		add(editor.bind(mxResources.get("setAsExit"), new SetSpecialNodeAction(SetSpecialNodeAction.NodeType.EXIT_NODE)));
		// Restore default color
		add(editor.bind(mxResources.get("defaultStyle"), new SetSpecialNodeAction(SetSpecialNodeAction.NodeType.NORMAL_NODE)));
		
		addSeparator();

		// Creates the format menu
		JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));

		EditorMenuBar.populateFormatMenu(menu, editor);

		// Creates the shape menu
		menu = (JMenu) add(new JMenu(mxResources.get("shape")));

		EditorMenuBar.populateShapeMenu(menu, editor);

		addSeparator();

		add(
				editor.bind(mxResources.get("edit"), mxGraphActions
						.getEditAction())).setEnabled(selected);

		addSeparator();

		add(editor.bind(mxResources.get("selectVertices"), mxGraphActions
				.getSelectVerticesAction()));
		add(editor.bind(mxResources.get("selectEdges"), mxGraphActions
				.getSelectEdgesAction()));

		addSeparator();

		add(editor.bind(mxResources.get("selectAll"), mxGraphActions
				.getSelectAllAction()));
	}
}
