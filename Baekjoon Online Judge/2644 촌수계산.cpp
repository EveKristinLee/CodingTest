#include <iostream>

using namespace std;

int n; //전체 사람수
int m; //관계의 개수
int p1, p2; //촌수를 계산해야 하는 두사람

int relation[101][101] = { 0, };
bool visit[101] = { false, };

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int cnt = -1; //촌수

bool isInside(int x, int y) {
	return (x > 0) && (x <= n) && (y > 0) && (y <= n);
}

void dfs(int x, int depth) {
	if (x == p2) {
		cnt = depth;
		return;
	}

	for (int i = 1; i <= n; i++)
	{
		if (relation[x][i] == 1 && !visit[i]) {
			visit[i] = 1;
			dfs(i, depth + 1);
		}
	}
	

}


int main(void) {

	cin >> n;
	cin >> p1 >> p2;
	cin >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		relation[a][b] = 1;
		relation[b][a] = 1;
	}

	dfs(p1, 0);
	

	cout << cnt << "\n";
	

	return 0;
}