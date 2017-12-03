package util;

import javafx.util.Pair;
import vo.StockInfo;

public class StockSorterImpl implements StockSorter {
    /**
     * Accepting series of stock info, sorting them ascending according to their comment length.
     * List.sort() or Arrays.sort() are not allowed.
     * You have to write your own algorithms,Bubble sort、quick sort、merge sort、select sort,etc.
     *
     * @param info stock information
     * @return sorted stock
     */
    @Override
    public Pair<String, Double>[] sort(Pair<String, Double>[] pair) {
    	for(int i=0;i<pair.length-1;i++)
    	{
    		for(int j=i+1;j<pair.length;j++)
    		{
    			if(pair[i].getValue()<pair[j].getValue())
    			{
    				Pair<String,Double> m=pair[i];
					pair[i]=pair[j];
					pair[j]=m;
    			}
    		}
    	}
    	return pair;
    }

    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
	 public Pair<String, Double>[] sort(Pair<String, Double>[] pair, boolean order){
		if(order)
		{
			for(int i=0;i<pair.length-1;i++)
	    	{
	    		for(int j=i+1;j<pair.length;j++)
	    		{
	    			if(pair[i].getValue()<pair[j].getValue())
	    			{
	    				Pair<String,Double> m=pair[i];
						pair[i]=pair[j];
						pair[j]=m;
	    			}
	    		}
	    	}
		}
		else
		{
			for(int i=0;i<pair.length-1;i++)
	    	{
	    		for(int j=i+1;j<pair.length;j++)
	    		{
	    			if(pair[i].getValue()>pair[j].getValue())
	    			{
	    				Pair<String,Double> m=pair[i];
						pair[i]=pair[j];
						pair[j]=m;
	    			}
	    		}
	    	}
		}
		return pair;
	}
}
