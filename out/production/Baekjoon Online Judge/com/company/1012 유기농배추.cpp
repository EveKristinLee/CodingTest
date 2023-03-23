#include <iostream>

using namespace std;

int T; //�׽�Ʈ���̽� ����
int M; //���α���
int N; //���α���
int K; //���߰� �ɾ��� �ִ� ��ġ�� ����

int map[51][51] = { 0, };
bool visit[51][51] = { false, };

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int cnt = 0; // ���߹��� ���� ��


void printMap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cout << map[i][j] << " ";
		}
		cout << "\n";
	}
}

void initMap() {
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = 0;
			visit[i][j] = false;
		}
	}
}

bool isInside(int x, int y) {
	return (x >= 0) && (x < M) && (y >= 0) && (y < N);
}

void dfs(int x, int y) {
	visit[x][y] = true;

	if (map[x][y] == 0) {
		return;
	}

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && visit[nx][ny] == false) {
			dfs(nx, ny);
		}
	}

}


int main(void) {
	cin >> T;

	for (int t = 0; t < T; t++) {
		initMap();
		cnt = 0;

		cin >> M >> N >> K;
		for (int i = 0; i < K; i++) {
			int x, y;
			cin >> x >> y;
			map[x][y] = 1;
		}
		
		
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visit[i][j] == false) {
					cnt++;
					dfs(i, j);
				}
				
			}
		}
		cout << cnt << "\n";
	}

	return 0;
}