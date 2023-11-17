class Board(player:Int, pieces:MutableList<Piece>) {
    var b : MutableList<Piece> = pieces // list of Pieces a.k.a. board; hence b
    val p : Int = player

    val piecePositions: MutableList<List<Int>> = mutableListOf()

    init {
        for (piece in b){
            piecePositions.add(piece.position)
        }
    }

    fun update() {
        piecePositions.clear()
        for (piece in b) {
            piecePositions.add(piece.position)
        }
    }


}