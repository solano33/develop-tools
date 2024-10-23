// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common/common.proto

package com.solano.grpc.common;

public final class Common {
  private Common() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GrpcBaseResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.solano.grpc.common.GrpcBaseResponse)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     **
     * 返回码
     * </pre>
     *
     * <code>int32 code = 1;</code>
     * @return The code.
     */
    int getCode();

    /**
     * <pre>
     **
     * 返回消息
     * </pre>
     *
     * <code>string responseMessage = 2;</code>
     * @return The responseMessage.
     */
    java.lang.String getResponseMessage();
    /**
     * <pre>
     **
     * 返回消息
     * </pre>
     *
     * <code>string responseMessage = 2;</code>
     * @return The bytes for responseMessage.
     */
    com.google.protobuf.ByteString
        getResponseMessageBytes();
  }
  /**
   * <pre>
   **
   * 基本返回信息
   * </pre>
   *
   * Protobuf type {@code com.solano.grpc.common.GrpcBaseResponse}
   */
  public static final class GrpcBaseResponse extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.solano.grpc.common.GrpcBaseResponse)
      GrpcBaseResponseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use GrpcBaseResponse.newBuilder() to construct.
    private GrpcBaseResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GrpcBaseResponse() {
      responseMessage_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new GrpcBaseResponse();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.solano.grpc.common.Common.internal_static_com_solano_grpc_common_GrpcBaseResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.solano.grpc.common.Common.internal_static_com_solano_grpc_common_GrpcBaseResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.solano.grpc.common.Common.GrpcBaseResponse.class, com.solano.grpc.common.Common.GrpcBaseResponse.Builder.class);
    }

    public static final int CODE_FIELD_NUMBER = 1;
    private int code_ = 0;
    /**
     * <pre>
     **
     * 返回码
     * </pre>
     *
     * <code>int32 code = 1;</code>
     * @return The code.
     */
    @java.lang.Override
    public int getCode() {
      return code_;
    }

    public static final int RESPONSEMESSAGE_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object responseMessage_ = "";
    /**
     * <pre>
     **
     * 返回消息
     * </pre>
     *
     * <code>string responseMessage = 2;</code>
     * @return The responseMessage.
     */
    @java.lang.Override
    public java.lang.String getResponseMessage() {
      java.lang.Object ref = responseMessage_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        responseMessage_ = s;
        return s;
      }
    }
    /**
     * <pre>
     **
     * 返回消息
     * </pre>
     *
     * <code>string responseMessage = 2;</code>
     * @return The bytes for responseMessage.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getResponseMessageBytes() {
      java.lang.Object ref = responseMessage_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        responseMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (code_ != 0) {
        output.writeInt32(1, code_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(responseMessage_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, responseMessage_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (code_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, code_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(responseMessage_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, responseMessage_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.solano.grpc.common.Common.GrpcBaseResponse)) {
        return super.equals(obj);
      }
      com.solano.grpc.common.Common.GrpcBaseResponse other = (com.solano.grpc.common.Common.GrpcBaseResponse) obj;

      if (getCode()
          != other.getCode()) return false;
      if (!getResponseMessage()
          .equals(other.getResponseMessage())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + CODE_FIELD_NUMBER;
      hash = (53 * hash) + getCode();
      hash = (37 * hash) + RESPONSEMESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getResponseMessage().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.solano.grpc.common.Common.GrpcBaseResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.solano.grpc.common.Common.GrpcBaseResponse prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     **
     * 基本返回信息
     * </pre>
     *
     * Protobuf type {@code com.solano.grpc.common.GrpcBaseResponse}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.solano.grpc.common.GrpcBaseResponse)
        com.solano.grpc.common.Common.GrpcBaseResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.solano.grpc.common.Common.internal_static_com_solano_grpc_common_GrpcBaseResponse_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.solano.grpc.common.Common.internal_static_com_solano_grpc_common_GrpcBaseResponse_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.solano.grpc.common.Common.GrpcBaseResponse.class, com.solano.grpc.common.Common.GrpcBaseResponse.Builder.class);
      }

      // Construct using com.solano.grpc.common.Common.GrpcBaseResponse.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        code_ = 0;
        responseMessage_ = "";
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.solano.grpc.common.Common.internal_static_com_solano_grpc_common_GrpcBaseResponse_descriptor;
      }

      @java.lang.Override
      public com.solano.grpc.common.Common.GrpcBaseResponse getDefaultInstanceForType() {
        return com.solano.grpc.common.Common.GrpcBaseResponse.getDefaultInstance();
      }

      @java.lang.Override
      public com.solano.grpc.common.Common.GrpcBaseResponse build() {
        com.solano.grpc.common.Common.GrpcBaseResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.solano.grpc.common.Common.GrpcBaseResponse buildPartial() {
        com.solano.grpc.common.Common.GrpcBaseResponse result = new com.solano.grpc.common.Common.GrpcBaseResponse(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.solano.grpc.common.Common.GrpcBaseResponse result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.code_ = code_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.responseMessage_ = responseMessage_;
        }
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.solano.grpc.common.Common.GrpcBaseResponse) {
          return mergeFrom((com.solano.grpc.common.Common.GrpcBaseResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.solano.grpc.common.Common.GrpcBaseResponse other) {
        if (other == com.solano.grpc.common.Common.GrpcBaseResponse.getDefaultInstance()) return this;
        if (other.getCode() != 0) {
          setCode(other.getCode());
        }
        if (!other.getResponseMessage().isEmpty()) {
          responseMessage_ = other.responseMessage_;
          bitField0_ |= 0x00000002;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 8: {
                code_ = input.readInt32();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 18: {
                responseMessage_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private int code_ ;
      /**
       * <pre>
       **
       * 返回码
       * </pre>
       *
       * <code>int32 code = 1;</code>
       * @return The code.
       */
      @java.lang.Override
      public int getCode() {
        return code_;
      }
      /**
       * <pre>
       **
       * 返回码
       * </pre>
       *
       * <code>int32 code = 1;</code>
       * @param value The code to set.
       * @return This builder for chaining.
       */
      public Builder setCode(int value) {

        code_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **
       * 返回码
       * </pre>
       *
       * <code>int32 code = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearCode() {
        bitField0_ = (bitField0_ & ~0x00000001);
        code_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object responseMessage_ = "";
      /**
       * <pre>
       **
       * 返回消息
       * </pre>
       *
       * <code>string responseMessage = 2;</code>
       * @return The responseMessage.
       */
      public java.lang.String getResponseMessage() {
        java.lang.Object ref = responseMessage_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          responseMessage_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       **
       * 返回消息
       * </pre>
       *
       * <code>string responseMessage = 2;</code>
       * @return The bytes for responseMessage.
       */
      public com.google.protobuf.ByteString
          getResponseMessageBytes() {
        java.lang.Object ref = responseMessage_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          responseMessage_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       **
       * 返回消息
       * </pre>
       *
       * <code>string responseMessage = 2;</code>
       * @param value The responseMessage to set.
       * @return This builder for chaining.
       */
      public Builder setResponseMessage(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        responseMessage_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       **
       * 返回消息
       * </pre>
       *
       * <code>string responseMessage = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearResponseMessage() {
        responseMessage_ = getDefaultInstance().getResponseMessage();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <pre>
       **
       * 返回消息
       * </pre>
       *
       * <code>string responseMessage = 2;</code>
       * @param value The bytes for responseMessage to set.
       * @return This builder for chaining.
       */
      public Builder setResponseMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        responseMessage_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.solano.grpc.common.GrpcBaseResponse)
    }

    // @@protoc_insertion_point(class_scope:com.solano.grpc.common.GrpcBaseResponse)
    private static final com.solano.grpc.common.Common.GrpcBaseResponse DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.solano.grpc.common.Common.GrpcBaseResponse();
    }

    public static com.solano.grpc.common.Common.GrpcBaseResponse getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GrpcBaseResponse>
        PARSER = new com.google.protobuf.AbstractParser<GrpcBaseResponse>() {
      @java.lang.Override
      public GrpcBaseResponse parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<GrpcBaseResponse> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GrpcBaseResponse> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.solano.grpc.common.Common.GrpcBaseResponse getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_solano_grpc_common_GrpcBaseResponse_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_solano_grpc_common_GrpcBaseResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023common/common.proto\022\026com.solano.grpc.c" +
      "ommon\"9\n\020GrpcBaseResponse\022\014\n\004code\030\001 \001(\005\022" +
      "\027\n\017responseMessage\030\002 \001(\tb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_solano_grpc_common_GrpcBaseResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_solano_grpc_common_GrpcBaseResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_solano_grpc_common_GrpcBaseResponse_descriptor,
        new java.lang.String[] { "Code", "ResponseMessage", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}