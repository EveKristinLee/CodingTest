#include <iostream>
#include <queue>

using namespace std;

int N; // 시작 위치
int K; // 종료 위치

int visit[100001] = { false, };

int sec = 0; //최소 시간

void bfs(int N, int K) {
	queue<pair<int, int>> q;
	q.push(make_pair(N, 0));
	visit[N] = true;

	while (!q.empty()) {
		int curPos = q.front().first;
		int cusSec = q.front().second;
		q.pop();

		//목적지에 도달
		if (curPos == K) {
			sec = cusSec;
			return;
		}

		//이동할 수 있는 세가지 경우의 수
		if (curPos - 1 >= 0 && !visit[curPos - 1]) {
			q.push(make_pair(curPos - 1, cusSec + 1));
			visit[curPos - 1] = true;
		}
		if (curPos + 1 < 100001 && !visit[curPos + 1]) {
			q.push(make_pair(curPos + 1, cusSec + 1));
			visit[curPos + 1] = true;
		}
		if (curPos * 2 < 100001 && !visit[curPos * 2]) {
			q.push(make_pair(curPos * 2, cusSec + 1));
			visit[curPos * 2] = true;
		}
	}

}

int main(void) {

	cin >> N >> K;
	bfs(N, K);

	cout << sec << "\n";

	return 0;
}