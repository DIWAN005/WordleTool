from asyncore import write


def loadWords():
    with open('words_alpha.txt') as word_file:
        validWords = set(word_file.read().split())
    return validWords


def writeWords():
    englishWords = loadWords()
    for id, word in enumerate(englishWords):
        if len(word) == 1:
            oneLetter = open("oneLetter.txt", "a")
            oneLetter.write(word + "\n")
        elif len(word) == 2:
            twoLetters = open("twoLetters.txt", "a")
            twoLetters.write(word + "\n")
        elif len(word) == 3:
            threeLetters = open("threeLetters.txt", "a")
            threeLetters.write(word + "\n")
        elif len(word) == 4:
            fourLetters = open("fourLetters.txt", "a")
            fourLetters.write(word + "\n")
        elif len(word) == 5:
            fiveLetters = open("fiveLetters.txt", "a")
            fiveLetters.write(word + "\n")
        elif len(word) == 6:
            sixLetters = open("sixLetters.txt", "a")
            sixLetters.write(word + "\n")
        elif len(word) == 7:
            sevenLetters = open("sevenLetters.txt", "a")
            sevenLetters.write(word + "\n")
        elif len(word) == 8:
            eightLetters = open("eightLetters.txt", "a")
            eightLetters.write(word + "\n")
        elif len(word) == 9:
            nineLetters = open("nineLetters.txt", "a")
            nineLetters.write(word + "\n")
        elif len(word) == 10:
            tenLetters = open("tenLetters.txt", "wt")
            tenLetters.write(word + "\n")
        elif len(word) == 11:
            elevenLetters = open("elevenLetters.txt", "wt")
            elevenLetters.write(word + "\n")
        elif len(word) == 12:
            twelveLetters = open("twelveLetters.txt", "wt")
            twelveLetters.write(word + "\n")
        print(id)
    oneLetter.close()
    twoLetters.close()
    threeLetters.close()
    fourLetters.close()
    fiveLetters.close()
    sixLetters.close()
    sevenLetters.close()
    eightLetters.close()
    nineLetters.close()
    tenLetters.close()
    elevenLetters.close()
    twelveLetters.close()

writeWords()