package Roles;

public class Warrior extends BaseRole {
	
    public Warrior()
    {
        super("Warrior", "Strike", "Strike another Player for 40 damage");
    }

    @Override
    public String action(BaseRole r)
    {
        if (r == this)
        {
            return "You stopped before you hit yourself.";
        }
        else if (r.isDead())
        {
            return "You beat on a dead body.";
        }
        else
        {
            r.takeDamage(40);
            return "The person you targeted took 40 points of damage"; 
        }
    }
	
}