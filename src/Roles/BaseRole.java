package Roles;

public abstract class BaseRole {
    private final String roleName;
    private final String actionName;
    private final String actionDesc;
    private int health;
    private boolean dead;
    private int count;
    
// -------------------------------------------------------------------------- //
    
    //constructor has a string for role name and action name
    public BaseRole(String rN, String act, String acD)
    {
	roleName = rN;
        actionName = act;
        actionDesc = acD;
        health = 100;
        dead = false;
        count = 0;
    }

// -------------------------------------------------------------------------- //
    public abstract String action(BaseRole r);
    
    public void takeDamage(int d)
    {
    	health -= d;
        if (health <= 0)
            dead = true;
    }
    
    public void heal(int h)
    {
        health += h;
        if (health > 100)
            health = 100;
    }
    
    public void kill()
    {
        dead = true;
        health = 0;
    }
    
    public void revive()
    {
        dead = false; 
        health = 100;
    }
    
// -------------------------------------------------------------------------- //
    
    public String getRoleName()
    {
	return roleName;
    }
    
    public String getActionName()
    {
        return actionName;
    }
    
    public String getActionDescription()
    {
        return actionDesc;
    }

    public int getHealth() 
    {
	return health;
    }
    public int getCount()
    {
        return count;
    }
    public boolean isDead()
    {
        return dead;
    }
    
    public void setHealth(int h)
    {
        health = h;
    }
    
    public void updateCount()
    {
        count++;
    }
    
    @Override
    public String toString()
    {
        String str = "- Role: " + roleName;
        str += "\n- Health: " + health;
        return str;
    }
    
}

