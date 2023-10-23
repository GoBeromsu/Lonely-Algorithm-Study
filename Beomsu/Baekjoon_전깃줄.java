import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] wires = new int[n][2];
        for (int i = 0; i < n; i++) {
            wires[i][0] = sc.nextInt();
            wires[i][1] = sc.nextInt();
        }
        sc.close();

        // A전봇대의 위치 번호를 기준으로 오름차순 정렬
        Arrays.sort(wires, (a, b) -> Integer.compare(a[0], b[0]));

        // LIS 계산
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (wires[i][1] > wires[j][1] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // 최장 증가 부분 수열의 길이 찾기
        int lisLength = Arrays.stream(dp).max().getAsInt();

        // 결과 출력
        System.out.println(n - lisLength);
    }
}
