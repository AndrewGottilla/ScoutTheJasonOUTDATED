package clientserver;

import java.util.Scanner;
import java.io.IOException;

public class MenuDisplayUI {
    
    // Method to display the MenuDisplayUI with selections 
    public static String menuDisplay()
    {
        return
        (
            "\n\nWelcome to Scout The Jason \n----------------------------------\n"
            + "Select one of the options below\n----------------------------------\n"
                + "1 - Read Game Overview \n"
                + "2 - Get Discord Link\n"
                + "3 - View Available Roles\n"
                + "4 - Hints & Tips \n"
                + "5 - Play the game\n"
                + "6 - Exit\n"
            + "Enter Choice: \n"
        );
    }
    
    public void casesDisplay() throws IOException
    {
        //scanner for user selection
        Scanner uisel = new Scanner(System.in);
        
        int selection;
        boolean run = true;
        
        do
        {
            // display MenuDisplayUI and allow user to input again
            System.out.printf("%s", menuDisplay());
            selection = uisel.nextInt();
            
            switch (selection)
            {
            case 1:
                System.out.printf
                (
                    "Game Overview \r\n" + 
                    "Welcome to the Scout The Jason game, a text based game which will require a bit of creativity to be declared the winner. \r\n" + 
                    "\r\n" + 
                    "Each player/role will have it's own set of abilities. \r\n" + 
                    "These actions and abilities are the fundamental pieces of diversity for Scout the Jason.\r\n" + 
                    "Each player will be assigned a randomly generated role.\r\n" + 
                    "Each role has its own unique action. "
                );
                break;
            case 2:
                System.out.printf
                (
                    "Please join our discord at the following: https://discord.gg/ZjmjM73\r\n" + 
                    "\r\n" + 
                    "You will then be able to see our game related annoucements, changelog, roles available, as-well as acccess other relevant information! \r\n" + 
                    "\r\n" + 
                    "Proper permissions will be asigned to you by an admin or moderator once you join."
                );
                break;
            case 3:
                System.out.printf("\n\n \t\t\t\t\t\tThe roles that exist in Scout The Jason:\n");
                System.out.printf("|--------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                System.out.printf("|%-25s %-10s %-20s %45s", "Role", "Actions", "Result", "Restrictions" );
                System.out.printf("%56s\n","|");
                System.out.printf("|--------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                System.out.printf("|%-25s %-10s %-20s %45s", "Jason", "Murder", "Kill another player", "" );
                System.out.printf("%56s\n","|");
                System.out.printf("|%-25s %-10s %-20s %45s", "Warrior", "Strike", "Strike another Player for 40 damage", "" );
                System.out.printf("%41s\n","|");
                System.out.printf("|%-25s %-10s %-20s %59s", "Cleric", "Remedy", "Heal 50 points of health", "1 Self-Remedy, Cannot overheal" );
                System.out.printf("%38s\n","|");
                System.out.printf("|%-25s %-10s %-20s %45s", "Investigator", "Scope", "Reveal role of another Player", "" );
                System.out.printf("%47s\n","|");
                System.out.printf("|%-25s %-10s %-20s %49s", "Witch Doctor", "Bewitch", "Kill/Revive any Player, unless they are Jason", "Can only Bewitch once, Cannot kill Jason " );
                System.out.printf("%27s\n","|");
                System.out.printf("|--------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                break;
            case 4:
                System.out.printf(" Hints & Tricks for playing the game\n");
                System.out.printf("|--------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                System.out.printf("** You do not want to give out who you are right away.\r\n" + 
                		"** You can lie about your role, to keep people guessing.\r\n" + 
                		"** Don't be entirely suggestive in your chat. \n");
                System.out.printf("|--------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
                break;
            case 5:
                System.out.printf("Starting game.... \n\n");
                run = false;
                break;
            case 6:
                System.out.println("Exiting...");
                System.out.println("Goodbye...");
                run = false; //changed to end loop
                System.exit(0);
                break;
            default:
                System.out.printf("Invalid Input! Try again!\n");
                break;
            } // end switch
        } while (run == true);
    }
}