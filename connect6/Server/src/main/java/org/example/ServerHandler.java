package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.PortUnreachableException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler
{
    private Game game;
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<Client>();
    private ProccesingThread proccesingThread = new ProccesingThread();

    public ServerHandler()
    {
        this.game = new Game();
        try
        {
            serverSocket = new ServerSocket(new  Config().PORT, 2, new Config().ip);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        proccesingThread.start();
    }
    public void Accept()
    {
        while (true)
        {
            try
            {
                Socket tmp = serverSocket.accept();
                clients.add(new Client(tmp));
                System.out.println("Клиент получен, Имя: "+tmp.getPort());
                if (clients.size() == 2) {
                    break;
                }
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public class ProccesingThread extends Thread
    {
        @Override
        public void run() {
            while (true)
            {
                Turn temp = game.turnQueue.poll();
                if (temp == null)
                {
                    try {
                        sleep(1000);
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("обрабатываю ход");
                if (game.MakeTurn(temp.m_x, temp.m_y, temp.m_player))
                {
                    game.ChangeTurn();
                    for (int i = 0; i < clients.size(); i++) {
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(clients.get(i).socket.getOutputStream());
                            oos.writeObject(new GameMassage(game.m_board, game.m_currentPlayer, ""));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
        }
    }
}
