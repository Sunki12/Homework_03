package segmenter;


import vo.StockInfo;

import java.util.List;
import java.util.ArrayList;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;


import java.util.*;


public class ChineseSegmenterImpl implements ChineseSegmenter {

    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmenterImpl
     * Example: 我今天特别开心 result : 我 今天 特别 开心
     *
     * @param stocks stocks info
     * @return chinese word
     * @see ChineseSegmenterImpl
     */
    @Override
    public List<String> getWordsFromInput(StockInfo[] newdate) {
        //TODO: write your code here
    	List<String> list=new ArrayList<String>();
		Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        for(int m=1;m<newdate.length;m++)
        {
           String str=newdate[m].getAnswer();
           Result result = ToAnalysis.parse(str);
           List<Term> terms = result.getTerms();
           for(int n=0;n<terms.size();n++) {
        	   String word = terms.get(n).getName(); 
               String natureStr = terms.get(n).getNatureStr(); 
               if(expectedNature.contains(natureStr)) list.add(word);
           }
        }
        for(int m=1;m<newdate.length;m++)
        {
           String str=newdate[m].getContent();
           Result result = ToAnalysis.parse(str);
           List<Term> terms = result.getTerms();
           for(int n=0;n<terms.size();n++) {
        	   String word = terms.get(n).getName(); 
               String natureStr = terms.get(n).getNatureStr(); 
               if(expectedNature.contains(natureStr)) list.add(word);
           }
        }
        for(int m=1;m<newdate.length;m++)
        {
           String str=newdate[m].getTitle();
           Result result = ToAnalysis.parse(str);
           List<Term> terms = result.getTerms();
           for(int n=0;n<terms.size();n++) {
        	   String word = terms.get(n).getName(); 
               String natureStr = terms.get(n).getNatureStr(); 
               if(expectedNature.contains(natureStr)) list.add(word);
           }
        }
        return list;
    }
    
    
   public List<String> getWordsFromInput2(StockInfo[] newdate) {
        //TODO: write your code here
    	List<String> list=new ArrayList<String>();
		Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        for(int m=1;m<newdate.length;m++)
        {
           String str=newdate[m].getContent();
           Result result = ToAnalysis.parse(str);
           List<Term> terms = result.getTerms();
           for(int n=0;n<terms.size();n++) {
        	   String word = terms.get(n).getName(); 
               String natureStr = terms.get(n).getNatureStr(); 
               if(expectedNature.contains(natureStr)) list.add(word);
           }
        }
        return list;
    }
}
