#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
	int answer = 0;
	queue<pair<int, int>> q;
	priority_queue<int> pq;
	for (int i = 0; i <priorities.size(); i++) {
		q.push(make_pair( priorities[i], i ));
		pq.push(priorities[i]);
	}
	while (!q.empty()) {
		pair<int, int> tmp = q.front();
		q.pop();
		if (tmp.first < pq.top()) {
			q.push(tmp);
		}
		else {
			pq.pop();
			answer++;
			if (tmp.second == location) {
				break;
			}
		}
	}
	return answer;
}

int main(void) {
	vector<int> priorities = { 1, 1, 9, 1, 1, 1 };
	int location = 0;
	int result = solution(priorities, location);
	cout << result << "\n";
	return 0;
}