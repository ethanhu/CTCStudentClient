package ctc.ctc.drawctc.station1.sound;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundPlayer {
	
	private static SoundPlayer SP =null;
	
	public String SOUNDS_PATH = System.getProperty("user.dir") + "\\resources\\sounds\\";
	
	public static SoundPlayer getInstance(){
		
		if(SP == null){
			SP = new SoundPlayer();
		}
		return SP;
	}
	
	//准备接车	
	public void soundZBJC() {
		try {
			InputStream is = new FileInputStream(SOUNDS_PATH + "zbjc.wav");
			AudioStream soundStream = new AudioStream(is);
			AudioPlayer.player.start(soundStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//操作错误
	public void soundCZCW() {
		try {
			InputStream is = new FileInputStream(SOUNDS_PATH + "czcw.wav");
			AudioStream soundStream = new AudioStream(is);
			AudioPlayer.player.start(soundStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//操作无效
	public void soundCZWX() {
		try {
			InputStream is = new FileInputStream(SOUNDS_PATH + "czwx.wav");
			AudioStream soundStream = new AudioStream(is);
			AudioPlayer.player.start(soundStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//进路选不出
	public void soundJLXBC() {
		try {
			InputStream is = new FileInputStream(SOUNDS_PATH + "jlxbc.wav");
			AudioStream soundStream = new AudioStream(is);
			AudioPlayer.player.start(soundStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {		
		
		SoundPlayer SP = SoundPlayer.getInstance();
		System.out.println(SP.SOUNDS_PATH);
		SP.soundCZWX();

	}
	
}
