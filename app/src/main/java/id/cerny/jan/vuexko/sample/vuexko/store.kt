package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.Store

typealias AppStore = Store<AppState>

val store = AppStore(AppState())