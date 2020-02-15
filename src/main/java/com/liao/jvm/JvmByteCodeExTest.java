package com.liao.jvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

public class JvmByteCodeExTest {

    public void test() {

        try {

            InputStream in = new FileInputStream("test.txt");

            ServerSocket serverSocket = new ServerSocket(9999);

            serverSocket.accept();
        } catch (FileNotFoundException ex) {

//            System.out.println("FileNotFoundException");

        } catch (IOException ex) {
//            System.out.println("IOException");

        } catch (Exception ex) {
//            System.out.println("Exception");

        } finally {

            System.out.println("finally");
        }


    }
}
