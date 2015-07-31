package org.cbillow.socket.customIP;

/**
 * 存放消息中所包含的的信息
 *
 * candidateID的范围在0~1000；
 * voteCount在请求消息中必须为0；
 * voteCount不能为负数
 */
public class VoteMsg {

    private boolean isInquiry;      //true表示该消息是查询请求，false表示该消息是投票请求
    private boolean isResponse;    //true表示该消息是服务器发送的相应消息，false表示该消息为客户端发送的请求消息
    private int candidateID;       //指示了候选人的ID
    private long voteCount;        //指示出所查询的候选人获得的总选票数。

    public static final int MAX_CANDIDATE_ID = 1000;

    public VoteMsg(boolean isInquiry, boolean isResponse, int candidateID, long voteCount) throws IllegalArgumentException {
        if (voteCount != 0 && !isResponse) {
            throw new IllegalArgumentException("Request vote count must be zero");
        }
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad Candidate ID:" + candidateID);
        }
        if (voteCount < 0) {
            throw new IllegalArgumentException("Total must be >= zero");
        }

        this.candidateID = candidateID;
        this.voteCount = voteCount;
        this.isInquiry = isInquiry;
        this.isResponse = isResponse;
    }

    public boolean isInquiry() {
        return isInquiry;
    }

    public void setIsInquiry(boolean isInquiry) {
        this.isInquiry = isInquiry;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public void setIsResponse(boolean isResponse) {
        this.isResponse = isResponse;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) throws IllegalArgumentException {
        if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
            throw new IllegalArgumentException("Bad Candidate ID:" + candidateID);
        }
        this.candidateID = candidateID;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long count) {
        if ((count != 0 && !isResponse) || count < 0) {
            throw new IllegalArgumentException("Bad vote count");
        }
        this.voteCount = count;
    }

    @Override
    public String toString() {
        String res = (isInquiry ? "inquiry" : "vote") + " for candidate " + candidateID;
        if (isResponse) {
            res = "response to " + res + " who now has " + voteCount + " vote(s)";
        }
        return res;
    }
}
