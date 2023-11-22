class User {

    constructor(name, lastName, age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    };

    //Getter
    get fullName() {
        return this.getConcatName();
    }


    //this is a method of class
    getConcatName() {
        return this.name + " " + this.lastName;
    }

    setName(name) {
        this.name = name
    }
}

module.exports = {
    User,
};