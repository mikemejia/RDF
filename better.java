/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yearproject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.*;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import static java.util.Objects.hash;
import org.apache.jena.util.FileManager;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.*;
import static org.apache.jena.vocabulary.RDF.object;
import static org.apache.jena.vocabulary.RDF.predicate;
import static org.apache.jena.vocabulary.RDF.subject;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
/**
 *
 * @author Michael
 */
public class better {
    public better(StmtIterator iter) throws FileNotFoundException, IOException{
    HashMap<String, Integer> map=new HashMap<>();
    FileWriter writer = new FileWriter("fileToWriteSPO");
    
        while (iter.hasNext()){
         String [] ar = iter.next().asTriple().toString().split(" ", 3);
         writer.write(hash(ar[0])+" "+ar[0]);
         writer.write(System.lineSeparator());
         writer.write(hash(ar[1])+" "+ar[1]);
         writer.write(System.lineSeparator());
         if(ar[2].contains("<")){
            String r = ar[2].replace("\n", "").replace("\r", "");
            
           writer.write(hash(r)+" "+r);
           writer.write(System.lineSeparator());}
          
        }
    File file = new File("fileToWriteSPO");
    writer.close();
    BufferedReader r = new BufferedReader(new FileReader(file));
    String q= "";
    
    while((q =r.readLine())!=null){
        map.put(q.split(" ", 2)[1], Integer.parseInt(q.split(" ", 2)[0]));
     
    }
    HashMap <String, Integer> sortedmap = sortByValues(map);
    FileWriter writer2 = new FileWriter("fileToWriteSPOSorted");
    Iterator it = sortedmap.entrySet().iterator();
    while(it.hasNext()){
        Map.Entry pair = (Map.Entry)it.next();
        writer2.write(pair.getValue().toString()+"\n");
        it.remove();
    }
   writer2.close();
        
        
        
    }
      private static HashMap sortByValues(HashMap map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, (Object o1, Object o2) -> ((Comparable) ((Map.Entry) (o1)).getValue())
               .compareTo(((Map.Entry) (o2)).getValue()));

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
  }
    
}
