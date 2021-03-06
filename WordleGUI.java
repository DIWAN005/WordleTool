import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Date;

public class WordleGUI extends JFrame {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel title;
    private static JLabel stats;
    private static JLabel invalidGuessMsg;
    private static JTextField userText1;
    private static JLabel[] labels;
    private static JButton enterButton;
    private static WordleBackend game = new WordleBackend("words/fiveLettersCommon.txt", 5757);
    private static int tries;
    private static boolean winCheck;
    private static boolean gameOver;
    private static long timer;

    public WordleGUI() {

    }

    public static void main(String[] args) throws FileNotFoundException {
        panel = new JPanel();
        frame = SetupPage.getFrame();
        frame.add(panel);

        panel.setLayout(null);
        title = new JLabel("WORDLE: ");
        title.setBounds(10, 20, 80, 25);
        panel.add(title);


        panel.setLayout(null);
        stats = new JLabel("Enter your guess: ");
        stats.setBounds(10, 50, 180, 25);
        panel.add(stats);

        userText1 = new JTextField();
        userText1.setBounds(40, 80, 80, 25);
        panel.add(userText1);

        enterButton = new JButton("Enter");
        enterButton.setMargin(new Insets(0, 0, 0, 0));
        enterButton.setBounds(100, 20, 80, 25);
        panel.add(enterButton);

        enterButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e) {
                invalidGuessMsg.setVisible(false);
                if (gameOver) {
                    enterButton.setText("Enter");
                    try {
                        main(null);
                        return;
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                String guess = userText1.getText();

                if (!(game.isValidWord(guess))) {
                    invalidGuessMsg.setVisible(true);
                } else {
                    buttonPressed(guess);
                }
            }

        });

        labels = new JLabel[6];
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel("<html><font size ='5' color=blue> ----- </font> <font");
            labels[i].setBounds(44, 80 + (i * 25), 80, 25);
            panel.add(labels[i]);
        }

        panel.setLayout(null);
        invalidGuessMsg = new JLabel("<html><font size='3' color=red> " + "Invalid Guess" + "</font> <font");
        invalidGuessMsg.setBounds(60, 230, 80, 25);
        panel.add(invalidGuessMsg);
        invalidGuessMsg.setVisible(false);

        panel.setVisible(true);

        gameOver = false;
        winCheck = false;
        tries = 0;

        startGame();
    }
    public static void startGame() throws FileNotFoundException {
        game.loadWords();
        game.getNewWord();
        tries = 0;
        Date d = new Date();
        resetTimer();
    }

    public static void setNextLabel(String string) {
        labels[tries - 1].setText(string);
    }

    public static void buttonPressed(String guess) {
        guess = guess.toLowerCase();
        resetTimer();
        userText1.setBounds(40, 80 + ((tries + 1) * 25), 80, 25);
        tries++;
        int[] correctLetters = game.whichLettersCorrect(guess);
        if (game.hasWon(correctLetters)) {
            winCheck = true;
        }
        String[] letterColors = new String[correctLetters.length];
        for (int i = 0; i < letterColors.length; i++) {
            if (correctLetters[i] == 0) {
                letterColors[i] = "black";
            }
            if (correctLetters[i] == 1) {
                letterColors[i] = "orange";
            }
            if (correctLetters[i] == 2) {
                letterColors[i] = "green";
            }
        }
        String finalString = (
                "<html><font size='5' color=" + letterColors[0] + "> " + guess.charAt(0) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[1] + "> " + guess.charAt(1) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[2] + "> " + guess.charAt(2) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[3] + "> " + guess.charAt(3) + "</font> <font            " +
                        "<html><font size='5' color=" + letterColors[4] + "> " + guess.charAt(4) + "</font> <font            ");
        setNextLabel(finalString);

        userText1.setText("");

        if (winCheck || tries > 5) {
            endGame(winCheck, game.getCurrentWord());
            return;
        }
    }

    public static void endGame(boolean winCheck, String currentWord) {
        userText1.setEnabled(false);
        userText1.setVisible(false);

        if (!winCheck) {
            stats.setText("<html><font size='3' color=red> " + new String(currentWord) + "</font> <font");
        }
        else {
            stats.setText("<html><font size='5' color=green> " + "You Win!" + "</font> <font");
        }
        gameOver = true;
        enterButton.setText("Play again");
    }

    public long getTimer()
    {
        return timer;
    }

    public long getCurrentTime()
    {
        Date d = new Date();
        return d.getTime();
    }

    public static void resetTimer()
    {
        Date d = new Date();
        timer = d.getTime();
    }

    public static int getTries()
    {
        return tries;
    }
}
