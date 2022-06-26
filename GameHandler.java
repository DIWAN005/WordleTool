import java.util.Date;

public class GameHandler {

    private static TwitchChatReader reader;
    private static SetupPage setup = new SetupPage();
    private static WordleGUI game = new WordleGUI();
    private static long currentTime;
    private static long minuteInMilliseconds = 10000;

    public static void main(String[] args) throws Exception {
        setup.main(null);
        game = setup.getGame();
        reader = setup.getReader();
        while (true) {
            while (game.getTries() < 6) {
                currentTime = game.getTimer();
                while (true) {
                    Date d = new Date();
                    long newTime = d.getTime();
                    long timer = Math.subtractExact(newTime, currentTime);
                    if (timer > minuteInMilliseconds) {
                        break;
                    }
                }
                LinkedList guessedWords = reader.getGuessedWords();
                if (guessedWords.getSize() == 0) {
                    game.resetTimer();
                    continue;
                }
                String guess = LinkedList.getHighestFrequency(guessedWords);
                game.buttonPressed(guess);
                LinkedList.clearLinkedList(guessedWords);
            }
        }
    }
}