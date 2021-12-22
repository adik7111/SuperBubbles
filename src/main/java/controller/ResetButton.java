package controller;

import view.ApplicationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Reset button action listener is utilised by the reset button
 * and resets the whole application.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class ResetButton implements ActionListener {

    private JFrame frame;

    /**
     * Once the button is clicked, the current frame is disposed
     * of, and a completely new application is initialised and ran.
     *
     * @param frame
     */
    public ResetButton(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new ApplicationView().start();
    }
}
