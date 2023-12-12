package pieces


import getPlayingPlayerBoard

class Bishop(startX:Int, startY:Int, override val player:Int) : Piece() {
    override val type = "bishop"
    override var position: MutableList<Int> = mutableListOf(startX,startY)


    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = this.position

        var upInterupt = false
        var downInterup = false
        for ((yOffset, coordinateX) in (pp[0]..8).withIndex()){
            if (yOffset == 0) continue
            when {
                pp[1]+yOffset > 8 -> break
                pp[1]-yOffset < 1 -> break
            }
            if (listOf(coordinateX,pp[1]+yOffset) !in getPlayingPlayerBoard().piecePositions && !upInterupt){
                moves.add(listOf(coordinateX,pp[1]+yOffset))

            } else {
                upInterupt = true
            }
            if (listOf(coordinateX,pp[1]-yOffset) !in getPlayingPlayerBoard().piecePositions
                && !downInterup) {
                moves.add(listOf(coordinateX, pp[1] - yOffset))
            } else {
                downInterup = true
            }

        }
        upInterupt = false
        downInterup = false
        for ((yOffset, coordinateX) in (pp[0]downTo 1).withIndex()){
            if (yOffset == 0) continue
            when {
                pp[1]+yOffset > 8 -> break
                pp[1]-yOffset < 1 -> break
            }
            if (listOf(coordinateX,pp[1]+yOffset) !in getPlayingPlayerBoard().piecePositions
                && !upInterupt){
                moves.add(listOf(coordinateX,pp[1]+yOffset))
            } else {
                upInterupt = true
            }
            if (listOf(coordinateX,pp[1]-yOffset) !in getPlayingPlayerBoard().piecePositions
                && !downInterup) {
                moves.add(listOf(coordinateX, pp[1] - yOffset))
            } else {
                downInterup = true
            }
        }
        println("queen: $moves")
        return moves
    }


}