import java.awt.*;

public class GamePiece
	{		
		int side=43;
		int x, y;
		int alive=0; //0 means alive
        Color clr, oc;		
		static Graphics g;
		public static void setGraphics(Graphics gr){
			g=gr;
		}
		GamePiece(int x, int y, Color clr){
			oc=g.getColor(); g.setColor(clr);
			this.clr=clr; this.x=x; this.y=y;
			g.fillOval(x, y, side, side);
			g.setColor(oc);
		}
		GamePiece(Square sq){
			oc=g.getColor(); g.setColor(clr);
			this.clr=clr; this.x=sq.x; this.y=sq.y;
			g.fillOval(x, y, side, side);
			g.setColor(oc);
		}
		void gamePiece(Color clr){
			oc=g.getColor();
			this.clr=clr;
			g.setColor(this.clr);
            g.fillOval(x, y, side, side);
			g.setColor(oc);
        }
		boolean match(GamePiece gp){
			if(x==gp.x && y==gp.y){
				return true;
			}else{
				return false;
			}
		}
	}