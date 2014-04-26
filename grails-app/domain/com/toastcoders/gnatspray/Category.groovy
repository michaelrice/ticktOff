package com.toastcoders.gnatspray

class Category {

    String name
    boolean showInList = true

    static belongsTo = [queue: Queue]

    static constraints = {
        name blank: false, unique: 'queue', pattern: /[-\.a-zA-Z0-9 ]+/
    }
}
