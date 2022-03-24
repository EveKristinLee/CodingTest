#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

//하, 우, 대각선
int dx[3] = { 0, 1, 1 };
int dy[3] = { 1, 0, 1 };
int M, N;

bool isInside(int x, int y) {
	return (x >= 0) && (y >= 0) && (x < M) && (y < N);
}

//4칸인지 확인 = true면 4칸
bool check4(int x, int y, vector<string> board) {
	for (int i = 0; i < 3; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (!isInside(nx, ny)) {
			return false;
		}
		if (board[x][y] != board[nx][ny]) {
			return false;
		}
	}
	return true;
}

int deleteBlock(vector<pair<int, int>> delBlock, vector<string> &board) {
	int cnt = 0;
	for (int i = 0; i < delBlock.size(); i++) {
		int x = delBlock[i].first;
		int y = delBlock[i].second;
		if (board[x][y] != '0') {
			board[x][y] = '0';
			cnt++;
		}

		for (int k = 0; k < 3; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (board[nx][ny] != '0') {
				board[nx][ny] = '0';
				cnt++;
			}
		}
	}
	return cnt;
}

void reArrange(vector<string> &board) {
	for (int i = 1; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (board[i][j] == '0') {
				//맨 위에서부터 다 땡겨옴
				for (int k = i; k > 0; k--) {
					if (board[k - 1][j] == '0') {
						break;
					}
					board[k][j] = board[k - 1][j];
					board[k - 1][j] = '0';
				}
			}
		}
	}
}

int solution(int m, int n, vector<string> board) {
	int answer = 0;
	M = m;
	N = n;
	bool flag = true;
	while (flag) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				cout << board[i][j] << " ";
			}
			cout << "\n";
		}
		vector<pair<int, int>> delBlock;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++){
				if (board[i][j] != '0') {
					if (check4(i, j, board)) {
						delBlock.push_back(make_pair(i, j));
					}
				}
			}
		}
		if (delBlock.empty()) {
			flag = false;
			break;
		}
		else {
			answer += deleteBlock(delBlock, board);
			cout << answer << "\n";
			reArrange(board);
		}
	}
	return answer;
}

int main(void) {
	int m = 7;
	int n = 2;
	vector<string> board = { "AA", "BB", "AA", "BB", "ZZ", "ZZ", "CC" };
	int result = solution(m, n, board);
	cout << result << "\n";
	return 0;
}