#include <iostream>
#include <vector>

using namespace std;

int calcCnt(int cnt, int w) {
	int maxWire = (w * 2) + 1;

	if (cnt > 0) {
		if (cnt % maxWire) {
			return (cnt / maxWire) + 1;
		}
		else {
			return (cnt / maxWire);
		}
	}
	return 0;
}

int solution(int n, vector<int> stations, int w)
{
	int answer = 0;
	int cnt;

	cnt = stations[0] - w - 1;
	answer += calcCnt(cnt, w);
	

	for (int i = 1; i < stations.size(); i++) {
		int firIdx = stations[i - 1] + w;
		int secIdx = stations[i] - w;

		cnt = secIdx - firIdx - 1;
		answer += calcCnt(cnt, w);
	}

	cnt = n - (stations[stations.size() - 1] + w);
	answer += calcCnt(cnt, w);

	return answer;
}

int main(void) {

	int n = 11;
	vector<int> stations = { 4, 11 };
	int w = 1;
	int result = solution(n, stations, w);
	cout << result << "\n";

	return 0;
}