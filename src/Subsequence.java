import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by anna on 27.09.16.
 */
public class Subsequence {
    static ArrayList<Object> X = new ArrayList<>();
    static ArrayList<Object> Y = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        System.out.println("Enter the first sequence:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        X=formList(reader);
        System.out.println("Enter the second sequence:");
        reader = new BufferedReader(new InputStreamReader(System.in));
        Y=formList(reader);
        System.out.print(isSub(X,Y));

    }
    /**
     * Create list
     * @param reader Enter str
     * @return ArrayList<Object></>
     */
    private static ArrayList<Object> formList(BufferedReader reader) throws IOException {
        String x=reader.readLine();
        StringTokenizer st = new StringTokenizer(x);
        ArrayList<Object> list=new ArrayList<>();
        while (st.hasMoreTokens()){
            list.add(st.nextToken());
        }
        return list;
    }
    /**
     * true-if one of sequences is subseq
     */
    private static boolean isSub(ArrayList<Object> X, ArrayList<Object>Y){
        if (X.size()>=Y.size()){
            int a = 0;
            for (int i=0;i<Y.size();i++){
                if (X.contains(Y.get(i))) a++;
            }
            if (a==Y.size())return true;
            else return false;
        }
        else  {
            int a = 0;
            for (int i=0;i<X.size();i++){
                if (Y.contains(X.get(i))) a++;
            }
            if (a==X.size())return true;
            else return false;
        }
    }
}
