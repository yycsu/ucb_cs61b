package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    private int r;

    private int g;

    private int b;

    private double repEnergyRetained = 0.5;

    private double repEnergyGiven = 0.5;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    public Clorus replicate() {
        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Clorus(babyEnergy);
    }

    public void move() {
        energy -= 0.03;
        // TODO
    }

    public void stay() {
        energy -= 0.01;
        // TODO
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlips = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}

        for (Map.Entry<Direction,Occupant> entry : neighbors.entrySet()) {
            Direction key = entry.getKey();
            Occupant value = entry.getValue();
            if (value.name().equals("empty")){
                emptyNeighbors.add(key);
            }
            else if (value.name().equals("plip")){
                plipNeighbors.add(key);
                anyPlips = true;
            }
        }
        if (emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        else if (anyPlips){
            Direction p = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, p);
        }

        // Rule 3
        else if (energy >= 1.0){
            Direction p = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE,p);
        }

        // Rule 4
        Direction p = HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, p);
    }
}
