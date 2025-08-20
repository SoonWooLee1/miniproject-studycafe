package studycafe.aggregate;

import java.io.Serializable;

public class Member implements Serializable {
    private int memNo;
    private String id;
    private String pwd;
    private String name;
    private AccountStatus accountStatus;
    private int remainingTime;      // 남은 시간
    private int currentSeatNo;      // 현재 좌석 (-1은 미사용)

    public Member() {
    }

    /* 설명. 회원 가입할 때의 생성자 */
    public Member(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    /* 설명. 전체 필드 초기화용 생성자 */
    public Member(int memNo, String id, String pwd, String name, AccountStatus accountStatus, int remainingTime, int currentSeatNo) {
        this.memNo = memNo;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.accountStatus = accountStatus;
        this.remainingTime = remainingTime;
        this.currentSeatNo = currentSeatNo;
    }

    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getCurrentSeatNo() {
        return currentSeatNo;
    }

    public void setCurrentSeatNo(int currentSeatNo) {
        this.currentSeatNo = currentSeatNo;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memNo=" + memNo +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", accountStatus=" + accountStatus +
                ", remainingTime=" + remainingTime +
                ", currentSeatNo=" + currentSeatNo +
                '}';
    }
}
