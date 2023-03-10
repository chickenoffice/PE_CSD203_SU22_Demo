/* This program contains 2 parts: (1) and (2)
 YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================

import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xForest, int xRate, int xSound) {
        //You should insert here statements to complete this function

        if (xForest.charAt(0) == 'A' || !isUnique(root, xSound)) {
            return;
        }
        root = insert(root, new Boo(xForest, xRate, xSound));
    }

    private Node insert(Node root, Boo boo) {
        if (root == null) {
            return new Node(boo);
        }
        if (boo.sound > root.info.sound) {
            root.right = insert(root.right, boo);
        } else {
            root.left = insert(root.left, boo);
        }
        return root;
    }

    public boolean isUnique(Node root, int value) {
        if (root == null) {
            return true;
        }
        if (root.info.sound == value) {
            return false;
        }

        if (root.info.sound > value) {
            return isUnique(root.left, value);
        } else {
            return isUnique(root.right, value);
        }
    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        preOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.info.sound < 6) {
            fvisit(p, f);
        }
        preOrder2(p.left, f);
        preOrder2(p.right, f);
    }

//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        int[] counter = {0};
        Node p = getFourthNode(root, counter);
        deleteKey(p.info.sound);
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node getFourthNode(Node node, int[] counter) {
        if (node == null) {
            return null;
        }

        counter[0]++; 
        if (counter[0] == 4) {
            return node;
        }

        Node left = getFourthNode(node.left, counter);
        if (left != null) {
            return left; 
        }

        Node right = getFourthNode(node.right, counter); 
        if (right != null) {
            return right; 
        }

        return null;
    }

    //WE HAVE TWO WAY OF DELETING A NODE: COPYING AND MERGE
    //THE TEST OBVIOUSLY WANT US TO USE THE COPYPASTA WAY
    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.info.sound) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.info.sound) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.info = minValue(root.right);
            root.right = deleteRec(root.right, root.info.sound);
        }

        return root;
    }

    Boo minValue(Node root) {
        Boo minv = root.info;
        while (root.left != null) {
            minv = root.left.info;
            root = root.left;
        }
        return minv;
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
         Your task is to insert statements here, just after this comment,
         to complete the question in the exam paper.*/
        int[] arr = {0};
        Node p = getFourthNode(root, arr);
        leftRotate(p);
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
    
    public void leftRotate(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        //FIX THE PARENT
        if (node == root) {
            root = pivot;
        } else {
            Node parent = findParent(root, node);
            if (parent.left == node) {
                parent.left = pivot;
            } else {
                parent.right = pivot;
            }
        }
    }
    private Node findParent(Node root, Node node) {
        if (root == null || root == node) {
            return null;
        } else if (root.left == node || root.right == node) {
            return root;
        } else if (node.info.sound < root.info.sound) {
            return findParent(root.left, node);
        } else {
            return findParent(root.right, node);
        }
    }

}
