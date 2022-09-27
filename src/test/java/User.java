public class User {

    String username;
    String password;
    String comment;
    boolean checkbox1;
    boolean checkbox2;
    boolean checkbox3;
    boolean radiobox1;
    boolean radiobox2;
    boolean radiobox3;
    int dropdown;

    public User(String username, String password, String comment, boolean checkbox1, boolean checkbox2, boolean checkbox3, boolean radiobox1, boolean radiobox2, boolean radiobox3, int dropdown) {
        this.username = username;
        this.password = password;
        this.comment = comment;
        this.checkbox1 = checkbox1;
        this.checkbox2 = checkbox2;
        this.checkbox3 = checkbox3;
        this.radiobox1 = radiobox1;
        this.radiobox2 = radiobox2;
        this.radiobox3 = radiobox3;
        this.dropdown = dropdown;
    }
}
