package org.cbillow.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * @author Created by Cbillow
 * @date 16/1/17
 * @time 15:24
 */
public class ClientBufferHandler extends SimpleChannelHandler {

    /**
     * 连接到服务端的时候发送信息
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        sendMessageByFrame(e);
    }

    /**
     * 发送的字符串
     * @param e
     */
    private void sendMessageByFrame(ChannelStateEvent e) {
        String msgOne = "Hello, ";
        String msgTwo = "I'm ";
        String msgThree = "client.";

        e.getChannel().write(tranStr2Buffer(msgOne));
        e.getChannel().write(tranStr2Buffer(msgTwo));
        e.getChannel().write(tranStr2Buffer(msgThree));
    }

    /**
     * 将字符串转换成ChannelBuffer
     * @param str
     * @return
     */
    private ChannelBuffer tranStr2Buffer(String str) {
        ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
        buffer.writeBytes(str.getBytes());
        return buffer;
    }
}
