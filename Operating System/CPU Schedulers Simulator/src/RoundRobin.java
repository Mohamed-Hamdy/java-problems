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

public class RoundRobin {
    ArrayList<Process> processes;
    Integer quantum;
    Integer context;
    ArrayList<String>names;
    ArrayList<Integer>times;



    public RoundRobin(ArrayList<Process> arr , Integer quantum , Integer context) {
       processes = new ArrayList<>();
        for(int i = 0 ;i < arr.size() ; i++) {
            Process p = new Process(arr.get(i));
            processes.add(p);
        }
        this.quantum = quantum;
        this.context = context;
        names = new ArrayList<>();
        times = new ArrayList<>();
    }

    public void work()
    {
        Operations.SortArrival(processes);
        Integer timer = processes.get(0).arrival;
        int rem = processes.size();
        boolean infi = false;
        while(rem > 0) {
            if(infi)
                timer++;
            infi = true;
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).finish != -1 || processes.get(i).arrival>timer)
                    continue;
                infi = false;
                if (processes.get(i).start == -1)
                    processes.get(i).start = timer;
                Integer wait = 0;
                boolean switching = false;
                if(names.size() == 0)
                {
                    names.add(processes.get(i).name);
                    times.add(timer);

                }
                else if(  !processes.get(i).name.equals(names.get(names.size()-1))) {
                    switching = true;
                    timer += context;
                    names.add(processes.get(i).name);
                    times.add(timer);
                }

                if (processes.get(i).burst > quantum) {
                    wait += quantum;
                    processes.get(i).burst -= quantum;
                } else {
                    wait += processes.get(i).burst;
                    processes.get(i).burst = 0;
                    processes.get(i).finish = timer + wait;
                    rem--;
                }
                for (int j = 0; j < processes.size(); j++) {
                    if (j == i || processes.get(j).finish!= -1) continue;
                    if (timer >= processes.get(j).arrival )
                        if(switching == false)
                            processes.get(j).wating += wait;
                        else
                            processes.get(j).wating += wait+context;
                    else if (timer+wait > processes.get(j).arrival)
                        processes.get(j).wating += (timer+wait-processes.get(j).arrival);
                }
                timer+=wait;

            }
        }
        System.out.println("\n\n\nRound Robin : ");
        System.out.println("the process order :");
        for(int i = 0 ; i < names.size() ; i++)
            System.out.println(times.get(i) + "  " + names.get(i));
        System.out.println("the wait of each process :");
        Double avgwait = 0.0;
        for(int i =0 ; i< processes.size() ; i++)
        {
            System.out.println(processes.get(i).wating +" "+ processes.get(i).name);
            avgwait += (double)processes.get(i).wating;
        }
        System.out.println("the turnaroun of each process :");
        Double avgturnaroun = 0.0;
        for(int i =0 ; i< processes.size() ; i++)
        {
            System.out.println(processes.get(i).name + " " + (processes.get(i).finish - processes.get(i).start));
            avgturnaroun += (double)(processes.get(i).finish - processes.get(i).start);
        }
        System.out.println("the avg waiting time is: " + avgwait/processes.size());
        System.out.println("the avg turnaround time is: " + avgturnaroun/processes.size());

    }
}
