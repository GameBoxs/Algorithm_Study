import java.util.Scanner;

/*
 * Question Name 	:	연산자 끼워넣기
 * Question Number 	:	14888
 * Question Tier	:	Silver1
 * Question Link	:	https://www.acmicpc.net/problem/14888
 */

public class Main_14888 {
	// 번호 갯수
	static int nCnt = 0;
	
	//번호 배열
	static int[] numberArr;
	
	// 연산자 순서
	// 예를 들면 1 2 1 1 이면 아래 배열에는
	// 1 2 2 3 4 이렇게 값이 들어감.
	static int[] operatorArr;
	
	// 조합에서 선택했는지 안했는지 체크
	static int[] visit;
	
	// 조합에서 선택됬을때 값이 들어가는 배열
	static int[] selectOperator;
	
	// 세번째 줄에서 받는 숫자들이 들어가는 배열
	static int[] operatorCnt = new int[4];
	static int maxN = Integer.MIN_VALUE;
	static int minN = Integer.MAX_VALUE;
	
	static int calcul(int n, int m, int n2) {
		switch(m){
		case 1:
			return n+n2;
		case 2:
			return n-n2;
		case 3:
			return n*n2;
		case 4:
			return n/n2;
		}
		return 0;
	}
	
	static void combination(int n, int r) {
		// 만약 r이 숫자 개수-1 과 같다면
		if(r==nCnt-1) {
			// 연산
			int sum = numberArr[0];
			for(int i=0; i<r; i++) {
				sum = calcul(sum,selectOperator[i],numberArr[i+1]);
			}
			maxN = Math.max(maxN, sum);
			minN = Math.min(minN, sum);
			return;
		}
		for(int i=0; i<n; i++) {
			if(visit[i] == 1) continue;
			visit[i] = 1;
			
			// 인덱스에 해당하는 값(operatorArr[i])을 선택했으므로 선택한 값을 selectOperator[r]의 배열에 넣음. 
			selectOperator[r] = operatorArr[i];
			
			// 조합을 계속 이어 나감
			combination(n,r+1);
			visit[i] = 0;
		}
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		nCnt = sc.nextInt();
		numberArr = new int[nCnt];
		for(int i=0; i<nCnt; i++) {
			numberArr[i] = sc.nextInt();
		}
		
		int index = 0;
		// 아래 3개는 연산자 갯수는 숫자 개수보다 1개 작아야 하므로 숫자 개수 보다 1작은 크기만큼 할당
		selectOperator = new int[nCnt-1];
		operatorArr = new int[nCnt-1];
		visit = new int[nCnt-1];
		
		
		for(int i=0; i<4; i++) {
			operatorCnt[i] = sc.nextInt();
			
			// 만약 연산자 개수가 0이 아닐때
			if(operatorCnt[i] != 0) {
				
				// 받은 개수 만큼 반복 돌리면서 operatorArr[] 배열에 값을 넣어줌
				for(int j=0; j<operatorCnt[i]; j++) {
					operatorArr[index] = i+1;
					index++;
				}
			}
		}
		combination(nCnt-1,0);
		
		System.out.println(maxN);
		System.out.println(minN);
	}

}
