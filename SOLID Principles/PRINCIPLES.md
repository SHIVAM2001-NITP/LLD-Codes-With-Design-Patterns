# 🏛️ SOLID Principles

SOLID is a set of five object-oriented design principles that keep code maintainable, testable and flexible. Every principle here is shown the same way: a **problem** version that violates it and a **solution** version that follows it, using the same `Marker` / `Invoice` example so you can compare them side by side.

**2 principles · 4 examples · 19 Java files**

## 📚 All principles

| # | Principle | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [🪪 **Single Responsibility**](./1.%20Single%20Responsibility%20Principle/README.md) | A class should have only one reason to change. | 2 | 8 |
| 2 | [🔓 **Open/Closed**](./2.%20Open%20Closed%20Principle/README.md) | Open for extension, closed for modification. | 2 | 11 |

## 🧭 Which principle do I need?

| If you want to... | Use |
|---|---|
| One class has several unrelated jobs (calculation, database, printing) | [Single Responsibility](./1.%20Single%20Responsibility%20Principle/README.md) |
| Adding a new kind of behaviour forces you to edit a class that already works | [Open/Closed](./2.%20Open%20Closed%20Principle/README.md) |

## 📖 Principle details

### 1. 🪪 [Single Responsibility](./1.%20Single%20Responsibility%20Principle/README.md)

> A class should have only **ONE reason to change**, meaning it should have ONE and ONLY ONE job or responsibility.

- ❌ Problem - one class, three responsibilities
- ✅ Solution - one class per responsibility

### 2. 🔓 [Open/Closed](./2.%20Open%20Closed%20Principle/README.md)

> A class should be **open for extension but closed for modification**.

- ❌ Problem - new save type means editing InvoiceDao
- ✅ Solution - interface + one class per save type

---

Other categories: [Creational Design Patterns](../Creational%20Design%20Pattern/PATTERNS.md) · [Structural Design Patterns](../Structural%20Design%20Pattern/PATTERNS.md) · [Behavioural Design Patterns](../Behavioural%20Design%20Pattern/PATTERNS.md)
