package services;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import protobuf.ChatServiceGrpc;
import protobuf.ChatServiceOuterClass;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {
    private static final Set<StreamObserver<ChatServiceOuterClass.ChatMessageFromServer>> observers = ConcurrentHashMap.newKeySet();

    @Override
    public StreamObserver<ChatServiceOuterClass.ChatMessage> chat(StreamObserver<ChatServiceOuterClass.ChatMessageFromServer> responseObserver) {
        observers.add(responseObserver);

        return new StreamObserver<ChatServiceOuterClass.ChatMessage>() {
            @Override
            public void onNext(ChatServiceOuterClass.ChatMessage value) {

            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {

            }
        };
    }
}

