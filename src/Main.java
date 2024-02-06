public class Main {
    public static void main(String[] args) {
        Jdbc jdbc = new Jdbc();
        jdbc.dropTable();
        jdbc.createTable();
        jdbc.insert();

    }
}