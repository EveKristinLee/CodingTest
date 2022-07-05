package com.company;

import java.util.HashMap;
import java.util.HashSet;

public class 신고결과받기 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        answer = new int[id_list.length];

        HashMap<String, HashSet<String>> reportedMap = new HashMap<>();
        HashMap<String, Integer> idx = new HashMap<>();
        for(int i=0; i<id_list.length; i++) {
            HashSet<String> reportingId = new HashSet<>();
            String name = id_list[i];
            reportedMap.put(name, reportingId);
            idx.put(name, 0);
        }
        for(int i=0; i<report.length; i++) {
            String[] reportInfo = report[i].split(" ");
            reportedMap.get(reportInfo[1]).add(reportInfo[0]);
        }
        for (String reportedId : reportedMap.keySet()) {
            HashSet<String> reportingUser = reportedMap.get(reportedId);
            if(reportingUser.size() >= k) {
                for (String reportingId : reportingUser) {
                    idx.put(reportingId, idx.get(reportingId) +1);
                }
            }
        }

        for(int i=0; i<id_list.length; i++) {
            answer[i] = idx.get(id_list[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        int[] result = solution(id_list, report, k);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
