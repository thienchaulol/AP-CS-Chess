import java.util.*;
//Kevin was here
//so was Evan
public class ChessBoard{
    ArrayList<ArrayList<ChessPiece>> board;
    ChessRules obj;
    String move;
    String lastMove;

    public void coolPrint(String str){
        for(int i = 0; i < str.length(); i++){
            try{
                System.out.print(str.substring(i, i+1));
                //Thread.sleep(50);
            } catch(Exception e){

            }
        }
        System.out.println();
    }

    public void printBoard(){
        System.out.println("    a    b    c   d    e    f   g    h");
        System.out.println("  ----------------------------------------");
        int count = 8;
        for (int i = 0; i < 8; i++) {
            System.out.print(count + " ");
            System.out.print("| ");
            for (int j = 1; j < 9; j++) {
                if (board.get(i).get(j).getPieceName().equals("☐")) {
                    if(j == 0 || j == 5 || j == 7) System.out.print("  | ");
                    else System.out.print("  |  ");
                } else {
                    System.out.print(board.get(i).get(j).getPieceName() + " | ");
                }
            }
            System.out.print(count);
            count--;
            System.out.println();
            System.out.println("  ----------------------------------------");
        }
        System.out.println("    a    b    c   d    e    f   g    h");
        System.out.println();
    }

    public void createStartingBoard(){
        for(int i = 0; i < 9; i++){
            ArrayList<ChessPiece> x = new ArrayList<ChessPiece>();
            for(int j = 0; j < 9; j++){
                x.add(new ChessPiece("☐", false));
            }
            board.add(x);
        }

        char start = 'a'; //populate bottom letters
        for(int i = 1; i < 9; i++){

            board.get(8).set(i, new ChessPiece(String.valueOf(start++), false));
        }

        // board.get(8).set(i, new ChessPiece(String.valueOf(start++), false));
        // }

        // for(int i = 1; i < 9; i++){
        // board.get(8-i).set(0, new ChessPiece(String.valueOf((char)(i+'0')),false));
        // }

        //Row 1 and Row 6 are 'P's (pawns)
        for(int i = 1; i < 9; i++){
            board.get(1).set(i, new ChessPiece("♟", true));
            board.get(6).set(i, new ChessPiece("♙", true));
        }

        //Corners are Rooks
        board.get(0).set(1, new ChessPiece("♜", true));
        board.get(0).set(8, new ChessPiece("♜", true));
        board.get(7).set(1, new ChessPiece("♖", false));
        board.get(7).set(8, new ChessPiece("♖", false));
        //Next to corners are Knights
        board.get(0).set(2, new ChessPiece("♞", true));
        board.get(0).set(7, new ChessPiece("♞", true));
        board.get(7).set(2, new ChessPiece("♘", false));
        board.get(7).set(7, new ChessPiece("♘", false));
        //Next to Knights are Bishops
        board.get(0).set(3, new ChessPiece("♝", false));
        board.get(0).set(6, new ChessPiece("♝", false));
        board.get(7).set(3, new ChessPiece("♗", false));
        board.get(7).set(6, new ChessPiece("♗", false));
        //White will always be bottom, black on top
        board.get(0).set(4, new ChessPiece("♛", false));
        board.get(0).set(5, new ChessPiece("♚", false));
        board.get(7).set(4, new ChessPiece("♕", false));
        board.get(7).set(5, new ChessPiece("♔", false));
    }

    public void main(){
        board = new ArrayList();

        createStartingBoard();
        printBoard();

        //obj = new ChessRules(board);
        playGame();
    }
    
    public void movePiece(ArrayList<ArrayList<ChessPiece>> board, String move) {
        // first check if possible
        String start = move.substring(0,2);
        String end = move.substring(2);
        
        String start_row_str = parseMove(start).substring(0,1);
        String start_col_str = parseMove(start).substring(2);
        String end_row_str = parseMove(end).substring(0,1);
        String end_col_str = parseMove(end).substring(2);
        int start_row = Integer.parseInt(start_row_str);
        int start_col = Integer.parseInt(start_col_str);
        int end_row = Integer.parseInt(end_row_str);
        int end_col = Integer.parseInt(end_col_str);
        
        ChessPiece temp = board.get(end_row).get(end_col);
        
        board.get(end_row).set(end_col, board.get(start_row).get(start_col));
        board.get(start_row).set(start_col, temp);
    }

    public void playGame(){
        do{
            System.out.println("Please input your chess move :)!");
            Scanner obj = new Scanner(System.in);
            lastMove = move;
            move = obj.nextLine();
        }while(true);
    }

    public boolean CheckValidMove(String startBox, String endBox){
        int startI = Integer.parseInt(startBox.substring(0,1));
        int startJ = Integer.parseInt(startBox.substring(2));
        int endI = Integer.parseInt(endBox.substring(0, 1));
        int endJ = Integer.parseInt(endBox.substring(2));
        if(startI < 1 || startI > 8) return false;
        else if(startJ < 1 || startJ > 8) return false;
        else if(endI < 1 || endI > 8) return false;
        else if(endJ < 1 || endJ > 8) return false;
        if(board.get(startI).get(startJ).name.equals("♟") || board.get(startI).get(startJ).name.equals("♙")){
            if((board.get(startI)).get(startJ).isB){
                if(startJ == endJ){
                    if((board.get(startI)).get(startJ).isB){
                        if(startI == endI -1){
                            if(board.get(endI).get(endJ).name.equals("☐")){// change to whatever is the new board empty character
                                return true;
                            }
                        } else if (startI == endI - 2){
                            if(board.get(endI).get(endJ).name.equals("☐")){
                                return true;
                            }
                        }
                    }
                } else if(startJ == endJ + 1 || startJ == endJ - 1){
                    if(startI == endI - 1){
                        if(!board.get(endI).get(endJ).name.equals("☐") && !board.get(endI).get(endJ).isB){
                            return true;
                        }// else if(parseMove(lastMove.substring(0,1)));
                    }
                }
            }
            return false;

        } else if(board.get(startI).get(startJ).name.equals("♜") || board.get(startI).get(startJ).name.equals("♖")){

        }
        return true;
    }
}