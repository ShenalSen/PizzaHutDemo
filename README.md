# Task 1

## Pizza Hut Demo

This is the first task of the Java Training Program. In this task, you will be creating a simple command line java app for make Pizza order from Pizza Hut.

### Requirements

- Use IntelliJ IDEA Community Edition
- Use Java 17 or higher version
- Create a Gradle project
- Use menuList.json data to populate program data.

***Note** : It would be better if you can read menuList.json file & load that data in your app programmatically. Or if you like you can hard code those data*

Your task is to create a simple command line app with java to read and process user inputs.

-----------------------------------------------------------
### Home View

When the app is run, first it should print following text.

```
Welcome to PizzaHut!
To View our menu, press [1]
To place an Order, press [2]
Press [x] to exit the store

```

If user enter x to exit store, you should print,

```
Thank you for visiting Pizza Hut, See you next time
```
and exit the program.


If user enter invalid number, your program should print as following.

```
Please enter valid option from bellow
To View our menu, press [1]
To place an Order, press [2]

```
-----------------------------------------------------------
### Main Menu View

When user enter 1, your program should print following.

```
PizzaHut Menu

#1 BBQ Chicken Pizza - 1299.00 LKR
#2 Devil Chicken Pizza - 1499.00 LKR
#3 Vegi Pizza - 999.00 LKR
#4 Pork Pizza - 1999.00 LKR


Press item number to view description
OR
Press [0] to go back
```

If user enter an invalid input, your program should print following.

```
Please enter a valid input
PizzaHut Menu

#1 BBQ Chicken Pizza - 1299.00 LKR
#2 Devil Chicken Pizza - 1499.00 LKR
#3 Vegi Pizza - 999.00 LKR
#4 Pork Pizza - 1999.00 LKR


Press item number to view description
OR
Press [0] to go back
```

When user enter any number in the given menu list, it should display the details of the relevant menu item as following. For example, think user has entered 1.

```
Name        : <Name of the item>
Description : <Description of the item>
Price       : <Price of the item> <currency code>

Press any to go back
```

for example, if user enter [1] then it should show as following.

```
Name        : BBQ Chicken Pizza
Description : BBQ Chicken brest, Mozzarella Cheese, Bell Pepper
Price       : 1299.00 LKR

Press any to go back
```

When user press [0], it should go back to previous menu option.

-------------------------------------------------------
### Make Order Screen

When user press [2] from the main menu view, it will go to order screen.

The order screen will be as following.

```
You can buy 3 items.
Please select first item you want to buy.

#1 BBQ Chicken Pizza - 1299.00 LKR
#2 Devil Chicken Pizza - 1499.00 LKR
#3 Vegi Pizza - 999.00 LKR
#4 Pork Pizza - 1999.00 LKR


Press item number to select first item
OR
Press [0] to go back to Main menu
```

When user select 1st item, then it should show the screen as following.

```
Please select second item you want to buy.

#1 BBQ Chicken Pizza - 1299.00 LKR
#2 Devil Chicken Pizza - 1499.00 LKR
#3 Vegi Pizza - 999.00 LKR
#4 Pork Pizza - 1999.00 LKR

Press item number to select as second item
OR
Press [E] to complete
OR
Press [0] to go back to Main menu
```

At this stage, if user press [E], then the app should show recept view (Which will described at the end of this document).
After selecting 2nd item, it should show the screen as following to select 3rd item.

```
Please select final item you want to buy.

#1 BBQ Chicken Pizza - 1299.00 LKR
#2 Devil Chicken Pizza - 1499.00 LKR
#3 Vegi Pizza - 999.00 LKR
#4 Pork Pizza - 1999.00 LKR

Press item number to select as third item
OR
Press [E] to complete
OR
Press [0] to go back to Main menu
```

In any stage, if user press [0], your program should go back to main menu view.

When user select 3rd item or press E at 2nd or 3rd item selection, user will see the scree as following.

For example, think user had select only 2/3 items.

```
You have ordered #2 number of items
            Pizza Hut
-------------------------------------
#1 BBQ Chicken Pizza - 1299.00 LKR
#2 Devil Chicken Pizza - 1499.00 LKR

        Total : 2798.00 LKR
-------------------------------------
     Thank You For Ordering
-------------------------------------
Thank you for visiting Pizza Hut, See you next time
```

And your program will end from here.

-------------------------------------------------------------

Hope you will enjoy this task.
Happy Coding!
