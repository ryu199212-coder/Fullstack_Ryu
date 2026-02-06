package com.company.java017_newtwork_ex;

import java.util.Scanner;

import javax.swing.JOptionPane;

class QuestionCount extends Thread {
    @Override public void run() {
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            try { Thread.sleep(1000); } 
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
class Question extends Thread {
    @Override public void run() {
    	String answer = JOptionPane.showInputDialog("사과 알파벳을 입력하세요.");
    	System.out.println(answer.toLowerCase().equalsIgnoreCase("apple")? "정답":"오답");
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("사과알파벳을 입력하세요.");
        String i2 = scanner.next();

        if ("apple".equalsIgnoreCase(i2)) 
        { System.out.println("정답입니다"); }
        else 
        { System.out.println("정답이 아닙니다"); }*/
    }
}

public class ThreadEx001 {
    public static void main(String[] args) {
        Thread count = new QuestionCount(); count.start();
        Thread question = new Question(); question.start();       
    }
}
