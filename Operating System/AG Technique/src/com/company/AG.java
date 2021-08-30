package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class AG {

    int quantum;
    int halfQuantum;
    int currentTime ; //time
    int workTime = 0; //the time that process spent in work
    double avgWaitingTime;
    double avgTurnaroundTime;

    LinkedList<Process> readyQueue = new LinkedList<>(); //this list contains all arrives processes if process starts work remove it form the list
    LinkedList<Process> waitingList = new LinkedList<>();
    ArrayList<Process> arr = new ArrayList<>(readyQueue);

    AG(LinkedList<Process> readyQueue, LinkedList<Process> waitingList, ArrayList<Process> arr){ //constructor
        this.readyQueue = readyQueue;
        this.waitingList = waitingList;
        this.arr = arr;

        currentTime = readyQueue.getFirst().arrivalTime;
    }

    public void nonPreemptiveAG(Process p){
        quantum = p.quantum; //get quantum
        halfQuantum = (int) Math.ceil( 0.5*quantum); //calc half quantum
        if(p.burstTime < halfQuantum){
            p.quantum = 0; // set q = 0
            currentTime += p.burstTime;
            p.burstTime = 0;
        }
        else
            currentTime += halfQuantum;
    }

    public boolean isProcessArrive(int currentAG){ //return true if there process arrived else return false
        if(readyQueue.isEmpty())
            return false;
        else if(readyQueue.getFirst().arrivalTime <= currentTime)
            if(readyQueue.getFirst().AGfactor < currentAG)
                return true;
            else {
                waitingList.addLast(readyQueue.getFirst());
                readyQueue.removeFirst();
                return false;
            }
        else
            return false;
    }

    public boolean isThereAG_SmallThanMe(int currentAG){ //return true if the waiting list contains process with AG factor smaller than the current AG
        ListIterator<Process> li = waitingList.listIterator();
        while (li.hasNext()){
            if(li.next().AGfactor < currentAG) {
                return true;
            }
        }
        return false;
    }

    public void updateBurstTime(Process p){ p.burstTime = p.burstTime - workTime; }

    public void updateQuantum(Process p){
        p.endProcessTime = currentTime;
        quantum = p.quantum;
//        printQuantum();
        if(quantum == workTime) {
            p.quantum += 1; //increment the value by 1
        }
        else {
            int temp = quantum - workTime;
            p.quantum += temp;
        }

        if(p.burstTime == 0)
            p.quantum = 0; //set q = 0
    }

    public Process getProcessArrive(){ //return first node in ready queue
        Process pr = new Process();
        pr = readyQueue.getFirst();
        readyQueue.removeFirst();
        return pr;
    }

    public Process getProcessWithSmallestAG( int currentAG){
        Process temp = new Process();
        Process smallestAG = new Process();
        ListIterator<Process> li = waitingList.listIterator();
        int min = currentAG; //consider the minimum AG = current AG
        int index = 0;
        int delindex = 0;
        while (li.hasNext()){
            temp = li.next();
            if(temp.AGfactor < min){
                smallestAG = temp;
                delindex = index;
            }
            index ++;
        }
        waitingList.remove(delindex);
        return smallestAG;
    }

    public void printWaitingList(){
        ListIterator<Process> li = waitingList.listIterator();
        while (li.hasNext()) {
            System.out.println(li.next().name);
        }
    }

    public void printQuantum(){
        System.out.println("\n");
        for(int i = 0; i < arr.size(); i++){
            Process temp = new Process();
            temp = arr.get(i);
            System.out.print(temp.quantum + ", ");
        }
    }

    public void printWaitingAndTurnaroundTime(){
        double sumWaitingTime = 0;
        double sumTurnaroundTime = 0;

        for(int i = 0; i < arr.size(); i++){
            Process temp = new Process();
            temp = arr.get(i);

            sumTurnaroundTime += temp.turnaroundTime;
            sumWaitingTime += temp.waitingTime;

            System.out.println("Process " + temp.name + " Waiting Time = " +  temp.waitingTime + " and Turnaround Time = " + temp.turnaroundTime);

        }
        avgTurnaroundTime = sumTurnaroundTime / arr.size();
        avgWaitingTime = sumWaitingTime / arr.size();

        System.out.println("average waiting time: " + avgWaitingTime);
        System.out.println("average turn around time: " + avgTurnaroundTime);

    }

    public void AG_technique(Process p){

        p.waitingTime = p.waitingTime +(currentTime - p.endProcessTime);
        p.turnaroundTime += (currentTime - p.endProcessTime);

        int startWork = currentTime;
        System.out.print("   " + currentTime +  "   " + p.name);

        nonPreemptiveAG(p); //50% non preemptive AG factor

        workTime = currentTime - startWork;

        for(int i = 0; i <= ((quantum-workTime)); i++){ //50% preemptive AG factor
            if(p.burstTime == 0){
                updateQuantum( p);
                break;
            }

            if(isProcessArrive( p.AGfactor)) {
                updateBurstTime(p);
                updateQuantum( p);
                waitingList.addLast(p); //push
                AG_technique(getProcessArrive());
                break;
            }
            else if(isThereAG_SmallThanMe(p.AGfactor)) {
                updateBurstTime(p);
                updateQuantum( p);
                waitingList.addLast(p); //push
                AG_technique(getProcessWithSmallestAG(p.AGfactor));
                break;
            }

            currentTime += 1;
            workTime = currentTime - startWork;

            if(workTime == quantum){
                updateBurstTime(p);
                updateQuantum( p);
                waitingList.addLast(p); //push
                break;
            }
            else if (workTime == p.burstTime){
                updateBurstTime(p);
                updateQuantum( p);
            }
        }
    }
}
