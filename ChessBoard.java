import java.util.*;
//Kevin was here
//so was Evan
public class ChessBoard{
    ArrayList<ArrayList<ChessPiece>> board;
    ChessRules obj;
    String move;
    
    public String parseMove(String move) { 
        String col_index = "";
        String row_index = "";
        String col = move.substring(0,1);
        String row = move.substring(1);
        
        if (col.equals("a")) {
            col_index = "1";
        } else if (col.equals("b")) {
            col_index = "2";
        } else if (col.equals("c")) {
            col_index = "3";
        } else if (col.equals("d")) {
            col_index = "4";
        } else if (col.equals("e")) {
            col_index = "5";
        } else if (col.equals("f")) {
            col_index = "6";
        } else if (col.equals("g")) {
            col_index = "7";
        } else if (col.equals("h")) {
            col_index = "8";
        }
        
        if (row.equals("1")) {
            row_index = "7";
        } else if (row.equals("2")) {
            row_index = "6";
        } else if (row.equals("3")) {
            row_index = "5";
        } else if (row.equals("4")) {
            row_index = "4";
        } else if (row.equals("5")) {
            row_index = "3";
        } else if (row.equals("6")) {
            row_index = "2";
        } else if (row.equals("7")) {
            row_index = "1";
        } else if (row.equals("8")) {
            row_index = "0";
        }
        
        return row_index + "," + col_index;
    } // will return the col of the imputted move, ex. a7 will give 1,1
    
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

        // char start = 'a'; //populate bottom letters
        // for(int i = 1; i < 9; i++){

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
            move = obj.nextLine();
        }while(true);
    }
}