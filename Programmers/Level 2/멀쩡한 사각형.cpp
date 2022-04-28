#include <iostream>
using namespace std;

int gcd(int a, int b) {
	int c;
	while (b != 0) {
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

long long solution(int w, int h) {
	long long answer = 0;
	int g = gcd(w, h);
	answer = (long long)w * (long long)h;
	answer -= ((w + h) - gcd(w, h));
	return answer;
}

int main(void) {
	int w = 8;
	int h = 12;
	long long result = solution(w, h);
	cout << result << "\n";

	return 0;
}