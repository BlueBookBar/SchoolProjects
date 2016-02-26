
def perm2(A, k):
    r = [[]]
    for i in range(k):
        r = [[a] + b for a in A for b in r if a not in b]
    return r


def perm3(A):
    r=[[]]
    k = 3
    for i in range(3):
        for a in A:
            for b in r:
                if a not in b:
                    r.append([a]+b)
    return r

if __name__ == "__main__":
    #lengtho=input('Enter the variable n, a list will generate based on the number entered(5 will generate list 1-5): ')
    #lengtho = int(lengtho)
    lengtho = 3
    a = []
    r=[[]]
    for i in range(0, lengtho):
        a.append(i+1)
    #print( perm2(a, 3))
    #print(perm3(a))
    g = ""
    g +=str(5)
    g+='dbcjshk'
    print(g[0])
    print(g[0:4])
    print("Hello")
