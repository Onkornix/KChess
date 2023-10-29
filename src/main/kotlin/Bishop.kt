class Bishop : Piece() {

    override val type = "bishop"

    //override var nearbyPieces: List<List<String>> = listOf()

    override fun setPosition(posx: Int, posy: Int) {
        position = listOf(posx,posy)
    }
    override fun findNearbyPieces(location: List<Int>) {

    }
    override fun checkIllegal(start: List<Int>, finish: List<Int>, board: List<Piece>): Boolean {

        return false
    }

}