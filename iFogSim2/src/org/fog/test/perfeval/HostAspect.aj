package org.fog.test.perfeval;

/**
 * Aspect for getting the parameters of the Host's id, storage, ram, bw, and MIPS value
 * The parameters are written to a txt under output/Host
 */
public aspect HostAspect {

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

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite(thisJoinPoint.getSignature().getName() + ": " + arg.toString() + "\n" , argumentFilePath);

            }
        }
    }

}
