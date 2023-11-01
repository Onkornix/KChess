
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
    mutableListOf(1,2).also { pawnJerry.position = it }
    mutableListOf(2,3).also { pawnRick.position = it}
    mutableListOf(3,2).also { pawnSeymour.position = it }
    //bishopRodger.setPosition(1,1)
}

fun getMovesPawn(piece: Piece): MutableList<List<Int>> {
    val moves: MutableList<List<Int>> = mutableListOf()

    when {
        //first move used
        !piece.firstMoveUsed -> moves.add(listOf(piece.position[0], piece.position[1] +2))
        piece.firstMoveUsed -> moves.add(listOf(piece.position[0], piece.position[1] +1))

        //piece found within capture positions
        pieceInCapturePositionPawn(piece) -> {
            for (capture in returnCapturesPawn(piece)){
                moves.add(capture)
            }
        }
    }
    return moves
}

fun pieceInCapturePositionPawn(playPiece: Piece): Boolean {
    var victim = false
    for (victimPiece in board) {
        if ((victimPiece.position[0] == (playPiece.position[0] + 1))
            && (victimPiece.position[1] == (playPiece.position[1] + 1))
        ){
            victim = true
        }
    }
    return victim
}
fun returnCapturesPawn(piece: Piece): List<List<Int>> {
    val foundPieces: MutableList<Piece> = mutableListOf()
    for (pieceOnBoard in board){
        when (pieceOnBoard.position){
            listOf(piece.position[0] +1, piece.position[1] +1 ) -> foundPieces.add(pieceOnBoard)
            listOf(piece.position[0] -1, piece.position[1] +1 ) -> foundPieces.add(pieceOnBoard)
        }
    }


    return emptyList()
}


fun move(what: String, where: String){
    if (where.length > 3 || where.length < 3){
        return Unit
    }
    fun whereToInt(where: String): List<Int>{
        val letterIntMap = mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8)
        val xpos: Int = letterIntMap[where[0]]!!
        val ypos: Int = where[2].toString().toInt()
        return listOf(xpos,ypos)
    }
    /*
    get piece type
    get final location

    check piece with move: if legal: do, else: move on
     */
    val whereInt = whereToInt(where)


    for (piece in board){
        when (piece.type){
            "pawn" -> {
                //if move is in the list of legal moves:
                if (whereInt in getMovesPawn(piece)){

                }


            }


        }
    }

}


fun main() {
    setBoard()

    move("pawn","a_3")
}