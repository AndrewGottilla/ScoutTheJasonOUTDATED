package Roles;

public class Investigator extends BaseRole {
	
    public Investigator()
    {
        super("Investigator", "Scope", "Reveal role of another Player");
    }

    @Override
    public String action(BaseRole r)
    {
        if (r == this)
        {
            return "You have an existential crisis! You are the Investigator lol";
        }
        else
        {
            return "The role of the person you investigated was " + r.getRoleName();  
        }
    }
	
}