//
//  DataStore.swift
//  MyToDos
//
//  Created by Stewart Lynch on 2021-04-07.
//

import Foundation
import shared

class DataStore: ObservableObject {
    
    let viewModel: TasksViewModel = TasksViewModel()
    
    @Published var toDos:[ToDo] = []
    
    init() {
        loadToDos()
    }
    
    func addToDo(_ toDo: ToDo) {
//        toDos.append(toDo)
        viewModel.addTask(task: Task(id: toDo.id, text: toDo.name, finished: toDo.completed))
    }
    
    func updateToDo(_ toDo: ToDo) {
//        guard let index = toDos.firstIndex(where: { $0.id == toDo.id}) else { return }
//        toDos[index] = toDo
        viewModel.updateTask(task: Task(id: toDo.id, text: toDo.name, finished: toDo.completed))
    }
    
    func deleteToDo(at indexSet: IndexSet) {
//        toDos.remove(atOffsets: indexSet)
        indexSet.forEach { i in
            viewModel.removeTask(id: toDos[i].id)
        }
    }
    
    func loadToDos() {
//        toDos = ToDo.sampleData
        viewModel.getTasks().watch { tasks in
            self.toDos = (tasks as? [Task])?.map { task in
                ToDo(id: task.id, name: task.text, completed: task.finished)
            } ?? []
        }
    }
    
    func saveToDos() {
        print("Saving toDos to file system eventually")
    }
}
