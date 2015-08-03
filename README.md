#Standing Ovation Problem

The standing ovation problem is a simple model introduced by (Miller & Page, 2004) that provides a rich domain from which to explore the world. The problem is basically whether to join to the applauding audience or not.

Tricky part is that the decision of an individual does not only depend on his impressions of the performance, but also on the audience surrounding him. If everyone around a person give standing ovation, it is very likely that so does the person, even if he does not enjoy the performance at all. This phenomenon is also named as descriptive norm. It is a behavioral rule that individuals follow when their empirical expectations of others following the same rule are met (Muldoon, Lisciandra, Bicchieri, Hartmann, & Sprenger, 2014). In this study, we model the standing ovation problem using agent-based modeling technique and MASON framework to explore the structure of the problem and observe the emergent patterns and behavior.

Download the [jar file](SOP.jar) and play with it ! (Bonus, I throw other very nice examples in the MASON library :-) ) //right click and open if your OS complains about security issues

**Single rule**: Follow the majority in the neighborhood, if the majority is standing so do I, if they sit I follow them !

In the model pane, you will find some stats of interest and also the free parameters:
* standing rate of the audience (which you can track and save),
* rate of the confused people (act exactly the opposite of the rule),
* rate of consistent audience (don’t care social pressure),
* agents’ vision (distance 1 is Moore neighborhood without seeing the back row), and finally
* the initial standing probability of the agents.

**Notes**:

1. Free parameters are added to prevent locks and to let modelers see their effects on the SOP.
2. In the `Model` pane, click on the key symbol next to `StandingRate` and select `Chart` to see the change in the standing rate in a chart.  