fetch('http://localhost:8080/api/v1/current-user')
    .then(response => {
        if (!response.ok) {
            throw new Error('Сеть не отвечает');
        }
        return response.json();
    })
    .then(data => {
        createElements(data)
    })
    .catch(error => {
        console.error('Ошибка:', error);
    });

function createElements(data) {
    const container = document.getElementById('container');
    const usernameField = document.getElementById('username');

    usernameField.innerText = data.username;

    data.products.forEach(element => {
        const newDiv = document.createElement('div');
        const newElement = document.createElement('a');
        const form = document.createElement('form');
        const button = document.createElement('button');
        const csrfToken = document.createElement('input');

        const token = document.querySelector('meta[name="_csrf"]').content;
        const header = document.querySelector('meta[name="_csrf_header"]').content;

        newDiv.className = "product-div";

        newElement.innerText = `Название товара: ${element.name} Стоимость:$${element.price}`;
        newElement.href = `/products/${element.id}`;

        form.method = "post";
        form.action = `/products/del/${element.id}`;
        button.type = "submit";
        button.innerText = "Убрать из заказа";

        csrfToken.type = "hidden";
        csrfToken.name = "_csrf";
        csrfToken.value = token;

        form.appendChild(button);
        form.appendChild(csrfToken);

        newDiv.appendChild(newElement);
        newDiv.appendChild(form);

        container.appendChild(newDiv);
    });


    if(data.products.length === 0){
        document.getElementById('submit-order').style.display = 'none';
    }
}