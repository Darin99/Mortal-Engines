package models;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachine implements Machine {
    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defencePoints;
    private double healthPoints;
    private List<String> targets;

    public BaseMachine(String name, double attackPoints, double defencePoints) {
        this.setName(name);
        this.setAttackPoints(attackPoints);
        this.setDefencePoints(defencePoints);
        this.setTargets(new ArrayList<>());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name.equals(" ") || name.isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new NullPointerException("Pilot cannot be null.");
        }
        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    public void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defencePoints;
    }

    public void setDefencePoints(double defencePoints) {
        this.defencePoints = defencePoints;
    }

    @Override
    public List<String> getTargets() {
        return this.targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    @Override
    public void attack(String targetName) {
        if (targetName.isEmpty() || targetName.equals(" ")) {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }
        this.targets.add(targetName);
    }
}