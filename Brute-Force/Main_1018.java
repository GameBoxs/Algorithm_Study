import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Question Name 	:	체스판 다시 칠하기
 * Question Number 	:	1018
 * Question Tier	:	Silver4
 * Question Link	:	https://www.acmicpc.net/problem/1018
 */
public class Main_1018 {
	static int N, M;
	static char[][] map;
	// 0,0이 흰색 검사 배열
	static char[][] search1 = {
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
	};
	
	// 0,0이 검은색 검사 배열
	static char[][] search2 = {
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
			{'B','W','B','W','B','W','B','W'},
			{'W','B','W','B','W','B','W','B'},
	};
	
	// 검사 메서드
	static int calcul(int ty, int tx, int c) {
		int cnt = 0;
		// 전달 받은 검시 시작 좌표가 바뀌면 나중에 x를 원래 시작 좌표로 다시 되돌리기 힘들어 변수 만들어서 넣어줌
		int ny = ty;
		int nx = tx;
		
		// 위 검사 배열을 돌리기위해 y,x 에 대한 2중 for문 시작 
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				if(c == 0 && search1[y][x] != map[ny][nx++]) {
					cnt+=1;
				}
				else if(c == 1 && search2[y][x] != map[ny][nx++]) {
					cnt+=1;
				}
			}
			nx = tx;
			ny += 1;
		}
		return cnt;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		int minN = Integer.MAX_VALUE;
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			map[y] = temp.toCharArray();
		}
		
		for(int y=0; y<=N-8; y++) {
			for(int x=0; x<=M-8; x++) {
				// 꼭 첫번째가 검은색이였다고 검은색 배열로 찾는것이 아니라 흰색 배열에도 검사를 돌려서 둘 중 최소가 나오는것을 선텍
				int temp = Math.min(calcul(y,x,0), calcul(y,x,1));
				
				// minN 에 원래 기존값 vs 위에서 구한 값 중 작은것을 넣음.
				minN = Math.min(minN, temp);
			}
		}
		System.out.println(minN);
	}

}