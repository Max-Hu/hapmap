package version1;

import java.io.IOException;

/**
 * Created by zhihu on 16/1/31.
 */
public class Exectution {

    public static void main(String[] args) {
        try {
            DataProcess dataProcess = new DataProcess();
            dataProcess.process();
        }catch (IOException ex){
            System.out.println("error");
        }

    }

}
