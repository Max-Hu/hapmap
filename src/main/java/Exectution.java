import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by zhihu on 16/1/31.
 */
public class Exectution {

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
//        try {
//            Util util = new Util(readFile.readFile());
//            util.formatToList();
//        }catch (IOException ex){
//            System.out.println("error");
//        }
        readFile.exe();

//        FileIO io = new FileIO();
//        LinkedList<DNAPiece> DNAList = io.formatDataintoList();
//        System.out.println(DNAList.get(0));
    }
}
