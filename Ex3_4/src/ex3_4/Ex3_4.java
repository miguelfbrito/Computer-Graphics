package ex3_4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TextComponent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ex3_4.Ex3_4;
import ex3_4.MyPanel2D;

public class Ex3_4 extends JApplet {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("EX3_4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JApplet applet = new Ex3_4();
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

		Font f = new Font("Arial", Font.BOLD, 100);

		g2.setColor(Color.BLACK);
		g2.fillOval(62, 62, 275, 275);

		g2.setFont(f);
		g2.setColor(Color.ORANGE);
	//	g2.drawString("M", 126, (int) (62 + 275 / 2) + g2.getFontMetrics().getHeight() / 4);
//		g2.drawString("B", 155, (int) (62 + 275 / 2) + g2.getFontMetrics().getHeight() / 4);

		FontRenderContext frc = g2.getFontRenderContext();
		GlyphVector gv = f.createGlyphVector(frc, "M");
		Shape glyph = gv.getOutline(138, (int) (62 + 275 / 2) + g2.getFontMetrics().getHeight() / 4);

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2f));
		g2.draw(glyph);

		Area a = new Area(glyph);

		gv = f.createGlyphVector(frc, "B");
		Shape glyph2 = gv.getOutline(196, (int) (62 + 275 / 2) + g2.getFontMetrics().getHeight() / 4);

		Area a2 = new Area(glyph2);
		a.add(a2);
		g2.setColor(Color.ORANGE);
	
		g2.fill(a);
		
		AffineTransform defaultTransform = new AffineTransform();
		defaultTransform = g2.getTransform();
		
		g2.setColor(Color.black);
		g2.translate(3,0);
		g2.draw(a);
		
		g2.setTransform(defaultTransform);
		
		g2.setStroke(new BasicStroke(1f));
		g2.setColor(Color.WHITE);
		g2.fillRoundRect(140, 235, 130, 9, 7, 7);
	}
}
