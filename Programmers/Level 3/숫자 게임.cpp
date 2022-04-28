#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> A, vector<int> B) {
	int answer = 0;
	sort(A.begin(), A.end(), greater<int>());
	sort(B.begin(), B.end(), greater<int>());
	int a = 0;
	int b = 0;

	for (int i = 0; i < A.size(); i++) {
		if (A[a] < B[b]) {
			answer++;
			b++;
		}
		a++;
	}

	return answer;
}

int main(void) {
	vector<int> A = { 5, 1, 3, 7 };
	vector<int> B = { 2, 2, 6, 8 };
	int result = solution(A, B);
	cout << result << "\n";

	return 0;
}