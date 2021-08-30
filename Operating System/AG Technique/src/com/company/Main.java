package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        LinkedList<Process> readyQueue = new LinkedList<>();
        LinkedList<Process> waitingList = new LinkedList<>();
        int numOfProcess;
        int quantum;

        System.out.print("Enter number of Processes: ");
        numOfProcess = input.nextInt();
        System.out.print("Enter quantum: ");
        quantum = input.nextInt();

        for(int i = 0; i < numOfProcess; i++){
            Process pr = new Process();

            System.out.println("process " + (i+1));
            System.out.print("   name: ");
            pr.name = input.next();

            System.out.print("   arrivalTime: ");
            pr.arrivalTime = input.nextInt();
            pr.endProcessTime = pr.arrivalTime;

            System.out.print("   burstTime: ");
            pr.burstTime = input.nextInt();
            pr.turnaroundTime = pr.burstTime;

            System.out.print("   priority: ");
            pr.priority = input.nextInt();
            pr.quantum = quantum;

            pr.AGfactor = pr.priority + pr.burstTime + pr.arrivalTime;
            readyQueue.add(pr);
        }
        ArrayList<Process> arr = new ArrayList<>(readyQueue);

        AG a = new AG(readyQueue, waitingList, arr);

        waitingList.addFirst(readyQueue.getFirst());
        readyQueue.removeFirst();
        System.out.println("\n");

        while (!waitingList.isEmpty()){
            Process process = new Process();
            process = waitingList.getFirst();
            waitingList.removeFirst();
            a.AG_technique(process);
        }

//        System.out.println("   " + a.currentTime);
        System.out.println("\n");
        a.printWaitingAndTurnaroundTime();
    }
}
