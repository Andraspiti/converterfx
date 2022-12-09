package org.fog.test.perfeval;

import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class VmTest {
    private static List<Vm> vmlist;
    private static List<Host> hostList;
    private static List<Cloudlet> cloudletList;


    public static void main(String[] args) throws Exception {
        // VM description
        /*
        int vmid = 0;
        int pesNumber = 1; // number of cpus
        */

        StartSimulation sim = new StartSimulation();

        sim.setVmid(0);
        sim.setPesNumber(9); // number of cpus

        hostList = sim.createHost(1000, 0, 2048,1000000, 10000 );

        vmlist = sim.createVM(2, 5, 0, 10000, 512, 250, 1000, 1, "Xen"); //creating 5 vms

        int num_user = 1; // number of cloud users
        Calendar calendar = Calendar.getInstance(); // Calendar whose fields have been initialized with the current date and time.
        boolean trace_flag = false; // trace events

        CloudSim.init(num_user, calendar, trace_flag);


        Datacenter datacenter0 = sim.createDatacenter("Datacenter_0", 1000, 0, 2048, 1000000,
                10000, "x86", "Linux", "Xen",  10.0, 3.0, 0.05, 0.001, 0.0 );

        cloudletList = new ArrayList<Cloudlet>();

        sim.createCloudlet(0, 400000, 300, 300);


    }

}


