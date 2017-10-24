package ex3_3;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ex3_3.Ex3_3;
import ex3_3.MyPanel2D;

public class Ex3_3 extends JApplet {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("EX3_3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JApplet applet = new Ex3_3();
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

	int x, y;
	Graphics2D g2;

	public MyPanel2D() {
		setPreferredSize(new Dimension(400, 400));
		setBackground(Color.WHITE);
		addMouseListener(this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.scale(1.35, 1.35);
		g2.translate(-35, -35);
		
		Font font = new Font("Serif", Font.BOLD, 14);
		g2.setFont(font);
		g2.setColor(Color.black);
		g2.drawString("x:" + x + " y:" + y, 320, 375);

		GeneralPath petala = new GeneralPath();

		GradientPaint gp = new GradientPaint(65, 25, Color.BLUE, 155, 250, Color.RED);


		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .65f);
		AlphaComposite acDefault = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);

		
		petala.moveTo(107, 175);
		petala.quadTo(92, 130, 109, 120);
		petala.quadTo(120, 130, 111, 175);
		petala.closePath();

		g2.setComposite(ac);
		for (int i = 0; i < 16; i++) {
			g2.rotate(Math.PI / 8, 108, 183.5);

			g2.setPaint(gp);
			g2.fill(petala);
			g2.setColor(Color.black);
			g2.draw(petala);
		}
		
		g2.setComposite(acDefault);
		petala.reset();

		g2.setColor(Color.yellow);
		g2.fillOval(99, 175, 18, 18);
		g2.setColor(Color.black);
		g2.drawOval(99, 175, 18, 18);

		// FLOR 2
		// --------------------------

		gp = new GradientPaint(150, 150, Color.green, 350, 350, Color.red);

		petala.moveTo(297, 175);
		petala.lineTo(293, 150);
		petala.lineTo(299.5, 125);
		petala.lineTo(306, 150);
		petala.lineTo(302, 175);
		petala.closePath();

		g2.translate(-35, 0);
		
		g2.setColor(Color.YELLOW);
		g2.fillRect(287, 175, 20, 20);

		int green = 155;
		for (int i = 0; i < 15; i++) {
			Color c = new Color(0, green, 0);

			green += 4;
			g2.setColor(c);

			g2.rotate(Math.PI / 7, 298, 183.5);

			g2.fill(petala);

			g2.setColor(Color.BLACK);
			g2.draw(petala);
		}

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