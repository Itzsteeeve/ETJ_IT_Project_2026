import java.util.List;

public class Postava {
    private String jmeno;
    private String popis;
    private List<String> vety;

    Postava(String jmeno, String popis, List<String> vety) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.vety = vety;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }

    public List<String> getVety() {
        return vety;
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
