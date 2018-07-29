package miniCAD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Rect extends Shape implements Serializable {

	@Override
	public void Draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		g.drawRect(x1, y1, x2, y2);
	}

	public Rect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rect(int x1,int y1,int x2,int y2,Color color,int width)
	{
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.color=color;
		this.width=width;
	}
}
