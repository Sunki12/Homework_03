package segmenter;


import org.ansj.splitWord.analysis.ToAnalysis;
import vo.StockInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;


import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;


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
    public List<String> getWordsFromInput(StockInfo[] stocks) {
        //TODO: write your code here
        Set<String> expectedNature = new HashSet<String>(){{
            add("n");add("v");add("nl");add("nt");
            add("vd");add("vn");add("vf");add("vx");
            add("vi");add("vl");add("vg");add("nz");
            add("nw");add("ng");add("d");add("a");add("ad");add("an");
        }};
        //用来存储符合条件的字符串
        List<String> answerSegList = new ArrayList<>();
        for(int i = 0; i<stocks.length; i++){
            //分词结果的一个封装，主要是一个List<Term>的terms
            Result result = ToAnalysis.parse(stocks[i].getContent());
            List<Term> terms = result.getTerms();
            for(int j = 0; j<terms.size();j++){
                String word = terms.get(j).getName(); //拿到词
                String natureStr = terms.get(j).getNatureStr();//拿到词性
                if (expectedNature.contains(natureStr)){
                    answerSegList.add(word);
                }
            }
        }
        return answerSegList;
    }
}
