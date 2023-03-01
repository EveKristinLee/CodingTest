package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

//BOJ G3
public class 개미굴_14725 {
    static int N;

    static class TrieNode {
        Map<String, TrieNode> child = new TreeMap<>();
    }
    static class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        void insert(String[] words) {
            TrieNode thisNode = root;
            for (String word : words) {
                thisNode = thisNode.child.computeIfAbsent(word, s -> new TrieNode());
            }
        }
    }
    static void show(TrieNode cur, int depth) {
        for(String key : cur.child.keySet()) {
            for(int i=1; i<depth; i++) {
                System.out.print("--");
            }
            System.out.println(key);
            show(cur.child.get(key), depth+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //루트 생성
        Trie t = new Trie();
        for(int i=0; i<N; i++) {
            String[] in = br.readLine().split(" ");
            int cnt = Integer.parseInt(in[0]);
            String[] s = new String[cnt];
            System.arraycopy(in, 1, s, 0, cnt);
            t.insert(s);
        }

        TrieNode curNode = t.root;
        for(String key : curNode.child.keySet()) {
            System.out.println(key);
            show(curNode.child.get(key), 2);
        }
    }
}
