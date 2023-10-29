package org.example;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {

   static class Handler
   {
       private ObjectInputStream m_objectInputStream;
       private Printthread printthread = new Printthread();
       private Socket socket;
       public  Handler() throws IOException {
           System.out.println("жду сокет");
           socket = new Socket(new Config().ip, new Config().PORT);
           System.out.println("я принт я жду");
           System.out.println("беру поток");
           InputStream in = socket.getInputStream();

           System.out.println("беру поток");
           m_objectInputStream = new ObjectInputStream(in);
           System.out.println("я запускаю тред");
           printthread.start();

       }
       public  static  class  Printthread extends Thread
       {

           @Override
           public void run() {
               System.out.println("я принт я жду");
               while (true)
               {
                   try {
                       sleep(1000);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       }
   }

    public static void main(String[] args)
    {
        Socket socket;
        try {

            Handler handler = new Handler();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}