import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zhihu on 16/2/1.
 */
public class FileIO {


    private static String INPATH = "src/main/resource/hapmap.gz";
    private static String OUTPATH = "src/main/resource/out.xlsx";

    public LinkedList<DNAPiece> formatDataintoList() {
        LinkedList<DNAPiece> DNAList = new LinkedList<>();
        try {
            File file = new File(INPATH);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader reader = new BufferedReader(read);
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                DNAPiece DNA = new DNAPiece();
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                else {
                    if (line.contains("\t")){
                        splitByTable(line,DNA);
                    }else {
                        splitBySpace(line,DNA);
                    }
                }
                lineNumber++;
                DNAList.add(DNA);
            }
            reader.close();
        } catch (IOException e){
            System.out.println("error");
        }
        return DNAList;
    }

    public void splitBySpace(String line, DNAPiece DNA){
        String[] array = line.split(" ");
        DNA.setRsID(array[0]);
        DNA.setPhysPosit(array[1]);
        LinkedList<String> peopleList = new LinkedList<>();
        for (int i = 2; i < array.length; i=i+2) {
            String contain = array[i] + array[i+1];
            peopleList.add(contain);
        }
        DNA.setPeopleList(peopleList);
    }

    public void splitByTable(String line, DNAPiece DNA){
        String[] array = line.split("\t");
        DNA.setRsID(array[0]);
        DNA.setPhysPosit(array[1]);
        LinkedList<String> peopleList = new LinkedList<>();
        for (int i = 2; i < array.length; i++) {
            String[] cell = array[i].split(" ");
            String contain = cell[0] + cell[1];
            peopleList.add(contain);
        }
        DNA.setPeopleList(peopleList);
    }


}
