#include <iostream>
#include <vector>

using namespace std;

int N;
int M;
vector<int> num;
bool check[9] = { false, };

void dfs(int cnt) {
	if (cnt == M) {
		for (int i = 0; i < M; i++) {
			cout << num[i] << " ";
		}
		cout << "\n";
		return;
	}

	for (int i = 1; i <= N; i++) {
		if (!check[i]) {
			num.push_back(i);
			check[i] = true;
			dfs(cnt + 1);
			num.pop_back();
			check[i] = false;
		}
	}
}

int main(void) {
	cin >> N >> M;
	dfs(0);
	return 0;
}