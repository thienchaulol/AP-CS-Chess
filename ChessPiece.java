public class ChessPiece{
    public String name;
    public boolean isB;
    public boolean hasMoved = false;
    public ChessPiece(String n, boolean val){
        this.name = n;
        this.isB = val;
    }
    
    public String getPieceName(){
        return name;
    }
}