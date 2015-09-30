/*
 * A monitor object that manages the triangle and threads accessing triangle
 * data. Allows threads to access initialized triangle elements and allows them
 * to add elements to uninitialized locations in the triangle.
 * 
 * @author	Pavel Rozvora (pxr8306)
 * @version	2015-09-24
 */
public class Monitor {
    private int[][] triangle;
    private int r;
    /*
     * Constructor.
     * 
     * 
     */
    public Monitor(int r) {
        this.r = r;
        this.triangle = new int[r][r];
        // triangle elements are initialized to -1, an impossible value
        for (int i = 0; i < r; i++) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = -1;
            }
        }
    }
    /*
     * Returns the number of rows in the triangle
     * @return	r	rows in triangle
     */
    public int rows() {
        return r;
    }
    /*
     * Puts a value into the triangle in the specified row and column. Only
     * allows one thread to put a value at a time and produces an error if a
     * location in the triangle is written to twice. Notifies waiting threads
     * after putting a value.
     * 
     * @param	r		row in triangle
     * @param	c		column in triangle
     * @param	value	value to put into specified row and column
     */
    public synchronized void putValue(int r, int c, int value) {
        if (triangle[r][c] != -1) {
            System.err.println("Error: a value has already been placed at row "
                    + r + ", column " + c);
            System.exit(0);
        }
        triangle[r][c] = value;
        notifyAll();
    }
    /*
     * Waits until a triangle element is put into the triangle. If the value
     * hasn't been put, it blocks the calling thread until the value is put
     * and then unblocks and returns the value to the calling thread.
     * 
     * @param		r						row in triangle
     * @param		c						column in triangle
     * @exception	InterruptedException	Never invoked because interrupt()
     *                                      is never called
     * 
     * @return		triangle[r][c]			returns the element at (r,c)
     */
    public synchronized int getValue(int r, int c) {
        while (triangle[r][c] == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                // shouldn't happen
            }
            notifyAll();
        }
        return triangle[r][c];
    }
}