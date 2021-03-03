# 1753번: 최단경로
# sparse graph여서 1차원 배열에 dict형식으로 graph 구현
# 특정 노드에서, 모든 노드의 최단 거리를 구하는 문제임으로, 다익스트라로 풀이 함
# 다익스트라는 우선순위 큐로 구현함

import sys
import heapq  # 우선순위 큐 구현을 위함

V, E = list(map(int, input().split()))
graph = [ {} for i in range(V+1) ]

K = int(input())
for i in range(E):
    u, v, w = list(map(int, input().split()))
    try:
        graph[u][v] = min( graph[u][v], w )
    except:
        graph[u][v] = w

distances = [ sys.maxsize for i in range(V+1) ]
distances[K] = 0
heap = []
heapq.heappush(heap, [ distances[K], K ])  # 시작 노드부터 탐색 시작 하기 위함.
while heap:
    minDist, node = heapq.heappop(heap)  # 탐색 할 노드의 거리와, 노드 번호
    
    if distances[node] < minDist: continue
            
    for nextNode, nextDist in graph[node].items():
        tmpDist = minDist + nextDist  # 해당 노드를 거쳐 갈 때 거리
        if tmpDist < distances[nextNode]:  # 알고 있는 거리 보다 작으면 갱신
            distances[nextNode] = tmpDist
            heapq.heappush(heap, [tmpDist, nextNode])  # 다음 인접 거리를 계산 하기 위해 큐에 삽입
            
for node in range( 1, V+1 ):
    if distances[node] == sys.maxsize: print( 'INF' )
    else: print( distances[node] )