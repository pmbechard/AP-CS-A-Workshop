import java.util.Arrays;

public class BinaryTime {

    private String[][] binaryTime;

    public BinaryTime(String input) {
        binaryTime = new String[4][];
        binaryTime[0] = new String[3];
        binaryTime[1] = new String[5];
        binaryTime[2] = new String[6];
        binaryTime[3] = new String[6];
        setBinaryTime(input);
    }

    public void setBinaryTime(String input) {
        String[] data = input.split(" ");
        for (int i = 0; i < data.length; i++) {
            if (i < 3) binaryTime[0][i] = data[i];
            else if (i < 8) binaryTime[1][i - 3] = data[i];
            else if (i < 14) binaryTime[2][i - 8] = data[i];
            else binaryTime[3][i - 14] = data[i];
        }
    }

    public String[][] getBinaryTime() {
        return binaryTime;
    }

    public String getReadableTime() {
        return convertBinaryString(new String[] {binaryTime[3][0], binaryTime[2][0]})
                + convertBinaryString(new String[] {binaryTime[3][1], binaryTime[2][1], binaryTime[1][0], binaryTime[0][0]})
                + ":" + convertBinaryString(new String[] {binaryTime[3][2], binaryTime[2][2], binaryTime[1][1]})
                + convertBinaryString(new String[] {binaryTime[3][3], binaryTime[2][3], binaryTime[1][2], binaryTime[0][1]})
                + ":" + convertBinaryString(new String[] {binaryTime[3][4], binaryTime[2][4], binaryTime[1][3]})
                + convertBinaryString(new String[] {binaryTime[3][5], binaryTime[2][5], binaryTime[1][4], binaryTime[0][2]});
    }

    private String convertBinaryString(String[] binary) {
        int result = 0;
        for (int i = 0; i < binary.length; i++) {
            if (binary[i].equals("*"))
                result += (int) Math.pow(2, i);
        }
        return result == 0 ? "0" : String.valueOf(result);
    }

    @Override
    public String toString() {
        return "Binary Time: " + Arrays.deepToString(binaryTime) + "\nReadable Time: " + getReadableTime() + "\n";
    }
}
