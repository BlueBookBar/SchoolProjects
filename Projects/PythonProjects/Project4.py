

#used the factorial number system
def Permutation(thisList):
    NumberofpossibleFactorial = [1]#Used to contain the number of factorial possiblities of the permutation

    for iterator in range(1,len(thisList)+1):#Populate the factorial list
        NumberofpossibleFactorial.append(NumberofpossibleFactorial[iterator-1]*iterator)# [1, 1, 2, 6, 24...]


    for iterator in range(0, NumberofpossibleFactorial[len(thisList)]):#Go loop through as many times as the factorial

        NewPermutationList = ""#Will hold the new permutation list
        OldPermutationList = str(thisList)#Will hold the old permutation list
        outerPosition = iterator
        for innerPosition in range(len(thisList), 0, -1):#loops and each time moves the approriate character from OldPermutationList to NewPermutationList
            selectedPosition = int(outerPosition/NumberofpossibleFactorial[innerPosition-1])#Divide the OuterPosition by the NumberofpossibleFactorial
            NewPermutationList =NewPermutationList+ OldPermutationList[selectedPosition]#Add the character from the OldList to the new list
            outerPosition %= NumberofpossibleFactorial[innerPosition - 1]#move the outposition to the next spot
            OldPermutationList = OldPermutationList[0:selectedPosition]+ OldPermutationList[selectedPosition+1:]# Remake the old list without the removed character
        print(NewPermutationList)#Print out the new variation of permutation



if __name__ == "__main__":
    lengtho=input('Enter the variable n, a list will generate based on the number entered(5 will generate list 0-4): ')
    lengtho = int(lengtho)# record the answer
    a = "" #Create the string
    for i in range(0, lengtho): #Populate the string with numbers 1, 2, 3...
        a+=str(i)
    Permutation(a)# call the permutation function