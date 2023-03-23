#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int getTime(int progress, int speed) {
	int answer = 0;
	int tmp = 100 - progress;
	while (tmp > 0) {
		tmp -= speed;
		answer++;
	}
	return answer;
}

vector<int> solution(vector<int> progresses, vector<int> speeds) {
	vector<int> answer;
	queue<int> time;
	for (int i = 0; i < progresses.size(); i++) {
		time.push(getTime(progresses[i], speeds[i]));
	}

	while (!time.empty()) {
		int cnt = 1;
		int current = time.front();
		time.pop();
		while (!time.empty() && current >= time.front()) {
			cnt++;
			time.pop();
		}
		answer.push_back(cnt);
	}
	return answer;
}

int main(void) {
	vector<int> progresses = { 93, 30, 55 };
	vector<int> speeds = { 1, 30, 5 };
	vector<int> result = solution(progresses, speeds);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}