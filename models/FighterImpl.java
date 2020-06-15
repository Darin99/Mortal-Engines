package models;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {

    private boolean aggressiveMode;
    private double attackPointsModifier;
    private double defencePointsModifier;

    public FighterImpl(String name, double attackPoints, double defencePoints) {
        super(name, attackPoints, defencePoints);
        super.setHealthPoints(200);
        this.setAggressiveMode(true);
        this.setAttackPointsModifier(50.0);
        this.setDefencePointsModifier(25.0);
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    public void setAggressiveMode(boolean aggressiveMode) {
        this.aggressiveMode = aggressiveMode;
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
    public void toggleAggressiveMode() {

        if (this.getAggressiveMode()) {

            this.setAggressiveMode(false);
            super.setAttackPoints(super.getAttackPoints() + this.getAttackPointsModifier());
            super.setDefencePoints(super.getDefensePoints() - this.getDefencePointsModifier());
        } else {

            this.setAggressiveMode(true);
            super.setAttackPoints(super.getAttackPoints() - this.getAttackPointsModifier());
            super.setDefencePoints(super.getDefensePoints() + this.getDefencePointsModifier());
        }
    }
}