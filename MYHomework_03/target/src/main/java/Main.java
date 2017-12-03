
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import com.github.davidmoten.guavamini.Lists;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.FontWeight;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.image.AngleGenerator;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;

import javafx.util.Pair;
import recommend.RecommenderImpl;
import tf_idf.TF_IDFImpl;
import util.FileHandlerImpl;
import vo.StockInfo;

public class Main {
	static StockInfo[] stockInfos=new StockInfo[100];
	static List<String> keys;
	static double[][] similar;//全局变量
	
	
    public static void main(String[] args) throws IOException {
        String[] words = new String[]{
                "词频","java","考试","小明","毕业"
        };
        List<String> s = Lists.newArrayList(words);
        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128); 
        }
        
        //添加界面
        JFrame frame=new JFrame("搜索器");
        frame.setBounds(400,200,400,300);
        frame.setLayout(null);
        JButton select=new JButton("select");//按钮select
        frame.add(select);
        select.setBounds(160,30,100,30);
        //监听事件
        select.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		//写入文件选择器
        		JFileChooser chooser=new JFileChooser();
        		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        		chooser.showDialog(new JLabel(), "choose");
        		File fileselect=chooser.getSelectedFile();
        		String path = fileselect.getAbsolutePath(); 
        		if(fileselect.isDirectory()){  
                    System.out.println("文件夹:"+fileselect.getAbsolutePath());  
                }else if(fileselect.isFile()){  
                    System.out.println("文件:"+fileselect.getAbsolutePath());  
                }  
        	//System.out.println(chooser.getSelectedFile());//返回获取文件	
        	/*	try {
					FileWriter output=new FileWriter(fileselect);
					output.write("读取成功");
					output.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//获取文件*/
        		
        		
        	//文件处理	filehander
        		FileHandlerImpl fileHandler=new FileHandlerImpl();
        		try {
					stockInfos =fileHandler.getStockInfoFromFile(path);
					//读取的文件的关键词矩阵
	        		RecommenderImpl recommend=new RecommenderImpl();
	        		similar=recommend.calculateMatrix(stockInfos);//similar
	        	    keys=recommend.keys(stockInfos);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}     
        	//data的关键词矩阵输出完毕
            //并且输出二十个关键词序列
        }
        });
        
        final JTextField ss=new JTextField();
        frame.add(ss);
        ss.setBounds(40,30,100,30);
        final JTextArea show=new JTextArea();
        frame.add(show);
        show.setBounds(40,80,200,200);
        JButton search=new JButton("search");
        frame.add(search);
        search.setBounds(280,30,100,30);
        search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//进行搜索显示
        		
        	//对输入的文件进行分词
        		Set<String> expectedNature = new HashSet<String>() {{
                    add("n");add("v");add("vd");add("vn");add("vf");
                    add("vx");add("vi");add("vl");add("vg");
                    add("nt");add("nz");add("nw");add("nl");
                    add("ng");add("userDefine");add("wh");
                }};
                List<String> listss=new ArrayList<String>();
                String str =""+ss.getText() ;
                Result result = ToAnalysis.parse(str); //分词结果的一个封装，主要是一个List<Term>的terms

                List<Term> terms = result.getTerms(); //拿到terms
                for(int i=0; i<terms.size(); i++) {
                    String word = terms.get(i).getName(); //拿到词
                    String natureStr = terms.get(i).getNatureStr(); //拿到词性
                    if(expectedNature.contains(natureStr)) {
                       listss.add(natureStr);
                    }
                }
                //分词完毕
                //以下进行输入值一维数组生成
                double []input=new double[100];
                for(int k=0;k<keys.size();k++) {
                	double count=0;
                	for(int l=0;l<listss.size();l++) {
                		if(keys.get(k)==listss.get(l))
                		{
                			count++;
                		}
                	}
                    input[k]=count;
                }
        		
                //以下计算输入文本和已有文件相似度
                double [] sim=new double [100];
                for(int i=0;i<60;i++)
                {
                	double molecular=0;
            		double denominator1=0;
            		double denominator2=0;
            		for(int k=0;k<20;k++)
            		{
            			molecular=molecular+similar[i][k]*input[k];
            			denominator1=denominator1+similar[i][k]*similar[i][k];
            			denominator2=denominator2+input[k]*input[k];
            		}
            		double denominator=Math.sqrt(denominator2)*Math.sqrt(denominator1);
            		sim[i]=molecular/denominator;
                }
                //已经得出和现有的60条文件的相似度
                
                //以下对这六十条文件进行降序排序
                for(int i=0;i<60;i++) {
                	for(int j=0;j<60;j++) {
                		if(sim[i]<sim[j])
            			{
            				double m=sim[i];
        					sim[i]=sim[j];
        					sim[j]=m;
            			}
                	}
                }
                
                //输出最大的十个记录
                int count[] =new int[10] ;
               for(int i=0;i<10;i++) 
               {
            	   int m;
            	   for(m=0;m<60;m++)
            	   {
            		   double max=sim[0];
            		   if(sim[m]>max)
            		   {
            			   max=sim[m];
            			   count[i]=m;
            		   }
            	   }
            	   sim[count[i]]=0;
               }
                for(int i=0;i<10;i++)
                {
               show.append(stockInfos[count[i]].getId()+" "+stockInfos[count[i]].getTitle()+" "+
               stockInfos[count[i]].getAuthor()+" "+stockInfos[count[i]].getDate()+" "+stockInfos[count[i]].getLastUpdate()
               +" "+stockInfos[count[i]].getContent()+" "+stockInfos[count[i]].getAnswerAuthor()+" "
               +stockInfos[count[i]].getAnswer()+"\n");//
                }
        	}
        });
        
        frame.setVisible(true);//设置窗体可见



        WordCloudBuilder.buildWordCouldByWords(200,200,4,20,10,s,new Color(-1),"data.png",colors);
    }

}





