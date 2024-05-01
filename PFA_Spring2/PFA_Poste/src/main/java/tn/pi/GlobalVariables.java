package tn.pi;

public class GlobalVariables {

    private static Long user_id;
    public static Long getUser_id() {
        return user_id;
    }

    public static void setUser_id(Long id) {
        GlobalVariables.user_id = id;
    }
}
