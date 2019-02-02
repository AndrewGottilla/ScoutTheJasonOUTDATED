package Roles;

public class Cleric extends BaseRole {
	
    public Cleric()
    {
        super("Cleric", "Remedy", "Heal 50 points of health");
    }

    @Override
    public String action(BaseRole r)
    {
        if(r == this)
        {
            if(super.getCount() == 0)
            {
                super.updateCount(); //update count, cleric can only self-heal once
                r.heal(50);
                return "You healed yourself for 50 points, but can't heal yourself again";                
            }
            else
                return "You healed youself once already, can't heal again!";
        }
        else if (r.isDead())
        {
            return "You cannot heal a dead person.";
        }
        else
        {
            r.heal(50);
        }
	return "Your target was healed for 50 points" ;
    }
        
}