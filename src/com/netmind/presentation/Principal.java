package com.netmind.presentation;

public class Principal {
    public static void main(String[] args) {

        Boolean interruptor = true;

        while (interruptor) {

            int decission = Menu.hello();

            switch (decission) {
                case 1:
                    Menu.askNewStudent();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    interruptor = false;

            }
        }
    }
}