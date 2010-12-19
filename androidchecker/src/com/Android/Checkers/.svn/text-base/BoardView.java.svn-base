package com.Android.Checkers;




import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * @author Doaa Ashour
 * This class handles all game GUI.
 *
 */

public class BoardView extends View 
{

	private final Game game;
	protected ColorBall[] redBalls = new ColorBall[12]; 
	protected ColorBall[] blackBalls = new ColorBall[12]; 
	private int balID = 0; // variable to know what ball is being dragged
	int iWidth;
	int iHeight;
	protected int W;
	protected int H;
	boolean first=true;
	Context context;
	private int selX; // X index of selection
	private int selY; // Y index of selection
	private int oldX; // X index of selection
	private int oldY; // Y index of selection
	protected int turn;
	protected boolean cont;
	final Handler handler = new Handler();
	
	public BoardView(Context context) 
	{
		super(context);
	        
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true); 
		
		cont=false;
		
		this.context=context;
		
	}
	
	protected void initBalls()
	{
   		int X,Y;
		X=(int)(W/2);
		Y=(int)(H/2);
  	     //draw black balls
  		 blackBalls[0].coordX=X+W;
  		 blackBalls[0].coordY=Y;
  		 blackBalls[1].coordX=X+W*3;
  		 blackBalls[1].coordY=Y;
  		 blackBalls[2].coordX=X+W*5; 
  		 blackBalls[2].coordY=Y;		 
  		 blackBalls[3].coordX=X+W*7; 
  		 blackBalls[3].coordY=Y;
  		
  		 blackBalls[4].coordX=X; 
  		 blackBalls[4].coordY=Y+H;
  		 blackBalls[5].coordX=X+W*2; 
  		 blackBalls[5].coordY=Y+H;
  		 blackBalls[6].coordX=X+W*4; 
  		 blackBalls[6].coordY=Y+H;
  		 blackBalls[7].coordX=X+W*6; 
  		 blackBalls[7].coordY=Y+H;		 
  		
  		 blackBalls[8].coordX=X+W;
  		 blackBalls[8].coordY=Y+H*2;
  		 blackBalls[9].coordX=X+W*3;
  		 blackBalls[9].coordY=Y+H*2;
  		 blackBalls[10].coordX=X+W*5;
  		 blackBalls[10].coordY=Y+H*2;
  		 blackBalls[11].coordX=X+W*7; 
  		 blackBalls[11].coordY=Y+H*2;
  	     
  		 //draw red balls
  		 redBalls[0].coordX=X; 
  		 redBalls[0].coordY=Y+H*5;
  		 redBalls[1].coordX=X+W*2; 
  		 redBalls[1].coordY=Y+H*5;
  		 redBalls[2].coordX=X+W*4; 
  		 redBalls[2].coordY=Y+H*5;
  		 redBalls[3].coordX=X+W*6; 
  		 redBalls[3].coordY=Y+H*5;
  		 
  		 redBalls[4].coordX=X+W; 
  		 redBalls[4].coordY=Y+H*6;
  		 redBalls[5].coordX=X+W*3; 
  		 redBalls[5].coordY=Y+H*6;
  		 redBalls[6].coordX=X+W*5; 
  		 redBalls[6].coordY=Y+H*6;
  		 redBalls[7].coordX=X+W*7; 
  		 redBalls[7].coordY=Y+H*6;
  		 
  		 redBalls[8].coordX=X; 
  		 redBalls[8].coordY=Y+H*7;
  		 redBalls[9].coordX=X+W*2; 
  		 redBalls[9].coordY=Y+H*7;
  		 redBalls[10].coordX=X+W*4; 
  		 redBalls[10].coordY=Y+H*7;
  		 redBalls[11].coordX=X+W*6; 
  		 redBalls[11].coordY=Y+H*7;
  		 
  		 for(int i=0;i<12;i++)
  		 {
  			redBalls[i].img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_rood); 
  			blackBalls[i].img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_black); 
  			
  			redBalls[i].visible=true;
  			blackBalls[i].visible=true;
  		 }

	}
	
	protected void init()
	{
		
		balID = 0;
		
		
	    iWidth=getWidth();
		iHeight=(getHeight()-50);
			
		W=iWidth/8;
	    H=iHeight/8;
	    	    
	    iWidth=W*8;
	    iHeight=H*8;

	    turn=1;

	    if(first)
	    {
	    	first=false;
    		int X,Y;
    		X=(int)(W/2);
    		Y=(int)(H/2);
   	     //draw black balls
   		 blackBalls[0] = new ColorBall(context,R.drawable.bol_black, new Point(X+W,Y),1);
   		 blackBalls[1] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*3,Y),2);
   		 blackBalls[2] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*5,Y),3);
   		 blackBalls[3] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*7,Y),4);
   		 
   		 blackBalls[4] = new ColorBall(context,R.drawable.bol_black, new Point(X,Y+H),5);
   		 blackBalls[5] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*2,Y+H),6);
   		 blackBalls[6] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*4,Y+H),7);
   		 blackBalls[7] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*6,Y+H),8);
   		 
   		 blackBalls[8] = new ColorBall(context,R.drawable.bol_black, new Point(X+W,Y+H*2),9);
   		 blackBalls[9] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*3,Y+H*2),10);
   		 blackBalls[10] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*5,Y+H*2),11);
   		 blackBalls[11] = new ColorBall(context,R.drawable.bol_black, new Point(X+W*7,Y+H*2),12);
   		 
   	     //draw red balls
   		 redBalls[0] = new ColorBall(context,R.drawable.bol_rood, new Point(X,Y+H*5),1);
   		 redBalls[1] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*2,Y+H*5),2);
   		 redBalls[2] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*4,Y+H*5),3);
   		 redBalls[3] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*6,Y+H*5),4);
   		 
   		 redBalls[4] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W,Y+H*6),5);
   		 redBalls[5] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*3,Y+H*6),6);
   		 redBalls[6] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*5,Y+H*6),7);
   		 redBalls[7] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*7,Y+H*6),8);
   		 
   		 redBalls[8] = new ColorBall(context,R.drawable.bol_rood, new Point(X,Y+H*7),9);
   		 redBalls[9] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*2,Y+H*7),10);
   		 redBalls[10] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*4,Y+H*7),11);
   		 redBalls[11] = new ColorBall(context,R.drawable.bol_rood, new Point(X+W*6,Y+H*7),12);
	    }
	}
	
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		
		   init();
		   
		   if(cont)
		   {
			   game.continueGame();
			   cont=false;
		   }
	
	};

	@Override
	protected void onDraw(Canvas canvas) 
	{
		   
		
		// Draw the background...
			Paint background = new Paint();
			background.setColor(Color.rgb(105, 105, 105));
			canvas.drawRect(0, 0, iWidth, iHeight, background);

	
			Paint blackColor = new Paint();
			blackColor.setColor(Color.BLACK);
	
			for (int i = 0; i < 8; i++) 
			{
				for (int j = 0; j < 8; j++) 
				 {	 					 
					 //color black squares
					 if((j%2==0 && i%2==0) || (i%2!=0 && j%2!=0))
					   canvas.drawRect(W*j,H*i, (W*j)+W, (H*i)+H, blackColor);
					 
				 }
	 
			}

	 
			//draw time
			
			
			canvas.drawText("Copyright doaaashour © 2010", 5,iHeight+35, background);
	 
			String str="",str2="";
			//draw the balls on the canvas
			for (int i=0;i<12;i++) 
			{      
				//left top
				int tempW,tempH;
				tempW=30/2;
				tempH=30/2;
				if(redBalls[i].visible) 
					{
					 canvas.drawBitmap(redBalls[i].getBitmap(), redBalls[i].getX()-tempW, redBalls[i].getY()-tempH, null);
					  str+=","+i;
					}
				if(blackBalls[i].visible)
					{
					  canvas.drawBitmap(blackBalls[i].getBitmap(), blackBalls[i].getX()-tempW, blackBalls[i].getY()-tempH, null);			
					  str2+=","+i;
					}
			}
			
			
			Log.d("BoardView","Redballs indices="+str);
			Log.d("BoardView","Blackballs indices="+str2);
			canvas.drawLine(0, iHeight, iWidth, iHeight, background);
			canvas.drawLine(0,1, iWidth, 1, background);
	 
	}
	
	// events when touching the screen
    public boolean onTouchEvent(MotionEvent event) 
    {
    	if(turn==1)
      {
        int eventaction = event.getAction(); 
        
        int X = (int)event.getX(); 
        int Y = (int)event.getY(); 

        switch (eventaction ) { 

        case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on a ball
        	balID = 0;
        	for (ColorBall ball : redBalls) {
        		// check if inside the bounds of the ball (circle)
        		// get the center for the ball
        		int centerX = ball.getX() ;//+ 12;
        		int centerY = ball.getY() ;//+ 12;
        		
        		// calculate the radius from the touch to the center of the ball
        		double radCircle  = Math.sqrt( (double) (((centerX-X)*(centerX-X)) + (centerY-Y)*(centerY-Y)));
        		
        		// if the radius is smaller then 23 (radius of a ball is 22), then it must be on the ball
        		if (radCircle < 16)
        		{       	
          			balID = ball.getID();
        		
        			
        			if(balID>0)
                	{
            			if(!redBalls[balID-1].visible)
            				break;
            			
        				oldX=redBalls[balID-1].getX();
        				oldY=redBalls[balID-1].getY();
                	}
                    
                    break;
        		}


              }
             
             break; 


        case MotionEvent.ACTION_MOVE:   // touch drag with the ball
        	// move the balls the same as the finger
            if (balID > 0) 
            {
            	//set the new coordinates of ball

                redBalls[balID-1].setX(X-15);
            	redBalls[balID-1].setY(Y-15);
               
            }
        	
            break; 

        case MotionEvent.ACTION_UP: 
       		// touch drop - just do things here after dropping

            if (balID > 0) 
            {
            	
            	//validate position
            	if(select((X-15)/W,(Y-15)/H))
            	{
            	
            	//set the new coordinates of ball
            	
            	int tempX=(((X-15)/W)*W)+(W/2);
            	int tempY=(((Y-15)/H)*H)+(H/2);


               	redBalls[balID-1].setX(tempX);
            	redBalls[balID-1].setY(tempY);
            	
                invalidate(); 
                
        	
      
            	if(!game.canStillJump)
            	{
            		changeTurn();   

            	}
            	else
            	{
            		game.showToast("You still got available jumps.");
            	}
            	
            
                
            	}
            	else
            	{
                    redBalls[balID-1].setX(oldX);
                	redBalls[balID-1].setY(oldY);
                	
                    invalidate(); 
            	}
            	
            	
            }

             break; 
        } 
        // redraw the canvas
        invalidate(); 
        
        
      }
        return true; 
        
       
	
    }
    
    protected void moveComputer()
    {
    	int index=game.moveComputer();
    	

    	
    	
    	Log.d("BoardView","index="+index);
    	
    	if(index!=-1)
    	{

    		blackBalls[index].setX((game.blackXIndices[index]*W)+(W/2));
    		blackBalls[index].setY((game.blackYIndices[index]*H)+(H/2));
    		
    		
    		//invalidate();

    		changeTurn();
    		
    		//check if player can move or draw
        	game.checkEnd(2);
    	}
    	else
    	{
    		//check if computer lost or draw
        	game.checkEnd(-1);
    	}
    	
        game.printKings();
        Log.d("BoardView","------:");
        game.printBoard();
    }
    
    private boolean select(int x, int y) 
    {

    	
    	int tempX = Math.min(Math.max((oldX/W), 0), 7);
    	int tempY = Math.min(Math.max((oldY/H), 0), 7);
    	selX = Math.min(Math.max(x, 0), 7);
    	selY = Math.min(Math.max(y, 0), 7);	
    	
    	Log.d("BoardView","player's moves:"+tempY+","+tempX+","+selY+","+selX+",");

    	
    	return game.move(tempX,tempY,selX,selY);
    	

    }
    
    protected void removeRed(int x,int y)
    {
    	for(int i=0;i<12;i++)
    	{
        	int tempX = Math.min(Math.max((redBalls[i].getX()/W), 0), 7);
        	int tempY = Math.min(Math.max((redBalls[i].getY()/H), 0), 7);
        	
        	
    		if(tempX==x && tempY==y && redBalls[i].visible)
    		{
    			
    			Log.d("BoardView","red piece found="+i);
    			redBalls[i].visible=false;
    		//	invalidate();
    			break;
    		}
    	}
    }
    
    protected void makeKing(int index)
    {
  
    	blackBalls[index].img=BitmapFactory.decodeResource(context.getResources(), R.drawable.black_king); 
    	//invalidate();
    }
    
    protected void makeKing(int x,int y)
    {
  
    	for(int i=0;i<12;i++)
    	{
        	int tempX = Math.min(Math.max((redBalls[i].getX()/W), 0), 7);
        	int tempY = Math.min(Math.max((redBalls[i].getY()/H), 0), 7);
        	
        	
    		if(tempX==x && tempY==y && redBalls[i].visible)
    		{
    	    	redBalls[i].img=BitmapFactory.decodeResource(context.getResources(), R.drawable.red_king); 
    			invalidate();
    			break;
    		}
    	}
    }
    
    protected void changeTurn()
    {
    	if(turn==1)
    		{
    			turn=2;

    		    handler.postDelayed( new Runnable() {
                    public void run() 
                    {
                    	moveComputer();
                    }
    		    	}, 3000	);

    		      
    
    		}
    	else 
    		{		
		      invalidate();
    			turn=1;
    		}
    }
    
    
}
