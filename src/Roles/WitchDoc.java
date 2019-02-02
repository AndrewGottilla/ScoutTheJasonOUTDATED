package Roles;

public class WitchDoc extends BaseRole {
	
    public WitchDoc()
    {
        super("Witch Doctor", "Bewitch", "Kill/Revive any Player, unless they are Jason");
    }

    @Override
    public String action(BaseRole r)
    {
        if (r == this)
        {
            return "You are immune to your own magic!";
        }
        else if (super.getCount() == 0)
        {
            super.updateCount(); //update count, cleric can only self-heal once
            if (actionW(r))
            {
                return "Successfully Killed/Revived the " + r.getRoleName() + "!";
            }
            else
            {
                return "You targeted Jason!";
            }
        }
        else
        {
            return "You already used your action!";
        }
    }

    public boolean actionW(BaseRole r)
    {
        if (r.getRoleName().equals("Jason"))
        {
            return false;
        }
        else
        {
            if (r.isDead())
                r.revive();
            else
                r.kill();
            return true;
        }
    }
	
}