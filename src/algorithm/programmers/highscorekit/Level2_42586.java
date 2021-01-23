package algorithm.programmers.highscorekit;

import java.util.LinkedList;
import java.util.Queue;

/*
https://programmers.co.kr/learn/courses/30/lessons/42586
문제 설명
프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고,
이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와
각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때
 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

제한 사항
작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
작업 진도는 100 미만의 자연수입니다.
작업 속도는 100 이하의 자연수입니다.
배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다.
예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
입출력 예
progresses	                speeds	                return
[93, 30, 55]	            [1, 30, 5]	            [2, 1]
[95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	    [1, 3, 2]
입출력 예 설명
입출력 예 #1
첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다.
하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.

따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.

입출력 예 #2
모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다.
어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.

따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.
 */
public class Level2_42586 {
    public static void main(String[] args) {
        int [] progresses = {95, 90, 99, 99, 80, 99};
        int [] speeds = {1, 1, 1, 1, 1, 1};
        int[] solution = solution(progresses, speeds);
        for(int i = 0 ; i < solution.length; i++){
            System.out.print(solution[i] + ", ");
        }

    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int [] day = new int[progresses.length];
        Queue<Integer> waitingQueue = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();

        // 작업률이 100 이상 되는데 필요한 날짜 구하기.
        for(int i = 0 ; i < progresses.length; i++){
            int days = 0;
            while (progresses[i] < 100){
                progresses[i] += speeds[i];
                days ++;
            }
            day[i] = days;
        }

        // 첫 번째 작업에 필요한 남은 날짜는 직접 넣어줬습니다.
        waitingQueue.add(day[0]);

        // 다음 작업부터는 반복문을 돌면서
        for(int i = 1; i < day.length; i++){
            // waitingQueue 가 비었다면 waitingQueue 에 다음 작업에 걸리는 날짜를 집어넣습니다.
            if(waitingQueue.isEmpty()){
                waitingQueue.add(day[i]);
            }else {
                // 비어있지 않다면 현재작업에 필요한 남은날짜와 waitingQueue 에 남은 값(남은날짜)중 가장 큰값과 비교하게됩니다.
               if(waitingQueue.peek() > day[i]){
                   // waitingQueue 에 있는 값이 더 크다면 현재작업에 필요한 날짜도 넣어줍니다
                   waitingQueue.add(day[i]);
               } else{
                   // 현재작업의 남은날짜가 더 크다면, 현재작업이 끝나는 순간 waitingQueue 의 모든 작업이 다 끝남으로 queue 에 waitingQueue 의 사이즈를 넣어줍니다.
                   queue.add(waitingQueue.size());
                   waitingQueue.clear();
                   waitingQueue.add(day[i]);
               }
            }
        }

        // 마지막으로 처리되지 않은 부분을 처리하고
        if(!waitingQueue.isEmpty()){
            queue.add(waitingQueue.size());
        }

        // queue 를 배열로 만들어
        int [] answer = new int[queue.size()];
        for(int i = 0 ; i  < answer.length; i++){
            answer[i] = queue.poll();
        }

        // 반환합니다.
        return answer;
    }
}

/*
progresses = [98, 99, 97, 96]
speeds = [1, 1, 1, 1]
이와 같이
뒤에 있는 기능이 앞의 기능보다 먼저 종료됨 + 바로 다음 단계에 뒤에 있는 기능도 완료됨
경우 넣어보세요.
[2,1,1]이 아니라 [4]가 나올겁니다.
 */
