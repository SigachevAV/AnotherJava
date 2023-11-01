package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler
{
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<Client>();
    private List<Socket> sockets = new ArrayList<Socket>();
    private Game m_game = new Game();
    private TaskProcessor m_taskProcessor = new TaskProcessor();
    public ServerHandler()
    {
        Config config = new Config();
        try {
            this.serverSocket = new ServerSocket(config.PORT, 2, config.ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        m_taskProcessor.start();
    }
    public void Accept()
    {
        while (clients.size() < 2)
        {
            try {
                Socket client = this.serverSocket.accept();
                clients.add( new Client(client, m_game.m_turnQueue));
                System.out.println("Получен клиент " + client.getPort());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Бродкаст");
        Broadcast(new GameMassage(m_game.m_board, m_game.m_currentPlayer, "Игра началась"));
    }

    private void Broadcast(GameMassage _gameMassage)
    {
        for (int i = 0; i < clients.size(); i++)
        {
            try {
                clients.get(i).m_objectOutputStream.writeObject(_gameMassage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class TaskProcessor extends Thread
    {
        @Override
        public void run() {
            while (true) {
                Turn turn = m_game.m_turnQueue.poll();
                if (turn == null) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    continue;
                }
                System.out.println("Ход обрабатываем");
                GameMassage massage;
                if (m_game.MakeTurn(turn.m_x, turn.m_y, turn.m_player))
                {
                    m_game.ChangeTurn();
                    if (m_game.IsWinner(turn.m_player))
                    {
                        massage = new GameMassage(m_game.m_board, m_game.m_currentPlayer, ("Победил ") + turn.m_player.toString());
                    }
                    else
                    {
                        massage = new GameMassage(m_game.m_board, m_game.m_currentPlayer, "");
                    }

                }
                else
                {
                    massage = new GameMassage(m_game.m_board, m_game.m_currentPlayer, "Неверный ход");
                }
                Broadcast(massage);
            }
        }
    }
}
