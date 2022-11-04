import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Mallocator {

    public static void main(String[] args) throws IOException {

        // vars for minput
        int mslots;
        int[] msizes = new int[10];
        int[] mslotstart = new int[10];

        // Read Minput.data
        String minput = "Minput.data";
        Scanner mscanner = new Scanner(new File(minput));
        mslots = mscanner.nextInt();
        System.out.println("mslots: " + mslots);

        // Print out Minput.data
        while (mscanner.hasNext()) {
            // Adds sizes of memory slots to array
            for (int i = 0; i < mslots; i++) {
                int temp1 = mscanner.nextInt();
                int temp2 = mscanner.nextInt();
                int temp3 = temp2 - temp1;
                mslotstart[i] = temp1;
                msizes[i] = temp3;
            }
        }

        // vars for pinput
        int pnumber;
        int[] psizes = new int[10];

        // Read Pinput.data
        String pinput = "Pinput.data";
        Scanner pscanner = new Scanner(new File(pinput));
        pnumber = pscanner.nextInt();
        System.out.println("pnumber: " + pnumber);

        // Print out Pinput.data
        while (pscanner.hasNextLine()) {
            for(int i = 0; i < pnumber; i++) {
                int temp1 = pscanner.nextInt();
                int temp2 = pscanner.nextInt();
                psizes[i] = temp2;
            }
        }

        for(int i = 0; i < pnumber; i++) {
            System.out.println("psizes: " + psizes[i]);
        }

        FF(pnumber, msizes, psizes, mslotstart);
    }

    static void FF(int pnumber, int[] msizes, int[] psizes, int[] mslotstart) throws IOException {
        // First Fit
        File pout = new File("Pout.data");
        FileWriter pfilewriter = new FileWriter(pout);
        PrintWriter pprintwriter = new PrintWriter(pfilewriter);

        for(int i = 0; i < pnumber; i++) {
            for(int j = 0; j < msizes.length; j++) {
                if(psizes[i] <= msizes[j]) {
                    int amendpnum = i+1;
                    System.out.println("Process " + amendpnum + "starts at " + mslotstart[j] + " and ends at " + (mslotstart[j] + psizes[i]));
                    pprintwriter.println(mslotstart[j] + " " + (mslotstart[j] + psizes[i]) + " " + amendpnum);
                    msizes[j] = msizes[j] - psizes[i];
                    break;
                }
                if(j == msizes.length - 1) {
                    System.out.println("Process " + (i+1) + " cannot be allocated");
                    pprintwriter.println("Process " + (i+1) + " cannot be allocated");
                }
            }
        }
        pprintwriter.close();
    }

    static void BF(int[] msizes, int[] psizes) {
        // Best Fit

    }

    static void WF(int[] msizes, int[] psizes) {
        // Worst Fit

    }
}
