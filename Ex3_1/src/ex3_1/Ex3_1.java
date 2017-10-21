package ex3_1;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex3_1 extends JApplet {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("EX3_1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JApplet applet = new Ex3_1();
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

	ArrayList<Number> VS1 = new ArrayList<Number>();
	ArrayList<Number> VS2 = new ArrayList<Number>();

	int marginX = 125;
	int marginY = 95;

	public void randomVS(int quant) {
		VS1.clear();
		VS2.clear();
		int val = (int) randomNumber(2, quant);
		for (int i = 0; i < val; i++) {
			VS1.add(randomNumber(5, 750));
			VS2.add(randomNumber(5, 750));
		}
	}

	public MyPanel2D() {
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.WHITE);
		addMouseListener(this);

		randomVS(12);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		Font font = new Font("Serif", Font.BOLD, 14);
		g2.setFont(font);
		g2.setColor(Color.black);
	//	g2.drawString("x:" + x + " y:" + y, 415, 480);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		String rotulos[] = { "Jan.", "Fev.", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Set.", "Out.",
				"Nov.", "Dez." };

		GraficoBarras(7, 7, 475, 455, rotulos, "Visitantes Mensais por categoria", VS1, VS2);
		// TODO : passar fonts a derivadas

	}

	public void GraficoBarras(int x, int y, int l, int a, String[] rotulos, String titulo, ArrayList<Number> VS1, ArrayList<Number> VS2) {
		// x,y = ponto superior esquerdo
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y, l, a);

		int xG = x + marginX / 2;
		int yG = y + marginY / 2;

		int lG = l - marginX;
		int aG = a - marginY;

		
		
		// Painel do gráfico (inclui pattern)
		Rectangle r = new Rectangle(xG+lG, yG+aG, (int)(lG*1.4), (int)(aG*1.1));
		BufferedImage bi = null;

		try {

			bi = ImageIO.read(new File("images/pattern.jpg"));
		} catch(IOException ex){
			ex.printStackTrace();
		}

		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f);
		AlphaComposite ac1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		
		g2.setComposite(ac);
	    TexturePaint tp = new TexturePaint(bi, r);

	    g2.setPaint(tp);
	   
		g2.fillRect(xG, yG, lG, aG);
		g2.setComposite(ac1);
		g2.setColor(Color.BLACK);
		g2.drawRect(xG, yG, lG, aG);
		
		
		// Eixo Y
		int d = 5;
		int incLinha = aG / d;

		int max = (int) Math.ceil(getMaxValue(VS1));
		int max2 = (int) Math.ceil(getMaxValue(VS2));
		
		if(max<max2) {
			max = max2;
		}
		
		max+=15;
		while(max % d != 0) {
			max++;
		}
		
		int incEscala = max / d;
		g2.setColor(Color.red);
		g2.drawLine(xG, yG, xG, yG + aG);
		
		
		// Eixo Y
		for (int i = 0; i <= d; i++) {
			int yi = yG + aG - incLinha * i;
			
			
			if(i == 0 || i == d) {
				g2.setStroke(new BasicStroke(1f));
			} else {
				g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 2f, new float[] {5,5}, 2f));
			}
			
			g2.drawLine(xG, yi, xG - 5, yi);
			g2.drawLine(xG, yi, xG+lG, yi);
			
			String ei = i * incEscala + "";
			Font font = new Font("Arial", Font.BOLD, 11);
			g2.setFont(font);
			int height =  g2.getFontMetrics().getHeight();
			int width = g2.getFontMetrics().stringWidth(ei);
			g2.drawString(ei,  xG-width-8,  yi+(height/2));
		}
		
		
		//Eixo X
		//desenho da escala
		int numBarras = VS1.size();
		incLinha = lG/numBarras;
		g2.drawLine(xG, yG+aG, xG+lG, yG+aG);
		
		for(int i = 0; i<=VS1.size(); i++) {
			int xi = xG + incLinha * i;
			g2.drawLine(xi,  yG+aG, xi,  yG+aG+5);
		}

		float fEscala = aG / (float)max;
		int marginBarras = 25;
		
		Font fontLegenda = new Font("Arial", Font.BOLD, 16);
		Font fontTitulo = new Font("Arial", Font.BOLD, 20);
		Font fontNota = new Font("Arial", Font.BOLD, 11);
		
		GradientPaint gpBarras = new GradientPaint(235, 50, Color.decode("#0ABFBC"), 280, 476, Color.decode("#FC354C"));
		
		//desenho das barras
		int lBarra = Math.max(Math.min(lG/numBarras-marginBarras, 65), 15);
		for(int i=0; i<numBarras; i++) {
			int aBarra = (int) ((float)VS1.get(i) * fEscala);
			int xi = xG + i * incLinha + incLinha/2 - (lBarra)/2;
			int yi = yG + aG - aBarra;

			g2.setPaint(gpBarras);
			g2.fillRect(xi, yi, lBarra, aBarra);
			
			Font font = new Font("Serif", Font.BOLD, 8);
			g2.setFont(font);
			g2.setColor(Color.black);
			
			String txt = rotulos[i]+"";
			int width = g2.getFontMetrics().stringWidth(txt);
	
			// texto eixo x
			g2.drawString(txt, xG + i * incLinha + (incLinha - width)/2, yG+aG+15);
		//	System.out.println((float) VS1.get(i) + "");
			
		}
		
		//Legenda Direita
		gpBarras = new GradientPaint(xG+lG+10, yG+(aG/2)-30, Color.decode("#0ABFBC"), xG+lG+10, yG+(aG/2)-5, Color.decode("#FC354C"));;
		g2.setPaint(gpBarras);
		g2.fillRect(xG+lG+10, yG+(aG/2)-25, 35, 12);
		g2.setColor(Color.BLACK);
		g2.setFont(fontNota);
		g2.drawString("Arte",xG+lG+10, yG+(aG/2));
		
		g2.setColor(Color.BLACK);
		g2.setFont(fontNota);
		g2.drawString("Ciência",xG+lG+10, yG+(aG/2)+37);
		
		g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 2f, new float[] {5,5}, 2f)); 
	
		g2.setColor(Color.decode("#08E700"));
		g2.fillRoundRect(xG+lG+21, yG+(aG/2)+15, 10, 10 , 20, 20);		
		g2.drawLine(xG+lG+10, yG+(aG/2)+20, xG+lG+40, yG+(aG/2)+20);
			
		
		//Legenda Esquerda
		g2.setFont(fontLegenda);
		g2.setColor(Color.black);
		String txt = "Visitantes";
		
		AffineTransform at = new AffineTransform();
		at = g2.getTransform();

		int width = g2.getFontMetrics().stringWidth(txt);
		int height =  g2.getFontMetrics().getHeight();
	
	    g2.rotate(-Math.PI/2, xG, (yG + aG)/2 + height );
		g2.drawString(txt, xG-(width/2), (yG + aG)/2 - 20 );
		g2.setTransform(at);
		
		//Título
		g2.setFont(fontTitulo);
		g2.setColor(Color.black);
		width = g2.getFontMetrics().stringWidth(titulo);
		g2.drawString(titulo, (lG/2) + xG - width/2, yG-15);
		
		
		//Legenda Inferior
		g2.setFont(fontLegenda);
		g2.setColor(Color.black);
		txt = "Meses";
		width = g2.getFontMetrics().stringWidth(txt);
		g2.drawString(txt, (lG/2) + xG - width/2, yG+aG+40);
		
		//Nota
		g2.setFont(fontNota);
		g2.setColor(Color.black);
		txt = "Uma nova tabela com dados aleatórios é gerada a cada duplo-clique";
		width = g2.getFontMetrics().stringWidth(txt);
		g2.drawString(txt, x , yG+aG+60);
		
		
		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		
		float numPontos = VS2.size();
		
		for(int i = 0; i<numPontos; i++) {
			
			int di = (int) Math.min(lBarra*0.7, 15);
			int xi = xG + i * incLinha + incLinha/2 -di/2;
			int yi = (int) ( yG+aG-((float) VS2.get(i)*fEscala)) -(di/2);
	
			g2.setColor(Color.decode("#08E700"));
			g2.fillRoundRect(xi, yi, di, di , 20, 20);
			
			if(i<numPontos-1) {
				int xiNext = xG + (i+1) * incLinha + incLinha/2;
				int yiNext = (int) ( yG+aG-(float) VS2.get(i+1) * fEscala);
				g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 2f, new float[] {5,5}, 2f)); 
				g2.drawLine(xi+(di/2), yi+(di/2), xiNext, yiNext);
				
			}
//x
		}
		
		
	}


	public float getMaxValue(ArrayList<Number> v) {
		float max = 0f;
		for (int i = 0; i < v.size(); i++) {
			if ((float) v.get(i) > max) {
				max = (float) v.get(i);
			}
		}
		return max;
	}

	public float randomNumber(float min, float max) {
		return (float) min + (int) (Math.random() * ((max - min) + 1));
	}

	long pastMillis = 1;

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		if(e.getClickCount() == 2) {
		
			randomVS(12);
		}
//		pastMillis = System.currentTimeMillis();
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