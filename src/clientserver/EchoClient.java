package clientserver;

import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class EchoClient {
    static boolean isDead = false;
    static boolean gameState = true;
    static boolean valid = true;
    
    public static void main(String[] args) throws IOException 
    { 
        MenuDisplayUI menu = new MenuDisplayUI();
        menu.casesDisplay();//run menu b4 game begins

        Socket s = new Socket();		
        DataInputStream dis;
        DataOutputStream dos;
        String server_msg;
        int messageCount = 0;

        Scanner scn = new Scanner(System.in); 
        try
        {
            // establish the connection with server port 9806
            s = new Socket("localhost", 9806);

            dis = new DataInputStream(s.getInputStream()); 

            System.out.println(dis.readUTF()); //first string hello from server
            //speak to server and clients
          //  scn.close();
        }
        catch (Exception e)
        {
            // TODO: Error message
            System.exit(0);
        }

        // obtaining input and out streams 			
        dis = new DataInputStream(s.getInputStream()); 
        dos = new DataOutputStream(s.getOutputStream()); 

        System.out.println("* Waiting for all players to connect . . .");
        
        do
        {
            messageCount = 0;
            
            // Phase 1
            while (messageCount != 5)
            {
                try
                {
                    // Receive messages from server when possible
                    server_msg = dis.readUTF();
                    
                    // Check that server prompted user
                    if (server_msg.equals("input"))
                    {
                        System.out.print("Enter message 0" + ++messageCount + ": ");
                        server_msg = scn.nextLine(); //Give answer
                        dos.writeUTF(server_msg);
                    }
                    else
                    {
                        System.out.println(server_msg);
                    }
                }
                catch (Exception e)
                {
                    // do nothing
                }
                
            }//end phase 1

            // Action Input
            while (true)
            {
                try
                {
                    server_msg = dis.readUTF();

                    // Check that server prompted user
                    if (server_msg.equals("action"))
                    {
                        int action = -1;
                        do
                        {
                            System.out.print("> ");
                            server_msg = scn.nextLine(); //Give answer
                            try
                            {
                                action = Integer.parseInt(server_msg);
                            }
                            catch (Exception e)
                            {
                                System.out.println("Invalid Input.");
                                action = -1;
                                continue;
                            }
                            
                            if ( action < 0 || action > 5 )
                            {
                                System.out.println("Number is out of bounds.");
                            }
                        } while ( action < 0 || action > 5 );
                        
                        dos.writeUTF(server_msg);
                        break;
                    }
                    else
                    {
                        System.out.println(server_msg);
                    }
                }
                catch (Exception e)
                {
                    // do nothing
                }
            }

            // Action result
            while (true)
            {
                try
                {
                    server_msg = dis.readUTF();

                    // Check that server prompted user
                    if (server_msg.equals("result1"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println(server_msg);
                    }
                }
                catch (Exception e)
                {
                    // do nothing
                }
            }
            
            // Results Phase
            while (true)
            {
                try
                {
                    server_msg = dis.readUTF();

                    // Check that server prompted user
                    if (server_msg.equals("result2"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.println(server_msg);
                    }
                }
                catch (Exception e)
                {
                    // do nothing
                }
            }
            
            // Game State
            while (true)
            {
                try
                {
                    //System.out.print(dis.readUTF());
                    // Receive messages from server when possible
                    server_msg = dis.readUTF();

                    // Check that server prompted user
                    if (server_msg.equals("end"))
                    {
                        gameState = false;
                        break;
                    }
                    else
                    {
                        System.out.print(server_msg);
                        // move on
                        break;
                    }
                }
                catch (Exception e)
                {
                    // ignore for now
                }
            }
        } while (gameState == true);
    }
}