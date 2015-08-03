/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.app.standingovation;
import sim.engine.*;
//import sim.app.tutorial3.Tutorial3;
import sim.display.*;
import sim.portrayal.grid.*;

import java.awt.*;

import javax.swing.*;

public class StandingOvationWithUI extends GUIState
    {
    //private static final long serialVersionUID = 1;

    public Display2D display;
    public JFrame displayFrame;

    SparseGridPortrayal2D spectatorsPortrayal = new SparseGridPortrayal2D();
    FastValueGridPortrayal2D specsPortrayal = new FastValueGridPortrayal2D("Spectator");

    public static void main(String[] args)
        {
        new StandingOvationWithUI().createController();
        }
    
    public StandingOvationWithUI() { super(new StandingOvation(System.currentTimeMillis())); }
    
    public StandingOvationWithUI(SimState state) { super(state); }
    
    public static String getName() { return "StandingOvation: Spectators"; }
    
    
    public void quit()
        {
        super.quit();
        
        if (displayFrame!=null) displayFrame.dispose();
        displayFrame = null;  // let gc
        display = null;       // let gc
        }

    public void start()
        {
        super.start();
        // set up our portrayals
        setupPortrayals();
        }
    
    public void load(SimState state)
        {
        super.load(state);
        // we now have new grids.  Set up the portrayals to reflect that
        setupPortrayals();
        }
        
    // This is called by start() and by load() because they both had this code
    // so I didn't have to type it twice :-)
    public void setupPortrayals()
        {
        // tell the portrayals what to
        // portray and how to portray them
        specsPortrayal.setField(
                ((StandingOvation)state).specs);
        
            specsPortrayal.setMap(
                new sim.util.gui.SimpleColorMap(
                    0.0,1.0,Color.white,Color.red));
                      
        // reschedule the displayer
        display.reset();
                
        // redraw the display
        display.repaint();
        }
    
    public void init(Controller c)
        {
        super.init(c);
        
        // Make the Display2D.  We'll have it display stuff later.
        display = new Display2D(400,400,this); // at 400x400, we've got 4x4 per array position
        displayFrame = display.createFrame();
        c.registerFrame(displayFrame);   // register the frame so it appears in the "Display" list
        displayFrame.setVisible(true);

        // specify the backdrop color  -- what gets painted behind the displays
        display.setBackdrop(Color.black);

        // attach the portrayals
        display.attach(specsPortrayal,"Specs");
        display.attach(spectatorsPortrayal,"Spectators");
        }
    
    public Object getSimulationInspectedObject(){return state; }
    
    }
    
    
    
    
    
