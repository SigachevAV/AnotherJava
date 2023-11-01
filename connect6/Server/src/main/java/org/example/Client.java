package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;


public class Client
{
    private Queue<Turn> m_turnQueue;
    private Socket m_socket;
    private ObjectInputStream m_objectInputStream;
    public ObjectOutputStream m_objectOutputStream;
    private ListnerThread m_listnerThread = new ListnerThread();
    public Client(Socket _socket, Queue<Turn> _turnQueue)
    {
        this.m_turnQueue = _turnQueue;
        this.m_socket = _socket;
        try {
            this.m_objectOutputStream = new ObjectOutputStream(_socket.getOutputStream());
            this.m_objectInputStream = new ObjectInputStream(_socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.m_listnerThread.start();
    }
    private class ListnerThread extends Thread
    {
        @Override
        public void run()
        {
            while (true){
                try {
                    Turn temp = (Turn) m_objectInputStream.readObject();
                    m_turnQueue.add(temp);
                    System.out.println("Ход получен");
                } catch (IOException e) {
                    System.out.println("Клиент отвалился ");
                    break;
                } catch (ClassCastException | ClassNotFoundException e) {
                    continue;
                }
            }
        }
    }
}