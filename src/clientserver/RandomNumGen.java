package clientserver;

import java.util.Random;

public class RandomNumGen {
    private Random randomObj = new Random();
    private int player[] = new int[5]; 

    public RandomNumGen()
    {
        // Randomly generate a number 1 to 5
        for(int i = 0; i < player.length; i++) 
        {
            player[i] = randomObj.nextInt(5) + 1;
        }

        //Check for duplicates, leave index 0 
        //check index 1 == to other index, generate new random 1 - 5 
        while(player[1] == player[0] || player[1] == player[2] || player[1] == player[3] || player[1] == player[4])
        {
            player[1] = randomObj.nextInt(5) + 1;
        }	

        //check index 2 == to other index, generate new random 1 - 5 
        while(player[2] == player[0] || player[2] == player[1] || player[2] == player[3] || player[2] == player[4])
        {
            player[2] = randomObj.nextInt(5) + 1;
        }

        //check index 3 == to other index, generate new random 1 - 5 
        while(player[3] == player[0] || player[3] == player[1] || player[3] == player[2] || player[3] == player[4])
        {
            player[3] = randomObj.nextInt(5) + 1;
        }
        
        //check index 4 == to other index, generate new random 1 - 5 
        while(player[4] == player[0] || player[4] == player[1] || player[4] == player[2] || player[4] == player[3])
        {
            player[4] = randomObj.nextInt(5) + 1;
        }
    }

    public int getRandomNum(int i)
    {
        if(i > 4 || i < 0) 
        {
            System.out.println("Error: Random num can not be:" + i + " must be between index 0 and 5");	
            return 0;
        }
        else
            return player[i];
    }
}
