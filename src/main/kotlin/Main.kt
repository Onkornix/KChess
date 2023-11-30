import pieces.*

//find out whose turn
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

fun checkCheck(): Boolean {
    /*
    player pieces.King,
    search all check positions for enemy pieces in enemy board
    - if found, break out early to save iterations
    - return something idk yet
     */
    return false
}

fun checkCapture(piece: Piece) {
    //check enemy player board if two pieces are overlapping, remove enemy piece.
    if (piece.position in getWaitingPlayerBoard().piecePositions){
        for (checkPiece in getWaitingPlayerBoard().b){
            if (checkPiece.position == piece.position){
                getWaitingPlayerBoard().b.remove(checkPiece)
                wholeBoard.b.remove(checkPiece)
                break
            }
        }
    }
    wholeBoard.update()
    playerOne.update()
    playerTwo.update()
}
fun changeWhereToInteger(where: String): MutableList<Int>{
    val letterIntMap = mapOf('a' to 1, 'b' to 2, 'c' to 3, 'd' to 4, 'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8)
    val xpos: Int = letterIntMap[where[0]]!!
    val ypos: Int = where[2].toString().toInt()
    return mutableListOf(xpos,ypos)
}
fun move(pieceToMove: String, whereToMove: String){
    val whereToMoveInteger: MutableList<Int> = changeWhereToInteger(whereToMove)
    fun checkMove(piece: Piece) {
        val piecesCanMove: MutableList<Piece> = mutableListOf()

        for (playerPiece in getPlayingPlayerBoard().b) {
            if (playerPiece.type != pieceToMove) break
            if (whereToMoveInteger in playerPiece.moves(piece)) {
                piecesCanMove.add(playerPiece)
            }
        }

        if (piecesCanMove.size > 1) {
            println("More than 1 ${piece.type} can move there. \n Which one should move?")
            for ((iterator,p) in piecesCanMove.withIndex()) {
                println("[$iterator] : ${p.type} at ${p.position}")
            }

        }
/*
        if (whereToMoveInteger in piece.moves(piece)) {
            piece.position = whereToMoveInteger
            piece.firstMoveUsed = true

            wholeBoard.update()
            playerOne.update()
            playerTwo.update()

            checkCapture(piece)
            //moves++



        }else {
            //println("cannot move $pieceToMove to $whereToMove: illegal")
        }
        */
    }

    if (whereToMove.length > 3 || whereToMove.length < 3){
        println("ERROR: please use proper syntax (example: a 3, a_3, a-3")
        return

    }

    for (piece in getPlayingPlayerBoard().b){
        when {
            (piece.type == pieceToMove && pieceToMove == "pawn") -> {
                checkMove(piece)
            }
            (piece.type == pieceToMove && pieceToMove == "bishop") -> {
                checkMove(piece)
            }
        }
    }
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
        println("[${xRow.joinToString("][")}]")
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
        move(pieceToMove, whereToMove)
        printBoard()
        game++
    }
}


//player 1 pieces (good pieces)
val pawnJerry = Pawn(1,2,1); val pawnRick = Pawn(2,2,1) ; val pawnSeymour = Pawn(3,2,1)
val pawnHilary = Pawn(4,2,1) ; val pawnJohan = Pawn(5,2,1) ; val pawnBillie = Pawn(6,2,1)
val pawnSusan =  Pawn(7,2,1) ; val pawnKelly =  Pawn(8,2,1)
val bishopRodger = Bishop(6,4,1) ; val bishopMiranda = Bishop(3,1,1)
val knightTerry = Knight(2,1,1) ; val knightRodrick = Knight(7,1,1)
val rookJohn = Rook(1,1,1) ; val rookLeeroy = Rook(8,1,1)
val kingGeorge = King(4,1,1)
val queenMarika = Queen(5,1,1)


//player 2 pieces (evil)
val evilPawnJerry = Pawn(2,3,2) ; val evilPawnRick = Pawn(2, 7, 2) ; val evilPawnSeymour = Pawn(3, 7, 2)
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
val playerOne = Board(
    1, mutableListOf(
        pawnJerry, pawnRick, pawnSeymour, pawnHilary, pawnJohan, pawnBillie, pawnSusan, pawnKelly,

        bishopRodger, bishopMiranda,

        knightRodrick, knightTerry,

        rookJohn, rookLeeroy,

        kingGeorge, queenMarika
    )
)

//player 2 only board
val playerTwo = Board(
    2, mutableListOf(
        evilPawnJerry, evilPawnRick, evilPawnSeymour,

        evilBishopRodger
    )
)