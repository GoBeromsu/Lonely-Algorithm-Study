package project;

import java.util.*;

public class Main {

    static int N, M;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        paper = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                paper[i][j] = sc.nextInt();
            }
        }
        sc.close();
        
        int hours = 0;
        while (true) {
            visited = new boolean[N][M];
            bfs();
            if (isAllMelted()) break;
            meltCheese();
            hours++;
        }
        
        System.out.println(hours);
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && paper[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }
    
    public static boolean isAllMelted() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (paper[i][j] == 1) return false;
            }
        }
        return true;
    }
    
    public static void meltCheese() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (paper[i][j] == 1) {
                    int airCount = 0;
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny]) {
                            airCount++;
                        }
                    }
                    if (airCount >= 2) paper[i][j] = 0;
                }
            }
        }
    }
    
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
