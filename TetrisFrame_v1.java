package tetris;


/*
class TetrisFrame_v1 extends JFrame implements ActionListener{
    private JTextArea textArea;

        TetrisFrame_v1(Board board){
            super("Mitt Tetris");

            this.setLayout(new BorderLayout());
            createMenus();

            textArea = new JTextArea(board.getWidth(),board.getHeight());
            textArea.setText(BoardToTextConverter.convertToText(board));
            this.add(textArea,BorderLayout.CENTER);
            textArea.setFont(new Font("Courier New",Font.PLAIN,20));
            this.pack();
            this.setVisible(true);
    }


    private void createMenus(){
        final JMenuBar bar = new JMenuBar();

        final JMenu Exit = new JMenu("Exit");
        JMenuItem exit = new JMenuItem("Exit");
        Exit.add(exit);
        exit.addActionListener(this);
        bar.add(Exit);
        this.setJMenuBar(bar);

    }
    //Klicka på exit -> stäng med exit code 0
    public void actionPerformed(final ActionEvent e) {
        int answer = JOptionPane.showConfirmDialog
                (new JFrame(), "Vill du verkligen sluta?", "Är det svårt eller?", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    //Uppdaterar board
    public static void Update(Board board) {
        //textArea.setText(BoardToTextConverter.convertToText(board));
    }
} */