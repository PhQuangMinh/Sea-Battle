import java.util.ArrayList;

public class Player {
    private int index;
    private String name;
    private int numberShot;
    private char[][] board;
    private char[][] oppositeBoard;
    private boolean completed;
    private ArrayList<Ship> ships;
    private int ship;
    private ArrayList<Shot> shots;
    private boolean statusNormal;
    private int sizeBoard;

    Player(int index, String name, int numberShot, char[][] board, char[][] oppositeBoard, Boolean completed,
            ArrayList<Ship> ships, ArrayList<Shot> shots, int sizeBoard, boolean statusNormal) {
        this.index = index;
        this.name = name;
        this.numberShot = numberShot;
        this.board = board;
        this.oppositeBoard = oppositeBoard;
        this.completed = completed;
        this.ships = ships;
        this.shots = shots;
        this.sizeBoard = sizeBoard;
        this.statusNormal = statusNormal;
    }

    Player(String name, int numberShot, int ship) {
        this.name = name;
        this.numberShot = numberShot;
        this.ship = ship;
    }

    Player() {
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return name;
    }

    public char[][] getBoard() {
        return board;
    }

    public char[][] getOppositeBoard() {
        return oppositeBoard;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setOppositeBoard(char[][] oppositeBoard) {
        this.oppositeBoard = oppositeBoard;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public int getNumberShot() {
        return this.numberShot;
    }

    public void setNumberShot(int numberShot) {
        this.numberShot = numberShot;
    }

    public int getShip() {
        return this.ship;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> list) {
        this.shots = list;
    }

    public boolean getStatusNormal() {
        return statusNormal;
    }

    public void setStatusNormal(boolean statusNormal) {
        this.statusNormal = statusNormal;
    }
    public int getSizeBoard(){
        return sizeBoard;
    }
    public int destroyedShips() {
        int count = 0;
        for (var ship : ships) {
            if (!ship.getStatus())
                count++;
        }
        return count;
    }

    public int remainNumberShips() {
        int count = 0;
        for (var ship : ships) {
            if (ship.getStatus())
                count++;
        }
        return count;
    }
}
