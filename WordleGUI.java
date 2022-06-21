import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordleGUI extends JFrame implements ActionListener
{
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel title;
    private static JLabel stats;
    private static JTextField userText1;
    private static JLabel[] labels;
    private static WordleBackend game = new WordleBackend("words/fiveLetters.txt", 15920);

    public static Scanner scanner = new Scanner(System.in);
    public static final String ANSIReset = "\u001B[0m";
    public static final String ANSIYellow = "\u001B[33m";
    public static final String ANSIGreen = "\u001B[32m";

    static String[] possibleWords;
    static int tries;
    static int attempts;
    static char[] input;
    static long startTime;
    static char[] answer;
    static boolean winCheck;
    static String answerChosen;

    public static void main (String[] args) throws FileNotFoundException {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wordle GUI");
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        panel.setLayout(null);
        title = new JLabel("Wordle: ");
        title.setBounds(10, 20, 80, 25);
        panel.add(title);

        panel.setLayout(null);
        stats = new JLabel("Enter your guess: ");
        title.setBounds(10, 50, 180, 25);
        panel.add(stats);

        userText1 = new JTextField();
        userText1.addActionListener(new WordleGUI());
        userText1.setBounds(40, 80, 80, 25);
        panel.add(userText1);

        JButton button = new JButton("Enter");
        button.setBounds(100, 20, 80, 25);
        button.addActionListener(new WordleGUI());
        panel.add(button);

        labels = new JLabel[6];
        for (int i = 0; i < 6; i++)
        {
            labels[i] = new JLabel("<html><font size ='5' color=blue> ----- </font> <font");
            labels[i].setBounds(44, 80 + (i * 25), 80, 25);
            panel.add(labels[i]);
        }

        frame.setVisible(true);

        startGame();
    }

    public void actionPerformed(ActionEvent e)
    {
        buttonPressed();
    }

    public static void startGame() throws FileNotFoundException {
        game.loadWords();
        game.getNewWord();
        tries = 0;
        System.out.println("Wordle: Type A Five Letter Word");
    }

    public static void setNextLabel(String string){
        labels[tries - 1].setText(string);
    }

    public static void buttonPressed()
    {
        userText1.setBounds(40, 80 + ((tries + 1) * 25), 80, 25);
        String guess = userText1.getText();
        if (!(game.isValidWord(guess)))
        {
            System.out.println("Invalid word");
            return;
        }
        int[] correctLetters = game.whichLettersCorrect(guess);
        if (game.hasWon(correctLetters))
        {
            winCheck = true;
        }
        if (winCheck || tries > 5)
        {
//            endGame();
            return;
        }

        String[] letterColors = new String[correctLetters.length];
        for (int i = 0; i < letterColors.length; i++)
        {
            if (correctLetters[i] == 0)
            {
                letterColors[i] = "black";
            }
            if (correctLetters[i] == 1)
            {
                letterColors[i] = "orange";
            }
            if (correctLetters[i] == 2)
            {
                letterColors[i] = "green";
            }
        }
        tries++;
        System.out.println("Set colors to " + letterColors[0] + " " + letterColors[1] + " " + letterColors[2] + " " + letterColors[3] + " " + letterColors[4] + " User Input was" + guess);
        String finalString = (
                "<html><font size='5' color=" + letterColors[0] + "> " + guess.charAt(0) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[1] + "> " + guess.charAt(1) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[2] + "> " + guess.charAt(2) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[3] + "> " + guess.charAt(3) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[4] + "> " + guess.charAt(4) + "</font> <font            ");
        setNextLabel(finalString);

        userText1.setText("");

        for (int i = 0; i < correctLetters.length; i++)
        {
            if(correctLetters[i] == 0)
            {
                System.out.println(guess.charAt(i));
            }
            if(correctLetters[i] == 1)
            {
                System.out.print(ANSIYellow + guess.charAt(i) + ANSIReset);
            }
            if(correctLetters[i] == 2)
            {
                System.out.println(ANSIGreen + guess.charAt(i) + ANSIReset);
            }
        }


    }
}
