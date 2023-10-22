package com.company;

public class Drawer {
    public static void drawTriangle(int size) {
        if(size > 0) {
            int n = 1;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < n; j++)
                    System.out.print("#");
                System.out.println();
                n++;
            }
        }
        else System.out.println("fail");
    }

    public static void drawSquare(int size) {
        if(size > 0){
            drawRectangle(size, size);
        } else
            System.out.println("fail");
    }

    public static void drawPyramid(int size) {
        if(size > 0){
            drawFilledPyramid(size, size);
        } else System.out.println("fail");
    }
    private static void drawGap(int size){
        for(int j = 0; j < size; j++)
            System.out.print(" ");
    }
    private static void drawFilledPyramid(int size, int fill){
        for (int j = 0; j < size; j++){
            drawGap(fill - j - 1);
            for (int z = 0; z < j + j + 1; z++)
                System.out.print("#");
            System.out.println();
        }
    }

    public static void drawChristmasTree(int size) {
        if(size > 0){
            for(int i = 1; i < size + 1; i++) {
                drawFilledPyramid(i, size);
            }
        } else System.out.println("fail");
    }

    public static void drawRectangle(int width, int height) {
        if(width > 0 && height > 0){
            for(int i = 1; i <= height; i++){
                if(i == 1 || i == height) {
                    for (int j = 0; j < width; j++)
                        System.out.print("#");
                    System.out.println();
                }
                else {
                    System.out.print("#");
                    for(int j = 0; j < width - 2; j++)
                        System.out.print(" ");
                    System.out.print("#");
                    System.out.println();
                }
            }
        } else
            System.out.println("fail");
    }
}
class Main{
    public static void main(String[] args){
        Drawer.drawChristmasTree(1);
    }
}
