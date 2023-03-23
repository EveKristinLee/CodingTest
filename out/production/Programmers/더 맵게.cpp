#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

int solution(vector<int> scoville, int K) {
	int answer = 0;
	priority_queue<int, vector<int>, greater<int>> pq;
	for (int i = 0; i < scoville.size(); i++) {
		pq.push(scoville[i]);
	}
	while (!pq.empty()) {
		int f = pq.top();
		pq.pop();
		if (f < K) {
			if (!pq.empty()) {
				int s = pq.top();
				pq.pop();
				pq.push(f + (s * 2));
				answer++;
			}
			else {
				return -1;
			}
		}
		else {
			break;
		}
	}
	return answer;
}

int main(void) {
	vector<int> scoville = { 1, 2, 3, 9, 10, 12 };
	int K = 7;
	int result = solution(scoville, K);
	cout << result << "\n";
	return 0;
}