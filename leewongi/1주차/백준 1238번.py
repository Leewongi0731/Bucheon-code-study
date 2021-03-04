# 1238번: 파티
# N : 마을 수, M : 도로 수(단방향), X : 목표
# N-1개의마을에서 X마을로 가는 최단거리는, reverse graph로 X에서 각 마을로의 최단거리와 같음
# 정방향, 역방향 그래프를 생성하고, 각 그래프에서 다익스트라로 모든 노드까지의 거리를 계산함
import heapq

def dijkstra( startCity, graph ):
    global N
    dist = [ 123456789 for i in range(N+1) ]
    dist[startCity] = 0
    
    queue = [[0, startCity]]
    while queue:
        cost, city = heapq.heappop( queue )
        
        if dist[city] < cost: continue
        
        for nextCity, roadCost in graph[city].items():
            if dist[nextCity] > dist[city] + roadCost:
                dist[nextCity] = dist[city] + roadCost
                heapq.heappush( queue, [dist[nextCity], nextCity] )
        
    return dist
############################################################
N, M, X = list(map(int, input().split()))

graph = [ {} for i in range(N+1) ]
reverseGraph = dist = [ {} for i in range(N+1) ]
for i in range(M):
    A, B, T = list(map(int, input().split()))
    graph[A][B] = T
    reverseGraph[B][A] = T

dist = dijkstra( X, graph )
reverseDist = dijkstra( X, reverseGraph )

result = 0
for city in range(1, N+1):
    result = max( result, dist[city] + reverseDist[city] ) 
print( result )