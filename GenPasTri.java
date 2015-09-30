/*
 * GenPasTri is a main program for a generalized Pascal's triangle.
 * It reads in the amount of rows (N) and triangle parameters (A, B, and S)
 * and calls threads to compute and print out the triangle.
 * 
 * @author 		Pavel Rozvora (pxr8306)
 * @version		2015-09-24
 */
public class GenPasTri {
    public static void main (String[] args) {
        int n, a, b, s;
        // checks if arguments were supplied correctly
        if (args.length != 4) {
            System.err.println("Usage: java GenPasTri <N> <A> <B> <S>");
            System.err.println("Please enter 4 (four) integer parameters");
            System.exit(0);
        } else {
            for (int i = 0; i < 4; i++) {
                try  {
                    // negative integer entered
                    if (Integer.parseInt(args[i]) < 0) {
                        System.err.println("Usage: All parameters must be "
                                + "integers greater than or equal to zero");
                        System.exit(0);
                    }
                }	
                // non integer entered
                catch (NumberFormatException e) {	
                    System.err.println("Usage: All parameters must be INTEGERS"
                            + " greater than or equal to zero");
                    System.exit(0);
                }
            }
        }
        n = Integer.parseInt(args[0]);
        a = Integer.parseInt(args[1]);
        b = Integer.parseInt(args[2]);
        s = Integer.parseInt(args[3]);

        Monitor monitor = new Monitor(n);
        PrintThread print = new PrintThread(monitor);
        print.start();
        // start compute threads bottom up
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j >= 0 ; j--) {
                new ComputeThread(i, j, a, b, s, monitor).start();
            }
        }
    }
}