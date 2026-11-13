# 🎤 Interview Questions

Classic low level design interview questions, each solved with clean, runnable Java. Every question has the problem statement, requirements, a class diagram, the responsibility of each class, the design patterns used, and the follow-up questions an interviewer is likely to ask. All code compiles and runs with a `main` demo.

**6 questions · 6 examples · 68 Java files**

## 📚 All questions

| # | Question | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [⭕ **Tic Tac Toe**](./1.%20Tic%20Tac%20Toe/README.md) | Two players take turns on an NxN grid; the first to fill a whole row, column or diagonal wins. | 1 | 12 |
| 2 | [🐍 **Snake and Ladder**](./2.%20Snake%20and%20Ladder/README.md) | Players roll dice and race to the last cell; snakes pull them down and ladders lift them up. | 1 | 11 |
| 3 | [🅿️ **Parking Lot**](./3.%20Parking%20Lot/README.md) | Vehicles get a ticket and a spot of the right size on entry, and pay by parked time on exit. | 1 | 19 |
| 4 | [🛗 **Elevator**](./4.%20Elevator/README.md) | A controller dispatches hall calls to the best of several elevators using the LOOK algorithm. | 1 | 9 |
| 5 | [🚗 **Car Rental**](./5.%20Car%20Rental/README.md) | Customers search a fleet by type and dates, book, and later cancel or return. | 1 | 13 |
| 6 | [🧮 **HashMap**](./6.%20HashMap/README.md) | A key/value store built from an array of buckets, each a chain of entries, with automatic resizing. | 1 | 4 |

## 📖 Question details

### 1. ⭕ [Tic Tac Toe](./1.%20Tic%20Tac%20Toe/README.md)

> Design a **Tic Tac Toe** game for two players. Players alternate placing their symbol on an `N x N` board (3 x 3 by default). The first player to fill an entire row, column or diagonal wins. If the board fills up with no winner, the game is a draw.

### 2. 🐍 [Snake and Ladder](./2.%20Snake%20and%20Ladder/README.md)

> Design a **Snake and Ladder** game. Two or more players take turns rolling a dice and moving forward. Landing on a snake head moves the player down to its tail, landing on a ladder bottom moves the player up to its top. The first player to reach the last cell wins.

### 3. 🅿️ [Parking Lot](./3.%20Parking%20Lot/README.md)

> Design a **multi-floor Parking Lot**. A vehicle (bike, car or truck) enters, gets a free spot of the matching size and a ticket. On exit it hands back the ticket, the fee is calculated from the time parked, and the spot becomes free again.

### 4. 🛗 [Elevator](./4.%20Elevator/README.md)

> Design an **elevator system** for a building with several floors and several elevators. People press a hall button (UP/DOWN) outside, and a destination button inside the car. The system must pick a good elevator for each hall call and move every car efficiently without starving any request.

### 5. 🚗 [Car Rental](./5.%20Car%20Rental/README.md)

> Design a **car rental system**. A customer searches for a vehicle type over a date range, books an available vehicle, and later returns it or cancels. The price depends on the vehicle type, the number of days and, optionally, weekend discounts.

### 6. 🧮 [HashMap](./6.%20HashMap/README.md)

> Implement a **HashMap** from scratch: `put`, `get`, `remove`, `containsKey`, resizing, and the collision strategy, without using `java.util.HashMap`. Operations should run in **amortised O(1)**.

---

Other categories: [Creational Design Patterns](../Creational%20Design%20Pattern/PATTERNS.md) · [Structural Design Patterns](../Structural%20Design%20Pattern/PATTERNS.md) · [Behavioural Design Patterns](../Behavioural%20Design%20Pattern/PATTERNS.md) · [SOLID Principles](../SOLID%20Principles/PRINCIPLES.md)
