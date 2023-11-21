package org.example;

import com.example.grpc.Connect6ServiceGrpc;
import com.example.grpc.Connect6ServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class ConnectService extends Connect6ServiceGrpc.Connect6ServiceImplBase
{
    private Game m_game = new Game();

    private Connect6ServiceOuterClass.GridResponse GetResponse()
    {
        return BuildResponse(BuildMessage());
    }
    private Connect6ServiceOuterClass.GridResponse GetResponse(boolean _isValid)
    {
        String message = "";
        if (!_isValid)
        {
            message += "Неверный ход\n";
        }
        message+=BuildMessage();
        return BuildResponse(message);
    }

    private Connect6ServiceOuterClass.GridResponse BuildResponse(String _message)
    {
        return Connect6ServiceOuterClass.GridResponse.newBuilder().setGrid(m_game.m_board.toString())
                .setTurn(m_game.m_currentPlayer)
                .setId(m_game.m_turnId)
                .setMessage(_message)
                .build();
    }
    private String BuildMessage()
    {
        String message = "";
        if (m_game.IsWinner(1))
        {
            message = "Победил 1";
        }
        else if (m_game.IsWinner(-1))
        {
            message = "Победил -1";
        }
        else
        {
            message = "Ходит " + m_game.m_currentPlayer;
        }
        return message;
    }

    @Override
    public void getGrid(Connect6ServiceOuterClass.GridRequest request, StreamObserver<Connect6ServiceOuterClass.GridResponse> responseObserver)
    {
        responseObserver.onNext(GetResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void makeTurn(Connect6ServiceOuterClass.TurnRequest request, StreamObserver<Connect6ServiceOuterClass.GridResponse> responseObserver)
    {
        responseObserver.onNext(GetResponse(m_game.MakeTurn(request.getX(), request.getY(), request.getTurn())));
        responseObserver.onCompleted();
    }
}
