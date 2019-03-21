package view;

import layout.MainWindowLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetGame {

    public void resetar(JButton jButton, JFrame jFrame) {
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new MainWindowLayout();
            }
        });
    }
}
