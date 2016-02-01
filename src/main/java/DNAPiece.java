import java.util.LinkedList;

/**
 * Created by zhihu on 16/2/1.
 */
public class DNAPiece {
    private String rsID;
    private String physPosit;
    private LinkedList<String> peopleList;

    @Override
    public String toString() {
        return "DNAPiece{" +
                "rsID='" + rsID + '\'' +
                ", physPosit='" + physPosit + '\'' +
                ", peopleList=" + peopleList +
                '}';
    }

    public String getRsID() {
        return rsID;
    }

    public void setRsID(String rsID) {
        this.rsID = rsID;
    }

    public String getPhysPosit() {
        return physPosit;
    }

    public void setPhysPosit(String physPosit) {
        this.physPosit = physPosit;
    }

    public LinkedList<String> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(LinkedList<String> peopleList) {
        this.peopleList = peopleList;
    }
}
