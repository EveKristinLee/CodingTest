#include <iostream>
#include <string>
#include <vector>

using namespace std;

int n; //�ڹ����� ũ��
int m; //������ ũ��
int boardSize;

vector<vector<int>> board;

//false�� �ȿ��Ⱦ��
bool isOpen(vector<vector<int>> key, int x, int y) {
	bool result = true;

	//���忡 �ڹ��� ����
	for (int i = x; i < x + m; i++) {
		for (int j = y; j < y + m; j++) {
			board[i][j] += key[i - x][j - y];
		}
	}

	//���ȴ��� Ȯ��
	for (int i = m; i < m + n; i++) {
		for (int j = m; j < m + n; j++) {
			if(board[i][j] != 1) {
				result = false;
				break;
			}
		}
	}

	//�����ǿ� �ڹ��� ����
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

	boardSize = (m * 2) + n; //�� �˳��� ��Ƶ� ��
	board = vector<vector<int>>(boardSize, vector<int>(boardSize));
	
	//board ����� �ڹ��� ����
	for (int i = m; i < m+n; i++) {
		for (int j = m; j < m+n; j++) {
			board[i][j] = lock[i - m][j - m];
		}
	}

	//board���� ��� ��ǥ���� key�� ��� ����Ǽ� Ȯ��
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