package org.fog.test.perfeval;

import java.io.FileWriter;
import java.io.IOException;

public aspect CloudletAspect {
/*
    private pointcut cloudletPointCut(): execution (* org.cloudbus.cloudsim.Cloudlet.*(..))
            || execution (* org.cloudbus.cloudsim.UtilizationModel.getUtilization.*(..));
*/

    private pointcut cloudletPointCut(): execution (org.cloudbus.cloudsim.Cloudlet.new(..));

    private final String argumentFilePath = "output/Cloudlet/arguments" + System.currentTimeMillis() + ".txt";
    private final StartSimulation sim = new StartSimulation();


    after(): cloudletPointCut(){

        Object[] args = thisJoinPoint.getArgs();
        //System.out.print(thisJoinPoint.getSignature().getName() + ": ");

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite(arg.toString() + "\n", argumentFilePath);
            }
        }
    }

}
