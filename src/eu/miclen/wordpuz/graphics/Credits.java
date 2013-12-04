package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Credits extends JFrame {

	public String version = "1.0";
	public String autore = "Ciro Somma";
	public String email = "miclen.developers@gmail.com";
	public String website = "www.miclen.eu";
	public String data = "NaN";
	private ResourceBundle res;
	
	private static final long serialVersionUID = 1L;
	public Credits() {
		
		res = ResourceBundle.getBundle("locale",Locale.getDefault());
		
		setLayout(new BorderLayout());
		add(new JPanel(),BorderLayout.NORTH);
		add(new JPanel(),BorderLayout.SOUTH);
		add(new JPanel(),BorderLayout.WEST);
		add(new JPanel(),BorderLayout.EAST);
		JPanel principale = new JPanel();
		principale.setLayout(new BorderLayout());
		principale.setBackground(Color.white);
		principale.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel center = new JPanel();
		center.setBackground(Color.white);
		center.setLayout(new GridLayout(5,2));
		
		Font myFont = new Font("MONOSPACED", Font.BOLD, 15);
		JLabel JVersion = new JLabel(res.getString("version"));
		JVersion.setFont(myFont);
		center.add(JVersion);
		center.add(new JLabel(version));
		JLabel JAuthor = new JLabel(res.getString("author"));
		JAuthor.setFont(myFont);
		center.add(JAuthor);
		center.add(new JLabel(autore));

		JLabel JEmail = new JLabel(res.getString("email"));
		JEmail.setFont(myFont);
		center.add(JEmail);
		center.add(new JLabel(email));

		JLabel JWebSite = new JLabel(res.getString("website"));
		JWebSite.setFont(myFont);
		center.add(JWebSite);
		center.add(new JLabel(website));

		JLabel JData = new JLabel(res.getString("data"));
		JData.setFont(myFont);
		center.add(JData);
		center.add(new JLabel(data));
		
		principale.add(center,BorderLayout.CENTER);
		principale.add(new JLabel(" "),BorderLayout.EAST);
		principale.add(new JLabel(" "),BorderLayout.WEST);

		add(principale,BorderLayout.CENTER);
		setTitle("Credits");
		setSize(500,300);
		setLocation(370,0);
		setVisible(true);
	}
}