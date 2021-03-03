# 1916번: 최소비용 구하기
# start -> target으로 가는 최단 경로를 구하는 전형적인 문제
# Dense graph라 보기에는 간선의 수가 적어 1차원 배열로 graph 구현
# BFS를 이용하였고, visted는 cost로 계산하여 구현

from collections import deque
import sys

N = int(input())
M = int(input())
graph = [ {} for i in range(N+1) ]

for i in range(M):
    start, end, cost = list(map(int, input().split()))
    try:
        graph[start][end] = min( graph[start][end], cost )
    except:
        graph[start][end] = cost
        
start, target = list(map(int, input().split()))
cost = [ sys.maxsize for i in range(N+1) ] # nodeNum -> cost
cost[start] = 0
queue = deque()
queue.append( start )
while queue:
    city = queue.popleft()
    for nextCity, busCost in graph[city].items():
        # 현재 도시(city)에서 다음 도시로 가는 것이, 다음 도시로 가는 최단 경로를 update하는 것이라면,
        if cost[ nextCity ] > cost[city] + busCost:
            cost[ nextCity ] = cost[city] + busCost
            queue.append( nextCity )

print( cost[target] )