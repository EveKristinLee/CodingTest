#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n) {
	vector<int> answer;
	vector<vector<int>> map(n + 1, vector<int>(n + 1)); //int map[n+1][n+1]
	int maxNum = (n * (n + 1)) / 2;
	int top = 1;
	int bottom = n;
	int left = 1;
	int right = 0;

	int num = 1;
	int state = 0; //0이면 내려가기, 1이면 오른쪽으로 가기, 2면 올라가기
	while (num <= maxNum) {
		if (state == 0) {
			for (int i = top; i <= bottom; i++) {
				map[i][left] = num++;
			}
			top++;
			left++;
			state = 1;
		}
		else if (state == 1) {
			for (int i = left; i <= bottom - right; i++) {
				map[bottom][i] = num++;
			}
			bottom--;
			state = 2;
		}
		else if (state == 2) {
			for (int i = bottom; i >= top; i--) {
				map[i][i - right] = num++;
			}
			right++;
			top++;
			state = 0;
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= i; j++) {
			answer.push_back(map[i][j]);
		}
	}

	return answer;
}

int main(void) {
	int n = 5;
	vector<int> result = solution(n);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << "\n";
	}

	return 0;
}