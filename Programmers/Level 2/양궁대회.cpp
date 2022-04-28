#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> ryanScore(11, 0);
int maxDif = 0;
int maxScore = 0;

bool cmp(vector<int> ryan) {
	for (int i = 10; i >= 0; i--) {
		if (ryanScore[i] < ryan[i]) {
			return true;
		}
		else if (ryanScore[i] > ryan[i]) {
			return false;
		}
	}
}

void calc(vector<int> info, vector<int> ryan) {
	int sumAppeach = 0;
	int sumRyan = 0;
	for (int i = 0; i < 11; i++) {
		if (ryan[i] > info[i]) {
			sumRyan += (10 - i);
		}
		else if (info[i] > 0) {
			sumAppeach += (10 - i);
		}
	}
	if (sumRyan > sumAppeach) {
		cout << sumRyan << " " << sumAppeach << "\n";
	}
	int dif = sumRyan - sumAppeach;
	if (dif > 0 && maxDif <= dif) {
		if (maxDif == dif && !cmp(ryan)) {
			return;
		}
		maxDif = dif;
		maxScore = sumRyan;
		ryanScore = ryan;
	}
}

void getScore(int idx, int n, vector<int> info, vector<int> ryan) {
	if (n == 0 || idx == 11) {
		ryan[10] += n;
		calc(info, ryan);
		ryan[10] -= n;
		return;
	}

	if (info[idx] < n) {
		ryan[idx] += info[idx] + 1;
		getScore(idx + 1, n - (info[idx] + 1), info, ryan);
		ryan[idx] -= info[idx] + 1;
	}
	getScore(idx + 1, n, info, ryan);
}

vector<int> solution(int n, vector<int> info) {
	vector<int> answer;
	vector<int> ryan(11, 0);
	getScore(0, n, info, ryan);

	bool flag = false;
	for (int i = 0; i < 11; i++) {
		if (ryanScore[i] != 0) {
			flag = true;
		}
	}
	if (flag) {
		for (int i = 0; i < 11; i++) {
			answer.push_back(ryanScore[i]);
		}
	}
	else {
		answer.push_back(-1);
	}
	return answer;
}

int main(void) {
	int n = 9;
	vector<int> info = { 0,0,1,2,0,1,1,1,1,1,1 };
	vector<int> result = solution(n, info);
	cout << maxScore << "\n";
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}