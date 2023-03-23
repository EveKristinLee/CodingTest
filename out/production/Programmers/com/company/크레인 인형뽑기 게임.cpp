#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
	int answer = 0;
	vector<int> basket;
	int row = board.size();

	for (int i = 0; i < moves.size(); i++) {
		for (int j = 0; j < row; j++) {
			if (board[j][moves[i] - 1] == 0) {
				continue;
			}
			else {
				int tmp = board[j][moves[i] - 1];
				if (!basket.empty()) {
					if (basket.back() == tmp) {
						answer += 2;
						basket.pop_back();
					}
					else {
						basket.push_back(tmp);
					}
				}
				else {
					basket.push_back(tmp);
				}
				
				board[j][moves[i] - 1] = 0;
				break;
			}
		}
	}
	return answer;
}

int main(void) {
	int ans = solution({ {0,0,0,0,0}, {0,0,1,0,3}, {0, 2, 5, 0, 1}, {4,2,4,4,2}, {3,5,1,3,1} }, { 1, 5, 3, 5, 1, 2, 1, 4 });
	cout << ans << "\n";

	return 0;
}