db.restaurant.find();
db.restaurant.find({}, {_id:1, name:1, cuisine: 1, borough: 1, restaurant_id: 1});
db.restaurant.find({}, {_id:0, name:1, cuisine: 1, borough: 1, restaurant_id: 1});
db.restaurant.find({}, {_id:0, name:1, cuisine: 1, borough: 1, restaurant_id: 1, "address.zipcode": 1});
db.restaurant.find({"borough" : "Bronx"});
db.restaurant.find({"borough" : "Bronx"}).limit(5);
db.restaurant.find({"borough" : "Bronx"}).skip(5).limit(5);
db.restaurant.find({"grades.score" : {"$gt" : 90}});
db.restaurant.find({$and : [{"grades.score" : {"$gt" : 80}},{"grades.score" : {"$lt" : 100}}]});
db.restaurant.find({"address.coord.0" : {$lt : -95.754168}});
db.restaurant.find({$and : [{"cuisine" : {$ne : "American "}}, {"address.coord.0" : {$lt : -65.754168}}, {"grades.score" : {$gt : 70}}]});
db.restaurant.find({"cuisine" : {$ne : "American "}, "address.coord.0" : {$lt : -65.754168}, "grades.score" : {$gt : 70}});
db.restaurant.find({$and : [{"cuisine" : {$ne : "American "}}, {"grades.grade" : "A"}, {"borough" : {$ne : "Brooklyn "}}]}).sort({cuisine : -1});
db.restaurant.find({"name" : { $regex: "^Wil"}}, {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({"name" : { $regex: "ces$"}}, {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({"name" : { $regex: "Reg"}}, {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({borough: "Bronx", cuisine: {$in: ["American ","Chinese"]}}, {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({$or: [{"borough": "Staten Island"}, {"borough": "Bronxor Brooklyn"}, {"borough": "Queens"}]}, {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find( {borough: {$nin: ["Staten Island","Queens","Bronx","Brooklyn"]}} , {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({"grades.score": {$lte: 10}}, {_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({$nor: [{cuisine: {$in: ["American ","Chinese"]}},{name: "^Wil"}]},{_id:0, restaurant_id:1, name:1, borough:1, cuisine:1});
db.restaurant.find({"grades" : {$elemMatch: {"date": ISODate("2014-08-11T00:00:00Z"), "grade":"A", "score":11}}}, {_id:0, restaurant_id:1, name:1, grades:1});
db.restaurant.find({$and: [{"grades.1.grade":"A"}, {"grades.1.score": 9}, {"grades.1.date": ISODate("2014-08-11T00:00:00Z")}]},{_id:0, restaurant_id:1, name:1, grades:1});
db.restaurant.find({$and : [{"address.coord.1": {$gt : 42}},{"address.coord.1": {$lte : 52}}]}, {_id:0, restaurant_id:1, name:1, address:1});
db.restaurant.find({},{_id:0, name:1}).sort( {name: 1});
db.restaurant.find({},{_id:0, name:1}).sort( {name: -1});
db.restaurant.find({}, {_id:0, cuisine:1, borough:1}).sort({cuisine: 1, borough: -1});
db.restaurant.find({"address.street": {$regex: "Street"}}),db.restaurant.find({"address.street": {$ne: {$regex: /Street/}}});
db.restaurant.find({"address.coord": {$type: "double"}}, {_id:0, address:1});
db.restaurant.find({"grades": {$elemMatch: {"score": {$mod: [7,0]}}}},{_id:0, restaurant_id:1, name:1, grades:1});
db.restaurant.find({name: {$regex: "mon"}},{_id:0, name:1, borough:1, "address.coord":1, cuisine:1});
db.restaurant.find({name: {$regex: "^Mad"}},{_id:0, name:1, borough:1, "address.coord":1, cuisine:1});