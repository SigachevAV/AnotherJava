package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {
    public Socket socket;
    public Game game;
    private ListnerThread lt = new ListnerThread();
    public Client(Socket _socket)
    {
        this.socket = _socket;
        this.lt.start();
    }
    private class ListnerThread extends Thread
    {
        @Override
        public void run()
        {
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (true)
            {
                try
                {
                    System.out.println("ход получен");
                    Turn temp = (Turn) objectInputStream.readObject();
                    game.turnQueue.add(temp);
                    System.out.println("ход получен");
                }
                catch (IOException e)
                {
                    System.out.println("Отвал клиента, Имя: "+socket.getPort());
                    return;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
