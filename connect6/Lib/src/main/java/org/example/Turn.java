package org.example;

import java.io.Serializable;

public class Turn implements Serializable
{
    public Integer m_x;
    public Integer m_y;
    public Integer m_player;
    public Turn(Integer _x, Integer _y, Integer _player)
    {
        this.m_player = _player;
        this.m_x = _x;
        this.m_y = _y;
    }
}
