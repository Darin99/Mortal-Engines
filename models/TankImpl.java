package models;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {

    private boolean defenceMode;
    private double attackPointsModifier;
    private double defencePointsModifier;

    public TankImpl(String name, double attackPoints, double defencePoints) {
        super(name, attackPoints, defencePoints);
        super.setHealthPoints(100);
        this.setDefenceMode(true);
        this.setAttackPointsModifier(40.0);
        this.setDefencePointsModifier(30.0);
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenceMode;
    }

    public void setDefenceMode(boolean defenceMode) {
        this.defenceMode = defenceMode;
    }

    public double getAttackPointsModifier() {
        return this.attackPointsModifier;
    }


    public void setAttackPointsModifier(double attackPointsModifier) {
        this.attackPointsModifier = attackPointsModifier;
    }


    public double getDefencePointsModifier() {
        return this.defencePointsModifier;
    }


    public void setDefencePointsModifier(double defencePointsModifier) {
        this.defencePointsModifier = defencePointsModifier;
    }

    @Override
    public void toggleDefenseMode() {

        if (this.getDefenseMode()) {

            this.setDefenceMode(false);
            super.setAttackPoints(super.getAttackPoints() - this.getAttackPointsModifier());
            super.setDefencePoints(super.getDefensePoints() + this.getDefencePointsModifier());

        } else {

            this.setDefenceMode(true);
            super.setAttackPoints(super.getAttackPoints() + this.getAttackPointsModifier());
            super.setDefencePoints(super.getDefensePoints() - this.getDefencePointsModifier());
        }
    }
}