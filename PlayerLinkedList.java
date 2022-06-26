public class PlayerLinkedList {
    private Players head;
    private static int size = 0;

    static class Players {
        String playerName;
        String word;
        Players next;

        public Players(String playerName, String word)
        {
            this.playerName = playerName;
            this.word = word;
            next = null;
        }
    }

    public static void insert(PlayerLinkedList list, String playerName, String word)
    {
        Players new_node = new Players(playerName, word);
        new_node.next = null;

        if (list.head == null) {
            list.head = new_node;
        }
        else {
            Players last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        size++;
    }

    public static PlayerLinkedList deleteWord(PlayerLinkedList list, String playerName)
    {
        Players currNode = list.head;
        Players prev = null;

        // CASE 1:
        // If head node itself holds the key to be deleted
        if (currNode != null && playerName.equals(currNode.playerName)) {
            list.head = currNode.next; // Changed head
            size--;
            return list;
        }

        // CASE 2:
        // If the key is somewhere other than at head
        while (currNode != null && !(playerName.equals(currNode.playerName))) {
            prev = currNode;
            currNode = currNode.next;
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if (currNode != null) {
            prev.next = currNode.next;
            size--;
        }
        return list;
    }

    // returns previous guess if player had one. null if they did not.
    public static String replacePrevGuess(PlayerLinkedList list, String playerName, String newGuess) {
        PlayerLinkedList.Players currNode = list.head;
        while (currNode != null) {
            if (playerName.equals(currNode.playerName)) {
                String prevGuess = currNode.word;
                currNode.word = newGuess;
                return prevGuess;
            }
            currNode = currNode.next;
        }
        insert(list, playerName, newGuess);
        return null;
    }

    public static void printList(PlayerLinkedList list)
    {
        Players currNode = list.head;

        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.playerName + " ");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println();
    }

    public static void clearLinkedList(PlayerLinkedList list)
    {
        list.head = null;
        size = 0;
    }

    public static int getSize()
    {
        return size;
    }
}
