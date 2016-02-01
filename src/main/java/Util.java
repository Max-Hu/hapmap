import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zhihu on 16/1/31.
 */
public class Util {

    private int rowstart;
    private int rowEnd;
    private Sheet sheet;

    private static int unitNumber = 100;

    public Util(Sheet sheet) {
        rowstart = sheet.getFirstRowNum()+1;
        rowEnd = sheet.getLastRowNum();
        this.sheet = sheet;
    }

//    public void formatToList(){
//        int index = rowstart;
//        boolean first = true;
//        while (index < rowEnd){
//            LinkedList<String> DNAList = new LinkedList<>();
//            for (int i = index ; i < (index + unitNumber); i++) {
//                Row row = sheet.getRow(i);
//                if(null == row) continue;
//                int cellStart = row.getFirstCellNum();
//                int cellEnd = row.getLastCellNum();
//                int personID = 0;
//                for (int j = cellStart + 2; j < cellEnd; j = j+2) {
//                    String contain = row.getCell(j).toString() + row.getCell(j+1);
//                    if (first){
//                        DNAList.add(contain);
//                    }else {
//                        contain = DNAList.get(personID) + contain;
//                        DNAList.remove(personID);
//                        DNAList.add(personID,contain);
//                    }
//                    personID ++;
//                }
//                first = false;
//            }
//            System.out.println(index);
//            Map<String, Integer> map = transformListToMap(DNAList);
//            System.out.println(map.size());
//            index = index + unitNumber;
//        }
//    }

//    public Map<String, Integer> transformListToMap(LinkedList<String> list){
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        for (String str : list) {
//            if (map.containsKey(str)) {
//                map.put(str, map.get(str) + 1);
//            } else {
//                map.put(str,1);
//            }
//        }
//        return map;
//    }
}
