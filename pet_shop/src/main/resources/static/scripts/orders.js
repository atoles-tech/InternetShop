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
    let a = 0.;
    data.orders.forEach(element => {
        const newDiv = document.createElement('div');

        newDiv.className = "order";
        newDiv.innerText = "Заказ №" + element.id + '\n' + "Адрес доставки: " + element.address + '\n' + "Позиции: ";

        element.products.forEach(el => {
            const p = document.createElement('p');
            p.className = "order-p";
            p.innerText = "Название: " + el.name + " Стоимость: $" + el.price;
            newDiv.appendChild(p);
            a += el.price;
        });

        container.appendChild(newDiv);
    });

    document.getElementById('total-price').innerText = "Итого: $" + a;

}