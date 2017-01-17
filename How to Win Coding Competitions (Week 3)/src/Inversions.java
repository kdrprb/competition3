import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Inversions {

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("inversions.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("inversions.out"));
    }
    
    // solution based on pseudocode provided here: http://www.geeksforgeeks.org/counting-inversions/
    private static long mergeSort(int[] toSort, int s, int e, int[] tmp, int lvl) throws IOException
    {
        long numInversions = 0;
        // continue partitioning until size of sub-array = 1
        if(e>s)
        {
            int m = ((e+1-s)/2+s);
            //System.out.println("calling mergeSort(toSort, "+s+", "+(m-1)+", tmp, "+(lvl+1)+");");
            numInversions += mergeSort(toSort, s, m-1, tmp, lvl+1);
            //System.out.println("calling mergeSort(toSort, "+m+", "+e+", tmp, "+(lvl+1)+");");
            numInversions += mergeSort(toSort, m, e, tmp, lvl+1);
            
            //System.out.println("toSort: "+ toStringBuilder(toSort).toString());
            // copy sorted sub-arrays into scratch memory
            for(int i=s; i<=e; i++)
            {
                tmp[i]=toSort[i];
            }
            //System.out.println("tmp: "+ toStringBuilder(tmp).toString());
            
            int leftCounter = s;
            int rightCounter = m;
            boolean leftOK = true;
            boolean rightOK = true;
            int idx = 0;
            // then merge sorted sub-arrays into original array
            while(leftOK && rightOK)
            {
                if(tmp[leftCounter]<=tmp[rightCounter])
                {
                    toSort[s+idx]=tmp[leftCounter];
                    leftCounter++;
                    if(leftCounter>=m) leftOK = false;
                }
                else
                {
                    toSort[s+idx]=tmp[rightCounter];
                    rightCounter++;
                    if(rightCounter>e) rightOK = false;
                    numInversions+=(m-leftCounter);
                }
                idx++;
            }
            
            while(leftOK)
            {
                toSort[s+idx]=tmp[leftCounter];
                leftCounter++;
                if(leftCounter>=m) leftOK = false;
                idx++;
            }
            
            while(rightOK)
            {
                toSort[s+idx]=tmp[rightCounter];
                rightCounter++;
                if(rightCounter>e) rightOK = false;
                idx++;
            }
            
        }
        //bw.write((s+1)+" "+(e+1)+" "+toSort[s]+" "+toSort[e]+"\n");
        return numInversions;
    }
    
/*    private static StringBuilder toStringBuilder(int[] a)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(a[0]);
        for(int i=1; i<a.length; i++) sb.append(" "+a[i]);
        return sb;
    }*/

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int listSize = Integer.parseInt(in.readLine());
            //System.out.println("listSize = " + listSize);
            if(listSize <=0) System.exit(1);
            
            int[] list = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //System.out.println("list: "+toStringBuilder(list).toString());
            int[] scratch = new int[listSize];
            
            long numInversions = mergeSort(list,0,listSize-1,scratch,0);
            out.write(numInversions+"\n");
            out.flush();
        }// end try
    }// end main

}
