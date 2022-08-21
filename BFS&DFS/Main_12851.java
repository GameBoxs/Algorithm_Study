import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_12851 {
	static int N, M;
	static int[] v;
	static int count = 0;
	static int minN = Integer.MAX_VALUE;
	
	static void bfs() {
		int next = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		while(!q.isEmpty()) {
			int n = q.poll();
			
			if(v[n] > minN) {
				return;
			}
			
			for(int i=0; i<3; i++) {
				if(i == 0) next = n + 1;
				if(i == 1) next = n - 1;
				if(i == 2) next = n * 2;
				
				if(next == M) {
					minN = v[n];
					count += 1;
				}
				
				if(next>=0 && next<100000) {
					if(v[next] == 0 || v[next] == v[n]+1) {
						q.add(next);
						v[next] = v[n] +1;
					}
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		v = new int[100001];
		
		if(N > M) {
			System.out.println(N-M);
			System.out.println(1);
			System.exit(0);
		}
		if(N == M) {
			System.out.println(0);
			System.out.println(1);
			System.exit(0);
		}
		
		v[N] = 1;
		bfs();
		
		System.out.println(minN);
		System.out.println(count);
	}

}
