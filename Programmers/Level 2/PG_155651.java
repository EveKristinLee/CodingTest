//Programmers Level2 155651 호텔 대실

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PG_155651 {
    private static class Book implements Comparable<Book> {
        int startTime;
        int endTime;
        public Book (int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int compareTo(Book book) {
            if(this.startTime == book.startTime) {
                return this.endTime - book.endTime;
            }
            return this.startTime - book.startTime;
        }
    }
    static List<Book> reservation;

    public static int solution(String[][] book_time) {
        int cnt = book_time.length;
        reservation = new ArrayList<>();
        for(int i=0; i<cnt; i++) {
            int start = StringToInt(book_time[i][0]);
            int end = StringToInt(book_time[i][1]);
            // System.out.println("start : " + start + " end : " + end);
            reservation.add(new Book(start, end));
        }
        Collections.sort(reservation);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(reservation.get(0).endTime);

        for(int i=1; i<cnt; i++) {
            Integer now = pq.peek();
            if(now + 10 <= reservation.get(i).startTime) {
                pq.poll();
            }
            pq.offer(reservation.get(i).endTime);
        }
        return pq.size();
    }

    private static int StringToInt(String time) {
        int res = 0;
        String[] sp = time.split(":");
        res += (Integer.parseInt(sp[0]) * 60);
        res += Integer.parseInt(sp[1]);
        return res;
    }


    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        int res = solution(book_time);
        System.out.println(res);
    }
}
