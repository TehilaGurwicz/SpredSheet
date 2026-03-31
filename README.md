# Java Spreadsheet Engine 📊

An advanced, Object-Oriented 2D Spreadsheet engine developed in **Java**. 
This project replicates core spreadsheet functionalities, featuring a custom mathematical expression parser, dynamic cell referencing, and a robust dependency resolution system to detect and prevent circular references.

## ✨ Key Features

* **Dynamic Data Types:** Seamlessly handles and differentiates between raw Text, Numbers (Doubles), and complex Formulas.
* **Mathematical Formula Engine:** Evaluates complex nested formulas (e.g., `=((A1+B2)*3)/2`) supporting standard arithmetic operations using recursive parsing algorithms.
* **Dynamic Cell Referencing:** Formulas can accurately reference other cells across the grid. The engine automatically updates dependent cells when referenced values change.
* **Circular Dependency Detection:** Implements a depth-calculation algorithm to map the dependency graph of the spreadsheet. Effectively detects and safely flags infinite reference loops (`ERR_CYCLE`) and syntax errors (`ERR_FORM_WRONG`).
* **Save & Load Functionality:** Supports exporting the spreadsheet state to a text file and importing it back without data loss.
* **Graphical User Interface (GUI):** Fully integrated with a provided GUI (`Ex2GUI` & `StdDrawEx2`) for interactive real-time editing and visual feedback.

## 🏗️ Architecture & OOP Design

* **Interface-Driven Development:** Built upon a strict set of interfaces (`Sheet`, `Cell`, `Index2D`) ensuring high modularity and loose coupling.
* **`Ex2Sheet`:** The core data structure managing the 2D grid, handling coordinate translations, and orchestrating the global depth-evaluation algorithm.
* **`SCell`:** The individual cell component responsible for determining its own type, parsing its raw string input, and computing its specific value.
* **Helper Utilities (`Helpfull` & `Ex2Utils`):** Custom utility classes containing the underlying algorithmic logic for string manipulation, formula validation, and mathematical computation.
* **Comprehensive Unit Testing:** Core logic, edge cases, and algorithmic boundaries are thoroughly verified using **JUnit** (`Ex2SheetTest`, `SCellTest`).

## 🛠️ Tech Stack

* **Language:** Java
* **Testing Framework:** JUnit
* **Architecture:** Object-Oriented Programming (OOP)

## 🚀 How to Run

1. Clone this repository to your local machine.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Ensure that the project structure recognizes the `src` folder as the sources root.
4. Run the `Ex2GUI.java` main class to launch the visual spreadsheet interface.
