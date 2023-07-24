const cartForm = document.getElementById("cart-form");
const cartItems = document.getElementById("cart-items");
const totalPrice = document.getElementById("total-price");
const checkOut = document.getElementById("checkout-btn");

// Attach event listeners
cartForm.addEventListener("click", handleButtonClick);
cartForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent the form from submitting
});
checkOut.addEventListener("click", function (event) {
    saveCartItems();
    window.location.href = "http://localhost:63342/untitled3/untitled3.main/payment.html";
});


function saveCartItems() {
    const cartItem = [];
    const allCheckboxes = cartItems.querySelectorAll(".item-checkbox");

    allCheckboxes.forEach(checkbox => {
        if (checkbox.checked) {
            const itemDiv = checkbox.parentElement;
            const productName = itemDiv.querySelector(".product-name").textContent;
            const productQuantity = parseInt(itemDiv.querySelector(".product-quantity").textContent);
            const productPrice = parseFloat(itemDiv.querySelector(".product-price").textContent);
            cartItem.push({
                name: productName,
                quantity: productQuantity,
                price: productPrice
            });
        }
    });

    localStorage.setItem("cartItems", JSON.stringify(cartItem));
}

function handleButtonClick(event) {
    if (event.target.classList.contains("btn-add")) {
        handleQuantityChange(event.target, 1);
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

function showTable(tableName) {
    // Hide all tables
    document.querySelectorAll('.user_table, .product_table, .order_table').forEach(table => {
        table.style.display = 'none';
    });

    // Show the selected table
    document.querySelector('.' + tableName).style.display = 'block';
}

$('#carousel-related-product').slick({
    infinite: true,
    arrows: false,
    slidesToShow: 4,
    slidesToScroll: 3,
    dots: true,
    responsive: [{
        breakpoint: 1024,
        settings: {
            slidesToShow: 3,
            slidesToScroll: 3
        }
    },
        {
            breakpoint: 600,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 3
            }
        },
        {
            breakpoint: 480,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 3
            }
        }
    ]
});