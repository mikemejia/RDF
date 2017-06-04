/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yearproject;
import java.io.IOException;
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
public class YearProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Model m = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open
        ("C:\\Users\\Michael\\Documents\\NetBeansProjects\\YearProject\\src\\yearproject\\persondata_en.nt");
       
      m.read(in, null, "NTRIPLES");
     
      StmtIterator iter =
  m.listStatements(new SimpleSelector());
      
     long time;
     long memsize = 0; 
    time = 0;
for(int i = 0; i < 3; i++){
  
   Hashtable test = new Hashtable();
    





    long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    long r = System.currentTimeMillis();
 
//    basic impl = new basic(iter);

     while (iter.hasNext()){
         
         String [] ar = iter.next().asTriple().toString().split(" ", 3);
     test.insert(ar[0].toCharArray());
     test.insert(ar[1].toCharArray());
     if(ar[2].contains("<")){
     test.insert(ar[2].toCharArray());}
     }
     
     
      long e = System.currentTimeMillis();
      
      System.out.print(e-r);
    long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    long actualMemUsed=afterUsedMem-beforeUsedMem;
   memsize = memsize + (actualMemUsed/1048576);
   System.out.print(memsize+" ");
   time = time + (e-r);
   
   
}
    
    System.out.println((time)*.001);
    System.out.println(memsize);
    }
     
     

    
    
    
}
