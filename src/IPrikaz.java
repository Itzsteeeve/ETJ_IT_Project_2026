public interface IPrikaz {
    String provedPrikaz(String... parametry);
    String getNazev();
    default String provedPrikaz() { return provedPrikaz(new String[0]); }
}