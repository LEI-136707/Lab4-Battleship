// java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    // Minimal concrete Ship for testing
    static class TestShip extends Ship {
        TestShip() {
            super("test", null, null); // assertions in the constructor are usually disabled during tests
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
            return false;
        }

        @Override
        public boolean isAdjacentTo(IPosition other) {
            if (other == null) return false;
            int dr = Math.abs(this.getRow() - other.getRow());
            int dc = Math.abs(this.getColumn() - other.getColumn());
            return dr <= 1 && dc <= 1 && !(dr == 0 && dc == 0);
        }

        @Override
        public void occupy() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IPosition)) return false;
            IPosition p = (IPosition) o;
            return this.row == p.getRow() && this.col == p.getColumn();
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
        ship.getPositions().add(new FakePosition(1, 1));
        ship.getPositions().add(new FakePosition(1, 2));

        // starts floating
        assertTrue(ship.stillFloating());

        // hit one position -> still floating
        ship.getPositions().get(0).shoot();
        assertTrue(ship.stillFloating());

        // hit the remaining position -> no longer floating
        ship.getPositions().get(1).shoot();
        assertFalse(ship.stillFloating());
    }
}
