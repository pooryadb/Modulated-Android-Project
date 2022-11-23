package ir.pooryadb.xappnamex.core.baseUi.utils

object ValidatorHelper {

    fun isPhoneNumberValid(number: String): Boolean =
        /*Patterns.PHONE.matcher(number).matches()
               &&*/ number.length == 11
            && number.startsWith("09")

    fun isTextValid(text: String): Boolean =
        text.length > 2

}