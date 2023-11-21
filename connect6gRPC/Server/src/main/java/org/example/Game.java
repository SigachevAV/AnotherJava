package org.example;

import java.util.*;

public class Game extends Observable
{
    public Board m_board;
    public Integer m_currentPlayer;
    public Integer m_turnId;
    public Game()
    {
        this.m_board = new Board();
        this.m_currentPlayer = 1;
        this.m_turnId =0;
    }
    private void ChangeTurn()
    {
        if (this.m_currentPlayer == 1)
        {
            this.m_currentPlayer = -1;
        }
        else
        {
            this.m_currentPlayer = 1;
        }
    }
    public boolean MakeTurn(int _x, int _y, int _player)
    {
        if(_player != m_currentPlayer)
        {
            return false;
        }
        _x -= 1;
        if (_x<0 || _x > 19)
        {
            return false;
        }
        _y -= 1;
        if (_y<0 || _y > 19)
        {
            return false;
        }
        if (m_board.GetCell(_x, _y) != 0)
        {
            return false;
        }
        m_board.SetCell(_x, _y, _player);
        ChangeTurn();
        this.m_turnId++;
        return true;
    }
    public boolean IsWinner(int _Player) {
        int winCondition = _Player * 6;
        for (int y = 0; y < 19; y++) {
            for (int x = 0; x < 19 - 6; x++) {
                int gridSum = 0;
                for (int i = 0; i < 6; i++) {
                    gridSum += m_board.GetCell(x + i, y);
                }
                if (gridSum == winCondition) {
                    return true;
                }
            }
        }
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19 - 6; y++) {
                int gridSum = 0;
                for (int i = 0; i < 6; i++) {
                    gridSum += m_board.GetCell(x, y + i);
                }
                if (gridSum == winCondition) {
                    return true;
                }
            }
        }
        for (int x = 0; x < 19 - 6; x++) {
            for (int y = 0; y < 19 - 6; y++) {
                int gridSum = 0;
                for (int i = 0; i < 6; i++) {
                    gridSum += m_board.GetCell(x + i, y + i);
                }
                if (gridSum == winCondition) {
                    return true;
                }
            }
        }
        for (int x = 0; x < 19 - 6; x++) {
            for (int y = 0; y < 19 - 6; y++) {
                int gridSum = 0;
                for (int i = 0; i < 6; i++) {
                    gridSum += m_board.GetCell(x - i + 5, y + i);
                }
                if (gridSum == winCondition) {
                    return true;
                }
            }
        }
        return false;
    }
}
