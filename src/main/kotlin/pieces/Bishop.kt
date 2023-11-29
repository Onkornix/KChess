package pieces

import wholeBoard

class Bishop(startX:Int, startY:Int, override val player:Int) : Piece() {
    override val type = "bishop"
    override var position: MutableList<Int> = mutableListOf(startX,startY)


    override fun moves(piece: Piece): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = piece.position

        for ((yOffset, coordinateX) in (pp[0]..8).withIndex()){
            if (pp[1]+yOffset > 8 || pp[1]-yOffset < 1) break
            if (listOf(coordinateX,pp[1]+yOffset) !in wholeBoard.piecePositions){
                moves.add(listOf(coordinateX,pp[1]+yOffset))
            }
            if (listOf(coordinateX,pp[1]-yOffset) !in wholeBoard.piecePositions) {
                moves.add(listOf(coordinateX, pp[1] - yOffset))
            }
        }
        for ((yOffset, coordinateX) in (pp[0]downTo 1).withIndex()){
            if (pp[1]+yOffset > 8 || pp[1]-yOffset < 1) break
            moves.add(listOf(coordinateX,pp[1]+yOffset))
            moves.add(listOf(coordinateX,pp[1]-yOffset))
        }
        return moves
    }


}