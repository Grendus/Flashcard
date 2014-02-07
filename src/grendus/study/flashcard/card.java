package grendus.study.flashcard;

public class card 
{
	private String question;
	private String answer;
	
	public card(String q, String a)
	{
		question = q;
		answer = a;
	}
	public String getQuestion()
	{
		return question;
	}
	
	public String getAnswer()
	{
		return answer;
	}
}
