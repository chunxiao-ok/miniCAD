package miniCAD;

import java.awt.Color;
import java.awt.Graphics2D;

public class Text extends Shape{
	private String str;
	@Override
	public void Draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(null != str)
			g.drawString(str, x1, y1);
	}
	public Text(int x1,int y1,int x2,int y2,Color color,int width, String str) {
		// TODO Auto-generated constructor stub
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.color=color;
		this.width=width;
		this.str = str;
	}

}
