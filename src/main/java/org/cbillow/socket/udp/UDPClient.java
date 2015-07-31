package org.cbillow.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * Created by Cbillow on 15/7/28.
 */
public class UDPClient {


    private static final int TIMEOUT = 5000;
    private static final int MAXNUM = 5;

    public static void main(String[] args) throws IOException {
        String str_send = "Hello UDPserver";
        byte[] buf = new byte[1024];

        DatagramSocket ds = new DatagramSocket(9000);
        InetAddress loc = InetAddress.getLocalHost();
        DatagramPacket dp_send = new DatagramPacket(str_send.getBytes(), str_send.length(), loc, 3000);
        DatagramPacket dp_receive = new DatagramPacket(buf, 1024);

        ds.setSoTimeout(TIMEOUT);
        int tries = 0;
        boolean receiveResponse = false;

        while (!receiveResponse && tries < MAXNUM) {
            ds.send(dp_send);
            try {
                ds.receive(dp_receive);
                if (!dp_receive.getAddress().equals(loc)) {
                    throw new IOException("Received packet from umknown source");
                }
                receiveResponse = true;
            } catch (IOException e) {
                tries += 1;
                System.out.println("Time out, " + (MAXNUM - tries) + " more tries...");
            }
        }
        if (receiveResponse) {
            System.out.println("Client received data from server: ");
            String str_receive = new String(dp_receive.getData(), 0, dp_receive.getLength())
                    + "from " + dp_receive.getAddress().getHostAddress() + ":" +
                    dp_receive.getPort();
            System.out.println(str_receive);
            dp_receive.setLength(1024);

        } else {
            System.out.println("No response -- give up.");
        }
        ds.close();

    }
}


