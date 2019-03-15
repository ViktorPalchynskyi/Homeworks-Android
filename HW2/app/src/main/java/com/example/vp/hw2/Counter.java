package com.example.vp.hw2;

public class Counter {

    int leftCounter ;
    int rightCounter ;




    public void CountLeft (){
        leftCounter++;
    }

    public void CountRight (){
        rightCounter++;
    }


    public int getLeftCounter() {
        return leftCounter;
    }

    public void setLeftCounter(int leftCounter) {
        this.leftCounter = leftCounter;
    }

    public int getRightCounter() {
        return rightCounter;
    }

    public void setRightCounter(int rightCounter) {
        this.rightCounter = rightCounter;
    }
}
