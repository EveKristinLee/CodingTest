#include<iostream>
#include <string>
#include <vector>

using namespace std;

bool visit[201] = { false };

void dfs(int n, vector<vector<int>> computers, int x) {
	visit[x] = true;
	
	for (int i = 0; i < n; i++) {
		if (computers[x][i] == 1 && !visit[i]) {
			dfs(n, computers, i);
		}
	}

}

int solution(int n, vector<vector<int>> computers) {
	int answer = 0;
	for (int i = 0; i < n; i++) {
		if (!visit[i]) {
			dfs(n, computers, i);
			answer++;
		}
	}
	return answer;
}

int main(void) {

	int n = 3;
	vector<vector<int>> computers = { {1, 1, 0}, {1, 1, 0}, {0, 0, 1} };

	cout << solution(n, computers);

	return 0;
}
