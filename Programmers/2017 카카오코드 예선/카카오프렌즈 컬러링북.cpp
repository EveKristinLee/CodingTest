#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int M;
int N;
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };
bool visit[101][101];
int nowCnt = 0;

bool isInside(int x, int y) {
	return (x >= 0) && (y >= 0) && (x < M) && (y < N);
}

void dfs(int x, int y, int now, vector<vector<int>> picture) {
	if (picture[x][y] != now) {
		return;
	}
	nowCnt++;
	visit[x][y] = true;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && !visit[nx][ny] && picture[nx][ny] != 0) {
			dfs(nx, ny, picture[x][y], picture);
		}
	}
	
}

vector<int> solution(int m, int n, vector<vector<int>> picture) {
	int number_of_area = 0;
	int max_size_of_one_area = 0;

	M = m;
	N = n;
	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			visit[i][j] = false;
		}
	}
	
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!visit[i][j] && picture[i][j] != 0) {
				nowCnt = 0;
				dfs(i, j, picture[i][j], picture);
				number_of_area++;
				max_size_of_one_area = max(max_size_of_one_area, nowCnt);
			}
		}
	}

	vector<int> answer(2);
	answer[0] = number_of_area;
	answer[1] = max_size_of_one_area;
	return answer;
}

int main(void) {
	int m = 6;
	int n = 4;
	vector<vector<int>> picture = { {1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3} };
	vector<int> result = solution(m, n, picture);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}
	return 0;
}