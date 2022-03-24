#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int num[8];
vector<int> v;
bool check[10001] = { false, };

void dfs(int cnt) {
	if (cnt == M) {
		for (int i = 0; i < M; i++) {
			cout << v[i] << " ";
		}
		cout << "\n";
		return;
	}
	for (int i = 0; i < N; i++) {
		if (!check[num[i]]) {
			v.push_back(num[i]);
			check[num[i]] = true;
			dfs(cnt + 1);
			v.pop_back();
			check[num[i]] = false;
		}
	}
}

int main(void) {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> num[i];
	}
	sort(num, num + N);
	dfs(0);
	return 0;
}