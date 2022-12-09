package org.fog.test.perfeval;

import java.io.FileWriter;
import java.io.IOException;

public aspect HostAspect {
/*
    private pointcut hostPointCut(): execution (* org.cloudbus.cloudsim.Host.*(..))
            || execution(* org.cloudbus.cloudsim.provisioners.RamProvisioner.*(..))
            || execution(* org.cloudbus.cloudsim.provisioners.BwProvisioner.*(..))
           || execution(* org.cloudbus.cloudsim.provisioners.PeProvisioner.*(..));

    private pointcut hostPointCut(): execution (org.cloudbus.cloudsim.Host.new(..));
/*
            || execution(org.cloudbus.cloudsim.provisioners.RamProvisioner.new(..))
            || execution(org.cloudbus.cloudsim.provisioners.BwProvisioner.new(..))
            || execution(org.cloudbus.cloudsim.provisioners.PeProvisioner.new(..));

*/

    private pointcut hostPointCut():
            execution (* org.cloudbus.cloudsim.Host.setId(..))
            || execution (* org.cloudbus.cloudsim.Host.setStorage(..))

            || execution(* org.cloudbus.cloudsim.provisioners.RamProvisioner.setRam(..))
            || execution(* org.cloudbus.cloudsim.provisioners.BwProvisioner.setAvailableBw(..))
            || execution(* org.cloudbus.cloudsim.provisioners.PeProvisioner.setMips(..));

    private final String argumentFilePath = "output/Host/arguments" + System.currentTimeMillis() + ".txt";
    private final StartSimulation sim = new StartSimulation();


    after(): hostPointCut(){
        Object[] args = thisJoinPoint.getArgs();
        //System.out.print(thisJoinPoint.getSignature().getName() + ": ");

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite(thisJoinPoint.getSignature().getName() + ": " + arg.toString() + "\n" , argumentFilePath);

            }
        }
    }

}
