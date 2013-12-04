package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class WordFound extends JFrame{

	private static final long serialVersionUID = 1L;
	private ResourceBundle res;
	private JList<String> listaParole;
	private ArrayList<String> parole;
	public WordFound() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (dimension.getWidth() + GameFrame.width/5)/2;
		int y = (int) ((dimension.getHeight() - GameFrame.height) / 3);
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		setLocation(x, y);
		setSize(200,400);
		setTitle(res.getString("wordfind").substring(0, res.getString("wordfind").length()-1));
		setResizable(false);
		parole = new ArrayList<String>();
		listaParole = new JList<String>();
        DefaultListModel<String> model = new DefaultListModel<String>();
        listaParole.setModel(model);
        setLayout(new BorderLayout());
		add(listaParole,BorderLayout.CENTER);
		JPanel testo = new JPanel();
		testo.add(new JLabel(res.getString("wordfind")));
		add(testo,BorderLayout.NORTH);
		add(new JPanel(),BorderLayout.EAST);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.SOUTH);

		setVisible(true);
	}
	public void aggiungiParola(String parola) {
		parole.add(parola);
		String[] nuove = new String[parole.size()];
		int i=parole.size()-1;
		for(String x : parole) {
			nuove[i--] = x;
		}
		listaParole.setListData(nuove);
	}

}
