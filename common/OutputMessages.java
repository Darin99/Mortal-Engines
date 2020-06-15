package common;

public class OutputMessages {

    public static final String PILOT_HIRED = "Pilot %s hired";

    public static final String PILOT_EXISTS = "Pilot %s is hired already";

    public static final String TANK_MANUFACTURED = "Tank %s manufactured - attack: %.2f; defense: %.2f";

    public static final String FIGHTER_MANUFACTURED = "Fighter %s manufactured - attack: %.2f; defense: %.2f";

    public static final String MACHINE_EXISTS = "Machine %s is manufactured already";

    public static final String MACHINE_HAS_PILOT_ALREADY = "Machine %s is already occupied";

    public static final String PILOT_NOT_FOUND = "Pilot %s could not be found";

    public static final String MACHINE_NOT_FOUND = "Machine %s could not be found";

    public static final String MACHINE_ENGAGED = "Pilot %s engaged machine %s";

    public static final String FIGHTER_OPERATION_SUCCESSFUL = "Fighter %s toggled aggressive mode";

    public static final String TANK_OPERATION_SUCCESSFUL = "Tank %s toggled defense mode";

    public static final String NOT_SUPPORTED_OPERATION = "Machine %s does not support this operation";

    public static final String ATTACK_SUCCESSFUL = "Machine %s was attacked by machine %s - current health: %.2f";

    public static final String ATTACK_UNSUCCESSFUL = "Machine %s have zero health";

    public static final String MACHINE_DOES_NOT_HAVE_ENOUGH_ATTACK_POINTS = "Machine %s does not have enough attack points. It needs %.2f more points to attack";
}
