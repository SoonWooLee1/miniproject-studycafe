package studycafe.repository;

import studycafe.aggregate.AccountStatus;
import studycafe.aggregate.Member;

import java.io.*;
import java.util.ArrayList;

public class MemberRepository {
    private ArrayList<Member> memberList = new ArrayList<Member>();
    private final File file = new File(
            "src/main/java/studycafe/db/memberDB.dat");

    public MemberRepository() {
        File dbDir = new File("src/main/java/studycafe/db");
        if (!dbDir.exists()) {
            dbDir.mkdirs();
    }
        if (!file.exists()) {
            ArrayList<Member> defaultMemberList = new ArrayList<>();
            defaultMemberList.add(new Member(1, "user01", "pass01", "홍길동", AccountStatus.ACTIVE, 180, -1));
            defaultMemberList.add(new Member(2, "user02", "pass02", "김철수", AccountStatus.ACTIVE, 240, -1));
            defaultMemberList.add(new Member(3, "user03", "pass03", "이영희", AccountStatus.ACTIVE, 300, -1));

            saveMembers(defaultMemberList);
        }

        loadMembers();
    }

    private void saveMembers(ArrayList<Member> members) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (Member member : members) {
                oos.writeObject(member);
            }
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadMembers() {
        if (!file.exists()) return;

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            memberList.clear();
            while (true) {
                memberList.add((Member) ois.readObject());
            }
        } catch (EOFException e) {
            // 파일 끝
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Member> findAllMembers() {
        ArrayList<Member> returnList = new ArrayList<>();
        for (Member member : memberList) {
            if (member.getAccountStatus() == AccountStatus.ACTIVE) {
                returnList.add(member);
            }
        }
        return returnList;
    }

    public Member findMemberBy(int memNo) {
        for (Member member : memberList) {
            if (member.getMemNo() == memNo && member.getAccountStatus() == AccountStatus.ACTIVE) {
                return member;
            }
        }
        return null;
    }

    public int findLastMemberNo() {
        if (memberList.isEmpty()) {
            return 0;
        }
        return memberList.get(memberList.size() - 1).getMemNo();
    }

    public int registMember(Member newMember) {
        memberList.add(newMember);
        saveMembers(memberList);
        return 1;
    }

    public int modifyMember(Member updatedMember) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemNo() == updatedMember.getMemNo()) {
                memberList.set(i, updatedMember);
                saveMembers(memberList);
                return 1;
            }
        }
        return 0;
    }

    public int removeMember(int memNo) {
        for (Member member : memberList) {
            if (member.getMemNo() == memNo) {
                member.setAccountStatus(AccountStatus.DEACTIVE);
                saveMembers(memberList);
                return 1;
            }
        }
        return 0;
    }

}
