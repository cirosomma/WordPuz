package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eu.miclen.wordpuz.ManageScore;

public class ShowScore extends JFrame{

	private static final long serialVersionUID = 1L;
	private ResourceBundle res;
	public ShowScore() throws IOException {
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		setLayout(new BorderLayout());
		ArrayList<String> punteggi = ManageScore.visualizza();
		String[] nuove = new String[punteggi.size()];
		int i=punteggi.size()-1;
		for(String x : punteggi) {
			nuove[i--] = x;
		}
		JScrollPane scroll = new JScrollPane();
		JList<String> lista = new JList<String>(nuove);
		lista.setBackground(this.getBackground());
		scroll.setPreferredSize(new Dimension(270,200));
		scroll.setViewportView(lista);
		
		add(scroll,BorderLayout.CENTER);
		JPanel panel3 = new JPanel();
		panel3.add(new JLabel(res.getString("savedscores")) ,BorderLayout.CENTER);
		add(panel3,BorderLayout.NORTH);
		//add(panel1,BorderLayout.CENTER);
		JPanel panel2 = new JPanel();
		JButton del = new JButton(res.getString("reset"));
		del.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				ManageScore.reset();
				setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		panel2.add(del);
		add(panel2,BorderLayout.SOUTH);
		setTitle(res.getString("savedscores").substring(0, res.getString("savedscores").length()-1));
		setSize(300,300);
		setLocation(370,0);
		setResizable(false);
		setVisible(true);
	}
}
