package currency;

import exchange_rate.CurrencyInfoGrpc;
import exchange_rate.RatioService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ServiceProxy {
    private final ManagedChannel channel;
    private final CurrencyInfoGrpc.CurrencyInfoBlockingStub blockingStub;
    private final CurrencyInfoGrpc.CurrencyInfoStub asyncStub;

    ServiceProxy() {
        this("localhost", 32323);
    }

    ServiceProxy(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    private ServiceProxy(ManagedChannelBuilder channelBuilder) {
        this.channel = channelBuilder.build();
        this.blockingStub = CurrencyInfoGrpc.newBlockingStub(this.channel);
        this.asyncStub = CurrencyInfoGrpc.newStub(this.channel);
    }

    RatioService.CurrenciesRatio getRatio(RatioService.WantedCurrencies request) {
        return this.blockingStub.getRatio(request);
    }

    void update(RatioService.WantedCurrencies request, StreamObserver<RatioService.Ratio> observer) {
        this.asyncStub.updateRatio(request, observer);
    }

}
