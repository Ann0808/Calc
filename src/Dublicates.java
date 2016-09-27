import java.io.*;
import java.util.*;

/**
 * Created by anna on 27.09.16.
 */
public class Dublicates {
    public static void main(String[] args) throws Exception{
        System.out.println("Enter PathToFile/filenameIn:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        System.out.println("Enter PathToFile/filenameOut:");
        reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName2 = reader.readLine();

            try {
               System.out.print(writeFile(fileName2,povtorAndSort(readFile(fileName))));
            }
            catch (Exception e){
                System.out.print(false);
            }
    }
    /**
     * Whrite new file
     * @param f - name of file, where to whrite, m - sorted file
     * @return true if everything ok
     */
    private static boolean writeFile(String f, SortedMap<String,Integer> m){
        File newFile = new File(f);
        if (m==null) return false;
        try(FileWriter writer = new FileWriter(f, true))
        {

            Set<Map.Entry<String, Integer>> set = m.entrySet();
            for (Map.Entry<String, Integer> me : set) {
                writer.write(me.getKey() + ": ");
                writer.write(me.getValue().toString());
                writer.write('\n');
            }
            writer.flush();
            return true;
        }
        catch(IOException ex){

            System.out.println(ex);
            return false;
        }
    }

    /**
     * Formiruet spisok strok
     * @param fileName
     * @return ArrayList<String></>
     */
    public static ArrayList<String> readFile(String fileName){
        String line = null;
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null){
                list.add(line);
            }
        } catch (FileNotFoundException e) {

            list=null;

        } catch (IOException e) {

        }
        return list;
    }
    /**
     * counter of dublicates and string sorter
     */
    private static SortedMap povtorAndSort(ArrayList<String> list) {
        HashMap<String, Integer> hm = new HashMap<>();
        Integer am;
        for (String i : list) {

            am = hm.get(i);
            hm.put(i, am == null ? 1 : am + 1);
        }
        SortedMap sortedMap =new TreeMap<>(hm);
        if(list==null) return null;
        return sortedMap;

    }


}
