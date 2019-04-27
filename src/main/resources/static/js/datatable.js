$(document).ready(function () {
    var table = $('#userTable').DataTable({
        "ajax": "/department/users/json",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "columns": [
            {"data": "id", "name": "id"},
            {"data": "fullName"},
            {"data": "birthday"},
            {"data": "gender"},
            {"data": "primaryAddress"},
            {"data": "secondAddress"},
            {"data": "country"},
            {"data": "email"},
            {"data": "cardNumber"},
            {
                'data': 'Action', 'render': function (data, type, row, meta) {
                    return '<div class="row justify-content-center"><input type="button" id="' + row.id + '" value="Edit" class="btn btn-outline-warning btnEdit" /> </div>';
                        // + '<div class="row justify-content-center"><input type="button" id="' + row.id + '" value="Delete" class="btn btn-outline-dark btnDelete" /></div>'
                }

            },
        ]
    })

    $("#userTable tbody").on("click", ".btnEdit", function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "http://localhost:8000/user/edit/" + id,
            success: function (data, status) {
                console.log(data)
            },
            error: function (xhr, status, errorThrown) {
                console.log(xhr);
                console.log(status);
                console.log(errorThrown);
            }
            //  data: id,

        });
    });

    $("#userTable tbody").on("click", ".btnDelete", function () {
        var id = this.id;
        $.ajax({
            type: "GET",
            url: "http://localhost:8000/user/delete/" + id,
            success: function (data, status) {
                console.log(data)
            },
            error: function (xhr, status, errorThrown) {
                console.log(xhr);
                console.log(status);
                console.log(errorThrown);
            }
        });
    });
});