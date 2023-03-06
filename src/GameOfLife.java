//import java.util.ArrayList;
//
//class XOY {
//
//    public int x, y;
//
//    public XOY(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
//
//class Testing {
//    public static int[][] get_matrix(int length, String str) {
//        int[][] new_matrix = new int[length][length];
//
//        for (int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//            new_matrix[i / length][i % length] = Character.getNumericValue(ch);
//        }
//
//        return new_matrix;
//    }
//}
//
//public class GameOfLife {
//
////    int[][] matrix
//    public static void main(String[] args) throws InterruptedException {
//        int[][] matrix = new int[5][5];
//        int iterations = 5;
//
////        ArrayList<XOY> points = new ArrayList<>();
////        points.add(new XOY(2, 1));
////        points.add(new XOY(2, 2));
////        points.add(new XOY(2, 3));
//
//
////       add_points(matrix, points);
//
//        matrix = Testing.get_matrix(3, "0000000000011100000000000");
//        print_matrix(matrix);
//        // TimeUnit.SECONDS.sleep(1);
//
//        while(iterations > 0) {
//           check_matrix(matrix);
//            // TimeUnit.SECONDS.sleep(1);
//            print_matrix(matrix);
//            iterations--;
//        }
//    }
//
//    public static ArrayList<XOY> make_neigh() {
//        ArrayList<XOY> neigh_dir = new ArrayList<>();
//        neigh_dir.add(new XOY(-1, 0));
//        neigh_dir.add(new XOY(-1, 1));
//        neigh_dir.add(new XOY(0, 1));
//        neigh_dir.add(new XOY(1, 1));
//        neigh_dir.add(new XOY(1, 0));
//        neigh_dir.add(new XOY(1, -1));
//        neigh_dir.add(new XOY(0, -1));
//        neigh_dir.add(new XOY(-1, -1));
//
//        return neigh_dir;
//    }
//
//    public static void print_matrix(int[][] matrix) {
//        for (int[] line : matrix) {
//            for (int element : line) {
//                System.out.print(element + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//    public static void add_points(int[][] matrix, ArrayList<XOY> points) {
//        for (XOY point : points) {
//            matrix[point.x][point.y] = 1;
//        }
//    }
//
//    public static void check_matrix(int[][] matrix) {
//
//        ArrayList<XOY> deads = new ArrayList<>();
//        ArrayList<XOY> borns = new ArrayList<>();
//
//        int length = matrix[0].length;
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < length; j++) {
//                int res = check_point(matrix, i, j);
//
//                if (res == -1)
//                    deads.add(new XOY(i, j));
//                else
//                    borns.add(new XOY(i, j));
//            }
//        }
//
//
//        for (XOY point : deads) {
//            matrix[point.x][point.y] = 0;
//        }
//
//        for (XOY point : borns) {
//            // print_point(point);
//            matrix[point.x][point.y] = 1;
//        }
//    }
//
//    public static int check_point(int[][] matrix, int i, int j) {
//        ArrayList<XOY> neigh_dir = make_neigh();
//        int length = matrix[0].length;
//        int neighbours = 0;
//
//        for (int dir = 0; dir < 8; dir++) {
//            XOY add_on = neigh_dir.get(dir);
//            XOY potential_neighbour = new XOY(i + add_on.x, j + add_on.y);
//
//            if (out_of_bounds(length, potential_neighbour.x, potential_neighbour.y) == 0) {
//                if (matrix[potential_neighbour.x][potential_neighbour.y] == 1)
//                    neighbours++;
//            }
//        }
//
//        if (neighbours == 3 && matrix[i][j] == 0)
//            return 1;
//
//        if((neighbours == 2 || neighbours == 3) && matrix[i][j] == 1)
//            return 0;
//
//        return -1;
//    }
//
//    public static int out_of_bounds(int length, int i, int j) {
//        if (i < 0 || i >= length || j < 0 || j >= length)
//            return 1;
//        return 0;
//    }
//
//    public static void print_point(XOY e) {
//        System.out.println("{" + e.x + "," + e.y + "}");
//    }
//}