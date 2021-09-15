//
//  ToDo.swift
//  MyToDos
//
//  Created by Stewart Lynch on 2021-04-07.
//

import Foundation

struct ToDo: Identifiable, Codable {
    var id: Int64
    var name: String
    var completed: Bool = false
    
    static var sampleData: [ToDo] {
        [
            ToDo(id: 0, name: "Get Groceries"),
            ToDo(id: 1, name: "Make Dr. Appointment", completed: true)
        ]
    }
}
