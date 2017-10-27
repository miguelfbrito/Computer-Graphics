package ex3_2;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TextComponent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.ShapeGraphicAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ex3_2.Ex3_2;
import ex3_2.MyPanel2D;

public class Ex3_2 extends JApplet {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("EX3_2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JApplet applet = new Ex3_2();
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

class MyPanel2D extends JPanel {

	int x, y;
	Graphics2D g2;

	public MyPanel2D() {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		
		GradientPaint bp = new GradientPaint(200, 0, Color.decode("#63A8D9"), 200, 355, Color.decode("#73BCE9"));
		g2.setPaint(bp);
		g2.fillRect(0, 0, 400, 355);

		Font f = new Font("Arial", Font.BOLD, 20);
		g2.setColor(Color.white);
		g2.setFont(f);
		g2.drawString("Level 1", 10, 30);
		
		AffineTransform defaultTransform = new AffineTransform();
		defaultTransform = g2.getTransform();

		montanha();
		g2.translate(-95, -15);
		g2.scale(1.65, 1.4);
		
		g2.rotate(-Math.PI / 8);
		

		montanha();

		g2.setTransform(defaultTransform);

		terra();
		erva();
		
		// BANDEIRA

		g2.setTransform(defaultTransform);
		g2.setColor(Color.decode("#36190E"));
		g2.fillRoundRect(329, 283, 25, 10, 8, 8);

		g2.setColor(Color.decode("#616B71"));
		g2.fillRect(340, 237, 3, 56);

		g2.translate(0, -123);
		g2.setColor(Color.decode("#EF5A24"));
		g2.shear(0, 0.35);
		g2.fillRect(343, 242, 20, 17);

		g2.setTransform(defaultTransform);

		g2.setColor(Color.ORANGE);
		
	   AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f);
	   AlphaComposite acDefault = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	   
		g2.fillOval(25, 290, 8, 8);
		g2.setComposite(ac);
		
		for(int i = 0; i<7; i++) {
			g2.fillOval(25+(i*14), 290-(i*6), 7+(int)(i*1.1), 7+(int)(i*1.1));
		}
		
		g2.setComposite(acDefault);
	
		g2.translate(-35, 45);
		cloud();
		
		g2.translate(125, 35);
		g2.scale(.9, 1);
		cloud();
		g2.translate(85, -55);
		g2.scale(1.4, 1.2);
		cloud();
		
		g2.setTransform(defaultTransform);
		
	}

	public void terra() {
		String brown = "#6B3215";
		GeneralPath terra = new GeneralPath();
		terra.moveTo(0, 300);
		terra.lineTo(55, 300);
		terra.lineTo(165, 345);
		terra.lineTo(185, 345);
		terra.lineTo(200, 355);
		terra.lineTo(215, 355);
		terra.lineTo(235, 335);
		terra.lineTo(310, 285);
		terra.lineTo(400, 285);
		terra.lineTo(400, 400);
		terra.lineTo(0, 400);
		terra.closePath();
		g2.setColor(Color.decode(brown));
		g2.fill(terra);
		
		
		

	}

	public void cloud() {
		 AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
		g2.setColor(Color.WHITE);
		g2.setComposite(ac);
//		g2.fillOval(75, 55, 100, 35);
//		g2.fillOval(90, 45, 70, 35);
		
		Ellipse2D e = new Ellipse2D.Double(75, 55, 85, 35);
		Ellipse2D e1 = new Ellipse2D.Double(85, 45, 45, 35);
		Ellipse2D e2 = new Ellipse2D.Double(105, 45, 45, 35);
		
		Area a = new Area(e);
		Area b = new Area(e1);
		Area c = new Area(e2);
		
		a.add(b);
		a.add(c);
		
		g2.fill(a);
	}
	
	public void erva() {

		GeneralPath erva = new GeneralPath();
		erva.moveTo(0, 300);
		erva.lineTo(55, 300);
		erva.lineTo(165, 345);
		erva.lineTo(185, 345);
		erva.lineTo(200, 355);
		erva.lineTo(215, 355);
		erva.lineTo(235, 335);
		erva.lineTo(310, 285);
		erva.lineTo(400, 285);

		erva.lineTo(400, 295);
		erva.lineTo(310, 295);
		erva.lineTo(235, 345);
		erva.lineTo(215, 365);
		erva.lineTo(200, 365);
		erva.lineTo(185, 355);
		erva.lineTo(165, 355);
		erva.lineTo(55, 310);
		erva.lineTo(0, 310);

		erva.closePath();

		g2.setColor(Color.decode("#88A838"));
		g2.fill(erva);

		g2.setColor(Color.decode("#9EC442"));
		g2.translate(0, -3.5);
		g2.fill(erva);

	}

	public void montanha() {
		GeneralPath m = new GeneralPath();
		m.moveTo(75, 315);
		m.lineTo(125, 245);
		m.lineTo(170, 355);
		m.closePath();

		g2.setColor(Color.decode("#439F46"));
		g2.fill(m);

		m.reset();
		m.moveTo(110, 266);
		m.lineTo(125, 245);
		m.lineTo(138, 277);
		m.lineTo(133, 280);
		m.lineTo(129, 270);
		m.lineTo(125, 275);
		m.lineTo(121, 266);
		m.lineTo(113, 272);
		m.closePath();

		g2.setColor(Color.WHITE);
		g2.fill(m);
	}
}
