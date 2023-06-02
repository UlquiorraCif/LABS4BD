let usersTable = $('.table');
function Book(id, name, publisher, age, author, pageCount) {
    this.id = id;
    this.name = name;
    this.publisher = publisher;
    this.age = age;
    this.author = author;
    this.pageCount = pageCount;
}
function Data(action, book) {
    this.action = action;
    this.book = book;
}

function Add() {
    let rawName = $('#inputName').val();
    let rawPublisher = $('#inputPublisher').val();
    let rawAge = $('#inputAge').val();
    let rawAuthor = $('#inputAuthor').val();
    let rawPageCount = $('#inputPageCount').val();

    let book = new Book(null, rawName, rawPublisher, rawAge, rawAuthor, rawPageCount);
    let data = new Data("add", book);

    // Отправляем данные нового пользователя на сервер в формате JSON с помощью AJAX-запроса
    $.ajax( {
        type: 'POST',
        url: 'MainServlet',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

function Refresh() {
    usersTable.find('tbody').empty();
    $.ajax({
        url: 'MainServlet',
        type: "GET",
        dataType: "json",
        success: function(data) {
            $.each(data, function(i, book) {
                let row = $("<tr>");

                let tdId = document.createElement("td");
                tdId.innerText = book.id;
                row.append(tdId);

                let tdName = document.createElement("td");
                tdName.setAttribute('contenteditable', 'true');
                tdName.innerText = book.name;
                row.append(tdName);

                let tdPublisher = document.createElement("td");
                tdPublisher.setAttribute('contenteditable', 'true');
                tdPublisher.innerText = book.publisher;
                row.append(tdPublisher);

                let tdAge = document.createElement("td");
                tdAge.setAttribute('contenteditable', 'true');
                tdAge.innerText = book.age;
                row.append(tdAge);

                let tdAuthor = document.createElement("td");
                tdAuthor.setAttribute('contenteditable', 'true');
                tdAuthor.innerText = book.author;
                row.append(tdAuthor);

                let tdPageCount = document.createElement("td");
                tdPageCount.setAttribute('contenteditable', 'true');
                tdPageCount.innerText = book.pageCount;
                row.append(tdPageCount);

                // Кнопка редактирования
                let btnEdit = document.createElement("button");
                btnEdit.setAttribute("id", "btnEdit");
                btnEdit.setAttribute("class", "btn btn-primary mr-1");
                btnEdit.innerText="Ред.";

                btnEdit.addEventListener('click', () => {
                    const row = btnEdit.parentNode.parentNode;
                    const id = row.querySelector('td:nth-child(1)').innerText;
                    const name = row.querySelector('td:nth-child(2)').innerText;
                    const publisher = row.querySelector('td:nth-child(3)').innerText;
                    const age = row.querySelector('td:nth-child(4)').innerText;
                    const author = row.querySelector('td:nth-child(5)').innerText;
                    const pageCount = row.querySelector('td:nth-child(6)').innerText;

                    Edit(new Book(id, name, publisher, age, author, pageCount));
                });

                // Кнопка удаления
                let btnDelete = document.createElement("button");
                btnDelete.setAttribute("id", "btnDelete");
                btnDelete.setAttribute("class", "btn btn-primary");
                btnDelete.innerText="Удалить";

                btnDelete.addEventListener('click', () => {
                    const row = btnDelete.parentNode.parentNode;
                    const id = row.querySelector('td:nth-child(1)').innerText;
                    const name = row.querySelector('td:nth-child(2)').innerText;
                    const publisher = row.querySelector('td:nth-child(3)').innerText;
                    const age = row.querySelector('td:nth-child(4)').innerText;
                    const author = row.querySelector('td:nth-child(5)').innerText;
                    const pageCount = row.querySelector('td:nth-child(6)').innerText;

                    Delete(new Book(id, name, publisher, age, author, pageCount));
                })

                row.append($('<td>').append(btnEdit).append(btnDelete));

                usersTable.find('tbody').append(row);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

function Delete(book) {
    let data = new Data("delete", book);
    $.ajax( {
        type: 'POST',
        url: 'MainServlet',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

function Edit(book) {
    let data = new Data("edit", book);
    $.ajax( {
        type: 'POST',
        url: 'MainServlet',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}