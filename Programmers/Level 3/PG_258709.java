//Programmers level3 258709 주사위 고르기

import java.util.*;

public class PG_258709 {
    static int cnt; //주사위의 개수
    static int[] select; //선택한 주사위의 조합
    static int combiCnt;
    static Map<Integer, List<Integer>> diceSumMap; //조합에 대한 주사위합 경우의 수
    static Map<Integer, Integer[]> diceCombiMap; //조합에 대한 경우의 수
    static List<Integer> sum;

    public static int[] solution(int[][] dice) {
        cnt = dice.length;
        int[] answer = new int[cnt/2];
        int res = -1;
        int maxCnt = 0;
        select = new int[cnt/2];
        combiCnt = 0;
        diceSumMap = new HashMap<>();
        diceCombiMap = new HashMap<>();
        combi(0, 0, dice);

        //이기는 경우 구하기
        int Aidx = 0;
        int Bidx = combiCnt -1;
        while(Aidx < combiCnt && Bidx >= 0) {
            int listCnt = sum.size();
            int AwinCnt = 0;
            int BwinCnt = 0;
            List<Integer> Aarr = diceSumMap.get(Aidx);
            List<Integer> Barr = diceSumMap.get(Bidx);

            for(int i=0; i<listCnt; i++) {
                int number = Aarr.get(i);

                int front = 0;
                int end = listCnt-1;
                while(front<=end) {
                    int mid = (front + end) / 2;

                    if(Barr.get(mid) < number) {
                        front = mid + 1;
                    }
                    else {
                        end = mid - 1;
                    }
                }

                AwinCnt += front;
            }
            if(maxCnt < AwinCnt) {
                maxCnt = AwinCnt;
                res = Aidx;
            }
            Aidx += 1;
            Bidx -= 1;
        }

        Integer[] nowCombi = diceCombiMap.get(res);
        for(int i=0; i<cnt/2; i++) {
            answer[i] = nowCombi[i]+1;
        }

        return answer;
    }

    private static void combi(int idx, int c, int[][] dice) {
        if(c >= cnt/2) {
            diceCombiMap.put(combiCnt, Arrays.stream(select).boxed().toArray(Integer[]::new));
            //승률 계산
            sum = new ArrayList<>();
            calcSum(0, 0, dice);
            Collections.sort(sum);
            diceSumMap.put(combiCnt, sum);
            combiCnt += 1;
            return;
        }

        for(int i=idx; i<cnt; i++) {
            select[c] = i;
            combi(i+1, c+1, dice);
        }
    }

    private static void calcSum(int depth, int sumNow, int[][] dice) {
        if(depth >= cnt/2) {
            sum.add(sumNow);
            return;
        }

        for(int i=0; i<6; i++) {
            calcSum(depth+1, sumNow + dice[select[depth]][i], dice);
        }
    }


    public static void main(String[] args) {
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};
        int[] result = solution(dice);
        System.out.println(Arrays.toString(result));
    }
}
