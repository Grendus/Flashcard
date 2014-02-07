package grendus.study.flashcard;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Stack_Saver_Activity extends ListActivity implements OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_stack);
        ArrayList<String> files = new ArrayList<String>();
        File rootFile = getExternalFilesDir(null);
        ((Button)findViewById(R.id.save_button)).setOnClickListener(this);
		
        if(rootFile != null)
        {
        	for(File temp:rootFile.listFiles())
        		files.add(temp.getName());
        }
        else
        {
        	AlertDialog ad = new AlertDialog.Builder(this).create();
			ad.setMessage("External storage is not mounted on this device. Either insert an external storage card, or dismount your device from your computer.\n\nEven if your device doesn't have expandable memory, it still has a section of storage marked as external. If your device is currently mounted to a PC, however, this app cannot access that section of storage.");
			ad.show();
        }
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.row ,files));
    }
    
    protected void onListItemClick (ListView l, View v, int position, long id)
	{
		try
		{
			File[] fileList = getExternalFilesDir(null).listFiles();
			if(!fileList[position].getName().equals(".nomedia"))
			{
				((EditText)findViewById(R.id.file_name)).setText(fileList[position].getName());
			}
		}
		catch(Exception e)
		{
			AlertDialog ad = new AlertDialog.Builder(this).create();
			ad.setMessage(e.getMessage());
			ad.show();
		}
	}
    
    public void onClick(View v)
    {
    	String fileName = ((EditText)findViewById(R.id.file_name)).getText().toString();
    	File saveFile = new File(getExternalFilesDir(null), fileName);
    	try
    	{
    		FileOutputStream writer = new FileOutputStream(saveFile);
    		writer.write(flashcard.stackOfCards.saveDeck().getBytes());
    	}
    	catch(Exception e)
    	{
    		AlertDialog ad = new AlertDialog.Builder(this).create();
			ad.setMessage("External storage is not mounted on this device. Either insert an external storage card, or dismount your device from your computer.\n\nEven if your device doesn't have expandable memory, it still has a section of storage marked as external. If your device is currently mounted to a PC, however, this app cannot access that section of storage.\n\n"+e.getMessage());
			ad.show();
    	}
    	finish();
    }
}
