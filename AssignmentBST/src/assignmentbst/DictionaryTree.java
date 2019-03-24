package assignmentbst;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

class E_L_Word implements Comparable<E_L_Word> {

    String E_Word;
    LinkedList<String> L_Words = new LinkedList<>();

    public E_L_Word(String E) {
        E_Word = E;
    }

    public void addToLatinList(String L) {
        L_Words.add(L);
    }

    @Override
    public String toString() {
        return (E_Word + " : " + L_Words.toString()).replaceAll("[\\[\\]]", "");
    }

    @Override
    public int compareTo(E_L_Word another) {
        return this.E_Word.compareTo(another.E_Word);
    }

}

class DictionaryTree extends BST<E_L_Word> {

    String unit;
    String filename;
    PrintWriter writer;

    public DictionaryTree() {
        this("%Unit0", "English to Latin.txt");
    }

    public DictionaryTree(String unit, String filename) {
        this.unit = unit;
        this.filename = filename;
    }

    public void InsertWord(String E_Word, String L_Word) {
        E_L_Word newWord = new E_L_Word(E_Word);
        E_L_Word w = this.search(newWord);
        if (w == null) {
            newWord.addToLatinList(L_Word);
            insert(newWord);
        } else {
            w.addToLatinList(L_Word);
        }
    }

    // Put your code here...
    @Override
    protected void visit(BSTNode<E_L_Word> p) {
        System.out.println(p.el);
        writer.println(p.el);
    }

    public void flush() {
        try (FileWriter fw = new FileWriter(filename, true);
                PrintWriter pw = new PrintWriter(fw)) {
            writer = pw;
            writer.println(unit);
            this.inorder();
        } catch (Exception e) {
        }
        this.clear();
    }
}
