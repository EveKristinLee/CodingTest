#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

int getNum(int x) {
	if (x % 2 == 0) {
		return x / 2;
	}
	x /= 2;
	x++;
	return x;
}

int solution(int n, int a, int b){
	int answer = 1;
	while (1) {
		if (getNum(a) == getNum(b)) {
			break;
		}
		answer++;
		a = getNum(a);
		b = getNum(b);
	}
	return answer;
}

int main(void) {
	int n = 8;
	int a = 4;
	int b = 7;
	int result = solution(n, a, b);
	cout << result << "\n";
	return 0;
}