const cartForm = document.getElementById("cart-form");
const cartItems = document.getElementById("cart-items");
const totalPrice = document.getElementById("total-price");
const checkout = document.getElementById("checkout-btn");

// Attach event listeners
cartForm.addEventListener("click", handleButtonClick);
cartForm.addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the form from submitting
});
checkout.addEventListener("click", goToPay);

function handleButtonClick(event) {
    if (event.target.classList.contains("btn-add")) {
        handleQuantityChange(event.target, 1);
        if(localStorage.getItem("items") === null){
            localStorage.setItem("items", )
        }
    } else if (event.target.classList.contains("btn-sub")) {
        handleQuantityChange(event.target, -1);
    } else if (event.target.classList.contains("btn-remove-item")) {
        removeCartItem(event.target);
    }

    updateTotalPrice();
}

function handleQuantityChange(button, change) {
    const itemDiv = button.parentElement;
    const quantityElement = itemDiv.querySelector(".product-quantity");
    const priceElement = itemDiv.querySelector(".product-price");

    let quantity = parseInt(quantityElement.textContent);
    let price = parseFloat(priceElement.textContent);

    quantity += change;
    if (quantity < 0) quantity = 0;

    quantityElement.textContent = quantity;
    updateTotalPrice();
}

function removeCartItem(button) {
    const listItem = button.closest("li");
    listItem.remove();
    updateTotalPrice();
}

function updateTotalPrice() {
    const allCheckboxes = cartItems.querySelectorAll(".item-checkbox");
    let totalQuantity = 0;
    let totalPriceValue = 0;

    allCheckboxes.forEach(checkbox => {
        if (checkbox.checked) {
            const itemDiv = checkbox.parentElement;
            const quantityElement = itemDiv.querySelector(".product-quantity");
            const priceElement = itemDiv.querySelector(".product-price");

            let quantity = parseInt(quantityElement.textContent);
            let price = parseFloat(priceElement.textContent);

            totalQuantity += quantity;
            totalPriceValue += quantity * price;
        }
    });

    totalPrice.textContent = totalPriceValue.toFixed(2);
}

function goToPay(){
    window.location.href = "http://localhost:63342/kakaoShopping/src/main/resources/templates/payment.html?_ijt=voqum5c6ejvnrdn55a2skc8qjg&totalPrice="
    + document.getElementById("total-price").innerText;
}