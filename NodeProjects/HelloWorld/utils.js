function add(a, b) {
    return a + b
}

function sub(a, b) {
    return a - b;
}

function div(a, b) {
    return a / b;
}

function multiply(a, b) {
    return a * b;
}

module.exports = {
    suma: add,
    add: add,
    sub: sub,
    div: div,
    multiply: multiply
};