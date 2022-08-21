package edu.practice.triangles;


import edu.practice.triangles.thread.IsoscelesCheckThread;
import edu.practice.triangles.thread.ObtuseCheckThread;
import edu.practice.triangles.thread.RectangularCheckThread;

public class Main {

    public static void main(String[] args) {
        Thread thread1 = new IsoscelesCheckThread("Равнобедренный поток");
        Thread thread2 = new RectangularCheckThread("Прямоугольный поток");
        Thread thread3 = new ObtuseCheckThread("Тупоугольный поток");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
