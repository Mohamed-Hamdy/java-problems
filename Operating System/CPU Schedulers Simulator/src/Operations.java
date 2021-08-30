/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AA
 */
import Os_3.Non_preemptive_Priority;
import Os_3.SJF_Non_Preemptive;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {

    Integer numer_of_process;
    Integer round_robin;
    Integer context_switching;
    ArrayList<Process> processes;

    private Process AddProcess() {
        Scanner s = new Scanner(System.in);
        String name;
        System.out.println("Please enter name of process: ");
        name = s.nextLine();
        Integer arrival;
        System.out.println("Please enter arrival time of process: ");
        arrival = s.nextInt();
        Integer burst;
        System.out.println("Please enter burst time of process: ");
        burst = s.nextInt();
        Integer priority;
        System.out.println("Please enter priority of process: ");
        priority = s.nextInt();
        Integer quantam;
        System.out.println("Please enter quantam of process: ");
        quantam = s.nextInt();
        Process p = new Process(name, arrival, burst, priority, quantam);
        return p;
    }

    public static void SortArrival(ArrayList<Process> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).arrival > arr.get(j).arrival) {
                    Process temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
    }

    public static void SortBurst(ArrayList<Process> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).burst > arr.get(j).burst) {
                    Process temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
    }

    public static void SortPriority(ArrayList<Process> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).priority > arr.get(j).priority) {
                    Process temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }
    }

    public Operations(Integer numer_of_process, Integer round_robin, Integer context_switching) {

        this.numer_of_process = numer_of_process;
        this.round_robin = round_robin;
        this.context_switching = context_switching;
        processes = new ArrayList<>();
        for (int i = 0; i < numer_of_process; i++) {
            System.out.println("please enter data of process number " + (int) (i + 1));
            processes.add(AddProcess());
        }
    }

    public static void main(String[] args) {
        OUTER:
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.println("1- For SJF_Non_Preemptive .\n2- Non_preemptive_Priority .\n3 SRTF And RR And AG Scheduling .\n0- To Break .");
            System.out.println("Enter Your Choose :");
            int x = s.nextInt();
            switch (x) {
                case 1:
                    SJF_Non_Preemptive object = new SJF_Non_Preemptive();
                    break;
                case 2:
                    Non_preemptive_Priority object1 = new Non_preemptive_Priority();
                    break;
                case 3:
                    System.out.println("Please enter number of process : ");
                    Integer numprocess = s.nextInt();
                    System.out.println("Please enter quantum  : ");
                    Integer round = s.nextInt();
                    System.out.println("Please enter context switcing  : ");
                    Integer cswitch = s.nextInt();
                    Operations o = new Operations(numprocess, round, cswitch);
                    ShortestJob S = new ShortestJob(o.processes, o.context_switching);
                    S.work();
                    RoundRobin r = new RoundRobin(o.processes, o.round_robin, o.context_switching);
                    r.work();
                    Priority p = new Priority(o.processes);
                    p.work();
                    AG a = new AG(o.processes);
                    a.work();
                    break;
                default:
                    break OUTER;
            }
        }
    }
}
