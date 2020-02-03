package com.algorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 经典问题: 归并排序
 * Created by xifeng.yang on 2020/2/2
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort o = new MergeSort();
        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E", "H"};
        o.sortBottomUp(a);
        show(a);
    }


    /**
     * 自顶向下的归并排序
     *
     * @param a
     */
    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }


    /**
     * 自底向上的归并排序
     *
     * @param a
     */
    public void sortBottomUp(Comparable[] a) {
        int len = a.length;
        Comparable[] aux = new Comparable[len];

        for (int step = 1; step < len; step *= 2) {
            //从lo起所剩元素>step时才需要进行merge.
            for (int lo = 0; lo < len - step; lo += step * 2) {


                merge(a, aux, lo, lo + step - 1, Math.min(lo + step + step - 1, len - 1));
            }
        }

    }

    // merge-sort a[lo..hi] using auxiliary array aux[lo..hi]
    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
