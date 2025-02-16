fetch('http://localhost:8080/api/v1' + window.location.pathname)
    .then(response => {
        if (!response.ok) {
            throw new Error('Сеть не отвечает');
        }
        return response.json();
    })
    .then(data => {
        if (Array.isArray(data)) {
            console.error('Ошибка');  
        } else {
            addElements(data);
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
    });

function addElements(data) {
    const product_name = document.getElementById('product-name');
    const product_price = document.getElementById('product-price');
   
    product_name.innerText = data.name;
    product_price.innerText = data.price;

}
