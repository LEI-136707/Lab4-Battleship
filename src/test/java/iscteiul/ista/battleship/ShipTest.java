// java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    // Minimal concrete Ship for testing
    static class TestShip extends Ship {
        TestShip() {
            super("test", Compass.NORTH, new FakePosition(0, 0));
        }

        @Override
        public Integer getSize() {
            return getPositions().size();
        }
    }

    // Minimal fake IPosition used only for these tests
    static class FakePosition implements IPosition {
        private final int row;
        private final int col;
        private boolean hit = false;
        private boolean occupied = false;

        FakePosition(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int getRow() {
            return row;
        }

        @Override
        public int getColumn() {
            return col;
        }

        @Override
        public boolean isHit() {
            return hit;
        }

        @Override
        public void shoot() {
            hit = true;
        }

        @Override
        public boolean isOccupied() {
            return occupied;
        }

        @Override
        public void occupy() {
            occupied = true;
        }

        @Override
        public boolean isAdjacentTo(IPosition other) {
            if (other == null) return false;
            int dr = Math.abs(this.getRow() - other.getRow());
            int dc = Math.abs(this.getColumn() - other.getColumn());
            return dr <= 1 && dc <= 1 && !(dr == 0 && dc == 0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IPosition)) return false;
            IPosition p = (IPosition) o;
            return this.row == p.getRow() && this.col == p.getColumn();
        }

        @Override
        public int hashCode() {
            return 31 * row + col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }

    @Test
    void toStringNotNull() {
        TestShip ship = new TestShip();
        assertNotNull(ship.toString());
    }

    @Test
    void shipFloatsUntilAllPositionsHit() {
        TestShip ship = new TestShip();
        FakePosition p1 = new FakePosition(1, 1);
        FakePosition p2 = new FakePosition(1, 2);
        ship.getPositions().add(p1);
        ship.getPositions().add(p2);

        assertTrue(ship.stillFloating());

        // shoot first position via ship.shoot
        ship.shoot(new FakePosition(1,1)); // equals compares coords
        assertTrue(ship.stillFloating());

        // shoot remaining position directly
        ship.getPositions().get(1).shoot();
        assertFalse(ship.stillFloating());
    }

    @Test
    void shootMarksMatchingPosition() {
        TestShip ship = new TestShip();
        FakePosition p = new FakePosition(2, 3);
        ship.getPositions().add(p);

        assertFalse(p.isHit());
        ship.shoot(new FakePosition(2,3));
        assertTrue(p.isHit());
    }

    @Test
    void occupiesDetectsPositionByCoordinates() {
        TestShip ship = new TestShip();
        ship.getPositions().add(new FakePosition(4, 5));

        assertTrue(ship.occupies(new FakePosition(4,5)));
        assertFalse(ship.occupies(new FakePosition(0,0)));
    }

    @Test
    void tooCloseToDetectsAdjacentShip() {
        TestShip a = new TestShip();
        TestShip b = new TestShip();

        a.getPositions().add(new FakePosition(5,5));
        b.getPositions().add(new FakePosition(6,6)); // diagonal adjacent

        assertTrue(a.tooCloseTo(b));
        assertTrue(b.tooCloseTo(a));

        // non adjacent
        TestShip c = new TestShip();
        c.getPositions().add(new FakePosition(10,10));
        assertFalse(a.tooCloseTo(c));
    }

    @Test
    void boundaryHelpersReturnCorrectValues() {
        TestShip ship = new TestShip();
        ship.getPositions().add(new FakePosition(3,7));
        ship.getPositions().add(new FakePosition(1,5));
        ship.getPositions().add(new FakePosition(2,9));

        assertEquals(1, ship.getTopMostPos());
        assertEquals(3, ship.getBottomMostPos());
        assertEquals(5, ship.getLeftMostPos());
        assertEquals(9, ship.getRightMostPos());
    }
}
