# 🧱 Structural Design Patterns

Structural patterns explain **how classes and objects are composed** into larger structures while keeping those structures flexible and efficient.

**7 patterns · 13 examples · 70 Java files**

## 📚 All patterns

| # | Pattern | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [🔌 **Adapter**](./1.%20Adapter%20Design%20Pattern/README.md) | Make incompatible interfaces work together. | 1 | 5 |
| 2 | [🌉 **Bridge**](./2.%20Bridge%20Design%20Pattern/README.md) | Decouple an abstraction from its implementation. | 1 | 9 |
| 3 | [🌳 **Composite**](./3.%20Composite%20Design%20Pattern/README.md) | Treat single objects and groups of objects uniformly. | 3 | 12 |
| 4 | [🎁 **Decorator**](./4.%20Decorator%20Design%20Pattern/README.md) | Add behaviour to objects dynamically by wrapping them. | 2 | 14 |
| 5 | [🏛️ **Facade**](./5.%20Fascade%20Design%20Pattern/README.md) | One simple entry point to a complex subsystem. | 1 | 8 |
| 6 | [🪶 **Flyweight**](./6.%20Flyweight%20Design%20Pattern/README.md) | Share common state to support huge numbers of objects. | 3 | 13 |
| 7 | [🛡️ **Proxy**](./7.%20Proxy%20Design%20Pattern/README.md) | A stand-in that controls access to another object. | 2 | 9 |

## 🧭 Which one do I need?

| If you want to... | Use |
|---|---|
| Two interfaces are incompatible but must work together | [Adapter](./1.%20Adapter%20Design%20Pattern/README.md) |
| Abstraction and implementation should vary independently | [Bridge](./2.%20Bridge%20Design%20Pattern/README.md) |
| Treat a single object and a tree of objects the same way | [Composite](./3.%20Composite%20Design%20Pattern/README.md) |
| Add behaviour to an object at runtime without subclassing | [Decorator](./4.%20Decorator%20Design%20Pattern/README.md) |
| Give a simple front door to a complicated subsystem | [Facade](./5.%20Fascade%20Design%20Pattern/README.md) |
| Thousands of similar objects eat too much memory | [Flyweight](./6.%20Flyweight%20Design%20Pattern/README.md) |
| Control access / add laziness, caching or logging in front of an object | [Proxy](./7.%20Proxy%20Design%20Pattern/README.md) |

## 📖 Pattern details

### 1. 🔌 [Adapter](./1.%20Adapter%20Design%20Pattern/README.md)

> Convert the interface of a class into another interface the client expects.

### 2. 🌉 [Bridge](./2.%20Bridge%20Design%20Pattern/README.md)

> Split a class into two independent hierarchies, **abstraction** and **implementation**, so they can evolve separately.

### 3. 🌳 [Composite](./3.%20Composite%20Design%20Pattern/README.md)

> Compose objects into **tree structures** and let clients treat individual objects and compositions the same way.

- ❌ Problem - special-casing files and directories
- ✅ Solution 1 - File system
- ✅ Solution 2 - Calculator

### 4. 🎁 [Decorator](./4.%20Decorator%20Design%20Pattern/README.md)

> Attach additional responsibilities to an object **at runtime** as a flexible alternative to subclassing.

- 💡 Pizza shop
- 💡 Coffee shop

### 5. 🏛️ [Facade](./5.%20Fascade%20Design%20Pattern/README.md)

> Provide a **unified, simplified interface** to a set of interfaces in a subsystem.

### 6. 🪶 [Flyweight](./6.%20Flyweight%20Design%20Pattern/README.md)

> Use sharing to support **large numbers of fine-grained objects** efficiently.

- ❌ Problem - every robot owns its sprite
- ✅ Solution - Robot clone
- ✅ Solution - Word processor

### 7. 🛡️ [Proxy](./7.%20Proxy%20Design%20Pattern/README.md)

> Provide a **surrogate or placeholder** for another object to control access to it.

- 💡 Protection proxy - User vs ADMIN
- 💡 Filtering proxy - Website

---

Other categories: [Creational Design Patterns](../Creational%20Design%20Pattern/PATTERNS.md) · [Behavioural Design Patterns](../Behavioural%20Design%20Pattern/PATTERNS.md) · [SOLID Principles](../SOLID%20Principles/PRINCIPLES.md)
