package ex2_2;

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

public class Ex2_2 extends JApplet {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("EX2_2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JApplet applet = new Ex2_2();
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

	int x = 100, y = 100;
	Graphics2D g2;

	public MyPanel2D() {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		
	/*	g2.scale(0.6,0.6);
		x*=1.66;
		y*=1.66;*/
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/*Font font = new Font("Serif", Font.BOLD, 14);
		g2.setFont(font);
		g2.setColor(Color.black);
		g2.drawString("x:" + x + " y:" + y, 300, 380);*/

		g2.setColor(Color.decode("#97BE3F"));
	
		//Face format
		GeneralPath f = new GeneralPath();
		f.moveTo(x-125, y-75);
		f.curveTo(x-90, y-120, x+90, y-120,x+125, y-75);
		f.curveTo(x+145, y-40, x+125, y+30, x+25, y+125);
		f.quadTo(x, y+145, x-25, y+125);
		f.curveTo(x-125, y+30, x-145, y-40, x-125, y-75);
		f.closePath();
		g2.fill(f);
		
		//Eyes
		eye(x);
		g2.setColor(Color.decode("#372E29"));
		Ellipse2D e2 = new Ellipse2D.Double(x+95,y-50, 20, 20);
		g2.fill(e2);
		eye(x-170);
		e2 = new Ellipse2D.Double(x-115,y-50, 20, 20);
		g2.setColor(Color.decode("#372E29"));
		g2.fill(e2);
		
		nose();
		antennas();
		
	}
	
	public void antennas() {
		g2.setColor(Color.decode("#97BE3F"));
		GeneralPath a = new GeneralPath();
		a.moveTo(x+40, y-110);
		a.quadTo(x+40, y-135, x+125, y-150);
		g2.setStroke(new BasicStroke(14.0f, BasicStroke.CAP_ROUND, 2));
		g2.draw(a);
		
		a = new GeneralPath();
		a.moveTo(x-40, y-110);
		a.quadTo(x-40, y-135, x-125, y-150);
		g2.setStroke(new BasicStroke(14.0f, BasicStroke.CAP_ROUND, 2));
		g2.draw(a);
		
	}
	
	public void nose() {
		g2.setColor(Color.BLACK);
		GeneralPath n = new GeneralPath();
		n.moveTo(x-45, y+70);
		n.lineTo(x-35, y+70);	
		n.lineTo(x, y+95);	
		n.lineTo(x+35, y+70);
		n.lineTo(x+45, y+70);
		n.lineTo(x, y+105);
		n.lineTo(x-45, y+70);
		g2.fill(n);
	}
	
	public void eye(int x) {
		
		Ellipse2D e1 = new Ellipse2D.Double(x+30,y-92, 110, 100);
		GeneralPath gp = new GeneralPath();
		gp.moveTo(x+30, y-40);
	    gp.quadTo(x+85, y+155, x+140,  y-40);
	    gp.closePath();
	    
	    Area a1 = new Area(e1);
	    Area a2 = new Area(gp);
	    
	    a1.add(a2);
	    
	    g2.setColor(Color.BLACK);
	    g2.setStroke(new BasicStroke(2f));
	    g2.draw(a1);
	    g2.setColor(Color.WHITE);
	    g2.fill(a1);
	
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