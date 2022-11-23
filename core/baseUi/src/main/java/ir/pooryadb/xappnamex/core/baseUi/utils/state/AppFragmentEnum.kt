package ir.pooryadb.xappnamex.core.baseUi.utils.state

import ir.pooryadb.xappnamex.core.baseUi.state.AppFragment

enum class AppFragmentEnum(
    private val isFullScreen: Boolean = false,
    private val resizeInputMode: Boolean = false
) : AppFragment {
    DEFAULT,

    REGISTER(resizeInputMode = true)
    ;

    override fun isFullScreen(): Boolean = isFullScreen
    override fun resizeInputMode(): Boolean = resizeInputMode
}