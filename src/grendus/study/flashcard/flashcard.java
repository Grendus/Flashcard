package grendus.study.flashcard;

//icon was taken from the public domain, at url http://www.clker.com/clipart-8139.html

import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Menu;

public class flashcard extends Activity implements OnClickListener 
{
	TextView questionField;
	TextView answerField;
	Button nextButton;
	Button hideOrShowButton;
	ToggleButton randomToggleButton;
	
	public static cardstack stackOfCards;
	private card currentQuestion;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	
    	stackOfCards = new cardstack();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        try
		{
			FileOutputStream nomedia = new FileOutputStream(new File(getExternalFilesDir(null), ".nomedia"));
			nomedia.write('a');
			nomedia.close();
		}
		catch(Exception e)
		{
		}
        questionField = (TextView)findViewById(R.id.questionView);
        answerField = (TextView)findViewById(R.id.answerView);
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        hideOrShowButton = (Button)findViewById(R.id.hideButton);
        hideOrShowButton.setOnClickListener(this);
        
        randomToggleButton = (ToggleButton)findViewById(R.id.randomToggleButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_flashcard, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem menu)
    {
    	Intent loader;
    	switch(menu.getItemId())
    	{
    		case R.id.menu_import: 	loader = new Intent(getBaseContext(), stackLoader.class);
    								startActivity(loader);
    								break;
    		case R.id.menu_create:	loader = new Intent(getBaseContext(), Create_card_activity.class);
									startActivity(loader);
									break;
    		case R.id.menu_save: 	loader = new Intent(getBaseContext(), Stack_Saver_Activity.class);
									startActivity(loader);
									break;
    		case R.id.menu_clear_stack:
    								stackOfCards.clearStack();
    								questionField.setText("");
    								answerField.setText("");
    								currentQuestion = null;
    								break;
    	}
    	return false;
    }
    
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    		case R.id.nextButton: 
    			currentQuestion = stackOfCards.getCard(randomToggleButton.isChecked());
    			if(currentQuestion!=null)
    			{
    				questionField.setText(currentQuestion.getQuestion());
    				answerField.setText("");
    				hideOrShowButton.setText("Show Answer");
    			}
    			break;
    		case R.id.hideButton: 
    			if(currentQuestion != null && answerField.getText().equals(""))
    			{
    				answerField.setText(currentQuestion.getAnswer());
    				hideOrShowButton.setText("Hide Answer");
    			}
    			else if (currentQuestion != null)
    			{
    				answerField.setText("");
    				hideOrShowButton.setText("Show Answer");
    			}
    			break;
    	}
    	
    }
    
}
