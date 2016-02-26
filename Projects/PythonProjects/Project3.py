import sys

class Node:
    #Initialize
    def __init__(self, letter = "" , row = None, column = None, left = None, right = None, up = None, down = None, distance= None):

        self.distance = 0
        self.letter = letter
        self.left = left
        self.right = right
        self.up = up
        self.down = down
        self.row = row
        self.column= column


        #get methods
    def getDistance(self):
        return self.distance
    def getColumn(self):
        return self.column
    def getRow(self):
        return self.row
    def getLetter(self):
        return self.letter
    def getLeft(self):
        return self.left
    def getRight(self):
        return self.right
    def getUp(self):
         return self.up
    def getDown(self):
        return self.down

        #set methods
    def setDistance(self, a):
        self.distance = a
    def setRow(self, a):
        self.row = a
    def setColumn(self, a):
        self.column = a
    def setLetter(self,a):
        self.letter = a
    def setLeft(self,a):
        self.left = a
    def setRight(self,a):
        self.right = a
    def setUp(self,a):
        self.up = a
    def setDown(self,a):
        self.down = a


class LinkedList:

    def __init__(self, row, column):
        self.dummyNode = Node("Uncounted", 1, -1)
        self.row = row
        self.column = column
        self.numberNodes = 0
        self.WORD= ""

    def changeRow(self, r):#changes row number but also adds dummy nodes for each row
        if int(r) < 1:
            raise ValueError("Row parameter is not valid: "+ r)
        else:
            self.row= r
            currentNode = self.dummyNode
            for i in range(1, int(self.row)):
                newDummy = Node("Uncounted", i, -1)
                currentNode.setDown(newDummy)
                newDummy.setUp(currentNode)
                currentNode= newDummy


    def changeColumn(self, c):#changes column number
        if int(c) > 50:
            raise ValueError("Column parameter is not valid: "+ c)
        else:
            self.column= c

    def changeWord(self, a):
        self.WORD= a

    def getWord(self):
        return self.WORD

    def getRow(self):
        return self.row

    def getColumn(self):
        return self.column

    #Adds a node to the LinkedList
    def addNode(self, letter, Xcoor, Ycoor):
        TempNode= Node(letter, Xcoor, Ycoor)
        if self.numberNodes == 0:#if it is a new LinkedList then add the first node
            self.dummyNode.setRight(TempNode)
            TempNode.setLeft(self.dummyNode)
            self.numberNodes+=1
        else:
            currentNode=self.dummyNode.getRight()
            if Xcoor is 1:#if on the first row, just add it on the line
                while currentNode.getRight() is not None:
                    currentNode= currentNode.getRight()
                currentNode.setRight(TempNode)
                TempNode.setLeft(currentNode)
                self.numberNodes+=1
            else:
                currentNode= self.dummyNode
                for i in range(1,Xcoor):
                    currentNode= currentNode.getDown()
                if currentNode.getRight() is not None:
                    while currentNode.getRight() is not None:
                        currentNode = currentNode.getRight()
                    currentNode.setRight(TempNode)
                    TempNode.setLeft(currentNode)
                    self.numberNodes+=1
                else:
                    currentNode.setRight(TempNode)
                    TempNode.setLeft(currentNode)
                    self.numberNodes+=1



    #Prints the list
    def printlist(self):
        if self.numberNodes==0:
            return
        currentNode=self.dummyNode
        currentDummy = self.dummyNode
        for i in range(1,int(self.row)+1):
            currentNode=currentDummy.getRight()
            outWord = ""
            while currentNode.getRight() is not None:
                outWord += currentNode.getLetter()
                currentNode= currentNode.getRight()
            outWord += currentNode.getLetter()+"\n"
            print(outWord )
            currentDummy = currentDummy.getDown()




    #Connects up and down channels for Nodes so that they can be used
    def upDownConnector(self):
        if self.dummyNode.getDown() is None:
            return
        topDummy= self.dummyNode
        bottomDummy= self.dummyNode.getDown()
        topCurrent =topDummy
        bottomCurrent = bottomDummy
        while bottomDummy.getDown() is not None:
            topCurrent = topDummy
            bottomCurrent = bottomDummy
            while (topCurrent.getRight() is not None) and (bottomCurrent.getRight() is not None):
                topCurrent.setDown(bottomCurrent)
                bottomCurrent.setUp(topCurrent)
                topCurrent= topCurrent.getRight()
                bottomCurrent = bottomCurrent.getRight()
            temp = bottomDummy.getDown()
            topDummy = bottomDummy
            bottomDummy = temp
        topCurrent =topDummy
        bottomCurrent = bottomDummy
        while (topCurrent.getRight() is not None) and (bottomCurrent.getRight() is not None):
            topCurrent.setDown(bottomCurrent)
            bottomCurrent.setUp(topCurrent)
            topCurrent= topCurrent.getRight()
            bottomCurrent = bottomCurrent.getRight()


    def searchNextPoint(self, startNode, searchLetter):
        currentNode=self.dummyNode
        currentDummy = self.dummyNode
        newDistance = self.numberNodes+1
        tempX=0
        tempY=0
        endNode = self.dummyNode
        #REMEMBER TO ADD FOR WHEN THERE IS ONLY ONE ROW
        if self.row==1:
            currentNode=currentDummy.getRight()
            while currentNode.getRight() is not None:
                if currentNode.getLetter() == searchLetter:#here find the right letter
                    tempX = abs(startNode.getRow() - currentNode.getRow())
                    tempY = abs(startNode.getColumn() - currentNode.getColumn())
                    if newDistance > (tempX+tempY):
                        newDistance= tempX+tempY
                        endNode = currentNode
                        endNode.setDistance(newDistance)
                currentNode= currentNode.getRight()
            endNode.setDistance(newDistance+1)
            return endNode
        else:
            for i in range(1,int(self.row)+1):
                currentNode=currentDummy.getRight()
                while currentNode.getRight() is not None:
                    if currentNode.getLetter() == searchLetter:#here find the right letter
                        tempX = abs(startNode.getRow() - currentNode.getRow())
                        tempY = abs(startNode.getColumn() - currentNode.getColumn())
                        if newDistance > (tempX+tempY):
                            newDistance= tempX+tempY
                            endNode = currentNode
                            endNode.setDistance(newDistance)
                    currentNode= currentNode.getRight()
                currentDummy = currentDummy.getDown()
            endNode.setDistance(newDistance+1)
            return endNode



    def runVirtualKeyboard(self):
        statement = self.getWord()
        newNode = self.dummyNode
        TotalDistance=0
        for i in range(0,len(statement)):
            TempNode= self.searchNextPoint(newNode, statement[i])
            TotalDistance +=TempNode.getDistance()#Add one for pressing button
            newNode=TempNode
        TempNode= self.searchNextPoint(newNode, "*")
        TotalDistance +=TempNode.getDistance()#Add one for pressing button
        newNode=TempNode
        return (TotalDistance)




#Checks if the variable is a number(whether in string format or not)
def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False








#Takes the input file and makes a linkedlist for the keyboard
def initKeyboard(file):
    a=""
    lineNumber=0
    previousLine= None
    LL= LinkedList(-1, -1)
    with open(file,'r') as infile:
        for line in infile:
            if not previousLine== None:
                tempString = previousLine
                for i in range(len(previousLine)):
                    a= tempString[i]
                    if is_number(a):
                        if LL.getRow()==-1:
                            LL.changeRow(a)
                        elif LL.getColumn()==-1:
                            LL.changeColumn(a)
                    elif a is not " ":#addNode
                        LL.addNode(tempString[i], lineNumber, i)
                lineNumber+=1
            previousLine= line
        LL.changeWord(previousLine)
        LL.upDownConnector()
    return LL



#Main function
#Read file is hardcoded as input.txt
#Write file is hardcoded as output.txt
if __name__ == "__main__":
    a = initKeyboard("input.txt")
    temp = str(a.runVirtualKeyboard())
    outfile = open('output.txt', 'w')
    outfile.write(temp)
    outfile.close()