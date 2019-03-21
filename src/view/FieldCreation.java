package view;

import model.Matrix;
import model.Sound1;
import model.Sound2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FieldCreation {

    public void mouseEvent(JButton jButton) {
        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                //flag in and out
                if (mouseEvent.getButton() == 3) {
                    if (jButton.getIcon().toString().equals("src/resources/flag.png")) {
                        jButton.setIcon(new ImageIcon("src/resources/nothing.png"));
                    } else if (jButton.isContentAreaFilled()) {
                        jButton.setIcon(new ImageIcon("src/resources/flag.png"));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    //when bomb is there
    public void bomb(JButton jButton, ArrayList<JButton> buttonArray, Matrix matrix) {
        jButton.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (matrix.isBomb(i, j))
                        buttonArray.get((i * 8) + j).setIcon(new ImageIcon("src/resources/bomber.png"));
                    else
                        buttonArray.get((i * 8) + j).setEnabled(false);

                    Sound1 defeat = new Sound1();

        });

    }

    //when bomb is around
    public void bombAround(JButton jButton,Matrix m, ArrayList<JButton> buttonArray, int c) {
        jButton.addActionListener((ActionEvent e) -> {
            jButton.setLabel(String.valueOf(c));
            jButton.setContentAreaFilled(false);
            checkVictory(buttonArray,m);
        });
    }

    //
    public void noBomb(JButton jButton, Matrix m, ArrayList<JButton> buttonArray, int x, int y) {
        jButton.addActionListener((ActionEvent e) -> {
            jButton.setContentAreaFilled(false);
            m.searchForZeroes(x,y);
            m.printMatrix();
            for (int i = 0; i<8 ;i++)
                for (int j = 0; j< 8; j++) {
                    if (m.getValueAt(i, j) == -2) {
                        buttonArray.get(i * 8 + j).setContentAreaFilled(false);
                    } else if (m.getValueAt(i, j) >= 9 && m.getValueAt(i, j) <= 16) {
                        m.setValueBack(i, j);
                        buttonArray.get(i * 8 + j).setLabel(String.valueOf(m.getValueAt(i, j)));
                        buttonArray.get(i * 8 + j).setContentAreaFilled(false);
                    }
                }
            checkVictory(buttonArray,m);

        });
    }


    //check victory
    void checkVictory (ArrayList<JButton> buttonArray, Matrix m){
        int cont = 0;
        for (JButton a : buttonArray){
            if (a.isContentAreaFilled())
                cont++;
        }
        if (cont == m.getmMines()) {
            System.out.print("YOU WIN\n\n");
            Sound2 victory = new Sound2();
            return;
        }
        return;
    }
}