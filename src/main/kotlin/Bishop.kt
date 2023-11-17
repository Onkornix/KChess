class Bishop(startX:Int,startY:Int,override val player:Int) : Piece() {
    override val type = "bishop"
    override var position: MutableList<Int> = mutableListOf(startX,startY)


    override fun moves(piece: Piece): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val pp = piece.position

        for ((yOff, x) in (pp[0]..8).withIndex()){
            if (pp[1]+yOff > 8 || pp[1]-yOff < 1) break
            if (listOf(x,pp[1]+yOff) !in wholeBoard.piecePositions){
                moves.add(listOf(x,pp[1]+yOff))
            }
            if (listOf(x,pp[1]-yOff) !in wholeBoard.piecePositions) {
                moves.add(listOf(x, pp[1] - yOff))
            }
        }
        for ((yOff, x) in (pp[0]downTo 1).withIndex()){
            if (pp[1]+yOff > 8 || pp[1]-yOff < 1) break
            moves.add(listOf(x,pp[1]+yOff))
            moves.add(listOf(x,pp[1]-yOff))
        }
        println(moves)
        return moves
    }


}