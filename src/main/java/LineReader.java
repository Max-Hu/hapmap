//import java.util.LinkedList;
//
///**
// * Created by zhihu on 16/2/4.
// */
//public class LineReader {
//
//    LinkedList<String> A = new LinkedList<>();
//    LinkedList<String> B = new LinkedList<>();
//    LinkedList<String> AB = new LinkedList<>();
//
//    public DNA initDNA(String line){
//        String[] array;
//        if (line.contains("\t")){
//            array = line.split("\t");
//            for (int i = 2; i < array.length; i++) {
//                String[] cell = array[i].split(" ");
//                String A = cell[0];
//                String B = cell[1];
//                addToList(A,B,personId);
//                personId++;
//            }
//        }else {
//            array = line.split(" ");
//            for (int i = 2; i < array.length; i=i+2) {
//                String A = array[i];
//                String B = array[i+1];
////            String contain = array[i] + array[i+1];
//                addToList(A,B,personId);
//                personId++;
//            }
////        }
//        peopleList.add(0,array[0]);
//        peopleList.add(1,array[1]);
//        return dna;
//    }
//
//    public String[] splitByTable(String line){
//        String[] array = line.split("\t");
//        for (int i = 2; i < array.length; i++) {
//            String[] cell = array[i].split(" ");
//            String A = cell[0];
//            String B = cell[1];
//            addToList(A,B,personId);
//            personId++;
//        }
//    }
//
//
//    public void addToList(String A, String B){
//        this.A.add(A);
//        this.B.add(B);
//        this.AB.add(A+B);
//    }
//}
