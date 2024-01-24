package solving.solve_1028;
//BOJ G4 16434 드래곤 앤 던전

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16434 {
    private static class Room {
        long type; //1-몬스터, 2-포션
        long a; //공격력
        long h; //생명력

        public Room(long type, long a, long h) {
            this.type = type;
            this.a = a;
            this.h = h;
        }
    }
    static long N; //방의 개수
    static long OriginAtk; //용사 초기 공격력
    static long atk;
    static Room[] rooms;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        OriginAtk = Long.parseLong(st.nextToken());
        rooms = new Room[(int)N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            rooms[i] = new Room(t, a, h);
        }

        long front = 0;
        long end = 1_000_000L * 1_000_000L * 123_456L;
        while(front <= end) {
            long mid = (front + end) / 2L;
            atk = OriginAtk;

            if(enter(mid)) {
                ans = mid;
                end = mid - 1;
            }
            else {
                front = mid + 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean enter(long hp) {
        long nowHp = hp;
        for(int i=0; i<N; i++) {
            if(rooms[i].type == 1) {
                long cnt = rooms[i].h / atk; //때리는 횟수
                if(rooms[i].h % atk == 0) {
                    cnt--;
                }
                nowHp -= (cnt * rooms[i].a);
                if(nowHp <= 0) {
                    return false;
                }
            }
            else if(rooms[i].type == 2){
                atk += rooms[i].a;
                nowHp += rooms[i].h;
                if(nowHp > hp) {
                    nowHp = hp;
                }
            }
        }
        return true;
    }
}
