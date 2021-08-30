/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author AA
 */
import java.util.ArrayList;

public class Priority {
    ArrayList<Process> processes;
    ArrayList<Process> executed;
    ArrayList<Process> finished;
    ArrayList<String>names;
    ArrayList<Integer>times;

    public Priority(ArrayList<Process> arr) {
        processes = new ArrayList<>();
        for(int i = 0 ;i < arr.size() ; i++) {
            Process p = new Process(arr.get(i));
            processes.add(p);
        }
        executed = new ArrayList<>();
        finished = new ArrayList<>();
        names = new ArrayList<>();
        times = new ArrayList<>(); 
    }

    public void work()
    {

        Operations.SortArrival(processes);
        while(processes.size() > 0) {
            Integer timer = processes.get(0).arrival;
            int size = processes.size();
            for (int i = 0; i < size; i++) {
                if (processes.get(0).arrival != timer)
                    break;
                executed.add(processes.get(0));
                processes.remove(0);
            }

            Integer nexttimer;
            if (processes.size() == 0)
                nexttimer = 1000000;
            else
                nexttimer = processes.get(0).arrival;
            Operations.SortPriority(executed);
            for (int i = 0; i < executed.size(); i++) {
                Integer wait = 0;
                if(names.size() == 0  || !executed.get(i).name.equals(names.get(names.size()-1))) {
                    names.add(executed.get(i).name);
                    times.add(timer);
                }

                if (executed.get(i).start == -1) {
                    executed.get(i).start = timer;
                }
                if (executed.get(i).burst > (nexttimer - timer)) {
                    wait = (nexttimer - timer);
                    executed.get(i).burst -= (nexttimer - timer);
                    timer = nexttimer;
                } else {
                    Integer temp = executed.get(i).burst;
                    ;/////////
                    wait = temp;
                    executed.get(i).burst = 0;////////////wrong
                    timer += temp;
                    executed.get(i).finish = timer;
                }
                for (int j = i + 1; j < executed.size(); j++) {
                    executed.get(j).wating += wait;
                    if (executed.get(j).arrival < timer - 20 && executed.get(j).priority > 1)
                        executed.get(j).priority--;
                }
                if (timer == nexttimer)
                    break;
            }

            size = executed.size();
            int p = 0;
            for (int i = 0; i < size; i++) {
                if (executed.get(p).finish != -1) {
                    finished.add(executed.get(p));
                    ;///////////////
                    executed.remove(p);
                } else
                    p++;
            }


        }


        System.out.println("\n\n\nPeriority : ");
        System.out.println("the process order :");
        for(int i = 0 ; i < names.size() ; i++)
            System.out.println(times.get(i) + "  " + names.get(i));
        System.out.println("the wait of each process :");
        Double avgwait = 0.0;
        for(int i =0 ; i< finished.size() ; i++)
        {
            System.out.println(finished.get(i).wating +" "+ finished.get(i).name);
            avgwait += (double)finished.get(i).wating;
        }
        System.out.println("the turnaroun of each process :");
        Double avgturnaroun = 0.0;
        for(int i =0 ; i< finished.size() ; i++)
        {
            System.out.println(finished.get(i).name + " " + (finished.get(i).finish - finished.get(i).start));
            avgturnaroun += (double)(finished.get(i).finish - finished.get(i).start);
        }
        System.out.println("the avg waiting time is: " + avgwait/finished.size());
        System.out.println("the avg turnaround time is: " + avgturnaroun/finished.size());



    }
}

