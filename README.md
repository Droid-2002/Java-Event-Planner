# Java-Event-Planner

## Overview
Desktop calendar and event management application. Users can create, edit, and delete events such as meetings, birthdays, and appointments; view them in a monthly calendar; and receive in-app reminders before events start. It is an offline desktop application developed in Java Swing that allows multiple users from the same environment to manage their own events and view colleagues' availability through a dynamic planner interface.

## 🚀 Key Features

*   **Authentication and Ownership**: The system requires the selection of an 'Active User' before granting access to the main screen. Every new event automatically registers the active user as its owner. Edit and delete buttons are disabled if the event's owner is different from the active user.
*   **Dynamic Interface and Views**: The layout features a permanent monthly calendar grid on the left and a tabbed panel on the right. The tabs include a Daily View, a Weekly View, and a Team View (Persons). The Team View acts as a planner grid where rows represent 30-minute blocks and columns represent system users.
*   **Event Management**: Users can create events with mandatory fields (title, date, time, category, etc.), edit them, or delete them. It also includes a real-time keyword search feature that filters titles, descriptions, and locations.
*   **Smart Reminders**: Upon login, the system checks for events happening within the next 24 hours or events with a triggered lead time and displays them in a consolidated alert dialog. A single event can have multiple reminders associated with it.
*   **Recurring Events**: Events can be set to repeat daily, weekly, or monthly, with a strict limit of 2 to 50 occurrences. The system calculates the dates synchronously and saves each occurrence as an independent line sharing the same `recurrence_id`. When editing or deleting, users can choose to apply changes to "only this" or "this and future" occurrences.

## 🛠️ Architecture and Technical Constraints

*   **Single-Threaded UI (EDT)**: All UI code runs exclusively on the native Swing Event Dispatch Thread (EDT). Instantiating `Thread`, `Runnable`, or `SwingWorker` is strictly prohibited because Swing is not thread-safe.
*   **In-Memory Processing**: To eliminate I/O bottlenecks during navigation, the CSV files are read only once at startup. All subsequent operations manipulate the collection directly in memory.
*   **Hybrid Persistence**: Data is stored locally in two separate files: `events.csv` and `reminders.csv`. New inserts are appended to the end of the file, while edits and deletions trigger a full rewrite of the CSV based on the current state of the memory.
*   **Safe Error Handling**: All input validations and I/O exceptions are caught via `try/catch` blocks and notified to the user using `JOptionPane`. System stack traces are never exposed.

## ⚙️ How to Use

To compile and run the application, open your terminal and execute the following commands:

```bash
cd .\Java-Event-Planner\src\main\java\
javac .\Main.java
java .\Main.java
```

*Note: Upon initialization, the application will load its default state based on the active user session, reading and parsing data directly from the local files `events.csv` and `reminders.csv`.*
