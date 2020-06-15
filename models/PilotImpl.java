package models;

import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.ArrayList;
import java.util.List;

public class PilotImpl implements Pilot {
    private String name;
    private List<Machine> machines;

    public PilotImpl(String name) {
        this.setName(name);
        this.setMachines(new ArrayList<>());
    }

    private void setName(String name) {
        if (name.equals(" ") || name.isEmpty()) {
            throw new IllegalArgumentException("Pilot name cannot be null or empty string.");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if (machine == null) {
            throw new NullPointerException("Null machine cannot be added to the pilot.");
        }
        this.getMachines().add(machine);
    }

    @Override
    public List<Machine> getMachines() {
        return this.machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        if (this.getMachines().isEmpty()) {
            builder.append(String.format("%s - %d machines", this.getName(), this.getMachines().size()));
        } else {
            builder.append(String.format("%s - %d machines", this.getName(), this.getMachines().size()))
                    .append(System.lineSeparator());
            for (Machine machine : this.getMachines()) {
                builder.append(String.format("- %s", machine.getName())).append(System.lineSeparator())
                        .append(String.format(" *Type: %s", machine.getClass().getSimpleName().substring(0, machine.getClass().getSimpleName().length() - 4)))
                        .append(System.lineSeparator())
                        .append(String.format(" *Health: %.2f", machine.getHealthPoints())).append(System.lineSeparator())
                        .append(String.format(" *Attack: %.2f", machine.getAttackPoints())).append(System.lineSeparator())
                        .append(String.format(" *Defence: %.2f", machine.getDefensePoints())).append(System.lineSeparator());
                if (machine.getTargets().isEmpty()) {
                    builder.append(" *Targets: None").append(System.lineSeparator());
                } else {
                    builder.append(" *Targets: ").append(String.join(", ", machine.getTargets())).append(System.lineSeparator());
                }
                if (machine.getClass().getSimpleName().equals("FighterImpl")) {
                    Fighter fighter = (Fighter) machine;
                    if (fighter.getAggressiveMode()) {
                        builder.append(" *Aggressive Mode(ON)");
                    } else {
                        builder.append(" *Aggressive Mode(OFF)");
                    }
                } else {
                    Tank tank = (Tank) machine;
                    if (tank.getDefenseMode()) {
                        builder.append(" *Defence Mode(ON)").append(System.lineSeparator());
                    } else {
                        builder.append(" *Defence Mode(OFF)").append(System.lineSeparator());
                    }
                }
            }
        }
        return builder.toString();
    }
}