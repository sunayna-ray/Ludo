import java.applet.Applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/*<applet code="LudoGreenOnly" width="300" height="400"></applet>*/
public class LudoGreenOnly extends Applet implements ActionListener
{	
	Graphics g=getGraphics();
	final Color blue=new Color(45, 66, 85);
	final Color green=new Color(22, 84, 43);
	final Color yellow= new Color(194, 192, 31);
	final Color red= new Color(120, 69, 68);
	final Font f2=new Font("Arial",Font.BOLD,35);
	int chance=0;
	int valgenerate=0;
	int current_blue=0, current_green=0, current_red=0, current_yellow=0;
	int count_piece_green=0, count_piece_blue=0, count_piece_red=0, count_piece_yellow=0;
	TextField tg, tb, tr, ty;
	TextField tgc, tbc, trc, tyc;
	Label l1, l2, l3, l4, l5, l6, l7, l8;
	Button bb, b2;
	int valg=0,valb=0,valr=0,valy=0;
    int side=43;	
		
    Square[] bluesq=new Square[56];
    Square[] greensq=new Square[56];
	Square[] redsq=new Square[56];
    Square[] yellowsq=new Square[56];
	
	GamePiece[] piecegreen=new GamePiece[4];
	GamePiece[] pieceblue=new GamePiece[4];
	GamePiece[] piecered=new GamePiece[4];
	GamePiece[] pieceyellow=new GamePiece[4]; 
	
	class B2Listen implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			pieceblue[0]=new GamePiece(bluesq[0]);
			piecered[0]=new GamePiece(redsq[0]);
			piecegreen[0]=new GamePiece(greensq[0]);
			pieceyellow[0]=new GamePiece(yellowsq[0]);
			
			pieceblue[1]=new GamePiece(500,183,blue);pieceblue[2]=new GamePiece(371,54,blue);pieceblue[3]=new GamePiece(500,54,blue);
			piecegreen[1]=new GamePiece(758,441,green);piecegreen[2]=new GamePiece(887,570,green);piecegreen[3]=new GamePiece(758,570,green);
			pieceyellow[1]=new GamePiece(500,441,yellow);pieceyellow[2]=new GamePiece(371,570,yellow);pieceyellow[3]=new GamePiece(500,570,yellow);
			piecered[1]=new GamePiece(758,183,red);piecered[2]=new GamePiece(887,54,red);piecered[3]=new GamePiece(758,54,red);
			
			valb=0; valg=0; valr=0; valy=0; valgenerate=0; count_piece_green=0; count_piece_blue=0; count_piece_red=0; count_piece_yellow=0;
			bb.setLabel("DICE");
						
			repaint();
		}
	}
        
	public void init(){
        Square.setGraphics(getGraphics());
		GamePiece.setGraphics(getGraphics());
		setBoard(0);
		//add(tt);
		add(bb);add(b2);
		add(l1);add(tg);
		add(l3);add(tgc);
		b2.addActionListener(new B2Listen());
		bb.addActionListener(this);
    }
    public void paint(Graphics g)
    {
		setBoard(1);
		tg.setText(valg+"");
		paintGreen();
		tgc.setText(count_piece_green+"");
		setPieces();
    }
	public void actionPerformed(ActionEvent ae)
	{
		
		valgenerate=1+((int)(Math.random()*(6-1+1)));
		bb.setLabel(valgenerate+"");
		if(chance%2==1)
		{
			moveGreen();
		}
		chance++;
	}
    public void setBoard(int mode){
        Graphics g=getGraphics();
		g.setColor(new Color(230, 211, 128));//border
		g.fillRect(320,5,660,656);
		
		g.setColor(Color.white);//main box
        g.fillRect(323,8,654,650);//328, 15, 645, 645
		
		g.setColor(new Color(230, 211, 128));//goal box
		g.fillRect(588, 271, 125, 125);		
		g.setColor(Color.black);
		Font f=new Font("Arial",Font.PLAIN,20);
		g.setFont(f);
		g.drawString("GOAL", 624,335);
		
        g.setColor(new Color(81, 118, 151));//blue box
        g.fillRect(327,10,258,258);
        g.setColor(new Color(159, 94, 91));//red box
        g.fillRect(717,10,258,258);
        g.setColor(new Color(228, 228, 90));//yellow box
        g.fillRect(327,400,258,257);
        g.setColor(new Color(45, 166, 85));//green box
        g.fillRect(717,400,258,257);
		
        Square s;
        
		if (mode==0){			
			int bluec=0, k=0, t=0, redc=51, yellowc=55, greenc=51;
			for(int i=269; i<=(269+side*2); i+=side){
				for(int j=328; j<=(328+side*5); j+=side){
					if(k>11 && k<18){
						bluec=61-k;//greenc=bluec-26;
						bluesq[bluec]=new Square(j, i);
					}else if(k>5 && k<12){
						bluec=k+44;
						if(k>6)
							bluesq[bluec]=new Square(j, i,new Color(81, 118, 151));
						else
							bluesq[bluec++]=new Square(j, i);
					}else if(k>0 && k<6){
						if(k==1)
							bluesq[bluec++]=new Square(j, i,new Color(81, 118, 151));
						else 
							bluesq[bluec++]=new Square(j, i);// greenc=bluec-26;
					}
					else{
						redsq[38]=new Square(j, i);
						greensq[25]=redsq[38];
					}k++;
				}
			}t=18;k=0;
			for(int j=586; j<=(586+side*2); j+=side){
				for(int i=11; i<=(15+side*5); i+=side){
					if(k<6){
						bluec=10-k;//greenc=bluec-26;
						bluesq[bluec]=new Square(j, i);
					}else if(k==6){
						bluesq[11]=new Square(j, i);
					}else if(k>11){
						bluec=k;//greenc=bluec-26;
						if(k==13&&j==672)
						bluesq[bluec]=new Square(j, i,new Color(159, 94, 91));
						else
						bluesq[bluec]=new Square(j, i);
					}
					else{
						redsq[redc++]=new Square(j, i,new Color(159, 94, 91));
					}k++;
				}
			}yellowsq[25]=bluesq[12];
			k=0;greenc=55;
			for(int i=269; i<=(269+side*2); i+=side){
				for(int j=715; j<=(715+side*5); j+=side){	
					if(k<6){
						bluec=18+k;//greenc=bluec-26;
						bluesq[bluec]=new Square(j, i);                    
					}else if(k==11){
						bluec=24;//greenc=bluec-26;
						bluesq[bluec]=new Square(j, i);                    
					}else if(k>11){
						bluec=k+t;//greenc=bluec-26;  
						if(k==16)
							bluesq[bluec]=new Square(j, i,new Color(45, 166, 85));
						else
							bluesq[bluec]=new Square(j, i);   
						t-=2;
					}
					else{
						if(greenc>50)
							greensq[greenc]=new Square(j, i,new Color(45, 166, 85));
						else
							greensq[greenc]=new Square(j, i);
						greenc--;
					}k++;
				}
			}k=0;
			for(int j=586; j<=(586+side*2); j+=side){
				for(int i=398; i<=(398+side*5); i+=side){
					if(k>11 && k<18){
						bluec=k+19;
						bluesq[bluec]=new Square(j, i);
					}else if(k==11){
						bluec=37;
						bluesq[bluec]=new Square(j, i);
					}else if(k>11){
						bluec=k+t;//greenc=bluec-26;
						bluesq[bluec]=new Square(j, i);                
						t-=2;
					}else if(k<6){
						bluec=43-k;
						if(k==4)
							bluesq[bluec]=new Square(j, i,new Color(228, 228, 90));      
						else			
							bluesq[bluec]=new Square(j, i);
					}else{
						yellowsq[yellowc--]=new Square(j, i,new Color(228, 228, 90));
					}k++;
				}
			}
			for(int i=8; i<48; i+=13){//All safe states
				bluesq[i].state=0;
			}			
			for(int i=13; i<51; i++){
				redsq[i-13]=bluesq[i];
			}
			for(int i=0; i<12; i++){
				redsq[i+39]=bluesq[i];
			}
			for(int i=0; i<25; i++){
				greensq[i+26]=bluesq[i];
			}
			for(int i=26; i<51; i++){
				greensq[i-26]=bluesq[i];
			}
			for(int i=0; i<25; i++){
				yellowsq[i+26]=redsq[i];
			}
			for(int i=26; i<51; i++){
				yellowsq[i-26]=redsq[i];
			}
			for(int i=8; i<48; i+=13){
				bluesq[i].state=0;
			}bb=new Button("DICE");
			b2=new Button("START");
			tg=new TextField ();
			tb=new TextField();
			tgc=new TextField();
			tbc=new TextField();
			tr=new TextField ();
			ty=new TextField();
			trc=new TextField();
			tyc=new TextField();
			l1=new Label("Total Value of Green");
			l2=new Label("Total Value of Blue");
			l3=new Label(" Green Pieces Completed");
			l4=new Label("  Blue Pieces Completed");
			l5=new Label("Total Value of Red");
			l6=new Label("Total Value of Yellow");
			l7=new Label("   Red Pieces Completed");
			l8=new Label("Yellow Pieces Completed");
			setLayout(null);
			//tt.setBounds(200,10, 0,0);
			bb.setBounds(1000,50, 80,80);
			b2.setBounds(200,50, 80,80);	
			l1.setBounds(1000,215,150,40);
			tg.setBounds(1150,225,40,20);
			l2.setBounds(1000,305,150,40);
			tb.setBounds(1150,315,40,20);
			l3.setBounds(60,200,140,40);
			tgc.setBounds(230,210,40,20);
			l4.setBounds(60,290,140,40);
			tbc.setBounds(230,300,40,20);
		
			l5.setBounds(1000,395,150,40);
			tr.setBounds(1150,405,40,20);
			l6.setBounds(1000,485,150,40);
			ty.setBounds(1150,495,40,20);
			l7.setBounds(60,380,140,40);
			trc.setBounds(230,390,40,20);
			l8.setBounds(60,470,140,40);
			tyc.setBounds(230,480,40,20);
							
			piecegreen[0]=new GamePiece(887,441,green);
			piecegreen[1]=new GamePiece(758,441,green);
			piecegreen[2]=new GamePiece(887,570,green);
			piecegreen[3]=new GamePiece(758,570,green);	
			setPieces();
			
		}else{ //mode==1
			for(int i=0; i<56; i++){
				bluesq[i].draw();
			}
			for(int i=50; i<56; i++){
				redsq[i].draw();
				greensq[i].draw();
				yellowsq[i].draw();
			}
			greensq[25].draw();
		}
		for(int i=8; i<48; i+=13){//All safe states
			Font f1=new Font("Serif",Font.PLAIN,50);
			g.setFont(f1);
			g.setColor(new Color(77, 77, 77));
			g.drawString("*", bluesq[i].x+7, (bluesq[i].y+50));
			g.drawString("*", bluesq[i-8].x+7, (bluesq[i-8].y+50));
		}
		new Square().check(greensq);
		
	}
	
	public void setPieces(){
		for(int i=0; i<4; i++){
				if(piecegreen[i].alive==0)	piecegreen[i].gamePiece(green);
				else continue;
			}
	}
	
	public void paintGreen(){
		Graphics g=getGraphics();
		if(valg==56){
			count_piece_green++;
			piecegreen[current_green].alive=1;
			current_green++;
			if(count_piece_green==4){
				g.setFont(f2);
				g.drawString("GREEN WON!",570,350);
				current_green=0;
				chance=0;
				bb.setLabel("DICE");
			}
			valg=0;
		}
		if(count_piece_green!=4){
			piecegreen[current_green]=new GamePiece(greensq[valg]);
		}
	}
	
	public void moveGreen(){
		Graphics g=getGraphics();
		if(valg<50)
		{
		    valg=valg+valgenerate;
			repaint();
		}
		else if((valg+valgenerate)==56)
		{
			valg=valg+valgenerate;
			repaint();
		}
		else if((valg+valgenerate)<56)
		{
			valg=valg+valgenerate;
			repaint();
		}
		else{
		}
	}
}