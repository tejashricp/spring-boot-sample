This is very basic project to start using spring boot.
It would be retail store app having following features
Item controller
1. Get all items available in store
2. search for particular item.
3. add item to store
4. remove an item from store
CartController
5. Create a cart for a customer
6. buy an item from cart.
6. add to cart an item.


This will use
1. Dynamo db - right now uses H2 in memory db
2. spring boot - starter, test, data and security
3. spring security - implemented basic auth
4. kinesis - not implemenyed yet
5. docker and k8s - created docker image and ran on minikube
6. Used Spring RestDoc to give documentation of endpoints
8. dynamic configuration beans

7. WIP - kafka integration
9. security to use user, roles and privileges and other authentication mechanism/ redirect url after login/ method level security