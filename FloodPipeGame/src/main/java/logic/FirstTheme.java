package logic;

import gui.ThemeDisplayer;
import javafx.scene.media.MediaPlayer;

public class FirstTheme implements ThemeDisplayer {
	public MediaPlayer media;
	//boolean ismuted = false;
	/*public Image backgroundImage;

	public FirstTheme() {
		try {
			this.backgroundImage = new Image(new FileInputStream("./resources/FirstTheme/Background.jpg"));
			this.music = new Media (new File("./resources/FirstTheme/song.mp3").toURI().toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/
	
	@Override
	public String get_Line() {
		return "./resources/FirstTheme/-.jpg";
	}

	@Override
	public String get_I() {
		return "./resources/FirstTheme/I.jpg";
	}

	@Override
	public String getPipe_7() {
		return "./resources/FirstTheme/7.jpg";
	}
	public String getPipe_7A() {
		return "./resources/FirstTheme/7.jpg";
	}
	@Override
	public String getPipe_J() {
		return "./resources/FirstTheme/J.jpg";
	}

	@Override
	public String getPipe_L() {
		return "./resources/FirstTheme/L.jpg";
	}

	@Override
	public String getPipe_F() {
		return "./resources/FirstTheme/F.jpg";
	}
	
	@Override
	public String get_s() {
		return getConnection();
	}
	
	@Override
	public String get_g() {
		return getConnection();
	}

	@Override
	public String getBackgroundImage() {
		return "./resources/FirstTheme/Background.jpg";
	}

	@Override
	public String getMusic() {
		return "./resources/FirstTheme/song.mp3";
	}
	public String getVideo() {
		return "./resources/Video/BackVideo.mp4";
	}
	public String getConnection(){
		return "./resources/FirstTheme/-.jpg";

	}
	public String getStartConect(){

		return  "./resources/FirstTheme/StartingPoint.png";
	}
	@Override
	public void stopMusic() {
		if(media!=null)
		{
			media.stop();
		}


	}

	
}