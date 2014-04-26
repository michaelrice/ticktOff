package com.toastcoders.gnatspray

class Queue {

    String name

    static constraints = {
        name blank: false, unique: true, pattern: /[-\.a-zA-Z0-9]+/
    }
}
