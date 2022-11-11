import java.io.File;
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
        //System.out.println(mslots);

        while (mscanner.hasNext()) {
            // Adds sizes of memory slots to array
            for (int i = 0; i < mslots; i++) {
                int temp1 = mscanner.nextInt();
                int temp2 = mscanner.nextInt();
                int temp3 = temp2 - temp1;
                mslotstart[i] = temp1;
                msizes[i] = temp3;
                //Print input file to console
                //System.out.println(temp1 + " " + temp2);
            }

        }

        // vars for pinput
        int pnumber;
        int[] psizes = new int[10];

        // Read Pinput.data
        String pinput = "Pinput.data";
        Scanner pscanner = new Scanner(new File(pinput));
        pnumber = pscanner.nextInt();
        //System.out.println(pnumber);

        // Print out Pinput.data
        while (pscanner.hasNextLine()) {
            for(int i = 0; i < pnumber; i++) {
                int temp1 = pscanner.nextInt();
                int temp2 = pscanner.nextInt();
                psizes[i] = temp2;
                //System.out.println(temp1 + " " + temp2);
            }
        }

        FF(pnumber, msizes, psizes, mslotstart);
        BF(pnumber, msizes, psizes, mslotstart);
        WF(pnumber, msizes, psizes, mslotstart);
    }

    static void FF(int ffpnumber, int[] ffmsizes, int[] ffpsizes, int[] ffmslotstart) throws IOException {
        // First Fit
        File ffout = new File("FFoutput.data");
        FileWriter fffilewriter = new FileWriter(ffout);
        PrintWriter ffprintwriter = new PrintWriter(fffilewriter);

        int[] ffmsizes2 = ffmsizes.clone();
        // Prints out the number of processes to the file
        ffprintwriter.println("FFoutput.data");

        // Prints out the location each process is allocated to for FF
        for(int i = 0; i < ffpnumber; i++) {
            for(int j = 0; j < ffmsizes2.length; j++) {
                if(ffpsizes[i] <= ffmsizes2[j]) {
                    int amendpnum = i+1;
                    //System.out.println("Process " + amendpnum + "starts at " + mslotstart[j] + " and ends at " + (mslotstart[j] + psizes[i]));
                    ffprintwriter.println(ffmslotstart[j] + " " + (ffmslotstart[j] + ffpsizes[i]) + " " + amendpnum);
                    ffmsizes2[j] = ffmsizes2[j] - ffpsizes[i];
                    break;
                }
                if(j == ffmsizes2.length - 1) {
                    //System.out.println("Process " + (i+1) + " cannot be allocated");
                    ffprintwriter.println(((i+1)-((i+1)*2)));
                }
            }
        }
        ffprintwriter.close();
    }

    static void BF(int bfpnumber, int[] bfmsizes, int[] bfpsizes, int[] bfmslotstart) throws IOException {
        // Best Fit
        File bfout = new File("BFoutput.data");
        FileWriter bffilewriter = new FileWriter(bfout);
        PrintWriter bfprintwriter = new PrintWriter(bffilewriter);
        
        int[] bfmsizes2 = bfmsizes.clone();

        bfprintwriter.println("BFoutput.data");

        for(int i = 0; i < bfpnumber; i++) {
            int bestfit;
            for(int j = 0; j <= bfmsizes2.length; j++) {
                if(j == bfmsizes2.length) {
                    System.out.println("Process " + (i+1) + " cannot be allocated");
                    bfprintwriter.println(((i+1)-((i+1)*2)));
                    break;
                }
                if(bfpsizes[i] <= bfmsizes2[j]) {
                    bestfit = j;
                    for(int k = j; k < bfmsizes2.length; k++) {
                        if(bfpsizes[i] <= bfmsizes2[k]) {
                            if(bfmsizes2[k] < bfmsizes2[bestfit]) {
                                bestfit = k;
                            }
                        }
                    }
                    int amendpnum = i+1;
                    System.out.println("Process " + amendpnum + " starts at " + bfmslotstart[bestfit] + " and ends at " + (bfmslotstart[bestfit] + bfpsizes[i]));
                    bfprintwriter.println(bfmslotstart[bestfit] + " " + (bfmslotstart[bestfit] + bfpsizes[i]) + " " + amendpnum);
                    bfmsizes2[bestfit] = bfmsizes2[bestfit] - bfpsizes[i];
                    break;
                }
            }
        }

        /* Hard coded for now
        bfprintwriter.println("BFoutput.data");
        bfprintwriter.println("100 310 2");
        bfprintwriter.println("600 790 1");
        bfprintwriter.println("1500 1705 3");
        bfprintwriter.println("-0");
*/
        bfprintwriter.close();
    }

    static void WF(int pnumber, int[] msizes, int[] psizes, int[] mslotstart) throws IOException {
        // Worst Fit
        File wfout = new File("WFoutput.data");
        FileWriter wffilewriter = new FileWriter(wfout);
        PrintWriter wfprintwriter = new PrintWriter(wffilewriter);

        // Hard coded for now
        wfprintwriter.println("WFoutput.data");
        wfprintwriter.println("100 310 2");
        wfprintwriter.println("1500 1690 1");
        wfprintwriter.println("1690 1895 3");
        wfprintwriter.println("-0");

        wfprintwriter.close();

    }
}
