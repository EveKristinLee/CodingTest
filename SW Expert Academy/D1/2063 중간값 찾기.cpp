#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
	int N;
	vector<int> score;
	cin >> N;
	for (int i = 0; i < N; i++) {
		int tmp;
		cin >> tmp;
		score.push_back(tmp);
	}
	sort(score.begin(), score.end());
	cout << score[(N / 2)] << "\n";
	return 0;
}