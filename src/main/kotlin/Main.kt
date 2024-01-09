import pieces.*


fun getPlayingPlayerBoard(): Board {

    return if (moves % 2 == 0){
        playerOne
    }else{
        playerTwo
    }
}

fun getWaitingPlayerBoard() : Board {
    return if (moves % 2 == 1){
        playerOne
    }else{
        playerTwo
    }
}

fun getKing() : Piece {
    for (playingPlayerPiece in getPlayingPlayerBoard().b) {
        if (playingPlayerPiece.type == "king") {
            return playingPlayerPiece
        }
    }
    //fallback I guess but this will never happen because the king is always on the board
    return kingGeorge
}
fun checkMove(piece: Piece, whereToMoveInteger: List<Int>, pieceToMove: String): Boolean {


    val piecesCanMove: MutableList<Piece> = mutableListOf()
    // This is embarrassing
    val moveMutableList: MutableList<Int> = mutableListOf(whereToMoveInteger[0],whereToMoveInteger[1])



    //check if multiple pieces can move to the same place
    for (playerPiece in getPlayingPlayerBoard().b) {
        val pieceMoves = playerPiece.moves()

        if (playerPiece.type != pieceToMove) continue
        if (pieceMoves.contains(whereToMoveInteger)) piecesCanMove.add(playerPiece)
    }
    // choose which piece moves
    if (piecesCanMove.size > 1) {
        println("More than 1 ${piece.type} can move there. \n" +
                "Which one should move?")
        for ((iterator,p) in piecesCanMove.withIndex()) {
            println("[${iterator+1}] : ${p.type} at ${p.position}")
        }

        piecesCanMove[readln().toInt() - 1].position = moveMutableList
        return true
    }

    // find king and check if it's in check before checking piece moves
    if (piece.type != "king") {
        for (playingPlayerPiece in getPlayingPlayerBoard().b) {
            if (playingPlayerPiece.type == "king") {
                if (playingPlayerPiece.isInCheck()) {
                    println("whoops! you're in check, buckaroo")
                    return false
                }
                break
            }
        }
    }

    if (piece.moves().contains(whereToMoveInteger)) {
        val fallbackPosition = piece.position
        piece.position = moveMutableList

        if (getKing().isInCheck()) {
            println("Think again. That'll put you in check :(")
            piece.position = fallbackPosition
            return false
        }

        return true
    }

    return false
}
fun move(pieceToMove: String, whereToMove: String): Boolean{

    when {
        whereToMove.length > 3 || whereToMove.length < 3 -> {
        println("ERROR: incorrect move syntax (example: a 3, a_3, a-3)")
        return false
        }
        whereToMove[0] !in ('a'..'h') -> {
            println("ERROR: x coord not in scope (a..h)")
            return false
        }
        whereToMove[2].toString().toInt() > 8 || whereToMove[2].toString().toInt() < 1 -> {
            println("ERROR: y coord out of scope. (1..8)")
            return false
        }
    }

    val letterIntMap = mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8)
    val xpos: Int = letterIntMap[whereToMove[0]]!!
    val ypos: Int = whereToMove[2].toString().toInt()

    val whereToMoveInteger: List<Int> = listOf(xpos,ypos)

    if (!listOf("pawn","bishop","rook","knight","king","queen").contains(pieceToMove.lowercase())) {
        println("$pieceToMove is not a valid piece. check spelling, remove whitespaces, and try again")
        return false
    }



    for (piece in getPlayingPlayerBoard().b){
        if (piece.type == pieceToMove) {
            if (checkMove(piece,whereToMoveInteger,pieceToMove)) {
                wholeBoard.update()
                playerTwo.update()
                playerOne.update()
                return true
            } else {
                return false
            }
        }
    }
    println("cannot move $pieceToMove to $whereToMove")
    return false
}

fun printBoard(){
    val resetColor = "\u001b[0m"
    val emptyIcon = "*"
    fun icon(piece: Piece, type: String) : String{ //player color
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
            "knight" -> "${pColor()}k$resetColor"
            else -> "N"
        }
    }
    val xRow: MutableList<String> = mutableListOf()
    println("   a  b  c  d  e  f  g  h")
    for (y in 8 downTo 1){
        xRow.clear()
        print("$y ")
        for (x in 1 .. 8){
            var hasPiece = false
            for (piece in wholeBoard.b){
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
        println("[${xRow.joinToString("][")}] $y")
    }// y
    println("   a  b  c  d  e  f  g  h")
}


var moves: Int = 0
fun main() {

    var game = 0
    printBoard()
    while (game < 2){
        print("piece: ")
        val pieceToMove = readln()
        print("move to: ")
        val whereToMove = readln()
        if (!move(pieceToMove, whereToMove)){
            //println("failed to move piece")
            continue
        }

        printBoard()
        game++
    }
}


//player 1 pieces (good pieces)
val pawnJerry = Pawn(2,3,1); val pawnRick = Pawn(2,2,1) ; val pawnSeymour = Pawn(3,2,1)
val pawnHilary = Pawn(4,2,1) ; val pawnJohan = Pawn(5,5,1) ; val pawnBillie = Pawn(6,2,1)
val pawnSusan =  Pawn(7,2,1) ; val pawnKelly =  Pawn(8,2,1)
val bishopRodger = Bishop(5,7,1) ; val bishopMiranda = Bishop(3,1,1)
val knightTerry = Knight(2,1,1) ; val knightRodrick = Knight(7,1,1)
val rookJohn = Rook(1,1,1) ; val rookLeeroy = Rook(8,1,1)
val kingGeorge = King(3,5,1)
val queenMarika = Queen(7,5,1)


//player 2 pieces (evil)
val evilPawnJerry = Pawn(2,7,2) ; val evilPawnRick = Pawn(2, 7, 2) ; val evilPawnSeymour = Pawn(3, 7, 2)
val evilBishopRodger = Bishop(4,4,2)

//entire bord
val wholeBoard = Board(
    0, mutableListOf(
        pawnJerry, pawnRick, pawnSeymour, pawnHilary, pawnJohan, pawnBillie, pawnSusan, pawnKelly,

        bishopRodger, bishopMiranda,

        knightRodrick, knightTerry,

        rookJohn, rookLeeroy,

        kingGeorge, queenMarika,

        // ----class divide---- \\

        evilPawnJerry, evilPawnRick, evilPawnSeymour,

        evilBishopRodger

    )
)
//player 1 only board
val playerOne = Board(1, mutableListOf(
        pawnJerry, pawnRick, pawnSeymour, pawnHilary, pawnJohan, pawnBillie, pawnSusan, pawnKelly,

        bishopRodger, bishopMiranda,

        knightRodrick, knightTerry,

        rookJohn, rookLeeroy,

        kingGeorge, queenMarika
    )
)

//player 2 only board
val playerTwo = Board(2, mutableListOf(
        evilPawnJerry, evilPawnRick, evilPawnSeymour,

        evilBishopRodger
    )
)