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

![Storyboard](https://raw.githubusercontent.com/YifanBian-bianya/private/master/storyboard.png)

## Functional requirements

### Requirement 100.0: Search for restaurants

#### Scenario

As a consumer of a restaurant, I want to be able to search for a restaurant by its name, including the specific full name or part of the name.

#### Dependencies

Restaurant search data is available and accessible.

#### Assumptions

Restaurant names are stated in English.

#### Examples
1.1

**Given** a feed of restaurant data is available  

**When** I search for "Taco"  

**Then** I should receive at least one result with these attributes:  

Restaurant name: EI Taco Veloz  
Number of comments: 10 reviews  
Address: 7 Martin Luther Ling Dr E, Corryville  

And I should receive at least one result with these attributes:  

Restaurant name: Drunken Tacos  
Number of comments: 20 reviews  
Address: 200 West Mcmillan, Corryville  

1.2

**Given** a feed of restaurant data is available  

**When** I search for "McDonald's"  

**Then** I should receive at least one result with these attributes:  

Restaurant name: McDonald's  
Number of comments: 100 reviews  
Address: 1126 E Mcmillan St, Walnut Hills  

And I should receive at least one result with these attributes:  

Restaurant name: McDonald's  
Number of comments: 110 reviews  
Address: 255 Mitchell Ave, Winton Place  

1.3

**Given** plant data feed is available  
**When** I search for "asdfghjklzxc"  
**Then** I should receive zero results (an empty list)  

### Requirement 101: Uploading Comments  

#### Scenario  

As a restaurant consumer, I want to be able to upload a review for a restaurant, which can include photos  d

#### Dependencies
Restaurant search data is available and accessible.  
The device has a camera, and the user has granted access to the camera.  
The device has picture storage and the user has granted access to read and write mobile phone storage.  


#### Assumptions  
Restaurant names are in English.  

#### Examples  

1.1
**Given** a feed of restaurant data is available  
**Given** camera permissions are available  
**When**   

- Select restaurant EI Taco Veloz  
- Click the button "Add review"  
**Then** when I navigate to the "Write Review" screen, I should see the "Upload Image" button available, after clicking "Upload Image", select "Take a photo" in the pop-up window, and I should successfully open the camera  

1.2
**Given** a feed of restaurant data is available  
**Given** read and write mobile phone storage permissions are available  
**When**  

- Select restaurant EI Taco Veloz  
- Click the button "Add review"  
**Then** when I navigate to the "Write Review" view, I should see the "Upload Image" button available, after clicking "Upload Image", select "Upload from Album" in the pop-up window, and I should be successfully opened Album and can successfully select pictures  

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
- Product Owner, Developer:Tigran Klekchyan
- Scrum Master, Developer : Grant Kollar
- UI Specialists, Developrs: Chang Xu, Yifan Bian

## Meetings

Friday 12pm Teams
