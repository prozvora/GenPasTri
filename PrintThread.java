/*
 * PrintThread is a thread which gets the triangle values from the monitor
 * after they have been put and prints them out to the standard output.
 * 
 * @author 		Pavel Rozvora (pxr8306)
 * @version		2015-09-24
 */
public class PrintThread extends Thread {
    private int r;
    private Monitor monitor;
    /*
     * Constructor.
     * @param	monitor		The monitor object
     */
    public PrintThread(Monitor monitor) {
        r = monitor.rows();
        this.monitor = monitor;
    }
    /*
     * Gets the values in the triangle from a particular row then prints the
     * row.
     */
    public void run() {
        String line;
        for (int i = 0; i < r; i++) {
            line = "";
            for ( int j = 0; j <= i; j++) {
                if (j != i) {
                    line = line.concat(Integer.toString(monitor.getValue(i,j)));
                    line = line.concat(" ");
                } else {
                    line = line.concat(Integer.toString(monitor.getValue(i,j)));
                }
            }
            System.out.println(line);
        }
    }
}