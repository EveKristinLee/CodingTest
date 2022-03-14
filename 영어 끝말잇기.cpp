#include <string>
#include <vector>
#include <iostream>

using namespace std;

//같은 단어 있으면 return true
bool dupChk(string word, vector<string> words) {
	for (int i = 0; i < words.size(); i++) {
		if (words[i] == word) {
			return true;
		}
	}
	return false;
}

//앞 단어로 시작한 단어인지 확인 
bool endStartSame(string front, string back) {
	if (front[front.size() - 1] == back[0]) {
		return true;
	}
	return false;
}

//두글자 이상 단어이면 return true
bool isOne(string word) {
	if (word.size() > 1) {
		return true;
	}
	return false;
}

vector<int> solution(int n, vector<string> words) {
	vector<int> answer;
	vector<string> wordChain;

	string firstString = words[0];
	wordChain.push_back(firstString);
	if (isOne(firstString)) {
		for (int i = 1; i < words.size(); i++) {
			if (!endStartSame(words[i - 1], words[i]) || dupChk(words[i], wordChain) || !isOne(words[i])) {
				int num = (i + 1) % n;
				int idx = ((i + 1) / n) + 1;
				if (num == 0) {
					num = n;
					idx = ((i + 1) / n);
				}
				answer.push_back(num);
				answer.push_back(idx);
				return answer;
			}
			wordChain.push_back(words[i]);
		}
	}

	answer.push_back(0);
	answer.push_back(0);
	return answer;
}

int main(void) {
	int n = 2;
	vector<string> words = { "qwe", "eqwe", "eqwe" };

	vector<int> result = solution(n, words);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}
	return 0;
}