import org.example.Game;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest
{
    Game game;
    void setUpGame() {
        game = new Game();
    }
    @Test
    public void HorizontalWin()
    {
        setUpGame();
        game.MakeTurn(1, 1, 1);
        game.MakeTurn(2, 1, 1);
        game.MakeTurn(3, 1, 1);
        game.MakeTurn(4, 1, 1);
        game.MakeTurn(5, 1, 1);
        game.MakeTurn(6, 1, 1);
        assertTrue(game.IsWinner(1));
    }

    @Test
    public void VerticallWin()
    {
        setUpGame();
        game.MakeTurn(1, 1, 1);
        game.MakeTurn(1, 2, 1);
        game.MakeTurn(1, 3, 1);
        game.MakeTurn(1, 4, 1);
        game.MakeTurn(1, 5, 1);
        game.MakeTurn(1, 6, 1);
        assertTrue(game.IsWinner(1));
    }

    @Test
    public void DioganalWin()
    {
        setUpGame();
        game.MakeTurn(1, 1, 1);
        game.MakeTurn(2, 2, 1);
        game.MakeTurn(3, 3, 1);
        game.MakeTurn(4, 4, 1);
        game.MakeTurn(5, 5, 1);
        game.MakeTurn(6, 6, 1);
        assertTrue(game.IsWinner(1));
    }

    @Test
    public void BackDioganalWin()
    {
        setUpGame();
        game.MakeTurn(6, 1, 1);
        game.MakeTurn(5, 2, 1);
        game.MakeTurn(4, 3, 1);
        game.MakeTurn(3, 4, 1);
        game.MakeTurn(2, 5, 1);
        game.MakeTurn(1, 6, 1);
        assertTrue(game.IsWinner(1));
    }

    @Test
    public void ChangeTurn()
    {
        setUpGame();
        game.ChangeTurn();
        assertTrue(game.MakeTurn(1, 1, -1));
    }
    @Test
    public void WrongTurn()
    {
        setUpGame();
        game.ChangeTurn();
        assertFalse(game.MakeTurn(1, 1, 1));
    }

}
