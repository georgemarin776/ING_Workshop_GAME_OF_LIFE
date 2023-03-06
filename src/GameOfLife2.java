import java.util.ArrayList;

class XOY {
    int x, y;

    public XOY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public XOY sum(XOY point) {
        return new XOY(this.x + point.x, this.y + point.y);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XOY xoy = (XOY) o;
        return x == xoy.x && y == xoy.y;
    }
}

class Directions {
    ArrayList <XOY> dirArray;
    int no = 8;

    public Directions() {
        dirArray = new ArrayList<>();
        dirArray.add(new XOY(-1, 0));
        dirArray.add(new XOY(-1, 1));
        dirArray.add(new XOY(0, 1));
        dirArray.add(new XOY(1, 1));
        dirArray.add(new XOY(1, 0));
        dirArray.add(new XOY(1, -1));
        dirArray.add(new XOY(0, -1));
        dirArray.add(new XOY(-1, -1));
    }
}
public class GameOfLife2 {

    static ArrayList<XOY> points;
    static ArrayList<XOY> pointsCopy;
    static ArrayList<XOY> possibleNeighbours;
    static ArrayList<XOY> graveyard;
    static Directions directions;
    static int iterations = 10;
    public static void main(String[] args) {
        points = new ArrayList<>();
        pointsCopy = new ArrayList<>();
        directions = new Directions();
        graveyard = new ArrayList<>();

        points.add(new XOY(4, 3));
        points.add(new XOY(4, 4));
        points.add(new XOY(4, 5));
        printPoints(points);
        printMatrix();

        for (int i = 0; i < iterations; i++) {
            oneIteration();
            printPoints(points);
            printMatrix();
        }
    }

    public static void printMatrix() {
        int xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;

        for (XOY point : points) {
            if (point.x > xMax)
                xMax = point.x;

            if (point.x < xMin)
                xMin = point.x;

            if (point.y > yMax)
                yMax = point.y;

            if (point.y < xMin)
                yMin = point.y;
        }

        int min = Integer.min(xMin, yMin);
        int max = Integer.max(xMax, yMax);

        int length = max - min + 3;
        int gap = min * (-1) + 1;

        int[][] matrix = new int[length][length];

        for (XOY point : points) {
            matrix[point.x + gap][point.y + gap] = 1;
        }

        for (int[] line : matrix) {
            for (int i = 0; i < line.length - 1; i++) {
                System.out.print(line[i] + " ");
            }
            System.out.println(line[length - 1]);
        }
        System.out.println("--- ---");
    }
    public static void printPoints(ArrayList<XOY> arrayList) {
        for (XOY point : arrayList)
            System.out.println(point);
    }
    public static void oneIteration() {
        pointsCopy = new ArrayList<>(points);
        possibleNeighbours = new ArrayList<>();

        for (XOY point : points) {
            simulatePointFromPoints(point);
        }

        for (XOY point : possibleNeighbours) {
            simulatePointFromPossibleNeighbours(point);
        }

        points = new ArrayList<>(pointsCopy);
    }
    public static void simulatePointFromPoints(XOY point) {
        int neighboursNo = 0;
        for (int i = 0; i < directions.no; i++) {
            XOY possibleNeighbour = point.sum(directions.dirArray.get(i));
            if (points.contains(possibleNeighbour))
                neighboursNo++;
            else {
                if (!possibleNeighbours.contains(possibleNeighbour))
                    possibleNeighbours.add(possibleNeighbour);
            }
        }

        if (neighboursNo == 2 || neighboursNo == 3)
            return;

        if (graveyard.contains(point)) {
            return;
        }

        graveyard.add(point);
        pointsCopy.remove(point);
    }

    public static void simulatePointFromPossibleNeighbours(XOY point) {
        int neighboursNo = 0;
        for (int i = 0; i < directions.no; i++) {
            XOY possibleNeighbour = point.sum(directions.dirArray.get(i));
            if (points.contains(possibleNeighbour))
                neighboursNo++;
        }

        if (neighboursNo == 3)
            pointsCopy.add(point);
    }
}
