package org.cbillow.socket.customIP;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 实现了基于长度的成帧方法，适用于长度小于65535个字节的消息
 *
 * 发送者给出消息长度，从左边开始，由高位到低位发送，将消息一起写入输出流
 * 在接收端，使用DataInputStream读取整型的长度信息，readFully（）方法将阻塞等待，直到给定的数组完全填满
 * 使用这种成帧方法，发送者不需要检查要成帧的消息内容，而只需要检查消息的长度是否超出了限制
 */
public class LengthFramer implements Framer {

    public static final int MAXMESSAGELENGTH = 65535;
    public static final int BYTEMASK = 0xff;
    public static final int SHORTMASK = 0xffff;
    public static final int BYTESHIFT = 8;

    private DataInputStream in;

    public LengthFramer(DataInputStream in) {
        this.in = new DataInputStream(in);
    }

    //对字节流message添加成帧信息，并输出到指定流
    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        //消息的长度不能超过65535
        if (message.length > MAXMESSAGELENGTH) {
            throw new IOException("message too long");
        }
        out.write((message.length >> BYTESHIFT) & BYTEMASK);
        out.write(message.length & BYTEMASK);
        out.write(message);
        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        int length ;

        try {
            //该方法读取2个字节，将它们作为big-endian整数进行解释，并以int型整数返回它们的值
            length = in.readUnsignedShort();
        } catch (IOException e) {
            return null;
        }
        // 0 <= length <= 65535
        byte[] msg = new byte[length];
        //该方法处阻塞等待，直到接收到足够的字节来填满指定的数组
        in.readFully(msg);
        return new byte[0];
    }
}
