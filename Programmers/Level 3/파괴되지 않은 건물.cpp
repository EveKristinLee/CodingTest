#include <iostream>
#include <string>
#include <vector>

using namespace std;

//누적합
int solution(vector<vector<int>> board, vector<vector<int>> skill) {
	int answer = 0;
	int N = board.size();
	int M = board[0].size();
	vector<vector<int>> tmp(N+2, vector<int>(M+2, 0));
	for (int i = 0; i < skill.size(); i++) {
		int x1 = skill[i][1];
		int y1 = skill[i][2];
		int x2 = skill[i][3];
		int y2 = skill[i][4];
		int n = skill[i][5];
		if (skill[i][0] == 1) { //공격
			n = -n;
		}
		tmp[x1][y1] += n;
		tmp[x2 + 1][y1] -= n;
		tmp[x1][y2 + 1] -= n;
		tmp[x2 + 1][y2 + 1] += n;
	}

	for (int i = 0; i < N; i++) {
		for (int j = 1; j < M; j++) {
			tmp[i][j] += tmp[i][j - 1];
		}
	}
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < M; j++) {
			tmp[i][j] += tmp[i - 1][j];
		}
	}

	for (int i = 0; i < board.size(); i++) {
		for (int j = 0; j < board[i].size(); j++) {
			board[i][j] += tmp[i][j];
			if (board[i][j] > 0) {
				answer++;
			}
		}
	}
	return answer;
}

int main(void) {
	vector<vector<int>> board = { {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5} , {5, 5, 5, 5, 5} , {5, 5, 5, 5, 5} };
	vector<vector<int>> skill = { {1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1} };
	int result = solution(board, skill);
	cout << result << "\n";
	return 0;
}