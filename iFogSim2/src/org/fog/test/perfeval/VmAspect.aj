package org.fog.test.perfeval;

import java.io.*;


public aspect VmAspect {

    //private pointcut vmPointCut(): execution (org.cloudbus.cloudsim.Vm.new(..) );
    private pointcut vmPointCut(): execution (* org.cloudbus.cloudsim.Vm.setId(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setUserId(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setMips(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setNumberOfPes(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setRam(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setBw(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setSize(..))
                               ||  execution (* org.cloudbus.cloudsim.Vm.setVmm(..))

     ;

    private final StartSimulation sim = new StartSimulation();
    private final String argumentFilePath = "output/Vm/arguments" + System.currentTimeMillis()  + ".txt";


    after(): vmPointCut(){
        Object[] args = thisJoinPoint.getArgs();

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite( thisJoinPoint.getSignature().getName() + ":" + arg.toString() + "\n", argumentFilePath);
            }
        }
    }


    /*
        after():executionJoinPoints(){
            System.out.println("Aspect is working: ");
            System.out.println(thisJoinPoint.getSignature().getName());

            Object[] args = thisJoinPoint.getArgs();
            for (Object arg : args) {
                System.out.println(arg);
            }
        }
     */
//private pointcut vmPointCut(): execution (* org.cloudbus.cloudsim.Vm.*(..));
}