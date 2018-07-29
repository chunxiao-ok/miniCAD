package miniCAD;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleDialog extends JDialog implements ActionListener{

	 // 文本框，用于输入字符串
    JTextField field;
    // 对话框的父窗体
    JButton setButton;
    private String str;
    SimpleDialog() {
        // 添加Label和输入文本框
    	str = "";
        JPanel p1 = new JPanel();
        JLabel label = new JLabel("请输入要添加的文本:");
        p1.add(label);
        field = new JTextField(20);
        field.addActionListener(this);
        p1.add(field);
        getContentPane().add("Center", p1);
 
        // 添加确定和取消按钮
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("取 消");
        cancelButton.addActionListener(this);
        setButton = new JButton("确 定");
        setButton.addActionListener(this);
        p2.add(setButton);
        p2.add(cancelButton);
        getContentPane().add("South", p2);
 
        // 调整对话框布局大小
        pack();
    }
    public String getStr()
    {
    	return str;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
        if ((source == setButton)) {
            // 如果确定按钮被按下，则将文本矿的文本添加到父窗体的文本域中
        	str = field.getText();
        }
        else 
        {
        	str = "";
        }
        field.selectAll();
        // 隐藏对话框
       // setVisible(false);
        this.dispose();
	}

}
