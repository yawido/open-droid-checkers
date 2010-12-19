package com.Android.Checkers;







import java.util.Random;

/**
 * @author Doaa Ashour
 *  This activity handles all the game logic.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Game extends Activity
{

	
	public static final String PREF_BOARD = "MyPrefsFile";
	private BoardView boardView;
	private int board[][] = new int[8][8];
	private int isKing[][] = new int[8][8];
	protected int blackXIndices[]=new int[12];
	protected int blackYIndices[]=new int[12];
	private boolean isBlackVisible[]=new boolean[12];
	private boolean gameEnd;
	private int countRed,countBlack;
	private int X,Y;
	protected boolean canStillJump;
	Random rand=new Random();


	private void init()
	{
		//loop for red balls
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 
				 //color black squares
				 if((j%2==0 && i%2==0) || (i%2!=0 && j%2!=0))
				   {
					 board[i][j]=0;
					 continue;
				   }
				
				 //color white squares
				if(i<3) //black player
				{
				 board[i][j]=2;
				}
				else if(i>4) //red player
				{
					 board[i][j]=1;
				}
				else
				{
					board[i][j]=0;
				}
				
				//initialize kings
			    isKing[i][j]=0;

				 
			 }
 
		    
		    //initialize black
		    isBlackVisible[i]=true;
		}
		
		for(int i=8;i<12;i++)
			isBlackVisible[i]=true;
		
		canStillJump=false;		
		countRed=12;
		countBlack=12;
		
		blackXIndices[0]=1;
		blackXIndices[1]=3;
		blackXIndices[2]=5;
		blackXIndices[3]=7;
		blackXIndices[4]=0;
		blackXIndices[5]=2;
		blackXIndices[6]=4;
		blackXIndices[7]=6;
		blackXIndices[8]=1;
		blackXIndices[9]=3;
		blackXIndices[10]=5;
		blackXIndices[11]=7;
		
		blackYIndices[0]=0;
		blackYIndices[1]=0;
		blackYIndices[2]=0;
		blackYIndices[3]=0;
		blackYIndices[4]=1;
		blackYIndices[5]=1;
		blackYIndices[6]=1;
		blackYIndices[7]=1;
		blackYIndices[8]=2;
		blackYIndices[9]=2;
		blackYIndices[10]=2;
		blackYIndices[11]=2;
		
		

	}
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		gameEnd=false;
		
		boardView = new BoardView(this);

		setContentView(boardView);

		boardView.requestFocus();
		
		Bundle extras = getIntent().getExtras();
		String type=extras.getString("type");
		
		if(type.equals("cont"))
			{
			  boardView.cont=true;		 
			}
		else
			{
			//new game
		
				init();
			}
		
		printBoard();
		
		
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
		saveGame();
	};
	
	protected void continueGame()
	{
 		SharedPreferences settings = getSharedPreferences(PREF_BOARD, MODE_PRIVATE);
		boardFromString(settings.getString("redBoard",""), settings.getString("blackBoard",""));
		visibleFromString(settings.getString("redVisible",""),settings.getString("blackVisible",""));
		canStillJump=settings.getBoolean("canStillJump", false);
		countBlack=settings.getInt("countBlack", 12);
		countRed=settings.getInt("countRed", 12);
		boardView.turn=settings.getInt("turn", 1);
		kingsFromString(settings.getString("kings", ""));
	}
	
	protected boolean move(int tempX,int tempY,int selX,int selY)
	{

			//check if valid
			
			if(isJump(tempX,tempY,selX,selY))
			 {
				countBlack--;
				board[Y][X]=0;
				
				Log.d("BoardView","Player Jumped:"+Y+","+X);
			
				//remove jumped piece
				for(int i=0;i<12;i++)
				{
					if(blackXIndices[i]==X && blackYIndices[i]==Y)
					{
						Log.d("BoardView","Index of jumped:"+i);
						boardView.blackBalls[i].visible=false;
						isBlackVisible[i]=false;
						boardView.invalidate();
						break;
					}
				}
				
				if(canRedJump(selX,selY))
					canStillJump=true;
				else
					canStillJump=false;
			 }//end of isJump
			else if(!isMove(tempX,tempY,selX,selY))
            	{
            	   showToast("Invalid Move.");
            	   return false;
            	}
		
		board[tempY][tempX]=0;
		board[selY][selX]=1;
		
		if(isKing[tempY][tempX]==1)
		{
			isKing[tempY][tempX]=0;
			isKing[selY][selX]=1;
		}
		else if(selY==0)//became king  //position changed
		{
			isKing[selY][selX]=1;
			boardView.makeKing(selX,selY);
		}


		
        return true;
        
		
	}
	
	
	protected int moveComputer()
	{
			

		int i,index;
		//check if can jump
		for(i=0;i<12;i++)
		{
			if(isBlackVisible[i] && canJump(blackXIndices[i], blackYIndices[i],i))
			{
                //moving computer is already called inside canJump due to possibility 
				//of multiple jumps
				
				return i;
			}
		}
		
		//check if will be eaten
		for(i=0;i<12;i++)
		{
			if(isBlackVisible[i] && canBeEaten(blackXIndices[i], blackYIndices[i]))
			{
				if(canNotBeJumped(blackXIndices[i], blackYIndices[i]))
				{
					movingComputer (i);
					return i;
				}
			}
		}
		
		//check if can be king
		for(i=0;i<12;i++)
		{  
			if(isBlackVisible[i] && canBeKing(blackXIndices[i], blackYIndices[i]) && isKing[blackYIndices[i]][blackXIndices[i]]==0 )
			{
				movingComputer (i);
				return i;
			}
			
		}
		
		//go to empty square without being jumped
		
		index=getRandomInteger();
			if(isBlackVisible[index]  && canNotBeJumped(blackXIndices[index], blackYIndices[index]))
			{
				movingComputer (index);
				return index;
			}
		
		for(i=0;i<12;i++)
		{
			if(isBlackVisible[i]  && canNotBeJumped(blackXIndices[i], blackYIndices[i]))
			{
				movingComputer (i);
				return i;
			}
		}
		
		//got to any empty square
		index=getRandomInteger();
		if(isBlackVisible[index]  && getEmptySquare(blackXIndices[index], blackYIndices[index]))
		{
			movingComputer (index);
			return index;
		}
		
		for(i=0;i<12;i++)
		{
			if(isBlackVisible[i]  && getEmptySquare(blackXIndices[i], blackYIndices[i]))
			{
				movingComputer (i);
				return i;
			}
		}
		
		Log.d("BoardView","got here.");
		
		// no legal moves for computer
         return -1;
		
	}
	
	private void movingComputer (int i)
	{
		board[blackYIndices[i]][blackXIndices[i]]=0;
	    
		if(isKing[blackYIndices[i]][blackXIndices[i]]==1)
		{
			isKing[blackYIndices[i]][blackXIndices[i]]=0;
			isKing[Y][X]=1;
		}
		else if(Y==7)//became king //position changed
		{
			isKing[Y][X]=1;
			boardView.makeKing(i);
		}
		blackXIndices[i]=X;
		blackYIndices[i]=Y;
		board[Y][X]=2;
		//showToast("Your Turn.");
/*		   try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	}
	
	private boolean canJump(int x,int y,int i)
	{
		//check backward jump
		//position changed
		if(isKing[y][x]==1)
		{
			if((y-2>=0) && (x-2>=0) && board[y-1][x-1]==1 && board[y-2][x-2]==0 )
			{
			   countRed--;
			   boardView.removeRed(x-1,y-1);
			   board[y-1][x-1]=0;
			   X=x-2;
			   Y=y-2;
			   movingComputer (i);
			   canJump(x-2,y-2,i);
			  return true;
			}

		if((y-2>=0) && (x+2<=7) && board[y-1][x+1]==1 && board[y-2][x+2]==0 )
			{
			   countRed--;
			   boardView.removeRed(x+1,y-1);
			   board[y-1][x+1]=0;
			   X=x+2;
			   Y=y-2;
			   movingComputer (i);
			   canJump(x+2,y-2,i);
			  return true;
			}
		
		}
		
		//check forward jump
		

		if((y+2<=7) && (x+2<=7) && board[y+1][x+1]==1 && board[y+2][x+2]==0 )
		{
	
		   countRed--;
		   boardView.removeRed(x+1,y+1);
		   board[y+1][x+1]=0;
		   X=x+2;
		   Y=y+2;
		   movingComputer (i);
		   canJump(x+2,y+2,i);
		   return true;
		}

	if((y+2<=7) && (x-2>=0) && board[y+1][x-1]==1 && board[y+2][x-2]==0 )
		{
		   countRed--;		   
		   boardView.removeRed(x-1,y+1);
		   board[y+1][x-1]=0;
		   X=x-2;
		   Y=y+2;
		   movingComputer (i);
		   canJump(x-2,y+2,i);
		   return true;
		}
		return false;
		

	}
	
	private boolean redMovesExist()
	{
		for(int i=0;i<12;i++)
    	{
        	
    		if(boardView.redBalls[i].visible)
    		{
            	int tempX = Math.min(Math.max((boardView.redBalls[i].getX()/boardView.W), 0), 7);
            	int tempY = Math.min(Math.max((boardView.redBalls[i].getY()/boardView.H), 0), 7);
       
            	if(canRedJump(tempX,tempY) || canRedMove(tempX,tempY))
            		return true;
    		}
    	}
		
		return false;
	}
	
	private boolean canRedJump(int x,int y)
	{
		//position changed
		//check backward jump
		if(isKing[y][x]==1)
		{
			if((y+2<8) && (x+2<8) && board[y+1][x+1]==2 && board[y+2][x+2]==0 )
			{

			  return true;
			}

		if((y+2<8) && (x-2>=0) && board[y+1][x-1]==2 && board[y+2][x-2]==0 )
			{

			  return true;
			}
		}
		
		//check forward jump
		
		if((y-2>=0) && (x-2>=0) && board[y-1][x-1]==2 && board[y-2][x-2]==0 )
		{
	
		   return true;
		}

	if((y-2>=0) && (x+2<8) && board[y-1][x+1]==2 && board[y-2][x+2]==0 )
		{
		   return true;
		}
		

		
		return false;
	}
	
	private boolean canRedMove(int x, int y)
	{
		//position changed
		//check backward moves
        if(isKing[y][x]==1)
        {
       
          	if( (y+1<8) && (x-1>=0) && board[y+1][x-1]==0)
        	{
    			   
    			   	 X=x-1;
    			   	 Y=y+1;
    			     return true;
        	}
        	else if( (y+1<8) && (x+1<8) && board[y+1][x+1]==0)
        	{
    			   
    			   	 X=x+1;
    			   	 Y=y+1;
    			     return true;
        	}
        }

        //check forward moves
        
     	if( (y-1>=0) && (x-1>=0) && board[y-1][x-1]==0)
    	{
			   
			   	 X=x-1;
			   	 Y=y-1;
			     return true;
    	}
    	else if( (y-1>=0) && (x+1<8) && board[y-1][x+1]==0)
    	{
			   
			   	 X=x+1;
			   	 Y=y-1;
			     return true;
    	}


    	
    	return false;
	}
	
	private boolean canBeKing(int x,int y)
	{
		//position changed
           if(y==6)
           {
        	   if(x-1>=0)
        	   {
        		   if(board[7][x-1]==0)
        			   {
    				   	 X=x-1;
    				   	 Y=7;
        			     return true;
        			   }
        	   }
        	   else if(x+1<8)
        	   {
        		   if(board[7][x+1]==0)
        			   {
    				     X=x+1;
    				   	 Y=7;
        			     return true;
        			   }
        	   }
           }
           
           return false;
	}
	
	private boolean canNotBeJumped(int x,int y)
	{
		//position changed
		board[y][x]=0;
		//check backward moves
        if(isKing[y][x]==1)
        {
        	if( (y-1>=0) && (x-1>=0) && board[y-1][x-1]==0 && !canBeEaten(x-1,y-1))
        	{
    			   
    			   	 X=x-1;
    			   	 Y=y-1;
    			     return true;
        	}
        	else if( (y-1>=0) && (x+1<8) && board[y-1][x+1]==0 && !canBeEaten(x+1,y-1))
        	{
    			   
    			   	 X=x+1;
    			   	 Y=y-1;
    			     return true;
        	}

        }

        //check forward moves
    	if( (y+1<8) && (x-1>=0) && board[y+1][x-1]==0 && !canBeEaten(x-1,y+1))
    	{
			   
			   	 X=x-1;
			   	 Y=y+1;
			     return true;
    	}
    	else if( (y+1<8) && (x+1<8) && board[y+1][x+1]==0 && !canBeEaten(x+1,y+1))
    	{
			   
			   	 X=x+1;
			   	 Y=y+1;
			     return true;
    	}
        

			
    	board[y][x]=2;
		return false;
	}
	
	private boolean canBeEaten(int x,int y)
	{
		//position changed
		if((x-1>=0) && (y-1>=0) && board[y-1][x-1]==1 && isKing[y-1][x-1]==1 && (x+1<8) && (y+1<8) && board[y+1][x+1]==0)
			return true;
		
		if((x+1<8) && (y-1>=0) && board[y-1][x+1]==1 && isKing[y-1][x+1]==1 && (x-1>=0) && (y+1<8) && board[y+1][x-1]==0)
			return true;
		
		if((x-1>=0) && (y+1<8) && board[y+1][x-1]==1  && (x+1<8) && (y-1>=0) && board[y-1][x+1]==0)
			return true;
		
		if((x+1<8) && (y+1<8) && board[y+1][x+1]==1  && (x-1>=0) && (y-1>=0) && board[y-1][x-1]==0)
			return true;
		
		return false;
		
		
	}
	
	private boolean getEmptySquare(int x,int y)
	{
		//position changed
		//check backward moves
        if(isKing[y][x]==1)
        {
        	if( (y-1>=0) && (x-1>=0) && board[y-1][x-1]==0)
        	{
    			   
    			   	 X=x-1;
    			   	 Y=y-1;
    			     return true;
        	}
        	else if( (y-1>=0) && (x+1<8) && board[y-1][x+1]==0)
        	{
    			   
    			   	 X=x+1;
    			   	 Y=y-1;
    			     return true;
        	}
        }

        //check forward moves
    	if( (y+1<8) && (x-1>=0) && board[y+1][x-1]==0)
    	{
			   
			   	 X=x-1;
			   	 Y=y+1;
			     return true;
    	}
    	else if( (y+1<8) && (x+1<8) && board[y+1][x+1]==0)
    	{
			   
			   	 X=x+1;
			   	 Y=y+1;
			     return true;
    	}

    	
    	return false;
	}
	
	private boolean isJump(int tempX,int tempY,int selX,int selY)
	{

		//position changed
		if(isKing[tempY][tempX]==1) //check backward jump
		{

			if((tempY+2<8) && (tempX+2<8) && (selY==tempY+2) && (selX==tempX+2) && 
					board[tempY+1][tempX+1]==2 && board[selY][selX]==0 )
				{
					X=tempX+1;
					Y=tempY+1;
					return true;
				}
			

			if((tempY+2<8) && (tempX-2>=0) && (selY==tempY+2) && (selX==tempX-2) && 
					board[tempY+1][tempX-1]==2 && board[selY][selX]==0 )
				{
					X=tempX-1;
					Y=tempY+1;
					return true;
				}
		}
		
		 //check forward jump
		if((tempY-2>=0) && (tempX-2>=0) && (selY==tempY-2) && (selX==tempX-2) && 
				board[tempY-1][tempX-1]==2 && board[selY][selX]==0 )
			{
                X=tempX-1;
                Y=tempY-1;
				return true;
			}

		if((tempY-2>=0) && (tempX+2<8) && (selY==tempY-2) && (selX==tempX+2) && 
				board[tempY-1][tempX+1]==2 && board[selY][selX]==0 )
			{
				X=tempX+1;
				Y=tempY-1;
				return true;
			}
		

		
		return false;
	}
	
	private boolean isMove(int tempX,int tempY,int selX,int selY)
	{
		//position changed
		if(board[selY][selX]!=0)
			return false;
		
		if(isKing[tempY][tempX]==1) //check backward move
		{
			if((tempY+1<8) && (tempX+1<8) && (selY==tempY+1) && (selX==tempX+1)  )
				return true;
			

			if((tempY+1<8) && (tempX-1>=0) && (selY==tempY+1) && (selX==tempX-1)  )
				return true;
		}
		
		 //check forward move
		if((tempY-1>=0) && (tempX-1>=0) && (selY==tempY-1) && (selX==tempX-1) )
			return true;

		if((tempY-1>=0) && (tempX+1<8) && (selY==tempY-1) && (selX==tempX+1) )
			return true;
		
		

		
		return false;
	}
	
	private String redBoardToString()
	{
		String temp="";
		char board[][] = new char[8][8];
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

			    board[i][j]='0';			 
			 }
		}
		//save red indices
		String str="";
		for(int i=0;i<12;i++)
		{
			if(boardView.redBalls[i].visible)
	    	{
				int tempX = Math.min(Math.max((boardView.redBalls[i].getX()/boardView.W), 0), 7);
		    	int tempY = Math.min(Math.max((boardView.redBalls[i].getY()/boardView.H), 0), 7);

				board[tempY][tempX]=((char)(i+1+48));
		    	str+=board[tempY][tempX]+",";
	    	}

		}
		
		Log.d("BoardView2","indices:"+str);
		

		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

				 temp+=board[i][j];			 
			 }
		}
		

		
		
		return temp;
	}
	
	private String blackBoardToString()
	{
		String temp="";
		char board[][] = new char[8][8];
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

			    board[i][j]='0';			 
			 }
		}
		//save black indices
		String str="";
		for(int i=0;i<12;i++)
		{
			if(boardView.blackBalls[i].visible)
	    	{
				int tempX = Math.min(Math.max((boardView.blackBalls[i].getX()/boardView.W), 0), 7);
		    	int tempY = Math.min(Math.max((boardView.blackBalls[i].getY()/boardView.H), 0), 7);

				board[tempY][tempX]=((char)(i+1+48));
		    	str+=board[tempY][tempX]+",";
	    	}

		}
		
		Log.d("BoardView2","black indices:"+str);
		

		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

				 temp+=board[i][j];			 
			 }
		}
		

		
		
		return temp;
	}
	private void boardFromString(String redTemp,String blackTemp)
	{
		
		int index=0;
		int num;
		
		Log.d("BoardView2","redTemp="+redTemp);
		
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

				 if(redTemp.charAt(index)!='0')
					 {
					   	board[i][j]=1;
					   
					   	num=redTemp.charAt(index)-1-48;
					   	
					   	Log.d("BoardView2","index"+index+","+"numRed="+num);
					   
					   	
			    		boardView.redBalls[num].setX((j*boardView.W)+(boardView.W/2));
			    		boardView.redBalls[num].setY((i*boardView.H)+(boardView.H/2));
					   
					   
					 }
				 else if(blackTemp.charAt(index)!='0')
					 {
					 	board[i][j]=2;
					   
					   	num=blackTemp.charAt(index)-1-48;
					   
					 	blackXIndices[num]=j;
						blackYIndices[num]=i;
			    		boardView.blackBalls[num].setX((j*boardView.W)+(boardView.W/2));
			    		boardView.blackBalls[num].setY((i*boardView.H)+(boardView.H/2));
					 }
				 else
					 board[i][j]=0;
				 			 
				 index++;
			 }
		}
		

		
	}
	
	private String kingsToString()
	{
		String temp="";
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

				 temp+=isKing[i][j];			 
			 }
		}
		
		
		return temp;
	}
	
	private void kingsFromString(String temp)
	{
		int index=0;
		
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 

				 int num=(temp.charAt(index)-48);
				 isKing[i][j]=num;		
				 
				 if(isKing[i][j]==1)
				
			   {	 	 
				 
				 if(board[i][j]==1) //red king
					 boardView.makeKing(j, i);
				 else
				 {
						for(int k=0;k<12;k++)
						{
							if(blackXIndices[k]==j && blackYIndices[k]==i)
							{
								boardView.makeKing(k);
								break;
							}
						}
				 }
				 
			   }

				 index++;
			 }
		}
		
		
	}
	
	private String blackVisibleToString()
	{
		String temp="";
		
			for (int j = 0; j < 12; j++) 
			 {	 					 
				if(isBlackVisible[j])
					temp+=1;
				else
					temp+=0;			 
			 }
			
		return temp;
	}
	
	private String redVisibleToString()
	{
		String temp="";
		
			for (int j = 0; j < 12; j++) 
			 {	 					 
				if(boardView.redBalls[j].visible)
					temp+=1;
				else
					temp+=0;			 
			 }
			
		return temp;
	}
	
	private void visibleFromString(String redTemp,String blackTemp)
	{
		for (int j = 0; j < 12; j++) 
		 {	 					 
			if(blackTemp.charAt(j)=='1')
				isBlackVisible[j]=true;
			else
				isBlackVisible[j]=false;		
			
			if(redTemp.charAt(j)=='1')
				boardView.redBalls[j].visible=true;
			else
				boardView.redBalls[j].visible=false;
		 }
		
	}
	
	
	void printBoard()
	{
		String str="";
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 
                 
				 str+=board[i][j];	
			 }
			
			Log.d("BoardView",str);
			str="";		
		}
	}
	
	void printKings()
	{
		String str="";
		
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			 {	 					 
                 
				 str+=isKing[i][j];	
			 }
			
			Log.d("BoardView",str);
			str="";		
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	 super.onCreateOptionsMenu(menu);
	 MenuInflater inflater = getMenuInflater();
	 inflater.inflate(R.menu.menu, menu);
	 return true;
	}
	
	private void saveGame()
	{

 		SharedPreferences settings = getSharedPreferences(PREF_BOARD, MODE_PRIVATE);
		SharedPreferences.Editor e = settings.edit();
		e.putString("redBoard", redBoardToString());
		e.putString("blackBoard", blackBoardToString());
		e.putString("kings", kingsToString());
		e.putString("blackVisible", blackVisibleToString());
		e.putString("redVisible", redVisibleToString());
		e.putBoolean("canStillJump", canStillJump);
		e.putInt("countBlack", countBlack);
		e.putInt("countRed", countRed);
		e.putInt("turn", boardView.turn);
		e.commit();
		
		if(!gameEnd)
			showToast("Game Saved");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	  switch (item.getItemId()) 
	  {
	     case R.id.saveGame:
	    	 saveGame();
	     return true;
	     
	  }
	  
	    return false;
	}

	 private void showMessage(String messageString) 
	    {
	    	Intent i = new Intent(this, MessageDisplay.class);
	    	i.putExtra("Message", messageString);
	    	startActivity(i);
	    	
	    	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//restart game
	    	init();
	    	boardView.init();
	    	boardView.initBalls();

	    	
	    }
	 
	 protected void showToast(String msgToDisplay)
	 {
		  //Displaying Toast
         Context context = getApplicationContext();
         String msg=msgToDisplay;
         int duration = Toast.LENGTH_SHORT;
         Toast toast = Toast.makeText(context, msg, duration);
         toast.show();
	 }
	 
	 protected void checkEnd(int lastMoved) 
	 {
		 Log.d("BoardView","countred="+countRed+"countblack="+countBlack);
		 if(countRed==0)
			 {
			 	gameEnd=true;
			 	showMessage("I win! :P");		 
			 }
		 else if(countBlack==0)
			 {
			 	gameEnd=true;
			 	showMessage("You Win! :)");
			 }
		 
	
		 if(lastMoved==2) //computer last moved
		 {
			 if(!redMovesExist())
			 {
				 gameEnd=true;
				 showMessage("I win! :P");	
			 }
		 }
		 else //computer has no available moves
		 {
			//check if computer lost or draw 
			 
			 if(!redMovesExist())
			 {
				 gameEnd=true;
				 showMessage("Draw!");	
			 }
			 else
			 {
				 gameEnd=true;
				 showMessage("You Win! :)");
				 
			 }
		 }

	}
	 
	 private int getRandomInteger()
	 {

		 return rand.nextInt(12);
	 }
}

