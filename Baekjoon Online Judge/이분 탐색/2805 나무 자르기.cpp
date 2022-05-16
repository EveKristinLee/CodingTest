#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int ans;
	int n, m;
	cin >> n >> m;
	vector<int> tree;
	for (int i = 0; i < n; i++) {
		int t;
		cin >> t;
		tree.push_back(t);
	}
	sort(tree.begin(), tree.end());

	int left = 0;
	int right =*max_element(tree.begin(), tree.end());
	int mid;
	while (left <= right) {
		mid = (left + right) / 2;
		long long sum = 0;
		for (int i = 0; i < n; i++) {
			if (tree[i] > mid) {
				sum += (tree[i] - mid);
			}
		}
		if (m <= sum) {
			left = mid + 1;
			ans = mid;
		}
		else {
			right = mid - 1;
		}
	}
	cout << ans << "\n";
	return 0;
}