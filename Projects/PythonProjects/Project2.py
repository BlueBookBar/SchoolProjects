#Humza Galafano

#Import the right files
import sys, getopt

from shapely.geometry import Polygon, LineString, LinearRing, Point
from shapely.affinity import translate


#Class will handle the polygon objects
class MeteorObject(object):
    #initialize all the variables
    def __init__(self, line):
        self.arrayPoints = list(map(int, line.split()))
        self.arrayLength = len(self.arrayPoints)
        self.coordString = []
        self.NumbEdges = self.arrayPoints[0]
        self.NumbEdges = int(self.NumbEdges)
        self.midx = 0
        self.midy = 0

#Take the coordinates from the string and sort them into the polygon class
        i = 1
        self.coordString = [(self.arrayPoints[i],self.arrayPoints[i+1])]
        for x in range(0, self.NumbEdges-1):
            i=i+2
            self.coordString = self.coordString + [(self.arrayPoints[i],self.arrayPoints[i+1])]
            self.midx = self.midx + int(self.arrayPoints[i])
     #       print()
            self.midy = self.midy +  int(self.arrayPoints[i+1])
     #       print()
        i=i+2
        self.movementXPlane = self.arrayPoints[i];
        i=i+1
        self.movementYPlane = self.arrayPoints[i];


        self.checkInput()

        self.midpoint = Point(self.midx/self.NumbEdges, self.midy/self.NumbEdges)


        self.lineskel = LinearRing(self.coordString)
        self.Body = Polygon(self.lineskel)


#shift the points and polygons
    def shiftObject(self, time):
        self.Body = translate(self.Body, self.movementXPlane * time, self.movementYPlane * time)
        self.midpoint = translate(self.midpoint, self.movementXPlane * time, self.movementYPlane * time)

#check if the input is correct
    def checkInput(self):
        if not ( 3 <= self.arrayPoints[0] <= 10):
            sys.exit("Error, not enough vertices.")
        if not(-100 <=  self.arrayPoints[self.arrayLength-2] <= 100):
            sys.exit("Error, object is too fast.")
        if not(-100 <=  self.arrayPoints[self.arrayLength-1] <= 100):
            sys.exit("Error, object is too fast.")
        for x in range(1, self.arrayLength-3):
            if not(-100 <=  self.movementYPlane <= 100):
                sys.exit("Error, an edge is too far off the grid.")



def distanceMidpoint(metor):
    return metor[0].midpoint.distance(metor[1].midpoint)




#Main function
#Read file is hardcoded as input.txt
#Write file is hardcoded as output.txt
if __name__ == "__main__":
    meteors=[]
    numObj=0
    outfile = open('output.txt', 'w')


    with open('input.txt','r') as infile:
        for line in infile:
            numObj = numObj+1
            meteors.append(MeteorObject(line))
    infile.close()

#Begin the iterations for the search
    InitialDistance = distanceMidpoint(meteors)
    NewDistance = 0
    immediateTime=0
    perfectTime=0
    timeInterval=.001
    maximumarea=0
    inter = 0
    while (NewDistance < InitialDistance):#change this to while distance has not been ecxceeded
        if(meteors[0].Body.intersects(meteors[1].Body)):
            intersectedArea = Polygon(meteors[0].Body.intersection(meteors[1].Body))
            inter = 1
            if(intersectedArea.area > maximumarea):
                maximumarea = intersectedArea.area
                perfectTime = immediateTime
        elif(inter == 1 and (maximumarea > 0)):
            break
        meteors[0].shiftObject(timeInterval)
        meteors[1].shiftObject(timeInterval)
        NewDistance = distanceMidpoint(meteors)
        #if((NewDistance < InitialDistance/2) and timeInterval == .01):
        #    timeInterval = .0001
        immediateTime += timeInterval
    if(maximumarea > 0):
        outfile.write("The Greatest overlap is " + str(maximumarea)+ " and the time is "+ str(perfectTime) + ".")
    else:
        outfile.write("They never meet, there is no collision.")
    outfile.close()




