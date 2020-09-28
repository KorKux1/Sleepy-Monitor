package main;

import model.StudentThread;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;


/**
 * Main class for Sleepy Monitor.
 * @author Cristhian Castillo (KorKux).
 * @author Sebastián Correa Villada.
 * @author David Obando Gamboa.
 * @author John Camilo Sepulveda Serna.
 */
public class Main {


    public static void main(String[] args){
        try {
            Semaphore monitor = new Semaphore(1, true);

            Semaphore chairs = new Semaphore(StudentThread.CHAIRS, true);


            ArrayList<StudentThread> attendThreads = new ArrayList<StudentThread>();

            for(int i = 0; i < StudentThread.STUDENTS_NUMBER; i++){
                StudentThread attend = new StudentThread(i, monitor, chairs);
                attendThreads.add(attend);
                attendThreads.get(i).start();

                System.out.println("Iniciando...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
