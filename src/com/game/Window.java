package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.networking.test.GameClient;

public class Window extends Canvas{


	private static final long serialVersionUID = 2638324587210375601L;
	private JFrame mainframe;
	private JPanel content=new JPanel();
	private Game game;

	public Window() {
		
	}
	
	public Window(int width, int height, String title ,Game game) {		

		mainframe = new JFrame(title);
		
		mainframe.setPreferredSize(new Dimension(width,height));
		mainframe.setMaximumSize(new Dimension(width, height));
		mainframe.setMinimumSize(new Dimension(width,height));
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.add(game);
		mainframe.setVisible(true);
		game.start();
		
	}
	
	public void openMainGame(int width, int height, String title ,Game game) {

		mainframe = new JFrame(title);
		
		mainframe.setPreferredSize(new Dimension(width,height));
		mainframe.setMaximumSize(new Dimension(width, height));
		mainframe.setMinimumSize(new Dimension(width,height));
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.add(game);
		mainframe.setVisible(true);
		game.start();
		
	}
	
	public void openMenu(int width, int height, String title, Game game) throws IOException {
		this.game=game;
		
		mainframe=new JFrame(title);
		mainframe.setLayout(null);
		
		JLabel logo=new JLabel("");
		JButton single=new JButton("");
		JButton multi=new JButton("");
		JButton quit=new JButton("");
		
		Image imglogo=ImageIO.read(getClass().getResource("/img/logo.png"));
		imglogo = imglogo.getScaledInstance(550,236,java.awt.Image.SCALE_SMOOTH);
		
		Image imgbg=ImageIO.read(getClass().getResource("/img/menu-bg.png"));
		imgbg = imgbg.getScaledInstance(1000,750,java.awt.Image.SCALE_SMOOTH);
		
		Image img6single=ImageIO.read(getClass().getResource("/img/rec6-splayer.png"));
		img6single = img6single.getScaledInstance(190,50,java.awt.Image.SCALE_SMOOTH);
		
		Image img6multi=ImageIO.read(getClass().getResource("/img/rec6-mplayer.png"));
		img6multi = img6multi.getScaledInstance(190,50,java.awt.Image.SCALE_SMOOTH);

		Image img6quit=ImageIO.read(getClass().getResource("/img/rec6-quit.png"));
		img6quit = img6quit.getScaledInstance(190,50,java.awt.Image.SCALE_SMOOTH);

		Image imgicon=ImageIO.read(getClass().getResource("/img/iconimg.png"));
		mainframe.setIconImage(imgicon);
		
		logo.setBounds(230,130,550,236);
		single.setBounds(380,390,190,50);
		multi.setBounds(380,450,190,50);
		quit.setBounds(380,510,190,50);

		single.setBackground(Color.decode("#00000000"));
		multi.setBackground(Color.decode("#00000000"));
		quit.setBackground(Color.decode("#00000000"));
		
		single.setBorder(null);
		multi.setBorder(null);
		quit.setBorder(null);

		logo.setIcon(new ImageIcon(imglogo));
		single.setIcon(new ImageIcon(img6single));
		multi.setIcon(new ImageIcon(img6multi));
		quit.setIcon(new ImageIcon(img6quit));


		JLabel bgimage=new JLabel("");
		bgimage.setIcon(new ImageIcon(imgbg));
		
		content.setLayout(null);
		content.setBackground(Color.decode("#00000000"));
		content.setBounds(0,0,1000,750);
		bgimage.setBounds(0,0,1000,750);
		content.add(logo);
		content.add(single);
		content.add(multi);
		content.add(quit);
		content.add(bgimage);
		mainframe.add(content);
		
		
		mainframe.setPreferredSize(new Dimension(width,height));
		mainframe.setMaximumSize(new Dimension(width, height));
		mainframe.setMinimumSize(new Dimension(width,height));
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		
		single.addMouseListener(new mouseOnSinglePlayer());
		multi.addMouseListener(new mouseOnMultiplayer());
		quit.addMouseListener(new mouseOnQuit());
			
	}	
	
	class mouseOnSinglePlayer extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
//			mainframe.getContentPane().removeAll();
			mainframe.dispose();
			openMainGame(1000,750,"Space Suckers",new Game());			
		}
	}
	class mouseOnMultiplayer extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
//			mainframe.getContentPane().removeAll();
			mainframe.dispose();
			content.removeAll();
			try {				
				openMultiplayer(1000,750,"Space Suckers",null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class mouseOnQuit extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			mainframe.dispose();
		}		
	}
	
	class mouseOnCreate extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			mainframe.dispose();
		}		
	}
	class mouseOnConnect extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			mainframe.dispose();
		}		
	}
	class mouseOnExit extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			mainframe.dispose();
			try {
				openMenu(1000,750,"Space Suckers",new Game());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
	}
	
	public void openMultiplayer(int width, int height, String title, Game game) throws IOException {
		this.game=game;
		
		mainframe=new JFrame(title);
		mainframe.setLayout(null);
		
		JLabel logo=new JLabel("");
		JButton create=new JButton("");
		JButton connect=new JButton("");
		JButton exit=new JButton("");
		
		Image imglogo=ImageIO.read(getClass().getResource("/img/logo.png"));
		imglogo = imglogo.getScaledInstance(550,236,java.awt.Image.SCALE_SMOOTH);
		
		Image imgbg=ImageIO.read(getClass().getResource("/img/menu-bg.png"));
		imgbg = imgbg.getScaledInstance(1000,750,java.awt.Image.SCALE_SMOOTH);
		
		Image img6create=ImageIO.read(getClass().getResource("/img/rec6_CreateServer.png"));
		img6create = img6create.getScaledInstance(190,50,java.awt.Image.SCALE_SMOOTH);
		
		Image img6connect=ImageIO.read(getClass().getResource("/img/rec6_Connect.png"));
		img6connect = img6connect.getScaledInstance(190,50,java.awt.Image.SCALE_SMOOTH);

		Image img6exit=ImageIO.read(getClass().getResource("/img/rec6_Exit.png"));
		img6exit = img6exit.getScaledInstance(190,50,java.awt.Image.SCALE_SMOOTH);

		Image imgicon=ImageIO.read(getClass().getResource("/img/iconimg.png"));
		mainframe.setIconImage(imgicon);
		
		logo.setBounds(230,130,550,236);
		create.setBounds(380,390,190,50);
		connect.setBounds(380,450,190,50);
		exit.setBounds(380,510,190,50);

		create.setBackground(Color.decode("#00000000"));
		connect.setBackground(Color.decode("#00000000"));
		exit.setBackground(Color.decode("#00000000"));
		
		create.setBorder(null);
		connect.setBorder(null);
		exit.setBorder(null);

		logo.setIcon(new ImageIcon(imglogo));
		create.setIcon(new ImageIcon(img6create));
		connect.setIcon(new ImageIcon(img6connect));
		exit.setIcon(new ImageIcon(img6exit));


		JLabel bgimage=new JLabel("");
		bgimage.setIcon(new ImageIcon(imgbg));
		
		content.setLayout(null);
		content.setBackground(Color.decode("#00000000"));
		content.setBounds(0,0,1000,750);
		bgimage.setBounds(0,0,1000,750);
		content.add(create);
		content.add(logo);
		content.add(connect);
		content.add(exit);
		content.add(bgimage);
		mainframe.add(content);
		
		
		mainframe.setPreferredSize(new Dimension(width,height));
		mainframe.setMaximumSize(new Dimension(width, height));
		mainframe.setMinimumSize(new Dimension(width,height));
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setResizable(false);
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		
		create.addMouseListener(new mouseOnCreate());
		connect.addMouseListener(new mouseOnConnect());
		exit.addMouseListener(new mouseOnExit());
			
	}	
}
