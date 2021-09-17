package main;

public class Cell {
    public final static int MAX_DIRECTIONS = 4;
    public int x = 0;
    public int y = 0;

    private Cell[] walls;
    private int direction;

    private int exitDirection = -1;
    private int enterDirection = -1;

    public Cell() {
        walls = new Cell[4];
        direction = 2;
        enterDirection = 0;
    }

    public Cell(int direction, Cell parent) {
        y = parent.y;
        x = parent.x;
        switch (direction) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
        }

        walls = new Cell[4];
        this.direction = direction;

        // Помечаем вход как пустую ячейку
        this.turnRight();
        this.turnRight();
        walls[this.direction] = parent;
        this.turnRight();
        this.turnRight();
    }

    public void turnLeft() {
        direction--;
        if (direction == -1) {
            direction = MAX_DIRECTIONS-1;
        }
    }

    public void turnRight() {
        direction++;
        if (direction == MAX_DIRECTIONS) {
            direction = 0;
        }
    }

    public Cell goForward() {
        if (walls[direction] == null) {
            walls[direction] = new Cell(direction, this);
        }
        walls[direction].direction = direction;
        return walls[direction];
    }

    public void exit() {
        exitDirection = direction;
        turnLeft();
        turnLeft();
    }

    public Cell[] getWalls() {
        return walls;
    }

    public String getKey(){
        return getKey(
                walls[0]!=null || enterDirection != -1,
                walls[1]!=null || exitDirection == 1,
                walls[2]!=null  || exitDirection == 2,
                walls[3]!=null  || exitDirection == 3
        );

//        if (enterDirection != -1 ) {
//            return getKey(true, walls[1]!=null,walls[2]!=null, walls[3]!=null);
//        }
//        if (exitDirection != -1) {
//            switch (exitDirection){
//                case 0:
//                    return getKey(true, walls[1]!=null,walls[2]!=null, walls[3]!=null);
//                case 1:
//                    return getKey(walls[0]!=null, true,walls[2]!=null, walls[3]!=null);
//                case 2:
//                    return getKey(walls[0]!=null, walls[1]!=null,true, walls[3]!=null);
//                case 3:
//                    return getKey(walls[0]!=null, walls[1]!=null,walls[2]!=null, true);
//
//            }
//        }
//        switch (direction) {
//            case 0:
//                return getKey(true, walls[1]!=null,walls[2]!=null, walls[3]!=null);
//            case 1:
//                return getKey(walls[0]!=null,true,walls[2]!=null, walls[3]!=null);
//            case 2:
//                return getKey(walls[0]!=null, walls[1]!=null,true, walls[3]!=null);
//            case 3:
//                return getKey(walls[0]!=null, walls[1]!=null,walls[2]!=null, true);
//        }
//        return "";
    }
    private String getKey(boolean north, boolean east, boolean south, boolean west){
        if (north && !south && !west && !east) {
            return "1";
        }
        if (!north && south && !west && !east) {
            return "2";
        }
        if (north && south && !west && !east) {
            return "3";
        }
        if (!north && !south && west && !east) {
            return "4";
        }
        if (north && !south && west && !east) {
            return "5";
        }
        if (!north && south && west && !east) {
            return "6";
        }
        if (north && south && west && !east) {
            return "7";
        }
        if (!north && !south && !west && east) {
            return "8";
        }
        if (north && !south && !west && east) {
            return "9";
        }
        if (!north && south && !west && east) {
            return "a";
        }
        if (north && south && !west && east) {
            return "b";
        }
        if (!north && !south && west && east) {
            return "c";
        }
        if (north && !south && west && east) {
            return "d";
        }
        if (!north && south && west && east) {
            return "e";
        }
        return "f";
    }
}

