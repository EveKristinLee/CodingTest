#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int V; //정점의 개수
int E; //간선의 개수

vector<int> link[20001];
int visit[20001] = { 0, };

void clear() {
	for (int i = 1; i <= V; i++) {
		link[i].clear();
		visit[i] = 0;
	}
}

void bfs(int start) {
	queue<int> q;
	q.push(start);
	visit[start] = 1; //1 = Red, 2 = Blue
	
	while (!q.empty()) {
		int x = q.front();
		q.pop();

		for (int i = 0; i < link[x].size(); i++) {
			//빨강이라면
			if (visit[x] == 1) {
				//연결된 곳은 파랑으로 색칠
				if (visit[link[x][i]] == 0) {
					q.push(link[x][i]);
					visit[link[x][i]] = 2;
				}
			}
			//파랑이라면
			else if (visit[x] == 2) {
				//연결된 곳은 빨강으로 색칠
				if (visit[link[x][i]] == 0) {
					q.push(link[x][i]);
					visit[link[x][i]] = 1;
				}
			}
		}
	}
}

bool isBinary() {
	//인접한 곳이 색이 다른지 확인
	for (int i = 1; i <= V; i++) {
		for (int j = 0; j < link[i].size(); j++) {
			//인접한 곳이 색이 같으면 이분 그래프 x
			if (visit[i] == visit[link[i][j]]) {
				return false;
			}
		}
	}
	return true;
}

int main(void) {
	int K; //테스트 케이스
	cin >> K;
	for (int k = 0; k < K; k++) {
		cin >> V >> E;
		//초기화
		clear();
		for (int i = 1; i <= E; i++) {
			int a, b;
			cin >> a >> b;
			link[a].push_back(b);
			link[b].push_back(a);
		}

		for (int i = 1; i <= V; i++) {
			if (visit[i] == 0) {
				bfs(i);
			}
		}

		if (isBinary()) {
			cout << "YES" << "\n";
		}
		else {
			cout << "NO" << "\n";
		}

	}

	return 0;
}