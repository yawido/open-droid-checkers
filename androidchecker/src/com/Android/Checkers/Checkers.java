package com.Android.Checkers;





import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Checkers extends Activity 
{
    /** Called when the activity is first created. */
	
	public static final String PREF_BOARD = "MyPrefsFile";
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        

        setContentView(R.layout.main);
        
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				startGame();
				
			}
        	
        });
        
        View continueButton = findViewById(R.id.continue_button);
        SharedPreferences settings = getSharedPreferences(PREF_BOARD, MODE_PRIVATE);
        
        if(settings.getString("redBoard", "").length()==0)
        	continueButton.setBackgroundColor(color.background_dark);
        else
        {
        	continueButton.setOnClickListener(new OnClickListener()
        	{

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				continueGame();
				
			}
        	
        	});
        }
        
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				exit();
				
			}
        	
        });
        
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				showMessage("Developed by doaaashour © 2010.");
				
			}
        	
        });
        
    }
    
    private void startGame() 
    {
		Intent intent = new Intent(Checkers.this,Game.class);
		intent.putExtra("type", "new");
		startActivity(intent);
		
	}
    
    private void continueGame()
    {
        SharedPreferences settings = getSharedPreferences(PREF_BOARD, MODE_PRIVATE);
        String redBoard=settings.getString("redBoard","");
        
        if(redBoard.length()==0)
        {
        	showToast("No Game Previously Saved.");
        	return;
        }
        
        
		Intent intent = new Intent(Checkers.this,Game.class);
		intent.putExtra("type", "cont");
		startActivity(intent);
    	
    }
    
    private void exit()
    {
    	this.finish();
    }
    
    private void showMessage(String messageString) 
    {
    	Intent i = new Intent(this, MessageDisplay.class);
    	i.putExtra("Message", messageString);
    	startActivity(i);
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
}