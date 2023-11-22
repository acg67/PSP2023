const mongoose = require('mongoose')

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://127.0.0.1:27017/contacts')

//create schema bd
let contactSchema = new mongoose.Schema({
    name: String,
    telephone: String,
    age: Number
});

//get model
let Contact = mongoose.model('contacts', contactSchema);

//create model
let contact1 = new Contact({
    name: "Test alex 2",
    telephone: "965258741",
    age: 30
});

//save
contact1.save().then(result => {
    console.log("Contact added:", result);
}).catch(error => {
    console.log("ERROR adding contact:", error);
});
