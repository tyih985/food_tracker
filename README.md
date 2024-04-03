# My Personal Project — Meal Tracker

### <u>Project Proposal / Overview</u>

My application will be a **restaurant / food tracker** where users can keep track of the kinds of foods they eat at
different restaurants. They will be able to *log the prices of the dishes they eat*, *keep track of what restaurants
those dishes came from*, and be able to *rate how much they enjoyed each dish*. This project is of interest to me
because I have always been nonchalant when it comes to spending money when eating out. However, due to food cost
inflation in recent years, I recognize that I should be more conscious of my spending habits. With my newfound sense of
independence and responsibility as a post-secondary student, want to use this opportunity to build an application that
will help me achieve my goal of spending more responsibly.

My target audience encompasses others who want to be more mindful of their spending habits when dining out, such as
fellow post-secondary students. By providing users with a platform to log and compare the meals they eat, I want to
create a valuable tool that can help sustain one's financial goals. Whether users are looking to budget more
efficiently, or simply keep track of their favourite restaurant meals, I hope my application proves to be worthwhile.

### <u>User Stories</u>
- As a user, I want to be able to log a new dish, including its price, enjoyment level, the restaurant it came from,
  and if it was a favourite, into a list of dishes I’ve eaten
- As a user, I want to be able to view a list of all the dishes I’ve logged in the past
- As a user, I want to be able to filter my list of meal logs based on it's name, dishes from the same
  restaurant, the price of the dish, etc.
- As a user, I want to be able to edit my list of meal logs by changing information about past meal logs, or removing
  past meal logs from my list of meal logs
- As a user, I want the option to save all the dishes I’ve logged in the past to file
- As a user, I want the option to reload my saved state from file and resume exactly where I left off at some earlier 
  time
- As a user, when I select the quit option from the application main menu, I want to be reminded to save my Dish Logs
- to file and have the option to do so or not
- As a user, when I start the application, I want to be given the option to load my to-do list from file

### Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by going to the
  "Make Dish Logs" screen, filling out the fields, and pressing the "Add" button; To view the Dish Logs you have
  created, go to the "Dish Logs" screen and press the "Refresh" button
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by going to the
  "Dish Logs" screen, selecting a Dish Log (press the "Refresh" button if they are not visible yet), and pressing the
  "Remove" button
- You can generate a third action related to the user story "adding multiple Xs to a Y" by going to the
  "Edit Dish Logs" screen, filling out the fields, and pressing the "Edit" button; To view these changes, go to the 
  "Dish Logs" screen and press the "Refresh" button
- You can locate my visual component by going to the "Home" screen
- You can save the state of my application by going to the "Home" screen and pressing the "Save" button; You will also
  be prompted to save when closing the application
- You can reload the state of my application by going to the "Home" screen and pressing the "Load" button; To view the
  Dish Logs that have loaded from file, go to the "Dish Logs" screen and press the "Refresh" button

### Phase 4 Task 2:
Mon Apr 01 16:58:03 PDT 2024
Data saved to file.

Mon Apr 01 16:58:23 PDT 2024
Dish Log 1: added to list of Dish Logs.

Mon Apr 01 16:59:35 PDT 2024
Dish Log 1: set name to Changed Dish Log 1

Mon Apr 01 16:59:35 PDT 2024
Changed Dish Log 1: set restaurant to Jone's BBQ and Foot Massage

Mon Apr 01 16:59:35 PDT 2024
Changed Dish Log 1: set price to 25.5

Mon Apr 01 16:59:35 PDT 2024
Changed Dish Log 1: set enjoyment level to 2

Mon Apr 01 16:59:35 PDT 2024
Changed Dish Log 1: set favourite status to false

Mon Apr 01 16:59:40 PDT 2024
Changed Dish Log 1: removed from list of Dish Logs.

Mon Apr 01 16:59:47 PDT 2024
Data saved to file.

### Phase 4 Task 3:
Looking at my UML class diagram, I would begin refactoring the design of my program implementing the Singleton design
pattern on the ListOfDishLog class. This is because whenever my program runs, there should only ever be one instance of
ListOfDishLog, as the user should only have a single list of their Dish Logs in the application. Making use of the
Singleton design pattern would more efficiently create and maintain a single instance of ListOfDishLog by providing a
global point of access to the object, allowing its actions to be coordinated across my entire program.

Additionally, after implementing the Singleton design pattern, I would make further efforts to refactor my code to
prevent coupling / duplicated code in classes such as DishLogApp, EventLog, and ListOfDishLog. To achieve this, I would
refactor my code by making abstractions, whether it be new classes or methods, which would help make my code more
readable and maintainable.
