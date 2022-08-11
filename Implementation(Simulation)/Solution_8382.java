//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
// 
//public class Solution_8382 {
//     
//    private static int x1, x2, y1, y2, min;
// 
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        StringBuilder sb = new StringBuilder();
//         
//        int T = Integer.parseInt(br.readLine());
//        for (int t = 1; t <= T; t++) {
//            min = Integer.MAX_VALUE;
// 
//            st = new StringTokenizer(br.readLine());
//            x1 = Integer.parseInt(st.nextToken());
//            y1 = Integer.parseInt(st.nextToken());
//            x2 = Integer.parseInt(st.nextToken());
//            y2 = Integer.parseInt(st.nextToken());
//             
//            move(true);
//            move(false);
//            sb.append("#" + t + " " + min + "\n");
//        }
//        System.out.println(sb);
//    }
// 
//    private static void move(boolean flag) {
//        int dx = x1, dy = y1, count = 0;
//         
//        while (true) {
//            if (dx == x2 && dy == y2) {
//                if (min > count) min = count;
//                break;
//            }
//             
//            if (flag) {
//                if (dy > y2) dy--;
//                else dy++;
//                flag = false;
//                 
//            } else {
//                if (dx > x2) dx--;
//                else dx++;
//                flag = true;
//            }
//            count++;
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8382 {
	// y1/x1 : 출발지점, y2/x2 : 도착 지점
	static int T,x1,x2,y1,y2;

	// 좌표 변경
	static void changeCoordinate() {
		// 만약 출발지점이 0,0 이 아니라면 내가 사용하는 방식에선 계산을 해야하는데 어렵기 때문에 출발지점을 0으로 이동시키고
		// 이동한 만큼 도착 지점도 변경해줌.
		// ex) 출발지점(y,x) 1,1 도착 지점 0, 0 일 경우
		// 출발 지점을 0으로 만들려면 y축을 -1, x축을 -1 움직여 줘야 0,0이 됨.
		// 출발지점을 0으로 바꿔줬기에 도착지점도 y축을 -1, x축을 -1 해줘서 출발과 동일하게 움직여줌.
		// 계산하는데 -가 있을수도 있고 있으면 불편하기 때문에 Math.abs()를 사용하여 양수로 바꾸어줌. ( 예를들어 그래프 상으로 생각 할때,  도착지점이 y -1, x -1 일 경우 1사분면 쪽으로 동일한 위치에 뒤집는다 생각하면 됨)
		x2 += -(x1);
		y2 += -(y1);
		x2 = Math.abs(x2);
		y2 = Math.abs(y2);
		x1 = 0;
		y1 = 0;
	}
	
	// 만약에 도착지점 x or y 가 출발지점 x or y와 동일 할때 계산 하는 메서드
	static int calculCoordinate(int n) {
		// 인자는 동일한 축이 아닌 다른 축을 n으로 받음.
		// 만약 n이 짝수라면
		if(n%2 == 0) {
			// n*2
			return n*2;
		}
		else {
			// 짝수가 아니라면 n*2 -1 을 해줌
			return (n*2)-1;
		}
	}
	
	// 도착지점 x, y가 출발지점 x, y랑 다를때 계산하는 메서드
	static int calculCoordinate(int x, int y) {
		// x,y가 둘다 짝수거나 또는 둘 다 짝수가 아닐 경우
		if((y%2==0 && x%2==0)||(y%2!=0 && x%2!=0)) {
			// x, y 둘 중 최고 값을 가진 값 *2를 리턴
			return Math.max(x, y)*2;
		}
		else {
			// x, y 둘 중 하나가 짝수일 경우(ex.홀, 짝 / 짝 , 홀)
			// x, y 둘 중 최고 값 *2 -1
			return Math.max(x, y)*2-1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<T+1; t++) {
			st = new StringTokenizer(br.readLine());
			
			y1 = Integer.parseInt(st.nextToken());
			x1 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			
			
			sb.append("#").append(t).append(" ");
			changeCoordinate();
			
			///////  x 또는 y가 같을때 다른 축의 값을 메서드로 보냄  ////////
			if(x1 == x2) {
				sb.append(calculCoordinate(y2));
			}
			else if(y1 == y2) {
				sb.append(calculCoordinate(x2));
			}
			/////////////
			
			// x, y 둘 다 출발지점과 다를 때
			else {
				// 만약 도착지점 x,y 가 같으면
				if(x2==y2) {
					// 그냥 둘 다 더해줌
					sb.append(x2+y2);					
				}
				// 도착지점 x, y가 다르면 도착지점 x,y 를 메서드로 보냄
				else {
					sb.append(calculCoordinate(x2, y2));					
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}