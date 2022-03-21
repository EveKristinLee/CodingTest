#include <iostream>
#include <vector>

using namespace std;

int solution(vector<int> a) {
	int answer = 0;
	vector<int> leftMin(a.size(), 0);
	vector<int> rightMin(a.size(), 0);

	int minNum = a[0];
	for (int i = 0; i < a.size(); i++) {
		if (minNum > a[i]) {
			minNum = a[i];
		}
		leftMin[i] = minNum;
	}

	minNum = a[a.size() - 1];
	for (int i = a.size() - 1; i >= 0; i--) {
		if (minNum > a[i]) {
			minNum = a[i];
		}
		rightMin[i] = minNum;
	}

	for (int i = 0; i < a.size(); i++){
		if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
			answer++;
		}
	}
	return answer;
}

int main(void) {
	vector<int> a = { -16,27,65,-2,58,-92,-71,-68,-61,-33 };
	int result = solution(a);
	cout << result << "\n";
	return 0;
}