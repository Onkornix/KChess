
//Pawns
val pawnJerry    =   Pawn()
val pawnRick     =   Pawn()
val pawnSeymour  =   Pawn()

//Bishops
val bishopRodger = Bishop()

val board = mutableListOf(
    pawnJerry,
    pawnRick,
    pawnSeymour,
    bishopRodger
)
fun setBoard(){
    mutableListOf(1,1).also { pawnJerry.position = it }
    mutableListOf(2,1).also { pawnRick.position = it}
    mutableListOf(3,1).also { pawnSeymour.position = it }

    mutableListOf(3,2).also { bishopRodger.position = it}
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
    //check for first move privilages
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
    println("boshoping omg")
    return mutableListOf(listOf(1))
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
        when (pieceToMove) {
            "pawn" -> {
                //if move is in the list of legal moves:
                if (whereInt in getMovesPawn(piece)) {
                    println("can move $pieceToMove to $whereToMove")
                    piece.position = whereInt
                    return
                }
            }
            "bishop" -> {
                if (whereInt in getMovesBishop(piece)) {

                }
            }
        }
    }


}


fun main() {
    setBoard()

    var pieceToMove: String = readln()
    var whereToMove: String = readln()
    move(pieceToMove.lowercase(), whereToMove.lowercase())
}