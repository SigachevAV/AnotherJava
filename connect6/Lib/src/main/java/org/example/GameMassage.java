package org.example;

import java.io.Serializable;

public class GameMassage implements Serializable
{
    private static final long serialVersionUID = 1234567L;
    public Board m_board;
    public Integer m_player;
    public String m_massage;
    public GameMassage(Board _board, Integer _player, String _massage)
    {

        this.m_board =new Board(_board);

        this.m_player = _player;
        this.m_massage = _massage;
    }

    @Override
    public String toString()
    {
        String result = "";
        for (int i = 0; i < 19; i++)
        {
            for (int j = 0; j < 19; j++)
            {
                result += " " + m_board.GetCell(j, i) + " ";
            }
            result+="\n";
        }
        result+="\n";
        result+="Turn " + m_player;
        result+="\n";
        result+=m_massage;
        return result;
    }
}
