#include <iostream>
#include <string>

using namespace std;

int main(void) {
	int n;
	cin >> n;
	int cnt = 0;
	long long num = 666;

	while (1) {
		string a = to_string(num);
		//���ڿ� 666�迭 ����
		if (a.find("666") != string::npos) {
			cnt += 1;
		}
		if (cnt == n) {
			cout << num << "\n";
			break;
		}
		num += 1;

	}

	return 0;
}