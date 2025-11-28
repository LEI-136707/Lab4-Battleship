// java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarrackTest {

    // Minimal fake IPosition for constructing the Carrack
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
    }

    @Test
    void sizeIsThree() {
        Carrack c = new Carrack(Compass.NORTH, new FakePosition(0, 0));
        assertEquals(3, c.getSize());
        assertEquals(3, c.getPositions().size());
    }

    @Test
    void positionsForNorthOrSouthIncreaseRows() {
        FakePosition start = new FakePosition(5, 5);

        Carrack north = new Carrack(Compass.NORTH, start);
        assertEquals(3, north.getPositions().size());
        assertEquals(5, north.getPositions().get(0).getRow());
        assertEquals(5, north.getPositions().get(0).getColumn());
        assertEquals(6, north.getPositions().get(1).getRow());
        assertEquals(5, north.getPositions().get(1).getColumn());
        assertEquals(7, north.getPositions().get(2).getRow());
        assertEquals(5, north.getPositions().get(2).getColumn());

        Carrack south = new Carrack(Compass.SOUTH, start);
        assertEquals(5, south.getPositions().get(0).getRow());
        assertEquals(6, south.getPositions().get(1).getRow());
        assertEquals(7, south.getPositions().get(2).getRow());
    }

    @Test
    void positionsForEastOrWestIncreaseColumns() {
        FakePosition start = new FakePosition(2, 3);

        Carrack east = new Carrack(Compass.EAST, start);
        assertEquals(3, east.getPositions().size());
        assertEquals(2, east.getPositions().get(0).getRow());
        assertEquals(3, east.getPositions().get(0).getColumn());
        assertEquals(2, east.getPositions().get(1).getRow());
        assertEquals(4, east.getPositions().get(1).getColumn());
        assertEquals(2, east.getPositions().get(2).getRow());
        assertEquals(5, east.getPositions().get(2).getColumn());

        Carrack west = new Carrack(Compass.WEST, start);
        assertEquals(3, west.getPositions().size());
        assertEquals(3, west.getPositions().get(0).getColumn());
        assertEquals(4, west.getPositions().get(1).getColumn());
        assertEquals(5, west.getPositions().get(2).getColumn());
    }
}
