//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package logic;

import gui.ThemeDisplayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.ThemeMap;

import java.util.List;

public class PipeDisplayer extends Canvas {
    private List<char[]> pgboard;
    ThemeDisplayer theme;
    double w;
    double h;

    public PipeDisplayer() {
    }

    public void setPipeData(List<char[]> pgboard, ThemeDisplayer themeDisp) {
        this.setPipeTheme(themeDisp);
        this.setpipeboard(pgboard);
    }

    public void setpipeboard(List<char[]> pgboard) {
        this.pgboard = pgboard;
        this.redraw();
    }

    public void redraw() {
        if (this.pgboard != null) {
            double W = this.getWidth();
            double H = this.getHeight();
            this.w = W / (double)((char[])this.pgboard.get(0)).length;
            this.h = H / (double)this.pgboard.size();
            GraphicsContext gc = this.getGraphicsContext2D();
            gc.clearRect(0.0, 0.0, W, H);

            for(int i = 0; i < this.pgboard.size(); ++i) {
                for(int j = 0; j < ((char[])this.pgboard.get(i)).length; ++j) {
                    if (((char[])this.pgboard.get(i))[j] != ' ') {
                        gc.drawImage(ThemeMap.getInstance().getImage(((char[])this.pgboard.get(i))[j]), (double)j * this.w, (double)i * this.h, this.w, this.h);
                    }
                }
            }
        }

    }

    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        this.redraw();
    }

    public boolean isResizable() {
        return true;
    }

    public double minHeight(double width) {
        return 100.0;
    }

    public double maxHeight(double width) {
        return 1200.0;
    }

    public double prefHeight(double width) {
        return this.minHeight(width);
    }

    public double minWidth(double height) {
        return 0.0;
    }

    public double maxWidth(double height) {
        return 10000.0;
    }

    public void switchCell(int i, int j, int times) {
        for(int t = 0; t < times; ++t) {
            this.redraw();
            if (t < times - 1) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException var6) {
                }
            }
        }

    }

    public void setPipeTheme(ThemeDisplayer themedisp) {
        ThemeMap.getInstance().setTheme(themedisp);
    }

    public double getW() {
        return this.w;
    }

    public double getH() {
        return this.h;
    }
}
