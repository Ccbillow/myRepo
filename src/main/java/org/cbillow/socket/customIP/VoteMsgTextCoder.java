package org.cbillow.socket.customIP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 基于文本的编码方式
 *
 */
public class VoteMsgTextCoder implements VoteMsgCoder{

    public static final String MAGIC = "Voting";
    public static final String VOTESTR = "v";
    public static final String INQSTR = "i";
    public static final String RESPONSESTR = "R";

    public static final String CHARSETNAME = "US-ASCII";
    public static final String DELIMSTR = " ";
    public static final int MAX_WIRE_LENGTH = 2000;

    /**
     * toWire（）方法简单地创建一个字符串，该字符串中包含了消息的所有字段，并由空白符隔开
     * @param msg
     * @return
     * @throws IOException
     */
    @Override
    public byte[] toWire(VoteMsg msg) throws IOException {
        String msgString = MAGIC + DELIMSTR + (msg.isInquiry() ? INQSTR : VOTESTR)
                + DELIMSTR + (msg.isResponse() ? RESPONSESTR + DELIMSTR : "")
                + Integer.toString(msg.getCandidateID()) + DELIMSTR
                + Long.toString(msg.getVoteCount());
        byte[] data = msgString.getBytes(CHARSETNAME);
        return data;
    }

    /**
     * fromWire（）方法首先检查”魔术字符串“，如果在消息最前面没有魔术字符串，则抛出一个异常
     * @param message
     * @return
     * @throws IOException
     */
    @Override
    public VoteMsg fromWire(byte[] message) throws IOException {
        ByteArrayInputStream msgStream = new ByteArrayInputStream(message);
        Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));
        boolean isInquiry;
        boolean isResponse;
        int candidateID;
        long voteCount;
        String token;

        try {
            token = s.next();
            if (!token.equals(MAGIC)) {
                throw new IOException("Bad magic string: " + token);
            }
            token = s.next();
            if (token.equals(VOTESTR)) {
                isInquiry = false;
            } else if (!token.equals(INQSTR)) {
                throw new IOException("Bad vote/inq indicator: " + token);
            } else {
                isInquiry = true;
            }

            token = s.next();
            if (token.equals(RESPONSESTR)) {
                isResponse = true;
                token = s.next();
            } else {
                isResponse = false;
            }

            candidateID = Integer.parseInt(token);
            if (isResponse) {
                token = s.next();
                voteCount = Long.parseLong(token);
            } else {
                voteCount = 0;
            }
        } catch (IOException e) {
            throw new IOException("Parse error....");
        }
        return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
    }

}
