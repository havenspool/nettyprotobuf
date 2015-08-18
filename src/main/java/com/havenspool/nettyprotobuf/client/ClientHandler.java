package com.havenspool.nettyprotobuf.client;

import com.havenspool.nettyprotobuf.proto.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by havens on 15-8-18.
 */
public class ClientHandler extends ChannelHandlerAdapter {
    private final ByteBuf firstMessage;


    /**
     * Creates a client-side handler.
     */
    public ClientHandler() {
        firstMessage = Unpooled.buffer(Client.SIZE);
        for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Message.MessageVo.Builder builder= Message.MessageVo.newBuilder();
        builder.setCmd("time_check");
        builder.setData("hello");
        Message.MessageVo message=builder.build();
        ctx.writeAndFlush(message);
        System.out.println("channelActive:" + message);
        //ctx.writeAndFlush(firstMessage);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("channelRead:" + msg);
        //ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
