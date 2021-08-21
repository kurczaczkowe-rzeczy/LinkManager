package pl.gungnir.linkmanager.network

import javax.inject.Inject

class DatabaseRepoImpl @Inject constructor() : DatabaseRepo {

    override fun dump(): String {
        return "Dump function!!"
    }
}