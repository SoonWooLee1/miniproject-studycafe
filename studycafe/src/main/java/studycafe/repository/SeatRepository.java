package studycafe.repository;

import studycafe.aggregate.Seat;

import java.io.*;
import java.util.ArrayList;

public class SeatRepository {
    private ArrayList<Seat> seatList = new  ArrayList<>() ;
    private final File file = new File(
            "src/main/java/studycafe/db/seatDB.dat");

    public SeatRepository() {
        File dbDir = new File("src/main/java/studycafe/db");
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        if (!file.exists()) {
            ArrayList<Seat> defaultSeatList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                defaultSeatList.add(new Seat(i));
            }
            saveSeats(defaultSeatList);
        }

        loadSeats();
    }

    private void saveSeats(ArrayList<Seat> seats) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Seat seat : seats) {
                oos.writeObject(seat);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSeats() {
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            seatList.clear();
            while (true) {
                seatList.add((Seat) ois.readObject());
            }
        } catch (EOFException e) {
            // 파일 끝
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Seat> findAllSeats() {
        return seatList;
    }

    public Seat findSeatByNo(int seatNo) {
        for (Seat seat : seatList) {
            if (seat.getSeatNo() == seatNo) {
                return seat;
            }
        }
        return null;
    }

    public int modifySeat(Seat updatedSeat) {
        for (int i = 0; i < seatList.size(); i++) {
            if (seatList.get(i).getSeatNo() == updatedSeat.getSeatNo()) {
                seatList.set(i, updatedSeat);
                saveSeats(seatList);
                return 1;
            }
        }
        return 0;
    }
}
