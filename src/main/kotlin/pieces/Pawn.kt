package pieces

import getWaitingPlayerBoard
import getPlayingPlayerBoard
import wholeBoard

class Pawn(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "pawn"
    override var firstMoveUsed = false
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    //scan arbitrary area for pieces that can be captured or will make move illegal

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val piecePos = this.position // I hate writing piece.position every time

        fun returnList(num: Int): List<Int> {
            return if (getPlayingPlayerBoard().p == 1){
                listOf(piecePos[0],piecePos[1]+num)
            } else {
                listOf(piecePos[0],piecePos[1]-num)
            }
        }
        fun returnListCaptures(): List<MutableList<Int>> {
            return if (getPlayingPlayerBoard().p == 1){
                listOf(
                    mutableListOf(piecePos[0]+1,piecePos[1]+1),
                    mutableListOf(piecePos[0]-1,piecePos[1]+1)
                )
            } else {
                listOf(
                    mutableListOf(piecePos[0]+1,piecePos[1]-1),
                    mutableListOf(piecePos[0]-1,piecePos[1]-1)
                )
            }
        }

        // I need to figure out how to make this 1 function for both players
        when (this.firstMoveUsed) {
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
            }
            true -> {
                if (returnList(1) !in wholeBoard.piecePositions)
                moves.add(returnList(1))
            }
        }
        var pieceInPosition = false
        var piecePosition: MutableList<Int> = mutableListOf()
        for (list in returnListCaptures()) {
            if (list in getWaitingPlayerBoard().piecePositions) {
                pieceInPosition = true
                piecePosition = list

                //println("the pawn in which column would you like to capture the piece? (a-h)")
                //val whichColumn: Int = readln().toInt()
            }
        }
        if (pieceInPosition){
            for (pieceOnBoard in getWaitingPlayerBoard().b){ // checking player 2 board
                if (pieceOnBoard.position == piecePosition) {
                    moves.add(piecePosition)
                }
            }
        }

        return moves
    }

}