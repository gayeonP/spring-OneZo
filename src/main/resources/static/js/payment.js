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

function getCartItems() {
    const cartItems = JSON.parse(localStorage.getItem("cartItems"));
    if (cartItems && Array.isArray(cartItems) && cartItems.length > 0) {
        const orderDetails = document.getElementById("order-details");
        let totalPrice = 0;

        orderDetails.innerHTML = ""; // Clear existing content

        cartItems.forEach(item => {
            const listItem = document.createElement("li");
            listItem.classList.add("list-group-item");
            listItem.innerHTML = `
                    <div class="product-info">
                        <img src="product1.jpg" alt="${item.name}" class="product-image">
                        <span class="product-name">${item.name}</span>
                        <span class="product-quantity">${item.quantity}</span>
                        <span class="product-price">${item.price.toFixed(2)}</span>
                    </div>
                `;
            orderDetails.appendChild(listItem);
            totalPrice += item.price * item.quantity;
        });

        const totalPriceElement = document.getElementById("total-price");
        totalPriceElement.textContent = totalPrice.toFixed(2);
    } else {
        // If no cart items found, display an error or redirect back to the cart page.
        console.log("No cart items found.");
    }
}

// Call getCartItems() when the payment page loads to retrieve and display the cart items
window.onload = getCartItems;
