package hu.u_szeged.inf.fog.simulator.application.demo;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import hu.mta.sztaki.lpds.cloud.simulator.Timed;
import hu.mta.sztaki.lpds.cloud.simulator.energy.powermodelling.PowerState;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.PhysicalMachine;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.VirtualMachine;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.constraints.AlterableResourceConstraints;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.resourcemodel.ConsumptionEventAdapter;
import hu.mta.sztaki.lpds.cloud.simulator.iaas.resourcemodel.ResourceConsumption;
import hu.mta.sztaki.lpds.cloud.simulator.io.NetworkNode;
import hu.mta.sztaki.lpds.cloud.simulator.io.Repository;
import hu.mta.sztaki.lpds.cloud.simulator.io.StorageObject;
import hu.mta.sztaki.lpds.cloud.simulator.io.VirtualAppliance;
import hu.mta.sztaki.lpds.cloud.simulator.util.PowerTransitionGenerator;

public class VmTaskGenerated {
	public static void main(String[] args) throws Exception {
		final EnumMap<PowerTransitionGenerator.PowerStateKind, Map<String, PowerState>> transitions =
PowerTransitionGenerator.generateTransitions(20, 296, 493, 50, 108);

HashMap<String, Integer> latencyMap = new HashMap<String, Integer>();

Repository repo = new Repository(17179869184L,"repo", 1000000,1000000,1000000,latencyMap, 
transitions.get(PowerTransitionGenerator.PowerStateKind.storage),transitions.get(PowerTransitionGenerator.PowerStateKind.network));

repo.setState(NetworkNode.State.RUNNING);

PhysicalMachine pm0 = new PhysicalMachine(1,17.0, 1000000L,repo, 10000, 10000,transitions.get(PowerTransitionGenerator.PowerStateKind.host));
pm0.turnon();
PhysicalMachine pm1 = new PhysicalMachine(1,17.0, 1000000L,repo, 10000, 10000,transitions.get(PowerTransitionGenerator.PowerStateKind.host));
pm1.turnon();
PhysicalMachine pm2 = new PhysicalMachine(1,17.0, 1000000L,repo, 10000, 10000,transitions.get(PowerTransitionGenerator.PowerStateKind.host));
pm2.turnon();

Timed.simulateUntilLastEvent();


AlterableResourceConstraints arc0 = new AlterableResourceConstraints(1,4.0,512);
VirtualAppliance va0 = new VirtualAppliance( "0",0,1000L,false,10000);

repo.registerObject(va0);
AlterableResourceConstraints arc1 = new AlterableResourceConstraints(1,4.0,512);
VirtualAppliance va1 = new VirtualAppliance( "1",0,1000L,false,10000);

repo.registerObject(va1);
AlterableResourceConstraints arc2 = new AlterableResourceConstraints(1,4.0,512);
VirtualAppliance va2 = new VirtualAppliance( "2",0,1000L,false,10000);

repo.registerObject(va2);
AlterableResourceConstraints arc3 = new AlterableResourceConstraints(1,4.0,512);
VirtualAppliance va3 = new VirtualAppliance( "3",0,1000L,false,10000);

repo.registerObject(va3);
AlterableResourceConstraints arc4 = new AlterableResourceConstraints(1,4.0,512);
VirtualAppliance va4 = new VirtualAppliance( "4",0,1000L,false,10000);

repo.registerObject(va4);
Timed.simulateUntilLastEvent();
VirtualMachine vm0 = pm0.requestVM(va0, arc0, repo,1)[0];
VirtualMachine vm1 = pm1.requestVM(va1, arc1, repo,1)[0];
VirtualMachine vm2 = pm2.requestVM(va2, arc2, repo,1)[0];
Timed.simulateUntilLastEvent();
System.out.println("Time: "+Timed.getFireCount()+ " PM state: " + pm0.getState()+ " VM state: "+vm0.getState());
vm0.newComputeTask(100000, ResourceConsumption.unlimitedProcessing, new ConsumptionEventAdapter() {
            @Override
            public void conComplete() {
                System.out.println("Time: "+Timed.getFireCount());
                StorageObject storageObj = new StorageObject("1",20000, false);
                repo.registerObject(storageObj);
            }
        });
System.out.println("Time: "+Timed.getFireCount()+ " PM state: " + pm1.getState()+ " VM state: "+vm1.getState());
vm1.newComputeTask(100000, ResourceConsumption.unlimitedProcessing, new ConsumptionEventAdapter() {
            @Override
            public void conComplete() {
                System.out.println("Time: "+Timed.getFireCount());
                StorageObject storageObj = new StorageObject("1",20000, false);
                repo.registerObject(storageObj);
            }
        });
System.out.println("Time: "+Timed.getFireCount()+ " PM state: " + pm2.getState()+ " VM state: "+vm2.getState());
vm2.newComputeTask(100000, ResourceConsumption.unlimitedProcessing, new ConsumptionEventAdapter() {
            @Override
            public void conComplete() {
                System.out.println("Time: "+Timed.getFireCount());
                StorageObject storageObj = new StorageObject("1",20000, false);
                repo.registerObject(storageObj);
            }
        });


Timed.simulateUntilLastEvent();
    }
}