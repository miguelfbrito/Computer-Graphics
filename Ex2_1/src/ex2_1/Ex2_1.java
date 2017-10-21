package ex2_1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class Ex2_1 extends JApplet {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("EX2_1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JApplet applet = new Ex2_1();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}

	public void init() {
		JPanel panel = new MyPanel2D();
		getContentPane().add(panel);
	}

}

class MyPanel2D extends JPanel implements MouseListener {

	int x = 111, y = 111;
	Graphics2D g2;

	public MyPanel2D() {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		

	/*	Font font = new Font("Serif", Font.BOLD, 14);
		g2.setFont(font);
		g2.setColor(Color.black);
		g2.drawString("x:" + x + " y:" + y, 300, 380);*/
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.red);
		g2.fillOval(37,25, 325, 325);
	
		g2.setColor(Color.white);
		g2.fillOval(140, 85, 125, 135);
		
		
		g2.setStroke(new BasicStroke(50.0f));
		
		g2.setColor(Color.white);
		g2.drawArc(139, 180, 55, 200, 30, 30);
		
		
		g2.setStroke(new BasicStroke(5.0f));
		g2.setColor(Color.white);
		g2.fillRoundRect(170, 255, 65, 15, 5, 15);
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(50.0f));
		g2.drawArc(210, 180, 55, 200, 115, 40);
		
		g2.setStroke(new BasicStroke(5.0f));
		g2.fillRoundRect(170, 277, 65, 13, 5, 15);
		
		g2.fillRoundRect(170, 295, 65, 13, 5, 15);
		
		g2.fillRoundRect(192, 313, 20, 8, 5, 15);
		
		g2.setColor(Color.RED);

		g2.setStroke(new BasicStroke(5.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
		g2.drawLine(194, 250, 194, 200);
		g2.drawLine(208, 250, 208, 200);
		
		g2.drawLine(194, 200, 186, 184);
		g2.drawLine(208, 200, 216, 184);
		
		g2.drawLine(186, 184, 186, 157);
		g2.drawLine(216, 184, 216, 157);
		
		g2.drawOval(170, 146, 17, 17);
		g2.drawOval(215, 146, 17, 17);

		g2.setStroke(new BasicStroke(5.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g2.drawLine(186, 152, 200, 164);
		g2.drawLine(200, 166, 200, 146);
		g2.drawLine(198, 146, 215, 157);
		
		g2.setStroke(new BasicStroke(8f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g2.setColor(Color.WHITE);
		
		AffineTransform at = g2.getTransform();
		
		//RIGHT
		g2.rotate(Math.PI/8);
		g2.fillRoundRect(325, 65, 40, 10, 5, 15); // bottom
		g2.setTransform(at);
		g2.fillRoundRect(285, 145, 40, 10, 5, 15); // middle
		g2.rotate(-Math.PI/8);
		g2.fillRoundRect(213, 200, 40, 10, 5, 15); // top
	
		
		//LEFT
		g2.fillRoundRect(5, 220, 40, 10, 5, 15); //bottom
		g2.setTransform(at);
		g2.fillRoundRect(80, 145, 40, 10, 5, 15); //middle
		g2.rotate(Math.PI/8);
		g2.fillRoundRect(120, 46, 40, 10, 5, 15); // top
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}