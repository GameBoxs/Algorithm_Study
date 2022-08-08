import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Question Name 	:	[S/W 문제해결 기본] 7일차 - 미로2
 * Question Number 	:	1227
 * Question Tier	:	D4
 * Question Link	:	https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14wL9KAGkCFAYD
 */

// Queue 2개 쓰기 싫어서 직접 만듬.
class Pair{
	int x, y;
	Pair(){}
	Pair(int y, int x){
		this.x = x;
		this.y = y;
	}
	public int first() {return this.y;}
	public int second() {return this.x;}
}

public class Solution_1227 {
	// T : 테스트케이스 숫자, map : 맵 데이터, v : 방문 체크, dx/dy : 하,우,상,좌
	static int T;
	static char[][] map = new char[100][100];
	static char[][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<10; t++) {
			st = new StringTokenizer(br.readLine());
			
			// 위에 만든 Pair형으로 큐를 만듬.
			Queue<Pair> q = new LinkedList<>();
			
			// 방문 체크용 배열은 맵이랑 똑같게
			v = map;
			T = Integer.parseInt(st.nextToken());
			int result = 0;
			for(int y=0; y<100; y++) {
				st = new StringTokenizer(br.readLine());
				map[y] = st.nextToken().toCharArray();
			}
			// 전부 시작점이 1,1 이므로 큐에 y:1, x:1을 넣어줌
			q.add(new Pair(1,1));
			
			//큐가 없어질때 까지 반복
			roop:while(!q.isEmpty()) {
				// 큐 내용을 Pair에 poll로 빼면서 값 넣음
				Pair pair = q.poll();
				
				// 현재 ny, nx 는 큐에 저장된 y, x
				int ny = pair.first();
				int nx = pair.second();
				
				// 방문 체크 해줌
				v[ny][nx] = '1';
				
				// dy, dx를 돌면서 3을 만나면 결과값을 1로 바꾸고 바로 종료,
				// 만약 맵의 다음 지점이 0이고 방문을 하지 않았다면 큐에 다음 지점을 넣어줌.
				for(int i=0; i<4; i++) {
					if(map[ny+dy[i]][nx+dx[i]] == '3') {
						result = 1;
						break roop;
					}
					else if(map[ny+dy[i]][nx+dx[i]] == '0' && v[ny+dy[i]][nx+dx[i]] != '1') {
						q.add(new Pair(ny+dy[i],nx+dx[i]));
					}
				}
			}
			sb.append("#").append(T).append(" ").append(result).append("\n");			
		}
		System.out.println(sb);
	}

}
