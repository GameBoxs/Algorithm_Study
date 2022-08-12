import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012 {
	// T: 테스트 케이스 개수, N : 식재료
	static int T, N;
	// map : 시너지 데이터 저장 배열
	static int[][] map;
	// v : 방문 체크 배열
	static int[] v;
	// 최소값 저장 변수
	static int minN;
	
	// 선택된 식재료가 만약 1,2 가 선택 됬다면
	// 계산해야할 맛은 (1,2) + (2,1) 의 값임.
	static int calcul(int[] arr) {
		int sum = 0;
		// i는 0부터 arr.length-1 미만까지
		for(int i=0; i<arr.length-1; i++) {
			// j는 i+1 부터 arr.lenght까지
			for(int j=i+1; j<arr.length; j++) {
				// i,j + j,i 이렇게 더해줌 총 값 저장하는 변수에
				sum += map[arr[i]][arr[j]] + map[arr[j]][arr[i]];
			}
		}
		// 끝나면 리턴
		return sum;
	}
	
	// 조합으로 식재료/2 개수 선택 만들기
	static void search(int cnt, int index) {
		// N/2 개 선택 했을 때
		if(cnt == N/2) {
			// 또 나중에 계산 해야 하기 때문에 배열 생성
			int[] aArr = new int[N/2];
			int[] bArr = new int[N/2];
			int aIndex = 0;
			int bIndex = 0;
			for(int i=0; i<v.length; i++) {
				// 1이라면 A음식
				if(v[i] == 1) {
					// A음식의 식재료 번호를 만든 배열에 넣어줌
					aArr[aIndex++] = i;
				}
				// 1이 아니라면 B 음식임.
				else {
					bArr[bIndex++] = i;					
				}
			}
			// | A음식 맛 계산 - B음식 맛 계산 | , minN 두개 최소 비교하여 minN에 값 넣음
			minN = Math.min(minN, Math.abs(calcul(aArr)-calcul(bArr)));
			return;
		}
		for(int i=index; i<v.length; i++) {
			if(v[i] == 1) continue;
			v[i] = 1;
			search(cnt+1,i+1);
			v[i] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			v = new int[N];
			minN = Integer.MAX_VALUE;
			
			for(int y=0; y<N; y++) {
				st = new StringTokenizer(br.readLine());
				int x = 0;
				while(st.hasMoreTokens()) {
					map[y][x++] = Integer.parseInt(st.nextToken());
				}				
			}
			search(0,0);
			sb.append("#").append(t).append(" ").append(minN).append("\n");
		}
		System.out.println(sb);
	}

}
