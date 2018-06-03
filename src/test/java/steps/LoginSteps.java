package steps;

import pageObject.LoginPage;

public class LoginSteps {

    private static final LoginPage LOGIN_PAGE = new LoginPage();

    public static void signInWithFacebook() {
        LOGIN_PAGE.signInWithFacebook.execute();
    }

    public static void signInWithGoogle() {
        LOGIN_PAGE.signInWithGoogle.execute();
    }

    public static void signInWithEmail() {
        LOGIN_PAGE.signInWithEmail.execute();
    }

}
