import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Question Name 	:	격자판의 숫자 이어 붙이기
 * Question Number 	:	2819
 * Question Tier	:	D4
 * Question Link	:	https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7I5fgqEogDFAXB&
 */

public class Solution_2819 {
	static int T;
	static int[][] map = new int[4][4];
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1};
	static Set<String> set = new HashSet<>();
	static StringBuilder sbResult = new StringBuilder();
	
	static void dfs(int y, int x, int cnt, String s) {
		if(cnt == 6) {
			set.add(s);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny>=0 && ny<4 && nx>=0 && nx<4) {
				dfs(ny,nx,cnt+1,s+map[ny][nx]);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t=1; t<T+1; t++) {
			set = new HashSet<>();
			for(int y=0; y<4; y++) {
				for(int x=0; x<4; x++) {
					map[y][x] = sc.nextInt();
				}
			}
			for(int y=0; y<4; y++) {
				for(int x=0; x<4; x++) {
					dfs(y,x,0,map[y][x]+"");
				}
			}
			sbResult.append("#").append(t).append(" ").append(set.size()).append("\n");
//			set.clear();
		}
		System.out.print(sbResult);
	}

}
