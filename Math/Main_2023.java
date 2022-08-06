import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2022 - 08 - 05 오늘의 문제
 * Question Name 	:	신기한 소수
 * Question Number 	:	2023
 * Question Tier	:	G5
 * Question Link	:	https://www.acmicpc.net/problem/2023
 */

/*
 * 진행 결과 (시간 측정은 시간측정 함수를 사용해서 로컬에서 테스트)
 * 1. 처음에는 반복문 돌려서 1부터 정해진 자릿수까지 for문으로 돌려가며 소수 판별. (10초 넘게 나옴)
 * 2.(1 개선) 소수 판별에서 sqrt를 사용 ( 8초 걸림 )
 * 3.(2 개선) 각 자리수를 뗄때 예를 들면 10000초 일 경우 for문으로 10을 곱했음 -> 맨 앞자리 구하려면 Math.pow(10,number-1) 를 사용방식으로 바꿈 ( 4초 걸림 )
 * 4.(3 개선) 맨 앞자리가 2,3,5,7만 나오도록 바꿈 (2.5 ~ 2.7초 나옴)
 * 5.(4 개선) 애초에 처음부터 2,3,5,7로 시작하도록 하고 for문으로 자리수 끝까지 탐색이 아니라 숫자를 더해가며 더한 수를 소수인지 판별해서 최종적으로 정한 자릿수만큼 더해서 소수를 판별 -> (0.001초)
 */

/*
 * 에라토스테네스의 체 사용해서 미리 만들어 놓는다면 메모리는 터진다.
 * 결과를 보면 맨 앞은 2,3,5,7만 나타나기에 맨앞은 2,3,5,7로 시작하게 만든다.
 * 자릿수 만큼 10을 곱하고 1, 3, 5, 7, 9 만 더해줘서 소수 판별을 돌리는 재귀함수(뒤에 수를 붙인 값과 카운트는 1감소시켜서 전달) 이용.
 * 소수 판별에서는 구하려는 수에서 sqrt() 루트 한 수 만큼 홀수나눠보고 나눠지지 않으면 소수 , 나눠지면 소수가 아님.
 * 최종적으로 0이 된다면 문제 규칙대로 모두 소수이므로 sb에 값 append.
 */
public class Main_2023 {
	static int number;
	static int[] arrN = {2,3,5,7};
	static StringBuilder sb = new StringBuilder();
	static boolean isPrime(int n) {		
		if(n % 2 == 0) return n==2?false:true;		
		else {
			for(int i=3; i<=Math.sqrt(n) ; i+=2) {
				if(n%i == 0) return true;
			}
		}		
		return false;
	}
	
	static void recursive(int n, int cnt) {
		if(cnt == 0) {
			sb.append(n).append("\n");
		}
		
		for(int i=1; i<10; i+=2) {
			int tempN = n*10+i;
			if(!isPrime(tempN)) {
				recursive(tempN,cnt-1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		number = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<4; i++) {
			recursive(arrN[i],number-1);
		}
		
		System.out.println(sb);
	}
}
