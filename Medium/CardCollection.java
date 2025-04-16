import java.util.*;

public class CardCollection {

    // HashMap to store cards grouped by symbol
    private static HashMap<String, List<String>> cardMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    // Method to add a card to the collection
    public static void addCard(String symbol, String value) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(value);
    }

    // Method to search for cards by symbol
    public static void searchBySymbol(String symbol) {
        if (cardMap.containsKey(symbol)) {
            List<String> cards = cardMap.get(symbol);
            System.out.println("Cards of symbol \"" + symbol + "\":");
            for (String card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found with symbol: " + symbol);
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the number of cards: ");
        int num = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Input cards
        for (int i = 0; i < num; i++) {
            System.out.print("Enter card " + (i + 1) + " symbol: ");
            String symbol = scanner.nextLine();

            System.out.print("Enter card " + (i + 1) + " value: ");
            String value = scanner.nextLine();

            addCard(symbol, value);
        }

        // Search cards by symbol
        System.out.print("\nEnter a symbol to search: ");
        String searchSymbol = scanner.nextLine();
        System.out.println();

        searchBySymbol(searchSymbol);

        scanner.close();
    }
}
