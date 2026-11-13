# 🏛️ SOLID Principles

SOLID is a set of five object-oriented design principles that keep code maintainable, testable and flexible. Every principle here is shown the same way: a **problem** version that violates it and a **solution** version that follows it, so you can compare them side by side. Run the problem versions to see the failure (they throw on purpose).

**5 principles · 12 examples · 64 Java files**

## 📚 All principles

| # | Principle | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [🪪 **Single Responsibility**](./1.%20Single%20Responsibility%20Principle/README.md) | A class should have only one reason to change. | 2 | 8 |
| 2 | [🔓 **Open/Closed**](./2.%20Open%20Closed%20Principle/README.md) | Open for extension, closed for modification. | 2 | 11 |
| 3 | [🔁 **Liskov Substitution**](./3.%20Liskov%20Substitution%20Principle/README.md) | Subclasses must be usable wherever their parent is expected. | 4 | 20 |
| 4 | [✂️ **Interface Segregation**](./4.%20Interface%20Segregation%20Principle/README.md) | Many small, focused interfaces beat one fat interface. | 2 | 9 |
| 5 | [🔄 **Dependency Inversion**](./5.%20Dependency%20Inversion%20Principle/README.md) | Depend on abstractions, not on concrete classes. | 2 | 16 |

## 🧭 Which principle do I need?

| If you want to... | Use |
|---|---|
| One class has several unrelated jobs (calculation, database, printing) | [Single Responsibility](./1.%20Single%20Responsibility%20Principle/README.md) |
| Adding a new kind of behaviour forces you to edit a class that already works | [Open/Closed](./2.%20Open%20Closed%20Principle/README.md) |
| A subclass throws an exception or returns `null` where its parent promised real behaviour | [Liskov Substitution](./3.%20Liskov%20Substitution%20Principle/README.md) |
| A class is forced to implement interface methods it never uses | [Interface Segregation](./4.%20Interface%20Segregation%20Principle/README.md) |
| A high-level class is hard-wired to concrete low-level classes | [Dependency Inversion](./5.%20Dependency%20Inversion%20Principle/README.md) |

## 📖 Principle details

### 1. 🪪 [Single Responsibility](./1.%20Single%20Responsibility%20Principle/README.md)

> A class should have only **ONE reason to change**, meaning it should have ONE and ONLY ONE job or responsibility.

- ❌ Problem - one class, three responsibilities
- ✅ Solution - one class per responsibility

### 2. 🔓 [Open/Closed](./2.%20Open%20Closed%20Principle/README.md)

> A class should be **open for extension but closed for modification**.

- ❌ Problem - new save type means editing InvoiceDao
- ✅ Solution - interface + one class per save type

### 3. 🔁 [Liskov Substitution](./3.%20Liskov%20Substitution%20Principle/README.md)

> The **objects of a superclass should be replaceable with objects of its subclasses without breaking the application**. If class B is a subtype of class A, we should be able to replace objects of A with B without breaking the behaviour of the program. A subclass should extend the capability of the parent class, not narrow it down.

- ❌ Problem 1 - Bicycle breaks the Bike contract
- ❌ Problem 2 - Bicycle returns null for hasEngine()
- ✅ Solution 1 - Bike + Engine interface
- ✅ Solution 2 - Vehicle and EngineVehicle

### 4. ✂️ [Interface Segregation](./4.%20Interface%20Segregation%20Principle/README.md)

> **Clients should not be forced to depend on interfaces they don't use.** Instead of one large interface with many methods, it is better to have multiple smaller, focused interfaces. A client should NOT have to implement unnecessary functions it does not need.

- ❌ Problem - Waiter forced to implement chef and cleaning tasks
- ✅ Solution - ChefTasks, WaiterTasks and MaintenanceTasks

### 5. 🔄 [Dependency Inversion](./5.%20Dependency%20Inversion%20Principle/README.md)

> **High-level components should not depend on low-level components directly; instead, they should depend on abstractions.** Classes should depend on interfaces rather than concrete classes. Additionally, abstractions should not depend on details; details should depend on abstractions.

- ❌ Problem - MacBook is hard-wired to wired parts
- ✅ Solution - MacBook depends on Keyboard and Mouse

---

Other categories: [Creational Design Patterns](../Creational%20Design%20Pattern/PATTERNS.md) · [Structural Design Patterns](../Structural%20Design%20Pattern/PATTERNS.md) · [Behavioural Design Patterns](../Behavioural%20Design%20Pattern/PATTERNS.md) · [Interview Questions](../Interview%20Questions/QUESTIONS.md)
