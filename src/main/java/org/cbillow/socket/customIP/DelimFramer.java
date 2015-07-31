package org.cbillow.socket.customIP;

import java.io.*;

/**
 * 实现了基于定界符的成帧方法，
 *
 * 定界符为换行符“\n”，当成帧的字节序列中包含有定界符时，它只是简单地抛出异常；
 * nextMsg（）方法扫描刘，直到读取到了定界符，并返回定界符前面所有的字符，如果流为空则返回null；
 * 如果直到流结束也没找到定界符，程序将抛出一个异常来指示成帧错误。
 */
public class DelimFramer implements Framer {

    private InputStream in ;                        //数据来源
    public static final byte DELIMITER = '\n';      //定界符

    public DelimFramer(InputStream in) {
        this.in = in;
    }

    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        for (byte b : message) {
            if (b == DELIMITER) {
                //如果在消息中检查到界定符，则抛出异常
                throw new IOException("Message contains delimiter");
            }
        }
        out.write(message);
        out.write(DELIMITER);
        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte ;

        while ((nextByte = in.read()) != DELIMITER) {
            //如果流已经结束还没有读取到定界符
            if (nextByte == -1) {

                //如果读取到的流为空，则返回null
                if (messageBuffer.size() == 0) {
                    return null;
                } else {
                    //如果读取到的流不为空，则抛出异常
                    throw new EOFException("Non-empty message without delimiter");
                }
            }
            messageBuffer.write(nextByte);
        }

        return messageBuffer.toByteArray();
    }
}
