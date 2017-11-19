package vo;

import java.util.ArrayList;

public class UserInterest {
    private Integer[] interest;
    public Integer[] getInterest(){
        return interest;
    }
    //构造方法,把用户感兴趣的ID号存入ArrayList中
    public UserInterest(String line){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i)=='1'){
                temp.add(i+1);
            }
        }
        //把List类型的temp转换为数组
        interest = new Integer[temp.size()];
        temp.toArray(interest);

    }
}
