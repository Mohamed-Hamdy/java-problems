/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author AA
 */
public class Process {
    String name;
    Integer arrival;
    Integer burst;
    Integer priority;
    Integer wating;
    Integer start;
    Integer finish;
    Integer quantam;
    public Process(String name,Integer arrival , Integer burst , Integer priority , Integer quantam)
    {
        this.name = name;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.quantam = quantam;
        this.wating = 0;
        this.start = -1;
        this.finish = -1;
    }
    public Process(Process p1)
    {
        this.name = p1.name;
        this.arrival = p1.arrival;
        this.burst = p1.burst;
        this.priority = p1.priority;
        this.quantam = p1.quantam;
        this.wating = 0;
        this.start = -1;
        this.finish = -1;

    }
}
