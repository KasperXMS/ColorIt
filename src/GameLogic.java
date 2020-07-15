import java.awt.*;
import java.util.Random;

/**
 * <b>Title: </b> GameLogic
 * @author KasperXMS
 * @version 1.0
 * <b>Description: </b> Logical implementation of the game. By using two matrix with one indicates the color and the
 * other records whether the element is colored. Method of change and update is included in this class to operate data.
 * <br>
 * Once the game start, the 20x20 elements wil be randomly given colors and initialize the (0, 0) element as the start
 * point. for every click on buttons, every element that is in the colored tree will be changed, and adjacent elements
 * with the selected color will be included to the color tree in update.
 * <b>Copyright</b> Kasper Studio 1999-2020
 */
public class GameLogic {
    /* Current color indicator */

    private Color currentColor;

    /* Color code definition */

    final private int RED = 0;
    final private int GREEN = 1;
    final private int BLUE = 2;
    final private int YELLOW = 3;
    final private int PURPLE = 4;
    final private int ORANGE = 5;

    /* bound value */

    private int x_bound;
    private int y_bound;

    /* matrices */

    private int[][] colorMatrix;
    private Boolean[][] treeMatrix;

    /**
     * Constructor. Randomly color the map and set the element (0, 0) tree value as <b>true</b>.
     * @param x_bound maximum number of columns
     * @param y_bound maximum number of rows
     */
    public GameLogic(int x_bound, int y_bound){
        colorMatrix = new int[x_bound][y_bound];
        treeMatrix = new Boolean[x_bound][y_bound];
        this.x_bound = x_bound;
        this.y_bound = y_bound;
        Random r = new Random();
        for(int i = 0; i < x_bound; i++){
            for(int j = 0; j < y_bound; j++){
                colorMatrix[i][j] = r.nextInt(6);
                treeMatrix[i][j] = false;
            }
        }
        //showColorValue();
        treeMatrix[0][0] = true;
        currentColor = getColorType(colorMatrix[0][0]);
    }

    /**
     * Check elements with the selected color adjacent to tree elements. Check by multiple times until no elements are
     * updated.
     */
    public void update(){
        int addnum = 0;
        for(int i = 0; i < x_bound; i++){
            for(int j = 0; j < y_bound; j++){
                if(currentColor == getColorType(colorMatrix[i][j]) && !treeMatrix[i][j]){
                    if(i > 0){
                        if(getColorType(colorMatrix[i-1][j]) == currentColor && treeMatrix[i-1][j]){
                            treeMatrix[i][j] = true;
                            addnum++;
                        }
                    }
                    if(j > 0){
                        if(getColorType(colorMatrix[i][j-1]) == currentColor && treeMatrix[i][j-1]){
                            treeMatrix[i][j] = true;
                            addnum++;
                        }
                    }
                    if(i < x_bound - 1){
                        if(getColorType(colorMatrix[i+1][j]) == currentColor && treeMatrix[i+1][j]){
                            treeMatrix[i][j] = true;
                            addnum++;
                        }
                    }
                    if(j < y_bound - 1){
                        if(getColorType(colorMatrix[i][j+1]) == currentColor && treeMatrix[i][j+1]){
                            treeMatrix[i][j] = true;
                            addnum++;
                        }
                    }
                }
            }
        }
        if(addnum > 0) update();
    }

    /**
     * Change the color value of tree elements.
     * @param color new current color
     */
    public void change(Color color){
        for(int i = 0; i < x_bound; i++){
            for(int j = 0; j <y_bound; j++){
                if(treeMatrix[i][j]){
                    colorMatrix[i][j] = getColorID(color);
                    currentColor = color;
                }
            }
        }
    }

    public Boolean isAllColored(){
        for(int i = 0; i < x_bound; i++){
            for(int j = 0; j < y_bound; j++){
                if(!treeMatrix[i][j]) return false;
            }
        }
        return true;
    }

    /**
     * get corresponding color code by color type.
     * @param color Color type
     * @return color ID
     */
    public int getColorID(Color color){
        if(color == Color.RED) return this.RED;
        else if(color == Color.GREEN) return this.GREEN;
        else if(color == Color.BLUE) return this.BLUE;
        else if(color == Color.YELLOW) return this.YELLOW;
        else if(color == Color.MAGENTA) return this.PURPLE;
        else if(color == Color.ORANGE) return this.ORANGE;
        else return -1;
    }

    /**
     * get corresponding color type by color code
     * @param color color ID
     * @return Color type
     */
    public Color getColorType(int color){
        switch (color){
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case YELLOW:
                return Color.YELLOW;
            case PURPLE:
                return Color.MAGENTA;
            case ORANGE:
                return Color.ORANGE;
        }
        return null;
    }

    /**
     * For display color on JLabel.
     * @param i column index
     * @param j row index
     * @return Color type of the block (i, j)
     */
    public Color getColor(int i, int j){
        return getColorType(colorMatrix[i][j]);
    }

    public void showColorValue(){
        for(int i = 0; i < x_bound; i++){
            for(int j = 0; j < y_bound; j++){
                System.out.print(colorMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
