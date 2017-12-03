package tf_idf;

import javafx.util.Pair;
import util.StockSorter;
import vo.StockInfo;

import java.util.HashMap;
import java.util.List;

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
    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos)
    {
        //TODO: write your code here
    	HashMap<String,Double> tf=TF(words);
    	HashMap<String,Double> idf=IDF(words,stockInfos);
    	Pair<String, Double>[] pair=new Pair[tf.size()];
    	for (int i=0;i<words.size()-1;i++)
		{
			for(int j=i+1;j<words.size();j++)
			{
				if(words.get(i).equals(words.get(j)))
				{
					words.set(j," ");
				}
			}
		}
    	for(int i=0;i<tf.size();i++) {
    if(!(words.get(i).equals(" "))) {
	double A = tf.get(words.get(i)).doubleValue();
	double B = idf.get(words.get(i)).doubleValue();
	double result = A * B;
	pair[i] = new Pair(words.get(i), result);
}
else
	{
		pair[i]=new Pair(" ",0.0);
	}
		}
    	return pair;
}

    public HashMap<String,Double> TF(List<String> words)
    {
    	HashMap<String,Double> tf=new HashMap<String,Double>();
    	double count=1;

    	for(int i=0;i<words.size();i++)
    	{
    		count=1;
    		for(int j=0;j<words.size();j++)
    		{
    			if(words.get(i)==words.get(j)&&i!=j)
    			{
    				count++;
    			}
    		}
    		double Frequency=count/words.size();
    		tf.put(words.get(i),Frequency );
    	}

 //  	for(int i=0;i<words.size();i++)
//   	{
//   		for(int j=i+1;j<words.size();j++)
//    		{
//    			if(tf.get(i)==tf.get(j))
//    			{
//    				tf.remove(j);
//    				j--;
//    			}
//    		}
//    	}

    	return tf;
    }

    public HashMap<String,Double> IDF(List<String> words,StockInfo[] stockInfos)
    {
    	HashMap<String,Double> idf=new HashMap<String,Double>();
        for(int i=0;i<words.size();i++)
        {
        	double count=0;
        	for(int j=1;j<stockInfos.length;j++)
        	{
        		if (stockInfos[j].getId().indexOf(words.get(i))!=-1
        				||stockInfos[j].getTitle().indexOf(words.get(i))!=-1
        				||stockInfos[j].getAuthor().indexOf(words.get(i))!=-1
        				||stockInfos[j].getDate().indexOf(words.get(i))!=-1
        				||stockInfos[j].getLastUpdate().indexOf(words.get(i))!=-1
        				||stockInfos[j].getContent().indexOf(words.get(i))!=-1
        				||stockInfos[j].getAnswerAuthor().indexOf(words.get(i))!=-1
        				||stockInfos[j].getAnswer().indexOf(words.get(i))!=-1)
				{
					count++;
				}
        	}
        	double frequency=Math.log((double)(stockInfos.length-1)/(count+1));
    		idf.put(words.get(i), frequency);
        }
        return idf;
    }
}


    
    
    
    
    
    
    
    
    
    
    