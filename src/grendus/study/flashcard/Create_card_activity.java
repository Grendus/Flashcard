/**
 * 
 */
package grendus.study.flashcard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Dale
 *
 */
public class Create_card_activity extends Activity implements OnClickListener 
{
	TextView question_input;
	TextView answer_input;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        
        //set the OnClickListener for the buttons
    	((Button)findViewById(R.id.add_card_button)).setOnClickListener(this);
    	((Button)findViewById(R.id.back_button)).setOnClickListener(this);
    	question_input = (TextView)findViewById(R.id.question_input);
    	answer_input = (TextView)findViewById(R.id.answer_input);
    }
    
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    		case R.id.add_card_button:
    			if(!question_input.getText().toString().equals("") && !answer_input.getText().toString().equals(""))
    			{
    				flashcard.stackOfCards.addCard(new card(question_input.getText().toString(), answer_input.getText().toString()));
    				question_input.setText("");
    				answer_input.setText("");
    			}
    			break;
    		case R.id.back_button: finish();
    	}
    }
}
