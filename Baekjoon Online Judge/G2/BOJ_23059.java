package solving_2.solve_12.solve_19;
//BOJ G2 23059 리그 오브 레게노

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23059 {
    static int N; //관계의 수
    static int[] rel;
    static boolean[] visit;
    static List<List<Integer>> list;
    static Map<Integer, String> itemName;
    static Map<String, Integer> itemIdx;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        itemName = new HashMap<>();
        itemIdx = new HashMap<>();
        list = new ArrayList<>();
        rel = new int[400_001];
        int idx = 1;
        list.add(new ArrayList<>()); //idx를 맞추기 위한 더미 값
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            if(!itemIdx.containsKey(b)) {
                list.add(new ArrayList<>());
                itemIdx.put(b, idx);
                itemName.put(idx, b);
                idx+=1;
            }
            if(!itemIdx.containsKey(a)) {
                list.add(new ArrayList<>());
                itemIdx.put(a, idx);
                itemName.put(idx, a);
                idx+=1;
            }
            int idxB = itemIdx.get(b);
            int idxA = itemIdx.get(a);
            list.get(idxA).add(idxB);
            rel[idxB]+=1;
        }

        sb = new StringBuilder();
        visit = new boolean[itemIdx.size()+1];
        Queue<Integer> q = new ArrayDeque<>();
        List<String> itemList = new ArrayList<>();
        for(int i=1; i<=itemIdx.size(); i++) {
            if(rel[i] == 0) {
                itemList.add(itemName.get(i));
            }
        }
        Collections.sort(itemList);
        for (String name : itemList) {
            int nowIdx = itemIdx.get(name);
            q.offer(nowIdx);
            visit[nowIdx] = true;
        }


        while(!q.isEmpty()) {
            int qSize = q.size();
            itemList = new ArrayList<>();
            for(int i=0; i<qSize; i++) {
                Integer now = q.poll();
                sb.append(itemName.get(now)).append("\n");

                for(int j=0; j<list.get(now).size(); j++) {
                    rel[list.get(now).get(j)]--;
                    if(rel[list.get(now).get(j)] == 0 && !visit[list.get(now).get(j)]) {
                        itemList.add(itemName.get(list.get(now).get(j)));
                    }
                }
            }
            Collections.sort(itemList);
            for (String name : itemList) {
                int nowIdx = itemIdx.get(name);
                q.offer(nowIdx);
                visit[nowIdx] = true;
            }
        }

        boolean buy = true;
        for(int i=1; i<=itemIdx.size(); i++) {
            if(!visit[i]) {
                buy = false;
                break;
            }
        }
        if(buy) {
            System.out.println(sb);
        }
        else {
            System.out.println(-1);
        }
    }
}
