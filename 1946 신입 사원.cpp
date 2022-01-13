#include <iostream>
#include <algorithm>
#include <vector>

using namespace std; 

int main(void) {
	int t;
	cin >> t;

	while (t--) {
		int n;
		cin >> n;
		int maxNum = 0;
		int interviewGrade = 0;
		vector<pair<int, int>> grade;
		for (int i = 0; i < n; i++) {
			int a, b;
			cin >> a >> b;
			grade.push_back(make_pair(a, b));
		}
		sort(grade.begin(), grade.end()); //�����ɻ� ��������
	
		maxNum++; //�����ɻ� 1�� ä��
		interviewGrade = grade[0].second;
		for (int i = 1; i < n; i++) {
			if (interviewGrade > grade[i].second) {
				maxNum++;
				interviewGrade = grade[i].second;
			}
		}
		cout << maxNum << "\n";
	}
	return 0;
}