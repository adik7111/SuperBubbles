package main;

import view.ApplicationView;

/**
 * The Main class is our run class. It initialises a new
 * applications view and starts it, so that the application
 * may run.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class Main {

    public static void main(String[] args) {
        ApplicationView view = new ApplicationView();
        view.start();
    }
}
