import org.jibble.pircbot.*;

public class TwitchChatReader extends PircBot {

    private static final String ADDRESS = "irc.twitch.tv";
    private static final int PORT = 6667;
    private static  String OAuth = null;
    private static String channelName = null;
    private static LinkedList guessedWords = new LinkedList();
    private static final String wordIdentifier = "!sw ";
    private static SetupPage setup;

    public TwitchChatReader() {

    }

    public static void main(String[] args) throws Exception {
        OAuth = setup.getoAuth();
        channelName = setup.getChannelName();
        TwitchChatReader bot = new TwitchChatReader();
        bot.setVerbose(true); // Enable debugging input

        bot.setName("StreamyWordle");
        bot.setLogin("StreamyWordle");

        try {
            bot.connect(ADDRESS, PORT, OAuth);
        }
        catch (NickAlreadyInUseException e) {
            System.out.println("ERROR: Nickname in use");
        }
        catch (IrcException e) {
            System.out.println("ERROR: Server did not accept connection");
            e.printStackTrace();
        }
    }

    @Override
    protected void onConnect() {
        System.out.println("Connected!");
        joinChannel("#" + channelName);
        sendMessage("#" + channelName, "Hello, I'm StreamyWordle! To play, type !sw and your 5-letter guess separated by a space.");
        super.onConnect();
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        System.out.println(login + " joined channel " + channel);
        super.onJoin(channel, sender, login, hostname);
    }

    @Override
    protected void onUserList(String channel, User[] users) {
        for (User user : users) {
            System.out.println(user);
        }
        super.onUserList(channel, users);
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message)
    {

        String parsedMessage = null;
        if (message.startsWith(wordIdentifier))
        {
            parsedMessage = message.substring(4);
        }
        if(parsedMessage != null)
        {
            if(parsedMessage.length() == 5) {
                LinkedList.increaseFrequency(guessedWords, parsedMessage);
                LinkedList.printList(guessedWords);
            }
        }
    }

    public LinkedList getGuessedWords()
    {
        return guessedWords;
    }
}
