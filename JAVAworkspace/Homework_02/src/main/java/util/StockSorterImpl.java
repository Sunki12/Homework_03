package util;

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
    public StockInfo[] sort(StockInfo[] info) {
        //TODO: write your code here
        //冒泡排序
        for(int i = 0; i < info.length - 1; i++){
            for(int j = 0; j < info.length - i - 1; j++){
                if(info[j].getAnswer().length() > info[j+1].getAnswer().length()){
                    StockInfo temp = info[j];
                    info[j] = info[j+1];
                    info[j+1] = temp;
                }
            }
        }
        return info;
    }

    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info, boolean order) {
        //TODO: write your code here
        if(order){
            for(int i = 0; i < info.length - 1; i++){
                for(int j = 0; j < info.length - i - 1; j++){
                    if(info[j].getAnswer().length() > info[j+1].getAnswer().length()){
                        StockInfo temp = info[j];
                        info[j] = info[j+1];
                        info[j+1] = temp;
                    }
                }
            }
        }else{
            for(int i = 0; i < info.length - 1; i++){
                for(int j = 0; j < info.length - i - 1; j++){
                    if(info[j].getAnswer().length() < info[j+1].getAnswer().length()){
                        StockInfo temp = info[j];
                        info[j] = info[j+1];
                        info[j+1] = temp;
                    }
                }
            }
        }
        return null;
    }
}
