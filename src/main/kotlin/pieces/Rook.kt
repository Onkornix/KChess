package pieces

import getPlayingPlayerBoard
import getWaitingPlayerBoard
class Rook(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "rook"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()

        for (y in 1..8) {
            when {
                listOf(position[0],y) == position -> break
                getWaitingPlayerBoard().piecePositions.contains(listOf()) -> {
                    moves.add(listOf(position[0],y))
                    break
                }
                getPlayingPlayerBoard().piecePositions.contains(listOf(position[0],y)) -> {
                    break
                }
            }
            moves.add(listOf(position[0],y))
        }
        for (x in 1..8) {
            when {
                listOf(x,position[1]) == position -> break
                getWaitingPlayerBoard().piecePositions.contains(listOf()) -> {
                    moves.add(listOf(x,position[1]))
                    break
                }
                getPlayingPlayerBoard().piecePositions.contains(listOf(x,position[1])) -> {
                    break
                }
            }
            moves.add(listOf(x,position[1]))
        }
        println(moves)
        return moves
    }
}