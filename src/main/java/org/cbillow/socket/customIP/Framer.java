package org.cbillow.socket.customIP;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Cbillow on 15/7/28.
 */
public interface Framer {

    void frameMsg(byte[] message, OutputStream out) throws IOException;

    byte[] nextMsg() throws IOException;
}
