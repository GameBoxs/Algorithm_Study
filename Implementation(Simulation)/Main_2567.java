import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2567 {
	static int cnt;
	static int[][] map = new int[101][101];
	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		cnt = Integer.parseInt(st.nextToken());
		int result = 0;
		for(int i=0; i<cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			// 색종이 붙혀진 부분을 1로 전부 바꿔줌
			for(int y=startY; y<startY+10; y++) {
				for(int x=startX; x< startX+10; x++) {
					map[y][x] = 1;
				}
			}
		}
		
		// 아래로 내려오면서 자기 자신이 1이면서 좌 또는 우가 0일때 카운트 증가
		for(int x=0; x<101; x++) {
			for(int y=0; y<101; y++) {
				if(map[y][x] == 1 && (map[y][x-1] == 0 || map[y][x+1] == 0)) {
					result += 1;
				}
			}
		}
		// 좌에서 우로 탐색하면서 자기 자신이 1이면서 위 또는 아래가 0일때 카운트 증가
		for(int y=0; y<101; y++) {
			for(int x=0; x<101; x++) {
				if(map[y][x] == 1 && (map[y-1][x] == 0 || map[y+1][x] == 0)) {
					result += 1;
				}
			}
		}
		System.out.println(result);
	}
}
