import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SetupPage {
    private static JPanel setupPanel;
    private static JFrame frame;
    private static JLabel channelNameText;
    private static JTextField channelNameInput;
    private static JLabel oAuthText;
    private static JTextField oAuthInput;
    private static JButton setupButton;
    private static String filePath = "setup.txt";
    private static File setupFile;
    private static boolean existingoAuth = true;
    private static Path setupPath;
    private static String oAuth;
    private static String channelName;
    private static WordleGUI game = new WordleGUI();
    private static TwitchChatReader reader = new TwitchChatReader();

    public SetupPage() {
    }


    public static void main(String[] args) throws IOException {
        setupPath = Paths.get(filePath);
        boolean setupExists = Files.exists(setupPath);

        setupPanel = new JPanel();
        frame = new JFrame();
        frame.setSize(220, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wordle GUI");
        frame.setLocationRelativeTo(null);
        frame.add(setupPanel);

        setupPanel.setLayout(null);
        channelNameText = new JLabel("Enter your channel name: ");
        channelNameText.setBounds(10, 50, 180, 25);
        setupPanel.add(channelNameText);

        channelNameInput = new JTextField();
        channelNameInput.setBounds(20, 80, 160, 25);
        setupPanel.add(channelNameInput);

        if(!setupExists) {
            existingoAuth = false;
            setupFile = new File(filePath);
            setupPanel.setLayout(null);
            oAuthText = new JLabel("Enter your Twitch OAuth: ");
            oAuthText.setBounds(10, 110, 180, 25);
            setupPanel.add(oAuthText);

            oAuthInput = new JTextField();
            oAuthInput.setBounds(20, 140, 160, 25);
            setupPanel.add(oAuthInput);
        }


        setupButton = new JButton("Enter");
        setupButton.setBounds(60, 180, 80, 25);
        setupPanel.add(setupButton);
        frame.setVisible(true);
        setupButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                if (!existingoAuth) {
                    String oAuth = oAuthInput.getText();
                    try {
                        Files.writeString(setupPath, "oauth:" + oAuth);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    Scanner reader = null;
                    try {
                        setupFile = new File(filePath);
                        reader = new Scanner(setupFile);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    oAuth = reader.nextLine();
                }

                channelName = channelNameInput.getText();
                setupPanel.setVisible(false);
                game = new WordleGUI();
                try {
                    reader.main(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    game.main(null);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static JFrame getFrame() {
        return frame;
    }

    public static String getoAuth() {
        return oAuth;
    }

    public static String getChannelName() {
        return channelName;
    }

    public static WordleGUI getGame() {
        return game;
    }

    public static TwitchChatReader getReader() {
        return reader;
    }
}
