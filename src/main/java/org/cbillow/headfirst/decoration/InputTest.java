package org.cbillow.headfirst.decoration;

import java.io.*;

/**
 * @author Created by Cbillow
 * @date 15/12/24
 * @time 19:43
 */
public class InputTest {

    public static void main(String[] args) throws IOException {
        int c;
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));

        while ((c = in.read()) > 0) {
            System.out.println((char) c);
        }

        in.close();
    }
}
