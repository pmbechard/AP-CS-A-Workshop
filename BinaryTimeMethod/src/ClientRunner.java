import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClientRunner {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inFile = new Scanner(new File("input.txt"));

        while (inFile.hasNextLine()) {
            String data = "";
            for (int i = 0; i < 4; i++)
                data += inFile.nextLine() + " ";
            BinaryTime time = new BinaryTime(data);
            System.out.println(time.getReadableTime());
        }
        inFile.close();
    }
}
