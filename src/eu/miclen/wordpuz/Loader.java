package eu.miclen.wordpuz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Loader {
	public static HashMap<String,Integer> Dictionary() throws FileNotFoundException {
		File file = new File(ClassLoader.getSystemResource("dictionary_en.txt").getFile());
		if(Locale.getDefault() == Locale.ITALIAN)
			file = new File(ClassLoader.getSystemResource("dictionary_it.txt").getFile());
		Scanner in = new Scanner(file);
		HashMap<String,Integer> dizionarioParole = new HashMap<String,Integer>();
		while(in.hasNextLine()) {
			String x = in.nextLine();
			if(x.length() > 2)
				dizionarioParole.put(x, x.length());
		}
		in.close();
		return dizionarioParole;
	}
	public static Character[] Letters() {
		Character[] lettereBox =new Character[16];
		int numeroVocali;
		do {
			numeroVocali = 0;
			for(int i=0;i<16;i++) {
				/** Carico una lettera random */
				lettereBox[i] = (char) (Math.round((Math.random()*25)%25) + 65);
				if(lettereBox[i] == 'A') numeroVocali++;
				else if(lettereBox[i] == 'E') numeroVocali++;
				else if(lettereBox[i] == 'I') numeroVocali++;
				else if(lettereBox[i] == 'O') numeroVocali++;
				else if(lettereBox[i] == 'U') numeroVocali++;
			}
		}
		while(numeroVocali <= 4);
		return lettereBox;
	}
}
