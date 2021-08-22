package pl.gungnir.linkmanager.domain.model

sealed class Result {
    object Error : Result()
    object Empty : Result()
    object Loading : Result()

    class Success(val data: List<Link>) : Result()
}

object None