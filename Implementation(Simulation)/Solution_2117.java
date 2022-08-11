import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 매 자리마다 마름모를 만들었다가 터져서 새로 작성한 코드.
 * 마름모 크기를 키워나가며 해당 범위 안에 집이 있는지 체크를 하는 방식.
 */

class Pair{
	int x, y;

	public Pair(int y, int x) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_2117 {
	// T : 테스트 케이스, N : 도시 크기, M : 집 한채 서비스 수익
	static int T,N,M,totalHouseCnt;
	// result : 결과값 저장, map : 도시 데이터, al<Pair> : 집이 위치한 정보
	static int result;
	static int[][] map;
	static ArrayList<Pair> al;
	
	// 탐색 메서드
	static void search(int y, int x) {
		// 기본 운영비용
		int basicPrice = 0;
		
		for(int K=1; K<=N+1; K++) {
			// 만약 결과값이 집의 총 개수랑 같다면 최대치 이므로 바로 메서드를 탈출
			if(result == totalHouseCnt) return;
			
			// 탐색하여 조건에 맞는 집의 개수를 저장할 변수
			int homeCnt = 0;
			
			// 기본 운영비용 계산하여 변수에 값 저장
			basicPrice = K*K+(K-1)*(K-1);
			
			// 집의 정보가 저장되어 있는 ArrayList 를 for-each를 사용하여 반복
			// 집의 정보를 따로 저장한 이유는 0,0 ~ N,N까지 불필요한 반복을 줄일 수 있기 때문
			for(Pair p : al) {
				// 마름모꼴 범위에 있는지 확인 하기 위해서 집의 위치 거리를 구함
				int distance = Math.abs(p.x-x)+Math.abs(p.y-y);
				// 만약 거리가 마름모꼴 안이라면 집 카운트 개수 올림
				if(distance < K) {
					homeCnt += 1;
				}
			}
			// 저장된 집 정보에 대한 탐색 마친 후 찾은 집의 갯수 * 집 한채 서비스 수익에서 기본 유지비용을 뺀 값이 0 이상일때 (이익)
			if( (homeCnt * M) - basicPrice >= 0) {
				// 결과 값과 
				result = Math.max(result, homeCnt);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<T+1; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			result = 0;
			al = new ArrayList<>();
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(br.readLine());
				int x = 0;
				while(st.hasMoreTokens()) {
					map[y][x] = Integer.parseInt(st.nextToken());
					// 배열을 map에 넣으면서 만약 값이 1일 경우 집 위치 저장하는 ArrayList<Pair> al 에 추가 시킴.
					if(map[y][x] == 1) {
						al.add(new Pair(y,x));
					}
					x+=1;
				}
			}
			// 나중에 혹시나 불필요한 계산 줄이기 위해서 집의 총 개수를 알아 둠.
			totalHouseCnt = al.size();
			
			roop:for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					// 탐색 시작(현재 y위치, 현재 x위치)
					search(y,x);
					// 만약 결과 값이 집의 총 개수랑 같아지면 제일 최대치를 구했기 때문에 뒤에 연산을 할 필요가 없으므로 탈출.
					if(result == totalHouseCnt) break roop;
				}
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
