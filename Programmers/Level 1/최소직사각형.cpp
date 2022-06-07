#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes) {
	int answer = 0;
	vector<int> big;
	vector<int> small;
	int w = 0;
	int h = 0;
	for (int i = 0; i < sizes.size(); i++) {
		if (sizes[i][0] >= sizes[i][1]) {
			big.push_back(sizes[i][0]);
			small.push_back(sizes[i][1]);
		}
		else {
			big.push_back(sizes[i][1]);
			small.push_back(sizes[i][0]);
		}
	}
	w = *max_element(big.begin(), big.end());
	h = *max_element(small.begin(), small.end());

	return w*h;
}

int main(void) {
	vector<vector<int>> sizes = { {60, 50}, {30, 70}, {60, 30}, {80, 40} };
	int result = solution(sizes);
	cout << result << "\n";
	return 0;
}