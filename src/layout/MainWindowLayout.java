package layout;

import model.Matrix;
import view.FieldCreation;
import view.ResetGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindowLayout {

    public MainWindowLayout() {
        JFrame mainWindow = new JFrame("Minesweeper");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        //Layouts and definitions
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel flowPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(8, 8));
        JButton resetGameButton = new JButton("");

        Dimension itemFieldDimension = new Dimension(30, 30);
        Dimension resetButtonDimension = new Dimension(50, 50);

        ArrayList<JButton> buttonArray = new ArrayList<>();
        Matrix m = new Matrix();
        m.printMatrix();
        FieldCreation fieldCreation = new FieldCreation();

        //Header
        //Reset Button
        ResetGame resetGame = new ResetGame();
        resetGame.reset(resetGameButton, mainWindow);

        resetGameButton.setIcon(new ImageIcon("src/resources/reset.png"));
        resetGameButton.setBorder(BorderFactory.createEmptyBorder());
        resetGameButton.setContentAreaFilled(false);
        resetGameButton.setPreferredSize(resetButtonDimension);

        flowPanel.add(resetGameButton);

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {

                JButton itemButtonField = new JButton();
                itemButtonField.setPreferredSize(itemFieldDimension);

                itemButtonField.setIcon(new ImageIcon("src/resources/nothing"));
                //itemButtonField.setFocusPainted(true);
                itemButtonField.setBackground(Color.lightGray);
                itemButtonField.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                gridPanel.add(itemButtonField);
                buttonArray.add(itemButtonField);

                fieldCreation.mouseEvent(itemButtonField);
                if (m.isHint(row, column))
                    fieldCreation.bombAround(itemButtonField, m, buttonArray, m.getValueAt(row, column));
                if (m.isNothing(row, column))
                    fieldCreation.noBomb(itemButtonField, m, buttonArray, row, column);
                if (m.isBomb(row, column))
                    fieldCreation.bomb(itemButtonField, buttonArray, m);
            }
        }

        //Drawing the elements
        mainWindow.add(mainPanel);
        mainPanel.add(flowPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        flowPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Making visible and sized
        mainWindow.pack(); //sizes the frame
        mainWindow.setLocationRelativeTo(null); //put frame on center
        mainWindow.setVisible(true); //makes frame visible

    }
}
