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
    const element = document.createElement('div');
    element.className = 'item';
    element.innerHTML = '<strong>${item.name}</strong><br>Цена: $${item.price.toFixed(2)}';

    container.appendChild(element);
});
}