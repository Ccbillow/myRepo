package org.cbillow.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.nio.charset.Charset;

/**
 * @author Created by Cbillow
 * @date 16/1/17
 * @time 15:16
 */
public class ServerBufferHandler extends SimpleChannelHandler {

    /**
     * 用于接收客户端消息，当有消息传来时触发
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        while (buffer.readableBytes() >= 5) {
            ChannelBuffer bytes = buffer.readBytes(5);
            System.out.println(bytes.toString(Charset.defaultCharset()));
        }
        System.out.println(buffer.toString(Charset.defaultCharset()));
    }
}
