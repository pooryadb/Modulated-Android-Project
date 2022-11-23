package ir.pooryadb.xappnamex.core.baseUi

abstract class BaseUiModel(
    open var id: Long = default_id
) {

    companion object {
        const val default_id = 0L
        private const val serialVersionUID: Long = 7383408148346419009
    }
}