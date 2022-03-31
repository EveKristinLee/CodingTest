#include <iostream>
#include <string>
#include <vector>

using namespace std;

int n; //자물쇠의 크기
int m; //열쇠의 크기
int boardSize;

vector<vector<int>> board;

//false면 안열렸어요
bool isOpen(vector<vector<int>> key, int x, int y) {
	bool result = true;

	//보드에 자물쇠 적용
	for (int i = x; i < x + m; i++) {
		for (int j = y; j < y + m; j++) {
			board[i][j] += key[i - x][j - y];
		}
	}

	//열렸는지 확인
	for (int i = m; i < m + n; i++) {
		for (int j = m; j < m + n; j++) {
			if(board[i][j] != 1) {
				result = false;
				break;
			}
		}
	}

	//보드판에 자물쇠 해제
	for (int i = x; i < x + m; i++) {
		for (int j = y; j < y + m; j++) {
			board[i][j] -= key[i - x][j - y];
		}
	}
	return result;
}

void turn90(vector<vector<int>>& key) {
	vector<vector<int>> tmp(m, vector<int>(m));
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < m; j++) {
			tmp[i][j] = key[j][m - i - 1];
		}
	}

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < m; j++) {
			key[i][j] = tmp[i][j];
		}
	}
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
	bool answer = false;
	n = lock.size();
	m = key.size();

	boardSize = (m * 2) + n; //좀 넉넉히 잡아도 됨
	board = vector<vector<int>>(boardSize, vector<int>(boardSize));
	
	//board 가운데에 자물쇠 놓기
	for (int i = m; i < m+n; i++) {
		for (int j = m; j < m+n; j++) {
			board[i][j] = lock[i - m][j - m];
		}
	}

	//board판의 모든 좌표에서 key의 모든 경우의수 확인
	for (int i = 0; i < n + m; i++) {
		for (int j = 0; j < n + m; j++) {
			for (int k = 0; k < 4; k++) {
				turn90(key);
				if (isOpen(key, i, j)) {
					answer = true;
					break;
				}
			}
		}
	}
	return answer;
}

int main(void) {
	vector<vector<int>> key = { {0, 0, 0}, {1, 0, 0}, {0, 1, 1} };
	vector<vector<int>> lock = { {1, 1, 1}, {1, 1, 0}, {1, 0, 1} };
	bool result = solution(key, lock);
	if (result) {
		cout << "result : true\n";
	}
	else {
		cout << "result : false\n";
	}
	return 0;
}