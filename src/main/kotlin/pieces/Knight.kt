package pieces

import getPlayingPlayerBoard
class Knight(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "knight"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val posX = position[0]
        val posY = position[1]

        // hard coding is so goated.
        moves.run {
            add(listOf(posX-2,posY+1))
            add(listOf(posX-2,posY-1))

            add(listOf(posX+2,posY+1))
            add(listOf(posX+2,posY-1))

            add(listOf(posX-1,posY+2))
            add(listOf(posX+1,posY+2))

            add(listOf(posX-1,posY-2))
            add(listOf(posX+1,posY-2))
        }
        val forRemoval: MutableList<List<Int>> = mutableListOf()
        for (move in moves) {
            when {
                move[0] < 1 || move[0] > 8 || move[1] < 1 || move[1] > 8 -> {
                    forRemoval.add(move)
                    continue
                }
                getPlayingPlayerBoard().piecePositions.contains(move) -> {
                    forRemoval.add(move)
                    continue
                }
            }
        }
        for (move in forRemoval) {
            moves.remove(move)
        }

        return moves
    }
}