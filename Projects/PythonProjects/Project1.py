import sys, math

#Open the output and Input files


#Checks argument parameters and exits if incorrect



def Check_args(p, a, b, c, d, k):
    if int(p) < 1 and int(p) >1000:
        sys.exit("Input numbers are not in range.")
    if int(a) < 0 and int(a) >1000 and int(b) < 0 and int(b) >1000 and int(c) < 0 and int(c) >1000 and int(d) < 0 and int(d) >1000:
        sys.exit("Input numbers are not in range.")
    if int(p) < 1 and int(p) >1000000:
        sys.exit("Input numbers are not in range.")

        
#Runs the numbers through the equation and puts into the array
def MEquation(p, a, b, c, d, k):
    priceArry=[]
    sa=float(a)
    sb=float(b)
    sc=float(c)
    sd=float(d)
    sk=int(k)
    sp= float(p)
    value = float(0)
    for i in range (0, sk, 1):
        value = sp * (math.sin(sa * i + sb) + math.cos(sc * i + sd) + 2)
        priceArry.append(value)
    return priceArry

#Checks the difference in prices in the array
def PriceDiff(ar):
    topfl, topnum = 0,0
    for i in range (len(ar)):
        topfl = max(topfl, topnum - ar[i])
        topnum = max(topnum, ar[i])
    return topfl
    
#Main function
#Read file is hardcoded as input.txt
#Write file is hardcoded as output.txt
if __name__ == "__main__":
    outfile = open('output.txt', 'w')
    priceArry = []
    with open('input.txt','r') as infile:
        for line in infile:
            arrayofArgs = line.split()
            if len(arrayofArgs) != 6:
                sys.exit("Argument error");
            Check_args(arrayofArgs[0], arrayofArgs[1], arrayofArgs[2], arrayofArgs[3], arrayofArgs[4], arrayofArgs[5])
            priceArry = MEquation(arrayofArgs[0], arrayofArgs[1], arrayofArgs[2], arrayofArgs[3], arrayofArgs[4], arrayofArgs[5])
            outfile.write("Input: %s %s %s %s %s %s \n" %(arrayofArgs[0], arrayofArgs[1], arrayofArgs[2], arrayofArgs[3], arrayofArgs[4], arrayofArgs[5]))
            outfile.write("Output: %.6f  \n" %(PriceDiff(priceArry)) )
    outfile.close()
    
