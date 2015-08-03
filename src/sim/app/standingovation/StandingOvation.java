/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.app.standingovation;
import sim.engine.*;
import sim.field.grid.*;
import sim.util.*;


public class StandingOvation extends SimState
    {
    private static final long serialVersionUID = 1;

    public DoubleGrid2D specs;
    public SparseGrid2D spectators;
    
    public int gridWidth = 20;
    public int gridHeight = 20;
    public int numSpectators = 400;
    
    public double confused   = 0.01;
    public double consistent = 0.01;
    public int visiond = 1;
    public double initialStandingPref = 0.5;

    public double getStandingRate() {
    	int standing = 0;
    	int sitting = 0;
    	if(spectators == null)
    		return 0.0;
    	for (Object s : spectators.getAllObjects()){
    		if(((Spectator)s).isStanding)
				standing++;
    		else
    			sitting++;
    	}
		return (1.0*standing)/(sitting + standing);
	}
    
    public double getConfused() {
		return confused;
	}

	public void setConfused(double confused) {
		this.confused = confused;
	}

	public double getConsistent() {
		return consistent;
	}

	public void setConsistent(double consistent) {
		this.consistent = consistent;
	}

	public int getVisiond() {
		return visiond;
	}

	public void setVisiond(int visiond) {
		this.visiond = visiond;
	}

	public double getInitialStandingPref() {
		return initialStandingPref;
	}

	public void setInitialStandingPref(double initialStandingPref) {
		this.initialStandingPref = initialStandingPref;
	}

	public StandingOvation(long seed)
        {
        super(seed);
        }

    public void start()
        {
        super.start();

        specs = new DoubleGrid2D(gridWidth, gridHeight);
        spectators = new SparseGrid2D(gridWidth, gridHeight);
        
        Spectator p;
        
        for(int i=0 ; i<gridWidth ; i++)
        	for(int j=0 ; j<gridHeight ; j++){
	            p = new Spectator(this);
	            schedule.scheduleRepeating(p);
	            //schedule.scheduleRepeating(Schedule.EPOCH,2,p,1);
	            
	            spectators.setObjectLocation(p,new Int2D(i,j));	            
	            specs.field[i][j] = (p.isStanding == true) ? 1.0 : 0.0;
            }
        
        }

    public static void main(String[] args)
        {
        doLoop(StandingOvation.class, args);
        System.exit(0);
        }    
    }
