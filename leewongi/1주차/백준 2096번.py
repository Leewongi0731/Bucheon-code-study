# 2096번: 내려가기
import copy

N = int(input())
start = list(map(int, input().split()))
minV, maxV = copy.deepcopy( start ), copy.deepcopy( start )

for i in range(1,N):
    data = list(map(int, input().split()))
    tmp = copy.deepcopy( minV )
    minV[0] = min( tmp[0], tmp[1] ) + data[0]
    minV[1] = min( tmp[0], tmp[1], tmp[2] ) + data[1]
    minV[2] = min( tmp[1], tmp[2] ) + data[2]

    tmp = copy.deepcopy( maxV )
    maxV[0] = max( tmp[0], tmp[1] ) + data[0]
    maxV[1] = max( tmp[0], tmp[1], tmp[2] ) + data[1]
    maxV[2] = max( tmp[1], tmp[2] ) + data[2]

print( max(maxV), min(minV) )