#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int N;
int M;
int totalCnt = 0;

bool isInside(int x, int y) {
	return (x >= 0) && (y >= 0) && (x < N) && (y < M);
}

bool cantMove(int x, int y, vector<vector<int>> board) {
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && board[nx][ny]) {
			return false;
		}
	}
	return true;
}

//CanWin, turn
pair<bool, int> dfs(int nowx, int nowy, int optx, int opty, vector<vector<int>> board) {
	if (cantMove(nowx, nowy, board)) {
		return { false, 0 };
	}

	pair<bool, int> ret = { false, 0 };
	if (board[nowx][nowy]) {
		int minT = 987654321;
		int maxT = 0;
		for (int i = 0; i < 4; i++) {
			int nx = nowx + dx[i];
			int ny = nowy + dy[i];

			if (!isInside(nx, ny) || !board[nx][ny]) {
				continue;
			}
			board[nowx][nowy] = 0;
			pair<bool, int> tmp = dfs(optx, opty, nx, ny, board);
			board[nowx][nowy] = 1;

			if (!tmp.first) {
				ret.first = true;
				minT = min(minT, tmp.second);
			}
			else if (!ret.first) {
				maxT = max(maxT, tmp.second);
			}
		}
		ret.second = ret.first ? minT + 1 : maxT + 1;
	}
	return ret;
}

int solution(vector<vector<int>> board, vector<int> aloc, vector<int> bloc) {
	N = board.size();
	M = board[0].size();

	return dfs(aloc[0], aloc[1], bloc[0], bloc[1], board).second;
}

int main(void) {
	vector<vector<int>> board = { {1, 1, 1}, {1, 1, 1}, {1, 1, 1} };
	vector<int> aloc = { 1, 0 };
	vector<int> bloc = { 1, 2 };
	int result = solution(board, aloc, bloc);
	cout << result << "\n";
	return 0;
}