import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AntiQuicksort {
    
    static int counter;

    static BufferedReader newInput() throws IOException {
        return new BufferedReader(new FileReader("antiqs.in"));
    }
    
    static BufferedWriter newOutput() throws IOException {
        return new BufferedWriter(new FileWriter("antiqs.out"));
    }

    
    private static void antiQS(int[] listQS, int s, int e)
    {
        int m = ((e-s)/2+s);
        System.out.println("set idx["+m+"] = "+counter);
        listQS[m]=counter;
        counter--;
        
        if(m<e) 
        {
            System.out.println("right: antiQS with s="+(m+1)+"; e="+e);
            antiQS(listQS, m+1, e);
        }
        if(m>s) 
        {
            System.out.println("left: antiQS with s="+s+"; e="+(m-1));
            antiQS(listQS, s, m-1);
        }
    }

    private static StringBuilder toStringBuilder(int[] a)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(a[0]);
        for(int i=1; i<a.length; i++) sb.append(" "+a[i]);
        return sb;
    }

    
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = newInput(); BufferedWriter out = newOutput()) {
            int listSize = Integer.parseInt(in.readLine());
            //System.out.println("listSize = " + listSize);
            if(listSize <=0) System.exit(1);
            
            if(listSize==2)
            {
                out.write("1 2\n");
                out.flush();
            }
            else
            {
                int[] list = new int[listSize];
                counter=listSize;
                antiQS(list,0,listSize-1);
                
                out.write(toStringBuilder(list).toString());
                out.flush();
            }
        }// end try
    }// end main

}
