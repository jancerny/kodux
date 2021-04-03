package id.cerny.jan.kodux.sample.kodux

import id.cerny.jan.kodux.Store

typealias AppStore = Store<AppState>

val store = AppStore(AppState())