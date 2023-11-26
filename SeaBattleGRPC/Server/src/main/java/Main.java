import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {
        Server server = ServerBuilder.forPort(12345).addService(new SeaBattleService()).build();
        try
        {
            server.start();
        }
        catch (IOException e)
        {
            System.out.println("Сервер не запущен");;
        }
        try
        {
            server.awaitTermination();
        }
        catch (InterruptedException e)
        {
            System.out.println("Не смог убить сервер");
        }
    }
}
