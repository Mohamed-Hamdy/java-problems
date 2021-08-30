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

public class AG {

    ArrayList<Process> processes;
    ArrayList<Process> fcfs;
    ArrayList<Process> periority;
    ArrayList<Process> sjf;
    ArrayList<Process> finished;
    Integer timer,nexttimer;
    ArrayList<String>names;
    ArrayList<Integer>times;


    public AG(ArrayList<Process> arr) {
        processes = new ArrayList<>();
        for(int i = 0 ;i < arr.size() ; i++) {
            Process p = new Process(arr.get(i));
            processes.add(p);
        }
        fcfs = new ArrayList<>();
        periority = new ArrayList<>();
        sjf = new ArrayList<>();
        finished = new ArrayList<>();
        timer = 0 ;
        nexttimer = 0;
        names = new ArrayList<>();
        times = new ArrayList<>();    }

    private void fcfs()
    {
        Operations.SortArrival(fcfs);
        for (int i = 0; i < fcfs.size(); i++) {
            Integer wait = 0;
            if(names.size() == 0 || !fcfs.get(i).name.equals(names.get(names.size()-1))) {
                names.add(fcfs.get(i).name);
                times.add(timer);
            }
            System.out.println("before : " + fcfs.get(i).name + " " + fcfs.get(i).quantam);
            if (fcfs.get(i).start == -1) {
                fcfs.get(i).start = timer;
            }
            boolean finish;
            Integer availquan = (fcfs.get(i).quantam+3)/4;
            if (fcfs.get(i).burst > availquan) {
                wait = availquan;
                fcfs.get(i).burst -= availquan;
                fcfs.get(i).quantam+=2;
                timer += availquan;
                finish = false;
                System.out.println("after : " + fcfs.get(i).name + " " + fcfs.get(i).quantam);

            } else {//fcfs.get(i).burst <= availquan
                Integer temp = fcfs.get(i).burst;
                wait = temp;
                fcfs.get(i).burst = 0;////////////true
                timer += temp;
                fcfs.get(i).finish = timer;
                finish = true;
                System.out.println( fcfs.get(i).name + " finished ");
            }

            for (int j = i + 1; j < fcfs.size(); j++)
                fcfs.get(j).wating += wait;
            for(int j = 0 ; j < periority.size() ; j++)
                periority.get(j).wating += wait;
            for(int j = 0 ; j < sjf.size() ; j++)
                sjf.get(j).wating += wait;
            if(finish)
                finished.add(fcfs.get(i));
            else
                periority.add(fcfs.get(i));
            fcfs.remove(i);
            i--;
            if(timer >= nexttimer)
                return;
        }


    }

    public void Periority()
    {
        Operations.SortPriority(periority);
        for (int i = 0; i < periority.size(); i++) {
            Integer wait = 0;
            if(names.size() == 0 || !periority.get(i).name.equals(names.get(names.size()-1))) {
                names.add(periority.get(i).name);
                times.add(timer);
            }
            System.out.println("before : " + periority.get(i).name + " " + periority.get(i).quantam);
            boolean finish;
            Integer availquan = (periority.get(i).quantam+3)/4;
            if (periority.get(i).burst > availquan) {
                wait = availquan;
                periority.get(i).burst -= availquan;
                periority.get(i).quantam+=(periority.get(i).quantam/2);//////////////what
                timer += availquan;
                finish = false;
                System.out.println("after : " + periority.get(i).name + " " + periority.get(i).quantam);
            } else {//periority.get(i).burst <= availquan
                Integer temp = periority.get(i).burst;
                wait = temp;
                periority.get(i).burst = 0;////////////true
                timer += temp;
                periority.get(i).finish = timer;
                finish = true;
                System.out.println( periority.get(i).name + " finished ");
            }

            int size = processes.size();
            for (int j = 0; j < size; j++) {
                if (processes.get(0).arrival > timer)
                    break;
                fcfs.add(processes.get(0));
                processes.remove(0);
            }
            if (processes.size() == 0)
                nexttimer = 10000000;
            else
                nexttimer = processes.get(0).arrival;


            for (int j = 0; j < fcfs.size(); j++)
                fcfs.get(j).wating += timer - fcfs.get(i).arrival ;
            for(int j = i+1 ; j < periority.size() ; j++)
                periority.get(j).wating += wait;
            for(int j = 0 ; j < sjf.size() ; j++)
                sjf.get(j).wating += wait;
            if(finish)
                finished.add(periority.get(i));
            else
                sjf.add(periority.get(i));
            periority.remove(i);
            i--;
            if(timer >= nexttimer || fcfs.size()>0)
                return;
        }

    }

    public void sjf()
    {
        Operations.SortBurst(sjf);
        for (int i = 0; i < sjf.size(); i++) {
            Integer wait = 0;
            if( names.size() == 0  || !sjf.get(i).name.equals(names.get(names.size()-1))) {
                names.add(sjf.get(i).name);
                times.add(timer);
            }
            System.out.println("before : " + sjf.get(i).name + " " + sjf.get(i).quantam);
            if (sjf.get(i).burst > (nexttimer - timer)) {
                wait = (nexttimer - timer);
                sjf.get(i).burst -= (nexttimer - timer);
                sjf.get(i).quantam*=2;//////////////what
                sjf.get(i).quantam-=2;
                timer = nexttimer;
                System.out.println("after : " + sjf.get(i).name + " " + sjf.get(i).quantam);
            } else {
                System.out.println( sjf.get(i).name + " finished ");
                Integer temp = sjf.get(i).burst;
                wait = temp;
                sjf.get(i).burst -= 0;/////////////////////////wrong
                timer += temp;
                sjf.get(i).finish = timer;//++
                finished.add(sjf.get(i));
                sjf.remove(i);
                i--;

            }
            for (int j = i + 1; j < sjf.size(); j++)
                sjf.get(j).wating += wait;
            if (timer == nexttimer)
                break;
        }
        timer = nexttimer;
    }

    public void work()
    {
        System.out.println("\n\n\nAG : ");
        System.out.println("the quantum order: ");
        Operations.SortArrival(processes);
        timer = processes.get(0).arrival;
        while(processes.size() > 0) {
            int size = processes.size();
            for (int i = 0; i < size; i++) {
                if (processes.get(0).arrival > timer)
                    break;
                fcfs.add(processes.get(0));
                processes.remove(0);
            }

            if (processes.size() == 0)
                nexttimer = 10000000;
            else
                nexttimer = processes.get(0).arrival;

            fcfs();
            if (timer >= nexttimer) continue;
            if(fcfs.size() == 0)
                Periority();
            if (timer >= nexttimer) continue;
            if(fcfs.size() == 0 && periority.size()==0)
                sjf();

        }

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

