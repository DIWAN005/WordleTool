import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class WordleGUI extends JFrame implements ActionListener {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel title;
    private static JLabel stats;
    private static JLabel invalidGuessMsg;
    private static JTextField userText1;
    private static JLabel[] labels;
    private static JButton button;
    private static WordleBackend game = new WordleBackend("words/fiveLettersCommon.txt", 5757);
    static int tries;
    static boolean winCheck;
    static boolean gameOver;

    public static void main(String[] args) throws FileNotFoundException {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(220, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wordle GUI");
        frame.setLocationRelativeTo(null);
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
        userText1.addActionListener(new WordleGUI());
        userText1.setBounds(40, 80, 80, 25);
        panel.add(userText1);

        button = new JButton("Enter");
        button.setBounds(100, 20, 80, 25);
        button.addActionListener(new WordleGUI());
        panel.add(button);

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

        frame.setVisible(true);

        gameOver = false;
        winCheck = false;
        tries = 0;

        startGame();
    }

    public void actionPerformed(ActionEvent e) {
        invalidGuessMsg.setVisible(false);
        if(gameOver) {
            try {
                frame.dispose();
                main(null);
                return;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        String guess = userText1.getText();

        if (!(game.isValidWord(guess))) {
            invalidGuessMsg.setVisible(true);
            return;
        } else {
            buttonPressed(guess);
        }
    }

    public static void startGame() throws FileNotFoundException {
        game.loadWords();
        game.getNewWord();
        tries = 0;
    }

    public static void setNextLabel(String string) {
        labels[tries - 1].setText(string);
    }

    public static void buttonPressed(String guess) {
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
            stats.setText("<html><font size='3' color=red> " + "You lose. The correct answer was: " + new String(currentWord) + "</font> <font");
        }
        else {
            stats.setText("<html><font size='5' color=green> " + "You Win!" + "</font> <font");
        }
        gameOver = true;
        button.setBounds(100, 20, 80, 50);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setText("Play again");
    }
}
