package grendus.study.flashcard;
import java.io.File;
import java.util.Scanner;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;

public class cardstack 
{
	private ArrayList<card> reviewStack;
	private ArrayList<card> viewedStack;
	
	public cardstack()
	{
		reviewStack = new ArrayList<card>();
		viewedStack = new ArrayList<card>();
	}
	
	public void setStack(File QA)
	{
		try
		{
			Scanner reader = new Scanner(QA);
			
			while(reader.hasNext())
			{
				reviewStack.add(new card(reader.nextLine(), reader.nextLine()));
			}
		}
		//todo: handle specific exceptions
		catch(Exception e)
		{
			
		}
	}
	
	//remove all cards from both decks
	
	public void clearStack()
	{
		reviewStack = new ArrayList<card>();
		viewedStack = new ArrayList<card>();
	}
	
	//adds a card to the deck
	public void addCard(card newCard)
	{
		reviewStack.add(newCard);
	}
	
	public String saveDeck()
	{
		String cardStack = new String();
		for(int i = 0; i<reviewStack.size(); i++)
		{
			card temp = reviewStack.get(i);
			cardStack += (temp.getQuestion()+"\n"+temp.getAnswer()+"\n");
		}
		for(int i = 0; i<viewedStack.size(); i++)
		{
			card temp = viewedStack.get(i);
			cardStack += (temp.getQuestion()+"\n"+temp.getAnswer()+"\n");
		}
		return cardStack;
	}
	
	public card getCard(boolean random)
	{
		if(reviewStack.size()==0)
		{
			if(viewedStack.size() == 0)
				return null;
			reviewStack = viewedStack;
			viewedStack = new ArrayList<card>();
			return getCard(random);
		}
		else if(random)
		{
			Random randy = new Random();
			card tempCard = reviewStack.remove(randy.nextInt(reviewStack.size()));
			viewedStack.add(tempCard);
			return tempCard;
		}
		else
		{
			card tempCard = reviewStack.remove(0);
			viewedStack.add(tempCard);
			return tempCard;
		}
	}
}
