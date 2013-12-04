package eu.miclen.wordpuz.graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectLanguage extends JFrame{

	private static final long serialVersionUID = 1L;
	public SelectLanguage(final SelectLevel top) {	
		ResourceBundle resLn = ResourceBundle.getBundle("locale",Locale.getDefault());
		setTitle(resLn.getString("selectlanguage"));
		JPanel panel = new JPanel(new BorderLayout());
		final JRadioButton it = new JRadioButton("Italiano");
		final JRadioButton en = new JRadioButton("English");
		ButtonGroup scelta = new ButtonGroup();
		if(Locale.getDefault() == Locale.ENGLISH) en.setSelected(true); else it.setSelected(true);
		scelta.add(it);
		scelta.add(en);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(it);
		panel1.add(en);
		JPanel panelnorth = new JPanel();
		panelnorth.setLayout(new BorderLayout());
		panelnorth.add(new JLabel(resLn.getString("selectlanguagelabel")),BorderLayout.CENTER );
		panelnorth.add(new JPanel(),BorderLayout.EAST );
		panelnorth.add(new JPanel(),BorderLayout.WEST );
		panelnorth.add(new JPanel(),BorderLayout.NORTH );
		panel.add(panel1,BorderLayout.CENTER);
		panel.add(new JLabel(),BorderLayout.EAST);
		panel.add(new JPanel(),BorderLayout.WEST);
		setLayout(new BorderLayout());
		add(panelnorth,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		add(new JPanel(),BorderLayout.EAST);
		add(new JPanel(),BorderLayout.WEST);
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		JPanel southinternal = new JPanel();
		southinternal.setLayout(new GridLayout(1,2));
		southinternal.add(new JPanel());
		JButton aggiorna = new JButton(resLn.getString("refresh"));
		aggiorna.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				if(it.isSelected()) {
					Locale.setDefault(Locale.ITALIAN);	
				}
				else {
					Locale.setDefault(Locale.ENGLISH);	
				}
				top.refresh();
				setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		southinternal.add(aggiorna);

		south.add(southinternal,BorderLayout.CENTER);
		south.add(new JPanel(),BorderLayout.WEST);
		south.add(new JPanel(),BorderLayout.EAST);
		south.add(new JPanel(),BorderLayout.SOUTH);
		add(south,BorderLayout.SOUTH);

		setSize(300,120);
		setResizable(false);
		setVisible(true);
	}
}