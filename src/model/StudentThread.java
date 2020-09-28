package model;

import java.util.Random;
import java.util.concurrent.Semaphore;


/**
* Thread for Attend the Students.
* @author Cristhian Castillo (KorKux).
* @author Sebastián Correa Villada.
* @author David Obando Gamboa.
* @author John Camilo Sepulveda Serna.
*/
public class StudentThread extends Thread   {
    public static final int CHAIRS = 3;
    public static final int STUDENTS_NUMBER = 6;

    private int id;

    private Semaphore monitor;

    private Semaphore chairs;

    private Random randomSleep;

    public StudentThread(int id, Semaphore monitor, Semaphore chairs, Random randomSleep) {
        this.id = id;
        this.monitor = monitor;
        this.chairs = chairs;
        this.randomSleep = randomSleep;
    }

    @Override
    public void run() {
        

    }

}
