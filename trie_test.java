/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yearproject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class trie_test {
    
    private class HatrieNode{
       Map<Character, HatrieNode> leaf;
       boolean endOfword;
       public HatrieNode(){
           leaf = new HashMap<>();
           endOfword = false;
       }
    }
    
    
    private final HatrieNode root;
    public trie_test() {
        root = new HatrieNode();
    }
    
    public void insert(String word){
    HatrieNode current = root;
    for(int i = 0; i < word.length(); i++){
        char ch = word.charAt(i);
        HatrieNode node = current.leaf.get(ch);
    if (node == null) {
        node = new HatrieNode();
        current.leaf.put(ch, node);
    }
    current = node;
    }
    current.endOfword = true;
    }
    public boolean search(String word){
    HatrieNode current = root;
    for(int i = 0; i < word.length(); i++){
    char ch = word.charAt(i);
    HatrieNode node = current.leaf.get(ch);
    if(node == null){
    return false;
    }
    else{System.out.print(ch);}
    current = node;
    }
    return current.endOfword;}
}

    

