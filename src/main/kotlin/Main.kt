
//player 1 pieces (good pieces)
val pawnJerry = Pawn(1,2,1) ; val pawnRick = Pawn(2,2,1) ; val pawnSeymour = Pawn(3,2,1)
val pawnHilary = Pawn(4,2,1) ; val pawnJohan = Pawn(5,2,1) ; val pawnBillie = Pawn(6,2,1)
val pawnSusan =  Pawn(7,2,1) ; val pawnKelly =  Pawn(8,2,1)
val bishopRodger = Bishop(3,4,1) ; val bishopMiranda = Bishop(7,1,1)
val kingGeorge = King(4,1,1)

//player 2 pieces (evil)
val evilPawnJerry = Pawn(2,3,2) ; val evilPawnRick = Pawn(2, 7, 2) ; val evilPawnSeymour = Pawn(3, 7, 2)
val evilBishopRodger = Bishop(4,4,2)

//entire bord
val board = mutableListOf(
    pawnJerry, pawnRick, pawnSeymour, pawnHilary, pawnJohan, pawnBillie, pawnSusan, pawnKelly,

    bishopRodger, bishopMiranda,

    kingGeorge,

    // ----class divide---- \\

    evilPawnJerry, evilPawnRick, evilPawnSeymour,

    evilBishopRodger

)
//player 1 only board
val playerOne = Board(1, mutableListOf(
    pawnJerry, pawnRick, pawnSeymour, pawnHilary, pawnJohan, pawnBillie,
    pawnSusan, pawnKelly,

    bishopRodger, bishopMiranda,

    kingGeorge
))

//player 2 only board
val playerTwo = Board(2, mutableListOf(
    evilPawnJerry, evilPawnRick, evilPawnSeymour,

    evilBishopRodger
))


//find out whose turn
fun whichPlayer(): Board{

    return if (moves % 2 == 0){
        playerOne
    }else{
        playerTwo
    }
}

fun checkCheck(){
    
}

fun move(pieceToMove: String, whereToMove: String){
    if (whereToMove.length > 3 || whereToMove.length < 3){
        return
    }
    fun whereToInt(where: String): MutableList<Int>{
        val letterIntMap = mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8)
        val xpos: Int = letterIntMap[where[0]]!!
        val ypos: Int = where[2].toString().toInt()
        return mutableListOf(xpos,ypos)
    }
    val whereInt: MutableList<Int> = whereToInt(whereToMove)

    //do whichPayer() and iterate only within their board
    for (piece in whichPlayer().b){
        when {
            (piece.type == pieceToMove && pieceToMove == "pawn") -> {
                //if move is in the list of legal moves:
                if (whereInt in piece.moves(piece)) {
                    //println("can move $pieceToMove to $whereToMove")
                    piece.position = whereInt
                    moves++
                    return
                }else {
                    //println("cannot move $pieceToMove to $whereToMove: illegal")
                }
            }
            (piece.type == pieceToMove && pieceToMove == "bishop") -> {
                if (whereInt in piece.moves(piece)) {
                    //println("can move")
                    
                }
            }
        }
    }

}

fun printBoard(){
    val resetColor = "\u001b[0m"
    val emptyIcon = "*"
    fun icon(piece: Piece,type: String) : String{ //player color
        fun pColor(): String {
            return if (piece.player == 1){
                "\u001b[38;5;197m"
            }else{
                "\u001B[38;5;39m"
            }
        }
        return when(type){
            "pawn" -> "${pColor()}P$resetColor"
            "bishop" -> "${pColor()}B$resetColor"
            "king" -> "${pColor()}K$resetColor"
            "queen" -> "${pColor()}Q$resetColor"
            "rook" -> "${pColor()}R$resetColor"
            "knight" -> "${pColor()}K$resetColor"
            else -> "N"
        }
    }
    val xRow: MutableList<String> = mutableListOf()
    for (y in 8 downTo 1){
        xRow.clear()
        for (x in 1 .. 8){
            var hasPiece = false
            for (piece in board){
                if (piece.position == mutableListOf(x,y)){
                    when (piece.type){
                        "pawn" -> xRow.add(icon(piece,"pawn"))
                        "bishop" -> xRow.add(icon(piece,"bishop"))
                        "knight" -> xRow.add(icon(piece,"knight"))
                        "rook" -> xRow.add(icon(piece,"rook"))
                        "queen" -> xRow.add(icon(piece,"queen"))
                        "king" -> xRow.add(icon(piece,"king"))
                    }
                    hasPiece = true
                    break
                }
                // piece
            }
            // x
            if (!hasPiece){
                xRow.add(emptyIcon)
            }
        }
        println(xRow)
    }// y
}


var moves: Int = 0
fun main() {
    printBoard()
    move("pawn", "a_3")
    printBoard()
    //printBoard()
    //var pieceToMove: String = readln()
    //var whereToMove: String = readln()
    //move(pieceToMove.lowercase(), whereToMove.lowercase())

}