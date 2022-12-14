import hu.mta.sztaki.lpds.cloud.simulator.Timed;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.resourcemodel.ConsumptionEventAdapter;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.resourcemodel.ResourceConsumption;
import hu.mta.sztaki.lpds.cloud.simulator.io.StorageObject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Templates and functions helped to create the scenarios
 */
public class ClassTemplates {
    /**
     * Template for an iFogSim scenario, the scenario must be runnable under the package org.fog.test.perfeval located under iFogSim
     * Methods used are found under StartSimulation.java located in the org.fog.test.perfeval package
     */
    public String ifogSimFirstHalf = "package org.fog.test.perfeval;\n" +
            "import org.cloudbus.cloudsim.*;\n" +
            "import org.cloudbus.cloudsim.core.CloudSim;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.Calendar;\n" +
            "import java.util.List;\n" +
            "\n" +
            "\n" +
            "public class VmTestGenerated {\n" +
            "    private static List<Vm> vmlist;\n" +
            "    private static List<Host> hostList;\n" +
            "    private static List<Cloudlet> cloudletList;\n" +
            " public static void main(String[] args) throws Exception {\n" +
            "\n" +
            "        StartSimulation sim = new StartSimulation();\n" +
            "\n" +
            "        sim.setVmid(0);\n" +
            "        sim.setPesNumber(1); \n" +
            "\n";
    /**
     * Template for the generated Dissect file VmTaskGenerated.java
     * Contains the imports needed to run the generated file
     */
    public String dissectFirstHalf = "package hu.u_szeged.inf.fog.simulator.application.demo;\n" +
            "\n" +
            "import java.util.EnumMap;\n" +
            "import java.util.HashMap;\n" +
            "import java.util.Map;\n" +
            "\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.Timed;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.energy.powermodelling.PowerState;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.iaas.PhysicalMachine;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.iaas.VirtualMachine;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.iaas.constraints.AlterableResourceConstraints;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.iaas.resourcemodel.ConsumptionEventAdapter;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.iaas.resourcemodel.ResourceConsumption;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.io.NetworkNode;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.io.Repository;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.io.StorageObject;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.io.VirtualAppliance;\n" +
            "import hu.mta.sztaki.lpds.cloud.simulator.util.PowerTransitionGenerator;\n" +
            "\n" +
            "public class VmTaskGenerated {\n" +
            "\tpublic static void main(String[] args) throws Exception {\n" +
            "\t\t";

    /**
     * Can be used for creating an iFogSim scenario, unused as of now
     */
    public void createVmTestGenerated(int hostMips, int hostId, int hostRam, long hostStorage, int hostBw,
                                      int userId, int vms, int idShift, long vmSize, int vmRam, int vmMips, long vmBw, int vmPesNumber, String vmm,
                                      String centerName, int centerMips, int centerHostId, int centerRam, long centerStorage, int centerBw, String centerArch,
                                      String centerOs, String centerVmm, double time_zone, double centerCost, double costPerMem, double costPerStorage, double costPerBw,
                                      int cloudletId, long cloudletLength, long cloudletFilesize, long cloudletOutputSize
    ){
        String pathToGenerated = "iFogSim2/src/org/fog/test/perfeval/VmTestGenerated.java";
        try {
            File file = new File(pathToGenerated);
            FileWriter fileWriter = new FileWriter(file);
            String code =
                    "package org.fog.test.perfeval;\n" +
                            "import org.cloudbus.cloudsim.*;\n" +
                            "import org.cloudbus.cloudsim.core.CloudSim;\n" +
                            "import java.util.ArrayList;\n" +
                            "import java.util.Calendar;\n" +
                            "import java.util.List;\n" +
                            "\n" +
                            "\n" +
                            "public class VmTestGenerated {\n" +
                            "    private static List<Vm> vmlist;\n" +
                            "    private static List<Host> hostList;\n" +
                            "    private static List<Cloudlet> cloudletList;\n" +
                            " public static void main(String[] args) throws Exception {\n" +
                            "\n" +
                            "        StartSimulation sim = new StartSimulation();\n" +
                            "\n" +
                            "        sim.setVmid(0);\n" +
                            "        sim.setPesNumber(1); \n" +
                            "\n" +
                            "\n" +
                            "        hostList = sim.createHost(" + hostMips + "," + hostId + "," + hostRam + "," + hostStorage + "," + hostBw + ");\n" +
                            "\n" +
                            "        vmlist = sim.createVM(" + userId + ","  + vms + "," + idShift + "," + vmSize + "," + vmRam + "," + vmMips + "," + vmBw + "," + vmPesNumber + ",\"" + vmm + "\"); //creating 5 vms\n" +
                            "\n" +
                            "        int num_user = 1; // number of cloud users\n" +
                            "        Calendar calendar = Calendar.getInstance();\n" +
                            "        boolean trace_flag = false;\n" +
                            "\n" +
                            "        CloudSim.init(num_user, calendar, trace_flag);\n" +
                            "\n" +
                            "\n" +
                            "        Datacenter datacenter0 = sim.createDatacenter(\"" + centerName + "\"," + centerMips + "," + centerHostId + "," + centerRam + "," + centerStorage + ",\n" +
                            centerBw + ",\"" + centerArch + "\",\"" + centerOs + "\",\"" + centerVmm + "\"," + time_zone + "," + centerCost + "," + costPerMem + "," + costPerStorage + "," + costPerBw + ");\n" +
                            "\n" +
                            "\n" +
                            "        cloudletList = new ArrayList<Cloudlet>();\n" +
                            "\n" +
                            //int cloudletId, long cloudletLength, long cloudletFilesize, long cloudletOutputSize
                            "        sim.createCloudlet(" + cloudletId + "," + cloudletLength + "," + cloudletFilesize + "," + cloudletOutputSize + ");\n" +
                            "\n" +
                            "    }" +
                            "\n" +
                            "}";

            file.createNewFile();
            fileWriter.write(code);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Template for generating the DISSECT-CF file, VmTaskGenerated with the help of @multiplePhysicalMachine and @multipleVirtualAppliance
     * Creates the file VmTaskGenerated.java
     */
    public void createDissectFile(long capacity, long bws){
        String pathToGenerated = "dissect-cf/dissect-cf-application/src/main/java/hu/u_szeged/inf/fog/simulator/application/demo/VmTaskGenerated.java";

        try{
        File file = new File(pathToGenerated);
        FileWriter fileWriter = new FileWriter(file);
        String code = dissectFirstHalf +
                "final EnumMap<PowerTransitionGenerator.PowerStateKind, Map<String, PowerState>> transitions =\n" +
                "PowerTransitionGenerator.generateTransitions(20, 296, 493, 50, 108);\n" +
                "\n" +
                "HashMap<String, Integer> latencyMap = new HashMap<String, Integer>();\n" +
                "\n" +
                "Repository repo = new Repository(" + capacity + "L," + "\"repo\", " + bws + "," + bws + "," + bws + "," + "latencyMap, \n" +
                "transitions.get(PowerTransitionGenerator.PowerStateKind.storage),transitions.get(PowerTransitionGenerator.PowerStateKind.network));\n" +
                "\n" +
                "repo.setState(NetworkNode.State.RUNNING);\n" +
                "\n" +                                  //PhysicalMachine(double cores, double perCorePocessing, long memory, Repository disk, int onD, int offD,
                multiplePhysicalMachine(repairHostArguments()) +
                "\n" +
                "Timed.simulateUntilLastEvent();\n" +
                "\n" +
                //public VirtualAppliance(final String id, final double startupProcess, final long nl, boolean vary, final long reqDisk)
                multipleVirtualAppliance() + "\n" +
                "\n" +
                "Timed.simulateUntilLastEvent();\n" +
                "    }\n" +
                "}";

            fileWriter.write(code);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list with the repaired Host arguments
     * The repair is needed as the setStorage data for the first instantiated Host is always located at the end of the created Hosts
     */
    public ArrayList<String> repairHostArguments() throws IOException {
        ArrayList<String> hostArray = FileHandler.mostRecentInArray("output/Host");
        int numberOfHosts = FileHandler.timesCreated("output/Host") * 5;

        hostArray.add(0, hostArray.get(numberOfHosts - 1));

        return hostArray;
    }

    // processing in dissect is 1 MIPSMS / number of CPU cores
    // 1 MIPMS = 1 MIPS/60

    /**
     * Converts MIPS value to processing used in DISSECT-CF
     * @param mips the MIPS number
     * @param pesNumber Number of cpu cores
     * @return the processing value
     */
    public double mipsToTick(int mips, int pesNumber){
        return Math.round(mips / 60.0 / pesNumber);
    }

    /**
     * Creates a String to be used for creating the VmTaskGenerated.java
     *
     * @return a String to be concatenated into the String in  @createDissectFile
     */
    public String multipleVirtualAppliance() throws IOException {
        String result = "";
        String vaName = "va";
        int numberOfVms = FileHandler.timesCreated("output/Vm");
        ArrayList<String> vmArguments = FileHandler.mostRecentInArray("output/Vm");

        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> userId = new ArrayList<>();
        ArrayList<String> mips = new ArrayList<>();
        ArrayList<String> pesNumber = new ArrayList<>();
        ArrayList<String> ram = new ArrayList<>();
        ArrayList<String> bw = new ArrayList<>();
        ArrayList<String> size = new ArrayList<>();
        ArrayList<String> vmm = new ArrayList<>();

        for (int i = 0; i<numberOfVms * 8; i++){
            if (vmArguments.get(i).split(":", 2)[0].equals("setId")){
                id.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setUserId")){
                userId.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setMips")){
                mips.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setNumberOfPes")){
                pesNumber.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setRam")){
                ram.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setBw")){
                bw.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setSize")){
                size.add(vmArguments.get(i).split(":", 2)[1]);
            }
            if (vmArguments.get(i).split(":", 2)[0].equals("setVmm")){
                vmm.add(vmArguments.get(i).split(":", 2)[1]);
            }
        }

        for (int i = 0; i<numberOfVms; i++){
            String mipsString = mips.get(i);
            int mipsNumber = Integer.parseInt(mipsString.split("\\.", 2)[0]);

            result += "\nAlterableResourceConstraints arc" + i + " = new AlterableResourceConstraints("
                    + pesNumber.get(i) + "," + mipsToTick(mipsNumber, Integer.parseInt(pesNumber.get(i))) + "," + ram.get(i) + ");\n" +
                      "VirtualAppliance va" + i + " = new VirtualAppliance( "
                    + "\"" + id.get(i) + "\","  + "0" + "," + bw.get(i) + "L," + "false" + "," + size.get(i) + ");\n" +
                    "\nrepo.registerObject(" + "va" + i +");";
        }
        result += "\nTimed.simulateUntilLastEvent();\n";

        for (int i = 0; i<FileHandler.timesCreated("output/Host"); i++) {

            result += "VirtualMachine vm" + i + " = pm" + i + ".requestVM(va" + i + ", arc" + i +  ", repo," + 1 + ")[0];\n";
        }
        result += "Timed.simulateUntilLastEvent();\n";

        for (int i = 0; i<FileHandler.timesCreated("output/Host"); i++) {
            result += "System.out.println(\"Time: \"+Timed.getFireCount()+ \" PM state: \" + pm" + i +".getState()+ \" VM state: \"+vm" + i + ".getState());\n" +
            "vm" + i + ".newComputeTask(100000, ResourceConsumption.unlimitedProcessing, new ConsumptionEventAdapter() {\n" +
                    "            @Override\n" +
                    "            public void conComplete() {\n" +
                    "                System.out.println(\"Time: \"+Timed.getFireCount());\n" +
                    "                StorageObject storageObj = new StorageObject(\"1\",20000, false);\n" +
                    "                repo.registerObject(storageObj);\n" +
                    "            }\n" +
                    "        });\n"
            ;
        }
        return result;
    }

    /**
     * returns a string containing the number of physical machines according to the number of Hosts instantiated in iFogSim
     * @param hostArguments an ArrayList containing the Host arguments
     * @return String to be concatenated into the String in @createDissectFile
     */

    public String multiplePhysicalMachine(ArrayList<String> hostArguments) throws IOException {
        String result = "";
        String pmName = "pm";
        int numberOfHosts = FileHandler.timesCreated("output/Host");
        ArrayList<String> storage = new ArrayList<>();
        ArrayList<String> mips = new ArrayList<>();
        ArrayList<String> ram = new ArrayList<>();
        ArrayList<String> bwTemp = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> bw = new ArrayList<>();
        int pesNumber =  Integer.parseInt(FileHandler.mostRecentInArray("output/Cloudlet").get(2));


        for (int i = 0; i<numberOfHosts * 5; i++){
            if (hostArguments.get(i).split(":", 2)[0].equals("setStorage")){
                storage.add(hostArguments.get(i).split(":", 2)[1]);
            }
            if (hostArguments.get(i).split(":", 2)[0].equals("setMips")){
                mips.add(hostArguments.get(i).split(":", 2)[1]);
            }
            if (hostArguments.get(i).split(":", 2)[0].equals("setRam")){
                ram.add(hostArguments.get(i).split(":", 2)[1]);
            }
            if (hostArguments.get(i).split(":", 2)[0].equals("setAvailableBw")){
                bwTemp.add(hostArguments.get(i).split(":", 2)[1]);
            }
            if (hostArguments.get(i).split(":", 2)[0].equals("setId")){
                id.add(hostArguments.get(i).split(":", 2)[1]);
            }
        }

        if (bwTemp.size() == storage.size() && bwTemp.size() == mips.size()){
            bw = bwTemp;
        }  else{
            for (int i = 0; i<bwTemp.size(); i++){
                if (i % 2 == 0){
                    bw.add(bwTemp.get(i));
                }
            }
        }

        for (int i=0; i<numberOfHosts; i++){
            String mipsString = mips.get(i).strip();
            int mipsNumber = Integer.parseInt(mipsString.split("\\.", 2)[0]);

            result += "PhysicalMachine " + pmName + i + " = new PhysicalMachine(" + pesNumber + "," +
                    mipsToTick(mipsNumber, pesNumber) + ","  + storage.get(i) + "L," + "repo," +
                    bw.get(i) + "," + bw.get(i) + "," + "transitions.get(PowerTransitionGenerator.PowerStateKind.host));\n" +
                    pmName + i + ".turnon();\n";

        }
        return result;
    }


}
