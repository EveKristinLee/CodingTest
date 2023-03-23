#include <iostream>
#include <vector>

using namespace std;

int N;
int M;
vector<int> num;

void dfs(int cnt) {
	if (cnt == M) {
		for (int i = 0; i < M; i++) {
			cout << num[i] << " ";
		}
		cout << "\n";
		return;
	}

	for (int i = 1; i <= N; i++) {
		num.push_back(i);
		dfs(cnt + 1);
		num.pop_back();
	}
}

int main(void) {
	cin >> N >> M;
	dfs(0);
	return 0;
}