package pieces

import oppositeWhichPlayer
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

        fun returnList(num: Int): List<Int> {
            return if (whichPlayer().p == 1){
                listOf(pp[0],pp[1]+num)
            } else {
                listOf(pp[0],pp[1]-num)
            }
        }
        fun returnListCaptures(): List<List<Int>> {
            return if (whichPlayer().p == 1){
                listOf(
                    listOf(pp[0]+1,pp[1]+1),
                    listOf(pp[0]-1,pp[1]+1)
                )
            } else {
                listOf(
                    listOf(pp[0]+1,pp[1]-1),
                    listOf(pp[0]-1,pp[1]-1)
                )
            }
        }

        // I need to figure out how to make this 1 function for both players
        when (piece.firstMoveUsed) {
            false -> {
                moves.run {
                    if (returnList(1) !in wholeBoard.piecePositions
                        && returnList(2) !in wholeBoard.piecePositions) {
                        add(returnList(2))
                    }
                    if (returnList(1) !in wholeBoard.piecePositions){
                        add(returnList(1))
                    }
                }
                //piece.firstMoveUsed = false
            }
            true -> {
                if (returnList(1) !in wholeBoard.piecePositions)
                moves.add(returnList(1))
            }
        }
        var pieceInPosition = false
        var piecePosition: List<Int>
        for (list in returnListCaptures()) {
            if (list in oppositeWhichPlayer().piecePositions) {
                pieceInPosition = true

                println("the pawn in which column would you like to capture the piece? (a-h)")
                val whichColumn: Int = readln().toInt()
            }
        }
        /*if (returnListCaptures() in oppositeWhichPlayer().piecePositions ||
            returnListCaptures() in oppositeWhichPlayer().piecePositions) pieceInPosition = true


         */
        if (pieceInPosition){
            val foundPieces: MutableList<Piece> = mutableListOf()
            for (pieceOnBoard in oppositeWhichPlayer().b){ // checking player 2 board
                when (pieceOnBoard.position){
                    returnList(1) -> {
                        foundPieces.add(pieceOnBoard)
                    }
                    returnList(1) -> {
                        foundPieces.add(pieceOnBoard)
                    }
                }
            }
            for (p in foundPieces){
                moves.add(p.position)
            }
        }
        /*
        NORMAL MOVES: Y+-1, Y+-2
         */
        println(moves)
        return moves
    }

}