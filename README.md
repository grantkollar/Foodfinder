---

# Foodfinder

## Intro

moved? Traveling? Want to try a new cuisine? Food Finder can:

- Recommend restaurants based on your location and food preferences.
- Provide restaurant information, consumer reviews and consumer discounts
- Allows you to freely comment on restaurants

If you're a restaurant owner, Food Finder is still helpful:

- Register a restaurant and write your own restaurant homepage
- Recommend your restaurant information to customers

Use Food Finder to accurately and quickly select restaurants and cuisines, or increase the sales of your restaurant

## Storyboard

## Functional requirements

## Class Diagram
![Class Diagram](/FoodFinderClassDiagram.drawio.png)
## Class Diagram Description
- App Module is a container for all the code within the project.
- Eatiers is the parent of a restaurant it will store what category the resturuant falls under.
- Resturuant contains attributes about the restaurant.
- Reviews is class that has the information about user feedback on a specific restaurant.
- Restaurant menu is a one to one relationship with restaurant and has the data about the items the restaurant offers.
- Food is a many to one with a menu this class has the food name and price.
- The Interfaces in dao are used to pull information from the database about the restaurants
- The services package include interfaces that allow the data from the dao to interact with the app.

## Product Backlog

## Scrum
