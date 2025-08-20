package studycafe.run;

import studycafe.aggregate.Member;
import studycafe.service.MemberService;
import studycafe.service.StudyCafeService;

import java.util.Scanner;

public class Application {
    private static final MemberService ms =  new MemberService();
    private static final StudyCafeService scs =  new StudyCafeService();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("======= 스터디 카페 관리 프로그램 =======");
            System.out.println("1. 좌석 현황 보기");
            System.out.println("2. 입실히기");
            System.out.println("3. 퇴실히기");
            System.out.println("4. 시간 충전");
            System.out.println("5. 회원 관리");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴를 선택해 주세요: ");
            int input =  sc.nextInt();
            sc .nextLine();
            switch (input) {
                case 1: scs.showAllSeats(); break;
                case 2: scs.checkIn(chooseMemNo("입실할"), chooseSeatNo());  break;
                case 3: scs.checkOut(chooseMemNo("퇴실할"));  break;
                case 4: ms.addTimeToMember(chooseMemNo("시간을 충전할"), inputTime());
                case 5: memberManagementMenu(); break;
                case 9:
                    System.out.println("프로그램을 종료하겠습니다.");
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }

    private static void memberManagementMenu() {
        while (true) {
            System.out.println("======= 회원 관리 메뉴 =======");
            System.out.println("1. 모든 회원 정보 보기");
            System.out.println("2. 회원 찾기");
            System.out.println("3. 회원 가입");
            System.out.println("4. 회원 정보 수정");
            System.out.println("5. 회원 탈퇴");
            System.out.println("9. 메인 메뉴로 돌아가기");
            System.out.print("메뉴를 선택해주세요: ");
            int input =  sc.nextInt();
            sc .nextLine();

            switch (input) {
                case 1:
                    ms.findAllMembers(); break;
                case 2:
                    Member found = ms.findMemberBy(chooseMemNo("조회할"));
                    if (found != null) System.out.println(found);
                    break;
                case 3:
                    ms.registMember(signup()); break;
                case 4:
                    modifyMenu(); break;
                case 5:
                    ms.removeMember(chooseMemNo("탈퇴시킬"));
                    break;
                case 9:
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }

    private static void modifyMenu() {
        int memNo;
    }

    private static int chooseMemNo(String 입실할) {
        return 0;
    }


}
