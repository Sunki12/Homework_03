package vo;

public class StockInfo {
	private String id;
	private String title;
	private String author;
	private String date;
	private String lastUpdate;
	private String content;
	private String answerAuthor;
	private String answer;

	public void setId(String a)
	{
		id=a;
	}
	public void setTitle(String a)
	{
		title=a;
	}
	public void setAuthor(String a)
	{
		author=a;
	}
	public void setDate(String a)
	{
		date=a;
	}
	public void setLastUpdate(String a)
	{
		lastUpdate=a;
	}
	public void setContent(String a)
	{
		content=a;
	}
	public void setAnswerAuthor(String a)
	{
		answerAuthor=a;
	}
	public void setAnswer(String a)
	{
		answer=a;
	}
	public String getId() 
	{
		return id;
	}
	public String getTitle() 
	{
		return title;
	}
	public String getAuthor() 
	{
		return author;
	}
	public String getDate() 
	{
		return date;
	}
	public String getLastUpdate() 
	{
		return lastUpdate;
	}
	public String getContent() 
	{
		return content;
	}
	public String getAnswerAuthor() 
	{
		return answerAuthor;
	}
	public String getAnswer() 
	{
		return answer;
	}
}

