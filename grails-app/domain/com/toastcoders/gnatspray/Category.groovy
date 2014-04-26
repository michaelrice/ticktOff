package com.toastcoders.gnatspray

class Category {

    String name
    Boolean hidden = false
    List subcategories

    static belongsTo = [
        queue: Queue,
        parent: Category,
    ]

    static hasMany = [subcategories: Category]

    static constraints = {
        name blank: false, unique: ['queue', 'parent'], pattern: /[-\.a-zA-Z0-9 ]+/
    }
}
