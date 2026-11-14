# 🎤 Interview Questions

Classic low level design interview questions, each solved with clean, runnable Java. Every question has the problem statement, requirements, a class diagram, the responsibility of each class, the design patterns used, and the follow-up questions an interviewer is likely to ask. All code compiles and runs with a `main` demo.

**11 questions · 11 examples · 140 Java files**

## 📚 All questions

| # | Question | What it does | Examples | Java files |
|---|---|---|---|---|
| 1 | [⭕ **Tic Tac Toe**](./1.%20Tic%20Tac%20Toe/README.md) | Two players take turns on an NxN grid; the first to fill a whole row, column or diagonal wins. | 1 | 12 |
| 2 | [🐍 **Snake and Ladder**](./2.%20Snake%20and%20Ladder/README.md) | Players roll dice and race to the last cell; snakes pull them down and ladders lift them up. | 1 | 11 |
| 3 | [🅿️ **Parking Lot**](./3.%20Parking%20Lot/README.md) | Vehicles get a ticket and a spot of the right size on entry, and pay by parked time on exit. | 1 | 19 |
| 4 | [🛗 **Elevator**](./4.%20Elevator/README.md) | A controller dispatches hall calls to the best of several elevators using the LOOK algorithm. | 1 | 9 |
| 5 | [🚗 **Car Rental**](./5.%20Car%20Rental/README.md) | Customers search a fleet by type and dates, book, and later cancel or return. | 1 | 13 |
| 6 | [🧮 **HashMap**](./6.%20HashMap/README.md) | A key/value store built from an array of buckets, each a chain of entries, with automatic resizing. | 1 | 4 |
| 7 | [🥤 **Vending Machine**](./7.%20Vending%20Machine/README.md) | Select a product, insert coins, and the machine dispenses it (and any change) by a State machine. | 1 | 13 |
| 8 | [🏧 **ATM**](./8.%20ATM/README.md) | Insert a card, enter the PIN, withdraw cash; a State machine drives the session and a greedy dispenser picks the notes. | 1 | 17 |
| 9 | [♟️ **Chess**](./9.%20Chess/README.md) | Two players move pieces by their own rules; a move that leaves your own king in check is illegal. | 1 | 15 |
| 10 | [💸 **Splitwise**](./10.%20Splitwise/README.md) | Record an expense split equally, exactly or by percentage, and track who owes whom. | 1 | 12 |
| 13 | [🏷️ **Apply Coupon**](./13.%20Apply%20Coupon/README.md) | Validate a coupon against a cart (expiry, usage, applicability) and apply the right kind of discount. | 1 | 15 |

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

### 7. 🥤 [Vending Machine](./7.%20Vending%20Machine/README.md)

> Design a **vending machine**. A customer selects a product, inserts coins until the price is covered, and the machine dispenses the product plus any change. The machine must reject operations that make no sense for where it currently is in the flow (for example inserting a coin before selecting anything).

### 8. 🏧 [ATM](./8.%20ATM/README.md)

> Design an **ATM**. A customer inserts a card, enters a PIN, and withdraws cash. The machine must dispense the exact amount using the notes it has, verify the PIN against the bank, block a card after repeated wrong PINs, and reject anything out of order.

### 9. ♟️ [Chess](./9.%20Chess/README.md)

> Design a **Chess** engine: an 8x8 board, the six piece types with their movement rules, whose turn it is, and the game-ending states (check, checkmate, stalemate). Full chess (castling, en passant, promotion) is out of scope; the follow-up questions cover how each would be added.

### 10. 💸 [Splitwise](./10.%20Splitwise/README.md)

> Design a **Splitwise-style expense sharing app**. One user pays for a shared expense; it is split among participants equally, by exact amounts, or by percentage. The app tracks a running balance between every pair of users and lets debts be settled directly.

### 13. 🏷️ [Apply Coupon](./13.%20Apply%20Coupon/README.md)

> Design a **coupon / discount system** for an e-commerce cart. Different coupons give a flat amount off, a capped percentage off, a percentage off one category, or a 'buy one get one free' deal on a category. Every coupon also has an expiry date and a usage limit, checked the same way regardless of how the discount itself is computed.

---

Other categories: [Creational Design Patterns](../Creational%20Design%20Pattern/PATTERNS.md) · [Structural Design Patterns](../Structural%20Design%20Pattern/PATTERNS.md) · [Behavioural Design Patterns](../Behavioural%20Design%20Pattern/PATTERNS.md) · [SOLID Principles](../SOLID%20Principles/PRINCIPLES.md)
