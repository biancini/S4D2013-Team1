Géant School for Developers 2013
================================

Assignment exercise by Team 1.

The GoldenMaster test should be refactored into specific tests on the overall engine method.

Here the text for the assignment
--------------------------------

Hi and welcome to team Gilded Rose. As you know, we are a small inn with a prime location in a prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the finest goods. Unfortunately, our goods are constantly degrading in quality as they approach their sell by date. We have a system in place that updates our inventory for us. It was developed by a no-nonsense type named Leeroy, who has moved on to new adventures. 

Your task is to add the new features to (and fix bugs in…) our system so that we can proceed with our work. First an introduction to our system:
* All items have a SellIn value which denotes the number of days we have to sell the item
* All items have a Quality value which denotes how valuable the item is
* At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:
* Once the sell by date has passed, Quality degrades twice as fast
* The Quality of an item is never negative
* “Aged Brie” actually increases in Quality the older it gets
* The Quality of an item is never more than 50
* “Sulfuras”, being a legendary item, never has to be sold or decreases in Quality
* “Backstage passes”, like aged brie, increases in Quality as it’s SellIn value approaches; Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert

Unfortunately (or fortunately...) Leeroy has left us before all required functionalities have been implemented. What we need is:
* Retail service – every day we have 5 – 10 customers who buy 2 – 6 items (exact number of customers, exact number of items and types of items are randomized). If we do not have the item that customer requires, we must not offer him anything else
* Supply service – every third day we have a delivery of 40 – 60 items (types of items and exact number are randomized). If you do not have to complete the supply – take what do you want from the randomized set
* Business rules – each item has a value. Then, you calculate purchasePrice (this is what you pay to get it) and offerPrice (this is what you get when you sell the item). Calculation rules are as follows:
```
purchasePrice = value * Quality(current)
offerPrice = value * Quality(current)*1.5
```

So, if you buy an item and sell it the same day (so the Quality does not change) you earn 50%.

**Maximize your income and avoid bankruptcy.**

Feel free to make any changes to the UpdateQuality method and add any new code as long as everything still works correctly. However, do not alter the Item class or Items property as those belong to the goblin in the corner who will insta-rage and one-shot you as he doesn’t believe in shared code ownership.
Tomorrow we are going to provide you with additional requirements related to our system. So... get ready for changes!

* We have recently signed a supplier of conjured items. This requires an update to our system: “Conjured” items degrade in Quality twice as fast as normal items
* We have bought a fridge. Quality of items stored in a fridge decreases two times slower than ones that are kept outside.
* The fridge has a limited capacity – only 20 items can be stored inside.
* The fridge requires service. Every 30 days we have to pay 2% of offerPrice of items that currently are in a fridge for a service.
