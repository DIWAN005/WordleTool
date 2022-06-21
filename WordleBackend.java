import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class WordleBackend {
    private String fileName;
    private String[] words;
    private String currentWord;

    public WordleBackend(String fileName, int numberOfWords)
    {
        this.fileName = fileName;
        words = new String[numberOfWords];
    }

    public void loadWords() throws FileNotFoundException {
        try {
            File wordList = new File(fileName);
            Scanner reader = new Scanner(wordList);
            int index = 0;
            while (reader.hasNextLine()) {
                String iteratedWord = reader.nextLine();
                words[index] = iteratedWord;
                index++;
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred");
        }
    }

    public String getNewWord()
    {
        Random r = new Random();
        int randomIndex = r.nextInt(words.length + 1);
        currentWord = words[randomIndex];
        System.out.println(currentWord);
        return currentWord;
    }

    public boolean isValidWord(String guessedWord)
    {
        for(int i = 0; i < words.length; i++)
        {
            if (guessedWord.equals(words[i]))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrectWord(String guessedWord)
    {
        return guessedWord.equals(currentWord);
    }

    public int[] whichLettersCorrect(String guessedWord)
    {
        int[] correctLetters = new int[guessedWord.length()];
        for (int i = 0; i < correctLetters.length; i++)
        {
            if(guessedWord.charAt(i) == currentWord.charAt(i))
            {
                correctLetters[i] = 2;
            }
            else
            {
                correctLetters[i] = 0;
            }
        }
        for (int i = 0; i < correctLetters.length; i++)
        {
            for (int j = 0; j < correctLetters.length; j++)
            {
                if (guessedWord.charAt(i) == currentWord.charAt(j) && correctLetters[i] != 2)
                {
                    correctLetters[i] = 1;
                }
            }
        }
        return correctLetters;
    }

    public boolean hasWon(int[] correctLetters)
    {
        for (int i = 0; i < correctLetters.length; i++)
        {
            if (correctLetters[i] != 2)
            {
                return false;
            }
        }
        return true;
    }
}
