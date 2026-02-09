import java.util.ArrayList;
import java.util.List;

public class Postava {
    private String jmeno;
    private String popis;
    private ArrayList<String> vety;

    public Postava() {
    }

    Postava(String jmeno, String popis, List<String> vety) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.vety = (ArrayList<String>) vety;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public List<String> getVety() {
        return vety;
    }

    public void setVety(List<String> vety) {
        this.vety = (ArrayList<String>) vety;
    }

    @Override
    public String toString() {
        return "Postava{" +
                "jmeno='" + jmeno + '\'' +
                ", popis='" + popis + '\'' +
                ", vety=" + vety +
                '}';
    }
}
