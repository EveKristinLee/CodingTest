#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main(void) {
	int T;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		string ans;
		string game;
		cin >> game;
		int win = 0;
		for (int i = 0; i < game.size(); i++) {
			if (game[i] == 'o') {
				win++;
			}
		}
		int rest = 15 - game.size();
		if (win + rest >= 8) {
			ans = "YES";
		}
		else {
			ans = "NO";
		}

		cout << "#" << t << " " << ans << "\n";
	}
	return 0;
}