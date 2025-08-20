package studycafe.service;

import studycafe.aggregate.Member;
import studycafe.aggregate.Seat;
import studycafe.repository.MemberRepository;
import studycafe.repository.SeatRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudyCafeService {
    private final SeatRepository sr = new SeatRepository();
    private final MemberRepository mr = new MemberRepository();

    public void showAllSeats() {
        ArrayList<Seat> seats = sr.findAllSeats();
        System.out.println("======= 현재 좌석 현황 =======");
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            if (seat.isOccupied()) {
                System.out.printf(" [ %2d번 좌석 (사용 중 / %d번 회원) ] ", seat.getSeatNo(), seat.getOccupyingMemberNo());
            } else {
                System.out.printf(" [ %2d번 좌석 (사용 가능) ] ", seat.getSeatNo());
            }
            if ((i + 1) % 5 == 0) { // 5개씩 출력 후 줄바꿈
                System.out.println();
            }
        }
        System.out.println("\n==============================");
    }

    public void checkIn(int memNo, int seatNo) {
        Member member = mr.findMemberBy(memNo);
        if (member == null) {
            System.out.println("존재하지 않는 회원입니다.");
            return;
        }

        if (member.getCurrentSeatNo() != -1) {
            System.out.println("이미 " + member.getCurrentSeatNo() + "번 좌석을 이용 중입니다.");
            return;
        }

        if (member.getRemainingTime() <= 0) {
            System.out.println("남은 시간이 없습니다. 먼저 시간을 충전해주세요.");
            return;
        }

        Seat seat = sr.findSeatByNo(seatNo);
        if (seat == null) {
            System.out.println("존재하지 않는 좌석입니다.");
            return;
        }

        if (seat.isOccupied()) {
            System.out.println(seatNo + "번 좌석은 이미 다른 회원이 사용 중입니다.");
            return;
        }

        seat.setOccupied(true);
        seat.setOccupyingMemberNo(memNo);
        seat.setStartTime(LocalDateTime.now());
        sr.modifySeat(seat);

        member.setCurrentSeatNo(seatNo);
        mr.modifyMember(member);

        System.out.println(member.getName() + " 회원님, " + seatNo + "번 좌석으로 입실이 완료되었습니다.");
    }

    public void checkOut(int memNo) {
        Member member = mr.findMemberBy(memNo);
        if (member == null) {
            System.out.println("존재하지 않는 회원입니다.");
            return;
        }

        int seatNo = member.getCurrentSeatNo();
        if (seatNo == -1) {
            System.out.println("이용 중인 좌석이 없습니다.");
            return;
        }

        Seat seat = sr.findSeatByNo(seatNo);

        long usedMinutes = Duration.between(seat.getStartTime(), LocalDateTime.now()).toMinutes();
        if (usedMinutes < 1) usedMinutes = 1;

        int remaining = member.getRemainingTime() - (int) usedMinutes;
        member.setRemainingTime(Math.max(0, remaining));
        member.setCurrentSeatNo(-1);
        mr.modifyMember(member);

        seat.setOccupied(false);
        seat.setOccupyingMemberNo(-1);
        seat.setStartTime(null);
        sr.modifySeat(seat);

        System.out.println(member.getName() + " 회원님, 퇴실이 완료되었습니다.");
        System.out.println("사용 시간: " + usedMinutes + "분");
        System.out.println("남은 시간: " + member.getRemainingTime() + "분");
    }
}
