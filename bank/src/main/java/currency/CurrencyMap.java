package currency;

import exchange_rate.RatioService;
import exchange_rate.RatioService.*;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyMap implements StreamObserver<Ratio> {
    private Map<Relation, Double> currencies = new ConcurrentHashMap<>();
    private ServiceProxy ServiceProxy = new ServiceProxy();
    private WantedCurrencies wantedCurrencies;

    public CurrencyMap(String baseCur, List<String> wantedCur) {
        this.wantedCurrencies = RatioService.WantedCurrencies
                .newBuilder()
                .setBaseCurCode(baseCur)
                .addAllWantedCurCodes(wantedCur)
                .build();
    }

    public void init() {
        this.ServiceProxy
                .getRatio(this.wantedCurrencies)
                .getRatioList()
                .forEach(this::put);
        this.ServiceProxy.update(this.wantedCurrencies, this);
    }

    private void put(Ratio ratio) {
        Relation relation = new Relation(ratio.getBaseCur(), ratio.getOtherCur());
        this.currencies.put(relation, ratio.getValue());
        System.out.println("INFO: " + relation + " = " + String.valueOf(ratio.getValue()));
    }

    public Double getRatio(String base, String other) {
        if (base.equals(other)){
            return 1d;
        }
        return this.getRatio(new Relation(base, other));
    }

    private Double getRatio(Relation relation) {
        return this.currencies.get(relation);
    }

    @Override
    public void onNext(Ratio value) {
        this.put(value);
    }

    @Override
    public void onError(Throwable t) {
        System.err.println(t.toString());
    }

    @Override
    public void onCompleted() {
        System.err.println("server disconnected");
    }
}
