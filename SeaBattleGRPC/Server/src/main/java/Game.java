
public class Game {
    public int[][] seaPlayerOne = new int[10][10];
    public int[][] seaPlayerTwo = new int[10][10];

    public int currPlauer = 0;

    public int id = 0;

    public String printSea(int[][] arr) {
        String result = "";
        for (int n = 0; n < 10; n++) {
            for (int m = 0; m < 10; m++) {
                result += "[" + arr[m][n] + "]";
            }
            result +=  "\n";
        }
        result +=  "\n";
        return result;
    }

    public String getGridByPlayer(int i)
    {
        switch (i)
        {
            case 0:
            {
                return printSea(getSea2());
            }

            case 1:
            {
                return printSea(getSea());
            }
        }
        return "";
    }

    public boolean checkWin(int[][] arr) {
        int sum = 0;
        for (int n = 0; n < 10; n++) {
            for (int m = 0; m < 10; m++) {
                sum += arr[m][n];
            }
        }
        if (sum == 0) return false;
        return true;
    }



    public void hitShip(int state, int x, int y) {
        if (state == 0)
        {
            if (seaPlayerTwo[x][y] == 1)
            {
                seaPlayerTwo[x][y] = 0;
                currPlauer = 0;
            }
            else
            {
                currPlauer = 1;
            }
        }
        else
        {
            if (seaPlayerOne[x][y] == 1)
            {
                seaPlayerOne[x][y] = 0;
                currPlauer = 1;
            }
            else
            {
                currPlauer = 0;
            }
        }
        id++;
    }

    public int[][] getSea()  {
        return seaPlayerTwo;
    }

    public int[][] getSea2()  {
        return seaPlayerOne;
    }

    public void setSea(int[][] arr)  {
        seaPlayerTwo = arr;
    }

    public void setSea2(int[][] arr)  {
        seaPlayerOne = arr;
    }

    public int[][] GetSea(int i)
    {
        switch (i)
        {
            case (0):
            {
                return seaPlayerOne;
            }

            case (1):
            {
                return seaPlayerTwo;
            }
        }
        return null;
    }

    public void SetSea(int[][] sea, int i)
    {
        switch (i)
        {
            case (0):
            {
                seaPlayerOne = sea;
                return;
            }

            case (1):
            {
                seaPlayerTwo = sea;
                return;
            }
        }

    }


    public int[][] setShip(int in, int type, int x, int y, int direction) {

        int [][] arr = GetSea(in);
        switch (direction) {
            case 0:
                if (y >= type - 1) {
                    for (int i = 0; i < type; i++) {
                        arr[x][y - i] = 1;
                    }
                }

                break;
            case 2:
                if (y <= 10 - type) {
                    for (int i = 0; i < type; i++) {
                        arr[x][y + i] = 1;
                    }
                }
                break;
            case 1:
                if (x <= 10 - type) {
                    for (int i = 0; i < type; i++) {
                        arr[x + i][y] = 1;
                    }
                }
                break;
            case 3:
                if (x >= type - 1) {
                    for (int i = 0; i < type; i++) {
                        arr[x - i][y] = 1;
                    }
                }
                break;
        }
        return arr;
    }
}