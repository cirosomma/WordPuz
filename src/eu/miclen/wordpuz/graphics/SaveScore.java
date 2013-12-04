package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eu.miclen.wordpuz.ManageScore;

public class SaveScore extends JFrame {

	private static final long serialVersionUID = 1L;
	private ResourceBundle res;
	public SaveScore(int punteggio) {
		final int p = punteggio;
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		setTitle(res.getString("score").substring(0, res.getString("score").length()-1));
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(new JLabel(res.getString("score")+p));
		add(north,BorderLayout.NORTH);
		JPanel saveScore = new JPanel();
		JButton salva = new JButton(res.getString("savescore"));
		salva.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date today = Calendar.getInstance().getTime();				
				String reportDate = df.format(today);
				try {
					ManageScore.inserisci(p+ " "+reportDate);
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, res.getString("msgsavescore"));
				System.exit(1);
				
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		saveScore.add(salva,BorderLayout.CENTER);
		add(saveScore,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		add(new JPanel(),BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200,110);
		setResizable(false);
	}
}
