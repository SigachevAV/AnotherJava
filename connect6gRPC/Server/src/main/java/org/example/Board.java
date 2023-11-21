package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable
{
    private static final long serialVersionUID = 1234567L;
    public List<List<Integer>> m_board = new ArrayList<List<Integer>>();

    public Board()
    {
        for(int i = 0; i<19; i++)
        {
            m_board.add(new ArrayList<Integer>());
            for (int j = 0; j < 19; j++)
            {
                m_board.get(i).add(0);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < m_board.size(); i++) {
            for (int j = 0; j < m_board.get(i).size(); j++) {
                result += " ";
                result += m_board.get(i).get(j);
            }
            result += "\n";
        }
        return result;
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
