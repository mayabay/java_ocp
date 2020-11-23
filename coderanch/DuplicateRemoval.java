package coderanch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
public class DuplicateRemoval {
    public static void main(String[] args) {
        Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        String myString = "hello how are you";
        for (int i = 0; i < myString.length(); i++) {
            if (!charCountMap.containsKey(myString.charAt(i)))
                charCountMap.put(myString.charAt(i), 1);
            else {
                int count = charCountMap.get(myString.charAt(i));
                count++;
                charCountMap.put(myString.charAt(i), count);
            }
        }
        printMap(charCountMap);
    }
 
    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if ((Integer) pair.getValue() > 1)
                System.out.println("Duplicates--> " + pair.getKey());
            it.remove();
        }
    }
}