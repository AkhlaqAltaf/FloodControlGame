package logic;

import gui.IViewModel;
import javafx.beans.property.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModel implements IViewModel {

	private ModelPg modelpg;
	public ListProperty<char[]> pgboard;
	public BooleanProperty isGoal;
	public StringProperty timeLeft;
	private Timer timer;
	private TimerTask task;
	public IntegerProperty countStep=new SimpleIntegerProperty();
	//public IntegerProperty timer=new SimpleIntegerProperty();


	public ViewModel(ModelPg modelpg) {
		this.modelpg=modelpg;
		this.pgboard = new SimpleListProperty<>();
		this.pgboard.bind(modelpg.pgboard);
	}

	public void switchCell(int i, int j) {
		this.modelpg.switchCell(i, j);
	}

	public boolean isGoal() {
		return this.modelpg.isGoal();
	}

	public boolean save() throws IOException  {
	return this.modelpg.save();

	}

	public List<String> solve() {
		return this.modelpg.solve();

	}
	public void setPort(int port) {
		this.modelpg.setPort(port);
	}

	public void setHost(String host) {
		this.modelpg.setHost(host);
	}


	public ModelPg getModelpg() {
		return modelpg;
	}

	public void setModelpg(ModelPg modelpg) {
		this.modelpg = modelpg;
	}

	public void load(File file) {
		this.modelpg.load(file);
	}
	
	public boolean finish()throws IOException, InterruptedException
	{
		return this.modelpg.finishGame();
	}

}
