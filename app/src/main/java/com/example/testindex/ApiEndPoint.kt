package com.example.testindex

class ApiEndPoint {

    companion object {

        private val SERVER = "http://192.168.26.217/master/"
        val CREATE = SERVER+"insert_tanda_jadi.php"
//        val READ = SERVER+"read.php"
        val DELETE = SERVER+"delete_tanda_jadi.php"
        val UPDATE = SERVER+"update_tanda_jadi.php"

    }

}