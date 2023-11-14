object Moves {
    fun pawn(piece: Piece): MutableList<List<Int>> {
        println("$piece: ${piece.position}")
        val moves: MutableList<List<Int>> = mutableListOf()
        fun findCapturesPawn(playPiece: Piece) {
            var pieceInPosition = false
            val pp = playPiece.position
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
                            listOf(piece.position[0] +1, piece.position[1] +1 ) -> {
                                foundPieces.add(pieceOnBoard)
                            }
                            listOf(piece.position[0] -1, piece.position[1] +1 ) -> {
                                foundPieces.add(pieceOnBoard)
                            }
                        }
                    }

                }
                else{ //is player 2
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
        findCapturesPawn(piece)
        println(moves)
        return moves
    }
    // ----------------------------------------------------------------------------- \\

    fun getBishop(piece : Piece) : MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = piece.position

        for ((yOff, x) in (pp[0]..8).withIndex()){
            if (pp[1]+yOff > 8 || pp[1]-yOff < 1) break
            moves.add(listOf(x,pp[1]+yOff))
            moves.add(listOf(x,pp[1]-yOff))
        }
        for ((yOff, x) in (pp[0]downTo 1).withIndex()){
            if (pp[1]+yOff > 8 || pp[1]-yOff < 1) break
            moves.add(listOf(x,pp[1]+yOff))
            moves.add(listOf(x,pp[1]-yOff))
        }
        println(moves)
        return moves
    }

}