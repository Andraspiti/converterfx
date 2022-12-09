package org.fog.test.perfeval;

public aspect DatacenterAspect {
    private pointcut hostPointCut(): execution(org.cloudbus.cloudsim.DatacenterCharacteristics.new(..))
            || execution(org.cloudbus.cloudsim.Datacenter.new(..))
            || execution(org.cloudbus.cloudsim.VmAllocationPolicy.new(..))
            ;

    private final String argumentFilePath = "output/Datacenter/arguments" + System.currentTimeMillis() + ".txt";
    private final StartSimulation sim = new StartSimulation();


    after(): hostPointCut(){
        Object[] args = thisJoinPoint.getArgs();
        //System.out.print(thisJoinPoint.getSignature().getName() + ": ");

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite( arg.toString() + "\n", argumentFilePath);
            }
        }
    }

}
