package solving.solve_1005;
//BOJ G1 최솟값과 최댓값

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2357 {
    private static class MinSegmentTree {
        long[] tree;
        int treeSize;

        public MinSegmentTree(int arrSize) {
            int height = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            this.treeSize = (int) Math.pow(2, height+1);
            this.tree = new long[treeSize];
        }

        public long init(int[] arr, int node, int start, int end) {
            if(start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            long leftNode = init(arr, node*2, start, mid);
            long rightNode = init(arr, node*2+1, mid+1, end);
            return tree[node] = Math.min(leftNode, rightNode);
        }

        public long getMin(int node, int start, int end, int left, int right) {
            if(left > end || right < start) {
                return MAX;
            }

            if(left <= start && right >= end) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long leftNode = getMin(node*2, start, mid, left, right);
            long rightNode = getMin(node*2+1, mid+1, end, left, right);
            return Math.min(leftNode, rightNode);
        }
    }
    private static class MaxSegmentTree {
        long[] tree;
        int treeSize;

        public MaxSegmentTree(int arrSize) {
            int height = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            this.treeSize = (int) Math.pow(2, height+1);
            this.tree = new long[treeSize];
        }

        public long init(int[] arr, int node, int start, int end) {
            if(start == end) {
                return tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            long leftNode = init(arr, node*2, start, mid);
            long rightNode = init(arr, node*2+1, mid+1, end);
            return tree[node] = Math.max(leftNode, rightNode);
        }

        public long getMax(int node, int start, int end, int left, int right) {
            if(left > end || right < start) {
                return MIN;
            }

            if(left <= start && right >= end) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long leftNode = getMax(node*2, start, mid, left, right);
            long rightNode = getMax(node*2+1, mid+1, end, left, right);
            return Math.max(leftNode, rightNode);
        }
    }
    static final long MAX = Long.MAX_VALUE;
    static final long MIN = Long.MIN_VALUE;
    static int N;
    static int M;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N+1];
        MinSegmentTree minSegmentTree = new MinSegmentTree(N);
        MaxSegmentTree maxSegmentTree = new MaxSegmentTree(N);
        for(int i=1; i<=N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        minSegmentTree.init(nums, 1, 1, N);
        maxSegmentTree.init(nums, 1, 1, N);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long minValue = minSegmentTree.getMin(1, 1, N, a, b);
            long maxValue = maxSegmentTree.getMax(1, 1, N, a, b);
            System.out.println(minValue + " " + maxValue);
        }
    }
}
