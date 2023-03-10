/* This program contains 2 parts: (1) and (2)
 YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================

import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
     Khong su dung tieng Viet co dau de viet ghi chu.
     Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xForest, int xRate, int xSound) {
        //You should write here appropriate statements to complete this function.
        if (xForest.charAt(0) == 'B') {
            return;
        }
        Boo boo = new Boo(xForest, xRate, xSound);

        Node newest = new Node(boo, null);

        if (isEmpty()) {
            head = tail = newest;
        } else {
            tail.next = newest;
            tail = newest;
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Boo x, y;
        x = new Boo("X", 1, 2);
        y = new Boo("Y", 3, 4);
     //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        // HEAD -> TEMP -> ...
        // HEAD -> X -> Y -> TEMP -> ...
        Node temp = head.next;
        head.next = new Node(x, new Node(y, temp));
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        head = head.next;
        head.next = head.next.next;

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        // 0 -> 1 -> 2 -> 3
        Node node = head;
        int count = 4;
        for (int i = 0; i < count - 1; i++) {
            for (int j = count - 2 - i; j >= 0; j--) {
                if (node.info.rate > node.next.info.rate) {
                    Boo temp = node.info;
                    node.info = node.next.info;
                    node.next.info = temp;
                }
                node = node.next;
            }
            node = head;
        }

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
