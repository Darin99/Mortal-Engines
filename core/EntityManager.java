package core;

import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityManager implements Runnable {

    private InputStreamReader inputStreamReader;
    private BufferedReader reader;
    private String[] tokens;
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;
    private MachinesManager manager;

    public EntityManager() throws IOException {
        this.setInputStreamReader(new InputStreamReader(System.in));
        this.setReader(new BufferedReader(this.getInputStreamReader()));
        this.setTokens(this.getReader().readLine().split("\\s+"));
        this.setPilotFactory(new PilotFactoryImpl());
        this.setMachineFactory(new MachineFactoryImpl());
        this.setPilots(new LinkedHashMap<>());
        this.setMachines(new LinkedHashMap<>());
        this.setManager(new MachinesManagerImpl(this.getPilotFactory(), this.getMachineFactory(),
                this.getPilots(), this.getMachines()));
    }

    public InputStreamReader getInputStreamReader() {
        return this.inputStreamReader;
    }

    public void setInputStreamReader(InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    public BufferedReader getReader() {
        return this.reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public String[] getTokens() {
        return this.tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
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

    public MachinesManager getManager() {
        return this.manager;
    }

    public void setManager(MachinesManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {

        while (!this.getTokens()[0].equals("Over")) {

            switch (this.getTokens()[0]) {
                case "Hire":
                    System.out.println(this.getManager().hirePilot(this.getTokens()[1]));
                    break;
                case "Report":
                    System.out.println(this.getManager().pilotReport(this.getTokens()[1]));
                    break;
                case "ManufactureTank": {
                    double attackPoints = Double.parseDouble(this.getTokens()[2]);
                    double defencePoints = Double.parseDouble(this.getTokens()[3]);
                    System.out.println(this.getManager().manufactureTank(this.getTokens()[1], attackPoints, defencePoints));
                    break;
                }
                case "ManufactureFighter": {
                    double attackPoints = Double.parseDouble(this.getTokens()[2]);
                    double defencePoints = Double.parseDouble(this.getTokens()[3]);
                    System.out.println(this.getManager().manufactureFighter(this.getTokens()[1], attackPoints, defencePoints));
                    break;
                }
                case "Engage":
                    System.out.println(this.getManager().engageMachine(this.getTokens()[1], this.getTokens()[2]));
                    break;
                case "Attack":
                    System.out.println(this.getManager().attackMachines(this.getTokens()[1], this.getTokens()[2]));
                    break;
                case "DefenseMode":
                    System.out.println(this.getManager().toggleTankDefenseMode(this.getTokens()[1]));
                    break;
                case "AggressiveMode":
                    System.out.println(this.getManager().toggleFighterAggressiveMode(this.getTokens()[1]));
                    break;
            }
            try {
                this.setTokens(this.getReader().readLine().split("\\s+"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}