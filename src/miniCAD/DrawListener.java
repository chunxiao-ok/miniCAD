package miniCAD;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;

public class DrawListener implements MouseListener,MouseMotionListener, ActionListener{
	public Graphics2D g;
	private int x1,y1,x2,y2;
	public ButtonGroup bg;
	private String command;
	private Color color;
	public DrawBorder db;
	public ArrayList list;
	public boolean flag=true;
	private SimpleDialog dialog;

	
	public static final  Stroke s1 = new BasicStroke(1);
	public static final  Stroke s2 = new BasicStroke(10);
	public static final  Stroke s3 = new BasicStroke(15);
	
	//构造函数1
	public DrawListener(Graphics g1){
		g=(Graphics2D)g1;
	}
	
	//构造函数2
	public DrawListener(Graphics g2, ButtonGroup bg2) {
		g=(Graphics2D)g2;
		bg=bg2;
	}
 
    //构造函数3
	public DrawListener(Graphics g2, ButtonGroup bg2, DrawBorder db1,ArrayList list) {
		g=(Graphics2D)g2;
		bg=bg2;
		db=db1;
		this.list=list;
	}
 
    //鼠标按下事件监听
	public void mousePressed(MouseEvent e) {
		//获取鼠标按下点的坐标
		x1=e.getX();
		y1=e.getY();
		
		//判断选择的是左面板中的那个按钮被选中（前面已经设置每个按钮的名称了）  
        ButtonModel bm=bg.getSelection();//拿到按钮组中被选中的按钮  
        command=bm.getActionCommand();//拿到选中按钮的名字  
       
        if("pic5".equals(command))
        {
        	if (dialog == null) {
                dialog = new SimpleDialog();
            }
            dialog.setVisible(true);
        }
	}
 
	public void mouseReleased(MouseEvent e) {  
        //获取鼠标释放的坐标  
        x2=e.getX();  
        y2=e.getY();  
           
        //如果选中的是绘制直线的按钮，那么根据鼠标按下点的坐标和释放点的坐标绘制直线（两点确定一条直线）  
        if("pic0".equals(command))  
        {  
        	Shape line = new Line(x1, y1, x2, y2,g.getColor(),1);
        	line.Draw(g);
        	list.add(line);
        }//同理选中的是矩形按钮，那么绘制矩形（这里有绘制矩形的纠正，不纠正的话从右下角往左上角方向绘制矩形会出现问题，参看后面难点解析）  
        else if("pic1".equals(command)){  
        	Shape rect = new Rect(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),g.getColor(),1);
        	rect.Draw(g);
        	list.add(rect);
        }//绘制椭圆  
        else if("pic2".equals(command)){  
        	Shape oval = new Oval(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),g.getColor(),1);
        	oval.Draw(g);
        	list.add(oval);
        } 
        else if("pic5".equals(command))
        {
        	String str = dialog.getStr();
        //	g.drawString(str, x1, y1);
        	Shape text = new Text(x1, y1, x2, y2,g.getColor(),1, str);
        	if(null != str)
        	{
        		text.Draw(g);
            	list.add(text);
        	}
        }
    }  
 
	public void mouseClicked(MouseEvent e) {
		//多边形图形双击封闭
	}
 
	public void mouseEntered(MouseEvent e) {
		    color=db.c;//设置画笔颜色  
			g.setColor(color);
			g.setStroke(s1);
	}
 
	public void mouseExited(MouseEvent e) {
		
	}
 
	public void mouseDragged(MouseEvent e) {	
		int x=e.getX();
		int y=e.getY();
		
		//画笔功能
		if("pic0".equals(command)){	
			Shape line = new Line(x1, y1, x, y,g.getColor(),1);
         	line.Draw(g);
         	list.add(line);
		//	x1=x;
		//	y1=y;
         	db.repaint();
		}
		//橡皮擦功能
		else if("pic3".equals(command)){
			db.c=Color.white;
			g.setColor(db.c);
			g.setStroke(s3);
			
			Shape line = new Line(x1, y1, x, y,g.getColor(),15);
         	line.Draw(g);
         	list.add(line);
			
			x1=x;
			y1=y;
		}
		//刷子功能
		else if("pic6".equals(command)){
			g.setStroke(s2);//设置画笔 粗细
			
			Shape line = new Line(x1, y1, x, y,g.getColor(),10);
         	line.Draw(g);
         	list.add(line);
         	
			x1=x;
			y1=y;
		}		
	}
 
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if ("".equals(arg0.getActionCommand())) {
            
        } else {
        	command = arg0.getActionCommand();
        }
	}

}
