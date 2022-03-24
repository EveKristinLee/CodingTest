#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> weight;
int maxWeight = 0;

int main(void) {
	cin >> N;
	for (int i = 0; i < N; i++) {
		int tmp;
		cin >> tmp;
		weight.push_back(tmp);
		if (tmp > maxWeight) {
			maxWeight = tmp;
		}
	}
	sort(weight.begin(), weight.end()); 

	for (int i = 0; i < N; i++) {
		int tmp = weight[i] * (N - i);
		if (tmp > maxWeight) {
			maxWeight = tmp;
		}
	}
	
	cout << maxWeight << "\n";

	return 0;
}