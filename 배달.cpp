#include <iostream>
#include <vector>
#include <queue>

using namespace std;
#define INF 987654321;

int d[51] = { 0 };
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

void dijkstra(int N, vector<vector<int> > road) {
	for (int i = 1; i <= N; i++) {
		d[i] = INF;
	}

	d[1] = 0;
	pq.push(make_pair(d[1], 1));

	while (!pq.empty()) {
		int dx = pq.top().first;
		int idx = pq.top().second;
		pq.pop();
		for (int i = 0; i < road.size(); i++) {
			if (road[i][0] == idx) {
				if (d[idx] + road[i][2] < d[road[i][1]]) {
					d[road[i][1]] = d[idx] + road[i][2];
					pq.push(make_pair(d[road[i][1]], road[i][1]));
				}
			}
			else if (road[i][1] == idx) {
				if (d[idx] + road[i][2] < d[road[i][0]]) {
					d[road[i][0]] = d[idx] + road[i][2];
					pq.push(make_pair(d[road[i][0]], road[i][0]));
				}
			}
		}
	}
}

int solution(int N, vector<vector<int> > road, int K) {
	int answer = 0;

	dijkstra(N, road);
	for (int i = 1; i <= N; i++) {
		if (d[i] <= K) {
			answer++;
		}
	}
	return answer;
}

int main(void) {
	int N = 6;
	vector<vector<int> > road;
	road = { {1, 2, 1}, {1, 3, 2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {6, 5, 1} };
	int K = 4;

	int result = solution(N, road, K);
	cout << result << "\n";

	return 0;
}