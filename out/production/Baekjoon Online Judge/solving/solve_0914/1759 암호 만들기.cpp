#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int L; //암호의 길이
int C; //알파벳 개수
char alpha[16];
bool visit[16] = { false, };
vector<char> pw;
void dfs(int cnt) {
	if (cnt == L) {
		//모음,자음확인
		int moum = 0;
		for (int i = 0; i < L; i++) {
			if (pw[i] == 'a' || pw[i] == 'e' ||
				pw[i] == 'i' || pw[i] == 'o' ||
				pw[i] == 'u') {
				moum++;
			}
		}
		
		if (moum >= 1 && L - moum >= 2) {
			for (int i = 0; i < L; i++) {
				cout << pw[i];
			}
			cout << "\n";
			return;
		}
	}

	for (int i = 0; i < C; i++) {
		if (!pw.empty()) {
			if (pw.back() > alpha[i]) { //이전 알파벳보다 크면 넘어감
				continue;
			}
		}
		if (!visit[i]) {
			visit[i] = true;
			pw.push_back(alpha[i]);
			dfs(cnt + 1);
			visit[i] = false;
			pw.pop_back();
		}
	}
}

int main(void) {
	cin >> L >> C;
	for (int i = 0; i < C; i++) {
		cin >> alpha[i];
	}
	sort(alpha, alpha + C);
	
	dfs(0);

	return 0;
}