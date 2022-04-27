#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int map[101][101] = { 0 };

int rotation(int x1, int y1, int x2, int y2) {
	int dir = 1; //오른쪽
	int origin = map[x1][y1];
	int minNum = 987654321;
	if (dir == 1) {
		for (int i = y1; i < y2; i++) {
			int tmp = map[x1][i+1];
			map[x1][i + 1] = origin;
			origin = tmp;
			minNum = min(minNum, map[x1][i + 1]);
			if (i == y2 - 1) {
				dir = 2; //아래
			}
		}
	}
	if (dir == 2) {
		for (int i = x1; i < x2; i++) {
			int tmp = map[i + 1][y2];
			map[i + 1][y2] = origin;
			origin = tmp;
			minNum = min(minNum, map[i + 1][y2]);
			if (i == x2 - 1) {
				dir = 3; //왼쪽
			}
		}
	}
	if (dir == 3) {
		for (int i = y2; i > y1; i--) {
			int tmp = map[x2][i - 1];
			map[x2][i - 1] = origin;
			origin = tmp;
			minNum = min(minNum, map[x2][i - 1]);
			if (i == y1 + 1) {
				dir = 4;
			}
		}
	}
	if (dir == 4) {
		for (int i = x2; i > x1; i--) {
			int tmp = map[i - 1][y1];
			map[i - 1][y1] = origin;
			origin = tmp;
			minNum = min(minNum, map[i - 1][y1]);
		}
	}
	return minNum;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
	vector<int> answer;
	N = rows;
	M = columns;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			map[i][j] = ((i - 1) * columns) + j;
		}
	}

	for (auto q : queries) {
		int minNum = rotation(q[0], q[1], q[2], q[3]);
		answer.push_back(minNum);
	}
	return answer;
}

int main(void) {
	int rows = 3;
	int columns = 3;
	vector<vector<int>> queries = { {1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3} };
	vector<int> result = solution(rows, columns, queries);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}