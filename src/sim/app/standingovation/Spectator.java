/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.app.standingovation;
import sim.engine.*;
import sim.field.grid.Grid2D;
import sim.util.*;


public class Spectator implements Steppable
    {
    private static final long serialVersionUID = 1;

    public boolean isStanding = false;

    
    public Spectator(SimState state)
        {
    	StandingOvation so = (StandingOvation)state;
    	//initial standing probability
    	isStanding = so.random.nextBoolean(so.initialStandingPref);
    	//isStanding = so.random.nextBoolean(0.30);
        }

    public void step(SimState state)
        {
        StandingOvation so = (StandingOvation)state;
        
        int standingNeighbors = 0;
        int sittingNeighbors = 0;
        
        Int2D myloc = so.spectators.getObjectLocation(this);
        Bag result = new Bag();

        result = so.spectators.getMooreNeighbors(myloc.x, myloc.y, so.visiond, Grid2D.BOUNDED, false);
		 		
		for (Object s : result) {
			//do not count for the Moore neighbors at the row behind
			if(so.spectators.getObjectLocation((Spectator)s).y <= myloc.y)
				if(((Spectator)s).isStanding)
					standingNeighbors++;
				else
					sittingNeighbors++;
		}
		
		boolean isStandingPrev = isStanding;
		
		//joining to the majority
		if(sittingNeighbors < standingNeighbors)
			isStanding = true;
		if(sittingNeighbors > standingNeighbors)
			isStanding = false;
		
		//can be confused with some small probability
		if(so.random.nextBoolean(so.confused))
			isStanding = !isStanding;

		//can ignore neighbors and stay consistent with prev decision
		if(so.random.nextBoolean(so.consistent))
			isStanding = isStandingPrev;
		
		//Converts visualized in another color
		if(isStanding == isStandingPrev)
			so.specs.field[myloc.x][myloc.y] = (isStanding == true) ? 1.0 : 0.0;
		else
			so.specs.field[myloc.x][myloc.y] = (isStanding == true) ? 0.7 : 0.3;
		
        }
    }
