package util;

import vo.StockInfo;
import vo.UserInterest;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandlerImpl implements FileHandler {

    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     */
    @Override
    public StockInfo[] getStockInfoFromFile(String filePath) {
        //TODO: write your code here
        ArrayList<StockInfo> stockInfoArrayList = new ArrayList<>();
        File file = new File(filePath);
        try{
            Scanner scan = new Scanner(new FileInputStream(file),"UTF-8");
            scan.useDelimiter("\n");
            while(scan.hasNext()){
                StockInfo stockInfo = new StockInfo(scan.next());
                stockInfoArrayList.add(stockInfo);
            }
            scan.close();
        }
        catch(IOException e)//针对FileInputStream做出异常处理
        {
            System.out.println("文件路径错误");
            System.exit(1);//非正常中断
        }
        //把stockInfoArrayList转换为数组stockInfoArray
        StockInfo[] stockInfoArray = new StockInfo[stockInfoArrayList.size()];
        stockInfoArrayList.toArray(stockInfoArray);
        return stockInfoArray;
    }

    /**
     * This func gets user interesting from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return
     */
    @Override
    public UserInterest[] getUserInterestFromFile(String filePath) {
        ArrayList<UserInterest> stockArrayList = new ArrayList<>();
        File file = new File(filePath);
        try{
            Scanner scan = new Scanner(new FileInputStream(file),"UTF-8");
            while(scan.hasNext()){
                UserInterest userInterest = new UserInterest(scan.next());
                stockArrayList.add(userInterest);
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("文件路径错误");
            System.exit(1);
        }
        UserInterest[] stockArray = new UserInterest[stockArrayList.size()];
        stockArrayList.toArray(stockArray);
        return stockArray;
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) {
        //TODO: write your code here
        try{
            File file = new File("c:/similarMatrix.txt");
            System.out.print("ahahah");
            PrintStream out = new PrintStream(new FileOutputStream(file));
            for (double[] aMatrix : matrix) {
                for (int j = 0; j < aMatrix.length; j++) {
                    out.print(aMatrix[j]);
                    if (j != aMatrix.length - 1) {
                        out.print("\t");
                    }
                }
                out.print("\r\n");
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This function need write recommend to files
     *
     * @param recommend the recommend you calculate
     */
    @Override
    public void setRecommend2File(double[][] recommend) {
        //TODO: write your code here
        try{
            File file = new File("c://recommend.txt");
            PrintStream out = new PrintStream(new FileOutputStream(file));
            for (double[] aRecommend : recommend) {
                for (int j = 0; j < aRecommend.length; j++) {
                    out.print(aRecommend[j]);
                    if (j != aRecommend.length - 1) {
                        out.print("\t");
                    }
                }
                out.print("\r\n");
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
