#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool visit[51] = { false };
vector<int> cntList;
int answer = 98765;

bool oneWord(string word1, string word2) {
	int cntWord = 0;
	for (int i = 0; i < word1.size(); i++) {
		if (word1[i] != word2[i]) {
			cntWord++;
		}
	}
	if (cntWord == 1) {
		return true;
	}
	else {
		return false;
	}
}

void dfs(string word, string target, vector<string> words, int cnt) {
	if (word.compare(target) == 0) {
		answer = min(cnt, answer);
		return;
	}
	for (int i = 0; i < words.size(); i++) {
		if (!visit[i]) {
			if (oneWord(word, words[i])) {
				visit[i] = true;
				dfs(words[i], target, words, cnt + 1);
				visit[i] = false;
			}
		}
	}
}

int solution(string begin, string target, vector<string> words) {
	dfs(begin, target, words, 0);
	if (answer == 98765) {
		//answer가 안바뀌면 변환할 수 없는 경우
		return 0;
	}
	return answer;
}

int main(void) {
	string begin = "hit";
	string target = "cog";
	vector<string> words = { "hot", "dot", "dog", "lot", "log"};

	cout << solution(begin, target, words) << "\n";

	return 0;
}