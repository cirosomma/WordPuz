package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import eu.miclen.wordpuz.Loader;

public class SelectLevel extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int LOW = 5*60;
	public static final int MEDIUM = 3*60;
	public static final int HIGH = 2*60;
	private ResourceBundle res;
	private final SelectLevel my;
	private JMenu menuImpostazioni;
	private JMenuItem sceltaImpostazioneUno;
	/**private JMenuItem sceltaImpostazioneTre;*/
	private JMenuItem sceltaImpostazioneQuattro;
	private JMenuItem sceltaImpostazioneDue;
	private JLabel selectLevel;
	private JButton bottoneFacile;
	private JButton bottoneDifficile;
	private JButton bottoneMedio;

	public JLabel tempoRimanente;
	public SelectLevel() {	
		my = this;
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		JMenuBar menubar = new JMenuBar();
		menuImpostazioni = new JMenu(res.getString("settings"));
		JMenuItem credit = new JMenuItem("Credits");
		credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Credits();
			}
        });
		sceltaImpostazioneUno = new JMenuItem(res.getString("showscore"));
		sceltaImpostazioneUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ShowScore();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        });
		sceltaImpostazioneDue = new JMenuItem(res.getString("selectlanguage"));
		sceltaImpostazioneDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SelectLanguage(my);
			}
        });
		/**
		sceltaImpostazioneTre = new JMenuItem(res.getString("manual"));
		sceltaImpostazioneTre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
        });
        */
		sceltaImpostazioneQuattro = new JMenuItem(res.getString("exit"));
		sceltaImpostazioneQuattro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
        });
		menuImpostazioni.add(sceltaImpostazioneUno);
		menuImpostazioni.add(sceltaImpostazioneDue);
		/**menuImpostazioni.add(sceltaImpostazioneTre);*/
		menuImpostazioni.add(sceltaImpostazioneQuattro);

		
		menubar.add(menuImpostazioni);
		menubar.add(credit);
		setJMenuBar(menubar);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("logo.png").getFile());
        
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(new JLabel(icon),BorderLayout.NORTH);
		add(north,BorderLayout.NORTH);
		JPanel center = new JPanel();
		selectLevel = new JLabel(res.getString("gamelevel"));
		center.add(selectLevel,BorderLayout.CENTER);
		add(center,BorderLayout.CENTER);
		JPanel centrale = new JPanel();
		bottoneFacile = new JButton(res.getString("gamelevel1"));
		bottoneFacile.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				startGame(SelectLevel.LOW);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		centrale.add(bottoneFacile,BorderLayout.CENTER);
		bottoneMedio = new JButton(res.getString("gamelevel2"));
		bottoneMedio.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				startGame(SelectLevel.MEDIUM);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		centrale.add(bottoneMedio,BorderLayout.CENTER);
		bottoneDifficile = new JButton(res.getString("gamelevel3"));
		bottoneDifficile.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				startGame(SelectLevel.HIGH);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		centrale.add(bottoneDifficile,BorderLayout.WEST);
		centrale.add(new JLabel(" "),BorderLayout.SOUTH);
		add(centrale,BorderLayout.SOUTH);
		setVisible(true);
		setSize(300,300);
		setTitle("WordPuz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void refresh() {
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		menuImpostazioni.setText(res.getString("settings"));
		sceltaImpostazioneUno.setText(res.getString("showscore"));
		sceltaImpostazioneDue.setText(res.getString("selectlanguage"));
		/**sceltaImpostazioneTre.setText(res.getString("manual"));*/
		sceltaImpostazioneQuattro.setText(res.getString("exit"));
		selectLevel.setText(res.getString("gamelevel"));
		bottoneFacile.setText(res.getString("gamelevel1"));
		bottoneMedio.setText(res.getString("gamelevel2"));
		bottoneDifficile.setText(res.getString("gamelevel3"));
	}
	private void startGame(int secondi) {
		try {
			GameFrame frameGioco = new GameFrame(secondi,Loader.Letters(),Loader.Dictionary());
			final Thread t = new Thread(frameGioco);
			t.start();
			setVisible(false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}