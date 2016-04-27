package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David on 2016-02-07.
 */

class TetrisFrame extends JFrame implements ActionListener{
    private TetrisComponent tetrisComponent;

        TetrisFrame(Board board){
            super("Mitt Tetris");

            this.setLayout(new BorderLayout());
            createMenus();

            tetrisComponent = new TetrisComponent(board);

            this.add(tetrisComponent,BorderLayout.CENTER);
            tetrisComponent.setFont(new Font("Courier New",Font.PLAIN,20)); //Godtycklig storlek
            this.pack();
            this.setVisible(true);
    }


    private void createMenus(){
        final JMenuBar myBar = new JMenuBar();

        final JMenu exit = new JMenu("Exit");
        JMenuItem underExit = new JMenuItem("Exit");
        exit.add(underExit);
        underExit.addActionListener(this);
        myBar.add(exit);
        this.setJMenuBar(myBar);

    }
    //Klicka på exit -> stäng med exit code 0
    public void actionPerformed(final ActionEvent e) {
        int answer = JOptionPane.showConfirmDialog
                (new JFrame(), "Vill du verkligen sluta?", "Sluta", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
