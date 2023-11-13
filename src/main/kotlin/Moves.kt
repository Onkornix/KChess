object Moves {
    fun pawn(piece: Piece): MutableList<List<Int>> {
        println("$piece: ${piece.position}")
        fun pieceInCapturePositionPawn(playPiece: Piece): Boolean {
            if (whichPlayer().p == 1) { // is player 1
                for (victimPiece in playerTwo.b) {
                    if ((victimPiece.position[0] == (playPiece.position[0] + 1))
                        && (victimPiece.position[1] == (playPiece.position[1] + 1))) {
                        return true
                    }
                }
                return false
            }else{ // is player 2
                for (victimPiece in playerOne.b) {
                    if (victimPiece.position == listOf(playPiece.position[0]+1,playPiece.position[1]-1)
                        || victimPiece.position == listOf(playPiece.position[0]+1,playPiece.position[1]-1)) {
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
                            foundPieces.add(pieceOnBoard)
                        }
                        listOf(piece.position[0] -1, piece.position[1] -1 ) -> {
                            foundPieces.add(pieceOnBoard)
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
    // ----------------------------------------------------------------------------- \\

    fun getBishop(piece : Piece) : MutableList<List<Int>> {
        val moves: MutableList<Int> = mutableListOf()


        return mutableListOf()
    }

}