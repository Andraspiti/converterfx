package org.fog.test.perfeval;

/**
 * Aspect for getting the parameters of the Cloudlet's id, length, fileSize, outputSize
 * The parameters are written to a txt under output/Cloudlet
 */
public aspect CloudletAspect {

    private pointcut cloudletPointCut(): execution (org.cloudbus.cloudsim.Cloudlet.new(..));

    private final String argumentFilePath = "output/Cloudlet/arguments" + System.currentTimeMillis() + ".txt";
    private final StartSimulation sim = new StartSimulation();

    after(): cloudletPointCut(){
        Object[] args = thisJoinPoint.getArgs();

        for (Object arg : args) {
            if (arg == null) {
                sim.fileWrite("null\n", argumentFilePath);
            } else {
                sim.fileWrite(arg.toString() + "\n", argumentFilePath);
            }
        }
    }
}
