package studycafe.repository;

import studycafe.aggregate.Member;

import java.io.File;
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


}
