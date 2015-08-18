package com.havenspool.nettyprotobuf.server;

import com.havenspool.nettyprotobuf.proto.Message;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by havens on 15-8-18.
 */
public class ServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("channelRead:" + msg);
        Message.MessageVo.Builder builder= Message.MessageVo.newBuilder();
        builder.setCmd("time_check");
        builder.setData(System.currentTimeMillis()+"");
        Message.MessageVo message=builder.build();
        ctx.writeAndFlush(message);
        //System.out.println("channelRead:" + message);
//        ctx.write(msg);
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
