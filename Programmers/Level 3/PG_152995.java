//Programmers level3 152995 인사고과

import java.util.*;
public class PG_152995 {

    private static class Score implements Comparable<Score> {
        int x;
        int y;
        public Score(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int compareTo(Score s) {
            if(this.x == s.x) { //근무태도가 같다면
                return this.y - s.y; //동료평가를 오름차순
            }
            return s.x - this.x; //근무태도를 내림차순
        }
    }

    public static int solution(int[][] scores) {
        int answer = 0;
        int cnt = scores.length;
        Score wh = new Score(scores[0][0], scores[0][1]);
        List<Score> sc = new ArrayList<>();

        for(int i=0; i<cnt; i++) {
            sc.add(new Score(scores[i][0], scores[i][1]));
        }
        Collections.sort(sc);

        //인센티브를 받을 수 있는지 여부 확인
        int maxScore = sc.get(0).y; //근무태도를 기준으로 내림차순 했으므로 동료 평가만 비교해주기
        for(int i=1; i<cnt; i++) {
            if(maxScore > sc.get(i).y) {
                if(sc.get(i).x == wh.x && sc.get(i).y == wh.y) { //완호가 인센 못받음
                    return -1;
                }
                //인센 못받음 처리하기
                sc.get(i).x = -1;
                sc.get(i).y = -1;
            }
            else { //동료 평가 갱신해주기
                maxScore = sc.get(i).y;
            }
        }

        Collections.sort(sc, new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                return (s2.x + s2.y) - (s1.x + s1.y);
            }
        }); //합으로 내림차순

        //완호의 등수 계산
        answer = 1;
        for(int i=0; i<cnt; i++) {
            if(sc.get(i).x + sc.get(i).y > wh.x + wh.y) {
                answer += 1;
            }
            else {
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] scores = {{2,2},{1,4},{3,2},{3,2},{2,1}};
        int res = solution(scores);
        System.out.println(res);
    }
}
