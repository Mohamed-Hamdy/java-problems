/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_2;

/**
 *
 * @author AA
 */
import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;

public final class Router {

    private final Queue<Network> Network_array_Connections;
    private final Queue<Device> Devices_array_Connections;
    private final int Num_of_wifi_Connections;
    private final Semaphore mSemaphore;
    private final OutputForm mOut;

    public Router(int numOfconnections) {
        this.Network_array_Connections = new LinkedList<>();
        this.Devices_array_Connections = new LinkedList<>();
        this.Num_of_wifi_Connections = numOfconnections;
        initializaNetwork();
        this.mSemaphore = new Semaphore(Num_of_wifi_Connections);
        this.mOut = new OutputForm();

    }

    public void initializaNetwork() {
        for (int i = 0; i < Num_of_wifi_Connections; i++) {
            this.Network_array_Connections.add(new Network(i));
        }
    }

    public Semaphore getSemaphore() {
        return this.mSemaphore;
    }

    public synchronized boolean connectToNetwork(Device CurrentDevice) {
        if (this.Network_array_Connections.size() > 0) {
            CurrentDevice.setConnection(this.Network_array_Connections.poll());
            this.Devices_array_Connections.poll();
            return true;
        }
        return false;
    }

    public synchronized void logOut(Device CurrentDevice) {
        this.Network_array_Connections.add(CurrentDevice.getConnection());
        CurrentDevice.setConnection(null);
    }

    public void addDevices(String[] str, String[] type, int size, int connect_number) throws InterruptedException {
            for (int i = 0; i < size; i++) {
                if (i < connect_number) {
                    new Device(str[i], type[i], this , mOut).arrived_function();        
                } else {
                    new Device(str[i], type[i], this , mOut).arrived_wait_function();
                }
            }
            
            for (int i = 0; i < size; i++) {
                    new Device(str[i], type[i], this , mOut).occupied_function();                
            }
            
    }

    void reserve(Device newDevice) {
        Devices_array_Connections.add(newDevice);
    }

}
