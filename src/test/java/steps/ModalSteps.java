package steps;

import pageObject.ModalWindowPage;

public class ModalSteps {

    private static final ModalWindowPage MODAL_WINDOW_PAGE = new ModalWindowPage();

    public static void closeModalIfOpen() {
        MODAL_WINDOW_PAGE.closeModalIfOpen.execute();
    }

}
