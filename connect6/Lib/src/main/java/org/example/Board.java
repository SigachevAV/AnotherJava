package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable
{
    public List<List<Integer>> m_board = new ArrayList<>();

    public Board()
    {
        for(int i = 0; i<19; i++)
        {
            m_board.add(new ArrayList<>());
            for (int j = 0; j < 19; j++)
            {
                m_board.get(i).add(0);
            }
        }
    }
    public int GetCell(int _x, int _y)
    {
        return m_board.get(_y).get(_x);
    }
    public void SetCell(int _x, int _y, int _value)
    {
        m_board.get(_y).set(_x, _value);
    }
}
