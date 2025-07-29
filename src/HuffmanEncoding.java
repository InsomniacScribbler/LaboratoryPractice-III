import java.util.*;

public class HuffmanEncoding {
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    private class Node implements Comparable<Node> {
        Character data;
        int cost;
        Node left, right;

        public Node(Character data, int cost) {
            this.data = data;
            this.cost = cost;
            this.left = this.right = null;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    public HuffmanEncoding(String feeder) throws Exception {
        HashMap<Character, Integer> frequency = new HashMap<>();

        for (char c : feeder.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() > 1) {
            Node first = minHeap.poll();
            Node second = minHeap.poll();

            Node merged = new Node('\0', first.cost + second.cost);
            merged.left = first;
            merged.right = second;

            minHeap.add(merged);
        }

        Node root = minHeap.poll();

        encoder = new HashMap<>();
        decoder = new HashMap<>();

        buildEncoderDecoder(root, "");
    }

    private void buildEncoderDecoder(Node node, String path) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            encoder.put(node.data, path);
            decoder.put(path, node.data);
            return;
        }

        buildEncoderDecoder(node.left, path + "0");
        buildEncoderDecoder(node.right, path + "1");
    }

    public String encode(String message) {
        StringBuilder sb = new StringBuilder();
        for (char ch : message.toCharArray()) {
            sb.append(encoder.get(ch));
        }
        return sb.toString();
    }

    public String decode(String encodedMessage) {
        StringBuilder sb = new StringBuilder();
        String temp = "";

        for (char ch : encodedMessage.toCharArray()) {
            temp += ch;
            if (decoder.containsKey(temp)) {
                sb.append(decoder.get(temp));
                temp = "";
            }
        }

        return sb.toString();
    }

    public void printEncoderDecoderMaps() {
        System.out.println("Encoder Map (Character -> Code):");
        for (Map.Entry<Character, String> e : encoder.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("\nDecoder Map (Code -> Character):");
        for (Map.Entry<String, Character> e : decoder.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to encode: ");
        String input = sc.nextLine();

        HuffmanEncoding hf = new HuffmanEncoding(input);

        String encoded = hf.encode(input);
        String decoded = hf.decode(encoded);

        System.out.println("\nEncoded Message: " + encoded);
        System.out.println("Decoded Message: " + decoded);

        System.out.println();
        hf.printEncoderDecoderMaps();
    }
}
