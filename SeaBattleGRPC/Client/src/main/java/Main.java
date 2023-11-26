import com.example.grpc.SeaBattleServiceGrpc;
import com.example.grpc.SeaBattleServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main
{
    private static void PrintGrid(SeaBattleServiceOuterClass.GridResponse _response)
    {
        System.out.println(_response.getGrid());
        System.out.println(_response.getMessage());

        System.out.println(_response.getTurn());

    }

    private static int id = -1;

    public static void main(String[] args)
    {
        int sing;
        System.out.println("Введите ваш знак");
        sing = Integer.parseInt(new Scanner(System.in).nextLine());
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:12345").usePlaintext().build();
        SeaBattleServiceGrpc.SeaBattleServiceBlockingStub stub = SeaBattleServiceGrpc.newBlockingStub(channel);
        System.out.println("Please, set your ships");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 1; i++) {
            System.out.println("Enter type(1-4),x,y,direction(0-3)");
            int type = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int direction = in.nextInt();
            SeaBattleServiceOuterClass.ShipRequest request = SeaBattleServiceOuterClass.ShipRequest.newBuilder()
                    .setTurn(sing)
                    .setDir(direction)
                    .setSize(type)
                    .setX(x)
                    .setY(y)
                    .build();
            PrintGrid(stub.setShip(request));
        }
        SeaBattleServiceOuterClass.GridRequest emptyRequest  = SeaBattleServiceOuterClass.GridRequest.newBuilder().setTurn(sing).build();
        SeaBattleServiceOuterClass.GridResponse response;
        in = new Scanner(System.in);
        while (true)
        {
            response = stub.getGrid(emptyRequest);
            if (response.getId() == id)
            {
                try
                {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            PrintGrid(response);
            id = response.getId();
            while (response.getTurn() == sing)
            {
                System.out.println("Введите X");
                int x = Integer.parseInt(in.nextLine());
                System.out.println("Введите Y");
                int y = Integer.parseInt(in.nextLine());
                SeaBattleServiceOuterClass.TurnRequest request = SeaBattleServiceOuterClass.TurnRequest.newBuilder()
                        .setTurn(sing)
                        .setX(x)
                        .setY(y)
                        .build();
                response = stub.makeTurn(request);
                PrintGrid(response);
                id = response.getId();


            }
        }

    }
}
