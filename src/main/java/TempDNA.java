import java.util.LinkedList;

/**
 * Created by zhihu on 16/2/4.
 */
public class TempDNA {

    private String rsID;

    private String phy;

    private LinkedList<String> tmpA = new LinkedList<>();
    private LinkedList<String> tmpB = new LinkedList<>();

    public String getRsID() {
        return rsID;
    }

    public void setRsID(String rsID) {
        this.rsID = rsID;
    }

    public String getPhy() {
        return phy;
    }

    public void setPhy(String phy) {
        this.phy = phy;
    }

    public LinkedList<String> getTmpA() {
        return tmpA;
    }

    public void setTmpA(LinkedList<String> tmpA) {
        this.tmpA = tmpA;
    }

    public LinkedList<String> getTmpB() {
        return tmpB;
    }

    public void setTmpB(LinkedList<String> tmpB) {
        this.tmpB = tmpB;
    }
}
