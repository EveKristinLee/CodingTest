//BOJ G5
package solving.solve_0914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동물원_12907 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N];
        int[] ani_cnt = new int[41];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            ani_cnt[height[i]]++;
        }

        int res = 1;
        boolean one = false;
        int cnt = 0;
        for(int i=0; i<N; i++) {
            if(ani_cnt[i] == 2) {
                if(!one) { //같은 키가 둘 이상일때는 이전에 같은 키가 하나인 적이 없어야 한다.
                    res *= 2;
                }
                else { //findOne이 이미 true라면 현재보다 작은 키가 1인 경우이므로 균형 안맞음. break해서 cnt값 갱신 안됨
                    break;
                }
            }
            else if(ani_cnt[i] == 1) {
                one = true;
            }
            else { //중간에 0이 나오거나 3이상이면 균형 안맞음
                break;
            }
            cnt += ani_cnt[i];
        }

        if(one) {
            res *= 2; //가장 큰 동물이 토끼와 고양이일 경우 두가지
        }
        if(cnt != N) {
            res = 0; //반복문 동안 확인한 동물의 수가 N과 같지 않으면 주어진 동물의 수보다 큰 값들이 입력된 경우
        }
        System.out.println(res);
    }
}
