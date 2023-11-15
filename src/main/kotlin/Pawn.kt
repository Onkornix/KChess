/*
        can move y+1 unless firstMovedUsed = false, then move y+2,
        or a piece is within (x+/-1,y+/-1) then move (x+/-1,y+/-1)
        and capture piece
         */
class Pawn(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "pawn"
    override var firstMoveUsed = false
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    //scan arbitrary area for pieces that can be captured or will make move illegal

    override fun moves(piece: Piece): MutableList<List<Int>> {
        println("$piece: ${piece.position}")
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = piece.position // I hate writing piece.position every time

        fun findCapturesPawn() {
            var pieceInPosition = false

            if (whichPlayer().p == 1) { // is player 1, will search player 2 board
                for (victimPiece in playerTwo.b) {
                    if (victimPiece.position == listOf(pp[0]+1,pp[1]+1) // (x+1,y-1)
                        || victimPiece.position == listOf(pp[0]-1,pp[1]+1)) { // (x-1,y-1)
                        pieceInPosition = true
                    }
                }
            }
            else{ // is player 2, will search player 1 board
                for (victimPiece in playerOne.b) {
                    if (victimPiece.position == listOf(pp[0]+1,pp[1]-1)
                        || victimPiece.position == listOf(pp[0]-1,pp-1)) {
                        pieceInPosition = true
                    }
                }
            }
            if (pieceInPosition){
                val foundPieces: MutableList<Piece> = mutableListOf()


                if (whichPlayer().p == 1){ //is player 1
                    for (pieceOnBoard in playerTwo.b){ // checking player 2 board
                        when (pieceOnBoard.position){
                            listOf(pp[0] +1, pp[1] +1 ) -> {
                                foundPieces.add(pieceOnBoard)
                            }
                            listOf(pp[0] -1, pp[1] +1 ) -> {
                                foundPieces.add(pieceOnBoard)
                            }
                        }
                    }

                }
                else{ //is player 2
                    for (pieceOnBoard in playerOne.b){ // checking player 1 board
                        when (pieceOnBoard.position){
                            listOf(pp[0] +1, pp[1] -1 ) -> {
                                foundPieces.add(pieceOnBoard)
                            }
                            listOf(pp[0] -1, pp[1] -1 ) -> {
                                foundPieces.add(pieceOnBoard)
                            }
                        }
                    }
                }
                for (p in foundPieces){
                    moves.add(p.position)
                }
            }
        }


        //check for first move privileges
        if (piece.player == 1) {
            when (piece.firstMoveUsed) {
                false -> {
                    var isPieceInFont = false
                    for (pieceCheckInFont in board){ // looking for piece directly in front
                        if (pieceCheckInFont.position == listOf(pp[0],pp[1]+1)) {
                            isPieceInFont = true
                        }
                    }
                    if (!isPieceInFont) {
                        moves.add(listOf(pp[0], pp[1] + 2))
                        moves.add(listOf(pp[0], pp[1] + 1))
                        piece.firstMoveUsed = true
                    }
                }
                true -> moves.add(listOf(pp[0], pp[1] + 1))
            }
        }else{
            when (piece.firstMoveUsed) {
                false -> {
                    var isPieceInFont = false
                    for (pieceCheckInFont in board){ // looking for piece directly in front
                        if (pieceCheckInFont.position == listOf(pp[0],pp[1]-1)) {
                            isPieceInFont = true
                        }
                    }
                    if (!isPieceInFont) {
                        moves.add(listOf(pp[0], pp[1] - 2))
                        moves.add(listOf(pp[0], pp[1] - 1))
                        piece.firstMoveUsed = true
                    }
                }
                true -> moves.add(listOf(pp[0], pp[1] - 1))
            }
        }
        findCapturesPawn()
        println(moves)
        return moves
    }

}