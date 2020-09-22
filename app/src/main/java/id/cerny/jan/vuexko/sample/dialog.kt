package id.cerny.jan.vuexko.sample

import id.cerny.jan.vuexko.navigation.Dialog

data class AppDialog(
    val title: String? = null,
    val message: String? = null
) : Dialog