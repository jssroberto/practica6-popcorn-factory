package garcia.roberto.popcornfactory244913

import java.util.ArrayList
data class Movie(var titulo: String, var image: Int, var header: Int, var sinopsis: String) {
    var seats: ArrayList<Client> = ArrayList()
}