package practice.Backjoon.Control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Switch1244 {
	// int[] arr : 스위치 값 담을 배열
	static int[] arr;
	
	// int N : 스위치 개수, int studentN : 학생 수
	static int N, studentN;
	
	// void manSwitch(int selectN) : 남자의 경우 선택된 번호 배수 스위치 온/오프 메서드
	static void manSwitch(int selectN) {
		// index 는 1부터 N이하 까지
		for(int i=1; i<=N; i++) {
			
			// 만약 i가 선택된 번호 나머지가 0이라면
			if(i % selectN == 0) {
				
				// 스위치 상태를 현재에서 반대로
				arr[i] = arr[i] == 1 ? 0 : 1;
			}
		}
	}
	
	// void femaleSwitch(int selectN) : 여자의 경우 선택된 번호 앞/뒤 대칭이면서 많은 스위치를 포함하는 구간 스위치 온/오프 메서드
	static void femaleSwitch(int selectN) {
		// 자기 자신을 먼저 스위치 반대로 바꿔줌
		arr[selectN] = arr[selectN] == 1 ? 0 : 1;
		
		// int nextN : 자신으로 부터 몇번째 떨어져 있는지
		int nextN = 1;
		while(true) {
			// 자기자신 인덱스 -(or +) nextN 가  1~N 범위 사이여야 함.
			if(((selectN - nextN) >= 1) && ((selectN + nextN) <= N)) {
				
				// 만약 자기자신 인덱스 -(and +) nextN 가 값이 같다면
				if(arr[selectN - nextN] == arr[selectN + nextN]) {
					
					// 반전
					arr[selectN - nextN] = arr[selectN - nextN] == 1 ? 0 : 1;
					arr[selectN + nextN] = arr[selectN + nextN] == 1 ? 0 : 1;
				}
				
				// 만약 자기자신 인덱스 -(and +) nextN 가 값이 다르다면 while 탈출
				else
					break;
				nextN++;
			}
			// 범위를 벗어나면 탈출
			else
				break;
		}
	}
	public static void main(String[] args) throws IOException {
		//오늘 배운 BufferedReader , Stringtokenizer 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		studentN = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<studentN; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int selectN = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
				manSwitch(selectN);
			}
			else {
				femaleSwitch(selectN);
			}
		}
		

		for(int i=1; i<=N; i++) {
			if(i == N)
				System.out.print(arr[i]);
			else {
				// 20번째 일 경우 개행
				if(i%20 == 0) { 
					System.out.print(arr[i]);
					System.out.println();
				}
				else
					System.out.print(arr[i]+" ");
			}
		}
	}

}
