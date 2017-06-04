# RDF
best ways to read RDF triples
 
by Michael Mejia
The RDF (Resource Description Framework) is a way to represent information displayed
on  the  Internet.   RDF  can  be  displayed  as  triples  which  includes  subject,  predicate,
object.  With this structure where the elements can be Urls, black nodes, or data type
literals.  This is a way to represent and describe the RDF. RDF data sets are used to
organize  collections  of  RDF  graphs,  and  comprise  a  default  graph  and  zero  or  more
named graphs[1].  With the RDF data set it i possible to query information and possibly
in the future have machines make connections with this rdf data to search for similar
documents and literal.  The Jena package is used in this project in order to read the
different RDF several formats and transform them into the N-triples if the information
is not already set up in that way.  With the data set having files that can be much larger
than 50mb it is important to find a way to efficiently manage these large data files which
can contain millions of distinct URL’s.  All these record if not managed efficiently will
be  placed  in  memory.   The  different  management  schemes  can  be  trie’s,  hash  tables,
or  just  placing  the  information  into  a  new  file.   The  trie’s  data  structure  are  seen  as
one of the quickest for managing strings in memory.  The better data structures used
in this paper are a combination of a trie and other methods such as buckets that can
have arrays or hash tables.  In this paper I describe the performances in the different
uses of the different algorithms and data structures to handle such significantly large
documents/data sets.  . . .

 
Chapter 1
Introduction
The purpose of this paper will be to implement and compare different algorithms to see
which  are  better  suited  or perform  better when  implemented  with  RDF data.   These
algorithms are already used with strings data management.  Using these data structures
to encode the URLs to make it easier, less memory intesive, and less time consuming
when encoding the URLs for Fast retrieval.  The RDF data model is a way for not only a
person to make sense of a page but for a computer to understand (with limited amount
of comprehension) of what the data means and how it can be connected to other data.
1.1  RDF
Semantic web is the newer creation or guidelines of how standard html pages are created.
The problem that occured before and still occurs today is the locating, extracting, or
just understanding what these web pages mean not for human eyes but for computer
interpretation.  This format or guidelines is still not fully implemented.  The xml format
is a way to structure the document which makes it more easier for parsing the document
and retrieve information/variables that can be of importance.  The only problem with
this is that just like code in a program,  certain variables can be used repeatedly but
have different meanings making it more difficult to interpret the information present.
The RDF is a standard model set forth my the WC3.  This model is a way to merge
data together even if the way the underlying layout our different from each other.  With
this  data  structure.   It  created  in  a  way  that  can  always  be  implemented  even  with
the  advancement  and  changes  in  methodology  when  creating  data  in  the  world  wide
web.   The  rdf  uses  URI’s  to  create  links/relationships  between  objects,  subjects,  or
predicates.  Each link is when combined creates a triple.  With this triple standard it can
be interpreted, shared, and mixed with data from different applications.  With websites
such as wikipedia being as large as they are, it is necessary to encode the triples to be
able store with the least amount of memory.
1.2  Tire
A trie data structure is a type of tree that is used to store Dynamic sets or associative
arrays.  Every node is an array of pointers that represent every letter and symbol in an
alphabet.  The leafs of the nodes or where the chain might end is the representation of
the word that has been searched for.  There are different ways to to setup a trie but
usually  the  string  retrieval  is  very  quick.   The  problem  that  arises  once  again  is  the
amount of space the trie might be using.  Especially when the Strings might be URLs
which are long in length.  The way the trie is implemented in our experiments are with
a mapping with each node pointing to a char to the next node.  Usually this is how a
trie is implemented.  With the experement such as the Hat-trie the trie is implemented
as the beginning char of the string with will then point to buckets.  As the buckets begin
to fill to their capacity the buckets will then split removing the first char of the string to
make it into a new pointer creating new buckets.  This way the depth of the trie is kept
to a minimum except for this case where many urls will have the same starting prefix
which will cause the trie to start of deeper than what other strings might begin as.
1.3  Hash Table Section
Hash Table is a data structure that implements an associative array.  Associative array
is an abstract data type() composed of a key and value in which the key only appears
once.  The hash table uses a hash function in order to place a key into a unique bucket.
There is a possible collisions when hashing different inputs that can lead to the same
address  space.   When  this  occurs  the  the  address  space  is  incremented  until  an  open
space  is  found.   For  good  performance  the  hash  function  needs  to  achieve  reasonable
uniform  distribution  of  strings  to  slots.   Though  this  is  a  fast  method  of  retrevial  of
information, when the data set increases amount of hash collisions begin to grow.  Not
only does the hash collisions grow but the with the resize of the hash table when it gets
to size that is .75 to its capacity it grows in size.  This can create a problem when the
the amount of records can be in the millions.
 
1.4  Cache conscious Hashing
The point of creating hash tables is to try and create a fast method of retrieving large
numbers using less space.  ”Typical implementation of String hash tables that uses list
nodes  are  not  cache  efficient”  [CC  hash  table].   String  hashing  is  a  method  used  to
reduce a string to a number in a specified range.  The criteria used for this hashing is
to find uniformity getting the key that will hash to given slot.  ”Uniformity means that
for a given load factor (ratio of keys to slots) average access time is roughly constant,
regardless of table size.  Universality in terms presented by the document is finding a
randomly choosing function that has a high probability of performing well.  As well as
finding a function that is efficient and can be applied.  The design to be implemented is
the use of primitive operators. As stated in the Performance in Practice of String Hashing
Function.  The use of the ’and’ operator will lose information so the implementation of
shifts and Xors as well as the ’and’ operator will help in creating a good hashing function.



 
Chapter 2
Implementations
2.1  Setup
The Jena program is a framework that can read different types of semantic web data files
and convert it to the n-triples that will be used for encoding in our different algorithms.
The programming language is java using the Netbeans IDE plaform.  The System used
for this experiments is a leveno t430.
•
Leveno T430
•
Eight gigs of ram
•
Three gigs of available memory
•
Ten gigs of virtual memory
•
Intel Core i5-3320M 2 core
2.2  Errors
Some common errors on the setup was easily fixed as just changing the format between
the  different  input  files.   One  problem  occurred  when  the  machine  I  was  running  my
experiments on was running out of memory.  From what I understood from this was the
allocated memory to my experiment was not sufficient.  After allocating four gigabytes
of memory to the program it was then able to run smoothly.  The program also had to
be run in a loop multiple times to get the average of each run as the first run usually
created data that wasn’t accurate.  Usually after the 5th run it gave consistent results.



 


2.3  Hash Table
With  the  hash  function  that  was  recommended  to  be  used(the  bitwise  function)  we
implement the hash table on its own to see how the speed and memory usage compares
to the hash table already on the computer.  As specified on the document the new cache
concious table is 512 entries.  It is set up in a way that its a 2d char array that the hash
function points to the slot in the array.  Then in each array the string input is changed to
a char array and inputed in each slot.  The slot can increase in size which helps keep the
amount of space taken by instantiated to a minimum.  there are slots that have not been
instantiated incase those slots are never choosen by the hash function.  When adding a
string to a slot that already has a String (char array values) it will increase the size of
the array and then add the new String to the end of it.


 
Chapter 3
Testing
3.1  First Data
The first table demonstrates the trials between the five starting algorithms.  The data
set  was  homogeneous  which  is  the  RDF/XML  format  usually  seen.   Jena  would  read
each line and convert the documents into triples in order for the algorithms to process
the data set.  With the First chart and table the relatively low amount of triples around
1400000 rows gives the results presented.  For the moment The basic hash map is the
fastest and uses the least amount of memory.  The Basic algorithm is just a typical hash
map.  While the better algorithm is a method of finding every URL a specific unique ID
and placing that on a different text file.  Later it runs through the text file to see if there
were any collisions which if there are will be given new IDs.  The Trie took the longest
of the different algorithms.  The Hash table that was created with the document from
the cache conscious hash table is not as fast as the (most likely optimized) hash map
and is not as fast as the better algorithm.  So for smaller amounts of data it does not
improve compared to the data structures already available.
3.2  Second Data
With the second table the amount of n-triples is increases to a bit over 8 million.  Though
it might not seem that much greater, it does begin to show the weakness of the algo-
rithms.  The Tries alone preformed the worst out of all the algorithms.  The hashmap
once again came out on top as the best out of each.  The combination of the Trie with
the Arraylist brought the memory usage down.  With these results its obvious how using
a hash map with a combination of other algorithms can help reduce the memory usage.
It only becomes apparent that array lists are not the best data structure and is memory
intensive as the smaller the arrays the better the results until it reaches a crossing point
of the amount of arrays being instantiated compared to the amount of leafs being cre-
ated.  This helps give reason as to why a combination of Hatrie would improve the time
and reduce the memory.  The data sets which just loading on my Testing laptop took
a lot of memory but the CC hash table performed just like the name implies.  It used
the least amount of memory, but it did take the longest out of all the Data structures.
This can be optimized with adding more slots to the bucket size alone.  But this alone
does not create the Hat-trie.  Instead of just testing the Hat-trie in the beginning this
helps demonstrate every part or every basic component and their performances in order
to know which algorithm to choose to move forward.


 







