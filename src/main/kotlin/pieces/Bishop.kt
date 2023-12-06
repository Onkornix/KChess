package pieces


import getPlayingPlayerBoard

class Bishop(startX:Int, startY:Int, override val player:Int) : Piece() {
    override val type = "bishop"
    override var position: MutableList<Int> = mutableListOf(startX,startY)


    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = this.position

        for ((yOffset, coordinateX) in (pp[0]..8).withIndex()){

            if (listOf(coordinateX,pp[1]+yOffset) !in getPlayingPlayerBoard().piecePositions){
                moves.add(listOf(coordinateX,pp[1]+yOffset))
            }
            if (listOf(coordinateX,pp[1]-yOffset) !in getPlayingPlayerBoard().piecePositions) {
                moves.add(listOf(coordinateX, pp[1] - yOffset))
            }
        }
        for ((yOffset, coordinateX) in (pp[0]downTo 1).withIndex()){

            if (listOf(coordinateX,pp[1]+yOffset) !in getPlayingPlayerBoard().piecePositions){
                moves.add(listOf(coordinateX,pp[1]+yOffset))
            }
            if (listOf(coordinateX,pp[1]-yOffset) !in getPlayingPlayerBoard().piecePositions) {
                moves.add(listOf(coordinateX, pp[1] - yOffset))
            }
        }
        return moves
    }


}