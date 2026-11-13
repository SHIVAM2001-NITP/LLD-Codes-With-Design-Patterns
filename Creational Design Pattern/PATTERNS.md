# 🏭 Creational Design Patterns

Creational patterns abstract **how objects get created**. They hide construction logic, so the rest of the code depends on abstractions instead of concrete `new` calls, and the system stays flexible when the way objects are built changes.

**5 patterns · 13 examples · 40 Java files**

## 📚 All patterns

| # | Pattern | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [🏭 **Factory**](./1.%20Factory%20Design%20Pattern/README.md) | Create objects through a factory instead of calling `new` everywhere. | 1 | 5 |
| 2 | [🏗️ **Abstract Factory**](./2.%20Abstract%20Factory%20Design%20Pattern/README.md) | A factory of factories that builds families of related objects. | 1 | 10 |
| 3 | [🧰 **Builder**](./3.%20Builder%20Design%20Pattern/README.md) | Construct complex objects step by step. | 3 | 8 |
| 4 | [🧬 **Prototype**](./4.%20Prototype%20Design%20Pattern/README.md) | Create new objects by cloning an existing one. | 2 | 5 |
| 5 | [1️⃣ **Singleton**](./5.%20Singleton%20Design%20Pattern/README.md) | Exactly one instance, one global access point. | 6 | 12 |

## 🧭 Which one do I need?

| If you want to... | Use |
|---|---|
| I want callers to get an object without knowing its concrete class | [Factory](./1.%20Factory%20Design%20Pattern/README.md) |
| I need whole *families* of related objects that must be used together | [Abstract Factory](./2.%20Abstract%20Factory%20Design%20Pattern/README.md) |
| My constructor has too many (optional) parameters | [Builder](./3.%20Builder%20Design%20Pattern/README.md) |
| Creating an object is expensive, copying an existing one is cheaper | [Prototype](./4.%20Prototype%20Design%20Pattern/README.md) |
| Exactly one instance must exist and be globally reachable | [Singleton](./5.%20Singleton%20Design%20Pattern/README.md) |

## 📖 Pattern details

### 1. 🏭 [Factory](./1.%20Factory%20Design%20Pattern/README.md)

> Define one place that decides **which concrete class to instantiate**, so client code only talks to an interface.

### 2. 🏗️ [Abstract Factory](./2.%20Abstract%20Factory%20Design%20Pattern/README.md)

> Provide an interface for creating **families of related objects** without specifying their concrete classes.

### 3. 🧰 [Builder](./3.%20Builder%20Design%20Pattern/README.md)

> Separate the **construction** of a complex object from its representation so the same process can create different results.

- ❌ Problem 1 - telescoping constructor
- ❌ Problem 2 - same issue, other shape
- ✅ Solution - Builder + Director

### 4. 🧬 [Prototype](./4.%20Prototype%20Design%20Pattern/README.md)

> Specify the kind of objects to create using a **prototypical instance** and create new ones by copying it.

- ❌ Problem - rebuilding every object
- ✅ Solution - clone()

### 5. 1️⃣ [Singleton](./5.%20Singleton%20Design%20Pattern/README.md)

> Ensure a class has **only one instance** and provide a global point of access to it.

- 💡 Eager initialization
- 💡 Lazy initialization
- 💡 Synchronized method
- 💡 Double-checked locking (with `volatile`)
- 💡 Bill Pugh singleton (static holder class)
- 💡 Enum singleton

---

Other categories: [Structural Design Patterns](../Structural%20Design%20Pattern/PATTERNS.md) · [Behavioural Design Patterns](../Behavioural%20Design%20Pattern/PATTERNS.md) · [SOLID Principles](../SOLID%20Principles/PRINCIPLES.md) · [Interview Questions](../Interview%20Questions/QUESTIONS.md)
