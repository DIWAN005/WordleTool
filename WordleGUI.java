import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class WordleGUI extends JFrame implements ActionListener
{
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel title;
    private static JLabel stats;
    private static JTextField userText1;
    private static JLabel[] labels;

    public static Scanner scanner = new Scanner(System.in);
    public static final String ANSIReset = "\u001B[0m";
    public static final String ANSIYellow = "\u001B[33m";
    public static final String ANSIGreen = "\u001B[32m";

    static String[] possibleWords;
    static int attempts;
    static char[] input;
    static long startTime;
    static char[] answer;
    static boolean done;
    static String answerChosen;

    public static void main (String[] args)
    {
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
        panel.add(stats);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
