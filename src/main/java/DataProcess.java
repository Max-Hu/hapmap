import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zhihu on 16/2/4.
 */
public class DataProcess {

    private static int eachLine = 100;
    private static int topLine = 116416;
    private static int bundary = 80;

    private static String INPATH = "src/main/resource/hapmap.gz";
    private static String OUTPATH = "src/main/resource/out.xlsx";

    private LinkedList<DNA> DNAResult = new LinkedList<>();

    private LinkedList<TempDNA> tempDNAList = new LinkedList<>();

    public void process() throws IOException {
        File file = new File(INPATH);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader reader = new BufferedReader(read);
        int lineNumber = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            if (lineNumber == 0) {
                lineNumber++;
                continue;
            }
            List<String> list = translateLineToArray(line);
            addToExistList(list);
            createListInExistList(list);
            checkData(lineNumber);
//            System.out.println(lineNumber);
            lineNumber++;
        }
        read.close();
        reader.close();
        storeInExcel();
    }

    public List<String> translateLineToArray(String line){
        List<String> list;
        if(line.contains("\t")) {
            list = splitByTable(line);
        }else {
            list = splitBySpace(line);
        }
        return list;
    }

    public List<String> splitByTable(String line){
        String[] array = line.split("\t");
        LinkedList<String> outputList = new LinkedList<>();
        for (String cell: array) {
            if (cell.contains(" ")){
                String[] cellArray = cell.split(" ");
                outputList.add(cellArray[0]);
                outputList.add(cellArray[1]);
            }else {
                outputList.add(cell);
            }
        }
        return outputList;
    }

    public List<String> splitBySpace(String line){
        String[] array = line.split(" ");
        List<String> outputList = Arrays.asList(array);
        return outputList;
    }
//
//    public void createNewList(String line){
//        TempDNA tempDNA = new TempDNA();
//        if(line.contains("\t")) {
//            tempDNA = splitByTable(line,tempDNA,true);
//        }else {
//            tempDNA = splitBySpace(line,tempDNA,true);
//        }
//        tempDNAList.add(tempDNA);
//    }

    public void addToExistList(List<String> list){
        for (int i = 0; i < tempDNAList.size() ; i++) {
            TempDNA tempDNA = storeListInExistList(list,tempDNAList.get(i));
            tempDNAList.remove(i);
            tempDNAList.add(i,tempDNA);
        }
    }

    public TempDNA storeListInExistList(List<String> list, TempDNA tempDNA){
        LinkedList<String> AList = tempDNA.getTmpA();
        LinkedList<String> BList = tempDNA.getTmpB();
        int personId = 0;
        for (int i = 2; i < list.size(); i = i + 2) {
            String A = AList.get(personId) + list.get(i);
            String B = BList.get(personId) + list.get(i+1);
            AList.remove(personId);
            BList.remove(personId);
            AList.add(personId,A);
            BList.add(personId,B);
            personId ++;
        }
        tempDNA.setTmpA(AList);
        tempDNA.setTmpB(BList);
        return tempDNA;
    }

    public void createListInExistList(List<String> list){
        TempDNA tempDNA = new TempDNA();
        tempDNA.setRsID(list.get(0));
        tempDNA.setPhy(list.get(1));
        LinkedList<String> AList = tempDNA.getTmpA();
        LinkedList<String> BList = tempDNA.getTmpB();
        for (int i = 2; i < list.size(); i = i + 2) {
            AList.add(list.get(i));
            BList.add(list.get(i+1));
        }
        tempDNA.setTmpA(AList);
        tempDNA.setTmpB(BList);
        tempDNAList.add(tempDNA);
    }

    public void checkData(int lineNumber) throws IOException{
        TempDNA tempDNA = tempDNAList.get(0);
        if (tempDNA.getTmpA().get(0).length() == eachLine) {
            Map<String, Integer> mapA = transformListToMap(tempDNA.getTmpA());
            Map<String, Integer> mapB = transformListToMap(tempDNA.getTmpB());
            System.out.println("check out " + lineNumber);
//            if (mapA != null && mapB != null){
            if (mapA.size() > bundary && mapB.size() > bundary){
                DNA dna = new DNA(mapA.size(),mapB.size(),lineNumber, tempDNA.getRsID(),tempDNA.getPhy());
                DNAResult.add(dna);
                System.out.println("------Add to List " + lineNumber + "----------");
            }
            tempDNAList.remove(0);
        }
    }

    public void storeInExcel() throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = (Sheet) wb.createSheet("sheet1");
        int excelLine = 0;
        for (int i = 0; i < DNAResult.size(); i++) {
            DNA dna = DNAResult.get(i);
            Row row = sheet.createRow(excelLine);
            Cell rs = row.createCell(0);
            rs.setCellValue(dna.getRsID());
            Cell phy = row.createCell(1);
            phy.setCellValue(dna.getPhyPosit());
            Cell A = row.createCell(2);
            A.setCellValue(dna.getANumber());
            Cell B = row.createCell(3);
            B.setCellValue(dna.getBNumber());
            Cell lineNumber = row.createCell(4);
            lineNumber.setCellValue(dna.getLineNumber());
            Cell number = row.createCell(5);
            number.setCellValue(Math.abs(dna.getANumber()-dna.getBNumber()));
            System.out.println("write " + i + " line");
            excelLine ++;
        }
        OutputStream stream = new FileOutputStream(OUTPATH);
        wb.write(stream);
        stream.close();
    }

    public Map<String, Integer> transformListToMap(LinkedList<String> list){
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : list) {
//            if (map.size()<bundary) {
//                map = null;
//                break;
//            }
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str,1);
            }
        }
        return map;
    }




//    public TempDNA splitBySpace(String line,TempDNA tempDNA,boolean create){
//        String[] array = line.split(" ");
//        if (create){
//            tempDNA.setRsID(array[0]);
//            tempDNA.setPhy(array[1]);
//        }
//        int personId = 0;
//        for (int i = 2; i < array.length; i=i+2) {
//            String A = array[i];
//            String B = array[i+1];
//            String[] AB = {A,B};
//            tempDNA = addToTempDNA(AB,personId,tempDNA,create);
//            personId ++;
//        }
//        return tempDNA;
//    }
//
//    public TempDNA splitByTable(String line,TempDNA tempDNA,boolean create){
//        String[] array = line.split("\t");
//        int personId = 0;
//        for (int i = 2; i < array.length; i++) {
//            String[] cell = array[i].split(" ");
//            tempDNA = addToTempDNA(cell,personId,tempDNA,create);
//            personId++;
//        }
//        return tempDNA;
//    }

    public TempDNA addToTempDNA(String[] AB, int personId,TempDNA tempDNA, boolean create){
        String A = AB[0];
        String B = AB[1];
        LinkedList<String> AList = tempDNA.getTmpA();
        LinkedList<String> BList = tempDNA.getTmpB();
        if (create){
            AList.add(A);
            BList.add(B);
        }else {
            A = AList.get(personId) + A;
            AList.remove(personId);
            AList.add(personId,A);
            B = BList.get(personId) + B;
            BList.remove(personId);
            BList.add(personId,B);
        }
        tempDNA.setTmpA(AList);
        tempDNA.setTmpB(BList);
        return tempDNA;
    }
}
