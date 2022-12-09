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

    public List<Host> createHost(int mips, int hostId, int ram, long storage, int bw){
        List<Host> hostList = new ArrayList<Host>();

        List<Pe> peList = new ArrayList<Pe>();

        //int mips = 1000;

        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        /*
        int hostId = 0;
        int ram = 2048; // host memory (MB)
        long storage = 1000000; // host storage
        int bw = 10000;
*/
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

    public Datacenter createDatacenter(String name, int mips, int hostId, int ram, long storage, int bw, String arch,
                                               String os, String vmm , double time_zone, double cost, double costPerMem, double costPerStorage, double costPerBw) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        // our machine
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        //int mips = 1000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        // 4. Create Host with its id and list of PEs and add them to the list
        // of machines
        //int hostId = 0;
        //int ram = 2048; // host memory (MB)
        //long storage = 1000000; // host storage
        //int bw = 10000;

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerTimeShared(peList)
                )
        ); // This is our machine

        // 5. Create a DatacenterCharacteristics object that stores the
        // properties of a data center: architecture, OS, list of
        // Machines, allocation policy: time- or space-shared, time zone
        // and its price (G$/Pe time unit).
        //String arch = "x86"; // system architecture
        //String os = "Linux"; // operating system
        //String vmm = "Xen";
        //double time_zone = 10.0; // time zone this resource located
        //double cost = 3.0; // the cost of using processing in this resource
        //double costPerMem = 0.05; // the cost of using memory in this resource
        //double costPerStorage = 0.001; // the cost of using storage in this
        // resource
        //double costPerBw = 0.0; // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are not adding SAN
        // devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem,
                costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    //by default: a userId, vms, idShift , vmlist is a List<Vm>:  vmlist = createVM(2, 5, 0); creating 5 vms
    public List<Vm> createVM(int userId, int vms, int idShift, long size, int ram, int mips, long bw, int pesNumber, String vmm) {
        //Creates a container to store VMs. This list is passed to the broker later
        LinkedList<Vm> list = new LinkedList<Vm>();
/*
        //VM Parameters
        long size = 10000; //image size (MB)
        int ram = 512; //vm memory (MB)
        int mips = 250;
        long bw = 1000;
        int pesNumber = 1; //number of cpus
        String vmm = "Xen"; //VMM name

       // create VM
        Vm vm = new Vm(vmid, 2, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());

*/
        //create VMs
        Vm[] vm = new Vm[vms];

        for(int i=0;i<vms;i++){
            vm[i] = new Vm(idShift + i, userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            list.add(vm[i]);
        }

        return list;
    }

    public DatacenterBroker createBroker() {
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    public Cloudlet createCloudlet(int id, long length, long fileSize, long outputSize){
        // Third step: Create Broker
        DatacenterBroker broker = createBroker();
        int brokerId = broker.getId();


        /* Cloudlet properties
        int id = 0;
        long length = 400000;
        long fileSize = 300;
        long outputSize = 300;
*/
        UtilizationModel utilizationModel = new UtilizationModelFull();

        Cloudlet cloudlet =
                new Cloudlet(id, length, this.getPesNumber(), fileSize,
                        outputSize, utilizationModel, utilizationModel,
                        utilizationModel);
        cloudlet.setUserId(brokerId);
        cloudlet.setVmId(this.getVmid());

        return cloudlet;
    }

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
