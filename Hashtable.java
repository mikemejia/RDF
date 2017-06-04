/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yearproject;

import java.util.Arrays;
import static javax.management.Query.gt;
import static javax.management.Query.lt;

/**
 *
 * @author Michael
 */
public class Hashtable {
   /** Number of slots in CCHashTable. 512 from Asktis&amp;Sinha 2007. */
   public static int tableSize = 512;
   /** The CCHashTable's contents are stored in a 2D char array */
   public static int slotPageSize = 70;
   private char contents[][];
   /** Basic constructor. */
   public Hashtable()
   {
      this.contents = new char[Hashtable.tableSize][0];
   }
   
   public static int getHashCode(char inputString[])
{
int result = Hashtable.tableSize;
int length = inputString.length;
for (int i = 0; i < length; i++) {
result ^= ( (result << 5) + inputString[i] + (result >>> 2) );

}
//System.out.print(((result&0x7fffffff)&0x1ff)+" ");
return ((result&0x7fffffff)&0x1ff);
}
   public boolean insert(char inputString[])
{
//Sanity check
if ((inputString==null)||(inputString.length==0)) {
return false;
}
int i = Hashtable.getHashCode(inputString);
int j = 0;
int k = 0;
int length = 0;
int slotlength = this.contents[i].length;
//System.out.print(slotlength+" ");
int inputlength = inputString.length;
boolean match = false;
// First of all, is this string in the hash table allready?
while (j < slotlength) {
// if there's nothing left in the slot, jump out
if (this.contents[i][j] == '\u0000') { break; }
match = true;
// We store the strings as length-encoded, null-terminated
// strings in the char array hence first we swipe the
// length and reconvert it to an int

length = (int) (this.contents[i][j++] | (this.contents[i][j++] << 16));
// If the string length doesn't match, the strings don't match, so jump out early.
if (inputlength!=length){
match = false;
} else {
// Now check the array for a character match. Because this
// shows a stride pattern in memory accesses, it's
// cache-friendly.

Character chh= '\u0000';
for (k = j; this.contents[i][k] != chh; k++) {
if (this.contents[i][k] != inputString[k-j]) {
match = false;
break;
}
}
}
// If we found the string, we don't bother to add it, we
// just return back.
if (match) return false;
j += length +3;
}
// Do we need to grow this slot in the array? If so, do so
// now...
while ((slotlength - inputlength) < (j+5)) {
//char tmp[] = new char[slotlength + CCHashTable.slotPageSize];
//System.arraycopy(this.contents[i], 0, tmp, 0, slotlength);
//this.contents[i] = tmp
this.contents[i] = Arrays.copyOf(this.contents[i], slotlength + Hashtable.slotPageSize);
slotlength = this.contents[i].length;
}
// Add String to end of contents array.
// First encode the length of the string (this allows us to
// skip forward rapidly in the array when string matching if we
// get an early negative)
this.contents[i][j++] = (char)(inputlength & 0xffff);
this.contents[i][j++] = (char)((inputlength >> 16) & 0xffff);
// Now copy over the inputString characters
//System.arraycopy(inputString, 0, this.contents[i], j, inputlength);
//j += inputlength;
for (k = 0; k < inputlength; k++) {
this.contents[i][j++] = inputString[k];
}
// null-terminate
this.contents[i][j++] = '\u0000';
return true;
}
   
   
   }

