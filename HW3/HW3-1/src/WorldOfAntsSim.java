/**
 * WorldOfAntsSim.java
 *
 * This program simulates a 2D world with predators and prey.
 * The predators (anteaters) and prey (ants) inherit from the
 * Organism class that keeps track of basic information about each
 * critter (time ticks since last bred, position in the world).
 *
 * The 2D world is implemented as a separate class, World,
 * that contains a 2D array of pointers to type Organism.

 */

import java.util.Scanner;

/**
* The World class stores data about the world by creating a
* WORLDSIZE by WORLDSIZE array of type Organism.
* null indicates an empty spot, otherwise a valid object
* indicates an ant or anteater.  To determine which,
* invoke the virtual function getType of Organism that should return
* ANT if the class is of type ant, and ANTEATER otherwise.
*/
class World
{
	public static final int WORLDSIZE = 20;
	private Organism[][] grid;		// Array of organisms for each cell

   	/**
	* Default constructor;  Initialize the world to NULL's.
 	*/
	public World()
	{
		// Initialize world to empty spaces
		int i,j;

		grid = new Organism[WORLDSIZE][WORLDSIZE];
		for (i=0; i<WORLDSIZE; i++)
		{
			for (j=0; j<WORLDSIZE; j++)
			{
				grid[i][j]=null;
			}
		}
	}

	/**
	* getAt
	* Returns the entry stored in the grid array at x,y.
	* @param x X coordinate of the cell to retrieve
	* @param y Y coordinate of the cell to retrieve
	*/
	public Organism getAt(int x, int y)
	{
		if ((x>=0) && (x<World.WORLDSIZE) && (y>=0) && (y<World.WORLDSIZE))
			return grid[x][y];
		return null;
	}

	/**
	* setAt
	* Sets the entry in the grid array at x,y to the input organism
	* @param x X coordinate of the cell to store
	* @param y Y coordinate of the cell to store
	* @param org Organism object to store in the grid
	*/
	public void setAt(int x, int y, Organism org)
	{
		if ((x>=0) && (x<World.WORLDSIZE) && (y>=0) && (y<World.WORLDSIZE))
		{
			grid[x][y] = org;
		}
	}

	/**
	* Display
	* Displays the world in ASCII.text
	*/
	public void Display()
	{
		int i,j;
		System.out.println();
		System.out.println();
		for (j=0; j<World.WORLDSIZE; j++)
		{
			for (i=0; i<World.WORLDSIZE; i++)
			{
				if (grid[i][j]==null)
					System.out.print(".");
				else
					System.out.print(grid[i][j].getPrintableChar());  
					// X for Anteater, o for Ant
			}
			System.out.println();
		}
	}

	/**
	* SimulateOneStep
	* This is the main routine that simulates one turn in the world.
	* First, a flag for each organism is used to indicate if it has moved.
	* This is because we iterate through the grid starting from the top
	* looking for an organism to move . If one moves down, we don't want
	* to move it again when we reach it.
	* First move anteaters, then ants, and if they are still alive then
	* we breed them.
	*/
	public void SimulateOneStep()
	{
		int i,j;
		// First reset all organisms to not moved
		for (i=0; i<World.WORLDSIZE; i++)
			for (j=0; j<World.WORLDSIZE; j++)
			{
				if (grid[i][j]!=null) grid[i][j].setMoved(false);
			}
		// Loop through cells in order and move if it's an Anteater
		for (i=0; i<World.WORLDSIZE; i++)
			for (j=0; j<World.WORLDSIZE; j++)
			{
			 if ((grid[i][j]!=null) && (grid[i][j] instanceof Anteater))
			 {
				if (grid[i][j].getMoved() == false)
				{
				 grid[i][j].setMoved(true); // Mark as moved
				 grid[i][j].move();
				}
			 }
			}
		// Loop through cells in order and move if it's an Ant
		for (i=0; i<World.WORLDSIZE; i++)
			for (j=0; j<World.WORLDSIZE; j++)
			{
				if ((grid[i][j]!=null) && (grid[i][j] instanceof Ant))
				{
					if (grid[i][j].getMoved() == false)
					{
					 grid[i][j].setMoved(true); // Mark as moved
					 grid[i][j].move();
					}
				}
			}
		// Loop through cells in order and check if anyone is starving
		for (i=0; i<World.WORLDSIZE; i++)
			for (j=0; j<World.WORLDSIZE; j++)
			{
				// Kill off any anteaters that haven't eaten recently
				if ((grid[i][j]!=null) &&
					(grid[i][j] instanceof Anteater))
				{
					if (grid[i][j].starve())
					{
						grid[i][j] = null;
					}
				}
			}
		// Loop through cells in order and check if we should breed
		for (i=0; i<World.WORLDSIZE; i++)
			for (j=0; j<World.WORLDSIZE; j++)
			{
				// Only breed organisms that have moved, since
				// breeding places new organisms on the map we
				// don't want to try and breed those
				if ((grid[i][j]!=null) && (grid[i][j].getMoved()))
				{
					grid[i][j].breed();
				}
			}
	}
} // World


/**
* Definition for the Organism base class.
* Each organism has a reference back to
* the World object so it can move itself
* about in the world.
*/
abstract class Organism
{
	protected int x,y;			// Position in the world
	protected boolean moved;	// Bool to indicate if moved this turn
	protected int breedTicks;	// Number of ticks since breeding
	protected World world;		// Reference to the world object so we can update its
								// grid when we move around in the world
  	 /**
	* Constructors
 	*/
	public Organism()
	{
		world = null;
		moved = false;
		breedTicks = 0;
		x=0;
		y=0;
	}

	public Organism(World world, int x, int y)
	{
		this.world = world;
		moved = false;
		breedTicks = 0;
		this.x=x;
		this.y=y;
		world.setAt(x,y,this);
	}

  	 /**
	* Accessor/Mutator for the Moved variable
 	*/
	public boolean getMoved()
	{
		return moved;
	}

	public void setMoved(boolean moved)
	{
		this.moved = moved;
	}

	/**
	* Determines whether or not this organism should breed
	*/
	public abstract void breed();

	/**
	* Determines how this organism should move
	*/
	public abstract void move();

	/**
	* Determines if this organism starves
	*/
	public abstract boolean starve();

	/**
	* Returns a symbol for the organism
	*/
	public abstract String getPrintableChar();
} // Organism


/**
 * Start with the Ant class
 */
class Ant extends Organism
{
	public static final int ANTBREED = 3;	// How many ticks to breed an ant

    	/**
	* Constructors
 	*/
 	public Ant()
 	{
		super();
	}

	public Ant(World world, int x, int y)
	{
		super(world,x,y);
	}

	/**
	* Ant breed
	* Increment the tick count for breeding.
	* If it equals our threshold, then clone this ant either
	* above, right, left, or below the current one.
	*/
	public void breed()			// Must define this since virtual
	{
		breedTicks++;
		if (breedTicks == ANTBREED)
		{
			breedTicks = 0;
			// Try to make a new ant either above, left, right, or down
			if ((y>0) && (world.getAt(x,y-1)==null))
			{
				Ant newAnt = new Ant(world, x, y-1);
			}
			else if ((y<World.WORLDSIZE-1) && (world.getAt(x,y+1)==null))
			{
				Ant newAnt = new Ant(world, x, y+1);
			}
			else if ((x>0) && (world.getAt(x-1,y)==null))
			{
				Ant newAnt = new Ant(world, x-1, y);
			}
			else if ((x<World.WORLDSIZE-1) && (world.getAt(x+1,y)==null))
			{
				Ant newAnt = new Ant(world, x+1, y);
			}
		}
	}

	/**
	* Ant Move
	* Look for an empty cell up, right, left, or down and
	* try to move there.
	*/
	public void move()			// Must define this since virtual
	{
		// Pick random direction to move
		int dir = (int) (Math.random() * 4);
		// Try to move up, if not at an edge and empty spot
		if (dir==0)
		{
			if ((y>0) && (world.getAt(x,y-1)==null))
			{
			 world.setAt(x,y-1,world.getAt(x,y));  // Move to new spot
			 world.setAt(x,y,null);
			 y--;
			}
		}
		// Try to move down
		else if (dir==1)
		{
			if ((y<World.WORLDSIZE-1) && (world.getAt(x,y+1)==null))
			{
			 world.setAt(x,y+1,world.getAt(x,y));  // Move to new spot
			 world.setAt(x,y,null);  // Set current spot to empty
			 y++;
			}
		}
		// Try to move left
		else if (dir==2)
		{
			if ((x>0) && (world.getAt(x-1,y)==null))
			{
			 world.setAt(x-1,y,world.getAt(x,y));  // Move to new spot
			 world.setAt(x,y,null);  // Set current spot to empty
			 x--;
			}
		}
		// Try to move right
		else
		{
			if ((x<World.WORLDSIZE-1) && (world.getAt(x+1,y)==null))
			{
			 world.setAt(x+1,y,world.getAt(x,y));  // Move to new spot
			 world.setAt(x,y,null);  // Set current spot to empty
			 x++;
			}
		}
	}

	/**
	* Return false since an ant never starves
	*/
	public boolean starve()
	{
		  return false;
	}

	/**
	* an Ant is represented by "o"
	*/
	public String getPrintableChar()
	{
		return "o";
	}
} // Ant



/**
* Now define Anteater Class
*/
class Anteater extends Organism
{
    public static final int ANTEATER_BREED  = 8;
    public static final int ANTEATER_STARVE = 3;
    
    // Number of ticks since eating.
    private int starveTicks = 0;
    
    /**
     * Default constructor.
     */
    public Anteater()
    {
        super();
    }
    
    /**
     * 3-parameter constructor.
     * 
     * @param world The World object this Anteater lives in.
     * @param x The x coordinate of the spot in <b>world</b> this Anteater inhabits.
     * @param y The y coordinate of the spot in <b>world</b> this Anteater inhabits.
     */
    public Anteater(World world, int x, int y)
    {
        super(world, x, y);
    }
    
    // Basic code reused from Ant.breed() method with changes to create an Anteater object instead 
    // of an Ant object.
    
    /**
     * Adjusts the breed counter for the Anteater object and creates a new one when the appropriate
     * condition is met.
     */
    public void breed()
    {
        breedTicks++;
        if (breedTicks == ANTEATER_BREED)
        {
            breedTicks = 0;
            // Try to create a new Anteater object. Because world reference is passed in and
            // Anteater object adds itself to that world, Anteater reference doesn't need to be
            // explicitly saved.
            if ((y > 0) && (world.getAt(x, y - 1) == null))
            {
                new Anteater(world, x, y - 1);
            }
            else if ((y < World.WORLDSIZE - 1) && (world.getAt(x, y + 1) == null))
            {
                new Anteater(world, x, y + 1);
            }
            else if ((x > 0) && (world.getAt(x - 1, y) == null))
            {
                new Anteater(world, x - 1, y);
            }
            else if ((x < World.WORLDSIZE - 1) && (world.getAt(x + 1, y) == null))
            {
                new Anteater(world, x + 1, y);
            }
        }
    }
    
    // Basic code reused from Ant.move() method, with optimizations for move checking and additions
    // to handle starvation.
    
    /**
     * Moves an Anteater object around and handles the starvation counter.
     */
    public void move()
    {
        starveTicks++;
        int direction = (int) (Math.random() * 4);
        
        // up
        if (direction == 0)
        {
            if ((y > 0) && !(world.getAt(x, y - 1) instanceof Anteater))
            {
                // Reset starvation counter if Anteater "ate".
                if (world.getAt(x, y - 1) instanceof Ant)
                    starveTicks = 0;
                
                // Move to new spot.
                world.setAt(x, y - 1, world.getAt(x, y));
                world.setAt(x, y, null);
                y--;
            }
        }
        // down
        else if (direction == 1)
        {
            if ((y < World.WORLDSIZE - 1) && !(world.getAt(x, y + 1) instanceof Anteater))
            {
                // Reset starvation counter if Anteater "ate".
                if (world.getAt(x, y + 1) instanceof Ant)
                    starveTicks = 0;
                
                // Move to new spot.
                world.setAt(x, y + 1, world.getAt(x, y));
                world.setAt(x, y, null);
                y++;
            }
        }
        // left
        else if (direction == 2)
        {
            if ((x > 0) && !(world.getAt(x - 1, y) instanceof Anteater))
            {
                // Reset starvation counter if Anteater "ate".
                if (world.getAt(x - 1, y) instanceof Ant)
                    starveTicks = 0;
                
                // Move to new spot.
                world.setAt(x - 1, y, world.getAt(x, y));
                world.setAt(x, y, null);
                x--;
            }
        }
        // right
        else
        {
            if ((x < World.WORLDSIZE - 1) && !(world.getAt(x + 1, y) instanceof Anteater))
            {
                // Reset starvation counter if Anteater "ate".
                if (world.getAt(x + 1, y) instanceof Ant)
                    starveTicks = 0;
                
                // Move to new spot.
                world.setAt(x + 1, y, world.getAt(x, y));
                world.setAt(x, y, null);
                x++;
            }
        }
    }
    
    /**
     * Checks to see if the anteater is starving.
     * 
     * @return boolean, indicating whether the anteater is starving (true) or not (false).
     */
    public boolean starve()
    {
        return (starveTicks == ANTEATER_STARVE ? true : false);
    }
    
    /**
     * Returns "X" as the printable character for an Anteater object. 
     */
    public String getPrintableChar()
    {
        return "X";
    }
} // Anteater

public class WorldOfAntsSim
{
	public static final int INITIALANTS = 100;
	public static final int INITIALBUGS = 5;

	// ======================
	//     main method
	// ======================
	
	public static void main(String[] args)
	{
	  String s;
	  World w = new World();
	  Scanner scan = new Scanner(System.in);

	  // Randomly create 100 ants
	  int antCount = 0;
	  while (antCount < INITIALANTS)
	  {
		int x = (int) (Math.random() * World.WORLDSIZE);
		int y = (int) (Math.random() * World.WORLDSIZE);
		if (w.getAt(x,y)==null)		// Only put ant in empty spot
		{
			antCount++;
			Ant a1 = new Ant(w,x,y);
		}
	  }
	  // Randomly create 5 anteaters
	  int anteaterCount = 0;
	  while (anteaterCount < INITIALBUGS)
	  {
		int x = (int) (Math.random() * World.WORLDSIZE);
		int y = (int) (Math.random() * World.WORLDSIZE);
		if (w.getAt(x,y)==null)		// Only put anteater in empty spot
		{
			anteaterCount++;
			Anteater d1 = new Anteater(w,x,y);
		}
	  }

	  // Run simulation forever, until user cancels
	  while (true)
	  {
		w.Display();
		w.SimulateOneStep();
		System.out.println("Press enter for next step");
		s = scan.nextLine();
	  }
	}
} // WorldOfAntsSim
