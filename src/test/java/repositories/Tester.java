package repositories;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Tester {

    /**
     * Complete the function below.
     * DO NOT MODIFY anything outside this method.
     */
    static int[] balancedOrNot(String[] expressions, int[] maxReplacements) {
        int[] response = new int[expressions.length];
        String expression;
        for (int i=0 ; i < expressions.length; i ++){
            expression = expressions[i];
            if(isBalanced(expression, maxReplacements[i])){
                response[i] = 1;
            }else{
                response[i] = 0;
            }

        }
        return response;
    }

    private static boolean isBalanced(String expression, int maxReplacement) {
        char[] chars = expression.toCharArray();
        int countOpenChar = 0;
        for(char c : chars){
            if('<' == c){
                countOpenChar ++;
            } else if('>' == c){
                countOpenChar --;
            }else{
                return false;
            }

            if(countOpenChar < 0){
                if ( --maxReplacement < 0)
                    return false;
                else
                    countOpenChar = 0;
            }
        }
        if(countOpenChar > 0){
            return false;
        }
        return true;
    }



    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException {

        if(isBalanced("<<<>><>>>><>>", 7)) {
            System.out.println("SI");
        }else{
            System.out.println("NO");
        }
    }
}
