class Pawn(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "pawn"
    override var firstMoveUsed = false
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    //scan arbitrary area for pieces that can be captured or will make move illegal

    override fun moves(piece: Piece): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = piece.position // I hate writing piece.position every time


        /*
        NORMAL MOVES: Y+-1, Y+-2
         */
        when (piece.player){
            1 ->{
                when (piece.firstMoveUsed) {
                    false -> {
                        if (listOf(pp[0],pp[1]+1) !in wholeBoard.piecePositions){
                            moves.add(listOf(pp[0], pp[1] + 2))
                            moves.add(listOf(pp[0], pp[1] + 1))
                            piece.firstMoveUsed = true
                        }
                    }
                    true -> moves.add(listOf(pp[0], pp[1] + 1))
                }
            }
            2 -> {
                when (piece.firstMoveUsed) {
                    false -> {
                        if (listOf(pp[0],pp[1]-1) !in wholeBoard.piecePositions){
                            moves.add(listOf(pp[0], pp[1] - 2))
                            moves.add(listOf(pp[0], pp[1] - 1))
                            piece.firstMoveUsed = true
                        }
                    }
                    true -> moves.add(listOf(pp[0], pp[1] - 1))
                }
            }
        }


        /*
        FINDING PIECE IN CAPTURE POSITION
         */
        var pieceInPosition = false
        when (whichPlayer().p) {
            1 -> {
                for (victimPiece in playerTwo.b) {
                    if (victimPiece.position == listOf(pp[0]+1,pp[1]+1) // (x+1,y-1)
                        || victimPiece.position == listOf(pp[0]-1,pp[1]+1)) { // (x-1,y-1)
                        pieceInPosition = true
                    }
                }
            }
            2 -> {
                for (victimPiece in playerOne.b) {
                    if (victimPiece.position == listOf(pp[0]+1,pp[1]-1)
                        || victimPiece.position == listOf(pp[0]-1,pp-1)) {
                        pieceInPosition = true
                    }
                }
            }
        }

        if (pieceInPosition){
            val foundPieces: MutableList<Piece> = mutableListOf()
            when (whichPlayer().p) {
                1 -> {
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
                2 -> {
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
            }

            for (p in foundPieces){
                moves.add(p.position)
            }
        }

        println(moves)
        return moves
    }

}