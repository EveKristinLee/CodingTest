//[����] https://velog.io/@euneun/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-N%EC%9C%BC%EB%A1%9C-%ED%91%9C%ED%98%84-DP-%EB%8F%99%EC%A0%81%EA%B3%84%ED%9A%8D%EB%B2%95-C

#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int get_Ns(int N, int idx) {
	//idx:1 == NN, idx:2 == NNN...
	int result = N;
	for (int i = 1; i <= idx; i++) {
		result = (result * 10) + N;
	}
	return result;
}


int solution(int N, int number) {
	if (N == number) {
		return 1;
	}
	vector<unordered_set<int>> dp(8);
	dp[0].insert(N); //�Ѱ��� ����� �ִ� ���ڴ� N��

	for (int k = 1; k < 8; k++) {
		//NN, NNN, NNNN..���� ����� �ִ� ���� �ֱ�
		dp[k].insert(get_Ns(N, k));

		//��Ģ �������� ����� �ִ� ���� �ֱ�
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if (i + j + 1 != k) {
					continue;
				}
				for (int a : dp[i]) {
					for (int b : dp[j]) {
						dp[k].insert(a + b);
						if (a - b > 0) {
							dp[k].insert(a - b);
						}
						dp[k].insert(a * b);
						if (a / b > 0) {
							dp[k].insert(a / b);
						}
					}
				}
			}
		}

		//dp[k]�� number���� �ִٸ� k+1 ��ȯ
		if (dp[k].find(number) != dp[k].end()) {
			return k + 1;
		}
	}
	return -1; //����� ���ų� 8�̻��� ���� -1��ȯ
}

int main(void) {
	int N = 2;
	int number = 11;
	int result = solution(N, number);
	cout << result << "\n";
	return 0;
}
