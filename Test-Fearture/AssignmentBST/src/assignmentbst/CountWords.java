package assignmentbst;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;



class Word implements Comparable<Word>
{
    String word;
       HashSet<Integer> lines = new HashSet<>();
    public Word(String E)
    {
        word = E;
    }
    public void addToLinesList(int line)
    {
        lines.add(line);
    }

    @Override
    public String toString()
    {
        return (word + " : " + lines.toString()).replaceAll("[\\[\\]]", "");
    }

    @Override
    public int compareTo(Word another)
    {
        return this.word.compareTo(another.word);
    }
}

class CountWords extends BST<Word>
{
    String filename;
    PrintWriter writer;

    public CountWords() {
        this("outputCountWords.txt");
    }

    public CountWords(String filename) {
        this.filename = filename;
    }
    
	public void InsertWord(String word, int line)
	{
            Word newWord = new Word(word);
            Word w = this.search(newWord);
            if(w == null)
            {
                newWord.addToLinesList(line);
                insert(newWord);
            }
            else
            {
                    w.addToLinesList(line);
            }
	}
	
	// Put your code here...
        
    @Override
	protected void visit(BSTNode<Word> p) {
            System.out.println(p.el);
            writer.println(p.el);
        }

    public void flush() {
        try (FileWriter fw = new FileWriter(filename,true);
                PrintWriter pw = new PrintWriter(fw)) {
            writer = pw;
            this.inorder();
        } catch (Exception e) {
        }
        this.clear();
    }
}
