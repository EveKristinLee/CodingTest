#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(pair<int, double> a, pair<int, double> b) {
	if (a.second == b.second) {
		return a.first < b.first;
	}
	return a.second > b.second;
}

vector<int> solution(int N, vector<int> stages) {
	vector<int> answer;
	vector<pair<int, double>> game;
	double person = stages.size();
	double minusPerson = 0;
	int nowStage = 1;
	while (nowStage <= N) {
		double cnt = 0;
		for (int i = 0; i < stages.size(); i++) {
			if (stages[i] == nowStage) {
				cnt++;
			}
		}
		double fail = 0;
		if (person - minusPerson != 0) {
			fail = cnt / (person - minusPerson);
		}
		game.push_back({ nowStage, fail });
		minusPerson += cnt;
		nowStage++;
	}
	sort(game.begin(), game.end(), cmp);
	for (int i = 0; i < game.size(); i++) {
		answer.push_back(game[i].first);
	}
	return answer;
}

int main(void) {
	int n = 4;
	vector<int> stages = { 4,4,4,4,4 };
	vector<int> result = solution(n, stages);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}