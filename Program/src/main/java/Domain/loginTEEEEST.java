package Domain;

public class loginTEEEEST {
    public static void main(String[] args) {
        new User("H7523098SHJLJJLK","AndersW", "987", "Anders", "String email");

        Login.login("Kas", "1234");

        if (Login.isloggedIn()) {
            User test = Login.getUser();
            System.out.println(test.getName());
        }

    }
}
