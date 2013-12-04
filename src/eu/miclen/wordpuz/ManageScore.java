package eu.miclen.wordpuz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManageScore{
	
	public static ArrayList<String> visualizza() throws IOException {
		
		ArrayList<String> listaPunteggi = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(ClassLoader.getSystemResource("score.txt").getFile()));
			String text;
			while((text = in.readLine()) != null) {
				listaPunteggi.add(text);
			}	
			in.close();
		} catch (FileNotFoundException e) {
			File file = new File(ClassLoader.getSystemResource("score.txt").getFile());
			file.createNewFile();
		}
		return listaPunteggi;

	}
	public static void reset() {
		File file = new File(ClassLoader.getSystemResource("score.txt").getFile());
		file.delete();
	}
	public static void inserisci(String nuovoPunteggio) throws IOException {
		ArrayList<String> listaPunteggiAttuali = new ArrayList<String>();
		for(String tmp : visualizza()){
			listaPunteggiAttuali.add(tmp);
		}
		FileWriter file = new FileWriter(ClassLoader.getSystemResource("score.txt").getFile());
		for(String tmp : listaPunteggiAttuali){
			file.write(tmp+"\n");
		}
		file.write(nuovoPunteggio+"\n");
		file.close();
	}
}
