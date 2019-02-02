package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import Roles.*;

// ClientHandler class 
class ClientHandler extends Thread { 
    //AS| Added clientID so we can identify who is sending what to server
    final int clientID;
    final Socket s;
    final DataInputStream dis;
    final DataOutputStream dos;
    BaseRole role;

    ClientHandler()
    {
      	dis = null;
       	dos = null;
       	s = null;
        clientID = 0;
    }
                        
    // Constructor
    ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, int clientID, int roleNum) 
    {
        this.s = s;
	this.dis = dis;
	this.dos = dos;
	this.clientID = clientID;
	setRole(roleNum); //set role based on random number
    }
			
    //write code for phase1 to process input from clients
    //while loop taken out, no input to exit
    public String Phase1(int n) //basic input from user to set target
    {
        try
        {
            n++;
            // Timer t = new task Timer stuff
            
            // Prompt user to input
            dos.writeUTF("input");
            String input = dis.readUTF();
            // return (username + ": " + input);
            if (role.isDead())
            {
                return ("Ghost Player " + clientID + ": " + input);
            }
            else
            {
                return ("Player " + clientID + ": " + input);
            }
        }
        catch (Exception e)
        {
            // ignore
            return "Error";
        }
   }//end phase1 method
            
    public int Phase2()//basic input from user to set target
    {
        try
        {
            // Timer t = new task Timer stuff
            String userMessage = "Your ability is: " + role.getActionName() + '\n';
            userMessage += "- " + role.getActionDescription();
            userMessage += "\nWho do you " + role.getActionName() + "?: ";
            dos.writeUTF(userMessage);
            
            dos.writeUTF("action");
            
            String input = dis.readUTF();
            //return ("Player " + clientID + ": " + input);
            return Integer.parseInt(input);
        }
        catch (Exception e)
        {
            // ignore for now
            return 0;
        }
   } //end phase2 method
    
    public void End()
    {
        try
        {
            dos.writeUTF("end");
        }
        catch (Exception e)
        {
            // ignore for now
            System.exit(0);
        }
    }
    
    public void Continue()
    {
        try
        {
            dos.writeUTF("Continuing Game.\n\n");
        }
        catch (Exception e)
        {
            // ignore for now
            System.exit(0);
        }
    }

    @Override
    public void run() 
    {
        System.out.println("\nRUN");
    }
    
    //set role based on number
    private void setRole(int num)
    {
        switch (num)
        { 
        case 1: 
            role = new Jason();
            break;
        case 2: 
            role = new Warrior();
            break;
        case 3: 
            role = new Cleric();
            break;
        case 4: 
            role = new Investigator(); 
            break;
        case 5: 
            role  = new WitchDoc();
            break;
        }	
    }
}