/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentbst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 *
 * @author Kudo
 */
public class Main {
    public static void main(String[] args) {
        File f = new File("English to Latin.txt");
        if (f.exists()) f.delete();
        File w = new File("CountWords.txt");
        if (w.exists()) w.delete();
        processDictionary("Latin.txt", "English to Latin.txt");
        processCountWords("Words.txt", "CountWords.txt");
    }
    
    public static void processDictionary(String filenameInput, String filenameOutput) {
        File f = new File(filenameInput);
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            DictionaryTree dicTree = new DictionaryTree();
            String reader;
            while ((reader = br.readLine()) != null) {
                if (reader.startsWith("%")) {
                    if (!dicTree.isEmpty()) {
                        dicTree.flush();
                    }
                    dicTree = new DictionaryTree(reader, filenameOutput);
                }
                else {
                    String[] str = reader.split(":");
                    String meaning = str[0].trim();
                    StringTokenizer stk = new StringTokenizer(str[1], ",");
                    while (stk.hasMoreTokens()) {
                        String word = stk.nextToken().trim();
                        dicTree.InsertWord(word, meaning);
                    }
                }
            }
            if (!dicTree.isEmpty()) {
                dicTree.flush();
            }
        } catch (Exception e) {
        }
        System.out.println("Dictionary: SUCCESS");
    }
    
    public static void processCountWords(String filenameInput, String filenameOutput) {
        File f = new File(filenameInput);
        try (FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr)) {
            CountWords cw = new CountWords(filenameOutput);
            String reader;
            int line = 0;
            while ((reader = br.readLine()) != null) {
                line++;
                StringTokenizer stk = new StringTokenizer(reader, " .,:;\'\"?");
                while (stk.hasMoreTokens()) {
                    String word = stk.nextToken().trim();
                    cw.InsertWord(word, line);
                }
            }
            cw.flush();
        } catch (Exception e) {
        }
        System.out.println("Count Words: SUCCESS");
    }
}
