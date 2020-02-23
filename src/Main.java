import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int sizeMap;
    public static char[][] gameMap;
    public static Random rand = new Random();
    public static Scanner scan = new Scanner(System.in);
    public static boolean sum, sum2;
    public final static char DOT_X = 'X';
    public final static char DOT_O = 'O';
    public final static char DOT_ = '*';
    public static int x;
    public static int y;
    public static int k = 0;

    public static void main(String[] args) {
        sizeMap = 3;
        gameMap = new char[sizeMap][sizeMap];

        genMap();
        printMap();

        while (true){
            humTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек.");
                break;
            }
            if (checkMapFull()) {
                System.out.println("Ничья.");
                break;
            }

            compTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер.");
                break;
            }
            if (checkMapFull()) {
                System.out.println("Ничья.");
                break;
            }
        }
        System.out.println("Игра окончена.");
    }

    private static boolean checkWin(char xo){
        return checkColumn(xo)||checkLine(xo)||checkDiagonals(xo);
    }

    private static boolean checkLine(char xo){
        sum = false;
        for (int i=0; i<sizeMap;i++){
            sum2 = true;
            for (int j=0; j<sizeMap;j++){
                sum2 &= gameMap[i][j] == xo;
            }
            sum |=sum2;
        }
        return sum;
    }

    private static boolean checkColumn(char xo){
        sum = false;
        for (int j=0; j<sizeMap;j++){
            sum2 = true;
            for (int i=0; i<sizeMap;i++){
                sum2 &= gameMap[i][j] == xo;
            }
            sum |=sum2;
        }
        return sum;
    }

    private static boolean checkDiagonals(char xo){
        sum = sum2 = true;
        for (int i=0; i<sizeMap;i++){
            sum &= gameMap[i][i] == xo;
        }
        for (int i=0; i<sizeMap;i++){
            sum2 &= gameMap[i][sizeMap-i-1]==xo;
        }
        return sum|sum2;
    }

    private static boolean checkMapFull(){
        sum = true;
        for (int i=0; i<sizeMap;i++){
            for (int j=0; j<sizeMap;j++) {
                sum &= gameMap[i][j] == DOT_X || gameMap[i][j] == DOT_O;
            }
        }
        return sum;
    }

    private static void compTurn() {
        x = rand.nextInt(sizeMap);
        y = rand.nextInt(sizeMap);
        if (!(gameMap[x][y]==DOT_X||gameMap[x][y]==DOT_O)){
            System.out.println("Ход компьютера.");
            gameMap[x][y] = DOT_O;
        } else {
            compTurn();
        }
    }

    private static void humTurn() {
        System.out.println("Ход игрока.\n Введите координату вида X Y (где X-строка Y-столбец).");
        x = scan.nextInt()-1;
        y = scan.nextInt()-1;
        if(x>sizeMap||y>sizeMap||x<0||y<0) {
            System.out.println("Будьте внимательны! Ваш ход вне поля =).");
            humTurn();
        } else if(!(gameMap[x][y]==DOT_X||gameMap[x][y]==DOT_O)) {
            gameMap[x][y] = DOT_X;
        } else {
            System.out.println("Будьте внимательны! Ячейка занята.");
            humTurn();
        }
    }

    public static void genMap(){
        for(int i=0; i<sizeMap; i++) {
            for(int j=0;j<sizeMap; j++) {
                gameMap[i][j]=DOT_;
            }
        }
    }

    public static void printMap(){
        for(int k=0;k<sizeMap;k++) {
            System.out.print("\t"+(k+1));
        }
        System.out.println();
        for(int i=0; i<sizeMap; i++) {
            System.out.print((i+1) +"\t");
            for(int j=0;j<sizeMap; j++) {
                System.out.print(gameMap[i][j]+"\t");
            }
            System.out.println();
        }
    }
}