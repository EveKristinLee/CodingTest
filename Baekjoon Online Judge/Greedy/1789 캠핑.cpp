#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int L, P, V;
	cin >> L >> P >> V;
	int cnt = 1;
	while (L != 0 && P != 0 && V != 0) {
		int maxDay = 0;
		maxDay += (V / P) * L + min(L, (V % P));
		
		cout << "Case " << cnt << ": " << maxDay << "\n";
		cnt++;

		cin >> L >> P >> V;
	}

	return 0;
}