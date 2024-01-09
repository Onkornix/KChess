package pieces

import getPlayingPlayerBoard
import getWaitingPlayerBoard
class Rook(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "rook"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()

        fun returnList(xOrY: Int, iter:Int) : List<Int>{
            return when (xOrY) {
                0 -> listOf(position[0],iter)
                1 -> listOf(iter,position[1])
                else -> emptyList()
            }
        }
        fun whenStuff(iter: Int) : Int{
            when {
                //is the check position on this piece's position
                returnList(0,iter) == position -> return 1

                //is the check position on the other player's piece
                getWaitingPlayerBoard().piecePositions.contains(returnList(0,iter)) -> {
                    return 3
                }

                //is the check position on the player's piece
                getPlayingPlayerBoard().piecePositions.contains(returnList(0,iter)) -> {
                    return 2
                }

                else -> return 0
            }
        }
        //
        for (y in position[1]..8) {
            when (whenStuff(y)) {
                0 -> moves.add(listOf(position[0],y))
                1 -> continue
                2 -> break
                3 -> {
                    moves.add(listOf(position[0], y))
                    break
                }
            }
        }
        for (y in position[1]downTo 1) {
            when (whenStuff(y)) {
                0 -> moves.add(listOf(position[0],y))
                1 -> continue
                2 -> break
                3 -> {
                    moves.add(listOf(position[0], y))
                    break
                }
            }
        }
        for (x in position[1]..8) {
            when (whenStuff(x)) {
                0 -> moves.add(listOf(x,position[1]))
                1 -> continue
                2 -> break
                3 -> {
                    moves.add(listOf(x,position[1]))
                    break
                }
            }
        }
        for (x in position[1]downTo 1) {
            when (whenStuff(x)) {
                0 -> moves.add(listOf(x,position[1]))
                1 -> continue
                2 -> break
                3 -> {
                    moves.add(listOf(x,position[1]))
                    break
                }
            }
        }

        return moves
    }
}