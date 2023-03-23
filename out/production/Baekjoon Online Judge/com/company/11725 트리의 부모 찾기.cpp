#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int N; //����� ����

vector<int> tree[100001];
int parent[100001] = { 0, }; //����� �θ� ����
bool visit[100001] = { false, }; //�θ� ã�� ���� �湮

void bfs(int x) {
	queue<int> q;
	q.push(x);
	visit[x] = true;

	while (!q.empty()) {
		x = q.front();
		q.pop();

		for (int i = 0; i < tree[x].size(); i++) {
			int nx = tree[x][i];
			if (!visit[nx]) {
				q.push(nx);
				visit[nx] = true;
				parent[nx] = x;
			}
		}
	
	}
}

int main(void) {
	cin >> N;
	for (int i = 0; i < N - 1; i++) {
		int a, b;
		cin >> a >> b;
		tree[a].push_back(b);
		tree[b].push_back(a);
	}

	bfs(1);
	for (int i = 2; i <= N; i++) {
		cout << parent[i] << "\n";
	}
	return 0;
}