/**
 * @author KasperXMS
 * @version 1.0
 * <b>Copyright</b> Kasper Studio 1999-2020
 * Main program.
 */
public class Go {
    private static final int x_bound = 20;
    private static final int y_bound = 20;
    public static void main(String[] args){
        GUI g = new GUI(x_bound, y_bound);
        g.run();
    }
}
