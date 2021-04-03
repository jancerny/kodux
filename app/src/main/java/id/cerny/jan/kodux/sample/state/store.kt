package id.cerny.jan.kodux.sample.state

import id.cerny.jan.kodux.Store

typealias AppStore = Store<AppState>

val store = AppStore(AppState())