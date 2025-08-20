package studycafe.aggregate;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Seat implements Serializable {
    private int seatNo;
    private boolean isOccupied;
    private int occupyingMemberNo;
    private LocalDateTime startTime;

    public Seat() {
    }

    public Seat(int seatNo) {
        this.seatNo = seatNo;
        this.isOccupied = false;
        this.occupyingMemberNo = -1;
        this.startTime = null;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getOccupyingMemberNo() {
        return occupyingMemberNo;
    }

    public void setOccupyingMemberNo(int occupyingMemberNo) {
        this.occupyingMemberNo = occupyingMemberNo;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNo=" + seatNo +
                ", isOccupied=" + isOccupied +
                ", occupyingMemberNo=" + occupyingMemberNo +
                ", startTime=" + startTime +
                '}';
    }
}
