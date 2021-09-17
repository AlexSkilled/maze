package main;


public class Field {
    int maxX, minX, maxY, minY;

    Cell coreCell;

    public Field(String path){
        coreCell = new Cell();
        path = path.substring(1, path.length()-1);
        path = path.replace("W W", " ");
        countPath(path);
    }

    private void countPath(String path ) {
        for (char ch: path.toCharArray()) {
            switch (ch) {
                case 'W':
                    coreCell = coreCell.goForward();
                    break;
                case 'R':
                    coreCell.turnRight();
                    break;
                case 'L':
                    coreCell.turnLeft();
                    break;
                case ' ':
                    coreCell.exit();
                    break;
            }
            if (coreCell.x > maxX) {
                maxX = coreCell.x;
            }
            if (coreCell.x < minX) {
                minX = coreCell.x;
            }
            if (coreCell.y > maxY) {
                maxY = coreCell.y;
            }
            if (coreCell.y < minY) {
                minY = coreCell.y;
            }
        }
        countNet();
    }

    public String getResult(){
        String result = "";
        for (String[] s : net){
            result += String.join("", s) + "\n";
        }
        return result;
    }

    private String[][] net;

    private int xDelta, yDelta;

    private void countNet() {
        xDelta = Math.abs(minX);
        yDelta =  Math.abs(minY);
        net = new String[1+yDelta+maxY][1 + xDelta + maxX];

        extractCell(coreCell);
    }

    private void extractCell(Cell cell) {
        if (net[cell.y + yDelta][cell.x + xDelta ] != null) {
            return;
        }
        net[cell.y + yDelta][cell.x + xDelta ] = cell.getKey();
        for( Cell c : cell.getWalls()) {
            if (c != null) {
                extractCell(c);
            }
        }
    }
}
