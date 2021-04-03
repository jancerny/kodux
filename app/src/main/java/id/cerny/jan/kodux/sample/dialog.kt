package id.cerny.jan.kodux.sample

import id.cerny.jan.kodux.navigation.Dialog

data class AppDialog(
    val title: String? = null,
    val message: String? = null
) : Dialog