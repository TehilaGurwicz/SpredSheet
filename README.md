# 2D Spreadsheet Engine 📊

A basic yet robust Object-Oriented 2D Spreadsheet engine implemented in **Java**. 
This project focuses on recursive evaluation, string parsing, and complex dependency management, simulating core functionalities found in popular spreadsheet software.

## ✨ Key Features

* [cite_start]**Three Cell Types:** Supports varied data entry—Text, Numbers (Double), and dynamic Formulas[cite: 46].
* [cite_start]**Advanced Formula Evaluation:** Parses and calculates complex mathematical formulas including nested parentheses and standard arithmetic operations (`+`, `-`, `*`, `/`)[cite: 47, 49, 50].
* [cite_start]**Dynamic Cell Referencing:** Formulas can reference other cells (e.g., `=A1+B2`)[cite: 51], allowing for interconnected data calculations.
* [cite_start]**Error Handling & Validation:** Automatically detects and handles invalid formulas (`ERR_FORM_WRONG`)[cite: 54, 55].
* [cite_start]**Circular Dependency Detection:** Uses a depth-calculation algorithm to identify and prevent endless reference loops (`ERR_CYCLE`)[cite: 56, 161].
* [cite_start]**GUI Integration:** Integrates with a provided Graphical User Interface for real-time visualization, editing, saving, and loading of spreadsheet data[cite: 164, 165].

## 🏗️ Technical Implementation

* [cite_start]**Recursive Parsing:** Utilizes recursive algorithms to parse and evaluate mathematical strings and resolve nested dependencies[cite: 149].
* [cite_start]**Dependency Graph:** Implements a topological sort-like approach (via computational depth) to determine the correct order of cell evaluation and flag circular references[cite: 161].
* [cite_start]**Object-Oriented Design:** Designed using strict OOP principles, separating the `Spreadsheet` logic from individual `Cell` entities, adhering to provided interfaces (`Sheet`, `Cell`, `Index2D`)[cite: 194].
* [cite_start]**Comprehensive Testing:** Logic and edge cases are validated using JUnit testing[cite: 196].

## 🛠️ Tech Stack

* **Language:** Java
* [cite_start]**Development Environment:** IntelliJ IDEA (or any standard Java IDE)[cite: 142].
* [cite_start]**Testing:** JUnit[cite: 196].

## 🚀 How to Run

1. Clone this repository to your local machine.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA).
3. Ensure you have the GUI `.jar` file included in your project dependencies (if applicable).
4. [cite_start]Run the `Ex2GUI` main class to launch the visual spreadsheet interface[cite: 167].
