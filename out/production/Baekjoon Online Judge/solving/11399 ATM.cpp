#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(void) {
	vector<int> v;
	int sum = 0;
	int N;
	cin >> N;
	for (int i = 0; i < N; i++) {
		int a;
		cin >> a;
		v.push_back(a);
	}
	sort(v.begin(), v.end());
	for (int i = 0; i < N; i++) {
		for (int j = 0; j <= i; j++) {
			sum += v[j];
		}
	}
	cout << sum << "\n";
	return 0;
}