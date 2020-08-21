package creatures;
import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestClorus {

    @Test
    public void testBasics(){
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0 ,231), c.color());
        c.move();
        assertEquals(1.97,c.energy(),0.01);
        c.move();
        assertEquals(1.94,c.energy(),0.01);
        c.stay();
        assertEquals(1.93,c.energy(),0.01);
        c.stay();
        assertEquals(1.92,c.energy(),0.01);
    }

    @Test
    public void testAttack(){
        Clorus c = new Clorus(2);
        Clorus small1 = new Clorus(0.5);
        Clorus small2 = new Clorus(10);
        c.attack(small1);
        assertEquals(2.5,c.energy(),0.01);
        c.attack(small2);
        assertEquals(12.5,c.energy(),0.01);
    }

    @Test
    public void tsetReplicate(){
        Clorus c = new Clorus(2);
        Clorus r = c.replicate();
        assertEquals(1,c.energy(),0.01);
        assertEquals(1,r.energy(),0.01);
    }

    @Test
    public void testChoose(){

        //No empty adjacent, stay
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surround = new HashMap<Direction, Occupant>();
        surround.put(Direction.TOP, new Impassible());
        surround.put(Direction.BOTTOM, new Impassible());
        surround.put(Direction.LEFT, new Impassible());
        surround.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surround);
        Action expect = new Action(Action.ActionType.STAY);

        assertEquals(expect,actual);

        //any plip around, attack
        c = new Clorus(1.2);
        surround = new HashMap<Direction, Occupant>();
        surround.put(Direction.TOP, new Plip());
        surround.put(Direction.BOTTOM, new Impassible());
        surround.put(Direction.LEFT, new Impassible());
        surround.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(surround);
        expect = new Action(Action.ActionType.STAY);

        assertEquals(expect,actual);

        c = new Clorus(0.8);
        surround = new HashMap<Direction, Occupant>();
        surround.put(Direction.TOP, new Plip());
        surround.put(Direction.BOTTOM, new Empty());
        surround.put(Direction.LEFT, new Impassible());
        surround.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(surround);
        expect = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expect,actual);

        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expect = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expect, actual);

        // Energy < 1; replicate towards an empty space.
        c = new Clorus(0.8);
        topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expect = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expect, actual);
    }



}
