package com.toastcoders.gnatspray

class Category {

    String name
    int position
    boolean showInList = true

    static belongsTo = [queue: Queue]

    static constraints = {
    }
}
