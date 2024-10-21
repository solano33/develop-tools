package com.solano.grpc.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * GeneralService
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: general/hello.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GrpcHelloServiceGrpc {

  private GrpcHelloServiceGrpc() {}

  public static final String SERVICE_NAME = "com.solano.grpc.service.GrpcHelloService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.solano.grpc.service.Hello.GrpcHelloParam,
      com.solano.grpc.common.Common.GrpcBaseResponse> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sayHello",
      requestType = com.solano.grpc.service.Hello.GrpcHelloParam.class,
      responseType = com.solano.grpc.common.Common.GrpcBaseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.solano.grpc.service.Hello.GrpcHelloParam,
      com.solano.grpc.common.Common.GrpcBaseResponse> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.solano.grpc.service.Hello.GrpcHelloParam, com.solano.grpc.common.Common.GrpcBaseResponse> getSayHelloMethod;
    if ((getSayHelloMethod = GrpcHelloServiceGrpc.getSayHelloMethod) == null) {
      synchronized (GrpcHelloServiceGrpc.class) {
        if ((getSayHelloMethod = GrpcHelloServiceGrpc.getSayHelloMethod) == null) {
          GrpcHelloServiceGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<com.solano.grpc.service.Hello.GrpcHelloParam, com.solano.grpc.common.Common.GrpcBaseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solano.grpc.service.Hello.GrpcHelloParam.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.solano.grpc.common.Common.GrpcBaseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcHelloServiceMethodDescriptorSupplier("sayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcHelloServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcHelloServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcHelloServiceStub>() {
        @java.lang.Override
        public GrpcHelloServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcHelloServiceStub(channel, callOptions);
        }
      };
    return GrpcHelloServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcHelloServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcHelloServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcHelloServiceBlockingStub>() {
        @java.lang.Override
        public GrpcHelloServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcHelloServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcHelloServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcHelloServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcHelloServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcHelloServiceFutureStub>() {
        @java.lang.Override
        public GrpcHelloServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcHelloServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcHelloServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * GeneralService
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * 任务重置
     * </pre>
     */
    default void sayHello(com.solano.grpc.service.Hello.GrpcHelloParam request,
        io.grpc.stub.StreamObserver<com.solano.grpc.common.Common.GrpcBaseResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GrpcHelloService.
   * <pre>
   **
   * GeneralService
   * </pre>
   */
  public static abstract class GrpcHelloServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GrpcHelloServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GrpcHelloService.
   * <pre>
   **
   * GeneralService
   * </pre>
   */
  public static final class GrpcHelloServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GrpcHelloServiceStub> {
    private GrpcHelloServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcHelloServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcHelloServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 任务重置
     * </pre>
     */
    public void sayHello(com.solano.grpc.service.Hello.GrpcHelloParam request,
        io.grpc.stub.StreamObserver<com.solano.grpc.common.Common.GrpcBaseResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GrpcHelloService.
   * <pre>
   **
   * GeneralService
   * </pre>
   */
  public static final class GrpcHelloServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GrpcHelloServiceBlockingStub> {
    private GrpcHelloServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcHelloServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcHelloServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 任务重置
     * </pre>
     */
    public com.solano.grpc.common.Common.GrpcBaseResponse sayHello(com.solano.grpc.service.Hello.GrpcHelloParam request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GrpcHelloService.
   * <pre>
   **
   * GeneralService
   * </pre>
   */
  public static final class GrpcHelloServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GrpcHelloServiceFutureStub> {
    private GrpcHelloServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcHelloServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcHelloServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * 任务重置
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.solano.grpc.common.Common.GrpcBaseResponse> sayHello(
        com.solano.grpc.service.Hello.GrpcHelloParam request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.solano.grpc.service.Hello.GrpcHelloParam) request,
              (io.grpc.stub.StreamObserver<com.solano.grpc.common.Common.GrpcBaseResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSayHelloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.solano.grpc.service.Hello.GrpcHelloParam,
              com.solano.grpc.common.Common.GrpcBaseResponse>(
                service, METHODID_SAY_HELLO)))
        .build();
  }

  private static abstract class GrpcHelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcHelloServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.solano.grpc.service.Hello.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcHelloService");
    }
  }

  private static final class GrpcHelloServiceFileDescriptorSupplier
      extends GrpcHelloServiceBaseDescriptorSupplier {
    GrpcHelloServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcHelloServiceMethodDescriptorSupplier
      extends GrpcHelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcHelloServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcHelloServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcHelloServiceFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
