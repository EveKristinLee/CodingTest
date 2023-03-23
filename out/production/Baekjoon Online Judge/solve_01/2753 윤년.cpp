#include <iostream>

using namespace std;

int main(void) {
	int n;
	cin >> n;
	if (n % 4 == 0) {
		if (n % 100 != 0) {
			cout << "1\n";
		}
		if (n % 400 == 0) {
			cout << "1\n";
		}
	}
	else {
		cout << "0\n";
	}	
	return 0;
}