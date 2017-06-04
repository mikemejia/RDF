/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yearproject;

import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.*;
import java.io.InputStream;
import static java.util.Objects.hash;
import org.apache.jena.util.FileManager;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.*;
import static org.apache.jena.vocabulary.RDF.object;
import static org.apache.jena.vocabulary.RDF.predicate;
import static org.apache.jena.vocabulary.RDF.subject;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Michael
 */
public class basic {

    public basic(StmtIterator iter) {
      HashMap<String, Integer> s = new HashMap<>();
      HashMap<String, Integer> p = new HashMap<>();
      HashMap<String, Integer> o = new HashMap<>();
      int i = 0;
      int s1 = 0;
      int p1 = 3000000;
      int o1 = 12000000;
      
        while (iter.hasNext()){
         
         String [] ar = iter.next().asTriple().toString().split(" ", 3);
         if(!s.containsKey(ar[0])){
        s.put(ar[0], s1);
        s1++;
        }
         if(!p.containsKey(ar[1])){
        p.put(ar[1], p1);
        p1++;
        }
         if(!o.containsKey(ar[2])){
             if(ar[2].contains("<")){
                 o.put(ar[2], o1);
                   o1++;}
        }
         i++;
}
       System.out.println(i);
    }

    
    
}
