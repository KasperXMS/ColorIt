import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * <b>Title</b>: GUI.java
 * @author KasperXMS
 * @version 1.0
 * <b>Description</b>: This class graphically display the game.
 * <b>Copyright</b> Kasper Studio 1999-2020
 */
public class GUI extends JFrame implements ActionListener {
    /* Fill 23x20 grid with 30x30 siz labels and buttons*/

    private ArrayList<JLabel> labels;
    private ArrayList<JButton> colorBlocks;

    /* Color Button for control */

    private JButton Red;
    private JButton Green;
    private JButton Blue;
    private JButton Yellow;
    private JButton Purple;
    private JButton Orange;

    /* Replay button */

    private JButton Replay;

    /* values */

    private int x_bound;
    private int y_bound;
    private int count = 40;

    /* Game logic class */

    private GameLogic gl;

    /**
     * Constrctor class. Build the graphic UI of the program.
     * @param x_bound maximum columns
     * @param y_bound minimum columns
     */
    public GUI(int x_bound, int y_bound) {
        /* Frame configuration */
        super();
        this.x_bound = x_bound;
        this.y_bound = y_bound;
        GridLayout grid = new GridLayout();
        grid.setColumns(20);
        grid.setRows(23);
        this.setLayout(grid);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(612, 692);

        /* Replay button config */
        Replay = new JButton();
        Replay.setSize(30, 30);
        Replay.setVisible(false);
        Replay.addActionListener(this);

        /* Build colored blocks and label as blank space */
        labels = new ArrayList<JLabel>(53);
        colorBlocks = new ArrayList<JButton>(400);
        for (int i = 0; i < 400; i++) {
            JButton J = new JButton();
            J.setSize(30, 30);
            colorBlocks.add(J);
            this.getContentPane().add(J);
            J.setVisible(true);
        }
        for (int i = 0; i < 53; i++) {
            JLabel J = new JLabel();
            J.setSize(30, 30);
            labels.add(J);
            if (i < 20) this.getContentPane().add(J);
            J.setVisible(true);
        }
        for (int i = 0; i < 7; i++) {
            this.getContentPane().add(labels.get(20 + i * 2));
            this.getContentPane().add(labels.get(21 + i * 2));
            switch (i) {
                case 0:
                    Red = new JButton();
                    Red.setSize(30, 30);
                    Red.setBackground(Color.RED);
                    Red.addActionListener(this);
                    this.getContentPane().add(Red);
                    Red.setVisible(true);
                    break;
                case 1:
                    Green = new JButton();
                    Green.setSize(30, 30);
                    Green.setBackground(Color.GREEN);
                    Green.addActionListener(this);
                    this.getContentPane().add(Green);
                    Green.setVisible(true);
                    break;
                case 2:
                    Blue = new JButton();
                    Blue.setSize(30, 30);
                    Blue.setBackground(Color.BLUE);
                    Blue.addActionListener(this);
                    this.getContentPane().add(Blue);
                    Blue.setVisible(true);
                    break;
                case 3:
                    Yellow = new JButton();
                    Yellow.setSize(30, 30);
                    Yellow.setBackground(Color.YELLOW);
                    Yellow.addActionListener(this);
                    this.getContentPane().add(Yellow);
                    Yellow.setVisible(true);
                    break;
                case 4:
                    Purple = new JButton();
                    Purple.setSize(30, 30);
                    Purple.setBackground(Color.MAGENTA);
                    Purple.addActionListener(this);
                    this.getContentPane().add(Purple);
                    Purple.setVisible(true);
                    break;
                case 5:
                    Orange = new JButton();
                    Orange.setSize(30, 30);
                    Orange.setBackground(Color.ORANGE);
                    Orange.addActionListener(this);
                    this.getContentPane().add(Orange);
                    Orange.setVisible(true);
                    break;
            }
        }
        for (int i = 34; i < 43; i++) {
            this.getContentPane().add(labels.get(i));
        }
        this.getContentPane().add(Replay);
        for(int i = 43; i < 53; i++){
            this.getContentPane().add(labels.get(i));
        }
        this.setVisible(true);
    }

    /**
     * Run a new game.<br>
     * Initialize the trials count and randomly color the blocks.
     */
    public void run() {
        gl = new GameLogic(x_bound, y_bound);
        gl.update();
        count = 40;
        labels.get(46).setText("40");
        Random r = new Random();
        showColor();
        this.validate();
        this.setTitle("Color It!");
    }

    /**
     * Set background colors of labels to display the color block.
     */
    public void showColor() {
        for (int i = 0; i < x_bound; i++) {
            for (int j = 0; j < y_bound; j++) {
                colorBlocks.get(i * 20 + j).setBackground(gl.getColor(i, j));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Once clicked replay, the UI will clear and start a new game.
         */
        if(e.getSource() == Replay){
            Replay.setVisible(false);
            labels.get(43).setText("");
            labels.get(44).setText("");
            labels.get(8).setText("");
            labels.get(9).setText("");
            labels.get(10).setText("");
            labels.get(11).setText("");
            Red.setEnabled(true);
            Green.setEnabled(true);
            Blue.setEnabled(true);
            Yellow.setEnabled(true);
            Purple.setEnabled(true);
            Orange.setEnabled(true);
            run();
        }
        else{
            if (count > 0) {

                if (e.getSource() == Red) gl.change(Color.RED);
                else if (e.getSource() == Green) gl.change(Color.GREEN);
                else if (e.getSource() == Blue) gl.change(Color.BLUE);
                else if (e.getSource() == Yellow) gl.change(Color.YELLOW);
                else if (e.getSource() == Purple) gl.change(Color.MAGENTA);
                else if (e.getSource() == Orange) gl.change(Color.ORANGE);
                count--;
                labels.get(46).setText(Integer.toString(count));
                gl.update();
                showColor();
                //gl.showColorValue();
                this.validate();
                if (gl.isAllColored()) {
                    labels.get(8).setText("W");
                    labels.get(9).setText("I");
                    labels.get(10).setText("N");
                    Replay.setVisible(true);
                    labels.get(43).setText("Repl");
                    labels.get(44).setText("ay");
                }
                if(count == 0){
                    labels.get(8).setText("L");
                    labels.get(9).setText("O");
                    labels.get(10).setText("S");
                    labels.get(11).setText("E");
                    Red.setEnabled(false);
                    Green.setEnabled(false);
                    Blue.setEnabled(false);
                    Yellow.setEnabled(false);
                    Purple.setEnabled(false);
                    Orange.setEnabled(false);
                    Replay.setVisible(true);
                    labels.get(43).setText("Repl");
                    labels.get(44).setText("ay");
                }
            }
        }

    }
}
