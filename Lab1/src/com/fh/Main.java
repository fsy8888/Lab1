package com.fh;

public class Main {
    public static void main(String[] args) {
        ExpressionProcessor calculator = new ExpressionProcessor();     // 控制类对象声明
        while(true) {
            System.out.print("> ");
            String input = calculator.getIn().nextLine();
            calculator.expression(input);
        }
    }
}
