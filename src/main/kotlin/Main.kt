package org.example

import kotlinx.coroutines.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() = runBlocking {
//    test()
//    test01()
    jobTest()
}


suspend fun jobTest() {
    withContext(Dispatchers.IO) {
        val job = launch(start = CoroutineStart.LAZY) {
            logX("Coroutines start!")
        }
        job.log()
        job.cancel()
        job.log()
        delay(1500L)
    }
}


fun Job.log(){
    logX("""
        isActive = $isActive
        isCancel = $isCancelled
        isCompleted = $isCancelled
    """.trimIndent())
}
fun logX(any: Any?) {
    println(
        """
=============
$any
Thread:${Thread.currentThread().name}
============= 

    """.trimIndent()
    )
}


suspend fun test() {
    withContext(Dispatchers.Main) {
        val deferred: Deferred<String> = async {
            println("In async:${Thread.currentThread().name}")
            delay(1000)
            println("In async delay")
            return@async "Task completed"
        }
        println("result is:${deferred.await()}")
    }
}


suspend fun test01() {
    val user = getUserInfo()
    val friendList = getFriendList(user)
    val feedList = getFeedList(friendList.split(","))
    println(feedList)
}

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    println("getUserInfo")
    return "Tome"
}

suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    println("getFriendList")
    return "Jane,Mick"
}

suspend fun getFeedList(list: List<String>): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    println("getFeedList")
    return "FeedList"
}
