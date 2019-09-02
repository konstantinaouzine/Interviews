package CompanyA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BracketsChecker {
    //private static Stack<Character> my_stack = new Stack<Character>();

    //  { [ ] }
    static boolean checkForBalancedBrackets(String inputString){
        Stack<Character> my_stack = new Stack<Character>();
        for (Character character : inputString.toCharArray()){
            if ((character=='(') || (character == '{') || (character == '[')){
                my_stack.push(character);
            }
            else if (character == ')' && my_stack.size()!=0){
                if (my_stack.peek()=='(')
                    my_stack.pop();
                else
                    return false;
            }
            else if (character == '}' && my_stack.size()!=0){
                if (my_stack.peek()=='{')
                    my_stack.pop();
                else
                    return false;
            }
            else if (character == ']' && my_stack.size()!=0){
                if (my_stack.peek()=='[')
                    my_stack.pop();
                else
                    return false;
            }
            else
                return false;
        }

        return my_stack.isEmpty();
    }
    // { [ ( ] [ } ] )
    static boolean checkForUnbalancedBrackets(String inputString){
        Map<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();
        char[] inputAsCharArray = inputString.toCharArray();
        for (int i=0; i< inputAsCharArray.length; i++){
            if (map.containsKey(inputAsCharArray[i])){
                map.get(inputAsCharArray[i]).add(i);
            }
            else{
                map.put(inputAsCharArray[i], new ArrayList<Integer>());
                map.get(inputAsCharArray[i]).add(i);
            }
        }

        if (map.keySet().size() % 2 != 0)
            return false;

        for (Character character : map.keySet()){
            if (character=='('){
                ArrayList<Integer> openBracketArray = map.get(character);
                ArrayList<Integer> closeBracketArray = map.get(')');
                if (openBracketArray.size() != closeBracketArray.size())
                    return false;
                for (int i=0; i<openBracketArray.size(); i++){
                    if (openBracketArray.get(i) > closeBracketArray.get(i))
                        return false;
                }
            }
            if (character=='['){
                ArrayList<Integer> openBracketArray = map.get(character);
                ArrayList<Integer> closeBracketArray = map.get(']');
                if (openBracketArray.size() != closeBracketArray.size())
                    return false;
                for (int i=0; i<openBracketArray.size(); i++){
                    if (openBracketArray.get(i) > closeBracketArray.get(i))
                        return false;
                }
            }

            if (character=='{'){
                ArrayList<Integer> openBracketArray = map.get(character);
                ArrayList<Integer> closeBracketArray = map.get('}');
                if (openBracketArray.size() != closeBracketArray.size())
                    return false;
                for (int i=0; i<openBracketArray.size(); i++){
                    if (openBracketArray.get(i) > closeBracketArray.get(i))
                        return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        //correct
        System.out.println(checkForBalancedBrackets("{[][]{}()}"));
        System.out.println(checkForUnbalancedBrackets("{[]{[}()}]"));
        System.out.println();
        //incorrect
        System.out.println(checkForBalancedBrackets("{[}][]{}()"));
        System.out.println(checkForUnbalancedBrackets("{]}"));
        System.out.println(checkForUnbalancedBrackets("{][}"));
        System.out.println(checkForUnbalancedBrackets("{][}"));
    }
}
