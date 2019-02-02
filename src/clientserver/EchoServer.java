package clientserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class EchoServer {
    final static int SERVER_SIZE = 5;
    static int clientCount = 0;
    static boolean gameState = true;
    
    // Global index variables
    static RandomNumGen random = new RandomNumGen();
    static ClientHandler[] ch = new ClientHandler[SERVER_SIZE];
    static String[] IPs = new String[SERVER_SIZE];
    static int[] rolesOrder = new int[SERVER_SIZE]; // role priority list
    static int[] userChoices = new int[SERVER_SIZE];

    public static void main(String[] args) throws IOException {
        // serverSocket is the server object
        ServerSocket serverSocket = new ServerSocket(9806);
        
        System.out.println("Server Running Port #: " + serverSocket.getLocalPort());

        // Running loop for getting clients change to 5
        while (clientCount != SERVER_SIZE)
        {
            Socket socket = new Socket();

            try
            {
                // Socket object to receive incoming client requests
                socket = serverSocket.accept();
                // will increment clientCount if successful
                connectClient(socket, clientCount);
            } 
            catch (Exception e)
            {
                socket.close();
                e.printStackTrace();
            }
        } // end while loop for connecting Clients

        // Game start message
        for (int i = 0; i < SERVER_SIZE; i++)
        {
            ch[i].dos.writeUTF("* All players are now connected!\n\n\t`'~- GAME START -~'`");
        } System.out.println("\nGame has started!");
        
        String playerMsg;
        do // run phases at least once
        {
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                ch[i].dos.writeUTF("\n------------------------------------\n"
                        + "Phase 01 - Chat : Begin!\n------------------------------------\n");
            }
            
            // Phase 1
            for (int i = 0; i < 5; i++) // # of Chats
            {
                for (int j = 0; j < SERVER_SIZE; j++) // # of Clients
                {
                    System.out.println("Player 0" + (j+1) + " is typing . . .");
                    // send "input" to j and "Player is typing" to others
                    playerMsg = ch[j].Phase1(i);
                    // send message to console
                    System.out.println("Player 0" + (j+1) + ": " + playerMsg);
                    
                    for (int k = 0; k < 5; k++)
                    {
                        // send message to all players
                        ch[k].dos.writeUTF(playerMsg);
                    }
                }
            } System.out.println();
            
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                ch[i].dos.writeUTF("\n------------------------------------\n"
                        + "Phase 02 - Action : Begin!\n------------------------------------\n");
            }

            // Action Phase
            int choice;
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                for (int j = 0; j < SERVER_SIZE; j++)
                {
                    if (j != i)
                        ch[j].dos.writeUTF("* Player 0" + (i + 1) + " is choosing their action");
                }
                choice = ch[i].Phase2();
                userChoices[i] = --choice;
            } System.out.println();

            // SERVER: Action choices
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                System.out.println("Player 0" + (i+1) + " chose " + userChoices[i]);
            } System.out.println();

            // For WitchDoc
            boolean reviveKill = false;
            
            // Action results
            String userResult = "";
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                if (ch[rolesOrder[i]].role.isDead())
                {
                    userResult = "You were killed.";
                }
                else if ( userChoices[rolesOrder[i]] == -1 )
                {
                    userResult = "You chose nobody.";
                }
                else
                {
                    // Witch Doctor reviving the dead
                    if ( i == 4 && ch[userChoices[rolesOrder[i]]].role.isDead() )
                    {
                        ch[userChoices[rolesOrder[i]]].dos.writeUTF("You were revived!!\n");
                    }
                    userResult = ch[rolesOrder[i]].role.action(ch[userChoices[rolesOrder[i]]].role);
                }
                
                ch[rolesOrder[i]].dos.writeUTF("\n------------------------------------\n"
                        + "Phase 03 - Results : " + userResult + '\n'
                        + "------------------------------------\n");
            }

            // Results Phase - 1: Set up message
            String resultsMsg = "";
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                ch[rolesOrder[i]].dos.writeUTF("result1");
                // change this to only print out who died ?
                resultsMsg += "= Player 0" + (i+1) + " =\n\t- Health: " + ch[i].role.getHealth() + '\n';
            }
            
            // Results Phase - 2: Send message
            for (int i = 0; i < SERVER_SIZE; i++)
            {
                ch[i].dos.writeUTF(resultsMsg);
                ch[i].dos.writeUTF("result2");
            }
            
            // Game State
            if (ch[rolesOrder[0]].role.isDead() || 
                    ch[rolesOrder[1]].role.isDead() && ch[rolesOrder[2]].role.isDead() && ch[rolesOrder[3]].role.isDead())
            {
                gameState = false;
                for (int i = 0; i < SERVER_SIZE; i++)
                {
                    ch[i].End();
                }
                // Tell all clients that the game is over
            }
            else
            {
                for (int i = 0; i < SERVER_SIZE; i++)
                {
                    ch[i].Continue();
                }
            }
            
        } while (gameState == true);
        
        System.out.println("GOOD GAME!");
        
    } // end main
    
    public static void connectClient(Socket s, int i) throws IOException
    {
        // add client address to array of strings
        System.out.println("Client #" + (i + 1) + " with " + s.getInetAddress());

        // Store IP in index
        IPs[i] = s.getInetAddress().getHostAddress();

        // in & out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        System.out.println("Assigning new thread for this client");

        // create a new thread object
        int ran = random.getRandomNum(i); // 1 to 5
        ch[i] = new ClientHandler(s, dis, dos, (i + 1), ran);
        ran--; // 0 to 4
        rolesOrder[ran] = i;

        // first string to client
        dos.writeUTF("* Successfully connected!\n---------------------------------"
                + "\nYou are Player 0" + (i+1) 
                + "\nYour role is " + ch[i].role.getRoleName() + "\n");

        clientCount++;
    }
}// end class