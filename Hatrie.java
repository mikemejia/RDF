/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//http://www.stochasticgeometry.ie/2008/05/06/implementing-hat-tries-in-java/ 
 
package yearproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class Hatrie {

    private class HatrieNode {

        Map<Character, HatrieNode> leaf;
        Map<String, Character> a;
        boolean endOfword;
        boolean size;
        String e;

        public HatrieNode() {
            leaf = new HashMap<>();
            a = new HashMap(40);
            size = false;
            e = "";
            endOfword = false;
        }
    }

    private final HatrieNode root;

    public Hatrie() {
        root = new HatrieNode();
    }

    public void insert(String word) {
        HatrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            HatrieNode node = current.leaf.get(ch);
            if (node == null) {
                
                node = new HatrieNode();
                current.leaf.put(ch, node);
                String t = current.e;
                current = node;
                current.e = t + String.valueOf(ch);
                current.a.put(word.substring(i + 1),ch);

                break;
            }

            current = node;

            if (!current.size) {
                if (!current.a.containsKey(word.substring(i + 1))) {
                    current.a.put(word.substring(i + 1),ch);
                }
                if (current.a.size() == 4) {
                    current.size = true;
                }
                break;
            }
            if (current.a != null && current.size) {
                if (!current.a.containsKey(word.substring(i + 1))) {
                    for (int rr = 0; rr < current.a.size(); rr++) {
                        //System.out.println(current.e+current.a.get(rr).toString());
                        insert(current.e + current.a.get(rr).toString());
                    }
                    current.a = null;
                }

            }
        }
        current.endOfword = true;
    }

    public boolean search(String word) {
        HatrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            //System.out.println(ch);
            HatrieNode node = current.leaf.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
            if (current.a != null) {
               // System.out.print("+++" + word.substring(i + 1));
                return current.a.containsKey(word.substring(i + 1));
            }

        }
        return current.endOfword;
    }
}
