public class LinkedList {
    private GuessedWord head;
    private static int size = 0;

    static class GuessedWord {
        int frequency;
        String word;
        GuessedWord next;

        public GuessedWord (String word)
        {
            this.word = word;
            next = null;
        }
    }

    public static void insert(LinkedList list, String word)
    {
        GuessedWord new_node = new GuessedWord(word);
        new_node.next = null;

        if (list.head == null) {
            list.head = new_node;
        }
        else {
            GuessedWord last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        size++;
    }

    public static LinkedList deleteWord(LinkedList list, String word)
    {
        GuessedWord currNode = list.head;
        GuessedWord prev = null;

        // CASE 1:
        // If head node itself holds the key to be deleted
        if (currNode != null && word.equals(currNode.word)) {
            list.head = currNode.next; // Changed head
            size--;
            return list;
        }

        // CASE 2:
        // If the key is somewhere other than at head
        while (currNode != null && !(word.equals(currNode.word))) {
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

    public static void increaseFrequency(LinkedList list, String word) {
        GuessedWord currNode = list.head;
        boolean needToAddWord = true;
        while (currNode != null) {
            if (word.equals(currNode.word)) {
                currNode.frequency++;
                needToAddWord = false;
                break;
            }
            currNode = currNode.next;
        }
        if(needToAddWord)
        {
            insert(list, word);
        }
    }

    public static void printList(LinkedList list)
    {
        GuessedWord currNode = list.head;

        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.word + " ");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println();
    }

    public static String getHighestFrequency(LinkedList list)
    {
        GuessedWord currNode = list.head;
        String mostFreqWord = currNode.word;
        int freqOfMostFreqWord = currNode.frequency;

        // Traverse through the LinkedList
        while (currNode != null) {
            if (currNode.frequency > freqOfMostFreqWord)
            {
                mostFreqWord = currNode.word;
                freqOfMostFreqWord = currNode.frequency;
            }

            // Go to next node
            currNode = currNode.next;
        }
        return mostFreqWord;
    }

    public static void clearLinkedList(LinkedList list)
    {
        list.head = null;
        size = 0;
    }

    public static int getSize()
    {
        return size;
    }
}
