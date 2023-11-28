package solving.solve_1005;
//BOJ G1 구간 합 구하기 - 누적합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
    private static class SegmentTree {
        long[] tree;
        int treeSize;

        public SegmentTree(int arrSize) {
            //트리 높이 구하기
            int hegiht = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

            //배열의 size = 2^(height+1)
            this.treeSize = (int) Math.pow(2, hegiht+1);

            this.tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end) {
            //배열의 시작과 끝이 같다면 leaf 노드
            if(start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            //leaf 노드가 아니면, 현재 node의 자식 노드 합 구하기
            return tree[node] =
                    init(arr, node*2, start, mid) +
                            init(arr, node*2+1, mid+1, end);
        }

        public void update(int node, int start, int end, int idx, long diff) {
            //변경할 idx가 범위안에 없다면
            if(idx < start || idx > end) {
                return;
            }

            //차이 갱신
            tree[node] += diff;

            //리프 노트가 아니라면 자식 노드들도 갱신
            int mid = (start + end) / 2;
            if(start != end) {
                update(node*2, start, mid, idx, diff);
                update(node*2+1, mid+1, end, idx, diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right) {
            //범위를 벗어날 경우
            if(left > end || right < start) {
                return 0;
            }

            //범위를 완전히 포함할때는 더 내려가지 않고 리턴
            if(left <= start && right >= end) {
                return tree[node];
            }

            //리프노드 반복 탐색
            int mid = (start + end) / 2;
            return sum(node*2, start, mid, left, right) +
                    sum(node*2+1, mid+1, end, left, right);
        }
    }

    static int N; //수의 개수
    static int M; //수의 변경 횟수
    static int K; //구간합을 구하는 횟수
    static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new long[N+1];
        SegmentTree segmentTree = new SegmentTree(N);
        for(int i=1; i<=N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        segmentTree.init(nums, 1, 1, N);
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a == 1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                long diff = c - nums[b];
                segmentTree.update(1, 1, N, b, diff);
                nums[b] = c;
            }
            if(a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                System.out.println(segmentTree.sum(1, 1, N, b, c));
            }
        }
    }
}
