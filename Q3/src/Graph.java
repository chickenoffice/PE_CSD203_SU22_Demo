/* This program contains 2 parts: (1) and (2)
 YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================

import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------

public class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    void loadData(int k) {  //do not edit this function
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile("data.txt", "r");
            for (i = 0; i < k; i++) {
                f.readLine();
            }
            s = f.readLine();
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }
            f.close();
        } catch (Exception e) {
        }

    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes("  " + v[i]);
    }

    void fdispAdj(RandomAccessFile f) throws Exception {
        int i, j;
        f.writeBytes("n = " + n + "\r\n");
        for (i = 0; i < n; i++) {
            f.writeBytes("\r\n");
            for (j = 0; j < n; j++) {
                f.writeBytes("  " + a[i][j]);
            }
        }
        f.writeBytes("\r\n");
    }

    void breadth(boolean[] en, int i, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth(en, i, f);
            }
        }
    }

    void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }

    void depth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, f);
            }
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void f1() throws Exception {
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        depth(0, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        int[] counter = {0};
        boolean[] visited = new boolean[v.length];
        depth2(counter, 0, f);

        //-------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void depth2(int[] counter, int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth2(counter, visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth2(counter, visited, i, f);
            }
        }
    }

    void depth2(int[] counter, boolean[] visited, int k, RandomAccessFile f) throws Exception {
        counter[0]++;
        if (counter[0] >= 2 && counter[0] <= 6) {
            fvisit(k, f);
        }
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth2(counter, visited, i, f);
            }
        }
    }

//=================================================================
    void f2() throws Exception {
        loadData(13);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
      // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f2.txt 
        //  and statement f.writeBytes(" " + k); to write  variable k to the file f2.txt  
        int[] s = new int[20];
        dijkstra(a, 0, 6, s, f);
        f.writeBytes("\n");
   
        for (int i = 3; i < 7; i++) {
            fvisit(s[i],f);
        }
        
        f.writeBytes("\n");
        dijkstra(a, 2, 5, s, f);
        //-------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    public void dijkstra(int[][] a, int start, int end, int[] s, RandomAccessFile f) throws Exception {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = 99; // initialize all distances to infinity
            visited[i] = false;
            prev[i] = -1;
        }

        dist[start] = 0;

        for (int i = 0; i < n; i++) {
            int u = findMinDistance(dist, visited);
            visited[u] = true;
            s[i] = u;

            for (int v = 0; v < n; v++) {
                if (a[u][v] != 99 && !visited[v] && dist[u] + a[u][v] < dist[v]) {
                    dist[v] = dist[u] + a[u][v];
                    prev[v] = u;
                }
            }
        }

        // print the shortest path from start to end
        
        int u = end;
        int[] result = new int[20];
        int i = 0;
        while (u > -1) {
            result[i] = u;
            i++;
            u = prev[u];
        }
        for (int j = i - 1; j >= 0; j--) {
            fvisit(result[j],f);
        }
    }

    public static int findMinDistance(int[] dist, boolean[] visited) {
        int minDist = 99;
        int minIndex = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

}
