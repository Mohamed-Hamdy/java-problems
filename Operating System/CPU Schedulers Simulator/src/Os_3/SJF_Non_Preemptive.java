/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Os_3;

import java.util.Scanner;

/**
 *
 * @author AA
 */
public class SJF_Non_Preemptive {

    public SJF_Non_Preemptive() {
        main_Function();
    }



    public static void Table_with_AT(int num, int[][] mat) {
        int Old_ST, val = 0;
        int x, y;
        /*
            A.T= Arrival Time
            B.T= Burst Time
            C.T= Completion Time
            T.T = Turn around Time = C.T - A.T
            W.T = Waiting Time = T.T - B.T
         */

        mat[0][3] = mat[0][1] + mat[0][2]; // Start Time in first row = AT + BT
        // Then ST = Old ST + BT of cur index 

        int value = mat[0][3] - mat[0][1]; // Turnaround Time in first row = ST - AT
        // Then Turnaround Time = cur BT - cur AT
        mat[0][4] = value - mat[0][2]; // Waiting Time in first row = TAT - BT

        System.out.println();
        for (int i = 0; i < num; i++) {
            System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\t\t" + mat[i][3] + "\t\t" + mat[i][4] + "\t\t" + "\n");
        }

        for (int i = 1; i < num; i++) {
            Old_ST = mat[i - 1][3]; // Temp = ST[i-1][3]
            int Cur_BT = mat[i][2]; // low = BT[i][2]
            for (int j = i; j < num; j++) {
                // 
                if (Old_ST >= mat[j][1] && Cur_BT >= mat[j][2]) { // if ST[i-1][3] >= AT[j][1] && BT[i][2] >= BT[j][2]
                    Cur_BT = mat[j][2];
                    val = j;
                }

            }

            System.out.println("\n\nAfter J Loop .\n" + "Val = " + val + "\n");
            for (int k = 0; k < num; k++) {
                System.out.print(mat[k][0] + "\t\t" + mat[k][1] + "\t\t" + mat[k][2] + "\t\t" + mat[k][3] + "\t\t" + mat[k][4] + "\t\t" + "\n");
            }

            mat[val][3] = Old_ST + mat[val][2];  // cur ST = Old ST + cur BT  
            mat[val][5] = mat[val][3] - mat[val][1]; // cur TAT = cur BT - cur AT  
            mat[val][4] = mat[i - 1][3] - mat[i][1]; // cur WT = last ST - cur AT
            System.out.println("\n\nAfter lows .\n");
            for (int k = 0; k < num; k++) {
                System.out.print(mat[k][0] + "\t\t" + mat[k][1] + "\t\t" + mat[k][2] + "\t\t" + mat[k][3] + "\t\t" + mat[k][4] + "\n");
            }
            System.out.println(" i = " + i + "\nval =" +val);
            for (int k = 0; k < 4; k++) {
                x = mat[val][k];
                y = mat[i][k];
                mat[i][k] = x;
                mat[val][k] = y;
                x = 0;
                y = 0;
            }

        }
    }

    public static void Table_without_AT(int num, int[][] mat) {
        int Old_ST, val = 0;
        int x, y;
        /*
            A.T= Arrival Time
            B.T= Burst Time
            C.T= Completion Time
            T.T = Turn around Time = C.T - A.T
            W.T = Waiting Time = T.T - B.T
        */

        mat[0][3] = mat[0][1] + mat[0][2]; // Start Time in first row = AT + BT
        // Then ST = Old ST + BT of cur index 

        mat[0][5] = mat[0][3] - mat[0][1]; // Turnaround Time in first row = ST - AT
        // Then Turnaround Time = cur BT - cur AT
        mat[0][4] = mat[0][5] - mat[0][2]; // Waiting Time in first row = TAT - BT

        System.out.println();
        for (int i = 0; i < num; i++) {
            System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\t\t" + mat[i][3] + "\t\t" + mat[i][4]  + "\t\t" + mat[i][5]+ "\t\t" + "\n");
        }

        for (int i = 1; i < num; i++) {
            Old_ST = mat[i - 1][3]; // Temp = ST[i-1][3]
            int Cur_BT = mat[i][2]; // low = BT[i][2]
            for (int j = i; j < num; j++) {
                if (Old_ST >= mat[j][1] && Cur_BT >= mat[j][2]) { // if ST[i-1][3] >= AT[j][1] && BT[i][2] >= BT[j][2]
                    Cur_BT = mat[j][2];
                    val = j;
                }
            }

            System.out.println("\n\nAfter J Loop .\n" + "Val = " + val + "\n");
            for (int k = 0; k < num; k++) {
                System.out.print(mat[k][0] + "\t\t" + mat[k][1] + "\t\t" + mat[k][2] + "\t\t" + mat[k][3] + "\t\t" + mat[k][4]  + "\t\t" + mat[i][5]+ "\t\t" + "\n");
            }

            mat[val][3] = Old_ST + mat[val][2];  // cur ST = Old ST + cur BT  
            mat[val][5] = mat[val][2] - mat[val][1]; // cur TAT = cur BT - cur AT  
            mat[val][4] = mat[i - 1][3] - mat[i][1]; // cur WT = last ST - cur AT
            System.out.println("\n\nAfter lows .\n");
            for (int k = 0; k < num; k++) {
                System.out.print(mat[k][0] + "\t\t" + mat[k][1] + "\t\t" + mat[k][2] + "\t\t" + mat[k][3] + "\t\t" + mat[k][4] + "\t\t" + mat[i][5] + "\n");
            }
            for (int k = 1; k < 4; k++) {
                x = mat[val][k];
                y = mat[i][k];
                mat[i][k] = x;
                mat[val][k] = y;
                x = 0;
                y = 0;
            }
        }
    }
        public static void SortBY_AT(int num, int[][] mat) {
        int x, y;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num - i - 1; j++) {
                if (mat[j][1] > mat[j + 1][1]) {
                    for (int k = 0; k < 3; k++) {
                        x = mat[j][k];
                        y = mat[j + 1][k];
                        mat[j + 1][k] = x;
                        mat[j][k] = y;
                        x = 0;
                        y = 0;
                    }
                }
            }
        }
    }

    public static void SortBY_BT(int num, int[][] mat) {
        int x, y;
        for (int i = 0; i < num; i++) {
            for (int j = 1; j < num - i - 1; j++) {
                if (mat[j][2] > mat[j + 1][2]) {
                    for (int k = 0; k < 3; k++) {
                        x = mat[j][k];
                        y = mat[j + 1][k];
                        mat[j + 1][k] = x;
                        mat[j][k] = y;
                        x = 0;
                        y = 0;
                    }
                }
            }
        }
    }

    public static void main_Function() {
        int num, temp, x, y;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Process: ");
        num = sc.nextInt();

        int[][] mat = new int[num][6];
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                

        System.out.println("...Enter the process ID...\n");
        for (int i = 0; i < num; i++) {
            System.out.print("...Process " + i + 1 + "...\n");
            System.out.print("Enter Process Id: ");
            mat[i][0] = sc.nextInt();
            System.out.print("Enter Arrival Time: ");
            mat[i][1] = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            mat[i][2] = sc.nextInt();
        }
        System.out.print("Before Arrange...\n");
        System.out.print("Process ID\tArrival Time\tBurst Time\n");
        for (int i = 0; i < num; i++) {
            System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\n");
        }
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                

        for (int k = 0; k < num; k++) {
            if (mat[k][1] != 0) {
                System.out.print("\nAfter AT Sort...\n");
                SortBY_AT(num, mat);

                for (int i = 0; i < num; i++) {
                    System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\n");
                }
                System.out.print("\nAfter Burst Sort...\n");
                SortBY_BT(num, mat);

                for (int i = 0; i < num; i++) {
                    System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\n");
                }

                Table_with_AT(num, mat);
                System.out.print("\nFinal Result...\n");
                System.out.print("Process ID\tArrival Time\tBurst Time\tStart Time\tWaiting Time\tTurnaround Time\n");
                for (int i = 0; i < num; i++) {
                    System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\t\t" + mat[i][3] + "\t\t" + mat[i][4] + "\t\t" + mat[i][5] + "\n");
                }

                for (int i = 0; i < num; i++) {
                    int z = mat[i][2];
                    for (int j = 1; j < num; j++) {
                        
                        if ((z == mat[j][2]) && (j == mat.length - 1)) {
                            if (mat[i][1] > mat[j][1]) { // if AT Time of index i > Arrival Time of index j and j > i 
                                for (int W = 0; W < 3; W++) {
                                    x = mat[j][W];
                                    y = mat[i][W];
                                    mat[i][W] = x;
                                    mat[j][W] = y;
                                    x = 0;
                                    y = 0;
                                }
                            }
                        }
                    }

                }
                System.out.print("\nFinal Result...\n");
                System.out.print("Process ID\tArrival Time\tBurst Time\tEmpty Time\tWaiting Time\tTurnaround Time\n");
                for (int i = 0; i < num; i++) {
                    System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\t\t" + mat[i][3] + "\t\t" + mat[i][4] + "\t\t" + mat[i][5] + "\n");
                }
                break;

            } // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
            // case two AT = Zeros
            else if (mat[k][1] == 0 && (k == mat.length - 1)) {

                //SortBY_BT_AT_IS_Zeros(num, mat);
                System.out.print("\nAfter Burst Sort ( AT is all zeros )...\n");

                for (int i = 0; i < num; i++) {
                    System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\t\t" + mat[i][3] + "\n");
                }
                Table_without_AT(num, mat);
                System.out.print("\nFinal Result...\n");
                System.out.print("Process ID\tArrival Time\tBurst Time\tStart Time\tWaiting Time\tTurnaround Time\n");
                for (int i = 0; i < num; i++) {
                    System.out.print(mat[i][0] + "\t\t" + mat[i][1] + "\t\t" + mat[i][2] + "\t\t" + mat[i][3] + "\t\t" + mat[i][4] + "\t\t" + mat[i][5] + "\n");
                }
                for (int i = 0; i < num; i++) {
                    int z = mat[i][3];
                    for (int j = 1; j < num; j++) {
                        if ((z == mat[j][3]) && (j == mat.length - 1)) {
                            if (mat[i][3] > mat[j][3]) {
                                for (int w = 0; w < 3; w++) {
                                    x = mat[j][w];
                                    y = mat[i][w];
                                    mat[i][w] = x;
                                    mat[j][w] = y;
                                    x = 0;
                                    y = 0;
                                }
                            }
                        }
                    }

                }

            }
        }
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Chant Chart .");
        System.out.print("  ");
        for (int k = 0; k < num; k++) {
            System.out.print(mat[k][0] + "  ");
        }
        System.out.print("\n0");
        for (int k = 0; k < num; k++) {
            System.out.print(" " + mat[k][3]);
        }
        System.out.println();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.print("Average Waiting Time ");
        int temp_var  = 0 ; 
        for(int i = 0 ; i < num; i++){
           temp_var += mat[i][4]; 
        }
        System.out.print(temp_var/num + "\n");
        
        System.out.print("Average Turnaround Time ");
        int temp_var1  = 0 ; 
        for(int i = 0 ; i < num; i++){
           temp_var1 += mat[i][5]; 
        }
        System.out.print(temp_var1/num + "\n");
    }
}
/*
First input the processes with their arrival time, burst time and priority.
Sort the processes, according to arrival time then sort by Burst time from index 1 .  
if you hava two values with the same arrival time swap two items.
if arrival time is 0 ( all values ) sort by burst time.
Now simply apply FCFS algorithm.
 */

