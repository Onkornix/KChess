package pieces

class Queen(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "queen"
    override var position: MutableList<Int> = mutableListOf(startX,startY)
    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()

        val bishopHere = Bishop(position[0],position[1],player)
        val rookHere = Rook(position[0],position[1],player)

        moves.run {
            for(move in bishopHere.moves()) {
                add(move)
            }
            for(move in rookHere.moves()) {
                add(move)
            }
            add(listOf(position[0]+1,position[1]+1))
            add(listOf(position[0],position[1]+1))
            add(listOf(position[0]-1,position[1]+1))
            add(listOf(position[0]-1,position[1]))
            add(listOf(position[0]-1,position[1]-1))
            add(listOf(position[0],position[1]-1))
            add(listOf(position[0]+1,position[1]-1))
            add(listOf(position[0]+1,position[1]))
        }

        return moves
    }
}