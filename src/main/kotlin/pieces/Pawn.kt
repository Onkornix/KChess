package pieces

import playerOne
import playerTwo
import whichPlayer
import wholeBoard

class Pawn(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "pawn"
    override var firstMoveUsed = false
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    //scan arbitrary area for pieces that can be captured or will make move illegal

    override fun moves(piece: Piece): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = piece.position // I hate writing piece.position every time

        // I need to figure out how to make this 1 function for both players
        fun playerOne() {

            when (piece.firstMoveUsed) {
                false -> {
                    moves.run {
                        if (listOf(pp[0],pp[1]+1) !in wholeBoard.piecePositions
                            && listOf(pp[0],pp[1]+2) !in wholeBoard.piecePositions) {
                            add(listOf(pp[0], pp[1] + 2))
                        }
                        if (listOf(pp[0],pp[1]+1) !in wholeBoard.piecePositions){
                            add(listOf(pp[0], pp[1] + 1))
                        }
                    }
                    piece.firstMoveUsed = false
                }
                true -> {
                    if (listOf(pp[0], pp[1] + 1) !in wholeBoard.piecePositions)
                    moves.add(listOf(pp[0], pp[1] + 1))
                }
            }
            var pieceInPosition = false
            if (listOf(pp[0]+1,pp[1]+1) in playerTwo.piecePositions ||
                listOf(pp[0]-1,pp[1]+1) in playerTwo.piecePositions) pieceInPosition = true

            if (pieceInPosition){
                val foundPieces: MutableList<Piece> = mutableListOf()
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
                for (p in foundPieces){
                    moves.add(p.position)
                }
            }
        }

        fun playerTwo() {
            when (piece.firstMoveUsed) {
                false -> {
                    moves.run {
                        if (listOf(pp[0],pp[1]-1) !in wholeBoard.piecePositions
                            && listOf(pp[0],pp[1]-2) !in wholeBoard.piecePositions) {
                            add(listOf(pp[0], pp[1] - 2))
                        }
                        if (listOf(pp[0],pp[1]-1) !in wholeBoard.piecePositions){
                            add(listOf(pp[0],pp[1]-1))
                        }
                    }
                    piece.firstMoveUsed = false
                }
                true -> {
                    if (listOf(pp[0], pp[1] - 1) !in wholeBoard.piecePositions)
                        moves.add(listOf(pp[0], pp[1] - 1))
                }
            }

            var pieceInPosition = false
            if (listOf(pp[0]+1,pp[1]-1) in playerOne.piecePositions ||
                listOf(pp[0]-1,pp[1]-1) in playerOne.piecePositions) pieceInPosition = true

            if (pieceInPosition) {
                val foundPieces: MutableList<Piece> = mutableListOf()
                for (pieceOnBoard in playerOne.b) { // checking player 1 board
                    when (pieceOnBoard.position) {
                        listOf(pp[0] + 1, pp[1] - 1) -> {
                            foundPieces.add(pieceOnBoard)
                        }

                        listOf(pp[0] - 1, pp[1] - 1) -> {
                            foundPieces.add(pieceOnBoard)
                        }
                    }
                }
                for (p in foundPieces) {
                    moves.add(p.position)
                }
            }
        }
        /*
        NORMAL MOVES: Y+-1, Y+-2
         */
        if (whichPlayer().p == 1){
            playerOne()
        }else{
            playerTwo()
        }
        return moves
    }

}