import java.util.*;

// Evelyn Salas
// CSE 143 AO with Stuart Reges
// Homework 6
// Finds words that have the same combination of letters in a given phrase

public class AnagramSolver {
   private Map<String, LetterInventory> anagramDict; //anagram for dictionary
   private List<String> dict; //dictionary

   //Constructs anagram solver
   //@param list as a dictionary
   public AnagramSolver(List<String> list) {
      this.dict = list;
      this.anagramDict = new HashMap<String, LetterInventory>();
      for (String word : dict) {
         this.anagramDict.put(word, new LetterInventory(word));
      }
   }
   
   //finds combinations of words with the same letters as string
   //@param string s given phrase
   //@param int max maximum words
   //@throws IllegalArgumentException if max is less than zero
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException("Maximum number of words must"
                                           + "be greater than zero");
      }
      LetterInventory letters = new LetterInventory(s);
      List<String> pruneDict = this.prune(letters, this.dict);
      print(letters, pruneDict, new Stack<String>(), max);
   }
   
   //helper method for print
   //@param counter stores current words
   //@param pruneDict relevant dictionary
   //@param wordsChosen stores the anargam results
   //@param max maximum number of words
   private void print(LetterInventory counter, List<String> pruneDict, Stack<String> wordsChosen, int max) {
      if (counter.isEmpty()) {
         System.out.println(wordsChosen);
      }else if (wordsChosen.size() < max || max == 0) {
         for(String word : dict){
            wordsChosen.push(word);
            LetterInventory tempCounter = counter.subtract(anagramDict.get(word));
            print(tempCounter, pruneDict, wordsChosen, max);
            wordsChosen.pop();
         }
      }
   }
   
   //prunes dictionary
   //@param currentCounter stores curent words
   //@param dict original dictionary 
   //@returns pruneDict dictionary with relevant words only
   private List<String> prune(LetterInventory currentCounter, List<String> dict) {
      List<String> pruneDict = new ArrayList<String>();
      LetterInventory temp = null;
      for(String word : dict){
         temp = currentCounter.subtract(anagramDict.get(word));
         if(temp != null) {
            pruneDict.add(word);
         }
      }
      return pruneDict;
   }
}