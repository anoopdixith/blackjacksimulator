package dao;

/**
 * Created by adixith.
 */
public class Member {
    public Member(long memberId,
                  String memberName,
                  String memberEmail,
                  long memberInvestment) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberInvestment = memberInvestment;
    }

    public Member(long memberId) {
        this.memberId = memberId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public long getMemberInvestment() {
        return memberInvestment;
    }

    public void setMemberInvestment(long memberInvestment) {
        this.memberInvestment = memberInvestment;
    }

    private long memberId;
    private String memberName;
    private String memberEmail;
    private long memberInvestment;

}
