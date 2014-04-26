package com.toastcoders.gnatspray

class Queue {

    String name
    List categories

    static hasMany = [
        categories: Category,
        statuses: Status,
    ]

    static constraints = {
        name blank: false, unique: true, pattern: /[-\.a-zA-Z0-9]+/
    }
}
