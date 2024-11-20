package com.script.fairy.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daiepngfei on 2020-08-10
 */
public class DTimer {

    private DTMark mHead;
    private DTMark mTail;
    private DTMark min;
    private DTMark max;
    private long total;
    private int size;
    private final String fTag;
    private int maxLabelLen = 0;
    private int layer = 0;
    private IMarkPrinter mIMarkPrinter = new IMarkPrinter() {
        @Override
        public void print(DTMark marked, String defaultLog) {
            System.out.println(defaultLog);
        }
    };

    public DTimer(String tag) {
        this(tag, null);
    }

    public DTimer(String tag, IMarkPrinter markPrinter) {
        fTag = tag;
        if (markPrinter != null) {
            this.mIMarkPrinter = markPrinter;
        }
    }

    public void stepIn() {
        synchronized (this) {
            layer++;
        }
    }

    public void stepOut() {
        synchronized (this) {
            layer--;
            layer = Math.max(0, layer);
        }
    }

    public void mark(String label) {
        mark(label, null);
    }

    public void mark(String label, String lastTip) {
        synchronized (this) {
            DTMark mark = new DTMark(label);
            mark.layer = layer;
            if (mHead == null) {
                mHead = mark;
            }
            if (mTail == null) {
                mTail = mHead;
            } else {
                mTail.next = mark;
                final long consumed = mark.mark - mTail.mark;
                mTail.consumed = consumed;


                if (min == null || min.consumed > consumed) {
                    min = mTail;
                }
                if (max == null || max.consumed < consumed) {
                    max = mTail;
                }
                mTail.tip = lastTip;
                mTail = mTail.next;
                total += consumed;
                size++;
            }
            if (label != null && maxLabelLen < label.length()) {
                maxLabelLen = label.length();
            }
        }
    }

    private static class DTMark {
        String tip;
        DTMark next;
        private long mark;
        private long consumed;
        private String label;
        private int layer = 0;
        private List<DTMark> children;

        DTMark(String label) {
            this.label = label;
            this.mark = System.currentTimeMillis();
        }

        void addChild(DTMark mark) {
            if (mark != null) {
                if (children == null) {
                    children = new ArrayList<>();
                }
                mark.layer = layer + 1;
                children.add(mark);
            }
        }

    }

    @Override
    public String toString() {
        return "DTimer=> {total:" + total + "; max/min:" + max + "/" + min +
                "} =#= {size:" + size + "; average: " + (total * 1.0D / size) + "}";
    }

    public void printAllMarks() {
        System.out.println("printAllMarks");
        System.out.println("printAllMarks>>>>>>>>>>>>>>>>::" + fTag + "::>>>>>>>>>>>>>>>>");
        DTMark cursor = mHead;
        while (true) {
            DTMark next = cursor.next;
            if (next == null) {
                if (mIMarkPrinter != null) {
                    mIMarkPrinter.print(cursor, toString());
                }
                break;
            }
            if (mIMarkPrinter != null) {
                StringBuilder sb = new StringBuilder(cursor.label == null ? "" : cursor.label);
                final int max = maxLabelLen - sb.length();
                sb.append("_");
                for (int i = 0; i < max; i++) {
                    sb.append("$");
                }

                StringBuilder sb1 = new StringBuilder("");
                if (cursor.layer > 0) {
                    for (int i = 0; i < cursor.layer; i++) {
                        sb1.append("____");
                    }
                }
                mIMarkPrinter.print(cursor, "printAllMarks(label:" + (sb.toString()) + ")>>"
                        + sb1.toString()
                        + "mark-at: " + cursor.mark
                        + "/"
                        + "consumed: " + cursor.consumed
                        + "; "
                        + (cursor.tip == null ?  "" : "tip - : " + cursor.tip));
            }
            cursor = next;
        }
        if (mIMarkPrinter != null) {
            mIMarkPrinter.print(cursor, "printAllMarks(count:" + size + "):: fast/slow : "
                    + min.consumed + "(" + getMarkLabel(min) + ")"
                    + "/"
                    + max.consumed + "(" + getMarkLabel(max) + ")"
                    + "; total: " + total);
        }

        System.out.println("printAllMarks<<<<<<<<<<<<<<<<::" + fTag + "::<<<<<<<<<<<<<<<<\n");
        System.out.println("printAllMarks");
    }

    private String getMarkLabel(DTMark max) {
        return max == null || max.label == null ? "?" : max.label;
    }

    public interface IMarkPrinter {
        void print(DTMark marked, String defaultLog);
    }
}
