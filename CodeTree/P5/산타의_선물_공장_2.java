package P5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 산타의_선물_공장_2 {
    public static class Box {
        int id;
        int front;
        int back;

        public Box(int id, int front, int back) {
            this.id = id;
            this.front = front;
            this.back = back;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "id=" + id +
                    ", front=" + front +
                    ", back=" + back +
                    '}';
        }
    }
    public static class Belt {
        int id;
        int cnt;
        Box head;
        Box tail;

        public Belt(int id, int cnt, Box head, Box tail) {
            this.id = id;
            this.cnt = cnt;
            this.head = head;
            this.tail = tail;
        }
    }
    public static int N; //벨트 수
    public static int M; //박스의 수
    public static Belt[] belts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine()); //명령의 개수
        for(int q=0; q<Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            switch (order) {
                case 100: //초기화
                    init(st);
                    break;
                case 200: //물건 모두 옮기기
                    move_all(st);
                    break;
                case 300: //앞 물건만 교체하기
//                    move_front(st);
                    break;
                case 400: //물건 나누기
//                    box_divide(st);
                    break;
                case 500: //선물 정보 얻기
//                    get_box(st);
                    break;
                case 600: //벨트 정보 얻기
//                    get_belt(st);
                    break;
            }
        }
    }

    private static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        belts = new Belt[N+1];
        for(int i=1; i<=N; i++) {
            belts[i] = new Belt(i, 0,null, null);
        }
        for(int i=1; i<=M; i++) {
            int bN = Integer.parseInt(st.nextToken());
            belts[bN].cnt++;
            if(belts[bN].head == null) {
                Box box = new Box(i, -1, -1);
                belts[bN].head = box;
                belts[bN].tail = box;
            }
            else {
                Box nowFront = belts[bN].tail;
                Box box = new Box(i, nowFront.id, -1);
                nowFront.back = box.id;
                belts[bN].tail = box;
            }
        }

//        for(int i=1; i<=N; i++) {
//            System.out.println(i+"번 벨트 : " + belts[i].cnt);
//        }
    }

    private static int move_all(StringTokenizer st) {
        int m_src = Integer.parseInt(st.nextToken());
        int m_dst = Integer.parseInt(st.nextToken());
        Box o_dst_head = belts[m_dst].head;
        Box o_dst_tail = belts[m_dst].tail;
        Box o_src_head = belts[m_src].head;
        Box o_src_tail = belts[m_src].tail;
        belts[m_src].head = null;
        belts[m_src].tail = null;
        belts[m_dst].head = o_src_head; //
//        o_dst_head.front = o_

        return 0;
    }
}
