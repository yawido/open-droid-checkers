package com.Android.Checkers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 
 * @author Doaa Ashour
 * This Activity is used to display messages at the end of a game.
 *
 */

public class MessageDisplay extends Activity 

{
@Override
public void onCreate(Bundle icicle) 
{
super.onCreate(icicle);
TextView resultView = new TextView(this);


Bundle extras = getIntent().getExtras();
String value = "empty";
if (extras != null) {
value = extras.getString("Message");
}
resultView.setText(value);
setContentView(resultView);
}


}