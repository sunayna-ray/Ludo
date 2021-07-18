import java.applet.Applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/*<applet code="Ludo" width="300" height="400"></applet>*/
public class Ludo extends Applet implements ActionListener
{	
	Graphics g=getGraphics();
	final Color blue=new Color(45, 66, 85);
	final Color green=new Color(22, 84, 43);
	final Color yellow= new Color(194, 192, 31);
	final Color red= new Color(120, 69, 68);
	final Color gray=new Color(77, 77, 77);
	String[] team={"BLUE", "RED", "GREEN", "YELLOW"};
	final Font f=new Font("Arial",Font.BOLD,100);

	int[] valgen={0, 0, 0};
	int chance=0, last=0, won=5;
	int valgenerate=0;
	int current_blue=0, current_green=0, current_red=0, current_yellow=0;
	int count_piece_green=0, count_piece_blue=0, count_piece_red=0, count_piece_yellow=3;
	int openg=0, openb=0, openr=0, openy=0, flag=0;

	TextField tg, tb, tr, ty;
	TextField tgc, tbc, trc, tyc;
	Label l1, l2, l3, l4, l5, l6, l7, l8;
	Button bb, b2;

	int valg=0,valb=0,valr=0,valy=55;

    int side=43;

    Square[] bluesq=new Square[57];
    Square[] greensq=new Square[57];
	Square[] redsq=new Square[57];
    Square[] yellowsq=new Square[57];
	
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
			bb.setLabel("DICE");
			setBoard(0);			
			pieceblue[1]=new GamePiece(500,183,blue);pieceblue[2]=new GamePiece(371,54,blue);pieceblue[3]=new GamePiece(500,54,blue);
			piecegreen[1]=new GamePiece(758,441,green);piecegreen[2]=new GamePiece(887,570,green);piecegreen[3]=new GamePiece(758,570,green);
			pieceyellow[1]=new GamePiece(500,441,yellow);pieceyellow[2]=new GamePiece(371,570,yellow);pieceyellow[3]=new GamePiece(500,570,yellow);
			piecered[1]=new GamePiece(758,183,red);piecered[2]=new GamePiece(887,54,red);piecered[3]=new GamePiece(758,54,red);
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
		add(l2);add(tb);
		add(l3);add(tgc);
		add(l4);add(tbc);
		add(l5);add(tr);
		add(l6);add(ty);
		add(l7);add(trc);
		add(l8);add(tyc);
		//b2.addActionListener(new B2Listen());
		bb.addActionListener(this);
    }

    public void paint(Graphics g)
    {
		setBoard(1);
		tg.setText(valg+"");tb.setText(valb+"");tr.setText(valr+"");ty.setText(valy+"");
		paintGreen();
		paintBlue();
		paintRed();
		paintYellow();
		tgc.setText(count_piece_green+"");tbc.setText(count_piece_blue+"");
		trc.setText(count_piece_red+"");tyc.setText(count_piece_yellow+"");
		setPieces();
		if(won!=5){
			g.setFont(f);
			g.setColor(Color.black);
			g.drawString(team[won]+" WON!", 270, 190 );
		}
    }
	public void actionPerformed(ActionEvent ae)
	{
		valgenerate=1+((int)(Math.random()*(6-1+1)));
		//valgenerate=6;
		bb.setLabel(valgenerate+"");		
		valgen[0]=valgen[1];valgen[1]=valgen[2];valgen[2]=valgenerate;
		int chanceprev=chance%4;
		if (valgen[0]==6 && valgen[1]==6 && valgen[2]==6 && flag==0){
			valgenerate=0;
			valgen[2]=0;
		}
		if(chance%4==0){
			if(openb==0 && valgenerate==6){
				openb=1; valgenerate=0;flag=1;
			}
			if(openb==1){
				moveBlue();
			}
		}else if(chance%4==1){
			if(openr==0 && valgenerate==6){
				openr=1; valgenerate=0;flag=1;
			}
			if(openr==1){
				moveRed();
			}
		}else if(chance%4==2){
			if(openg==0 && valgenerate==6){
				openg=1; valgenerate=0;flag=1;
			}
			if(openg==1){
				moveGreen();
			}
		}else{
			if(openy==0 && valgenerate==6){
				openy=1; valgenerate=0;flag=1; 
			}
			if(openy==1){
				moveYellow();
			}
		}if (valgenerate!=6 && flag==0){
			chance++;
		}flag=0;
		showStatus("Previous Chance: "+team[chanceprev]+"  Now Chance: "+team[chance%4]);
		if(last!=1){
			repaint();
		}
		System.out.println(valgen[0]+" "+valgen[1]+" "+valgen[2]+" ");
		valgen[2]=valgenerate;
		/*if(last==1){
			last++;
		}*/
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
			}
			bluesq[56]=new Square(0, 0, Color.white);
			greensq[56]=new Square(0, 0, Color.white);
			yellowsq[56]=new Square(0, 0, Color.white);
			redsq[56]=new Square(0, 0, Color.white);
			bb=new Button("DICE");
			b2=new Button("LUDO");
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
			l2.setBounds(1000,215,150,40);
			tb.setBounds(1150,225,40,20);
			l5.setBounds(1000,305,150,40);
			tr.setBounds(1150,315,40,20);
			l4.setBounds(60,200,140,40);
			tbc.setBounds(230,210,40,20);
			l7.setBounds(60,290,140,40);
			trc.setBounds(230,300,40,20);
		
			l1.setBounds(1000,395,150,40);
			tg.setBounds(1150,405,40,20);
			l6.setBounds(1000,485,150,40);
			ty.setBounds(1150,495,40,20);
			l3.setBounds(60,380,140,40);
			tgc.setBounds(230,390,40,20);
			l8.setBounds(60,470,140,40);
			tyc.setBounds(230,480,40,20);
							
			piecegreen[0]=new GamePiece(887,441,green);
			piecegreen[1]=new GamePiece(758,441,green);
			piecegreen[2]=new GamePiece(887,570,green);
			piecegreen[3]=new GamePiece(758,570,green);	
			
			pieceblue[0]=new GamePiece(371,183,blue);
			pieceblue[1]=new GamePiece(500,183,blue);
			pieceblue[2]=new GamePiece(371,54,blue);
			pieceblue[3]=new GamePiece(500,54,blue);		
				
			piecered[0]=new GamePiece(887,183,red);
			piecered[1]=new GamePiece(758,183,red);
			piecered[2]=new GamePiece(887,54,red);
			piecered[3]=new GamePiece(758,54,red);
			
			pieceyellow[0]=new GamePiece(371,441,yellow);
			pieceyellow[1]=new GamePiece(500,441,yellow);
			pieceyellow[2]=new GamePiece(371,570,yellow);
			pieceyellow[3]=new GamePiece(500,570,yellow);	
			
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
			g.setColor(gray);
			g.drawString("*", bluesq[i].x+7, (bluesq[i].y+50));
			g.drawString("*", bluesq[i-8].x+7, (bluesq[i-8].y+50));
		}
		//new Square().check(greensq);
	}
	
	public void setPieces(){
		Graphics g= getGraphics();
		for(int i=0; i<4; i++){			
			if(piecegreen[i].alive==0){
				piecegreen[i].gamePiece(green);
			}
			if(pieceblue[i].alive==0){
				pieceblue[i].gamePiece(blue);
			}
			if(piecered[i].alive==0){
				piecered[i].gamePiece(red);
			}
			if(pieceyellow[i].alive==0){
				pieceyellow[i].gamePiece(yellow);
			}						
		}
	}
	
	public void paintGreen(){
		Graphics g=getGraphics();
		if(valg==56){
			openg=0;
			count_piece_green++;
			piecegreen[current_green].alive=1;
			current_green++;
			if(count_piece_green==4){
				g.setFont(f);
				g.drawString("GREEN WON!",270, 190);
				last=1;won=2;
				count_piece_green=0;count_piece_blue=0;count_piece_red=0;count_piece_yellow=0;
				current_green=0;current_blue=0;current_red=0;current_yellow=0;
				valg=0;valb=0;valr=0;valy=0;
				chance=0;
				bb.setLabel("DICE");
				tgc.setText(count_piece_green+"");
				tbc.setText(count_piece_blue+"");
				trc.setText(count_piece_red+"");
				tyc.setText(count_piece_yellow+"");
			}
			valg=0;
		}
		piecegreen[current_green]=new GamePiece(greensq[valg]);
		pieceblue[current_blue]=new GamePiece(bluesq[valb]);
		piecered[current_red]=new GamePiece(redsq[valr]);
		pieceyellow[current_yellow]=new GamePiece(yellowsq[valy]);
	}
	public void moveGreen(){
		Graphics g=getGraphics();
		if(valg<50){
		    valg=valg+valgenerate;
		}else if((valg+valgenerate)==56){
			valg=valg+valgenerate;
		}else if((valg+valgenerate)<56){
			valg=valg+valgenerate;
		}else{
			valg=valg;
		}
	}
	
	public void paintBlue(){
		Graphics g=getGraphics();
		if(valb==56){
			openb=0;
			count_piece_blue++;
			pieceblue[current_blue].alive=1;
			current_blue++;
			if(count_piece_blue==4){
				g.setFont(f);
				g.drawString("BLUE WON!",270, 190);
				last=1;won=0;
				current_blue=0;
				chance=0;
				bb.setLabel("DICE");
				count_piece_green=0;count_piece_blue=0;count_piece_red=0;count_piece_yellow=0;
				current_green=0;current_blue=0;current_red=0;current_yellow=0;
				valg=0;valb=0;valr=0;valy=0;
				chance=0;
				bb.setLabel("DICE");
				tgc.setText(count_piece_green+"");
				tbc.setText(count_piece_blue+"");
				trc.setText(count_piece_red+"");;
				tyc.setText(count_piece_yellow+"");
			}
			valb=0;
		}
		if(count_piece_blue!=4){
		pieceblue[current_blue]=new GamePiece(bluesq[valb]);
		}		
	}
	
	public void moveBlue(){
		Graphics g=getGraphics();
		if(valb<50){
		    valb=valb+valgenerate;
		}else if((valb+valgenerate)==56){
			valb=valb+valgenerate;
		}else if((valb+valgenerate)<56){
			valb=valb+valgenerate;
		}else{
			valb=valb;
		}
	}
	public void paintRed(){
		Graphics g=getGraphics();
		if(valr==56){
			openr=0;
			count_piece_red++;
			piecered[current_red].alive=1;
			current_red++;
			if(count_piece_red==4){
				g.setFont(f);
				g.drawString("RED WON!",270, 190);
				last=1;won=1;
				current_red=0;
				chance=0;
				bb.setLabel("DICE");	
				count_piece_green=0;count_piece_blue=0;count_piece_red=0;count_piece_yellow=0;
				current_green=0;current_blue=0;current_red=0;current_yellow=0;
				valg=0;valb=0;valr=0;valy=0;
				chance=0;
				bb.setLabel("DICE");
				tgc.setText(count_piece_green+"");
				tbc.setText(count_piece_blue+"");
				trc.setText(count_piece_red+"");
				tyc.setText(count_piece_yellow+"");
			}
			valr=0;
		}
		if(count_piece_red!=4){
		piecered[current_red]=new GamePiece(redsq[valr]);
		}		
	}
	
	public void moveRed(){
		Graphics g=getGraphics();
		if(valr<50){
		    valr=valr+valgenerate;
		}else if((valr+valgenerate)==56){
			valr=valr+valgenerate;
		}else if((valr+valgenerate)<56){
			valr=valr+valgenerate;
		}else{
			valr=valr;
		}
	}
	public void paintYellow(){
		Graphics g=getGraphics();
		if(valy==56){
			openy=0;
			count_piece_yellow++;
			pieceyellow[current_yellow].alive=1;
			current_yellow++;
			if(count_piece_yellow==4){
				g.setFont(f);
				g.drawString("YELLOW WON!",270, 190);
				last=1;won=3;
				current_yellow=0;
				chance=0;
				bb.setLabel("DICE");
				count_piece_green=0;count_piece_blue=0;count_piece_red=0;count_piece_yellow=0;
				current_green=0;current_blue=0;current_red=0;current_yellow=0;
				valg=0;valb=0;valr=0;valy=0;
				chance=0;
				bb.setLabel("DICE");
				tgc.setText(count_piece_green+"");
				tbc.setText(count_piece_blue+"");
				trc.setText(count_piece_red+"");
				tyc.setText(count_piece_yellow+"");
			}	
			valy=0;
		}
		if(count_piece_yellow!=4){
		pieceyellow[current_yellow]=new GamePiece(yellowsq[valy]);
		}		
	}
	
	public void moveYellow(){
		Graphics g=getGraphics();
		if(valy<50){
		    valy=valy+valgenerate;
		}else if((valy+valgenerate)==56){
			valy=valy+valgenerate;
		}else if((valy+valgenerate)<56){
			valy=valy+valgenerate;
		}else{
			valy=valy;
		}
	}
}