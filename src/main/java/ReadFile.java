import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by zhihu on 16/1/31.
 */
public class ReadFile {

    private static int eachLine = 100;
    private static int topLine = 116416;
    private static int bundary = 40;

    private static String INPATH = "src/main/resource/hapmap.gz";
    private static String OUTPATH = "src/main/resource/out.xlsx";

    private boolean first = true;

    private Workbook wb = new XSSFWorkbook();
    private Sheet sheet = (Sheet) wb.createSheet("sheet1");
    private int excelLine = 0;

    private LinkedList<String> DNAListB = new LinkedList<>();
    private LinkedList<String> DNAListA = new LinkedList<>();
    private LinkedList<String> DNAListAB = new LinkedList<>();
    private LinkedList<DNA> DNAList = new LinkedList<>();
    private String rsID;
    private String phyPosit;


//    public void readDataByline() {
//
//        try {
//            Map<Integer, Integer> result = new LinkedHashMap();
//            File file = new File(INPATH);
//            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
//            BufferedReader reader = new BufferedReader(read);
//            String line;
//            int lineNumber = 0;
//
//            while ((line = reader.readLine()) != null) {
//                if (lineNumber == 0) {
//                    lineNumber++;
//                    continue;
//                }
//                else {
//                    if (lineNumber%eachLine == 0){
//                        storeData(lineNumber);
//                    }else {
//                        if (line.contains("\t")){
//                            splitByTable(line);
//                        }else {
//                            splitBySpace(line);
//                        }
//                    }
//                }
//                lineNumber++;
//                System.out.println("#");
//            }
//            storeData(lineNumber);
////            result = sortMap(result);
//            reader.close();
//            OutputStream stream = new FileOutputStream(OUTPATH);
//            wb.write(stream);
//            stream.close();
//        } catch (IOException e){
//            System.out.println("error");
//        }
//        System.out.println("-------------finish!--------------");
//
//    }

    public void exe(){
        for (int i = 1; i < topLine; i++) {
            getDNAPieces(i,eachLine);
        }
        try {
            writeInExcel();
            OutputStream stream = new FileOutputStream(OUTPATH);
            wb.write(stream);
            stream.close();
            System.out.println("finish!");
        } catch (IOException e){
            System.out.println("error");
        }

    }

    public void getDNAPieces(int start, int length){
        try {
            File file = new File(INPATH);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader reader = new BufferedReader(read);
            int lineNumber = 0;
            String line;
//            boolean index = false;
            while ((line = reader.readLine()) != null) {
                if (lineNumber<start){
                    lineNumber++;
                    continue;
                }
                if (lineNumber>(start+length-1)){
                    storeData(lineNumber);
//                    index = true;
                    break;
                }
                else {
                    if (line.contains("\t")){
                        splitByTable(line);
                    }else {
                        splitBySpace(line);
                    }
                }
                lineNumber++;
            }
//            if (!index) storeData(lineNumber);
            System.out.println(start);
            reader.close();
            read.close();
        } catch (IOException e){
            System.out.println("error");
        }

    }

    public void storeData(int lineNumber){
        Map<String, Integer> mapA = transformListToMap(DNAListA);
        Map<String, Integer> mapB = transformListToMap(DNAListB);
//        Map<String, Integer> mapAB = transformListToMap(DNAListAB);
//                        System.out.println(lineNumber + "==>" + map.size());
//                        result.put(lineNumber,map.size());
//        writeInExcel(lineNumber,map.size());
        if (mapA.size()<bundary && mapB.size()<bundary){
            DNA dna = new DNA(mapA.size(),mapB.size(),lineNumber);
            DNAList.add(dna);
        }
        reset();
    }

    public void reset(){
        DNAListA = new LinkedList<>();
        DNAListB = new LinkedList<>();
        DNAListAB = new LinkedList<>();
        first = true;
        excelLine ++;
        rsID = "";
        phyPosit = "";
    }

//    public Sheet readFile() throws IOException{
//        InputStream stream = new FileInputStream(path);
//        Workbook wb = new XSSFWorkbook(stream);
////        SXSSFWorkbook web = new SXSSFWorkbook(1000);
//        return wb.getSheetAt(0);
//    }

    public void splitBySpace(String line){
        String[] array = line.split(" ");
        int personId = 0;
        for (int i = 2; i < array.length; i=i+2) {
            String A = array[i];
            String B = array[i+1];
//            String contain = array[i] + array[i+1];
            addToList(A,B,personId);
            personId++;
        }
        first = false;
    }

    public void splitByTable(String line){
        String[] array = line.split("\t");
        int personId = 0;
        for (int i = 2; i < array.length; i++) {
            String[] cell = array[i].split(" ");
            String A = cell[0];
            String B = cell[1];
            addToList(A,B,personId);
            personId++;
        }
        first = false;
    }


    public void addToList(String A, String B, int personId) {

        if (first){
            DNAListA.add(A);
            DNAListB.add(B);
            DNAListAB.add(A+B);
        }else {
            A = DNAListA.get(personId) + A;
            DNAListA.remove(personId);
            DNAListA.add(personId,A);
            B = DNAListB.get(personId) + B;
            DNAListB.remove(personId);
            DNAListB.add(personId,B);
            String contain = DNAListAB.get(personId) + A + B;
            DNAListAB.remove(personId);
            DNAListAB.add(personId,contain);
        }

    }

    public Map<String, Integer> transformListToMap(LinkedList<String> list){
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : list) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str,1);
            }
        }
        return map;
    }


    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> arg0,
                               Map.Entry<Integer, Integer> arg1) {
                return arg0.getValue() - arg1.getValue();
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }

    private static void printMap(Map map){
        System.out.println("===================mapStart==================");
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("===================mapEnd==================");
    }

    public void writeInExcel(){
        for (int i = 0; i < DNAList.size(); i++) {
            DNA dna = DNAList.get(i);
            Row row = (Row) sheet.createRow(excelLine);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(dna.getLineNumber());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(Math.abs(dna.getANumber()-dna.getBNumber()));
            System.out.println("write " + i + " line");
        }
    }

    public static boolean write(String outPath) throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 循环写入行数据
        for (int i = 0; i < 5; i++) {
            Row row = (Row) sheet1.createRow(i);
            // 循环写入列数据
            for (int j = 0; j < 8; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("测试" + j);
            }
        }
        // 创建文件流
        OutputStream stream = new FileOutputStream(outPath);
        // 写入数据
        wb.write(stream);
        // 关闭文件流
        stream.close();
        return true;
    }

}
