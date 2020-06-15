package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {

    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots,
                               Map<String, Machine> machines) {
        this.setPilotFactory(pilotFactory);
        this.setMachineFactory(machineFactory);
        this.setPilots(pilots);
        this.setMachines(machines);
    }

    public PilotFactory getPilotFactory() {
        return this.pilotFactory;
    }

    public void setPilotFactory(PilotFactory pilotFactory) {
        this.pilotFactory = pilotFactory;
    }

    public MachineFactory getMachineFactory() {
        return this.machineFactory;
    }

    public void setMachineFactory(MachineFactory machineFactory) {
        this.machineFactory = machineFactory;
    }

    public Map<String, Pilot> getPilots() {
        return this.pilots;
    }

    public void setPilots(Map<String, Pilot> pilots) {
        this.pilots = pilots;
    }

    public Map<String, Machine> getMachines() {
        return this.machines;
    }

    public void setMachines(Map<String, Machine> machines) {
        this.machines = machines;
    }

    @Override
    public String hirePilot(String name) {

        String output;

        if (this.getPilots().containsKey(name)) {
            output = String.format(OutputMessages.PILOT_EXISTS, name);
        } else {
            this.getPilots().put(name, this.getPilotFactory().createPilot(name));
            output = String.format(OutputMessages.PILOT_HIRED, name);
        }
        return output;
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        String output;
        if (this.getMachines().containsKey(name)) {
            output = String.format(OutputMessages.MACHINE_EXISTS, name);
        } else {
            this.getMachines().put(name, this.getMachineFactory().createTank(name, attackPoints, defensePoints));

            output = String.format(OutputMessages.TANK_MANUFACTURED, name, attackPoints, defensePoints);
        }
        return output;
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        String output;
        if (this.getMachines().containsKey(name)) {
            output = String.format(OutputMessages.MACHINE_EXISTS, name);
        } else {
            this.getMachines().put(name, this.getMachineFactory().createFighter(name, attackPoints, defensePoints));
            output = String.format(OutputMessages.FIGHTER_MANUFACTURED, name, attackPoints, defensePoints);
        }
        return output;
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {

        String output = "Error in method engageMachine";

        if (this.getMachines().containsKey(selectedMachineName)) {
            Machine machine = this.getMachines().get(selectedMachineName);
            boolean isEngaged = false;

            for (Pilot pilot : this.getPilots().values()) {
                if (pilot.getMachines().contains(machine)) {
                    isEngaged = true;
                    output = String.format(OutputMessages.MACHINE_HAS_PILOT_ALREADY, selectedMachineName);
                    break;
                }
            }

            if (!isEngaged) {
                Pilot pilot = this.getPilots().get(selectedPilotName);
                pilot.addMachine(machine);
                this.getMachines().get(selectedMachineName).setPilot(pilot);
                output = String.format(OutputMessages.MACHINE_ENGAGED, selectedPilotName, selectedMachineName);
            }
        } else {
            output = String.format(OutputMessages.MACHINE_NOT_FOUND, selectedMachineName);
        }

        return output;
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {

        String output;

        if (!this.getMachines().containsKey(attackingMachineName)) {
            output = String.format(OutputMessages.MACHINE_NOT_FOUND, attackingMachineName);
        } else if (!this.getMachines().containsKey(defendingMachineName)) {
            output = String.format(OutputMessages.MACHINE_NOT_FOUND, defendingMachineName);
        } else {

            Machine defender = this.getMachines().get(defendingMachineName);
            Machine attacker = this.getMachines().get(attackingMachineName);

            if (defender.getHealthPoints() > 0) {

                if (attacker.getAttackPoints() >= defender.getDefensePoints()) {
                    attacker.attack(defendingMachineName);
                    defender.setHealthPoints(defender.getHealthPoints() - attacker.getAttackPoints());
                    if (defender.getHealthPoints() < 0) {
                        defender.setHealthPoints(0);
                    }
                    output = String.format(OutputMessages.ATTACK_SUCCESSFUL, defendingMachineName, attackingMachineName, defender.getHealthPoints());
                } else {
                    output = String.format(OutputMessages.MACHINE_DOES_NOT_HAVE_ENOUGH_ATTACK_POINTS
                            , attackingMachineName, Math.abs(defender.getDefensePoints() - attacker.getHealthPoints()));
                }
            } else {
                defender.setHealthPoints(0);
                output = String.format(OutputMessages.ATTACK_UNSUCCESSFUL, defendingMachineName);
            }
        }
        return output;
    }

    @Override
    public String pilotReport(String pilotName) {

        String output;

        if (this.getPilots().containsKey(pilotName)) {
            output = this.getPilots().get(pilotName).report();
        } else {
            output = String.format(OutputMessages.PILOT_NOT_FOUND, pilotName);
        }
        return output;
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {

        String output;

        if (!this.getMachines().containsKey(fighterName)) {
            output = String.format(OutputMessages.MACHINE_NOT_FOUND, fighterName);
        } else {

            if (this.getMachines().get(fighterName) instanceof Fighter) {
                Fighter fighter = (Fighter) this.getMachines().get(fighterName);
                fighter.toggleAggressiveMode();
                output = String.format(OutputMessages.FIGHTER_OPERATION_SUCCESSFUL, fighterName);
            } else {
                output = String.format(OutputMessages.NOT_SUPPORTED_OPERATION, fighterName);
            }
        }
        return output;
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        String output;

        if (!this.getMachines().containsKey(tankName)) {
            output = String.format(OutputMessages.MACHINE_NOT_FOUND, tankName);
        } else {
            if (this.getMachines().get(tankName) instanceof Tank) {
                Tank tank = (Tank) this.getMachines().get(tankName);
                tank.toggleDefenseMode();
                output = String.format(OutputMessages.TANK_OPERATION_SUCCESSFUL, tankName);
            } else {
                output = String.format(OutputMessages.NOT_SUPPORTED_OPERATION, tankName);
            }
        }
        return output;
    }
}