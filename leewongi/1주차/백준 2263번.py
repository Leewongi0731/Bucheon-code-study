# 2263번: 트리의 순회
# inorder / postorder의 정로보 preorder 출력하기
# postorder의 맨뒤에는 항상 분기점인 것과, inorder은 해당 분기점을 기준으로 왼쪽 / 오른쪽이 나뉘고 각 subTree의 node 갯수는 inorder, poster오더 모두 같아야함.
import sys
sys.setrecursionlimit(10**9)

def sol( inorderLeft, inorderRight, postorderLeft, postorderRight ):
    if inorderLeft <= inorderRight:
        head = postorder[postorderRight]
        
        headIndex = indexList[head]
        leftLen = headIndex - inorderLeft
        rightLen = inorderRight - headIndex
        
        # 가운데 VISIT
        print( head, end=' ' )
        # 왼쪽 VISIT
        sol( inorderLeft, headIndex-1, postorderLeft, postorderLeft+leftLen-1 )
        # 오른쪽 VISIT
        sol( headIndex+1, inorderRight, postorderLeft+leftLen, postorderRight-1 )
#################################################### 
n = int(input())
inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))

indexList = [ 0 for i in range(n+1) ]
for i, data in enumerate( inorder ): indexList[ data ] = i

sol(0, n-1, 0, n-1)