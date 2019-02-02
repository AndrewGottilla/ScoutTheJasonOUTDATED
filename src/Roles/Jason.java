package Roles;

public class Jason extends BaseRole {
	
    public Jason()
    {
        super("Jason", "Murder", "Kill another Player");
    }

    @Override
    public String action(BaseRole r)
    {
        if (r == this)
        {
            super.updateCount();//update count to kill on second round
            return "Congrats. You tried to hang yourself.";
        }
        else if (r.isDead())
        {
            super.updateCount();//update count to kill on second round
            return "Sheesh. Overkill. They're dead already.";
        }
        else if(super.getCount() == 0)//Jason can't kill on first round
        {
            super.updateCount();//update count to kill on second round
            return "SorRy cAn't kIlL on FiRst ROund: ";
        }
        else
        {
            r.kill();
            return "The person you targeted has been killed";
        }
    }
        
}