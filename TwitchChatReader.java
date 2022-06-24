import org.jibble.pircbot.*;

import java.io.FileNotFoundException;
import java.util.Date;

public class TwitchChatReader extends PircBot {

    private static final String ADDRESS = "irc.twitch.tv";
    private static final int PORT = 6667;
    private static final String OAUTH = "oauth:tzfikt8wosnxgviv76udidn867pt2w";
    private static LinkedList guessedWords = new LinkedList();
    private static final String wordIdentifier = "!sw ";

    public TwitchChatReader() {

    }

    public static void main(String[] args) throws Exception {
        TwitchChatReader bot = new TwitchChatReader();
        bot.setVerbose(true); // Enable debugging input

        bot.setName("itsskydonut");
        bot.setLogin("itsskydonut");

        try {
            bot.connect(ADDRESS, PORT, OAUTH);
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
        joinChannel("#scientifix");
        sendMessage("#scientifix", "Hello, I'm StreamyWordle! To play, type !sw and your 5-letter guess separated by a space.");
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
