package org.fog.test.perfeval;


/**
 * Aspect for getting the parameters of the Datacenter's name, mips, hostId, ram, storage, bw, arch, os, vmm, time_zone, cost, costPerMem, costPerStorage, costPerBw
 * The parameters are written to a txt under output/Datacenter
 */
public aspect DatacenterAspect {
    private pointcut hostPointCut(): execution(org.cloudbus.cloudsim.DatacenterCharacteristics.new(..))
            || execution(org.cloudbus.cloudsim.Datacenter.new(..))
            || execution(org.cloudbus.cloudsim.VmAllocationPolicy.new(..));

    private final String argumentFilePath = "output/Datacenter/arguments" + System.currentTimeMillis() + ".txt";
    private final StartSimulation sim = new StartSimulation();

    after(): hostPointCut(){
        Object[] args = thisJoinPoint.getArgs();

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite( arg.toString() + "\n", argumentFilePath);
            }
        }
    }

}
