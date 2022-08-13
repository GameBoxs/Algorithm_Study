import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 익은 토마토 위치 저장 클래스
class TomatoLocation{
	int y,x;

	public TomatoLocation(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}

public class Main_7576 {
	static int[][] map;
	
	// M - 가로 , N - 세로
	static int M,N;
	
	// 0인 토마토 개수 
	static int tomatoCnt = 0;
	
	//결과 값
	static int result = 0;
	
	// Queue를 사용
	static Queue<TomatoLocation> q = new LinkedList<>();
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int y=0; y<N; y++) {
			int x= 0;
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
				// 1인 위치를 큐에 넣음
				if(map[y][x] == 1) {
					q.offer(new TomatoLocation(y,x));
				}
				
				// 0이면 익어야 하는 토마토 개수 1 추가
				else if(map[y][x] == 0) {
					tomatoCnt += 1;
				}
				x+=1;
			}
		}
		// 만약 익어야 할 토마토가 없다면 바로 종료
		if(tomatoCnt == 0) {
			System.out.println(result);
		}
		// 익어야 할 토마토 개수가 남아 있다면
		else {
			// q 에 저장된 데이터가 없을 때 까지 반복
			while(!q.isEmpty()) {
				// 빼내서 t에 저장
				TomatoLocation t = q.poll();
				
				for(int i=0; i<4; i++) {
					int ny = t.y+dy[i];
					int nx = t.x+dx[i];
					
					// 상 우 하 좌 돌면서 아직 익지 않은 0일 경우만 q에 넣고 해당 자리는 현재 자리 숫자 +1 해줌
					if(ny >= 0 && ny<N && nx>=0 && nx<M) {
						if(map[ny][nx] == 0) {
							result = map[t.y][t.x] + 1;
							map[ny][nx] = result;
							tomatoCnt -= 1;
							q.offer(new TomatoLocation(ny,nx));
						}
					}
				}
			}
			// 만약 익어야 할 토마토 개수가 0이 아니라면 어딘가 막혀서 남아있다는 뜻
			if(tomatoCnt != 0) {
				// -1
				System.out.println(-1);
			}
			// 0이라면 다 익었기 때문에 결과 -1 을 해줌.
			// -1 하는 이유는 처음 시작 숫자가 1이고 해당 숫자 +1부터 하기 때문에 마지막에 -1을 해줌
			else {
				System.out.println(result-1);
			}			
		}
	}

}
