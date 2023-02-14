import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class database {
    static public String fileName="laptops.csv";
    static public ArrayList<String> getNotebookFromFile(){
        File file = new File(fileName);
        ArrayList<String> s = new ArrayList <>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            while (scanner.hasNext()){
                s.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return s;
    }
}
