//BOJ G1
package solving.solve_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 멀티탭_스케줄링_1700 {
    //1. 각 용품의 사용 횟수를 초반에 저장해서 그 값으로 오름차순 정렬 해서 멀티탭에 자리가 없을 때마다 교체 => pq사용
    //   현재 시점에서 꽂혀있는 용품이 이후에 사용되는가 = 현재 idx에서 pq에 들어가면 cnt값 줄여주는걸로 잡을 수 있음
    //   멀티탭에 꽃혀있는 모든 용품이 이후에 사용되는가 = cnt로 정렬해주는걸로는 잡아줄 수 없음
    //   => 실패

    //2. 멀티탭에 자리가 없으면 현재 위치부터 맨 끝까지 현재 꽂혀있는 용품이 사용되는지 확인
    //   현재 꽃혀있는 용품 중 이후에 사용되지 않는 용품 빼기
    //   현재 꽃혀있는 용품 중 모든 용품이 이후에 사용된다면 가장 마지막에 사용되는 용품 빼기
    //   => 성공

    static int N; //멀티탭 구멍의 개수
    static int K; //총 사용 횟수
    static int[] products; //용품 사용 순서
    static int res;
    static boolean[] use; //용품 사용 여부
    static int cnt; //현재 멀티탭에 꽂혀있는 용품의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        products = new int[K];
        use = new boolean[K+1];
        res = 0;
        cnt = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            products[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<K; i++) {
            if(!use[products[i]]) { //사용하고자 하는 용품이 꽂혀있지 않다면
                if(cnt >= N) { //현재 멀티탭에 꽂혀있는 용품의 개수가 N보다 크다면
                    res++;
                    //지금 사용하고 있는 용품이 이후에도 사용되는지 확인
                    List<Integer> after = new ArrayList<>();
                    for(int j=i; j<K; j++) {
                        if(use[products[j]] && !after.contains(products[j])) {
                            after.add(products[j]);
                        }
                    }

                    //이후에도 사용하는 용품의 개수가 N보다 작으면 나머지 하나를 교체
                    if(after.size() < N) {
                        for(int j=0; j<K; j++) {
                            if(use[j+1] && !after.contains(j+1)) {
                                use[j+1] = false;
                                cnt--;
                                break;
                            }
                        }
                    }
                    else {//이후에 사용하는 용품의 개수가 N과 같으면 가장 나중에 사용되는 용품의 교체
                        use[after.get(after.size() -1)] = false;
                        cnt--;
                    }
                }
                //현재 사용할 용품의 사용 여부를 사용으로 바꾸기
                use[products[i]] = true;
                cnt++;
            }
        }
        System.out.println(res);
    }
}
