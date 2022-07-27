package practice.Backjoon.Control;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Josephus1158 {
	// int size : N , int turn : K
	static int size;
	static int turn;
	// Queue<Integer> q : 1~N까지 숫자가 들어가있는 큐
	static Queue<Integer> q = new LinkedList<>();
	
	// 1부터 size 만큼 q에 숫자 추가.
	static void InsertQueue() {
		for(int i=1; i<=size; i++) {
			q.add(i);
		}
	}

	// turn 간격 만큼 숫자가 빠짐.
	static StringBuilder OutQueue() {
		StringBuilder sb = new StringBuilder("<");
		// int t : 현재 간격
		int t = 1;
		
		// q가 비어있지 않다면 반복
		while(!q.isEmpty()) {
			
			// 만약 q의 크기가 1이면 1개만 남았으므로 빠진 숫자를 저장할 l에 q의 마지막 숫자를 넣고 q의 숫자를 빼냄으로써 q를 비움.
			if(q.size()==1) {
				sb.append(q.peek()).append(">");
				q.poll();
			}
			
			// q의 크기가 1 이상이라면.
			else {
				
				// 만약 현재 간격이 turn과 같다면 q의 맨앞 숫자를 l에 추가후 q의 맨앞 숫자를 빼냄. 그리고 현재 간격을 0으로 조정
				if(t==turn) {
					sb.append(q.peek()).append(", ");
					q.poll();
					t=0;
				}
				
				// 현재 간격이 turn과 다르다면 q의 맨앞숫자를 다시 q에 add하여 맨뒤로 넣고 맨앞의 수를 뺌.
				else {
					q.add(q.peek());
					q.poll();
				}
				
				// 현재 간격 증가.
				t++;
			}
		}
		return sb;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		turn = sc.nextInt();

		InsertQueue();
		StringBuilder sb = OutQueue();
		System.out.println(sb);
		sc.close();
	}
}
