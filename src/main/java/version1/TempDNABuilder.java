package version1;

import java.util.LinkedList;

/**
 * Created by zhihu on 16/2/4.
 */
public class TempDNABuilder {

    private LinkedList<String> tmpA = new LinkedList<>();
    private LinkedList<String> tmpB = new LinkedList<>();
    private TempDNA tempDNA = new TempDNA();


    public TempDNABuilder(String rs,String phy) {
        tempDNA.setRsID(rs);
        tempDNA.setPhy(phy);
    }

    public void setValue(String[] array){
        tmpA.add(array[0]);
        tmpB.add(array[1]);
        tempDNA.setTmpA(tmpA);
        tempDNA.setTmpB(tmpB);
    }
}
