package id.cerny.jan.kodux

typealias Action<Store> = suspend (Store) -> Unit