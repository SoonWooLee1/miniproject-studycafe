package studycafe.service;

import studycafe.aggregate.AccountStatus;
import studycafe.aggregate.Member;
import studycafe.repository.MemberRepository;

import java.util.ArrayList;

public class MemberService {
    private final MemberRepository mr = new MemberRepository();

    public void findAllMembers() {
        ArrayList<Member> members = mr.findAllMembers();
        if (members.isEmpty()) {
            System.out.println("조회할 수 있는 회원이 없습니다.");
            return;
        }
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public Member findMemberBy(int memNo) {
        Member selectedMember = mr.findMemberBy(memNo);
        if (selectedMember == null) {
            System.out.println("존재하지 않는 회원 번호입니다.");
            return null;
        }
        return selectedMember;
    }

    public void registMember(Member newMember) {
        int lastMemberNo = mr.findLastMemberNo();
        newMember.setMemNo(lastMemberNo + 1);
        newMember.setAccountStatus(AccountStatus.ACTIVE);
        newMember.setRemainingTime(0);
        newMember.setCurrentSeatNo(-1);

        int result = mr.registMember(newMember);
        if (result == 1) {
            System.out.println(newMember.getName() + "님의 회원 가입이 완료되었습니다.");
        } else {
            System.out.println("회원 가입에 실패했습니다.");
        }
    }

    public void modifyMember(Member updatedMember) {
        int result = mr.modifyMember(updatedMember);
        if (result > 0) {
            System.out.println("회원 정보 수정이 완료되었습니다.");
        } else {
            System.out.println("회원 정보 수정에 실패했습니다.");
        }
    }

    public void removeMember(int memNo) {
        int result = mr.removeMember(memNo);
        if (result > 0) {
            System.out.println("회원 탈퇴 처리가 완료되었습니다.");
        } else {
            System.out.println("회원 탈퇴에 실패했습니다.");
        }
    }

    public void addTimeToMember(int memNo, int minutes) {
        Member member = mr.findMemberBy(memNo);
        if (member == null) {
            System.out.println("존재하지 않는 회원입니다.");
            return;
        }
        member.setRemainingTime(member.getRemainingTime() + minutes);
        int result = mr.modifyMember(member);
        if (result > 0) {
            System.out.println(member.getName() + "님에게 " + minutes + "분이 충전되었습니다. (현재: " + member.getRemainingTime() + "분)");
        } else {
            System.out.println("시간 충전에 실패했습니다.");
        }
    }
}
