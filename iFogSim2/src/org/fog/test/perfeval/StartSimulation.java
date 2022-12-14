package org.fog.test.perfeval;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains Methods for creating an iFogSim scenario
 * methods from https://github.com/Cloudslab/cloudsim/blob/master/modules/cloudsim-examples/src/main/java/org/cloudbus/cloudsim/examples/CloudSimExample7.java
 * Example can be found under VmTest.java
 */
public class StartSimulation {
    private int vmid;
    private int pesNumber;

    public int getVmid() {
        return vmid;
    }

    public void setVmid(int vmid) {
        this.vmid = vmid;
    }

    public int getPesNumber() {
        return pesNumber;
    }

    public void setPesNumber(int pesNumber) {
        this.pesNumber = pesNumber;
    }

    /**
     * Creates a Host with the following parameters
     * @param mips MIPS value of the Host
     * @param hostId The Host's Id
     * @param ram The amount of ram assigned to the Host
     * @param storage The amount of storage assigned to the Host
     * @param bw The amount of bandwidth assigned to the Host
     */
    public List<Host> createHost(int mips, int hostId, int ram, long storage, int bw){
        List<Host> hostList = new ArrayList<Host>();

        List<Pe> peList = new ArrayList<Pe>();

        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerTimeShared(peList)
                )
        );

        return hostList;
    }

    /**
     * Creates a Datacenter with the following parameters:
     * @param name Name of the Datacenter
     * @param mips MIPS value of the Datacenter
     * @param hostId Id of the Host
     * @param ram host memory (MB)
     * @param storage host storage
     * @param bw datacenter bandwidth
     * @param arch system architecture
     * @param os operating system
     * @param vmm virtualization hypervisor type
     * @param time_zone time zone this resource located
     * @param cost the cost of using processing in this resource
     * @param costPerMem  the cost of using memory in this resource
     * @param costPerStorage the cost of using storage in this resource
     * @param costPerBw  the cost of using bw in this resource
     * @return The created Datacenter
     */
    public Datacenter createDatacenter(String name, int mips, int hostId, int ram, long storage, int bw, String arch,
                                               String os, String vmm , double time_zone, double cost, double costPerMem, double costPerStorage, double costPerBw) {

        List<Host> hostList = new ArrayList<Host>();
        List<Pe> peList = new ArrayList<Pe>();

        peList.add(new Pe(0, new PeProvisionerSimple(mips)));

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerTimeShared(peList)
                )
        );
        LinkedList<Storage> storageList = new LinkedList<Storage>();

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    /**
     * Creates a Vm with the following parameters:
     * @param userId the Id
     * @param vms the number of Vms to be created
     * @param idShift shifts the id by set amount
     * @param size image size (MB)
     * @param ram vm memory (MB)
     * @param mips MIPS value of the Vm
     * @param bw bandwidth value of the Vm
     * @param pesNumber number of cpus
     * @param vmm VMM name
     * @return list to be passed to the broker later
     */
    public List<Vm> createVM(int userId, int vms, int idShift, long size, int ram, int mips, long bw, int pesNumber, String vmm) {
        LinkedList<Vm> list = new LinkedList<Vm>();
        Vm[] vm = new Vm[vms];

        for(int i=0;i<vms;i++){
            vm[i] = new Vm(idShift + i, userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            list.add(vm[i]);
        }

        return list;
    }

    /**
     * Creates a broker
     * @return the created Broker
     */
    public DatacenterBroker createBroker(String name) {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    /**
     * Creates a Cloudlet
     * @param id Cloudlet id
     * @param length The size of the Cloudlet
     * @param fileSize The input file size of the Cloudlet
     * @param outputSize The output file size of the Cloudlet after execution (unit: in byte)
     * @return the created Cloudlet
     */
    public Cloudlet createCloudlet(int id, long length, long fileSize, long outputSize){
        DatacenterBroker broker = createBroker("Broker");
        int brokerId = broker.getId();

        UtilizationModel utilizationModel = new UtilizationModelFull();

        Cloudlet cloudlet =
                new Cloudlet(id, length, this.getPesNumber(), fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        cloudlet.setUserId(brokerId);
        cloudlet.setVmId(this.getVmid());

        return cloudlet;
    }

    /**
     * Creates a file to the directory given in the parameter
     * @param toWrite an Object to be written into the file
     * @param argumentFilePath The Path where the file is to be written
     */
    public void fileWrite(Object toWrite, String argumentFilePath) {
        try {

            FileWriter fileWriter = new FileWriter(argumentFilePath, true);

            fileWriter.write(toWrite.toString());
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }


}
