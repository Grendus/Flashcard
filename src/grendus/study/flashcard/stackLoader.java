package grendus.study.flashcard;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;
import java.lang.Exception;
public class stackLoader extends ListActivity
{
	File rootFile;
	ArrayList<String> files;
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stackloader);
        files = new ArrayList<String>();
        rootFile = getExternalFilesDir(null);
		
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
			File[] fileList = rootFile.listFiles();
			if(!fileList[position].getName().equals(".nomedia"))
			{
				flashcard.stackOfCards.setStack(fileList[position]);
			}
		}
		catch(Exception e)
		{
			AlertDialog ad = new AlertDialog.Builder(this).create();
			ad.setMessage(e.getMessage());
			ad.show();
		}
		finish();
	}
}
