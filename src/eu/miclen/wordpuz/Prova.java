package eu.miclen.wordpuz;

import javax.swing.ImageIcon;

public class Prova {
	public static void main(String[] args) {
		new ImageIcon(Prova.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"res/logo.png");
	}
}
