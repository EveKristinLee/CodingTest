#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> answers) {
	vector<int> answer;
	vector<int> sol1 = { 1, 2, 3, 4, 5 };
	vector<int> sol2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
	vector<int> sol3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
	int score[3] = { 0, };
	int maxScore = 0;

	for (int i = 0; i < answers.size(); i++) {
		if (answers[i] == sol1[i % 5]) {
			score[0]++;
		}
		if (answers[i] == sol2[i % 8]) {
			score[1]++;
		}
		if (answers[i] == sol3[i % 10]) {
			score[2]++;
		}
	}
	maxScore = max(max(score[0], score[1]), score[2]);
	for (int i = 0; i < 3; i++) {
		if (score[i] == maxScore) {
			answer.push_back(i + 1);
		}
	}

	return answer;
}

int main(void) {
	vector<int> ans = solution({ 1, 3, 2, 4, 2 });
	for (int i = 0; i < ans.size(); i++) {
		cout << ans[i] << " ";
	}
	return 0;
}