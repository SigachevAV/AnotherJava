import com.example.grpc.SeaBattleServiceGrpc;
import com.example.grpc.SeaBattleServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class SeaBattleService extends SeaBattleServiceGrpc.SeaBattleServiceImplBase
{
    Game game = new Game();

    private String BuildMessage()
    {
        String message = "";
        if (!game.checkWin(game.seaPlayerOne))
        {
            message = "Победил 1";
        }
        else if (!game.checkWin(game.seaPlayerTwo))
        {
            message = "Победил 0";
        }
        else
        {
            message = "Ходит " + game.currPlauer;
        }
        return message;
    }
    private SeaBattleServiceOuterClass.GridResponse BuildResponse (String grid)
    {
        return SeaBattleServiceOuterClass.GridResponse.newBuilder()
                .setTurn(game.currPlauer)
                .setMessage(BuildMessage())
                .setId(game.id)
                .setGrid(grid)
                .build();
    }

    @Override
    public void getGrid(SeaBattleServiceOuterClass.GridRequest request, StreamObserver<SeaBattleServiceOuterClass.GridResponse> responseObserver) {
        SeaBattleServiceOuterClass.GridResponse response = BuildResponse(game.getGridByPlayer(request.getTurn()));
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void makeTurn(SeaBattleServiceOuterClass.TurnRequest request, StreamObserver<SeaBattleServiceOuterClass.GridResponse> responseObserver) {
        int fildId = 0;
        switch (request.getTurn())
        {
            case 0: {
                fildId = 1;
            }
            case 1:
            {
                fildId = 0;
            }
        }
        game.hitShip(fildId, request.getX(), request.getY());
        System.out.println(game.getGridByPlayer(0));

        System.out.println(game.getGridByPlayer(1));
        SeaBattleServiceOuterClass.GridResponse response = BuildResponse(game.getGridByPlayer(request.getTurn()));
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setShip(SeaBattleServiceOuterClass.ShipRequest request, StreamObserver<SeaBattleServiceOuterClass.GridResponse> responseObserver) {
        game.setShip(request.getTurn(), request.getSize(), request.getX(), request.getY(), request.getDir());

        System.out.println(game.getGridByPlayer(0));

        System.out.println(game.getGridByPlayer(1));
        SeaBattleServiceOuterClass.GridResponse response = BuildResponse(game.getGridByPlayer(request.getTurn()));
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
