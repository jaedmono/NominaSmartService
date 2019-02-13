import antlr.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Usuario on 06/11/2017.
 */
public class Test {
    int i = 1;

    {
        i=3;
    }


    private static void solution() {
        int max = 2+3*4+6/8;
        int[]i = {6};
        i[i.length -1]++;
        System.out.println(i);
    }

    public static void main(String[] a){
        solution();

    }


}
