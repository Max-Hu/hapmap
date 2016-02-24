/**
 * Created by zhihu on 16/2/4.
 */
public class DNA {

    private String rsID;

    private String phyPosit;

    private int ANumber;

    private int BNumber;

    private int TotalNumber;

    private int lineNumber;

    @Override
    public String toString() {
        return "DNA{" +
                "rsID='" + rsID + '\'' +
                ", phyPosit='" + phyPosit + '\'' +
                ", ANumber=" + ANumber +
                ", BNumber=" + BNumber +
                ", TotailNumber=" + TotalNumber +
                ", lineNumber=" + lineNumber +
                '}';
    }

    public DNA( int ANumber, int BNumber, int lineNumber, String rsID, String phyPosit) {
        this.ANumber = ANumber;
        this.BNumber = BNumber;
        this.lineNumber = lineNumber;
        this.rsID = rsID;
        this.phyPosit = phyPosit;
    }

    public DNA( int ANumber, int BNumber, int lineNumber) {
        this.ANumber = ANumber;
        this.BNumber = BNumber;
        this.lineNumber = lineNumber;
    }

    public String getRsID() {
        return rsID;
    }

    public void setRsID(String rsID) {
        this.rsID = rsID;
    }

    public String getPhyPosit() {
        return phyPosit;
    }

    public void setPhyPosit(String phyPosit) {
        this.phyPosit = phyPosit;
    }

    public int getANumber() {
        return ANumber;
    }

    public void setANumber(int ANumber) {
        this.ANumber = ANumber;
    }

    public int getBNumber() {
        return BNumber;
    }

    public void setBNumber(int BNumber) {
        this.BNumber = BNumber;
    }

    public int getTotalNumber() {
        return TotalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        TotalNumber = totalNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
