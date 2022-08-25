//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main_13460 {
//	static int N, M;
//	static char[][] map;
//	// 빨간 구슬 좌표 0-y / 1-x
//	static int[] red = new int[2];
//	// 파란 구슬 좌표 0-y / 1-x
//	static int[] blue = new int[2];
//
//	static int[] dy = {-1,0,1,0};
//	static int[] dx = {0,1,0,-1};
//
//	// 결과값
//	static int res = Integer.MAX_VALUE;
//
//	// 누가 선으로 시작할지 알아보는 메서드
//	static int calculFirstTurn(int n, int ry, int rx,int by, int bx) {
//		// dy dx 에 더할 i인덱스 값이 n임
//		switch(n) {
//		// 상
//		case 0:
//			// 위로 갈때 빨간색 구슬 x좌표와 파란삭 x좌표가 다르다면 그냥 빨간색 먼저
//			if(rx != bx) return 0;
//			// 만약 빨, 파 x좌표가 같다면
//			else {
//				// x좌표가 같은데 y좌표가 빨간색이 작다면 빨간색이 위에 위치해 있음.
//				// 고로 위로 올라갈때 빨간색 먼저 진행.
//				if(ry < by) return 0;
//				// y좌표가 빨간색이 파란색보다 더 밑에 있으면 파란색 진행
//				else return 1;
//			}
//		// 우
//		case 1:
//			// 빨간색 y좌표랑 파란색 y좌표가 다르면 그냥 빨간색 먼저
//			if(ry != by) return 0;
//			// 같다면
//			else {
//				// 빨간색이 더 오른쪽에 있으면 빨간색 먼저
//				if(rx > bx) return 0;
//				// 빨간색이 왼쪽에 있으면 파란색 먼저
//				else return 1;
//			}
//		// 하
//		case 2:
//			// x좌표가 둘 다 다를때 빨간색 먼저
//			if(rx != bx) return 0;
//			// 같다면
//			else {
//				// 빨간색이 파란색보다 아래에 위치하면 빨간색 먼저
//				if(ry < by) return 1;
//				// 아니라면 파란색 먼저
//				else return 0;
//			}
//		// 좌
//		case 3:
//			// y가 둘다 다르면 빨간색 먼저
//			if(ry != by) return 0;
//			// 같다면
//			else {
//				// 빨간색이 오른쪽에 있으면 파란색 먼저
//				if(rx > bx) return 1;
//				// 왼쪽에 있으면 빨간색 먼저
//				else return 0;
//			}
//		}
//		return 0;
//	}
//
//	// dfs 시작 ( 빨 y , 빨 x , 파 y , 파 x , 카운트 , 가지치기를 위한 이전에 사용한 이동방향 번호
//	static void dfs(int ry, int rx,int by, int bx, int cnt, int prevH) {
//		
//		// 카운트 증가
//		cnt += 1;
//		
//		// 카운트가 10보다 크거나 결과값 보다 크다면 그냥 종료(가지치기)
//		// 결과값은 계속 최소값을 갱신 할 것이기에 더 크다면 뒤로 더 진행해볼 가치가 없음
//		if(cnt > 10 || cnt > res) 
//			return;
//		
//		// 선으로 시작하는지 체크하는 메서드 결과값 저장
//		int firstTurn = 0; // 0 - r , 1 - b
//		
//		// 빨, 파 구슬 골인 여부
//		boolean redGoal = false;
//		boolean blueGoal = false;
//		
//		// rny = 빨간색 구슬 다음 y좌표 지점 , rnx = 빨간색 구슬 다음 x좌표 지점
//		// bny = 파란색 구슬 다음 y좌표 지점 , bnx = 파란색 구슬 다음 x좌표 지점
//		int rny=ry, rnx=rx, bny=by, bnx=bx;
//		for(int i=0; i<4; i++) {
//			// 만약 빨, 파 공 둘 다 다음 지점이 벽으로 막혀있으면 해당 방향으로는 더이상 안해도 되서 가지치기
//			if(map[rny+dy[i]][rnx+dx[i]] == '#' && map[bny+dy[i]][bnx+dx[i]] == '#') {
//				continue;
//			}
//			
//			// 이전에 사용한 방향이랑 같거나 또는 이전 사용 방향+2 % 4 나머지랑 같다면 패스
//			// 즉 왼쪽으로 가서 재귀했을때 오른쪽으로 간다면 반복되므로 재귀 횟수만 늘어남.
//			// 그렇기 때문에 이전에 향했던 방향이 거나 향했던 방향 반대편이라면 진행시키지 않고 컨티뉴
//			if(prevH == i || (prevH+2)%4 == i) {
//				continue;
//			}
//			// 누가 선인지 확인.
//			firstTurn = calculFirstTurn(i, ry, rx, by, bx);
//			
//			// R 먼저
//			if(firstTurn == 0) {
//				rny = ry+dy[i];
//				rnx = rx+dx[i];
//				
//				// 빨간색 이동
//				while(true) {
//				
//					// 빈 공간이라면 계속 증가
//					if(map[rny][rnx] == '.') {
//						rny += dy[i];
//						rnx += dx[i];
//						continue;
//					}
//				
//					// 만약 빨간색 공이 골인 할 때
//					else if(map[rny][rnx] == 'O') {
//						redGoal = true;
//						break;
//					}
//				
//					// 벽에 막히면 한칸 이전으로 되돌리기
//					else if(map[rny][rnx] == '#') {
//				
//						// rny, rnx는 +dy dx 를 했는 상태이기때문에 이전 칸으로 되돌아가는게 빈칸임
//						rny -= dy[i];
//						rnx -= dx[i];
//						break;
//					}
//				}
//				
//				bny = by+dy[i];
//				bnx = bx+dx[i];
//			
//				// 파란색 이동
//				while(true) {
//					if(map[bny][bnx] == '.') {
//						bny += dy[i];
//						bnx += dx[i];
//						continue;
//					}
//			
//					// 골인해도 return하지 말기. 뒤에 최소의 경우의수가 나올 수 있기 때문 그냥 flag만 true로 하고 while만 탈출
//					else if(map[bny][bnx] == 'O') {
//						blueGoal = true;
//						break;
//					}
//					else if(map[bny][bnx] == '#') {
//						bny -= dy[i];
//						bnx -= dx[i];
//						break;
//					}
//				}
//			}
//		
//			// 파란색이 선 이라면
//			else {
//				bny = by+dy[i];
//				bnx = bx+dx[i];
//				while(true) {
//					if(map[bny][bnx] == '.') {
//						bny += dy[i];
//						bnx += dx[i];
//						continue;
//					}
//					else if(map[bny][bnx] == 'O') {
//						blueGoal = true;
//						break;
//					}
//					else if(map[bny][bnx] == '#') {
//						bny -= dy[i];
//						bnx -= dx[i];
//						break;
//					}
//				}
//				
//				// 파란색이 선인데 골인 한다면 빨간색은 할 필요가 없음
//				if(blueGoal == false) {
//					rny = ry+dy[i];
//					rnx = rx+dx[i];
//					
//					// 빨간색 이동
//					while(true) {
//						if(map[rny][rnx] == '.') {
//							rny += dy[i];
//							rnx += dx[i];
//							continue;
//						}
//						else if(map[rny][rnx] == 'O') {
//							redGoal = true;
//							break;
//						}
//						else if(map[rny][rnx] == '#') {
//							rny -= dy[i];
//							rnx -= dx[i];
//							break;
//						}
//					}					
//				}
//			}
//			
//			// 파란색이 골인 했을 때
//			if(blueGoal) {
//			
//				// 다음 경우의 수를 위해 rny ~ bnx 는 전부 다시 되돌리고 flag도 false로 한뒤에 뒤 코드들은 진행시키지 않음
//				blueGoal = false;
//				redGoal = false;
//				rny = ry; rnx= rx; bny = by; bnx = bx;
//				continue;
//			}
//			
//			// 만약 빨간색만 통과했다면 최소값 비교후 갱신하고 빨간공 flag를 false로
//			if(blueGoal == false && redGoal == true) {
//				res = Math.min(res, cnt);
//				redGoal = false;
//			}
//			
//			// 아무도 골인 하지 않았을 때
//			else if(blueGoal == false && redGoal == false) {
//				
//				// 만약 현재 빨간색 구슬과 파란색 구슬 좌표가 둘 다 같다면
//				if(rny == bny && rnx == bnx) {
//					// 빨간색과 파란색이 이동한 거리를 계산
//					int redDistance = Math.abs(ry-rny)+Math.abs(rx-rnx);
//					int blueDistance = Math.abs(by-bny)+Math.abs(bx-bnx);
//					
//					// 빨간색이 더 적게 이동했다면 파란색보다 앞서 있다는 뜻으로 파란색을 이전 한칸으로 되돌림
//					if(redDistance < blueDistance) {
//						bny -= dy[i];
//						bnx -= dx[i];
//					}
//					
//					// 빨간색이 더 많이 이동했다면 파란색보다 뒤에서 이동했다는 뜻, 빨간색을 이전 한칸으로
//					else {
//						rny -= dy[i];
//						rnx -= dx[i];
//					}
//				}
//				
//				// 만약 이리저리 움직였는데 새로 움직인 자리가 인자로 받은 위치랑 같다 == 움직임이 없다면 아래 재귀를 하지 않고
//				// 움직여서 좌표에 변화가 있으면 재귀 실행
//				if(rny != ry || rnx != rx || bny != by || bnx != bx)
//					dfs(rny, rnx, bny, bnx, cnt, i);
//			}
//			// 재귀가 끝나고 되돌아 왔으면 다음 경우의 수를 위해 빨간색, 파란색 구슬 좌표를 되돌려줌
//			rny = ry; rnx= rx; bny = by; bnx = bx;
//		}
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//
//		map = new char[N][M];
//
//		for(int y=0; y<N; y++) {
//			st = new StringTokenizer(br.readLine());
//			int x = 0;
//			for(char c : st.nextToken().toCharArray()) {
//				map[y][x] = c;
//				if(map[y][x] == 'B') {
//					blue[0] = y;
//					blue[1] = x;
//					map[y][x] = '.';
//				}
//				else if(map[y][x] == 'R') {
//					red[0] = y;
//					red[1] = x;
//					map[y][x] = '.';
//				}
//				x+=1;
//			}
//		}
//
//		dfs(red[0],red[1],blue[0],blue[1],0,-99);
//		if(res == Integer.MAX_VALUE) res = -1;
//		System.out.println(res);
//	}
//
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13460 {
	static int N, M;
	static char[][] map;
	// 빨간 구슬 좌표 0-y / 1-x
	static int[] red = new int[2];
	// 파란 구슬 좌표 0-y / 1-x
	static int[] blue = new int[2];

	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};

	// 결과값
	static int res = Integer.MAX_VALUE;

	// dfs 시작 ( 빨 y , 빨 x , 파 y , 파 x , 카운트 , 가지치기를 위한 이전에 사용한 이동방향 번호
	static void dfs(int ry, int rx,int by, int bx, int cnt, int prevH) {
		
		// 카운트 증가
		cnt += 1;
		
		// 카운트가 10보다 크거나 결과값 보다 크다면 그냥 종료(가지치기)
		// 결과값은 계속 최소값을 갱신 할 것이기에 더 크다면 뒤로 더 진행해볼 가치가 없음
		if(cnt > 10 || cnt > res) 
			return;
		
		// 빨, 파 구슬 골인 여부
		boolean redGoal = false;
		boolean blueGoal = false;
		
		// rny = 빨간색 구슬 다음 y좌표 지점 , rnx = 빨간색 구슬 다음 x좌표 지점
		// bny = 파란색 구슬 다음 y좌표 지점 , bnx = 파란색 구슬 다음 x좌표 지점
		int rny=ry, rnx=rx, bny=by, bnx=bx;
		for(int i=0; i<4; i++) {
			// 만약 빨, 파 공 둘 다 다음 지점이 벽으로 막혀있으면 해당 방향으로는 더이상 안해도 되서 가지치기
			if(map[rny+dy[i]][rnx+dx[i]] == '#' && map[bny+dy[i]][bnx+dx[i]] == '#') {
				continue;
			}
			
			// 이전에 사용한 방향이랑 같거나 또는 이전 사용 방향+2 % 4 나머지랑 같다면 패스
			// 즉 왼쪽으로 가서 재귀했을때 오른쪽으로 간다면 반복되므로 재귀 횟수만 늘어남.
			// 그렇기 때문에 이전에 향했던 방향이 거나 향했던 방향 반대편이라면 진행시키지 않고 컨티뉴
			if(prevH == i || (prevH+2)%4 == i) {
				continue;
			}
			
			rny = ry+dy[i];
			rnx = rx+dx[i];
			
			// 빨간색 이동
			while(true) {
				
				// 빈 공간이라면 계속 증가
				if(map[rny][rnx] == '.') {
					rny += dy[i];
					rnx += dx[i];
					continue;
				}
				
				// 만약 빨간색 공이 골인 할 때
				else if(map[rny][rnx] == 'O') {
					redGoal = true;
					break;
				}
				
				// 벽에 막히면 한칸 이전으로 되돌리기
				else if(map[rny][rnx] == '#') {
					
					// rny, rnx는 +dy dx 를 했는 상태이기때문에 이전 칸으로 되돌아가는게 빈칸임
					rny -= dy[i];
					rnx -= dx[i];
					break;
				}
			}
			
			bny = by+dy[i];
			bnx = bx+dx[i];
			
			// 파란색 이동
			while(true) {
				if(map[bny][bnx] == '.') {
					bny += dy[i];
					bnx += dx[i];
					continue;
				}
				
				// 골인해도 return하지 말기. 뒤에 최소의 경우의수가 나올 수 있기 때문 그냥 flag만 true로 하고 while만 탈출
				else if(map[bny][bnx] == 'O') {
					blueGoal = true;
					break;
				}
				else if(map[bny][bnx] == '#') {
					bny -= dy[i];
					bnx -= dx[i];
					break;
				}
			}
			
			// 파란색이 골인 했을 때
			if(blueGoal) {
			
				// 다음 경우의 수를 위해 rny ~ bnx 는 전부 다시 되돌리고 flag도 false로 한뒤에 뒤 코드들은 진행시키지 않음
				blueGoal = false;
				redGoal = false;
				rny = ry; rnx= rx; bny = by; bnx = bx;
				continue;
			}
			
			// 만약 빨간색만 통과했다면 최소값 비교후 갱신하고 빨간공 flag를 false로
			if(blueGoal == false && redGoal == true) {
				res = Math.min(res, cnt);
				redGoal = false;
			}
			
			// 아무도 골인 하지 않았을 때
			else if(blueGoal == false && redGoal == false) {
				
				// 만약 현재 빨간색 구슬과 파란색 구슬 좌표가 둘 다 같다면
				if(rny == bny && rnx == bnx) {
					// 빨간색과 파란색이 이동한 거리를 계산
					int redDistance = Math.abs(ry-rny)+Math.abs(rx-rnx);
					int blueDistance = Math.abs(by-bny)+Math.abs(bx-bnx);
					
					// 빨간색이 더 적게 이동했다면 파란색보다 앞서 있다는 뜻으로 파란색을 이전 한칸으로 되돌림
					if(redDistance < blueDistance) {
						bny -= dy[i];
						bnx -= dx[i];
					}
					
					// 빨간색이 더 많이 이동했다면 파란색보다 뒤에서 이동했다는 뜻, 빨간색을 이전 한칸으로
					else {
						rny -= dy[i];
						rnx -= dx[i];
					}
				}
				
				// 만약 이리저리 움직였는데 새로 움직인 자리가 인자로 받은 위치랑 같다 == 움직임이 없다면 아래 재귀를 하지 않고
				// 움직여서 좌표에 변화가 있으면 재귀 실행
				if(rny != ry || rnx != rx || bny != by || bnx != bx)
					dfs(rny, rnx, bny, bnx, cnt, i);
			}
			// 재귀가 끝나고 되돌아 왔으면 다음 경우의 수를 위해 빨간색, 파란색 구슬 좌표를 되돌려줌
			rny = ry; rnx= rx; bny = by; bnx = bx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			int x = 0;
			for(char c : st.nextToken().toCharArray()) {
				map[y][x] = c;
				if(map[y][x] == 'B') {
					blue[0] = y;
					blue[1] = x;
					map[y][x] = '.';
				}
				else if(map[y][x] == 'R') {
					red[0] = y;
					red[1] = x;
					map[y][x] = '.';
				}
				x+=1;
			}
		}

		dfs(red[0],red[1],blue[0],blue[1],0,-99);
		if(res == Integer.MAX_VALUE) res = -1;
		System.out.println(res);
	}
}
