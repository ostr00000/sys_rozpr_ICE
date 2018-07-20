package exchange_rate;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class CurrencyInfoGrpc {

  private CurrencyInfoGrpc() {}

  public static final String SERVICE_NAME = "exchange_rate.CurrencyInfo";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<exchange_rate.RatioService.WantedCurrencies,
      exchange_rate.RatioService.CurrenciesRatio> METHOD_GET_RATIO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "exchange_rate.CurrencyInfo", "GetRatio"),
          io.grpc.protobuf.ProtoUtils.marshaller(exchange_rate.RatioService.WantedCurrencies.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(exchange_rate.RatioService.CurrenciesRatio.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<exchange_rate.RatioService.WantedCurrencies,
      exchange_rate.RatioService.Ratio> METHOD_UPDATE_RATIO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "exchange_rate.CurrencyInfo", "UpdateRatio"),
          io.grpc.protobuf.ProtoUtils.marshaller(exchange_rate.RatioService.WantedCurrencies.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(exchange_rate.RatioService.Ratio.getDefaultInstance()));

  public static CurrencyInfoStub newStub(io.grpc.Channel channel) {
    return new CurrencyInfoStub(channel);
  }

  public static CurrencyInfoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CurrencyInfoBlockingStub(channel);
  }

  public static CurrencyInfoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CurrencyInfoFutureStub(channel);
  }

  public static interface CurrencyInfo {

    public void getRatio(exchange_rate.RatioService.WantedCurrencies request,
        io.grpc.stub.StreamObserver<exchange_rate.RatioService.CurrenciesRatio> responseObserver);

    public void updateRatio(exchange_rate.RatioService.WantedCurrencies request,
        io.grpc.stub.StreamObserver<exchange_rate.RatioService.Ratio> responseObserver);
  }

  public static interface CurrencyInfoBlockingClient {

    public exchange_rate.RatioService.CurrenciesRatio getRatio(exchange_rate.RatioService.WantedCurrencies request);

    public java.util.Iterator<exchange_rate.RatioService.Ratio> updateRatio(
        exchange_rate.RatioService.WantedCurrencies request);
  }

  public static interface CurrencyInfoFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<exchange_rate.RatioService.CurrenciesRatio> getRatio(
        exchange_rate.RatioService.WantedCurrencies request);
  }

  public static class CurrencyInfoStub extends io.grpc.stub.AbstractStub<CurrencyInfoStub>
      implements CurrencyInfo {
    private CurrencyInfoStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyInfoStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CurrencyInfoStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyInfoStub(channel, callOptions);
    }

    @java.lang.Override
    public void getRatio(exchange_rate.RatioService.WantedCurrencies request,
        io.grpc.stub.StreamObserver<exchange_rate.RatioService.CurrenciesRatio> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_RATIO, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateRatio(exchange_rate.RatioService.WantedCurrencies request,
        io.grpc.stub.StreamObserver<exchange_rate.RatioService.Ratio> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_UPDATE_RATIO, getCallOptions()), request, responseObserver);
    }
  }

  public static class CurrencyInfoBlockingStub extends io.grpc.stub.AbstractStub<CurrencyInfoBlockingStub>
      implements CurrencyInfoBlockingClient {
    private CurrencyInfoBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyInfoBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CurrencyInfoBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyInfoBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public exchange_rate.RatioService.CurrenciesRatio getRatio(exchange_rate.RatioService.WantedCurrencies request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_RATIO, getCallOptions(), request);
    }

    @java.lang.Override
    public java.util.Iterator<exchange_rate.RatioService.Ratio> updateRatio(
        exchange_rate.RatioService.WantedCurrencies request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_UPDATE_RATIO, getCallOptions(), request);
    }
  }

  public static class CurrencyInfoFutureStub extends io.grpc.stub.AbstractStub<CurrencyInfoFutureStub>
      implements CurrencyInfoFutureClient {
    private CurrencyInfoFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyInfoFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CurrencyInfoFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyInfoFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<exchange_rate.RatioService.CurrenciesRatio> getRatio(
        exchange_rate.RatioService.WantedCurrencies request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_RATIO, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RATIO = 0;
  private static final int METHODID_UPDATE_RATIO = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CurrencyInfo serviceImpl;
    private final int methodId;

    public MethodHandlers(CurrencyInfo serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RATIO:
          serviceImpl.getRatio((exchange_rate.RatioService.WantedCurrencies) request,
              (io.grpc.stub.StreamObserver<exchange_rate.RatioService.CurrenciesRatio>) responseObserver);
          break;
        case METHODID_UPDATE_RATIO:
          serviceImpl.updateRatio((exchange_rate.RatioService.WantedCurrencies) request,
              (io.grpc.stub.StreamObserver<exchange_rate.RatioService.Ratio>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final CurrencyInfo serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_GET_RATIO,
          asyncUnaryCall(
            new MethodHandlers<
              exchange_rate.RatioService.WantedCurrencies,
              exchange_rate.RatioService.CurrenciesRatio>(
                serviceImpl, METHODID_GET_RATIO)))
        .addMethod(
          METHOD_UPDATE_RATIO,
          asyncServerStreamingCall(
            new MethodHandlers<
              exchange_rate.RatioService.WantedCurrencies,
              exchange_rate.RatioService.Ratio>(
                serviceImpl, METHODID_UPDATE_RATIO)))
        .build();
  }
}
