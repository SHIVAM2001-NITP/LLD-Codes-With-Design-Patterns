# 🎭 Behavioural Design Patterns

Behavioural patterns are about **how objects communicate and share responsibility**. They make interaction between objects flexible and loosely coupled.

**12 patterns · 30 examples · 173 Java files**

## 📚 All patterns

| # | Pattern | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [♟️ **Strategy**](./1.%20Strategy%20Design%20Pattern/README.md) | Swap algorithms at runtime behind a common interface. | 2 | 13 |
| 2 | [🔔 **Observer**](./2.%20Observer%20Design%20Pattern/README.md) | Notify many objects automatically when one changes. | 2 | 11 |
| 3 | [🚦 **State**](./3.%20State%20Design%20Pattern/README.md) | Let an object change behaviour when its state changes. | 1 | 12 |
| 4 | [⛓️ **Chain Of Responsibility**](./4.%20Chain%20Of%20Responsibilty%20Design%20Pattern/README.md) | Pass a request along handlers until one handles it. | 2 | 11 |
| 5 | [📟 **Command**](./5.%20Command%20Design%20Pattern/README.md) | Turn a request into a stand-alone object. | 6 | 35 |
| 6 | [📐 **Template Method**](./6.%20Template%20Design%20Pattern/README.md) | Fix the skeleton of an algorithm, let subclasses fill in the steps. | 1 | 4 |
| 7 | [💾 **Memento**](./7.%20Momento%20Design%20Pattern/README.md) | Save and restore an object's state without exposing its internals. | 1 | 4 |
| 8 | [🧮 **Interpreter**](./8.%20Interpretor%20Design%20Pattern/README.md) | Evaluate sentences of a small language by modelling its grammar as classes. | 3 | 16 |
| 9 | [🧑‍⚖️ **Mediator**](./9.%20Mediator%20Design%20Pattern/README.md) | One central object coordinates how many objects interact. | 1 | 5 |
| 10 | [🫥 **Null Object**](./10.%20Null%20Object%20Design%20Pattern/README.md) | Replace `null` with an object that does nothing. | 3 | 7 |
| 11 | [🔁 **Iterator**](./11.%20Iterator%20Design%20Pattern/README.md) | Traverse a collection without exposing its internals. | 2 | 7 |
| 12 | [🧳 **Visitor**](./12.%20Visitor%20Design%20Pattern/README.md) | Add new operations to a set of classes without modifying them. | 6 | 48 |

## 🧭 Which one do I need?

| If you want to... | Use |
|---|---|
| Swap an algorithm at runtime | [Strategy](./1.%20Strategy%20Design%20Pattern/README.md) |
| Many objects must react when one object changes | [Observer](./2.%20Observer%20Design%20Pattern/README.md) |
| Behaviour changes with the internal state of an object | [State](./3.%20State%20Design%20Pattern/README.md) |
| Pass a request along a chain until someone handles it | [Chain Of Responsibility](./4.%20Chain%20Of%20Responsibilty%20Design%20Pattern/README.md) |
| Turn a request into an object (queue, log, undo / redo) | [Command](./5.%20Command%20Design%20Pattern/README.md) |
| Fixed skeleton of an algorithm, some steps customised by subclasses | [Template Method](./6.%20Template%20Design%20Pattern/README.md) |
| Save and restore an object's state without breaking encapsulation | [Memento](./7.%20Momento%20Design%20Pattern/README.md) |
| Evaluate sentences of a tiny language / grammar | [Interpreter](./8.%20Interpretor%20Design%20Pattern/README.md) |
| Many objects talk to each other in a tangled way | [Mediator](./9.%20Mediator%20Design%20Pattern/README.md) |
| Callers keep checking `if (x == null)` | [Null Object](./10.%20Null%20Object%20Design%20Pattern/README.md) |
| Walk a collection without exposing how it is stored | [Iterator](./11.%20Iterator%20Design%20Pattern/README.md) |
| Add new operations to a stable class hierarchy without editing its classes | [Visitor](./12.%20Visitor%20Design%20Pattern/README.md) |

## 📖 Pattern details

### 1. ♟️ [Strategy](./1.%20Strategy%20Design%20Pattern/README.md)

> Define a family of algorithms, **encapsulate each one** and make them interchangeable.

- ✅ With Strategy (the solution)
- ❌ Without Strategy (the problem)

### 2. 🔔 [Observer](./2.%20Observer%20Design%20Pattern/README.md)

> Define a **one-to-many dependency** so that when one object changes state, all its dependents are notified and updated.

- 💡 Out-of-stock notification
- 💡 YouTube channel and subscribers

### 3. 🚦 [State](./3.%20State%20Design%20Pattern/README.md)

> Allow an object to **alter its behaviour when its internal state changes**; it will appear to change its class.

### 4. ⛓️ [Chain Of Responsibility](./4.%20Chain%20Of%20Responsibilty%20Design%20Pattern/README.md)

> Avoid coupling the sender of a request to its receiver by giving **several objects a chance to handle it**.

- 💡 Logging system
- 💡 ATM cash dispenser

### 5. 📟 [Command](./5.%20Command%20Design%20Pattern/README.md)

> Encapsulate a request as an object so you can **parameterise, queue, log and undo** operations.

- ✅ With Command - undo / redo
- ✅ With Command - AC remote
- ❌ Without Command - AC remote (problem)
- ❌ Without Command - AC + Bulb remote (problem)
- ✅ With Command - file operations
- ✅ With Command - file operations + undo

### 6. 📐 [Template Method](./6.%20Template%20Design%20Pattern/README.md)

> Define the **skeleton of an algorithm** in a base class and let subclasses redefine specific steps without changing its structure.

### 7. 💾 [Memento](./7.%20Momento%20Design%20Pattern/README.md)

> Capture and externalise an object's internal state so it can be **restored later**, without violating encapsulation.

### 8. 🧮 [Interpreter](./8.%20Interpretor%20Design%20Pattern/README.md)

> Given a language, define a representation of its **grammar** plus an interpreter that uses it to evaluate sentences.

- 💡 Example 1 - `a * b`
- 💡 Example 2 - `(a * b) + (c * d)`
- 💡 Example 2 - optimised

### 9. 🧑‍⚖️ [Mediator](./9.%20Mediator%20Design%20Pattern/README.md)

> Define an object that **encapsulates how a set of objects interact**, so they no longer refer to each other directly.

### 10. 🫥 [Null Object](./10.%20Null%20Object%20Design%20Pattern/README.md)

> Provide an object that implements the expected interface with **neutral, do-nothing behaviour** instead of using `null`.

- ❌ Problem - `NullPointerException`
- ❌ Solution 1 - manual `if (vehicle != null)`
- ✅ Solution 2 - Null Object

### 11. 🔁 [Iterator](./11.%20Iterator%20Design%20Pattern/README.md)

> Provide a way to **access the elements of an aggregate sequentially** without exposing its underlying representation.

- 💡 Custom iterator - Book and Library
- 💡 JDK example - `LinkedHashSet`

### 12. 🧳 [Visitor](./12.%20Visitor%20Design%20Pattern/README.md)

> Represent an operation to be performed on the elements of an object structure. Visitor lets you **define a new operation without changing the classes** of the elements it works on.

- ❌ Problem - every operation lives inside every class
- ✅ Solution 1 - Shopping cart visitors
- ✅ Solution 2 - File system visitors
- ✅ Solution 3 - Geometric shapes
- ✅ Solution 4 - Expression tree (visitor that returns a value)
- ✅ Solution 5 - Bank accounts

---

Other categories: [Creational Design Patterns](../Creational%20Design%20Pattern/PATTERNS.md) · [Structural Design Patterns](../Structural%20Design%20Pattern/PATTERNS.md) · [SOLID Principles](../SOLID%20Principles/PRINCIPLES.md)
