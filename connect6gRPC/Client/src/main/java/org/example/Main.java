package org.example;

import com.example.grpc.Connect6ServiceGrpc;
import com.example.grpc.Connect6ServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Main
{

    private static void PrintGrid(Connect6ServiceOuterClass.GridResponse _response)
    {
        System.out.println(_response.getGrid());
        System.out.println(_response.getMessage());
        id = _response.getId();
    }

    private static int id = -1;

    public static void main(String[] args)
    {
        int sing;

        System.out.println("Введите ваш знак");
        sing = Integer.parseInt(new Scanner(System.in).nextLine());
        ManagedChannel channel = ManagedChannelBuilder.forTarget(Config.ipConfig).usePlaintext().build();
        Connect6ServiceGrpc.Connect6ServiceBlockingStub stub = Connect6ServiceGrpc.newBlockingStub(channel);
        Connect6ServiceOuterClass.GridRequest emptyRequest = Connect6ServiceOuterClass.GridRequest.newBuilder().build();
        Scanner scanner = new Scanner(System.in);

        Connect6ServiceOuterClass.GridResponse response;
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
            if (response.getTurn() == sing)
            {
                System.out.println("Введите X");
                int x = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите Y");
                int y = Integer.parseInt(scanner.nextLine());
                Connect6ServiceOuterClass.TurnRequest request = Connect6ServiceOuterClass.TurnRequest.newBuilder()
                        .setTurn(sing)
                        .setX(x)
                        .setY(y)
                        .build();
                response = stub.makeTurn(request);
                PrintGrid(response);
            }
        }
    }
}