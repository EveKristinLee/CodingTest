package solving.solve_1005;
//BOJ G1 11505 구간 곱 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11505 {
    public static class SegmentTree {
        long[] tree;
        int treeSize;

        public SegmentTree(int arrSize) {
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            this.treeSize = (int) Math.pow(2, h+1);
            this.tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end) {
            if(start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            return tree[node] =
                    (init(arr, node*2, start, mid) *
                            init(arr, node*2+1, mid+1, end)) % MOD;
        }

        public long update(int node, int start, int end, int idx, long num) {
            if(idx < start || idx > end) {
                return tree[node];
            }

            if(start == end && start == idx) {
                return tree[node] = num;
            }

            int mid = (start + end) / 2;
            return tree[node] = (update(node*2, start, mid, idx, num) *
                    update(node*2+1, mid+1, end, idx, num)) % MOD;
        }

        public long getResult(int node, int start, int end, int left, int right) {
            if(left > end || right < start) {
                return 1;
            }

            if(left <= start && right >= end) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            return (getResult(node*2, start, mid, left, right) *
                    getResult(node*2+1, mid+1, end, left, right)) % MOD;
        }
    }
    static final int MOD = 1_000_000_007;
    static int N;
    static int M;
    static int K;
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
                segmentTree.update(1, 1, N, b, c);
                nums[b] = c;
            }
            if(a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                System.out.println(segmentTree.getResult(1, 1, N, b, c));
            }
        }
    }
}
