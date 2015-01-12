package fr.lusis.tools.subFSM.shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxBasicShape;
import com.mxgraph.view.mxCellState;

public class Component extends mxBasicShape {

	public Shape createShape(mxGraphics2DCanvas canvas, mxCellState state)
	{
		Rectangle temp = state.getRectangle();
		int x = temp.x;
		int y = temp.y;
		int width = temp.width;
		int height = temp.height;
		float w = width / 10;
		float h = height / 6;

		GeneralPath path = new GeneralPath();

		path.moveTo(x, y + h);
		path.lineTo(x + w * 2, y + h);
		path.lineTo(x + w * 2, y + h * 2);
		path.lineTo(x, y + h * 2);
		path.lineTo(x, y + h);
		
		path.moveTo(x, y + h * 4);
		path.lineTo(x + w * 2, y + h * 4);
		path.lineTo(x + w * 2, y + h * 5);
		path.lineTo(x, y + h * 5);
		path.lineTo(x, y + h * 4);
		
		path.moveTo(x + w, y);
		path.lineTo(x + width, y);
		path.lineTo(x + width, y + height);
		path.lineTo(x + w, y + height);
		path.lineTo(x + w, y + h * 5);
		path.moveTo(x + w, y + h * 4);
		path.lineTo(x + w, y + h * 2);
		path.moveTo(x + w, y + h);
		path.lineTo(x + w, y);
		
		path.closePath();

		return path;
	}
	
}
