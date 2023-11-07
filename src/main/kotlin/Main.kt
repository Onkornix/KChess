var moves: Int = 0

val pawnJerry    =   Pawn(1,2)
val pawnRick     =   Pawn(2,2)
val pawnSeymour  =   Pawn(3,2)
val pawnHilary   =   Pawn(4,2)
val pawnJohan   =   Pawn(5,2)
val pawnBillie =  Pawn(6,2)
val pawnSusan =  Pawn(7,2)
val pawnKelly =  Pawn(8,2)

val bishopRodger = Bishop(2,1)
val bishopMiranda = Bishop(7,1)

val board = mutableListOf(
    pawnJerry, pawnRick, pawnSeymour,
    pawnHilary, pawnJohan, pawnBillie,
    pawnSusan, pawnKelly,

    bishopRodger, bishopMiranda,
)
val playerOne = Board(1, mutableListOf(
    pawnJerry, pawnRick, pawnSeymour,
    pawnHilary, pawnJohan, pawnBillie,
    pawnSusan, pawnKelly,
))
val playerTwo = Board(2, mutableListOf())

fun whichPlayer(): Board{
    //find out whose turn
    return if (moves % 2 != 0){
        playerOne
    }else{
        playerTwo
    }

}

fun getMovesPawn(piece: Piece): MutableList<List<Int>> {
    fun pieceInCapturePositionPawn(playPiece: Piece): Boolean {
        for (victimPiece in board) {
            if ((victimPiece.position[0] == (playPiece.position[0] + 1))
                && (victimPiece.position[1] == (playPiece.position[1] + 1))
            ){
                return true
            }
        }
        return false
    }
    fun returnCapturesPawn(piece: Piece): MutableList<Piece> {
        val foundPieces: MutableList<Piece> = mutableListOf()
        for (pieceOnBoard in board){
            when (pieceOnBoard.position){
                listOf(piece.position[0] +1, piece.position[1] +1 ) -> foundPieces.add(pieceOnBoard)
                listOf(piece.position[0] -1, piece.position[1] +1 ) -> foundPieces.add(pieceOnBoard)
            }
        }
        return foundPieces
    }
    val moves: MutableList<List<Int>> = mutableListOf()

    //check for first move privileges
    when (piece.firstMoveUsed) {
        false -> {
            moves.add(listOf(piece.position[0], piece.position[1] + 2))
            piece.firstMoveUsed = true
        }
        true -> moves.add(listOf(piece.position[0], piece.position[1] + 1)) // is true
    }
    //piece found within capture positions
    if (pieceInCapturePositionPawn(piece)) {
        for (capture in returnCapturesPawn(piece)) {
            moves.add(capture.position)
        }
    }
    return moves
}

fun getMovesBishop(piece: Piece): MutableList<List<Int>> {
    return mutableListOf()
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


    for (piece in board){

        when {
            (piece.type == pieceToMove && pieceToMove == "pawn") -> {
                //if move is in the list of legal moves:
                if (whereInt in getMovesPawn(piece)) {
                    println("can move $pieceToMove to $whereToMove")
                    piece.position = whereInt
                    moves++
                    return
                }else {
                    println("cannot move $pieceToMove to $whereToMove: illegal")
                }
            }
            (piece.type == pieceToMove && pieceToMove == "bishop") -> {
                if (whereInt in getMovesBishop(piece)) {
                    println("can move")
                    
                }
            }
        }
    }


}

fun printBoard(){
    val red = "\u001b[38;5;197m"
    val resetColor = "\u001b[0m"
    val pawnIcon = "${red}P${resetColor}"
    val bishopIcon = "B"
    val emptyIcon = "*"


    val xRow: MutableList<String> = mutableListOf()
    for (y in 1..8){
        xRow.clear()
        for (x in 1..8){
            var hasPiece = false
            for (piece in board){
                if (piece.position == mutableListOf(x,y)){
                    when (piece.type){
                        "pawn" -> xRow.add(pawnIcon)
                        "bishop" -> xRow.add(bishopIcon)
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
fun main() {
    //move("bishop", "d_3")
    //var pieceToMove: String = readln()
    //var whereToMove: String = readln()
    //move(pieceToMove.lowercase(), whereToMove.lowercase())
    //printBoard()
}