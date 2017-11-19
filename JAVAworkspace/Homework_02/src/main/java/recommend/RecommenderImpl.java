package recommend;

import javafx.util.Pair;
import segmenter.ChineseSegmenter;
import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDFImpl;
import vo.StockInfo;
import vo.UserInterest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class RecommenderImpl implements Recommender {

    /**
     * this function need to calculate stocks' content similarity,and return the similarity matrix
     *
     * @param stocks stock info
     * @return similarity matrix
     */
    @Override
    public double[][] calculateMatrix(StockInfo[] stocks) {
        //TODO: write your code here
        double[][] similarityMatrix = new double[stocks.length][stocks.length];
        ChineseSegmenterImpl seg = new ChineseSegmenterImpl();
        Set<String> temp=new HashSet<>();
        TF_IDFImpl tf_idf=new TF_IDFImpl();
        for (int i = 0; i < stocks.length; i++) {
            StockInfo stock = stocks[i];
            List<String> listString;
            listString = seg.getWordsFromInput(stocks);
            Pair<String, Double>[] res;
            res= tf_idf.getResult(listString,stocks);
            System.out.println(res.length);
            for (int n=0;n<res.length&&n<20;n++){
                temp.add(res[n].getKey());
            }
            for (int j=0;j<stocks.length;j++){
                Set<String> temp_j;
                temp_j=temp;
                List<String> listStr_j;
                listStr_j= seg.getWordsFromInput(stocks);
                Pair<String, Double>[] res_j;
                res_j=tf_idf.getResult(listString,stocks);
                for (int k=0;k<20&&k<res_j.length;k++){
                    temp_j.add(res[k].getKey());
                }
                int[] itf=new int[temp_j.size()];
                int count=0;
                int[] jtf=new int[temp_j.size()];
                for (String str :temp_j){
                    for (int m=listString.size()-1;m>=0;m--){
                        if (listString.get(m).equals(str)){
                            itf[count]++;
                        }
                    }
                    for (int m=listStr_j.size()-1;m>=0;m--){
                        if (listStr_j.get(m).equals(str)){
                            jtf[count]++;
                        }
                    }
                    count++;
                }
                int sum_i=0;
                int sum_j=0;
                int sum_i_j=0;
                for (int k=0;k<temp_j.size();k++){
                    sum_i+=itf[k]*itf[k];
                    sum_j+=jtf[k]*jtf[k];
                    sum_i_j+=itf[k]*jtf[k];
                }
                similarityMatrix[i][j]=sum_i_j/(Math.sqrt(sum_i)*Math.sqrt(sum_j));
            }
        }
        return null;
    }

    /**
     * this function need to recommend the most possibility stock number
     *
     * @param matrix       similarity matrix
     * @param userInterest user interest
     * @return commend stock number
     */
    @Override
    public double[][] recommend(double[][] matrix, UserInterest[] userInterest) {
        //TODO: write your code here
        double[][] result =new double[userInterest.length][10];
        for (int i=0;i<userInterest.length;i++){
            Integer[] user_i=userInterest[i].getInterest();
            Pair[] rec=new Pair[matrix.length];
            for (int k=0;k<matrix.length;k++){
                double temp=0;
                for (int j=0;j<user_i.length;j++){
                    temp+=matrix[user_i[j]-1][k];
                }
                rec[k]=new Pair<>(k,temp);
            }
            rec=this.sort(rec);
            for (int m=0;m<10;m++){
                result[i][m]= (double) rec[m].getKey();
            }
        }
        return result;
    }
    private Pair<Integer,Double>[] sort(Pair<Integer,Double>[] pairarray){
        for (int i = 0; i < pairarray.length - 1; i++) {
            for (int j = 0; j < pairarray.length - i - 1; j++) {
                if (pairarray[j].getValue() > pairarray[j + 1].getValue()) {
                    Pair<Integer,Double> temp = pairarray[j];
                    pairarray[j] = pairarray[j + 1];
                    pairarray[j + 1] = temp;
                }
            }
        }
        return pairarray;
    }
}
