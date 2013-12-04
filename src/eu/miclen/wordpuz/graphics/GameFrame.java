package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eu.miclen.wordpuz.AudioGame;

public class GameFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int width = 400;
	public static final int height = 400;
	private static final String title = "WordPuz";
	private JLabel JPunteggio;
	private JLabel JParolaInserita = new JLabel("");
	private HashMap<String,String> paroleTrovate;
	private WordFound JParoleTrovate;
	private JLabel JTempoRestante;
	private static boolean parolaCliccata = false;
	private ArrayList<JButton> parolaAttuale;
	private static Thread tempoMassimoInserimento;
	private int punteggio;
	private Character[] lettereBox;
	private int secondiGioco;
	private HashMap<String,Integer> dizionarioParole;
	private ResourceBundle res;
	public void run() {
		visualizza();
	}
	public void visualizza() {
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		JPunteggio = new JLabel(res.getString("score"));
		JTempoRestante = new JLabel(res.getString("remainingtime"));
		setSize(width,height);
		setTitle(title);
		setResizable(false);
		this.add(crea_tabella(),BorderLayout.CENTER);
		this.add(crea_cronometro(),BorderLayout.NORTH);
		JPanel sud = new JPanel();
		sud.setLayout(new BorderLayout());
		sud.add(JPunteggio,BorderLayout.WEST);
		sud.add(JParolaInserita,BorderLayout.EAST);
		sud.setBackground(Color.white);
		this.add(sud,BorderLayout.SOUTH);
		JPanel east = new JPanel();
		east.setBackground(Color.blue);
		this.add(east,BorderLayout.EAST);
		JPanel west = new JPanel();
		west.setBackground(Color.blue);
		this.add(west,BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.blue);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 3);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 3);
		JParoleTrovate = new WordFound();
		setLocation(x, y);
		setVisible(true);
	}
	public GameFrame(int secondiGioco,Character[] lettereBox,HashMap<String,Integer> dizionarioParole) {
		parolaAttuale = new ArrayList<JButton>();
		this.secondiGioco = secondiGioco;
		this.lettereBox = lettereBox;
		this.dizionarioParole = dizionarioParole;
		this.paroleTrovate = new HashMap<String,String>();
	}
	public JPanel crea_tabella() {
		JPanel boxGioco = new JPanel();
		boxGioco.setLayout(new GridLayout(4,4));
		Font myFont = new Font("MONOSPACED", Font.BOLD, 30);
		for(int i=0;i<16;i++) {
			final JButton bottoneLettera = new JButton(""+lettereBox[i]);
			bottoneLettera.setFont(myFont);
			bottoneLettera.setBackground(Color.white);
			bottoneLettera.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					if(parolaCliccata && bottoneLettera.getBackground()!=Color.ORANGE) {
						parolaAttuale.add(bottoneLettera);
						bottoneLettera.setBackground(Color.orange);
						if(tempoMassimoInserimento!=null) tempoMassimoInserimento.interrupt();
						tempoMassimoInserimento = new Thread(new Runnable() {

							public void run() {
								try {
									Thread.sleep(1500);
									parolaCliccata = false;
									String parolaInserita = estraiParola(parolaAttuale).toLowerCase();
									if(dizionarioParole.containsKey(parolaInserita) && !paroleTrovate.containsKey(parolaInserita)) {
										punteggio += dizionarioParole.get(parolaInserita);
										paroleTrovate.put(parolaInserita, null);
										JParoleTrovate.aggiungiParola(parolaInserita+ "    +"+dizionarioParole.get(parolaInserita));
										aggiornaPunteggio(parolaInserita);
										AudioGame.playSound("ok");
									}
									else {
										AudioGame.playSound("error");
									}
								} catch (InterruptedException e) {
								} catch (UnsupportedAudioFileException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} catch (LineUnavailableException e) {
									e.printStackTrace();
								}
							}
							
						});
						tempoMassimoInserimento.start();
					}
					else if(bottoneLettera.getBackground()!=Color.orange){
						parolaCliccata=true;
						bottoneLettera.setBackground(Color.orange);
						parolaAttuale.add(bottoneLettera);
					}
				}

				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
			boxGioco.add(bottoneLettera);
		}
		return boxGioco;
	}
	public JPanel crea_cronometro() {
		JPanel tab = new JPanel();
		tab.setBackground(Color.blue);
		tab.setLayout(new BorderLayout());
		tab.setBackground(Color.orange);
		JTempoRestante.setForeground(Color.white);
		tab.add(JTempoRestante,BorderLayout.CENTER);
		tab.add(new JLabel("  "),BorderLayout.SOUTH);
		tab.add(new JLabel("  "),BorderLayout.NORTH);
		tab.add(new JLabel("   "),BorderLayout.EAST);
		tab.add(new JLabel("   "),BorderLayout.WEST);
		new Thread(new Runnable() {
			public void run() {
				for(int i=secondiGioco;i>=0;i--) {
					try {
						Thread.sleep(1000);
						int minuti = i/60;
						int secondi = i%60;
						JTempoRestante.setText(res.getString("remainingtime")+minuti+":"+secondi);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				new SaveScore(punteggio);
				JParoleTrovate.dispose();
				setVisible(false);
			}
		}).start();

		return tab;
	}
	public String estraiParola(ArrayList<JButton> bottoniCliccati) {
		String parola = "";
		for(JButton tmp : bottoniCliccati) {
			parola = parola.concat(tmp.getText());
			tmp.setBackground(Color.white);
		}
		while(!bottoniCliccati.isEmpty()) bottoniCliccati.remove(0);
		return parola;
	}
	public void aggiornaPunteggio(String parolaAggiunta) {
		final String parolaVisualizzata = parolaAggiunta;
		JPunteggio.setText(res.getString("score")+punteggio);
		new Thread(new Runnable() {
			public void run() {
				JParolaInserita.setText(res.getString("word")+parolaVisualizzata + " +"+parolaVisualizzata.length());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				JParolaInserita.setText("");
			}
		}).start();
	}
}