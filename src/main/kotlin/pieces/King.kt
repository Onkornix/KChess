package pieces


import getPlayingPlayerBoard
import getWaitingPlayerBoard
class King(startX:Int, startY:Int, override val player: Int) : Piece() {

    override val type = "king"
    override var position: MutableList<Int> = mutableListOf(startX,startY)

    override fun moves(): MutableList<List<Int>> {
        val moves: MutableList<List<Int>> = mutableListOf()
        val posX = position[0]
        val posY = position[1]

        // silly hard coding don't care shut up.
        moves.run {
            add(listOf(posX,posY+1))
            add(listOf(posX+1,posY+1))
            add(listOf(posX-1,posY+1))

            add(listOf(posX,posY-1))
            add(listOf(posX-1,posY-1))
            add(listOf(posX+1,posY-1))

            add(listOf(posX-1,posY))
            add(listOf(posX+1,posY))
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
    // true is in check, false is not in check
    override fun isInCheck(): Boolean {


        fun getPieceAtPos(pos: List<Int>,targetPiece: String) : Piece {
            for (piece in getWaitingPlayerBoard().b) {
                if (piece.position == pos && piece.type == targetPiece) {
                    return piece
                } else {
                    continue
                }
            }
            return this //  this is ok because it will never happen (I hope)
        }


        fun checkWithPiece(piece: Piece) : Boolean {
            for (move in piece.moves()) {
                if (getPieceAtPos(move, piece.type).type == piece.type) {
                    return true
                }
            }
            return false
        }
        val pp = position
        return when {
            checkWithPiece(Queen(pp[0],pp[1],player)) -> true
            checkWithPiece(Bishop(pp[0],pp[1],player)) ->  true
            checkWithPiece(Rook(pp[0],pp[1],player)) ->  true
            checkWithPiece(Knight(pp[0],pp[1],player)) ->  true
            checkWithPiece(Pawn(pp[0],pp[1],player)) -> true
            else -> false

        }
    }

    fun isInCheckmate(checkStatus: Boolean): Boolean {
        return checkStatus || this.moves().size == 0
    }

}