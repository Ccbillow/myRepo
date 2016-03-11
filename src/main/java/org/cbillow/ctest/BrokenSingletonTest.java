package org.cbillow.ctest;

import java.io.*;

/**
 * @author Created by Cbillow
 * @date 16/3/5
 * @time 16:50
 */
public class BrokenSingletonTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(Singleton.getSingleton());

        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Singleton newInstance = (Singleton) ois.readObject();
        System.out.println(newInstance == Singleton.getSingleton());

    }
}
