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
    private String line;

    public String getLine() {
        return line;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getContent() {
        return content;
    }

    public String getAnswerAuthor() {
        return answerAuthor;
    }

    public String getAnswer() {
        return answer;
    }

    public StockInfo(String stockinfo){
        line = stockinfo;
        String[] arrayInfo = new String[8];
        arrayInfo = stockinfo.split("\t");
        id=arrayInfo[0];
        title=arrayInfo[1];
        author=arrayInfo[2];
        date=arrayInfo[3];
        lastUpdate=arrayInfo[4];
        content=arrayInfo[5];
        answerAuthor=arrayInfo[6];
        answer=arrayInfo[7];
    }


}
