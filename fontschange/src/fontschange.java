import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fontschange {
    public static void main(String[] args) throws Exception {
        // nio讀取每行txt轉文件流輸出
        Path path = Paths.get("C:/Users/Ryujo_PAD/Desktop/fonts.txt");
        List lines = new ArrayList();
        lines = Files.readAllLines(path);
        File f = new File("C:/Users/Ryujo_PAD/Desktop/fonts2.txt");
        FileOutputStream fop = new FileOutputStream(f);
        OutputStreamWriter writer = new OutputStreamWriter(fop);
        // 逐行轉換為字庫數組
        int conter = 0;//行计数器
        String getcn="no character";
        for (String str : (List<String>) lines) {
            conter++;
            if (str.equals("")) {
                conter = 0;
                writer.append(",\n");
            } else {
                //正則表達式過濾非漢字
                if (conter == 1) {
                    Pattern p = Pattern.compile("[^\u4e00-\u9fa5]");
                    Matcher m = p.matcher(str);
                    getcn = "\""+(m.replaceAll("")).substring(2)+"\"";
                    writer.append(str+"\n");
                }
                else if (conter == 3) {
                    writer.append("{");
                    writer.append(getcn+",\n");
                    writer.append(str+"\n");
                }
                else if(conter==5){
                    writer.append(str+"}");
                }
                else{
                    writer.append(str+"\n");
                }
            }

        }
        System.out.println("字庫生成完畢");
        writer.close();
        // Path newpath=Paths.get("C:/Users/Ryujo_PAD/Desktop/temp.txt");
        // String data2=Files.readString(newpath);
        // Path newpath1=Paths.get("C:/Users/Ryujo_PAD/Desktop/temp2.txt");
        // String data3=Files.readString(newpath1);
        // System.out.println(data.length());
        // System.out.println(data2.length());
        // System.out.println(data3.length());
    }
}
