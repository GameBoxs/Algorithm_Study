import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Question Name 	:	재귀함수가 뭔가요?
 * Question Number 	:	17478
 * Question Tier	:	Silver5
 * Question Link	:	https://www.acmicpc.net/problem/17478
 */
public class Main {
	// void Recursion(int start, int end) : 재귀 함수 , start는 현재 진행된 단계, end는 끝나야 하는 단계
	static void Recursion(int start, int end) {
		StringBuilder sb = new StringBuilder();
		// 0부터 현재 단계까지 sb에 ____를 append
		for(int i=0; i<start; i++)
			sb.append("____");
		
		// 만약 현재 진행단계가 0번째 라면(젤 처음)
		if(start == 0) {
			System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
			System.out.println("\"재귀함수가 뭔가요?\"");
			System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n"+
					"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"+
					"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			
			// 진행단계를 1 더해주고, end는 그대로 두고 함수 호출
			Recursion(start+1, end);
		}
		
		// 현재 진행 단계가 0이 아니라면
		else {
			System.out.println(sb+"\"재귀함수가 뭔가요?\"");
			//현재 진행 단계가 끝나야 하는 단계랑 다르다면
			if(start != end) {
				System.out.println(sb+"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n"+
						sb+"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"+
						sb+"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
				
				// 진행단계를 1 더해주고, end는 그대로 두고 함수 호출
				Recursion(start+1,end);
			}
			
			// 현재 진행단계가 끝나야 하는 단계랑 같다면
			else {
				System.out.println(sb+"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			}
		}
		
		// 마무리 출력
		System.out.println(sb+"라고 답변하였지.");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		Recursion(0,N);
	}

}
