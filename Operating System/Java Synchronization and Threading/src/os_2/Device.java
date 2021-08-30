/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_2;

import java.util.ArrayList;

/**
 *
 * @author AA
 */
public class Device {

    private String Name = "";
    private String Type = "";
    private Network Network_Connections;
    private final Router Router;
    private final OutputForm mOut;



    public Device(String name, String type, Router router, OutputForm out) throws InterruptedException {
        this.Name = name;
        this.Type = type;
        this.Router = router;
        this.mOut = out;
    }

    void setConnection(Network connection) {
        this.Network_Connections = connection;
    }

    public Network getConnection() {
        return this.Network_Connections;
    }

    public void arrived_function() throws InterruptedException {

        this.Router.reserve(this);
        //System.out.println("[" + Type + "] " + Name + " arrived.");
        this.mOut.addUpdates("[" + Type + "] " + Name + " arrived." + "\n");
        this.Router.getSemaphore().acquire();
    }

    public void occupied_function() throws InterruptedException {

        this.Router.connectToNetwork(this);
        //System.out.println("Connection " + Network_Connections.getConnection() + " : " + Name + " occupied");
        this.mOut.addUpdates("Connection " + Network_Connections.getConnection() + " : " + Name + " occupied." + "\n");
        
        //System.out.println("Connection " + Network_Connections.getConnection() + " : " + Name + " performs online activity");
        this.mOut.addUpdates("Connection " + Network_Connections.getConnection() + " : " + Name + " performs online activity." + "\n");


        //System.out.println("Connection " + Network_Connections.getConnection() + " : " + Name + " log out.\n\n");
        this.mOut.addUpdates("Connection " + Network_Connections.getConnection() + " : " + Name + " log out." + "\n\n");
       // this.mOut.addUpdates("The Next Device That Take The number Connection will wait a few Second ."+ "\n\n");
        
        Router.logOut(this);
        this.Router.getSemaphore().release();

    }
    
    void arrived_wait_function() {
        //System.out.println("[" + Type + "] " + Name + " arrived and wait.");
        this.mOut.addUpdates("[" + Type + "] " + Name + " arrived and will wait." + "\n");

    }

}
