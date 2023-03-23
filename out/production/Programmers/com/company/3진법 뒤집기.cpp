#include <vector>
#include <cmath>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(int n) {
	int answer = 0;
	vector<int> v;
	
	while (n > 0) {
		v.push_back(n % 3);
		n /= 3;
	}
	
	reverse(v.begin(), v.end());

	for (int i = 0; i < v.size(); i++) {
		answer += pow(3, i) * v[i];
	}
	return answer;
}

int main(void) {
	cout << solution(45) << "\n";
	cout << solution(125) << "\n";
	return 0;
}