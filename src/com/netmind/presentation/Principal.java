package com.netmind.presentation;

public class Principal {
    public static void main(String[] args) {

        int decission = Menu.hello();
        switch (decission) {
            case 1:
                Menu.askNewStudent();
                break;
            case 2:
                break;
        }

    }
}