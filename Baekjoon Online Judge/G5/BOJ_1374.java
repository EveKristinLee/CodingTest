package solving.solve_0914;
//BOJ G5 1374 강의실

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1374 {
    private static class Class implements Comparable<Class> {
        int idx;
        int start;
        int end;

        public Class(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
        public int compareTo(Class c) {
            if(this.start == c.start) {
                return this.end - c.end;
            }
            return this.start - c.start;
        }
    }
    static int N;
    static Queue<Integer> room; //강의실 개수 (끝나는 시간 기준 정렬)
    static Queue<Class> classes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classes = new PriorityQueue<>();
        room = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classes.offer(new Class(idx, start, end));
        }
        room.offer(classes.poll().end); //첫번째 강의 끝나는 시간 넣기

        while(!classes.isEmpty()) {
            Class now = classes.poll();
            int roomCnt = room.size();
            boolean switchClass = false;
            for(int i=0; i<roomCnt; i++) {
                if(room.peek() <= now.start) { //현재 강의가 끝나고 다음 강의 진행 가능
                    room.poll();
                    room.offer(now.end);
                    switchClass = true;
                    break;
                }
            }
            if(!switchClass) { //현재 강의와 겹치는 시간 = 강의실 추가
                room.offer(now.end);
            }
        }

        System.out.println(room.size());
    }
}
