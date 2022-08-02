import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * Question Name 	:	문자열 폭팔
 * Question Number 	:	9935
 * Question Tier	:	Gold4
 * Question Link	:	https://www.acmicpc.net/problem/9935
 */
public class Main_9935 {
	static String str, answer;
	static Stack<String> stack = new Stack<>();
	public static void main(String[] args) throws IOException {		
		// BufferedReader, StringTokenizer 사용으로 입력 시간 조금이라도 더 줄이기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 그냥 String 쓰지 않고 StringBuilder 사용
		StringBuilder sb = new StringBuilder();

		// str : 문장 , answer : 터지는 단어
		str = st.nextToken();
		st = new StringTokenizer(br.readLine());
		answer = st.nextToken();
		
		// 0부터 str 길이 만큼 반복
		for(int i=0; i<str.length(); i++) {
			// 일단 먼저 스택(String값으로 변환하여)에 저장
			stack.add(String.valueOf(str.charAt(i)));
			
			// append 해줌.
			sb.append(String.valueOf(str.charAt(i)));
			
			// 만약 현재 문장이 터지는 단어 마지막 글자와 같다면
			if(str.charAt(i) == answer.charAt(answer.length()-1)) {
				
				// 현재 쌓인 스택 사이즈가 터지는 단어 길이보다 커야함.
				if(stack.size() >= answer.length()) {
					
					// 플래그 사용
					boolean flag = false;
					
					// j는 0부터 터지는 단어 길이만큼 반복
					for(int j=0; j<answer.length(); j++) {
						// 만약 터지는 단어가 1개일 때
						if(answer.length() == 1) {
							// 현재 문장의 i번째 글자와 터지는 단어 j번째가 다르다면 flag를 변경 그리고 탈출
							if(str.charAt(i) != answer.charAt(j)) {
								flag = true;
								break;
							}							
						}
						// 터지는 단어 길이가 1보다 클 때
						else {
							// 스택 마지막(stack.size()) 에서 터지는 단어 길이(answer.lenght())만큼 빼고 + j를 해준다.
							/*
							 * 문장 (abcdabcdee) 터지는 단어(abcde)
							 * a	b	c	d	a	b	c	d	e	e
							 * 0	1	2	3	4	5	6	7	8	9
							 * 처음으로 터지는 단어를 만나는 인덱스 자리는 8임.
							 * 내가 사용한 방식은 터지는 단어 마지막 글자를 만나면 글자 자리수 만큼 빼줘서 나온 인덱스 부터 터진 단어를 비교함.
							 * 8에서 만나니 터지는 단어 길이 4를 빼주면 인덱스는 4에서 시작하여 비교함.
							 * j는 터지는 단어 0부터 터지는 단어 길이까지 이기에
							 * stack.get(8 - 4 + 0 = 4) = a
							 * String.valueOf(answer.charAt(0)) = a
							 * 이제 j가 1 증가하면
							 * stack.get(8 - 4 + 1 = 5) = b
							 * String.valueOf(answer.charAt(1)) = b
							 * ... 이런식으로 비교해서 끝날때 까지 글자가 다르지 않다면 flag는 안바뀜. (중간에 비교 두개 글자가 다르면 flag 값은 바뀜)
							 */
							if(!stack.get(stack.size()-answer.length()+j).equals(String.valueOf(answer.charAt(j)))) {
								flag = true;
								break;
							}	
						}
					}
					// flag가 false면 비교한 두 문장이 서로 같다는 것이므로 터지는 단어 길이 숫자 만큼 stack에서 pop 해줌
					if(!flag) {
						for(int j=0; j<answer.length(); j++) {
							stack.pop();
							// sb도 stack과 같이 제일 마지막 글자를 터지는 단어 길이 숫자 만큼 빼줌.
							sb.deleteCharAt(sb.length()-1);
						}
					}
				}
			}
		}
		// 최종적으로 끝났을때 stack의 사이즈가 0이라면
		if(stack.size() == 0)
		{
			System.out.println("FRULA");
		}
		// 남아 있다면 sb를 출력
		else {
			System.out.println(sb);
		}
	}
}