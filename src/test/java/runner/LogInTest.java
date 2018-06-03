package runner;

import org.testng.annotations.*;
import steps.BasePageSteps;
import steps.LoginSteps;
import steps.ModalSteps;
import util.WebDriverConfig;

public class LogInTest {

    @BeforeClass
    public void setUp() {
        BasePageSteps.openBasePage();
    }

    @BeforeMethod
    public void beforeMethod() {
        ModalSteps.closeModalIfOpen();
    }

    @Test
    public void signInWithFacebook() {
        BasePageSteps.clickSignIn();
        LoginSteps.signInWithFacebook();
    }

    @Test
    public void signInWithGoogle() {
        BasePageSteps.clickSignIn();
        LoginSteps.signInWithGoogle();
    }

    @Test
    public void signInWithEmail() {
        BasePageSteps.clickSignIn();
        LoginSteps.signInWithEmail();
    }

    @AfterClass
    public void tearDown() {
        WebDriverConfig.quitDriver();
    }

}
