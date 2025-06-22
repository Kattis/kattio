/** Simple yet moderately fast I/O routines.
 *
 * Example usage:
 *
 * Kattio io = new Kattio(System.in, System.out);
 *
 * while (io.hasMoreTokens()) {
 *    int n = io.getInt();
 *    double d = io.getDouble();
 *    double ans = d*n;
 *
 *    io.println("Answer: " + ans);
 * }
 *
 * io.close();
 *
 *
 * Some notes:
 *
 * - When done, you should always do io.close() or io.flush() on the
 *   Kattio-instance, otherwise, you may lose output.
 *
 * - The getInt(), getDouble(), and getLong() methods will throw an
 *   exception if there is no more data in the input, so it is generally
 *   a good idea to use hasMoreTokens() to check for end-of-file.
 *
 * @author: Kattis
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        this.r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        this.r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return this.peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(this.nextToken());
    }

    public double getDouble() { 
        return Double.parseDouble(this.nextToken());
    }

    public long getLong() {
        return Long.parseLong(this.nextToken());
    }

    public String getWord() {
        return this.nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (this.token == null) {
            try {
                while (this.st == null || !this.st.hasMoreTokens()) {
                    this.line = this.r.readLine();
                    if (this.line == null) {
                        return null;
                    }
                    this.st = new StringTokenizer(this.line);
                }
                this.token = this.st.nextToken();
            } catch (IOException e) {}
        }

        return this.token;
    }

    private String nextToken() {
        String ans = this.peekToken();
        this.token = null;
        return ans;
    }
}
