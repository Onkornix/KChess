

val pawnJerry    =   Pawn(1,2,1)
val pawnRick     =   Pawn(2,7,1)
val pawnSeymour  =   Pawn(3,2,1)
val pawnHilary   =   Pawn(4,2,1)
val pawnJohan   =   Pawn(5,2,1)
val pawnBillie =  Pawn(6,2,1)
val pawnSusan =  Pawn(7,2,1)
val pawnKelly =  Pawn(8,2,1)

val bishopRodger = Bishop(2,1,1)
val bishopMiranda = Bishop(7,1,1)


val evilPawnJerry = Pawn(1,8,2)

val board = mutableListOf(
    pawnJerry, pawnRick, pawnSeymour,
    pawnHilary, pawnJohan, pawnBillie,
    pawnSusan, pawnKelly,

    bishopRodger, bishopMiranda,

    evilPawnJerry


)
val playerOne = Board(1, mutableListOf(
    pawnJerry, pawnRick, pawnSeymour,
    pawnHilary, pawnJohan, pawnBillie,
    pawnSusan, pawnKelly,

    bishopRodger, bishopMiranda
))
val playerTwo = Board(2, mutableListOf(
    evilPawnJerry
))

fun whichPlayer(): Board{
    //find out whose turn
    return if (moves % 2 == 0){
        playerOne
    }else{
        playerTwo
    }
}

fun getMovesPawn(piece: Piece): MutableList<List<Int>> {
    println("$piece: ${piece.position}")
    fun pieceInCapturePositionPawn(playPiece: Piece): Boolean {
        if (whichPlayer().p == 1) { // is player 1
            for (victimPiece in playerTwo.b) {
                if ((victimPiece.position[0] == (playPiece.position[0] + 1))
                    && (victimPiece.position[1] == (playPiece.position[1] + 1))
                ) {
                    return true
                }
            }
            return false
        }else{ // is player 2
            for (victimPiece in playerOne.b) {
                if ((victimPiece.position[0] == (playPiece.position[0] - 1))
                    && (victimPiece.position[1] == (playPiece.position[1] - 1))
                ) {
                    return true
                }
            }
            return false
        }

    }
    fun returnCapturesPawn(piece: Piece): MutableList<Piece> {
        val foundPieces: MutableList<Piece> = mutableListOf()

        if (whichPlayer().p == 1){ //is player 1
            for (pieceOnBoard in playerTwo.b){ // checking player 2 board
                when (pieceOnBoard.position){
                    listOf(piece.position[0] +1, piece.position[1] +1 ) -> {
                        foundPieces.add(pieceOnBoard)
                    }
                    listOf(piece.position[0] -1, piece.position[1] +1 ) -> {
                        foundPieces.add(pieceOnBoard)
                    }
                }
            }
            return foundPieces
        }else{ //is player 2
            for (pieceOnBoard in playerOne.b){ // checking player 1 board
                when (pieceOnBoard.position){
                    listOf(piece.position[0] +1, piece.position[1] -1 ) -> {
                        if (piece.player == 1) foundPieces.add(pieceOnBoard)
                    }
                    listOf(piece.position[0] -1, piece.position[1] -1 ) -> {
                        if (piece.player == 1) foundPieces.add(pieceOnBoard)
                    }
                }
            }
            return foundPieces
        }
    }
    val moves: MutableList<List<Int>> = mutableListOf()

    //check for first move privileges
    if (piece.player == 1) {
        when (piece.firstMoveUsed) {
            false -> {
                var isPieceInFont = false
                for (pieceCheckInFont in board){ // looking for piece directly in front
                    if (pieceCheckInFont.position == listOf(piece.position[0],piece.position[1]+1)) {
                        isPieceInFont = true
                    }
                }
                if (!isPieceInFont) {
                    moves.add(listOf(piece.position[0], piece.position[1] + 2))
                    moves.add(listOf(piece.position[0], piece.position[1] + 1))
                    piece.firstMoveUsed = true
                }
            }
            true -> moves.add(listOf(piece.position[0], piece.position[1] + 1))
        }
    }else{
        when (piece.firstMoveUsed) {
            false -> {
                var isPieceInFont = false
                for (pieceCheckInFont in board){ // looking for piece directly in front
                    if (pieceCheckInFont.position == listOf(piece.position[0],piece.position[1]-1)) {
                        isPieceInFont = true
                    }
                }
                if (!isPieceInFont) {
                    moves.add(listOf(piece.position[0], piece.position[1] - 2))
                    moves.add(listOf(piece.position[0], piece.position[1] - 1))
                    piece.firstMoveUsed = true
                }
            }
            true -> moves.add(listOf(piece.position[0], piece.position[1] - 1))
        }
    }


    //piece found within capture positions
    if (pieceInCapturePositionPawn(piece)) {
        for (capture in returnCapturesPawn(piece)) {
            moves.add(capture.position)

        }

    }
    println(moves)
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

    //do whichPayer() and iterate only within their board
    for (piece in whichPlayer().b){
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
    val resetColor = "\u001b[0m"
    val p1color = "\u001b[38;5;197m"
    val p2color = "\u001B[38;5;39m"
    val emptyIcon = "*"
    fun icon(piece: Piece,type: String) : String{ //player color
        when(type){
            "pawn" ->{
                return if (piece.player == 1){
                    "${p1color}P${resetColor}"
                }else{
                    "${p2color}P${resetColor}"
                }
            }
            "bishop" -> {
                return if (piece.player == 1){
                    "${p1color}B${resetColor}"
                }else{
                    "${p2color}B${resetColor}"
                }
            }
            else -> return "N"
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


var moves: Int = 1
fun main() {
    printBoard()
    move("pawn", "b_7")
    printBoard()
    //var pieceToMove: String = readln()
    //var whereToMove: String = readln()
    //move(pieceToMove.lowercase(), whereToMove.lowercase())

}