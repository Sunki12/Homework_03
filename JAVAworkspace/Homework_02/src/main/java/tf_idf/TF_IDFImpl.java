package tf_idf;

import javafx.util.Pair;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import util.StockSorter;
import vo.StockInfo;

import java.text.DecimalFormat;
import java.util.*;

public class TF_IDFImpl implements TF_IDF {
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter
     */
    @Override
    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos) {
        //TODO: write your code here
        //用HashSet存储经过分词后的words
        Set<String> wordAfSeg = new HashSet<>();
        wordAfSeg.addAll(words);
        //创建存储每个分词后的词和其键值对的LIst
        List<Pair<String, Double>> result = new ArrayList<>();
        //对于wordAfSeg中的每个词
        for (String str: wordAfSeg) {
            double IF_num = 0;
            double IDF_num = 0;
            //计算该词在文档中的出现次数
            for(int i = 0; i<wordAfSeg.size()-1;i++){
                if(str.equals(words.get(i))){
                    IF_num++;
                }
            }
            //计算该词在content中的出现次数
            for(int j = 0; j<stockInfos.length-1; j++){
                if(stockInfos[j].getContent().contains(str)){
                    IDF_num++;
                }
            }
            double IF = (IF_num)/words.size();
            double IDF = Math.log(stockInfos.length/(IDF_num+1));
            result.add(new Pair<>(str, IF * IDF));
        }
        //用Pair型数组存储键（str）-值(IF*IDF)对
        Pair<String, Double>[] res = new Pair[result.size()];
        res=result.toArray(res);
        //对IF*IDF降序排序，并返回
        for (int i = 0; i < res.length - 1; i++) {
            for (int j = 0; j < res.length - i - 1; j++) {
                if (res[j].getValue() < res[j + 1].getValue()) {
                    Pair<String, Double> tempPair = res[j];
                    res[j] = res[j + 1];
                    res[j + 1] = tempPair;
                }
            }
        }
        return res;
    }

}

