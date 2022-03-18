#include <iostream>
#include <vector>
using namespace std;

int cnt0 = 0;
int cnt1 = 0;
vector<vector<int>> map;

void dfs(int size, int x, int y) {
	bool zero, one;
	zero = one = true;
	for (int i = x; i < x+size; i++) {
		for (int j = y; j < y+size; j++) {
			if (map[i][j] == 0) {
				one = false;
			}
			if (map[i][j] == 1) {
				zero = false;
			}
		}
	}

	if (zero) {
		cnt0++;
		return;
	}
	if (one) {
		cnt1++;
		return;
	}

	dfs(size / 2, x + size / 2, y); //1사분면
	dfs(size / 2, x, y); //2사분면
	dfs(size / 2, x, y + size / 2); //3사분면
	dfs(size / 2, x + size / 2, y + size / 2); //4사분면
}


vector<int> solution(vector<vector<int>> arr) {
	vector<int> answer;
	map = arr;
	dfs(arr.size(), 0, 0);

	answer.push_back(cnt0);
	answer.push_back(cnt1);
	return answer;
}

int main(void) {
	vector<vector<int>> arr = { {1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1} };
	vector<int> answer = solution(arr);
	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i] << " ";
	}
	cout << "\n";
	return 0;
}