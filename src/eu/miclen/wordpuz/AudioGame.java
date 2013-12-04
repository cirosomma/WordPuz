package eu.miclen.wordpuz;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioGame {
	public static void playSound(String type) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File f;
		if(type.equals("ok"))
			f = new File(ClassLoader.getSystemResource("ok.wav").getFile());
		else
			f = new File(ClassLoader.getSystemResource("error.wav").getFile());
        AudioInputStream audio = AudioSystem.getAudioInputStream(f);
        AudioFormat format;
        format = audio.getFormat();
        SourceDataLine auline;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        auline = (SourceDataLine) AudioSystem.getLine(info);
        auline.open(format);
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[524288];
        while (nBytesRead != -1) {
            nBytesRead = audio.read(abData, 0, abData.length);
            if (nBytesRead >= 0) {
                auline.write(abData, 0, nBytesRead);
            }
        }
	}
}
