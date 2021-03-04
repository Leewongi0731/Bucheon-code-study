# 1865번: 웜홀
# 음의 가중치가 존재하기 떄문에, 다익스트는 사용할 수 없고, 벨만 포드 방정식을 이용해야 함
# 그래프가 모두 이어져있다는 조건이 없으므로, 각 노드의 군집에서 모두 확인해 주어야함
import sys

def bellman_ford(start):
    global N
    visitedCheck[start] = True
    
    # 거리 값을 모두 무한대로 초기화, 시작지점의 거리는 0으로 set
    distance = [123456789 for i in range(N+1)]
    distance[start] = 0  
    
    # 간선 개수(V-1)만큼 반복
    for _ in range(N - 1):
        for node in range(1, N+1):
            if distance[node]==123456789: continue
            
            # 각 정점마다 모든 인접 정점들을 탐색
            for neighbour, cost in graph[node].items():  
                # (기존 인접 정점까지의 거리 > 기존 현재 정점까지 거리 + 현재 정점부터 인접 정점까지 거리)인 경우 갱신
                if distance[neighbour] > distance[node] + cost:
                    distance[neighbour] = distance[node] + cost                    
                    visitedCheck[neighbour] = True

    # 음수 사이클 존재 여부 검사 : V-1번 반복 이후에도 갱신할 거리 값이 존재한다면 음수 사이클 존재
    for node in range(1, N+1):
        if distance[node]==123456789: continue

        for neighbour, cost in graph[node].items():
            if distance[neighbour] > distance[node] + cost:
                return True

    return False
########################################################################
TC =  int(input())

for _ in range(TC):
    # node수 / 도로 수 / 웜홀 수
    N, M, W = list(map(int, input().split()))
    graph = [ {} for i in range(N+1) ]
    
    # 도로는 양방향, +가중치
    for i in range(M):
        S, E, T = list(map(int, input().split()))
        try:
            graph[S][E] = graph[E][S] = min( graph[S][E], T )
        except:
            graph[S][E] = graph[E][S] = T
            
            
    # 웜홀은 단방향, -가중치
    startCandidate = set()
    for i in range(W):
        S, E, T = list(map(int, input().split()))
        startCandidate.add(S)
        try:
            graph[S][E] = min( graph[S][E], -T )
        except:
            graph[S][E] = -T
    
    
    visitedCheck = [ False for i in range(N+1) ]
    possibleFlag = False
    for start in range(1, N+1):
        # 이전에 확인해본 node set이 아니고, 이번 node를 포함한 set에서 음의 사이클이 존재한다면
        if visitedCheck[start] == False and bellman_ford( start ):
            possibleFlag=True
            break
            
    if possibleFlag: print( "YES" )
    else: print( "NO" )