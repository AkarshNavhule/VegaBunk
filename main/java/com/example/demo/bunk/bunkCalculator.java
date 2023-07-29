package com.example.demo.bunk;

import com.example.demo.db.dbParser;
public class bunkCalculator {
    public int value(int expected, int present, int done, int total) {
        int yetToGo = total - done;
        Double val;
        for(int i = 0; i + done <= total; i++) {
            val = (double) (present + i) / (done + i);
            if(val * 100 >= expected) return (yetToGo - i);
        }

        return -1;
    }
}
