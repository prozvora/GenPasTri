/*
 * ComputeThread is a thread which computes the value of one element in the
 * triangle and puts the value into the triangle.
 * 
 * @author 		Pavel Rozvora (pxr8306)
 * @version		2015-09-24
 */
public class ComputeThread extends Thread {
    private int r, c, a, b, s;
    private Monitor monitor;
    private int put;
    /*
     * Constructor.
     * 
     * @param	row			the row in which the element resides
     * @param	col			the column in which the element resides
     * @param	a			left edge constant added to triangle operation
     * @param	b			right edge constant added to triangle operation
     * @param	s			first value of the triangle
     * @param	monitor		The monitor object
     * 
     */
    public ComputeThread(int row, int col, int a, int b, int s, Monitor monitor) {
        r = row;
        c = col;
        this.a = a;
        this.b = b;
        this.s = s;
        this.monitor = monitor;
    }
    /*
     * Computes the value of the triangle element based on the position of the
     * element in relation to other elements and the triangle parameters
     * N, A, B, and S.
     */
    public void run() {
        if (r == 0 && c == 0) {
            monitor.putValue(r, c, s);
        } else if (c == 0) {
            put = a + monitor.getValue(r-1, 0);
            monitor.putValue(r, c, put);
        } else if (r == c) {
            put = b + monitor.getValue(r-1, c-1);
            monitor.putValue(r, c, put);
        } else {
            put = monitor.getValue(r-1, c-1);
            put += monitor.getValue(r-1, c);
            monitor.putValue(r, c, put);
        }
    }
}
