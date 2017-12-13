package com.example.erikn.qlearning;

/**
 * Created by erikn on 2017-12-12.
 */

import java.math.*;

public class Sensor {

    public Sensor(){

    }

    public int getValue(){
        double d  = Math.random();
        if(d>0.8){
            if(d<0.5){
                return 10;
            }else{
                return 3;
            }
        }
        return 0;
    }
}
