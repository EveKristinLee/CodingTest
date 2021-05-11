#include <iostream>
#include <queue>


using namespace std;


queue<int> q;

int N; //정점의 개수
int M; //간선의 개수
int V; //탐색을 시작할 정점의 번호

bool visit[1001] = { false };
bool graph[1001][1001] = { false };


void initVisit() {
	for (int i = 1; i <= N; i++) {
		visit[i] = 0;
	}
}

void dfs(int x) {
	cout << x << " ";
	visit[x] = 1;

	for (int i = 1; i <= N; i++) {
		if (graph[x][i] == 1 && visit[i] != true) {
			dfs(i);
		}
	}
}


void bfs(int x) {
	q.push(x);
	visit[x] = true;
	cout << x << " ";

	while (!q.empty()) {
		x = q.front();
		q.pop();

		for (int i = 1; i <= N; i++) {
			if (graph[x][i] == 1 && visit[i] != true) {
				q.push(i);
				visit[i] = true;
				cout << i << " ";
			}
		}
	}
}



int main(void) {
	cin >> N >> M >> V;
	for (int i = 0; i < M; i++) {
		int a;
		int b;
		cin >> a >> b;
		graph[a][b] = 1;
		graph[b][a] = 1;
	}

	dfs(V);
	initVisit();
	cout << "\n";
	bfs(V);
	return 0;
}