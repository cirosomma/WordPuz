package eu.miclen.wordpuz;
import java.io.IOException;
import java.util.Locale;
import eu.miclen.wordpuz.graphics.*;

public class WordPuz{
	public  static void main(String[] args) throws InterruptedException, IOException {
		Locale.setDefault(Locale.ENGLISH);
		new SelectLevel();
	}

}
