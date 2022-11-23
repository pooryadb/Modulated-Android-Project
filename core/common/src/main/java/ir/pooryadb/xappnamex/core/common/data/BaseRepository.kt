package ir.pooryadb.xappnamex.core.common.data

interface BaseRepository {

    val TAG: String
        get() = this::class.java.simpleName

}