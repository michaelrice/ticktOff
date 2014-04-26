package com.toastcoders.gnatspray

class Status {

    String name
    Boolean hidden = false

    static belongsTo = [queue: Queue]

    static constraints = {
        name blank: false, unique: 'queue', pattern: /[-\.a-zA-Z0-9 ]+/
    }
}
