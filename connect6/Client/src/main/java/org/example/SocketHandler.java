package org.example;

import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketHandler
{
    private Socket m_socket;
    private ObjectInputStream m_objectInputStream;
    private ObjectOutputStream m_objectOutputStream;
    private ReaderThred m_readerThred = new ReaderThred();
    private int m_playerSing;
    public SocketHandler(Socket _socket, int _playerSing)
    {
        this.m_socket = _socket;
        this.m_playerSing = _playerSing;
        try {
            this.m_objectInputStream = new ObjectInputStream(this.m_socket.getInputStream());
            this.m_objectOutputStream = new ObjectOutputStream(this.m_socket.getOutputStream());
            this.m_objectOutputStream.writeObject("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        m_readerThred.start();
    }

    private class ReaderThred extends Thread
    {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                try {
                    GameMassage gameMassage =  (GameMassage) m_objectInputStream.readObject();
                    System.out.println(gameMassage.toString());
                    if (gameMassage.m_player == m_playerSing)
                    {
                        System.out.println("Введите X");
                        int x = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите Y");
                        int y = Integer.parseInt(scanner.nextLine());
                        m_objectOutputStream.writeObject(new Turn(x, y, m_playerSing));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
