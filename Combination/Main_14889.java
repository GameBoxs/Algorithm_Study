import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14889 {
	// N : 사람 수
	static int N;
	
	// 능력치 정보 배열
	static int[][] map;
	
	// 조합 체크
	static int[] v;
	
	// 조합으로 만들어진 스타트팀, 링크팀 선수 번호 저장할 배열
	// statusArr[0] - 스타트팀, statusArr[1] - 링크 팀
	static int[][] statusArr;
	
	// 결과 값 저장
	static int result = Integer.MAX_VALUE;
	
	// 조합으로 만들어진 선수들 능력치 계산하는 메서드
	static int calcul(int[][] arr) {
		// 스타트팀 능력치 총합
		int sSum = 0;
		
		// 링크팀 능력치 총합
		int lSum = 0;
		
		// 0부터 arr배열 마지막-1 미만 까지
		for(int i=0; i<arr[0].length-1; i++) {
			// i+1 부터 마지막 까지
			for(int j=i+1; j<arr[0].length; j++) {
				// 스타트팀 선수 2명 능력치 합하기
				sSum += map[arr[0][i]][arr[0][j]] + map[arr[0][j]][arr[0][i]];
				
				// 링크팀 선수 2명 능력치 합하기
				lSum +=  map[arr[1][i]][arr[1][j]] + map[arr[1][j]][arr[1][i]];
			}
		}
		// | 스타트팀 능력치 총 합 - 링크팀 능력치 총 합 |
		return Math.abs(sSum-lSum);
	}
	
	// 조합
	static void comb(int cnt, int index) {
		// 전체 인원수 N에서 절반으로 나눠야함.
		if(cnt == N/2) {
			int startIndex = 0;
			int linkIndex = 0;
			for(int i=0; i<v.length; i++) {
				// 스타트팀 
				if(v[i] == 1) {
					statusArr[0][startIndex++] = i;
				}
				// 링크 팀
				else {
					statusArr[1][linkIndex++] = i;					
				}
			}
			// 최소값을 넣어줌
			result = Math.min(result, calcul(statusArr));
			return;
		}
		for(int i=index; i<v.length; i++) {
			if(v[i] == 1) continue;
			v[i] = 1;
			comb(cnt+1,i+1);
			v[i] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		v = new int[N];
		map = new int[N][N];
		statusArr = new int[2][N/2];
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			int x = 0;
			while(st.hasMoreTokens()) {
				map[y][x++] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0);
		System.out.println(result);
	}

}
