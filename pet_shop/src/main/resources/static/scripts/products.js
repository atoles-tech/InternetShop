fetch('http://localhost:8080/api/v1/products')
    .then(response => {
        if (!response.ok) {
            throw new Error('Сеть не отвечает');
        }
        return response.json();
    })
    .then(data => {
        if (Array.isArray(data)) {
            createElements(data);
        } else {
            console.error('Ожидался массив, но получены данные другого типа:', data);
        }
    })
    .catch(error => {
        console.error('Ошибка:', error);
    });

function createElements(data) {
    const container = document.getElementById('container');

    data.forEach(item => {
        const element = document.createElement('a');
        element.className = 'item';
        element.setAttribute('price', item.price);
        element.setAttribute('name', item.name);
        element.href = `/products/${item.id}`;
        element.innerHTML = `${item.name} Стоимость: $${item.price.toFixed(2)}<br>`;

        container.appendChild(element);
    });
}

document.getElementById('sort').addEventListener('change', function () {
    const selectedValue = this.value;
    const items = Array.from(document.querySelectorAll('.item'));

    items.sort((a, b) => {
        const priceA = parseFloat(a.getAttribute('price'));
        const priceB = parseFloat(b.getAttribute('price'));

        return selectedValue == 1 ? priceA - priceB : priceB - priceA;
    });

    items.forEach(item => document.getElementById('container').appendChild(item));
});

document.getElementsByClassName('search-button')[0].addEventListener('click',function search() {
    const container = document.getElementById('container');
    const input = document.getElementById('search-input');

    container.innerHTML = '';

    fetch('http://localhost:8080/api/v1/products')
        .then(response => {
            if (!response.ok) {
                throw new Error('Сеть не отвечает');
            }
            return response.json();
        })
        .then(data => {
            data.forEach(item => {
                if (item.name.toLowerCase().includes(input.value)) {
                    const element = document.createElement('a');
                    element.className = 'item';
                    element.setAttribute('price', item.price);
                    element.setAttribute('name', item.name);
                    element.href = `/products/${item.id}`;
                    element.innerHTML = `${item.name} Стоимость: $${item.price.toFixed(2)}<br>`;

                    container.appendChild(element);
                }
            });
        });
});