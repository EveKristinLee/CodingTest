#include <iostream>
#include <vector>

using namespace std;

int n; //카드의 개수
int m; 
vector<int> card;
int maxNum = 0;
int sum = 0;

int main(void) {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		card.push_back(tmp);
	}

	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			for (int z = j + 1; z < n; z++) {
				sum = card[i] + card[j] + card[z];
				if (sum <= m) {
					if (maxNum < sum) {
						maxNum = sum;
					}
				}
			}
		}
	}
	cout << maxNum << "\n";
	return 0;
}