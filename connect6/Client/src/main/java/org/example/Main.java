package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)
    {
        Config  config = new Config();
        try {
            Socket socket = new Socket(config.ip, config.PORT);
            int sing;
            System.out.println("Введите ваш знак");
            sing = Integer.parseInt(new Scanner(System.in).nextLine());
            SocketHandler socketHandler = new SocketHandler(socket, sing);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}