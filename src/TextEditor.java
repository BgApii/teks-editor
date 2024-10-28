import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private final Stack<String> textHistory;
    private final Stack<String> redo;
    private String currentText;

    public TextEditor() {
        textHistory = new Stack<>();
        redo = new Stack<>();
        currentText = "";
    }

    public void show() {
        System.out.println("text saat ini: ");
        System.out.println(currentText);
    }

    public void write(String text) {
        textHistory.push(currentText);
        currentText += text;
        redo.clear();
    }

    public void undo() {
        if (!textHistory.isEmpty()) {
            redo.push(currentText);
            currentText = textHistory.pop();
            show();
        } else {
            System.out.println("Tidak bisa undo");
        }
    }

    public void redo() {
        if (!redo.isEmpty()) {
            textHistory.push(currentText);
            currentText = redo.pop();
            show();
        } else {
            System.out.println("Tidak bisa redo");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();

        do {
            System.out.println("===============================================");
            System.out.println("Fitur (write, show, undo, redo, close)");
            String fitur = scanner.nextLine();
            System.out.println("===============================================");

            if (fitur.equalsIgnoreCase("write")) {
                System.out.print("teks: ");
                String text = scanner.nextLine();
                editor.write(text);
            } else if (fitur.equalsIgnoreCase("show")) {
                editor.show();
            } else if (fitur.equalsIgnoreCase("undo")) {
                editor.undo();
            } else if (fitur.equalsIgnoreCase("redo")) {
                editor.redo();
            } else if (fitur.equalsIgnoreCase("close")) {
                System.exit(0);
            } else {
                System.out.println("Inputan salah.");
            }
        } while (true);
    }
}
