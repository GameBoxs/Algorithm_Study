import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1697 {
	static int N, K;
//	static int count = Integer.MAX_VALUE;
	static Queue<Integer> q = new LinkedList<>();
	// 시간을 저장할 배열
	static int[] v = new int[100001];
	static void bfs() {
		while(!q.isEmpty()) {
			int n = q.poll();

			// n이 목적지와 같을 때
			if(n == K) {
				// 시간 배열의 n번째 출력
				System.out.println(v[n]);
				return;
			}
			// n-1 이 0 ~ 100000 일때
			if(n-1>=0 && n-1 < 100001) {
				if(v[n-1] == 0) {
					q.add(n-1);
					// v[n-1]은 현재 v[n]의 시간 +1
					v[n-1] = v[n] + 1;					
				}
			}
			if(n+1>0 && n+1 < 100001) {
				if(v[n+1] == 0) {
					q.add(n+1);
					v[n+1] = v[n]+1;
				}
			}
			if(n*2>0 && n*2 < 100001) {
				if(v[n*2] == 0) {
					q.add(n*2);
					v[n*2] = v[n]+1;					
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		q.add(N);
		v[N] = 0;
		bfs();
	}
}
