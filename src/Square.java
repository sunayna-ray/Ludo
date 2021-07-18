import java.applet.Applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Square{
		int side=43;
        int x, y, state=1; //0 means safe
        Color clr=Color.white, oc;
		static Graphics g;
		public static void setGraphics(Graphics gr){
			g=gr;
		}
		Square(){
			
		}
        Square(int x, int y){
            g.drawRect(x, y, side, side);
            this.x=x;
            this.y=y;
        }
        Square(int x, int y, Color clr ){
            oc=g.getColor();
			g.setColor(clr);
            g.fillRect(x, y, side, side);
            this.x=x;
            this.y=y;
            this.clr=clr;
			this.state=0;
            g.setColor(oc);
        }
        Square(Square sq){
            this.x=sq.x;
            this.y=sq.y;
            this.clr=sq.clr;
        }
		void draw(){
			if (clr==Color.white){
				g.drawRect(x, y, side, side);
			}else{
				oc=g.getColor();
				g.setColor(clr);
				g.fillRect(x, y, side, side);
				g.setColor(oc);
			}
		}
        void check(Square[] sq){
            for(int i=0; i<sq.length; i++){
                g.drawString(String.valueOf(i), sq[i].x, sq[i].y);
            }
        }
	}