class Bishop : Piece() {

    override val type = "bishop"

    //override var nearbyPieces: List<List<String>> = listOf()

     fun findNearbyPieces(location: List<Int>): Boolean {

        return false
    }
     fun checkIllegal(start: List<Int>, finish: List<Int>, board: List<Piece>): Boolean {

        return false
    }

}