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

    private Random random;

    public StudentThread(int id, Semaphore monitor, Semaphore chairs) {
        this.id = id;
        this.monitor = monitor;
        this.chairs = chairs;
        this.random = new Random();
    }

    @Override
    public void run() {
        System.out.printf("El Estudiante con código %x ha entrado a la sala de cómputo%n", this.id);

        while(true) {
            try {
                sleep(random.nextInt(10000));

                System.out.printf("El estudiante %x entra a la oficina del monitor%n", this.id);

                if (monitor.availablePermits() == 0){
                    if (chairs.availablePermits() == 0){
                        System.out.println("El monitor está atendiendo a un estudiando y las sillas en la sala de espera están ocupadas");
                        System.out.printf("El estudiante con id %x se retira del lugar, volverá más tarde%n", this.id);
                    }
                    else {
                        chairs.acquire();

                        System.out.printf("El monitor está atendiendo a un estudiante. Hay %x sillas disponibles.%nEl estudiante %x" +
                                "toma una silla%n", chairs.availablePermits(), this.id);

                        monitor.acquire();

                        chairs.release();

                        System.out.printf("El estudiante %x es ayudado por el monitor%n", this.id);

                        System.out.printf("Sillas disponibles: %x%n", chairs.availablePermits());

                        int attendTime = generateMonitorAttendTime();

                        sleep(attendTime);

                        monitor.release();

                        System.out.printf("El monitor terminó de ayudar al estudiante con código %s%n", this.id);

                    }

                }

                else {
                    System.out.printf("El monitor está dormido. El estudiante con código %x lo despierta %n", this.id);

                    monitor.acquire();

                    System.out.printf("El monitor ayuda al estudiante con código %x%n", this.id);


                    int attendTime = generateMonitorAttendTime();

                    sleep(attendTime);

                    monitor.release();

                    System.out.printf("El monitor terminó de ayudar al estudiante con código %s%n", this.id);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Gives the time that the monitor takes to attend a student. (Between 3 to 5 seconds)
     * @return int: Time between 3 and 5 seconds.
     */
    public int generateMonitorAttendTime(){
        return (random.nextInt(5-3) + 3) * 1000;
    }

}
