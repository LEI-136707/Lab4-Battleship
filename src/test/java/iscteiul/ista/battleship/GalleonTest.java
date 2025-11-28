// java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GalleonTest {

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
    void sizeIsFive() {
        Galleon g = new Galleon(Compass.NORTH, new FakePosition(0, 0));
        assertEquals(5, g.getSize());
        assertEquals(5, g.getPositions().size());
    }

    @Test
    void positionsForNorth() {
        FakePosition start = new FakePosition(2, 3);
        Galleon g = new Galleon(Compass.NORTH, start);

        assertEquals(5, g.getPositions().size());
        assertEquals(2, g.getPositions().get(0).getRow());
        assertEquals(3, g.getPositions().get(0).getColumn());

        assertEquals(2, g.getPositions().get(1).getRow());
        assertEquals(4, g.getPositions().get(1).getColumn());

        assertEquals(2, g.getPositions().get(2).getRow());
        assertEquals(5, g.getPositions().get(2).getColumn());

        assertEquals(3, g.getPositions().get(3).getRow());
        assertEquals(4, g.getPositions().get(3).getColumn());

        assertEquals(4, g.getPositions().get(4).getRow());
        assertEquals(4, g.getPositions().get(4).getColumn());
    }

    @Test
    void positionsForSouth() {
        FakePosition start = new FakePosition(2, 3);
        Galleon g = new Galleon(Compass.SOUTH, start);

        assertEquals(5, g.getPositions().size());
        assertEquals(2, g.getPositions().get(0).getRow());
        assertEquals(3, g.getPositions().get(0).getColumn());

        assertEquals(3, g.getPositions().get(1).getRow());
        assertEquals(3, g.getPositions().get(1).getColumn());

        assertEquals(4, g.getPositions().get(2).getRow());
        assertEquals(2, g.getPositions().get(2).getColumn());

        assertEquals(4, g.getPositions().get(3).getRow());
        assertEquals(3, g.getPositions().get(3).getColumn());

        assertEquals(4, g.getPositions().get(4).getRow());
        assertEquals(4, g.getPositions().get(4).getColumn());
    }

    @Test
    void positionsForEast() {
        FakePosition start = new FakePosition(2, 6);
        Galleon g = new Galleon(Compass.EAST, start);

        assertEquals(5, g.getPositions().size());
        assertEquals(2, g.getPositions().get(0).getRow());
        assertEquals(6, g.getPositions().get(0).getColumn());

        assertEquals(3, g.getPositions().get(1).getRow());
        assertEquals(4, g.getPositions().get(1).getColumn());

        assertEquals(3, g.getPositions().get(2).getRow());
        assertEquals(5, g.getPositions().get(2).getColumn());

        assertEquals(3, g.getPositions().get(3).getRow());
        assertEquals(6, g.getPositions().get(3).getColumn());

        assertEquals(4, g.getPositions().get(4).getRow());
        assertEquals(6, g.getPositions().get(4).getColumn());
    }

    @Test
    void positionsForWest() {
        FakePosition start = new FakePosition(2, 3);
        Galleon g = new Galleon(Compass.WEST, start);

        assertEquals(5, g.getPositions().size());
        assertEquals(2, g.getPositions().get(0).getRow());
        assertEquals(3, g.getPositions().get(0).getColumn());

        assertEquals(3, g.getPositions().get(1).getRow());
        assertEquals(3, g.getPositions().get(1).getColumn());

        assertEquals(3, g.getPositions().get(2).getRow());
        assertEquals(4, g.getPositions().get(2).getColumn());

        assertEquals(3, g.getPositions().get(3).getRow());
        assertEquals(5, g.getPositions().get(3).getColumn());

        assertEquals(4, g.getPositions().get(4).getRow());
        assertEquals(3, g.getPositions().get(4).getColumn());
    }
}
