package util;

import java.io.*;
import java.util.ArrayList;

import vo.StockInfo;
import vo.UserInterest;

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
    public StockInfo[] getStockInfoFromFile(String filePath) throws UnsupportedEncodingException {
        //TODO: write your code here
    	FileInputStream file=null;

		try {
			file=new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStreamReader files=null;
		try{
			files=new InputStreamReader(file,"utf-8");
		}catch (UnsupportedEncodingException el){
			el.printStackTrace();
		}
		BufferedReader bufferedreader = new BufferedReader(files);
		ArrayList<StockInfo> list = new ArrayList<StockInfo>();
		String t=null;
		try {
			while((t=bufferedreader.readLine())!=null) {
				String []node=t.split("\\s+");
				StockInfo record=new StockInfo();
				record.setId(node[0]);
				record.setTitle(node[1]);
				record.setAuthor(node[2]);
				record.setDate(node[3]);
				record.setLastUpdate(node[4]);
				record.setContent(node[5]);
				record.setAnswerAuthor(node[6]);
				record.setAnswer(node[7]);
				list.add(record);

			}
			bufferedreader.close();
			files.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StockInfo [] newdate=new StockInfo[list.size()];
		int i=0;
		while(i<list.size()) {
			newdate[i]=list.get(i);
			i++;
		}
		return newdate;
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
    	UserInterest[] userInterests=new UserInterest[500];
        for(int i=0;i<500;i++){
            userInterests[i]=new UserInterest();
        }
        try {
            FileReader fileReader=new FileReader(filePath);
            int mark;
            int row=0;int column=0;
            while((mark=fileReader.read())!=1&&row<500){
                if(mark!=2){
                    userInterests[row].array[column]=mark;
                    column++;
                    if(column>=60){
                        row++;
                        column=0;
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userInterests;
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) {
        //TODO: write your code here
    	StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<60;i++){
            for(int j=0;j<60;j++){
                stringBuilder.append(matrix[i][j]);
                if(j!=59)
                    stringBuilder.append("\t");
            }
            stringBuilder.append('\n');
        }
        BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(this.getClass().getClassLoader().getResource(".").getPath()+"CloseMatrix.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //BufferedWriter writer=new BufferedWriter(new FileWriter("D:/CloseMatrix.txt"));
        try {
			writer.write(stringBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
    	
    	StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<500;i++){
            for(int j=0;j<10;j++){
                stringBuilder.append(recommend[i][j]);
                if(j!=9)
                    stringBuilder.append("\t");
            }
            stringBuilder.append('\n');
        }
        BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(this.getClass().getClassLoader().getResource(".").getPath()+"Recommend.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //BufferedWriter writer=new BufferedWriter(new FileWriter("D:/Recommend.txt"));
        try {
			writer.write(stringBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
