document.getElementById("paymentMethod").addEventListener("change", function () {
    const paymentMethod = this.value;
    const creditCardFields = document.getElementById("creditCardFields");
    const paypalFields = document.getElementById("paypalFields");

    if (paymentMethod === "credit_card") {
        creditCardFields.style.display = "block";
        paypalFields.style.display = "none";
    } else if (paymentMethod === "paypal") {
        creditCardFields.style.display = "none";
        paypalFields.style.display = "block";
    } else {
        creditCardFields.style.display = "none";
        paypalFields.style.display = "none";
    }
});
